package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controller.*;

/**
 * The Class AddIngredientsToMealGui.<br>
 * Used to add ingredient to a meal recipe.
 */
class AddIngredientsToMealGui extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The cook user object. */
	private Cook cook = null;


	/**
	 * Instantiates a new frame.
	 *
	 * @param cook User object
	 */
	public AddIngredientsToMealGui(Cook cook){
		this.cook = cook;
		// Add dialog
		AddIngredientsDialog dialog = new AddIngredientsDialog(this);
		setLayout(new FlowLayout());
		pack();
		// Set window location in middle of screen
		dialog.setLocationRelativeTo(null);
		// Display window
		dialog.setVisible(true);
	}

	/**
	 * The Class AddIngredientsDialog.
	 */
	private class AddIngredientsDialog extends MyDialog {
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/** Convenience string handling methods*/
		private TextEditor editor = new TextEditor();

		/** List of meals available to add ingredients to*/
		private ArrayList<Meal> mealList = new ArrayList<Meal>();
		
		/** The combobox to select meal. */
		private JComboBox mealIdSelected;

		/** List of ingredients available to add to meal*/
		private ArrayList<Ingredient> ingredientsList = new ArrayList<Ingredient>();
		
		/** The combobox to select ingredient to add. */
		private JComboBox ingredientSelected;

		/** The ingredient quantity entry field. */
		private JTextField ingredient_quantityField = new JTextField(20);

		/** The quantity. */
		private float myQuantity;
		
		/** The meal index. */
		int mealIndex;
		
		/** The ingredient index. */
		int ingredientIndex;



		/**
		 * Instantiates dialog.
		 *
		 * @param parent Parent frame
		 */
		public AddIngredientsDialog(JFrame parent){
			super(parent, "Select ingredients ");
			add(new JPanel(), BorderLayout.NORTH);
			add(new IngredientsDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			setSize(500,600);
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
				GridLayout superGrid = new GridLayout(6,1);
				setLayout(superGrid);

				// Fetch available meals from database
				mealList = cook.viewAvailableMeals();

				// Convert to string arraylist
				ArrayList<String> my_list = new ArrayList<>();
				for(Meal m: mealList){
					my_list.add(m.getMealName());
				}

				//Set up meal select combobox
				mealIdSelected = new JComboBox<>(my_list.toArray());

				// Fetch ingredients from database
				ingredientsList = cook.viewIngredients();
				
				// Convert to string arraylist
				ArrayList<String> my_ingr_list = new ArrayList<>();
				for(Ingredient i: ingredientsList){
					my_ingr_list.add(i.getIngName());
				}
				
				// Set up ingredient select combobox
				ingredientSelected = new JComboBox<>(my_ingr_list.toArray());

				Font bigText = new Font("SansSerif", Font.PLAIN, 25);				
				
				// Add components
				add(new JLabel("Meal Id: ", JLabel.LEFT)).setFont(bigText);
				add(mealIdSelected).setFont(bigText);				
				add(new JLabel("Ingredient Id: ", JLabel.LEFT)).setFont(bigText);
				add(ingredientSelected).setFont(bigText);
				add(new JLabel("Ingredient quantity: ", JLabel.LEFT)).setFont(bigText);
				add(ingredient_quantityField).setFont(bigText);
			}
		}

		/* (non-Javadoc)
		 * @see GUI.MyDialog#okData()
		 */
		public boolean okData(){
			// Get index of selected ingredients
			mealIndex = mealIdSelected.getSelectedIndex();
			ingredientIndex =ingredientSelected.getSelectedIndex();

			// Get ingredient quantity text and convert to float
			String ingredient_quantity = ingredient_quantityField.getText();
			float my_quantity = editor.stringToFloat(ingredient_quantity);
			myQuantity = my_quantity;

			// Get meal ID and ingredient ID
			int mealId = mealList.get(mealIndex).getMealID();
			int ingredientId = ingredientsList.get(ingredientIndex).getIngID();

			// Register in database, return true if OK
			return cook.addIngredientToMeal(mealId, ingredientId, myQuantity) ;

		}
	}

}  





