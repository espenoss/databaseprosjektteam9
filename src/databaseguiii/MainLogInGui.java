package databaseguiii;
import controller.*;
import databasePackage.Database;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;


//lager vindu med to felter til utfylling(brukernavn og passord).
//bruker GridLayout.

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controller.*;

class LogInGui extends JFrame{
	public static final int U_ADMIN = 0;
	public static final int U_COOK = 1;
	public static final int U_DRIVER = 2;
	public static final int U_SALES = 3;
	private JTextField userID = new JTextField(20);
	private JPasswordField password = new JPasswordField(20);
	private JLabel message = new JLabel("Log in information");
	String username = "espenme";
	String passingword = "16Sossosem06";
	String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + passingword;	
	private Database database = new Database("com.mysql.jdbc.Driver", databasename);
	
	public LogInGui(String title){
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		UserInput userID = new UserInput();
		add(userID, BorderLayout.NORTH);
		
		JButton button = new JButton("Sign in");
		Buttonlistener buttonlistener = new Buttonlistener();
		button.addActionListener (buttonlistener);
		
		add(button, BorderLayout.CENTER);  
		add(message, BorderLayout.PAGE_END);
		pack();
	}	
	
	private class Buttonlistener implements ActionListener {
		private LogIn log=new LogIn();
		public void actionPerformed(ActionEvent event) {
			String[] loginSuccess = null;
			try {
				loginSuccess = log.logIn(userID.getText(), password.getPassword(), database);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (loginSuccess.length == 1){				
				String userid = userID.getText();
				message.setText(userid + " has successfully logged in ");	
				add(message, BorderLayout.PAGE_END);
				
				String userID = loginSuccess[0];
				int userType = Integer.parseInt(loginSuccess[1]);
				String userName = loginSuccess[2];
				
				User user = null;
				
				switch(userType){
					case U_ADMIN:
						user = new Admin(userID, userName, database);
						new MainAdminGui();
						break;
					case U_COOK:
						user = new Cook(userID, userName, database);
						break;					
					case U_DRIVER:
						user = new Driver(userID, userName, database);
						break;
					case U_SALES:
						user = new Sales(userID, userName, database);
						break;						
					default:
				}
			
			}else{
				message.setText("Wrong username or password");	
			}
		}
	}    
	
	/*
	 * Argumentene til GridLayout() er:
	 * antall rader, antall kolonner,
	 * horisontal avstand mellom rutene og vertikal avstand mellom rutene
	 * de to siste i antall piksler
	 */
	
	private class UserInput extends JPanel{
		public UserInput(){
			setLayout(new GridLayout(2,2,5,5));
			JLabel text = new JLabel("Username: ", JLabel.RIGHT);
			add(text);
			add(userID);
			text = new JLabel("Password: ", JLabel.RIGHT);
			add(text);
			add(password);
		}	
	}
}

 
class MainLogInGui{
	public static void main(String[] args){
		LogInGui window = new LogInGui("Log In");
		window.setVisible(true);
	}
}

