package GUI;

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


class LogIn extends JFrame{
	private JTextField username = new JTextField(20);
	private JTextField password = new JTextField(20);
	private JLabel message = new JLabel("Log in information");
	
	public LogIn(String title){
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		UserInput input1 = new UserInput();
		add(input1, BorderLayout.NORTH);
		
	    Button button = new Button();
	    add(button, BorderLayout.SOUTH);
	    add(message, BorderLayout.EAST);
	    pack();
	}	    
	    
	/*
	 * Lager en knapp
	 * Lager en lytter og knytter knappen til denne lytteren
	 */	  
	    
	private class Button extends JPanel{
		public Button(){
			Buttonlistener listener = new Buttonlistener();
			JButton button = new JButton("Sign in");
			add(button);
		}
	}
	
	/*
	 * Lytter etter knappetrykk
	 */
	private class Buttonlistener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JButton whichButton = (JButton)event.getSource();
			String buttonName = whichButton.getText();
			if (buttonName.equals("Sign in")){
			String name = username.getText();
			message.setText(name + " have successfully logged in ");	
			add(message);
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

class TestLogIn{
	public static void main(String[] args){
		LogIn window = new LogIn("Log In");
		window.setVisible(true);
	}
}




