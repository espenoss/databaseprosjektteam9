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
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import controller.*;

class RegisterOrderDialog extends JFrame {
	private Admin admin = null; 

	public RegisterOrderDialog(Admin admin) {
		this.admin = admin;
		DialogWindow dialog = new DialogWindow(this);
		dialog.setVisible(true);
		setTitle("View food orders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setLocation(300, 300); 
		dialog.setLocation(350, 350);  
	}

	//int customerID, String deliveryDate, String info, String userID, Database database
	private class DialogWindow extends MyDialog{
		private JSpinner dateSelect = new JSpinner(dateSelectModel);
		private JTextField delivery_dateField = new JTextField(10);
		private JTextField infoField = new JTextField(10);
		private Date date;
		private String DateStr;
		private JTextField dateField = new JTextField(10);
		private String info;
		
		public DialogWindow(JFrame parent){
			super(parent, "View orders");
			add(new JPanel(), BorderLayout.NORTH);
			add(new OrderDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			pack();
		}
	
		private class OrderDatapanel extends JPanel{
			public OrderDatapanel(){
				setLayout(new GridLayout(1,2));
			
				}
			}
		}
	}
