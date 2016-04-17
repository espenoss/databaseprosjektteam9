package databaseguiii;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import controller.*;
import databasePackage.Database;

class ChangeOrder extends JFrame {
	private Database database = new Database("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/espenme?user=espenme&password=16Sossosem06");
	private Sales sales = new Sales("espenme",0, "Espen","hei", database); 
	
	private class ChangeOrderDialog extends MyDialog implements ActionListener{

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
		
		public ChangeOrderDialog(JFrame parent){
			super(parent, "Change order information");
			add(new JPanel(), BorderLayout.NORTH);
			add(new ChangeOrderDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			pack();
		}
		private class ChangeOrderDatapanel extends JPanel{
			public ChangeOrderDatapanel(){
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
				add(new JLabel("New delivery date: ", JLabel.RIGHT));
				add(delivery_dateField);
		
				add(new JLabel("Info: ", JLabel.RIGHT));
				add(infoField);
			}
		}
		public boolean okData(){

			int custIndex = customerSelect.getSelectedIndex();
			Customer currCust = customerList.get(custIndex);
			
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			deliveryDateStr = s.format(dateSelect.getValue());
			info = infoField.getText();

			try {
				sales.
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			return true;		
		}
			
		}

				
				
				
				
				
				
			