package databaseguiii;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.*;

class AddMealToSubPlanDialog extends JFrame {
 	
 	Cook cook = null;
 	
 	public AddMealToSubPlanDialog(Cook cook){
 		this.cook = cook;
 		AddMealDialog dialog = new AddMealDialog(this);
 		dialog.setVisible(true);
  		setTitle("Choose the meal and its ingrediets");
  		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  		setLayout(new FlowLayout());
  		setLocation(300, 300); 
  		dialog.setLocation(350, 350); 
 	}

 	private class AddMealDialog extends MyDialog {
 		private TextEditor editor = new TextEditor();
 		

		private final String days_of_week[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday","Sunday"}; 
		
		private JComboBox dayList = new JComboBox(days_of_week);	
		
 		private ArrayList<SubPlan> SubPlanList = new ArrayList<SubPlan>();
 		private JComboBox SubPlanSelected;
 		
 		private ArrayList<Meal> mealList = new ArrayList<Meal>();
 		private JComboBox mealIdSelected;
 		
 		
 		private int my_day;
 		private int my_meal;
 		private int my_sub_plan;
	 
		
	 
 		public AddMealDialog(JFrame parent){
 			super(parent, "Choose supscription plan and meals");
 			add(new JPanel(), BorderLayout.NORTH);
 			add(new IngredientsDatapanel(),BorderLayout.CENTER);
 			add(getButtonPanel(),BorderLayout.SOUTH);
 			pack();
 		}
	   
 		private class IngredientsDatapanel extends JPanel{
 			public IngredientsDatapanel(){
 				setLayout(new GridLayout(3,2));
 				
 				try {
 					SubPlanList = cook.viewAllSubPlans();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				ArrayList<String> my_sub_list = new ArrayList<>();
				for(SubPlan plan: SubPlanList){
					my_sub_list.add(plan.getName());
				}
				SubPlanSelected = new JComboBox<>(my_sub_list.toArray());
				
 				add(new JLabel("Subscription Plan: ", JLabel.RIGHT));
 				add(SubPlanSelected);
 			
				
 				try {
	 				mealList = cook.viewAvailableMeals();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				ArrayList<String> my_list = new ArrayList<>();
				for(Meal m: mealList){
					my_list.add(m.getMealName());
				}
				mealIdSelected = new JComboBox<>(my_list.toArray());
 				add(new JLabel("Meal Id: ", JLabel.RIGHT));
 				add(mealIdSelected);
 		
 				

				add(new JLabel("Weekday: ", JLabel.RIGHT));
				add(dayList);
			

 			}
 		}

 		public boolean okData(){
 			my_meal = mealIdSelected.getSelectedIndex();
 			my_sub_plan =SubPlanSelected.getSelectedIndex();
 			my_day = dayList.getSelectedIndex()+1;
 			
 
		 	try {
		 		cook.addMealToSubPlan(my_sub_plan, my_meal, my_day);
		 	}catch (Exception e) {
		 		System.out.println(e.toString());
		 	}
		 	
		 	return true;
		 	
 		}
 	}
}
