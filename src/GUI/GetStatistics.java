package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controller.*;
import controller.TextEditor;

/**
 * The Class GetStatistics.<br>
 * Used to view income for a specific year
 */
class GetStatistics extends JFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The admin. */
	private Admin admin = null; 

	/**
	 * Instantiates a new frame	 
	 * 
	 * @param admin User object
	 */
	public GetStatistics(Admin admin) {
		this.admin = admin;
		DialogWindow dialog = new DialogWindow(this);
		setTitle("Get statistics");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		/** The editor. */
		private TextEditor editor = new TextEditor();

		/** The date field. */
		private JTextField dateField = new JTextField(4);
		
		/** The year. */
		private int year;

		/**
		 * Instantiates a new dialog window.
		 *
		 * @param parent the parent
		 */
		public DialogWindow(JFrame parent){
			super(parent, "Get statistics");
			add(new JPanel(), BorderLayout.NORTH);
			add(new OrderDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			setSize(500,130);
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
				setLayout(new GridLayout(1,2));

				add(new JLabel("Fill in year in format YYYY: ", JLabel.RIGHT));
				add(dateField);
				pack();


			}
		}

		/* (non-Javadoc)
		 * @see GUI.MyDialog#okData()
		 */
		public boolean okData(){

			// Get year entered
			String year_codeText  = dateField.getText();
			int myIntDate = editor.stringToInt(year_codeText);
			year = myIntDate;

			Calendar cal = Calendar.getInstance();

			// Check if valid year range
			if (year > 1980 && year <= cal.get(Calendar.YEAR)){ 

				String s = "";
				s=admin.getStatisticsForYear(year).toString();

				JOptionPane.showMessageDialog(null, s, "Statistics: ", JOptionPane.INFORMATION_MESSAGE );
				return true;
			}
			else{
				JOptionPane.showMessageDialog(null, "Could not retrieve statistics");
				return false;
			}	
		}
	}
}