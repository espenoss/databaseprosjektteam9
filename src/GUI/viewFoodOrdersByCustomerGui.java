package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.*;
import database.Database;

class ViewFoodOrdersByCustomerGui extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Customer> customerList = null;
	private ArrayList<Order> orderList = null;
	private JComboBox customerSelect;
	private DefaultListModel<String> listcontent = new DefaultListModel<String>();
	private JList<String> list = new JList<String>(listcontent);
	private Sales sales = null; 

	public ViewFoodOrdersByCustomerGui(Sales sales) {
		this.sales = sales;
		ViewFoodOrdersByCustomerDialog dialog = new ViewFoodOrdersByCustomerDialog(this);
		setTitle("View food orders of a customer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);

	} 

	private class ViewFoodOrdersByCustomerDialog extends MyDialog{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;


		public ViewFoodOrdersByCustomerDialog(JFrame parent){

			super(parent, "Choose a customer");
			add(new JPanel(), BorderLayout.NORTH);
			add(new CompanyDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			setSize(500,200);
		}


		private class CompanyDatapanel extends JPanel{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public CompanyDatapanel(){
				setLayout(new FlowLayout());

				customerList = sales.viewCustomerList();

				ArrayList<String> nameList = new ArrayList<>();
				for(Customer c: customerList){
					nameList.add(c.getCustomerID() + " " + c.getFirstName() + " " + c.getSurName());
				}
				customerSelect = new JComboBox<>(nameList.toArray());
				customerSelect.addActionListener(new comboListener());



				orderList = sales.viewFoodOrdersByCustomer(customerList.get(0).getCustomerID());

				if(orderList != null){
					for(Order o: orderList){
						listcontent.addElement(o.getOrderID() + "");
					}
				}

				add(new JLabel("Customer: ", JLabel.RIGHT));
				add(customerSelect);
				add(new JLabel("Orders: ", JLabel.RIGHT));
				JScrollPane orderScroller = new JScrollPane(list); 
				list.addListSelectionListener(new listListener());
				add(orderScroller, BorderLayout.CENTER);
				pack();
			}

			private class comboListener implements ActionListener{

				public void actionPerformed(ActionEvent arg0) {

					// Update orderlist when different customer selected

					int customerIndex = customerSelect.getSelectedIndex();
					if(customerIndex != -1){
						Customer currCust = customerList.get(customerIndex);
						listcontent.clear();
						orderList = sales.viewFoodOrdersByCustomer(currCust.getCustomerID());

						if(orderList != null){
							for(Order o: orderList){
								listcontent.addElement(o.getOrderID() + "");
							}
						}	
					}
				}				
			}

			private class listListener implements ListSelectionListener{
				public void valueChanged(ListSelectionEvent arg0) {
					int orderIndex = list.getSelectedIndex();
					if(orderIndex != -1){
						list.clearSelection();
						Order selectedOrder = orderList.get(orderIndex);
						new CustomerOrderMenu(sales, selectedOrder);
					}
				}
			}
		}

	}

	public static void main(String[] args){
		String username = "espenme";
		String passingword = "16Sossosem06";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + passingword;	
		Database database = new Database("com.mysql.jdbc.Driver", databasename);
		ViewFoodOrdersByCustomerGui del = new ViewFoodOrdersByCustomerGui(new Sales("","", database));
		del.setVisible(true);
	}

}


