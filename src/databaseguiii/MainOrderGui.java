package databaseguiii;

import controller.*;
import databasePackage.Database;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//Metoden registerNewOrder() ligger i Sales.java

class Parentwindow3 extends JFrame {
	private Database database = new Database("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/espenme?user=espenme&password=16Sossosem06");
	private Sales sales = new Sales("espenme",0, "Espen","hei", database); 
  

	//int customerID, String deliveryDate, String info, String userID, Database database
	private class RegisterOrderDialog extends MyDialog{
		private TextEditor editor = new TextEditor();
		private ArrayList<Customer> customerList = new ArrayList<>();
		private JComboBox customerSelect;	
		private JTextField delivery_dateField = new JTextField(10);
		private JTextField infoField = new JTextField(10);
		private int customerID;
		private String deliveryDate;
		private String info;
		
		public RegisterOrderDialog(JFrame parent){
			super(parent, "New order");
			add(new JPanel(), BorderLayout.NORTH);
			add(new OrderDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			pack();
		}
	
		private class OrderDatapanel extends JPanel{
			public OrderDatapanel(){
				setLayout(new GridLayout(4,2));
			
				try {
					customerList = sales.viewCustomerList();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				ArrayList<String> nameList = new ArrayList<>();
				for(Customer c: customerList){
					nameList.add(c.getCustomerID() + " " + c.getFirstName() + " " + c.getSurName());
				}
				customerSelect = new JComboBox<>(nameList.toArray());
				
				add(new JLabel("Customer: ", JLabel.RIGHT));
				add(customerSelect);
			
				add(new JLabel("Delivery date: ", JLabel.RIGHT));
				add(delivery_dateField);
			
				add(new JLabel("Information about the order: ", JLabel.RIGHT));
				add(infoField);
			
			}
		}

		public boolean okData(){

			int customerIndex = customerSelect.getSelectedIndex();
			customerID = customerList.get(customerIndex).getCustomerID();
			
			deliveryDate = delivery_dateField.getText();
			info = infoField.getText();
		
			try {
				sales.registerNewOrder(customerID, deliveryDate, info, sales.getUserID(), database);
			}catch (Exception e) {
				System.out.println(e.toString());
			}
			return true;
		}	
	}
		
	public Parentwindow3() {
		RegisterOrderDialog dialog = new RegisterOrderDialog(this);
		dialog.setVisible(true);
		setTitle("Register new order");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setLocation(300, 300); 
		dialog.setLocation(350, 350);  
	}
}
  
class MainOrderGui {
  static public void main(String[] args) {
	Parentwindow3 test = new Parentwindow3();
    
  }   
} 

