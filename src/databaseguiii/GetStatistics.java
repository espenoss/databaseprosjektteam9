package databaseguiii;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import controller.Customer;
import controller.*;
import controller.TextEditor;


class GetStatistics extends JFrame {
	private Admin admin = null; 

	public GetStatistics(Admin admin) {
		this.admin = admin;
		DialogWindow dialog = new DialogWindow(this);
		dialog.setVisible(true);
		setTitle("Get statistics");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setLocation(300, 300); 
		dialog.setLocation(350, 350);  
	}

	private class DialogWindow extends MyDialog{
		private TextEditor editor = new TextEditor();
		
		private JTextField dateField = new JTextField(10);
		private int year;
		
		public DialogWindow(JFrame parent){
			super(parent, "New order");
			add(new JPanel(), BorderLayout.NORTH);
			add(new OrderDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			pack();
		}
	
		private class OrderDatapanel extends JPanel{
			public OrderDatapanel(){
				setLayout(new GridLayout(1,2));
			
				add(new JLabel("Fill in year in format YYYY: ", JLabel.RIGHT));
				add(dateField);
				
			
			}
		}

		public boolean okData(){
						
			String year_codeText  = dateField.getText();
			int myIntDate = editor.stringToInt(year_codeText);
			year = myIntDate;
			
			String s = "";
			try {
				s=admin.getStatisticsForYear(year).toString();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			 JOptionPane.showMessageDialog(null, s, "Statistics: ", JOptionPane.INFORMATION_MESSAGE );
			return true;
		}	
	}
}