 package databaseguiii;
 import controller.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import databaseguiii.UserDialog;
import databasePackage.*;

class Parentwindow extends JFrame {
  private UserDialog dialog = new UserDialog(this);
  private String userID="";
  private String name = "";
  private int userType=0;
  private String password = "";
  private Database database = new Database("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/ninameed?user=ninameed&password=1Le5YPPr");
  private User user = new User("",0, "","", database); 
  
  
 public Parentwindow() {
	  QueryMethodsController queryMethodsController = new QueryMethodsController();
      if (dialog.showDialog(user)) {
          try {
        	  queryMethodsController.RegisterUser(user.getUserID(), user.getUserType(), user.getName(), user.getPword(), database);
            } catch (Exception e) {
      		e.printStackTrace();
            }
      } else {
        System.out.println("Cancelled");
      }
      System.out.println(user); //bruker toString().
    
    setTitle("Registrer user");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new FlowLayout());
    
   
    setLocation(300, 300); // plasserer foreldrevinduet..
    dialog.setLocation(350, 350);  // plasserer dialogen
  } 
  
/*  private class ButtonListener2 implements ActionListener {
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
  }	  */  
}

class TestUserDialog {
  static public void main(String[] args) {
	Parentwindow test = new Parentwindow();
    test.setSize(300, 200);  // for � f� litt st�rrelse p� vinduet
    test.setVisible(true);
  }   
}  
