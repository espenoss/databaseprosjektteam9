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

private ArrayList<String> myList;
private JList <Order> order_list = new JList <Order>();


 public ReadyForDelivery2(String tittel) {
	 java.util.Date utilDate = new java.util.Date();
	 java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	 ArrayList<Order> order = null;
	try {
		order = user.viewFoodOrders(sqlDate);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 setTitle(tittel);
	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	 myList = new ArrayList<>();
		
	 for(int i = 0; i< order.size(); i++){
	 	myList.add(order.get(i).toString());   
	 }
	 
	 myList.toArray();
	 
	 JLabel ledetekst = new JLabel("Choose one or more meals.");
	 add(ledetekst, BorderLayout.NORTH);

	 JScrollPane list = new JScrollPane();
	 add(list, BorderLayout.CENTER);

	 Listboxlistener lytter = new Listboxlistener();
	 order_list.addListSelectionListener(lytter);

	
	   
	 
	 pack();
  }
 
 /* Lytteren fanger opp alle klikk p� linjer i listeboksen */
 private class Listboxlistener implements ListSelectionListener {
   public void valueChanged(ListSelectionEvent hendelse) {
     // Object[] verdier = byliste.getSelectedValues();  - deprecated i Java 7
    Object[] values = order_list.getSelectedValuesList().toArray();
     String nyTekst = "Du har n� valgt " + values.length;
     nyTekst += (values.length == 1) ? " by." :  " byer.";
     
   }
 }

 
}

class MarkAsReadyForDelivery2 {
  public static void main(String[] args) {
	 ReadyForDelivery2 etVindu = new ReadyForDelivery2("You have chosen these meals ");
    etVindu.setVisible(true);
  }
}
