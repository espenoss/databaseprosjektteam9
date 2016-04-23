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

class RegisterSubscriptionPlan extends JFrame {
	private Cook cook = null; 

	public RegisterSubscriptionPlan(Cook cook) {
		this.cook = cook;
		DialogWindow dialog = new DialogWindow(this);
		dialog.setVisible(true);
		setTitle("Register new subscription plan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setLocation(300, 300); 
		dialog.setLocation(350, 350);  
	}

	private class DialogWindow extends MyDialog{
		private TextEditor editor = new TextEditor();
		
		private JTextField sub_plan_nameField = new JTextField(10);
		String sub_plan;
		
		
		public DialogWindow(JFrame parent){
			super(parent, "New subscpiption plan");
			add(new JPanel(), BorderLayout.NORTH);
			add(new OrderDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			pack();
		}
	
		private class OrderDatapanel extends JPanel{
			public OrderDatapanel(){
				setLayout(new GridLayout(1,2));
			
			
				add(new JLabel("Name of the supscription plan: ", JLabel.RIGHT));
				add(sub_plan_nameField);
			
			}
		}

		public boolean okData(){
			sub_plan = sub_plan_nameField.getText();
		
			try {
				cook.registerSubPlan(sub_plan, cook.getDatabase());
			}catch (Exception e) {
				System.out.println(e.toString());
			}
			return true;
		}	
	}
}
