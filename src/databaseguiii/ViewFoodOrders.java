package databaseguiii;
import controller.*;
import databasePackage.Database;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;



class ViewFoodOrders extends JFrame {
		private User user = null; 
		ArrayList<Order> o = null;
		SpinnerDateModel dateSelectModel = new SpinnerDateModel();
		JSpinner dateSelect = new JSpinner(dateSelectModel);
		
		public ViewFoodOrders(User user) {
			this.user = user;
			DialogWindow dialog = new DialogWindow(this);
			dialog.setVisible(true);
			setTitle("View food orders");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(new FlowLayout());
			setLocation(300, 300); 
			dialog.setLocation(350, 350);  
		}

		private class DialogWindow extends MyDialog{
	/*		ArrayList<Order> o = null;
			SpinnerDateModel dateSelectModel = new SpinnerDateModel();
			JSpinner dateSelect = new JSpinner(dateSelectModel);
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			String str = s.format(dateSelect.getValue());
			java.sql.Date sqlDate = java.sql.Date.valueOf(str);   */
			
			public DialogWindow(JFrame parent){
				super(parent, "View orders");
				add(new JPanel(), BorderLayout.NORTH);
				add(new OrderDatapanel(),BorderLayout.CENTER);
				add(getButtonPanel(),BorderLayout.SOUTH);
				pack();
			}
		
			private class OrderDatapanel extends JPanel{
				public OrderDatapanel(){
					setLayout(new GridLayout(4,2));
					
					add(new JLabel("Select date: ", JLabel.RIGHT));
			    	add(dateSelect);
					
				}
			}
					
					
			public boolean okData(){
				
				SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
				String str = s.format(dateSelect.getValue());
				java.sql.Date sqlDate = java.sql.Date.valueOf(str);    

					
			    try {
			    	o = user.viewFoodOrders(sqlDate);
				}
		     	catch (Exception e) {
		  	 		e.printStackTrace();
		   	 	}
		   		 String[] str2 = new String[o.size()];
		    	 for( int i = 0; i < o.size(); i++ ){
		    	 str2[i] = o.get(i).toString() + " ";
			   	 }
			    	 
			    JScrollPane scrollpane = new JScrollPane(); 
			    JList<String> list = new JList<String>(str2);
			    scrollpane = new JScrollPane(list);
			    JPanel panel = new JPanel(); 
			    panel.add(scrollpane);
			    scrollpane.getViewport().add(list);		    	 
			    JOptionPane.showMessageDialog(null, scrollpane, "All orders: ", JOptionPane.INFORMATION_MESSAGE );
			    return true;
				}
			}
		}
	

