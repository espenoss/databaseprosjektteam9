 package databaseguiii;
 import controller.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import databaseguiii.UserDialog;
import databasePackage.*;

class Parentwindow extends JFrame {
	private Database database = new Database("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/ninameed?user=ninameed&password=1Le5YPPr");
	private Admin admin = new Admin("",0, "","", database); 
  
  
 public Parentwindow() throws Exception { 

	 UserDialog dialog = new UserDialog(this);
	 dialog.setVisible(true);
	 dialog.setLocation(350, 350);  // plasserer dialogen  
	 admin.registerUser(dialog.getUserId(), dialog.getUserType(), dialog.getUserName(), dialog.getPword(), database);
	 setTitle("Registrer user");
	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 setLayout(new FlowLayout());
	 setLocation(300, 300); // plasserer foreldrevinduet..
    
  } 

}

class MainUserDialog {
  static public void main(String[] args) throws Exception {
	Parentwindow test = new Parentwindow();
    test.setSize(300, 200);  
    test.setVisible(true);
  }   
}  