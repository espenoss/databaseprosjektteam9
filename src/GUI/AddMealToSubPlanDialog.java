package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.*;

class AddMealToSubPlanDialog extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Cook cook = null;

	public AddMealToSubPlanDialog(Cook cook){
		this.cook = cook;
		AddMealDialog dialog = new AddMealDialog(this);
		setTitle("Choose the meal and its ingrediets");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}

	private class AddMealDialog extends MyDialog {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private JTextField subName = null;
		private JComboBox mondaySelect = null;	
		private JComboBox tuesdaySelect = null;	
		private JComboBox wednesdaySelect = null;	
		private JComboBox thursdaySelect = null;	
		private JComboBox fridaySelect = null;	
		private JComboBox saturdaySelect = null;	
		private JComboBox sundaySelect = null;	

		private ArrayList<SubPlan> subPlanList = null;
		private JComboBox subPlanSelected;

		private ArrayList<Meal> mealList = null;

		public AddMealDialog(JFrame parent){
			super(parent, "Choose supscription plan and meals");
			add(new JPanel(), BorderLayout.NORTH);
			add(new IngredientsDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			setSize(500,700);
			setLocationRelativeTo(null);
		}

		private class IngredientsDatapanel extends JPanel{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public IngredientsDatapanel(){
				GridLayout superGrid = new GridLayout(18,1);
				setLayout(superGrid);

				subPlanList = cook.viewAllSubPlans();

				ArrayList<String> my_sub_list = new ArrayList<>();

				for(SubPlan plan: subPlanList){
					my_sub_list.add(plan.getName());
				}

				mealList = cook.viewAvailableMeals();

				ArrayList<String> mealNames = new ArrayList<String>();
				for(Meal m:mealList){
					mealNames.add(m.getMealName());
				}

				mondaySelect = new JComboBox<>(mealNames.toArray());	
				tuesdaySelect = new JComboBox<>(mealNames.toArray());	
				wednesdaySelect = new JComboBox<>(mealNames.toArray());	
				thursdaySelect = new JComboBox<>(mealNames.toArray());	
				fridaySelect = new JComboBox<>(mealNames.toArray());	
				saturdaySelect = new JComboBox<>(mealNames.toArray());	
				sundaySelect = new JComboBox<>(mealNames.toArray());	

				ArrayList<String> my_list = new ArrayList<>();
				for(Meal m: mealList){
					my_list.add(m.getMealName());
				}

				subPlanSelected = new JComboBox<>(my_sub_list.toArray());

				add(new JLabel("Subscription Plan: ", JLabel.LEFT));
				add(subPlanSelected);				
				subPlanSelected.addActionListener(new subSelectListener());

				add(new JLabel("Sub name: ", JLabel.LEFT));
				subName = new JTextField(subPlanList.get(0).getName());
				add(subName);

				add(new JLabel("Monday: ", JLabel.LEFT));
				add(mondaySelect);

				add(new JLabel("Tuesday: ", JLabel.LEFT));
				add(tuesdaySelect);

				add(new JLabel("Wednesday: ", JLabel.LEFT));
				add(wednesdaySelect); 

				add(new JLabel("Thursday: ", JLabel.LEFT));
				add(thursdaySelect); 

				add(new JLabel("Friday: ", JLabel.LEFT));
				add(fridaySelect); 

				add(new JLabel("Saturday: ", JLabel.LEFT));
				add(saturdaySelect); 

				add(new JLabel("Sunday: ", JLabel.LEFT));
				add(sundaySelect); 			

			}
		}

		private class subSelectListener implements ActionListener{

			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == subPlanSelected){
					int subIndex = subPlanSelected.getSelectedIndex();
					SubPlan currSub = subPlanList.get(subIndex);

					subName.setText(currSub.getName());
				}
			} 			
		}

		public boolean okData(){

			int weekIndex[] = new int[7];
			weekIndex[0] = mondaySelect.getSelectedIndex();
			weekIndex[1] = tuesdaySelect.getSelectedIndex();
			weekIndex[2] = wednesdaySelect.getSelectedIndex();
			weekIndex[3] = thursdaySelect.getSelectedIndex();
			weekIndex[4] = fridaySelect.getSelectedIndex();
			weekIndex[5] = saturdaySelect.getSelectedIndex();
			weekIndex[6] = sundaySelect.getSelectedIndex();

			int subIndex = subPlanSelected.getSelectedIndex();
			SubPlan currSub = subPlanList.get(subIndex);

			String newPlanName = subName.getText().trim();

			Meal[] meals = currSub.getMeals();
			for(int i=0;i<weekIndex.length;i++){
				Meal selectedMeal = meals[i];
				if(meals[i] != null){
					cook.removeMealFromPlan(currSub.getSubPlanID(), selectedMeal.getMealID(), i+1);
				}
				cook.addMealToSubPlan(currSub.getSubPlanID(), mealList.get(weekIndex[i]).getMealID(), i+1);
			}

			currSub.setName(newPlanName);
			return currSub.updateSubPlan(cook.getDatabase());		 	
		}
	}
}
