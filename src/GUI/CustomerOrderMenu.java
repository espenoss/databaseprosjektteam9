package GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.*;

/**
 * The Class CustomerOrderMenu.
 */
class CustomerOrderMenu extends JFrame{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The sales user objects. */
	private Sales sales = null;
	
	/** The order. */
	private Order order = null;

	/**
	 * Instantiates a new customer order menu.
	 *
	 * @param sales the sales
	 * @param order the order
	 */
	CustomerOrderMenu(Sales sales, Order order){
		this.sales = sales;
		this.order = order;
		order.fetchMealsInOrder(sales.getDatabase());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
		menuDialog menu = new menuDialog(this);
		pack();
		// Set window location in middle of screen
		menu.setLocationRelativeTo(null);
		// Display window
		menu.setVisible(true);
	}

	/**
	 * The Class menuDialog.
	 */
	private class menuDialog extends MyDialog{

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;
		
		/** The add meal button. */
		private JButton addMeal = new JButton("Add meal");
		
		/** The add subscription button. */
		private JButton addSubscription = new JButton("Add subscription");	
		
		/** The view order button. */
		private JButton viewOrder = new JButton("View order");

		/**
		 * Instantiates a new menu dialog.
		 *
		 * @param parent Parent frame
		 */
		protected menuDialog(JFrame parent) {
			super(parent, "Order ID: " + order.getOrderID());
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
		
		private class buttonListener implements ActionListener{

			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				JButton buttonSource = (JButton)e.getSource();
				// If 'add meal' is pressed
				if(buttonSource == addMeal){
					new AddMealToOrderDialog(sales, order);
				// If 'add subscription' is pressed
				}else if(buttonSource == addSubscription){
					new AddSubscriptionToOrderDialog(sales, order);
				// If 'view order' is pressed
				}else{
					// Print out order info
					order.fetchMealsInOrder(sales.getDatabase());
					String s = order.toString();
					JOptionPane.showMessageDialog(null, s, "Meals in order", JOptionPane.INFORMATION_MESSAGE );						
				}
			}
		}
	}
}