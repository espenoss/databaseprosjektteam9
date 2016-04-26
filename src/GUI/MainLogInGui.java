package GUI;
import controller.*;
import database.Database;

import java.awt.BorderLayout;
import java.awt.Font;

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


/*
 * LogInGui
 * Used to log in. Takes user id and password, and opens
 * appropriate menu according to usertype. 
 */
class LogInGui extends JFrame{
	
	/** The Constant serialVersionUID. */	
	private static final long serialVersionUID = 1L;
	
	/** User type constants*/
	private static final int U_ADMIN = 0;
	private static final int U_COOK = 1;
	private static final int U_DRIVER = 2;
	private static final int U_SALES = 3;
	
	/** User ID entry field */
	private JTextField userID = new JTextField(20);
	
	/** User password entry field*/
	private JPasswordField password = new JPasswordField(20);

	// Log in label
	private JLabel message = new JLabel("Log in information");

	// Database login information
	private String username = "espenme";
	private String passingword = "16Sossosem06";
	private String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + passingword;	
	private Database database = new Database("com.mysql.jdbc.Driver", databasename);

	public LogInGui(){
		setTitle("Log in");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		UserInput userID = new UserInput();
		add(userID, BorderLayout.NORTH);

		Font bigText = new Font("SansSerif", Font.PLAIN, 25);
		
		JButton button = new JButton("Sign in");
		Buttonlistener buttonlistener = new Buttonlistener();
		button.addActionListener (buttonlistener);
		button.setFont(bigText);
		add(button, BorderLayout.CENTER);  

		add(message, BorderLayout.PAGE_END);
		setSize(500,250);
		setLocationRelativeTo(null);

	}	

	// Triggers on pressing 'sign in' button
	private class Buttonlistener implements ActionListener {
		// LogIn class from controller
		private LogIn log = new LogIn();
		public void actionPerformed(ActionEvent event) {
			String[] loginSuccess = null;
			
			// Check if log in information exists in database
			loginSuccess = log.logIn(userID.getText(), password.getPassword(), database);

			// If returned log in information
			if (loginSuccess != null){							
				String userid = userID.getText();
				message.setText(userid + " has successfully logged in ");	
				add(message, BorderLayout.PAGE_END);

				String userID = loginSuccess[0];
				int userType = Integer.parseInt(loginSuccess[1]);
				String userName = loginSuccess[2];

				// Open menu base on usertype
				switch(userType){
				case U_ADMIN:
					MainAdminGui admingui = new MainAdminGui(new Admin(userID, userName, database));
					admingui.setVisible(true);
					break;
				case U_COOK:
					MainCookGui cookgui = new MainCookGui(new Cook(userID, userName, database));
					cookgui.setVisible(true);
					break;					
				case U_DRIVER:
					MainDriverGui deliverylist = new MainDriverGui(new Driver(userID, userName, database));
					deliverylist.setVisible(true);						
					break;
				case U_SALES:
					MainSalesPersonGui salesgui = new MainSalesPersonGui(new Sales(userID, userName, database));
					salesgui.setVisible(true);
					break;						
				}
				
				// Hide login window
				setVisible(false);

			}else{
				message.setText("Wrong username or password");	
			}
		}
	}    

	private class UserInput extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

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


/**
 * The Class MainLogInGui.
 */
class MainLogInGui{
	
	/**
	 * The main method.<br>
	 * Opens login dialog.	
	 */
	public static void main(String[] args){
		LogInGui window = new LogInGui();
		window.setVisible(true);
	}
}

