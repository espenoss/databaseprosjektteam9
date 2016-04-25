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


class GetStatistics extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Admin admin = null; 

	public GetStatistics(Admin admin) {
		this.admin = admin;
		DialogWindow dialog = new DialogWindow(this);
		setTitle("Get statistics");
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

		private JTextField dateField = new JTextField(10);
		private int year;

		public DialogWindow(JFrame parent){
			super(parent, "Get statistics");
			add(new JPanel(), BorderLayout.NORTH);
			add(new OrderDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			setSize(500,130);
			setLocationRelativeTo(null);
		}

		private class OrderDatapanel extends JPanel{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public OrderDatapanel(){
				setLayout(new GridLayout(1,2));

				add(new JLabel("Fill in year in format YYYY: ", JLabel.RIGHT));
				add(dateField);
				pack();


			}
		}

		public boolean okData(){

			String year_codeText  = dateField.getText();
			int myIntDate = editor.stringToInt(year_codeText);
			year = myIntDate;

			Calendar cal = Calendar.getInstance();

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