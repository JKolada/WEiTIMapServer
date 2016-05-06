package org.weiti_map.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.weiti_map.db.GroupPlanObject;
import org.weiti_map.db.MyDatabase;

public class ClientTask implements Runnable {
    private final Socket clientSocket;
    private MyDatabase myDB;
    private PrintWriter out;
    private DataOutputStream outData;
    private DataInputStream inData;
    private BufferedReader in;
    private ObjectOutputStream objOut;
    private boolean areStreamsActive;

    public ClientTask(MyDatabase mDB, Socket clientSocket2) {
    	myDB = mDB;
        this.clientSocket = clientSocket2;
        areStreamsActive = false;
    }

	@Override
    public void run() {

        System.out.println("Client connected");
        printSocketInfo(clientSocket);
        
//        SSLSession session = clientSocket.getSession();
//        Certificate[] cchain2 = session.getLocalCertificates();
//        System.out.println(cchain2.toString());
//        for (int i = 0; i < cchain2.length; i++) {
//          System.out.println(((X509Certificate) cchain2[i]).getSubjectDN());
//        }   
        
        try {
        	inData = new DataInputStream(clientSocket.getInputStream());
        	outData = new DataOutputStream(clientSocket.getOutputStream());
        	
			out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            
			objOut = new ObjectOutputStream(clientSocket.getOutputStream());
			
			if (out != null && in != null && objOut != null) {
				areStreamsActive = true;
			}
//            ObjectInputStream clientInputStream = new
//            		   ObjectInputStream(clientSocket.getInputStream());            
//            if (handshake(out, in)) {
//            	sendGroupPlan(out, objOut, in);
//            }
			
			// TA DA! //
			sendMessageBytes("A");
            
		} catch (IOException e) {
            System.out.println("Writers/readers creation failed");            
			e.printStackTrace();
		}
        
        shutdown();       
    }
	
	private void shutdown() {
		try {
        	out.close();
        	in.close();
        	objOut.close();
            clientSocket.close();
            System.out.println("Connection ended");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
    
    private void sendGroupPlan() {
    	String clientInput;
		try {
			while (true) {
				clientInput = in.readLine();
				if (clientInput != null) {
					System.out.print(clientInput);
					break;
				}		
			}			
			
			Pattern pattern = Pattern.compile("GET_GROUP:_([\\S]+)");
			Matcher m = pattern.matcher(clientInput);
			boolean b = m.matches();
			if (!b) {
				System.out.print("Wrong request");
				out.println("WRONG_REQUEST");
			} else {							
				String groupName = m.group(1);
				System.out.println(groupName);
	
				if (myDB.checkGroupNameEx(groupName) == -1) {
					out.println("GROUP_DOESNT_EXIST");
					System.out.println("Wrong group object requested");
				} else {
					out.println("GROUP_EXISTS");
					GroupPlanObject groupObjToSend = myDB.getGroupPlanObject(groupName);				
				}			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
//         System.out.println("   Need client authentication = "
//            + clientSocket2.getNeedClientAuth());
//         SSLSession ss = clientSocket2.getSession();
//         System.out.println("   Cipher suite = "+ss.getCipherSuite());
//         System.out.println("   Protocol = "+ss.getProtocol());		
	}

	//TODO
//	private boolean handshake(PrintWriter out, BufferedReader reader) {
//		if (!areStreamsActive) {
//			return false;
//		}
//
//    	String clientInput;
//    	try {
//			out.println(ServerUtils.EMAIL_ADDRESS);
////			while (true /* (clientInput = reader.readLine()) != null */) {
//				clientInput = reader.readLine();				
//				System.out.println(clientInput);
//				if (clientInput != null && clientInput.equals(ServerUtils.EMAIL_ADDRESS)) {
//			    	System.out.println("Handshake suceeded");
//					return true;					
//				} else {
//			    	System.out.println("Handshake failed");
//					return false;
//				}
////				wait(500);			
////			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;	
//		}
//	}
    
	private void sendMessageBytes(String message){
		byte[] msgBytes = message.getBytes();
		String hexString = ServerUtils.bytesToHex(msgBytes);		
		System.out.println("\nHex of actual message:");
		System.out.println(hexString);
		
		int msgLength = 4 + msgBytes.length;
		
		byte[] msgLengthBytes = ByteBuffer.allocate(4).putInt(msgLength).array();
		hexString = ServerUtils.bytesToHex(msgLengthBytes);		
		System.out.println("Hex of string length:");
		System.out.println(hexString);
		
		byte[] combined = new byte[msgLengthBytes.length + msgBytes.length];
		System.arraycopy(msgLengthBytes, 0, combined, 0                    , msgLengthBytes.length);
		System.arraycopy(msgBytes,       0, combined, msgLengthBytes.length, msgBytes.length);
				
//		byte[] byteArray = new byte[] { -1, -128, 1, 127 };
//		System.out.println(Arrays.toString(combined));
			  
		hexString = ServerUtils.bytesToHex(combined);	
		System.out.println("Hex of overall message:");		
		System.out.println(hexString);
		
		
		try {
			outData.write(combined);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
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

        String hexString = ServerUtils.bytesToHex(prefixBuffer);
        System.out.println("Hex of message's length: " + hexString);
        // end of prefix reading

        final ByteBuffer b = ByteBuffer.wrap(new String(prefixBuffer).getBytes());
//        b.order(ByteOrder.BIG_ENDIAN);
        int dataLength = b.getInt() - 4;
        System.out.println("DataLength: " + dataLength);

        // actual message reading
        int dataBytesToRead = dataLength;
        int dataBytesRead = 0;
        // if dataLenght < 0 || > INFINITY ... throw something throwable

        byte[] dataBuffer = new byte[dataLength];
        while (dataBytesToRead > 0) {
            int n;
            try {
                n = inData.read(dataBuffer, dataBytesRead, dataBytesToRead);
                System.out.println("read bytes number: " + n);
                if (n == 0) return null;
                dataBytesRead += n;
                dataBytesToRead -= n;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // end of actual message reading

        hexString = ServerUtils.bytesToHex(dataBuffer);
        System.out.println("Hex actual message: " + hexString);

        String ret = new String(dataBuffer);
        System.out.println(ret);
        return ret;
    }
}