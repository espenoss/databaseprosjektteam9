package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.*;
import database.Database;

class ChangeOrderDialog extends JFrame {
	private Sales sales = null;
	private ArrayList<Order> orders = new ArrayList<>();
	private ArrayList<Customer> customerList = new ArrayList<>();
	private JComboBox customerSelect;
	private JComboBox orderSelect;		
	private SpinnerDateModel dateSelectModel = new SpinnerDateModel();
	private JSpinner dateSelect = new JSpinner(dateSelectModel);
	private JTextField infoField = new JTextField(10);

	public ChangeOrderDialog(Sales sales) {
		this.sales = sales;
		DialogWindow dialog = new DialogWindow(this);
		setTitle("Change order");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}

	private class DialogWindow extends MyDialog{

		private String deliveryDateStr;
		private String info;

		public DialogWindow(JFrame parent){
			super(parent, "Change order information");
			add(new JPanel(), BorderLayout.NORTH);
			add(new ChangeOrderDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);

			setSize(500,300);
			setLocationRelativeTo(null);
		}
		private class ChangeOrderDatapanel extends JPanel{
			public ChangeOrderDatapanel(){
				GridLayout superGrid = new GridLayout(8,1);
				setLayout(superGrid);
				customerList = sales.viewCustomerList();

				ArrayList<String> nameList = new ArrayList<>();
				for(Customer c: customerList){
					nameList.add(c.getCustomerID() + " " + c.getFirstName() + " " + c.getSurName());
				}

				orders = sales.viewFoodOrdersByCustomer(customerList.get(0).getCustomerID());

				ArrayList<String> orderNameList = new ArrayList<>();
				for(Order o: orders){
					orderNameList.add(String.valueOf(o.getOrderID()));
				}

				customerSelect = new JComboBox<>(nameList.toArray());
				orderSelect = new JComboBox<>(orderNameList.toArray());

				add(new JLabel("Customer: ", JLabel.LEFT));
				add(customerSelect);
				customerSelect.addActionListener(new customerListener());
				add(new JLabel("Order: ", JLabel.LEFT));				
				add(orderSelect);
				add(new JLabel("New delivery date: ", JLabel.LEFT));
				add(dateSelect);
				add(new JLabel("Info: ", JLabel.LEFT));
				add(infoField);
			}
		}
		public boolean okData(){

			int custIndex = customerSelect.getSelectedIndex();
			Customer currCust = customerList.get(custIndex);

			int orderIndex = orderSelect.getSelectedIndex();
			Order currOrder = orders.get(orderIndex);

			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			deliveryDateStr = s.format(dateSelect.getValue());
			info = infoField.getText();

			currOrder.setOrderDate(deliveryDateStr);
			currOrder.setInfo(info);

			return currOrder.uploadOrder(sales.getDatabase());
		}

	} 

	private class customerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JComboBox<String> l = (JComboBox<String>)e.getSource();
			int selected = l.getSelectedIndex();
			orders = sales.viewFoodOrdersByCustomer(customerList.get(selected).getCustomerID());

			orderSelect.removeAllItems();
			if(orders != null){
				for(Order o: orders){
					orderSelect.addItem(String.valueOf(o.getOrderID()));
				}
			}			
		}		
	}
}