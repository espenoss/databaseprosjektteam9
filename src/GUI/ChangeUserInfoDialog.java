package GUI;
import controller.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * The Class ChangeUserInfoDialog.<br>
 * Used to change user information.
 */
class ChangeUserInfoDialog extends JFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The admin object */
	private Admin admin = null; 

	/**
	 * Instantiates a new change user info dialog.
	 *
	 * @param user User object
	 */
	public ChangeUserInfoDialog(User user){ 
		admin = (Admin) user;
		// Add dialog		
		UserDialog dialog = new UserDialog(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
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
		
		/** The user id field. */
		private JComboBox<Object> userIDfield;
		
		/** The user roles. */
		private final String userRoles[] = {"Admin", "Cook", "Driver", "Sales"}; 
		
		/** The user list. */
		private JComboBox<String> userList = new JComboBox<String>(userRoles);	
		
		/** The username field. */
		private JTextField usernameField = new JTextField(20);		
		
		/** The password field. */
		private JTextField passwordField = new JTextField(20);
		
		/** The password. */
		private String pword;
		
		/** The name. */
		private String name;
		
		/** The user id. */
		private String userID;
		
		/** The user type. */
		private int userType;
		
		/** The users. */
		private ArrayList<User> users = new ArrayList<User>();

		/**
		 * Instantiates a new user dialog.
		 *
		 * @param parent the parent
		 */
		public UserDialog(JFrame parent){
			super(parent, "Fill in new info about the user");

			// Fetch users from database
			users = admin.viewUserList();

			// Convert to string list
			ArrayList<String> names = new ArrayList<>();
			for(User u:users){
				names.add(u.getUserID());
			}
			
			// Set up user selection box
			userIDfield = new JComboBox<Object>(names.toArray());
			userIDfield.addActionListener(new listListener());

			// Add components
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
				GridLayout superGrid = new GridLayout(8,1);
				setLayout(superGrid);
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

		/**
		 * Listens to user sekect box
		 */
		private class listListener implements ActionListener{
			
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e){
				// Get user information
				int userIndex = userIDfield.getSelectedIndex();	
				User currUser = users.get(userIndex);
				userList.setSelectedIndex(currUser.getUserType());
				usernameField.setText(currUser.getName());
			}
		}

		/* (non-Javadoc)
		 * @see GUI.MyDialog#okData()
		 */
		public boolean okData(){
			// Get field information
			int userIndex = userIDfield.getSelectedIndex();	
			userID = users.get(userIndex).getUserID();
			userType = userList.getSelectedIndex();				
			pword = passwordField.getText();
			name = usernameField.getText();

			// Check if data is valid
			boolean nameOk = editor.isAlpha(userID) 
					&& name != "" && editor.isAlpha(name);
			if(!nameOk) {
				JOptionPane.showMessageDialog(null, "Name cannot contain numbers");
				return false;
			}

			// Update info in database
			return admin.updateUser(userID, userType, name, pword);
		}
	}
}

