package databaseguiii;
import java.awt.*;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

import controller.*;
import databasePackage.Database;

class ReadyForDelivery2 extends JFrame {
private Database database = new Database("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/espenme?user=espenme&password=16Sossosem06");
private User user = new User("",0,"","",database);

java.util.Date utilDate = new java.util.Date();
java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
ArrayList <Order> order=  user.viewFoodOrders(sqlDate);
//JComboBox<Order> jcbBread = new JComboBox<Order>(order);
for(int i=0; i<order.size(); i++){
	JComboBox.addItem(order.get(i));
}

//private JTable order_array= new JTable();
private JList <Order> order_list = new JList <Order>();

 public ReadyForDelivery2(String tittel) {
	 setTitle(tittel);
	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	 JLabel ledetekst = new JLabel("Choose one or more meals.");
	 add(ledetekst, BorderLayout.NORTH);

	 JScrollPane list = new JScrollPane();
	 add(list, BorderLayout.CENTER);

	 ListeboksLytter listener = new ListeboksLytter();
	 order.addListSelectionListener(listener);
	 
	 pack();
  }

  /* Lytteren fanger opp alle klikk p� linjer i listeboksen */
  private class ListeboksLytter implements ListSelectionListener {
    public void valueChanged(ListSelectionEvent hendelse) {
      Object[] verdier = order.getSelectedValuesList().toArray();
      String nyTekst = "Du har n� valgt " + verdier.length;
      
    //Dersom vi bruker denne metoden med en ArrayList knyttet til viewFoodOrders(), m� vi sette verdien lik true her
   /*   nyTekst += (verdier.length == 1) ? " by." :  " byer.";
      tekst.setText(nyTekst);   */ 
    }
    private class LineListener implements ListSelectionListener{
    	public void valueChanged(ListSelectionEvent event){
    		int line = 
    	} 
    }
  }
}

class MarkAsReadyForDelivery2 {
  public static void main(String[] args) {
	 ReadyForDelivery2 etVindu = new ReadyForDelivery2("You have chosen these meals ");
    etVindu.setVisible(true);
  }
}
