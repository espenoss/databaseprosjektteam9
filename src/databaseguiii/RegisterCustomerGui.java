package databaseguiii;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class RegisterCustomerGui {
	private JTextField firstName = new JTextField(20);
	private JTextField surName = new JTextField(20);
	private JTextField email = new JTextField(20);
	private JTextField adress = new JTextField(20);
	JTextField zip_code = new JTextField("");
	String text1 = zip_code.getText();
	int  zip_codeInt= Integer.parseInt(text1);
	
	public RegisterCustomerGui(String title){
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		UserInput input = new UserInput();
		add(input, BorderLayout.NORTH);
		
		JButton button = new JButton("Register new customer");
		Buttonlistener buttonlistener = new Buttonlistener();
		button.addActionListener (buttonlistener);
		
		add(button, BorderLayout.CENTER);  
		add(message, BorderLayout.PAGE_END);
		pack();
	}	
	
	
		}
	

}