package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * The Class MainDriverGui.<br>
 * The main menu for Cook users, contains only the delivery list
 */
public class MainDriverGui extends JFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The list content model. */
	private DefaultListModel<String> listcontent = new DefaultListModel<String>(); 
	
	/** The list. */
	private JList<String> list = new JList<String>(listcontent);
	
	/** The driver. */
	private Driver driver = null;
	
	/** The meal list. */
	private String[][] mealList = null;

	/**
	 * Instantiates a new main driver gui.
	 *
	 * @param driver the driver
	 */
	public MainDriverGui(Driver driver) {
		this.driver = driver;
		setTitle("Delivery list");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new TextPanel(), BorderLayout.NORTH);
		add(new ListPanel(), BorderLayout.CENTER);
		JButton markButton = new JButton("Mark as delivered");
		markButton.addActionListener(new markButtonListener());
		add(markButton, BorderLayout.SOUTH);
		setSize(500, 300);
		setLocationRelativeTo(null);

	}

	/**
	 * The Class TextPanel.
	 */
	private class TextPanel extends JPanel {
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/**
		 * Instantiates a new text panel.
		 */
		public TextPanel() {
			setLayout(new GridLayout(4, 1, 2, 2));
			add(new JLabel(""));  
			add(new JLabel("List of deliveries"));
			add(new JLabel("Mark and click button to mark as delivered"));
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

			// Fetch delivery plan from database
			mealList = driver.generateDeliveryPlan();

			if(mealList.length == 0){
				listcontent.addElement("No deliveries left for today");
			}else{
				// Add delivery item to list
				for(int i=0; i<mealList.length;i++){
					listcontent.addElement(mealList[i][0] + ", Quantity: " + mealList[i][1]);
					listcontent.addElement(mealList[i][2]);
					listcontent.addElement(mealList[i][3] + " " + mealList[i][4]);
					listcontent.addElement(" ");
				}	
			}
			
			// Set up list and containing scrollpane
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane mealScroller = new JScrollPane(list); 
			add(mealScroller, BorderLayout.CENTER);
		}
	}

	/**
	 * Listen to the 'mark as delivered' button
	 */
	private class markButtonListener implements ActionListener{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent arg0) {
			// Get index of meal to be marked
			int selected = list.getSelectedIndex();
			// Every meal take up three spaces on the list
			// So we round to down nearest multiple of four			
			int baseIndex = (selected/4)*4; 
			int index = selected/4;
			// Remove meal from list and update database
			if(index < mealList.length){
				int orderID = Integer.parseInt(mealList[index][5]);
				int mealID = Integer.parseInt(mealList[index][6]);
				java.util.Date utilDate = new java.util.Date();
				java.sql.Date date = new java.sql.Date(utilDate.getTime());

				if(driver.markDelivered(orderID, mealID, date)){
					for(int i=0;i<4;i++){
						if(baseIndex > -1 && !listcontent.isEmpty()) listcontent.remove(baseIndex);
					}
				}
			}
		}
	}
}
