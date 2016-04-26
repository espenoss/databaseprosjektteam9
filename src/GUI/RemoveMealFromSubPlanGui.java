package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.*;

/**
 * The Class RemoveMealFromSubPlanGui.<br>
 * (Unused)
 */
class RemoveMealFromSubPlanGui extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The cook user object. */
	Cook cook = null;

	/**
	 * Instantiates a new remove the meal from sub plan gui.
	 *
	 * @param cook User object
	 */
	public RemoveMealFromSubPlanGui(Cook cook){
		this.cook = cook;
		RemoveMealFromSubPlanDialog dialog = new RemoveMealFromSubPlanDialog(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		pack();
		// Set window location in middle of screen
		dialog.setLocationRelativeTo(null);
		// Display window
		dialog.setVisible(true);	}

	/**
	 * The Class RemoveMealFromSubPlanDialog.
	 */
	private class RemoveMealFromSubPlanDialog extends MyDialog {
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/** The days_of_week. */
		private final String days_of_week[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday","Sunday"}; 

		/** The day list. */
		private JComboBox dayList = new JComboBox(days_of_week);	

		/** The Sub plan list. */
		private ArrayList<SubPlan> SubPlanList = new ArrayList<SubPlan>();
		
		/** The Sub plan selected. */
		private JComboBox SubPlanSelected;

		/** The meal list. */
		private ArrayList<Meal> mealList = new ArrayList<Meal>();
		
		/** The meal id selected. */
		private JComboBox mealIdSelected;

		/**
		 * Instantiates a new removes the meal from sub plan dialog.
		 *
		 * @param parent the parent
		 */
		public RemoveMealFromSubPlanDialog(JFrame parent){
			super(parent, "Choose supscription plan and meal to be removed");
			add(new JPanel(), BorderLayout.NORTH);
			add(new IngredientsDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			pack();
		}

		/**
		 * The Class IngredientsDatapanel.
		 */
		private class IngredientsDatapanel extends JPanel{
			
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * Instantiates a new ingredients datapanel.
			 */
			public IngredientsDatapanel(){
				setLayout(new GridLayout(3,2));

				// Get all subscripiton plans from database
				SubPlanList = cook.viewAllSubPlans();

				// convert to list of strings
				ArrayList<String> my_sub_list = new ArrayList<>();
				for(SubPlan plan: SubPlanList){
					my_sub_list.add(plan.getName());
				}
				
				// Set up subscription plan selection box
				SubPlanSelected = new JComboBox<>(my_sub_list.toArray());

				// Get all available meals from database
				mealList = cook.viewAvailableMeals();

				// Convert to list of strings
				ArrayList<String> my_list = new ArrayList<>();
				for(Meal m: mealList){
					my_list.add(m.getMealName());
				}
				// Set up meal selection box
				mealIdSelected = new JComboBox<>(my_list.toArray());

				// Add components
				add(new JLabel("Subscription Plan: ", JLabel.RIGHT));
				add(SubPlanSelected);
				
				add(new JLabel("Meal Id: ", JLabel.RIGHT));
				add(mealIdSelected);

				add(new JLabel("Weekday: ", JLabel.RIGHT));
				add(dayList);


			}
		}

		/* (non-Javadoc)
		 * @see GUI.MyDialog#okData()
		 */
		public boolean okData(){
			// Get values from lists
			int myMeal = mealIdSelected.getSelectedIndex()+1;
			int mySubPlan =SubPlanSelected.getSelectedIndex()+1;
			int myDay = dayList.getSelectedIndex()+1;

			// Remove entry frmo database
			return cook.removeMealFromPlan(mySubPlan, myMeal, myDay);
		}
	}
}
