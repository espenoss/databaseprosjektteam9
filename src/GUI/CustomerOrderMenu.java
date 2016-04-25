package GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.*;
import database.Database;

public class CustomerOrderMenu extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Sales sales = null;
	Order order = null;
	JButton addMeal = new JButton("Add meal");
	JButton addSubscription = new JButton("Add subscription");	
	JButton viewOrder = new JButton("View order");

	public CustomerOrderMenu(Sales sales, Order order){
		this.sales = sales;
		this.order = order;
		order.fetchMealsInOrder(sales.getDatabase());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		menuDialog menu = new menuDialog(this);
		pack();
		menu.setLocationRelativeTo(null);
		menu.setVisible(true);
	}

	private class menuDialog extends MyDialog{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		protected menuDialog(JFrame parent) {
			super(parent, "Order ID: " + order.getOrderID());
			JRootPane board = getRootPane();
			setLayout(new FlowLayout());
			buttonListener buttonPressed = new buttonListener();
			addMeal.addActionListener(buttonPressed);
			addSubscription.addActionListener(buttonPressed);
			viewOrder.addActionListener(buttonPressed);
			add(addMeal);
			add(addSubscription);
			add(viewOrder);
			pack();
		}
	}

	private class buttonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			JButton buttonSource = (JButton)e.getSource();
			if(buttonSource == addMeal){
				new AddMealToOrderDialog(sales, order);
			}else if(buttonSource == addSubscription){
				new AddSubscriptionToOrderDialog(sales, order);
			}else{
				order.fetchMealsInOrder(sales.getDatabase());
				String s = order.toString();
				JOptionPane.showMessageDialog(null, s, "Meals in order", JOptionPane.INFORMATION_MESSAGE );						
			}
		}

	}
	public static void main(String[] args) throws Exception{
		String username = "espenme";
		String passingword = "16Sossosem06";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + passingword;	
		Database database = new Database("com.mysql.jdbc.Driver", databasename);
		CustomerOrderMenu cm = new CustomerOrderMenu(new Sales("","", database), new Order(10010, "", 10005, "", ""));
	}
}
