package databaseguiii;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.*;
import databasePackage.Database;

public class MarkOrderAsReadyDialog extends JFrame{

	  private DefaultListModel<String> listcontent = new DefaultListModel<String>();
	  private JList<String> list = new JList<String>(listcontent);
	  private Cook cook = null;
	  ArrayList<Order> orderList = null;
	  ArrayList<MealOrdered> mealList = new ArrayList<>();
	  
	  public MarkOrderAsReadyDialog(Cook cook) {
		  this.cook = cook;
		  setTitle("Meals to be made");
		  
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  add(new TextPanel(), BorderLayout.NORTH);
	   	  add(new ListPanel(), BorderLayout.CENTER);
	   	  JButton markButton = new JButton("Mark as ready");
	   	  markButton.addActionListener(new markButtonListener());
	   	  add(markButton, BorderLayout.SOUTH);
		  pack();
		  setLocationRelativeTo(null);
		  setVisible(true);
		  
	  }
	  
	  private class markButtonListener implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				int selected = list.getSelectedIndex();
				int baseIndex = (selected/3)*3; // Round down to nearest multiple of 3 to get index of meal
				for(int i=0;i<3;i++){
					if(baseIndex > -1 && !listcontent.isEmpty()) listcontent.remove(baseIndex);
				}
				int index = selected/3;
				if(index < mealList.size()){
				    java.util.Date utilDate = new java.util.Date();
				    java.sql.Date date = new java.sql.Date(utilDate.getTime());
				    
					try {
						mealList.get(index).markMealAsReady(cook.getDatabase());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		  }

	  // Top text
	  private class TextPanel extends JPanel {
	    public TextPanel() {
	      setLayout(new GridLayout(4, 1, 2, 2));
	      add(new JLabel(""));
	      add(new JLabel("List of meals to be made today"));
	      add(new JLabel("Mark and click button to mark as ready"));
	      add(new JLabel(""));
	    }
	  }

	  private class ListPanel extends JPanel {
	    public ListPanel() {
	    	setLayout(new BorderLayout());
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    java.util.Date utilDate = new java.util.Date();
		    java.sql.Date date = new java.sql.Date(utilDate.getTime());
	    	
	    	try {
	    		orderList = cook.viewFoodOrders(date);
			} catch (Exception e) {
				e.printStackTrace();
			}

	    	if(orderList == null || orderList.size() == 0){
	    		listcontent.addElement("No meals left for today");
	    	}else{
		    	try{
			    	for(Order o: orderList){
						o.fetchMealsInOrder(cook.getDatabase());
			    	}	    		
		    	} catch (Exception e) {
					e.printStackTrace();
				}
	    		
	    	    for(Order o: orderList){
	    	    	ArrayList<MealOrdered> mealsInOrder = o.getMeals();
	    	    	for(MealOrdered m: mealsInOrder){
	    	    		boolean sameDate = sdf.format(date).equals(sdf.format(m.getDeliverydate()));
	    	    		if(sameDate && !m.isReadyDelivery()){
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
	  
	 	public static void main(String[] args){
	 		String username = "espenme";
	 		String passingword = "16Sossosem06";
	 		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + passingword;	
	 		Database database = new Database("com.mysql.jdbc.Driver", databasename);
			MarkOrderAsReadyDialog del = new MarkOrderAsReadyDialog(new Cook("","", database));

	 	}
}
