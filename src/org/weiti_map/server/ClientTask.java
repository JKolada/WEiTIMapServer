package org.weiti_map.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import org.weiti_map.db.MyDatabase;

public class ClientTask implements Runnable {
    private final Socket clientSocket;
    private MyDatabase myDB;
    private PrintWriter out;
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
        	
			out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
//            BufferedReader stdIn =
//                new BufferedReader(
//                    new InputStreamReader(System.in));
			objOut = new ObjectOutputStream(clientSocket.getOutputStream());
			if (out != null && in != null && objOut != null) {
				areStreamsActive = true;
			}
//            ObjectInputStream clientInputStream = new
//            		   ObjectInputStream(clientSocket.getInputStream());            

			out.println(ServerUtils.EMAIL_ADDRESS);
            if (handshake(out, in)) {
            	sendGroupPlan(out, objOut, in);
            }
            
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
    
    private void sendGroupPlan(PrintWriter out, ObjectOutputStream objOut,
			BufferedReader in) {
		// TODO Auto-generated method stub
		
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

	private boolean handshake(PrintWriter out, BufferedReader reader) {
		if (!areStreamsActive) {
			return false;
		}
    	String clientInput;
    	try {
			out.println(ServerUtils.EMAIL_ADDRESS);
//			while (true /* (clientInput = reader.readLine()) != null */) {
				clientInput = reader.readLine();				
				System.out.println(clientInput);
				if (clientInput != null && clientInput.equals(ServerUtils.EMAIL_ADDRESS)) {
			    	System.out.println("Handshake suceeded");
					return true;					
				} else {
			    	System.out.println("Handshake failed");
					return false;
				}
//				wait(500);			
//			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;	
		}
	}
    
}