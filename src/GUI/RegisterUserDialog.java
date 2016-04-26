package GUI;

import controller.*;

import java.awt.*;
import javax.swing.*;

/**
 * The Class RegisterUserDialog.<br>
 * Used to register new user
 */
class RegisterUserDialog extends JFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The admin user object. */
	private Admin admin = null; 
  
	/**
	 * Instantiates a new register user dialog.
	 *
	 * @param admin User object
	 */
	public RegisterUserDialog(Admin admin) { 
		this.admin = admin;
		UserDialog dialog = new UserDialog(this);
		setLayout(new FlowLayout()); 
		pack();
		// Set window location in middle of screen
		dialog.setLocationRelativeTo(null);
		// Display window
		dialog.setVisible(true);
	 } 

	/**
	 * The Class UserDialog.
	 */
	private class UserDialog extends MyDialog{

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;
		
		/** The editor. */
		private TextEditor editor = new TextEditor();
		
		/** The user i dfield. */
		private JTextField userIDfield = new JTextField(10);
		
		/** The user roles. */
		private final String userRoles[] = {"Admin", "Cook", "Driver", "Sales"}; 
		
		/** The user list. */
		private JComboBox<String> userList = new JComboBox<String>(userRoles);	
		
		/** The username field. */
		private JTextField usernameField = new JTextField(20);		
		
		/** The password field. */
		private JTextField passwordField = new JTextField(20);
		
		/**
		 * Instantiates a new user dialog.
		 *
		 * @param parent the parent
		 */
		public UserDialog(JFrame parent){
			super(parent, "New user");
			add(new JPanel(), BorderLayout.NORTH);
			add(new UserDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			setSize(500,300);
		}
				
		/**
		 * The Class UserDatapanel.
		 */
		private class UserDatapanel extends JPanel{
			
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * Instantiates a new user datapanel.
			 */
			public UserDatapanel(){
				setLayout(new GridLayout(8,1));
				
				add(new JLabel("Username: ", JLabel.LEFT));
				add(userIDfield);
				
				add(new JLabel("User type: ", JLabel.LEFT));
				add(userList);
				
				add(new JLabel("Name: ", JLabel.LEFT));
				add(usernameField);				

				add(new JLabel("Password: ", JLabel.LEFT));
				add(passwordField);
			}
		}
		
		/* (non-Javadoc)
		 * @see GUI.MyDialog#okData()
		 */
		public boolean okData(){
			// Get field values
			String userID = userIDfield.getText();	
			int userType = userList.getSelectedIndex();				
			String pword = passwordField.getText();
			String name = usernameField.getText();
			
			boolean nameOk = editor.isAlpha(userID) 
					&& name != "" && editor.isAlpha(name);
			if(!nameOk){
				JOptionPane.showMessageDialog(null, "Name cannot contain numbers");
				return false;
			}else{
				return admin.registerUser(userID, userType, name, pword);				
			}
		}
	}
}
