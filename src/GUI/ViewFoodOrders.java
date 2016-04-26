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



class ViewFoodOrders extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user = null; 
	private SpinnerDateModel dateSelectModel = new SpinnerDateModel();

	private JSpinner dateSelect = new JSpinner(dateSelectModel);
	private SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

	public ViewFoodOrders(User user) {
		this.user = user;
		DialogWindow dialog = new DialogWindow(this);
		setTitle("View food orders");
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
		ArrayList<Order> o = null;

		public DialogWindow(JFrame parent){
			super(parent, "View orders");
			add(new JPanel(), BorderLayout.NORTH);
			add(new OrderDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			pack();
		}

		private class OrderDatapanel extends JPanel{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public OrderDatapanel(){
				setLayout(new GridLayout(4,2));

				add(new JLabel("Select date: ", JLabel.RIGHT));
				add(dateSelect);

			}
		}


		public boolean okData(){

			String dateStr = s.format(dateSelect.getValue());		
			java.sql.Date sqlDate = java.sql.Date.valueOf(dateStr);    

			o = user.viewFoodOrders(sqlDate);

			if(o == null){
				JOptionPane.showMessageDialog(null, "No orders for this date");
				return false;
			}

			String[] str = new String[o.size()];
			for( int i = 0; i < o.size(); i++ ){
				str[i] = o.get(i).toString() + " ";
			}

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


