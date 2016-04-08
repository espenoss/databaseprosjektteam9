package databaseguiii;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.User;
import databaseguiii.UserDialog;
import databasePackage.*;

class Parentwindow extends JFrame {
  private User user = new User("",0, "","");
  private UserDialog dialog = new UserDialog(this);
  private String userID="";
  private String name = "";
  private int userType=0;
  private String password = "";
  private Database database = new Database("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/ninameed?user=ninameed&password=1Le5YPPr");
  
  public Parentwindow() {
    setTitle("Login");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new FlowLayout());
    JButton button = new JButton("Register new user");
    add(button);
    button.addActionListener(new ButtonListener());
    JButton button2 = new JButton("List users");
    add(button2);
    button2.addActionListener(new ButtonListener2());   
    setLocation(300, 300); // plasserer foreldrevinduet..
    dialog.setLocation(350, 350);  // plasserer dialogen
  }

  private class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent action) {
      if (dialog.showDialog(user)) {
          try {
      		QueryMethods.registerUser(user.getUserID(), user.getUserType(), user.getName(), user.getPassword(), database);
            } catch (Exception e) {
      		e.printStackTrace();
            }
      } else {
        System.out.println("Cancelled");
      }
      System.out.println(user); // bruker toString()
    }
  }
  
  private class ButtonListener2 implements ActionListener {
	  public void actionPerformed(ActionEvent action) {
		  
		  String text = "Userlist:\n";

		  String[][] dbData = null;
		  
		  try {
			  dbData = QueryMethods.viewAllUsers(database);
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		
		for(int x=0;x<dbData.length; x++){
			for(int y=0;y<dbData[x].length;y++){
				text += dbData[x][y] + " ";
			}
			text += "\n";
		}
		JOptionPane.showMessageDialog(null, text);
	  }
  }	    
}

class TestUserDialog {
  static public void main(String[] args) {
	Parentwindow test = new Parentwindow();
    test.setSize(300, 200);  // for å få litt størrelse på vinduet
    test.setVisible(true);
  }   
}  
