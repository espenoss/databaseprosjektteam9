package databaseguiii;

import java.awt.BorderLayout;

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


class LogInGui extends JFrame /*implements ActionListener*/{
	private JTextField username = new JTextField(20);
	private JTextField password = new JTextField(20);
	private JLabel message = new JLabel("Log in information");
	
/*	public static boolean authenticate(String username, String password){
		if(username.equals("WHATEVER") && password.equals("WHATEVER")){
			return true;
		}
		return false;
	}       */
	
	public LogInGui(String title){
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		UserInput input = new UserInput();
		add(input, BorderLayout.NORTH);
		
		JButton button = new JButton("Sign in");
		Buttonlistener buttonlistener = new Buttonlistener();
		button.addActionListener (buttonlistener);
		
		add(button, BorderLayout.CENTER);  
		add(message, BorderLayout.PAGE_END);
		pack();
	}	    
	    
	private class Buttonlistener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JButton button = (JButton)event.getSource();
			String buttonName = button.getText();
			if (buttonName.equals("Sign in")){
			String name = username.getText();
			message.setText(name + " have successfully logged in ");	
			add(message, BorderLayout.PAGE_END);
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
			add(username);
			text = new JLabel("Password: ", JLabel.RIGHT);
			add(text);
			add(password);
		}	
	}
}

class TestLogInGui{
	public static void main(String[] args){
		LogInGui window = new LogInGui("Log In");
		window.setVisible(true);
	}
}




