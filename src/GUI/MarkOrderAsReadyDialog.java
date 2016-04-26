package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import controller.*;

/**
 * The Class MarkOrderAsReadyDialog.<br>
 * Displays list of meals to be made today, and allow marig them as ready
 */
public class MarkOrderAsReadyDialog extends JFrame{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The list content  model. */
	private DefaultListModel<String> listcontent = new DefaultListModel<String>();
	
	/** The list of meals. */
	private JList<String> list = new JList<String>(listcontent);
	
	/** The cook user object. */
	private Cook cook = null;
	
	/** The order list. */
	private ArrayList<Order> orderList = null;
	
	/** The meal list. */
	private ArrayList<MealOrdered> mealList = new ArrayList<>();

	/**
	 * Instantiates a new mark order as ready dialog.
	 *
	 * @param cook User object
	 */
	public MarkOrderAsReadyDialog(Cook cook) {
		this.cook = cook;
		setTitle("Meals to be made");
		add(new TextPanel(), BorderLayout.NORTH);
		add(new ListPanel(), BorderLayout.CENTER);
		JButton markButton = new JButton("Mark as ready");
		markButton.addActionListener(new markButtonListener());
		add(markButton, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

	/**
	 * The Class TextPanel.
	 */
	// Top text
	private class TextPanel extends JPanel {
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/**
		 * Instantiates a new text panel.
		 */
		public TextPanel() {
			setLayout(new GridLayout(4, 1, 2, 2));
			add(new JLabel(""));
			add(new JLabel("List of meals to be made today"));
			add(new JLabel("Mark and click button to mark as ready"));
			add(new JLabel(""));
		}
	}

	/**
	 * The Class ListPanel.
	 */
	private class ListPanel extends JPanel {
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/**
		 * Instantiates a new list panel.
		 */
		public ListPanel() {
			setLayout(new BorderLayout());
			
			// Get todays date
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date date = new java.sql.Date(utilDate.getTime());

			// Get food orders to be delivered today
			orderList = cook.viewFoodOrders(date);

			
			if(orderList == null || orderList.size() == 0){
				listcontent.addElement("No meals left for today");
			}else{
				// Fetch meals in orders
				for(Order o: orderList){
					o.fetchMealsInOrder(cook.getDatabase());
				}	    		

				for(Order o: orderList){
					ArrayList<MealOrdered> mealsInOrder = o.getMeals();
					for(MealOrdered m: mealsInOrder){
						// Check if meals are to be delivered today
						boolean sameDate = sdf.format(date).equals(sdf.format(m.getDeliverydate()));
						if(sameDate && !m.isReadyDelivery()){
							// Add to list if so
							listcontent.addElement(String.valueOf(o.getOrderID())  + ":");
							listcontent.addElement(m.getMealName());
							listcontent.addElement(" ");
							mealList.add(m);	
						}
					}	
				}
			}

			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane mealScroller = new JScrollPane(list); 
			add(mealScroller, BorderLayout.CENTER);
		}
	}
	private class markButtonListener implements ActionListener{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			// Get index of meal to be marked
			int selected = list.getSelectedIndex();
			// Every meal take up three spaces on the list
			// So we round down to nearest multiple of three
			// Probably a better way to do this
			int baseIndex = (selected/3)*3; 
			int index = selected/3;
			// Remove meal from list and update database
			if(index < mealList.size()){
				java.util.Date utilDate = new java.util.Date();
				new java.sql.Date(utilDate.getTime());

				if(mealList.get(index).markMealAsReady(cook.getDatabase())){
					for(int i=0;i<3;i++){
						if(baseIndex > -1 && !listcontent.isEmpty()) listcontent.remove(baseIndex);
					}				
				}
			}
		}
	}
}
