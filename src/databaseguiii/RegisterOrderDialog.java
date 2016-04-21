package databaseguiii;

import controller.*;
import databasePackage.Database;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

class RegisterOrderDialog extends JFrame {
	private Sales sales = null; 

	public RegisterOrderDialog(Sales sales) {
		this.sales = sales;
		DialogWindow dialog = new DialogWindow(this);
		dialog.setVisible(true);
		setTitle("Register new order");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setLocation(300, 300); 
		dialog.setLocation(350, 350);  
	}

//int customerID, String deliveryDate, String info, String userID, Database database
	private class DialogWindow extends MyDialog{
		private TextEditor editor = new TextEditor();
		private ArrayList<Customer> customerList = new ArrayList<>();
		private JComboBox customerSelect;
		private SpinnerDateModel dateSelectModel = new SpinnerDateModel();
		private JSpinner dateSelect = new JSpinner(dateSelectModel);
		private JTextField delivery_dateField = new JTextField(10);
		private JTextField infoField = new JTextField(10);
		private int customerID;
		private String deliveryDateStr;
		private Date deliveryDate;
		private String info;
		
		public DialogWindow(JFrame parent){
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
				add(dateSelect);
			
				add(new JLabel("Information about the order: ", JLabel.RIGHT));
				add(infoField);
			
			}
		}

		public boolean okData(){

			int customerIndex = customerSelect.getSelectedIndex();
			customerID = customerList.get(customerIndex).getCustomerID();
			
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			deliveryDateStr = s.format(dateSelect.getValue());
			info = infoField.getText();
		
			try {
				sales.registerNewOrder(customerID, info, sales.getUserID());
			}catch (Exception e) {
				System.out.println(e.toString());
			}
			return true;
		}	
	}
}