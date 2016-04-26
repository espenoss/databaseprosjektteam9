package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import controller.*;

/**
 * The Class IngredientsInMealGui.
 * 
 */
class IngredientsInMealGui extends JFrame {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The cook user object. */
	private Cook cook = null;
	
	/**
	 * Instantiates a new frame
	 *
	 * @param cook User object
	 */
	public IngredientsInMealGui(Cook cook){ 
		this.cook=cook;
		IngredientsInMealDialog dialog = new IngredientsInMealDialog(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		pack();
		// Set window location in middle of screen
		dialog.setLocationRelativeTo(null);
		// Display window
		dialog.setVisible(true);
	} 

	/**
	 * The Class IngredientsInMealDialog.
	 */
	private class IngredientsInMealDialog extends MyDialog{

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/** The meal list. */
		private ArrayList<Meal> mealList = new ArrayList<Meal>();
		
		/** The meal id selection combobox. */
		private JComboBox mealIdSelected;

		/**
		 * Instantiates a new ingredients in meal dialog.
		 *
		 * @param parent the parent
		 */
		public IngredientsInMealDialog(JFrame parent){
			super(parent, "Choose the meal");
			add(new JPanel(), BorderLayout.NORTH);
			add(new UserDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			setSize(400,140);
		}

		/**
		 * The Class UserDatapanel.
		 */
		private class UserDatapanel extends JPanel{
			
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * Instantiates a new user datapanel.
			 */
			public UserDatapanel(){
				setLayout(new GridLayout(1,2));

				// Fetch meals from database
				mealList = cook.viewAvailableMeals();

				// Convert to string list
				ArrayList<String> my_list = new ArrayList<>();
				for(Meal m: mealList){
					my_list.add(m.getMealName());
				}
				
				// Set up and add combobox
				mealIdSelected = new JComboBox<>(my_list.toArray());
				add(new JLabel("Meal Id: ", JLabel.RIGHT));
				add(mealIdSelected);

			}
		}

		/* (non-Javadoc)
		 * @see GUI.MyDialog#okData()
		 */
		public boolean okData(){

			// Get meal selected
			int myMeal = mealIdSelected.getSelectedIndex();
			ArrayList <Ingredient > ingrInMeal = mealList.get(myMeal).getIngredients();

			// Collect ingredient information and display in dialog
			String s ="";
			for(int i=0; i<ingrInMeal.size(); i++){
				s +=  ingrInMeal.get(i).toString() + " \n";
			}
			JOptionPane.showMessageDialog(null, s, "Inredients of this meal: ", JOptionPane.INFORMATION_MESSAGE );

			return true;
		}
	}	
}  






