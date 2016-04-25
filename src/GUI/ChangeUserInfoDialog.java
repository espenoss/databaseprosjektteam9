package GUI;
import controller.*;
import database.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

class ChangeUserInfoDialog extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Admin admin = null; 

	public ChangeUserInfoDialog(User user){ 
		admin = (Admin) user;
		UserDialog dialog = new UserDialog(this);
		setTitle("Registrer user");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	} 

	private class UserDialog extends MyDialog{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private TextEditor editor = new TextEditor();
		private JComboBox<Object> userIDfield;
		private final String userRoles[] = {"Admin", "Cook", "Driver", "Sales", "Storage"}; 
		private JComboBox<String> userList = new JComboBox<String>(userRoles);	
		private JTextField usernameField = new JTextField(20);		
		private JTextField passwordField = new JTextField(20);
		private String pword;
		private String name;
		private String userID;
		private int userType;
		private ArrayList<User> users = new ArrayList<User>();

		public UserDialog(JFrame parent){
			super(parent, "Fill in new info about the user");

			users = admin.viewUserList();

			ArrayList<String> names = new ArrayList<>();
			for(User u:users){
				names.add(u.getName());
			}
			userIDfield = new JComboBox<Object>(names.toArray());
			userIDfield.addActionListener(new listListener());

			add(new JPanel(), BorderLayout.NORTH);
			add(new UserDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);

			setSize(500,300);
			setLocationRelativeTo(null);
		}

		private class UserDatapanel extends JPanel{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

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

		private class listListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				int userIndex = userIDfield.getSelectedIndex();	
				User currUser = users.get(userIndex);
				userList.setSelectedIndex(currUser.getUserType());
				usernameField.setText(currUser.getName());
			}
		}

		public boolean okData(){
			int userIndex = userIDfield.getSelectedIndex();	
			userID = users.get(userIndex).getUserID();
			userType = userList.getSelectedIndex();				
			pword = passwordField.getText();
			name = usernameField.getText();

			boolean nameOk = editor.isAlpha(userID) 
					&& name != "" && editor.isAlpha(name);
			if(!nameOk) JOptionPane.showMessageDialog(null, "Name cannot contain numbers");

			return admin.updateUser(userID, userType, name, pword);
		}
	}
}

