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
import database.Database;

public class MainDriverGui extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultListModel<String> listcontent = new DefaultListModel<String>(); 
	private JList<String> list = new JList<String>(listcontent);
	private Driver driver = null;
	String[][] mealList = null;

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

	private class TextPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public TextPanel() {
			setLayout(new GridLayout(4, 1, 2, 2));
			add(new JLabel(""));  
			add(new JLabel("List of deliveries"));
			add(new JLabel("Mark and click button to mark as delivered"));
			add(new JLabel(""));  
		}
	}

	private class ListPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ListPanel() {
			setLayout(new BorderLayout());

			mealList = driver.generateDeliveryPlan();

			// TODO: hente ut subscription?
			
			if(mealList.length == 0){
				listcontent.addElement("No deliveries left for today");
			}else{
				for(int i=0; i<mealList.length;i++){
					listcontent.addElement(mealList[i][0] + ", Quantity: " + mealList[i][1]);
					listcontent.addElement(mealList[i][2]);
					listcontent.addElement(mealList[i][3] + " " + mealList[i][4]);
					listcontent.addElement(" ");
				}	
			}

			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane mealScroller = new JScrollPane(list); 
			add(mealScroller, BorderLayout.CENTER);
		}
	}

	private class markButtonListener implements ActionListener{

		// TODO: forklare hva jeg har gjort
		
		public void actionPerformed(ActionEvent arg0) {
			int selected = list.getSelectedIndex();
			int baseIndex = (selected/4)*4; 
			int index = selected/4;
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

	public static void main(String[] args){
		String username = "espenme";
		String passingword = "16Sossosem06";
		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + passingword;	
		Database database = new Database("com.mysql.jdbc.Driver", databasename);
		MainDriverGui del = new MainDriverGui(new Driver("","", database));
		del.setVisible(true);
	}
}
