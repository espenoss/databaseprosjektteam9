package databaseguiii;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import databaseguiii.Customer;
import databaseguiii.CustomerDialog;

class Parentwindow extends JFrame {
  private Customer customer = new Customer("", "","", "", "");
  private CustomerDialog dialog = new CustomerDialog(this);

  public Parentwindow() {
    setTitle("New customer registration");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new FlowLayout());
    JButton button = new JButton("Register new customer ");
    add(button);
    button.addActionListener(new ButtonListener());
    setLocation(300, 300); // plasserer foreldrevinduet
    dialog.setLocation(350, 350);  // plasserer dialogen
  }

  private class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent action) {
      if (dialog.showDialog(customer)) {
        System.out.println("OK is pressed ...");
      } else {
        System.out.println("Cancel is pressed...");
      }
      System.out.println(customer); // bruker toString()
    }
  }
}

class MainCustomerGui {
  static public void main(String[] args) {
	Parentwindow test = new Parentwindow();
    test.setSize(500, 300);  // for å få litt størrelse på vinduet
    test.setSize(900, 600);  // for å få litt størrelse på vinduet
    test.setVisible(true);
  }   
}  
