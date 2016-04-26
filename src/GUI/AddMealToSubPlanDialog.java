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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.*;

/**
 * The Class AddMealToSubPlanDialog.
 * Used to add meal to subscription plan
 */
class AddMealToSubPlanDialog extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The cook user object. */
	private Cook cook = null;

	/**
	 * Instantiates a new adds the meal to sub plan dialog.
	 *
	 * @param cook User object
	 */
	public AddMealToSubPlanDialog(Cook cook){
		this.cook = cook;
		// Add dialog
		AddMealDialog dialog = new AddMealDialog(this);
		setTitle("Choose the meal and its ingrediets");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		pack();
		// Set window location in middle of screen
		dialog.setLocationRelativeTo(null);
		// Display window
		dialog.setVisible(true);
	}

	/**
	 * The Class AddMealDialog.
	 */
	private class AddMealDialog extends MyDialog {
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/** The subscription name. */
		private JTextField subName = null;
		
		/** The monday meal selection spinner. */
		private JComboBox mondaySelect = null;	
		
		/** The tuesday meal selection spinner.. */
		private JComboBox tuesdaySelect = null;	
		
		/** The wednesday meal selection spinner.. */
		private JComboBox wednesdaySelect = null;	
		
		/** The thursday meal selection spinner.. */
		private JComboBox thursdaySelect = null;	
		
		/** The friday meal selection spinner.. */
		private JComboBox fridaySelect = null;	
		
		/** The saturday meal selection spinner. */
		private JComboBox saturdaySelect = null;	
		
		/** The sunday meal selection spinner. */
		private JComboBox sundaySelect = null;	

		/** The subscription plan list. */
		private ArrayList<SubPlan> subPlanList = null;
		
		/** The subscription plan selection combobox. */
		private JComboBox subPlanSelected;

		/** The meal list. */
		private ArrayList<Meal> mealList = null;

		/**
		 * Instantiates a new adds the meal dialog.
		 *
		 * @param parent the parent
		 */
		public AddMealDialog(JFrame parent){
			super(parent, "Choose supscription plan and meals");
			add(new JPanel(), BorderLayout.NORTH);
			add(new MealDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			setSize(500,600);
			setLocationRelativeTo(null);
		}

		/**
		 * The Class IngredientsDatapanel.
		 */
		private class MealDatapanel extends JPanel{
			
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * Instantiates a new ingredients datapanel.
			 */
			public MealDatapanel(){
				setLayout(new GridLayout(18,1));

				// Fetch all subscription plans from database
				subPlanList = cook.viewAllSubPlans();

				// Put names in string list
				ArrayList<String> my_sub_list = new ArrayList<>();
				for(SubPlan plan: subPlanList){
					my_sub_list.add(plan.getName());
				}

				// Fetch available meals from database
				mealList = cook.viewAvailableMeals();

				// Put names in string list				
				ArrayList<String> mealNames = new ArrayList<String>();
				for(Meal m:mealList){
					mealNames.add(m.getMealName());
				}

				// Add meal names to weekday selection comboboxes
				mondaySelect = new JComboBox<>(mealNames.toArray());	
				tuesdaySelect = new JComboBox<>(mealNames.toArray());	
				wednesdaySelect = new JComboBox<>(mealNames.toArray());	
				thursdaySelect = new JComboBox<>(mealNames.toArray());	
				fridaySelect = new JComboBox<>(mealNames.toArray());	
				saturdaySelect = new JComboBox<>(mealNames.toArray());	
				sundaySelect = new JComboBox<>(mealNames.toArray());	

				// Add subscription names to subscription selection combobox
				subPlanSelected = new JComboBox<>(my_sub_list.toArray());

				// Add components
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

		/**
		 * Listener for the subscription list
		 * Sets subscription name in name entry field
		 */
		private class subSelectListener implements ActionListener{

			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == subPlanSelected){
					int subIndex = subPlanSelected.getSelectedIndex();
					SubPlan currSub = subPlanList.get(subIndex);
					subName.setText(currSub.getName());
				}
			} 			
		}

		/* (non-Javadoc)
		 * @see GUI.MyDialog#okData()
		 */
		public boolean okData(){

			// Get  selected indices of meals
			int weekIndex[] = new int[7];
			weekIndex[0] = mondaySelect.getSelectedIndex();
			weekIndex[1] = tuesdaySelect.getSelectedIndex();
			weekIndex[2] = wednesdaySelect.getSelectedIndex();
			weekIndex[3] = thursdaySelect.getSelectedIndex();
			weekIndex[4] = fridaySelect.getSelectedIndex();
			weekIndex[5] = saturdaySelect.getSelectedIndex();
			weekIndex[6] = sundaySelect.getSelectedIndex();

			// Get selected subscription
			int subIndex = subPlanSelected.getSelectedIndex();
			SubPlan currSub = subPlanList.get(subIndex);

			// Get new subscription name
			String newPlanName = subName.getText().trim();
			if(newPlanName.equals("")){
				JOptionPane.showMessageDialog(null, "Subscription name can not be blank");
				return false;
			}

			// Get meals selected and add to subscription
			Meal[] meals = currSub.getMeals();
			for(int i=0;i<weekIndex.length;i++){
				Meal selectedMeal = meals[i];
				// If meal is already registered that day, remove it
				if(meals[i] != null){
					cook.removeMealFromPlan(currSub.getSubPlanID(), selectedMeal.getMealID(), i+1);
				}
				cook.addMealToSubPlan(currSub.getSubPlanID(), mealList.get(weekIndex[i]).getMealID(), i+1);
			}

			// Register new name
			currSub.setName(newPlanName);
			return currSub.updateSubPlan(cook.getDatabase());		 	
		}
	}
}
