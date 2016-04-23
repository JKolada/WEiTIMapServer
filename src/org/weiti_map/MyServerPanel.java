package org.weiti_map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.weiti_map.db.MyDatabase;

public class MyServerPanel extends javax.swing.JPanel {

	private static final long serialVersionUID = 1018897889225847727L;

	private MyDatabase mDB;
	private JLabel serverIP;
	private JTextField serverPort;
	private JButton serverBtn;
	private ServerSocket socket;	
	private int port;	
	
	private enum onOff {SERVER_ON, SERVER_OFF};
	private onOff serverState;		
	
	public MyServerPanel(MyDatabase DB) {
		super();
		mDB = DB;		
		configure();			
	}

	private void configure() {
		try {
			serverIP = new JLabel(Inet4Address.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
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
		try {
			socket = new ServerSocket(15432);
			serverState = onOff.SERVER_ON;
			setServerButton(onOff.SERVER_ON);
			System.out.println("Server listening on port " + serverPort.getText());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			serverState = onOff.SERVER_OFF;
			setServerButton();
			e.printStackTrace();
		}		
		
		while(true) {
			// accept a new connection
			Socket client;
			try {
				client = socket.accept();
				Thread thrd = new Thread(new ServerThread(client));
//				list.add(thrd);
				thrd.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// start a new ServerThread to handle the connection and send
			// output to the client
			
//			numThreads.incrementAndGet();
//			System.out.println("Thread " + numThreads.get() + " started.");

		}		
		
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
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		setServerButton(onOff.SERVER_OFF);		
	}	
	
}
