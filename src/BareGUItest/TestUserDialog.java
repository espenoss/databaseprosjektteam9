package BareGUItest;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import BareGUItest.User;
import BareGUItest.UserDialog;

class Parentwindow extends JFrame {
  private User user = new User("Maria", "1234");
  private UserDialog dialog = new UserDialog(this);

  public Parentwindow() {
    setTitle("Login");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new FlowLayout());
    JButton button = new JButton("Press the button!");
    add(button);
    button.addActionListener(new ButtonListener());
    setLocation(300, 300); // plasserer foreldrevinduet
    dialog.setLocation(350, 350);  // plasserer dialogen
  }

  private class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent action) {
      if (dialog.showDialog(user)) {
        System.out.println("OK is pressed ...");
      } else {
        System.out.println("Cancel is pressed...");
      }
      System.out.println(user); // bruker toString()
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
