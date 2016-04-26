package GUI;
import controller.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

/**
 * The Class ViewFoodOrders.<br>
 * Used to view food orders for a date.
 */
class ViewFoodOrders extends JFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The user object. */
	private User user = null; 
	
	/** The date select model. */
	private SpinnerDateModel dateSelectModel = new SpinnerDateModel();

	/** The date selection spinner. */
	private JSpinner dateSelect = new JSpinner(dateSelectModel);
	
	/** The date formatter. */
	private SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Instantiates a new view food orders.
	 *
	 * @param user User object
	 */
	public ViewFoodOrders(User user) {
		this.user = user;
		DialogWindow dialog = new DialogWindow(this);
		setLayout(new FlowLayout());
		pack();
		// Set window location in middle of screen
		dialog.setLocationRelativeTo(null);
		// Display window
		dialog.setVisible(true);
	}

	/**
	 * The Class DialogWindow.
	 */
	private class DialogWindow extends MyDialog{
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;
		
		/** The order list*/
		ArrayList<Order> orderList = null;

		/**
		 * Instantiates a new dialog window.
		 *
		 * @param parent Parent frame
		 */
		public DialogWindow(JFrame parent){
			super(parent, "View orders");
			add(new JPanel(), BorderLayout.NORTH);
			add(new OrderDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			pack();
		}

		/**
		 * The Class OrderDatapanel.
		 */
		private class OrderDatapanel extends JPanel{
			
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * Instantiates a new order datapanel.
			 */
			public OrderDatapanel(){
				setLayout(new GridLayout(4,2));

				add(new JLabel("Select date: ", JLabel.RIGHT));
				add(dateSelect);
			}
		}


		/* (non-Javadoc)
		 * @see GUI.MyDialog#okData()
		 */
		public boolean okData(){

			// Get date from spinner
			String dateStr = s.format(dateSelect.getValue());		
			java.sql.Date sqlDate = java.sql.Date.valueOf(dateStr);    

			// Fetch orders for that date from database
			orderList = user.viewFoodOrders(sqlDate);

			if(orderList == null){
				JOptionPane.showMessageDialog(null, "No orders for this date");
				return false;
			}

			// convert to string list
			String[] str = new String[orderList.size()];
			for( int i = 0; i < orderList.size(); i++ ){
				str[i] = orderList.get(i).toString() + " ";
			}

			// Set up order view window and display
			JScrollPane scrollpane = new JScrollPane(); 
			JList<String> list = new JList<String>(str);
			scrollpane = new JScrollPane(list);
			JPanel panel = new JPanel(); 
			panel.add(scrollpane);
			scrollpane.getViewport().add(list);		    	 
			JOptionPane.showMessageDialog(null, scrollpane, "All orders: ", JOptionPane.INFORMATION_MESSAGE );
			return true;
		}
	}
}


