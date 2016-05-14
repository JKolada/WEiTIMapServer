package org.weiti_map.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.weiti_map.db.MyDatabase;

import com.example.kuba.weitimap.db.GroupPlanObject;

public class ClientTask implements Runnable {
	private final Socket clientSocket;
	private MyDatabase myDB;
	private DataOutputStream outData;
	private DataInputStream inData;
	private ObjectOutputStream objOut;

	public ClientTask(MyDatabase mDB, Socket clientSocket2) {
		myDB = mDB;
		this.clientSocket = clientSocket2;
	}

	@Override
	public void run() {

		System.out.println("Client connected");
		printSocketInfo(clientSocket);
		
//		SSLSession session = clientSocket.getSession();
//		Certificate[] cchain2 = session.getLocalCertificates();
//		System.out.println(cchain2.toString());
//		for (int i = 0; i < cchain2.length; i++) {
//		  System.out.println(((X509Certificate) cchain2[i]).getSubjectDN());
//		}   
		
		try {
			inData = new DataInputStream(clientSocket.getInputStream());
			outData = new DataOutputStream(clientSocket.getOutputStream());		 
		} catch (IOException e) {
			System.out.println("Writers/readers creation failed");			
			e.printStackTrace();
		}
			
		Message msg;
		while ((msg = receiveMessageObj()) == null) {};
		
		if (msg.getType() == Message.MessageType.HANDSHAKE) {
			sendMessage(new Message(Message.MessageType.HANDSHAKE));
			
			Message getGroupMessage = null;
			while ((getGroupMessage = receiveMessageObj()) == null) {};
			GroupPlanObject groupObjToSend = getGroupMessage.getGroupPlanobject(myDB);
			if (groupObjToSend == null) {
				sendMessage(new Message(Message.MessageType.SEND_GROUP, ServerUtils.GROUP_DOESNT_EXIST));
			} else {
				sendMessage(new Message(Message.MessageType.SEND_GROUP, ServerUtils.GROUP_EXISTS));
				try {
					objOut = new ObjectOutputStream(clientSocket.getOutputStream());
					objOut.writeObject(groupObjToSend);
					System.out.println("Group object sent and closing object stream.");
					objOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("Received a message that is not a handshake message.");
		}
		shutdown();	   
	}
	
	private void shutdown() {
		try {		   
			outData.close();
			inData.close();
			clientSocket.close();
			System.out.println("Connection ended");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void printSocketInfo(Socket clientSocket2) {
		System.out.println("   Socket class:          " + clientSocket2.getClass());
		System.out.println("   Remote address       = "
			+ clientSocket2.getInetAddress().toString());
		System.out.println("   Remote port          = " + clientSocket2.getPort());
		System.out.println("   Local socket address = "
			+ clientSocket2.getLocalSocketAddress().toString());
		System.out.println("   Local address        = "
			+ clientSocket2.getLocalAddress().toString());
		System.out.println("   Local port           = " + clientSocket2.getLocalPort());
//		 System.out.println("   Need client authentication = "
//			+ clientSocket2.getNeedClientAuth());
//		 SSLSession ss = clientSocket2.getSession();
//		 System.out.println("   Cipher suite = "+ss.getCipherSuite());
//		 System.out.println("   Protocol = "+ss.getProtocol());	   
	}
		
	private void sendMessage(Message msg) {
		sendMessageBytes(msg.toString());	   
	}

	private void sendMessageBytes(String message){
		byte[] msgBytes = message.getBytes();
//		String hexString = ServerUtils.bytesToHex(msgBytes);		
//		System.out.println("\nHex of actual message:");
//		System.out.println(hexString);
		
		int msgLength = 4 + msgBytes.length;
		
		byte[] msgLengthBytes = ByteBuffer.allocate(4).putInt(msgLength).array();
//		hexString = ServerUtils.bytesToHex(msgLengthBytes);	 
//		System.out.println("Hex of string length:");
//		System.out.println(hexString);
		
		byte[] combined = new byte[msgLengthBytes.length + msgBytes.length];
		System.arraycopy(msgLengthBytes, 0, combined, 0					, msgLengthBytes.length);
		System.arraycopy(msgBytes,	   0, combined, msgLengthBytes.length, msgBytes.length);
							  
//		hexString = ServerUtils.bytesToHex(combined);   
//		System.out.println("Hex of overall message:");	  
//		System.out.println(hexString);	  
		
		try {
			outData.write(combined);
		} catch (IOException e) {
			e.printStackTrace();
		}	   
		return;
	}   

	private Message receiveMessageObj() {
		String msgString = receivePrefixedMessage();
		if (msgString == null) return null;
		String regexp = "<(" + ServerUtils.MSG_TYPES_REGEXP + ")/([\\S]+)>";
		Pattern pattern = Pattern.compile(regexp);
		Matcher m = pattern.matcher(msgString);
		boolean b = m.matches();
		if (!b) {
			System.out.println("Wrong message received");
			shutdown();
		}
		String msg_type = m.group(1);
		String msg_param = m.group(2);

		return new Message(msg_type, msg_param);
	}

	private String receivePrefixedMessage() {
		byte[] prefixBuffer = new byte[4];
		int prefixBytesToRead = 4;
		int prefixBytesRead = 0;

		// prefix reading
		while (prefixBytesToRead > 0) {
			try {
				int n = inData.read(prefixBuffer, prefixBytesRead, prefixBytesToRead);
				if (n == 0) {
					return null;
				}
				prefixBytesToRead -= n;
				prefixBytesRead += n;

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

//		String hexString = ServerUtils.bytesToHex(prefixBuffer);
//		System.out.println("Hex of message's length: " + hexString);
		// end of prefix reading

		final ByteBuffer b = ByteBuffer.wrap(new String(prefixBuffer).getBytes());
//		b.order(ByteOrder.BIG_ENDIAN);
		int dataLength = b.getInt() - 4;
//		System.out.println("DataLength: " + dataLength);

		// actual message reading
		int dataBytesToRead = dataLength;
		int dataBytesRead = 0;
		
		if (dataLength <= 0) {
			return null;
		}

		byte[] dataBuffer = new byte[dataLength];
		while (dataBytesToRead > 0) {
			int n;
			try {
				n = inData.read(dataBuffer, dataBytesRead, dataBytesToRead);
//				System.out.println("read bytes number: " + n);
				if (n == 0) return null;
				dataBytesRead += n;
				dataBytesToRead -= n;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// end of actual message reading

//		hexString = ServerUtils.bytesToHex(dataBuffer);
//		System.out.println("Hex actual message: " + hexString);

		String ret = new String(dataBuffer);
//		System.out.println(ret);
		return ret;
	}
}