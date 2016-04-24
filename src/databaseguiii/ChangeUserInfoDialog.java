 package databaseguiii;
 import controller.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import databasePackage.*;

class ChangeUserInfoDialog extends JFrame {
	private Database database = new Database("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/espenme?user=espenme&password=16Sossosem06");
	private Admin admin = null; 
  
	public ChangeUserInfoDialog(User user) throws Exception { 
		admin = (Admin) user;
		UserDialog dialog = new UserDialog(this);
		dialog.setVisible(true);
		dialog.setLocation(350, 350);  
		setTitle("Registrer user");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setLocation(300, 300);    
	} 

	private class UserDialog extends MyDialog{

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
			
			try {
				users = admin.viewUserList();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
			
			try {
				admin.updateUser(userID, userType, name, pword, database);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			return true;		
		}
	}
}

