package GUI;

import controller.*;
import java.awt.*;
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

class RegisterOrderDialog extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Sales sales = null; 

	public RegisterOrderDialog(Sales sales) {
		this.sales = sales;
		DialogWindow dialog = new DialogWindow(this);
		setTitle("Register new order");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}


	private class DialogWindow extends MyDialog{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
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
			setSize(500,300);
			setLocationRelativeTo(null);
		}

		private class OrderDatapanel extends JPanel{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public OrderDatapanel(){
				GridLayout superGrid = new GridLayout(8,1);
				setLayout(superGrid);

				customerList = sales.viewCustomerList();

				ArrayList<String> nameList = new ArrayList<>();
				for(Customer c: customerList){
					nameList.add(c.getCustomerID() + " " + c.getFirstName() + " " + c.getSurName());
				}
				customerSelect = new JComboBox<>(nameList.toArray());

				add(new JLabel("Customer: ", JLabel.LEFT));
				add(customerSelect);

				add(new JLabel("Delivery date: ", JLabel.LEFT));
				add(dateSelect);

				add(new JLabel("Information about the order: ", JLabel.LEFT));
				add(infoField);

			}
		}

		public boolean okData(){

			int customerIndex = customerSelect.getSelectedIndex();
			customerID = customerList.get(customerIndex).getCustomerID();

			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			deliveryDateStr = s.format(dateSelect.getValue());
			info = infoField.getText();

			return sales.registerNewOrder(customerID, info, sales.getUserID()) != null;
		}	
	}
}