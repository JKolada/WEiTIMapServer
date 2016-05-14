package org.weiti_map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.weiti_map.db.MyDatabase;
import org.weiti_map.server.ClientTask;

import net.miginfocom.swt.MigLayout;

public class MyServerPanel extends javax.swing.JPanel {

	
	private static final long serialVersionUID = 1018897889225847727L;
	private static final int CLIENTS_MAX_AMOUNT = 10;

	private MyDatabase mDB;
	private JLabel serverIP;
	private JTextField serverPort;
	private JButton serverBtn;
	private ServerSocket socket;			

    private ExecutorService clientProcessingPool;
	private enum onOff {SERVER_ON, SERVER_OFF};
	private onOff serverState;		
	
	public MyServerPanel(MyDatabase DB) {
		super();
		mDB = DB;		
		configure();			
	}

	private void configure() {
//			try {
//				serverIP = new JLabel(Inet4Address.getLocalHost().getHostAddress());
//			} catch (UnknownHostException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
		
//		serverIP = new JLabel("192.168.0.1");
		
//		setLayout(new MigLayout("fillx"));
		serverPort = new JTextField("13131");
		serverState = onOff.SERVER_OFF;
		serverBtn = new JButton("Start server");		
		setServerButton();
		
		serverBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (serverState) {
				case SERVER_ON:
					closeServer();
					break;
				case SERVER_OFF:
					startServer();
					break;
				default:
					System.out.println("Error");
					break;
				}
				
			}
		});
		
		add(serverIP);
		add(serverPort);
		add(serverBtn);
	}

	private void startServer() {
		
		final String port = serverPort.getText();
		
		Pattern p = Pattern.compile("^([0-9]{1,4}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$");
        Matcher m = p.matcher(port);

        if (!m.matches()) {
            System.out.println("Invalid port number");
        	return;
        }        
        
        clientProcessingPool = Executors.newFixedThreadPool(CLIENTS_MAX_AMOUNT);
		   Runnable serverTask = new Runnable() {
	            @Override
	            public void run() {
	                try {
	                    socket = new ServerSocket(Integer.parseInt(port));
	                } catch (IOException e) {
	        			setServerButton(onOff.SERVER_OFF);
	                    System.err.println("Unable to process client request");
	                    e.printStackTrace();
	                }
	        			setServerButton(onOff.SERVER_ON);
	                    System.out.println("Waiting for clients to connect...");
	                    while (!socket.isClosed()) {
	                    	try {
	                    	Socket clientSocket = socket.accept();
	                    	System.out.println("Client accepted");	                    	
	                        clientProcessingPool.submit(new ClientTask(mDB, clientSocket));
	                    	} catch (IOException e) {
	                    		e.printStackTrace();
	                    	}
	                    }
	                
	            }
	        };
	        Thread serverThread = new Thread(serverTask);
	        serverThread.start();

		
	}

	private void setServerButton() {		
		String server_state = "error";
		switch (serverState) {
		case SERVER_OFF:
			server_state = "Start server";
			break;
		case SERVER_ON:
			server_state = "Stop server";
			break;
		default:
			System.out.println("Error");
			break;
		}
		serverBtn.setText(server_state);
	}
	
	private void setServerButton(onOff temp) {
		serverState = temp;
		setServerButton();
	}
	
	public void closeServer() {
		try {
			if (serverState == onOff.SERVER_ON || socket != null) {
				setServerButton(onOff.SERVER_OFF);		
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
}
