package GUI;

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

class AddIngredientsToMealGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Cook cook = null;


	public AddIngredientsToMealGui(Cook cook){
		this.cook = cook;
		AddIngredientsDialog dialog = new AddIngredientsDialog(this);
		setTitle("Choose the meal and its ingrediets");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);

	}

	private class AddIngredientsDialog extends MyDialog {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private TextEditor editor = new TextEditor();

		private ArrayList<Meal> mealList = new ArrayList<Meal>();
		private JComboBox mealIdSelected;

		private ArrayList<Ingredient> ingredientsList = new ArrayList<Ingredient>();
		private JComboBox ingredientSelected;

		private JTextField ingredient_quantityField = new JTextField(20);

		private float myQuantity;
		int mealIndex;
		int ingredientIndex;



		public AddIngredientsDialog(JFrame parent){
			super(parent, "Select ingredients ");
			add(new JPanel(), BorderLayout.NORTH);
			add(new IngredientsDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			setSize(500,200);
			setLocationRelativeTo(null);
		}

		private class IngredientsDatapanel extends JPanel{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public IngredientsDatapanel(){
				GridLayout superGrid = new GridLayout(6,1);
				setLayout(superGrid);

				mealList = cook.viewAvailableMeals();

				ArrayList<String> my_list = new ArrayList<>();
				for(Meal m: mealList){
					my_list.add(m.getMealName());
				}
				
				mealIdSelected = new JComboBox<>(my_list.toArray());
				add(new JLabel("Meal Id: ", JLabel.LEFT));
				add(mealIdSelected);

				ingredientsList = cook.viewIngredients();

				ArrayList<String> my_ingr_list = new ArrayList<>();
				for(Ingredient i: ingredientsList){
					my_ingr_list.add(i.getIngName());
				}
				ingredientSelected = new JComboBox<>(my_ingr_list.toArray());

				add(new JLabel("Ingredient Id: ", JLabel.LEFT));
				add(ingredientSelected);

				add(new JLabel("Ingredient quantity: ", JLabel.LEFT));
				add(ingredient_quantityField);
			}
		}

		public boolean okData(){
			mealIndex = mealIdSelected.getSelectedIndex();
			ingredientIndex =ingredientSelected.getSelectedIndex();

			String ingredient_quantity = ingredient_quantityField.getText();
			float my_quantity = editor.stringToFloat(ingredient_quantity);
			myQuantity = my_quantity;

			int mealId = mealList.get(mealIndex).getMealID();
			int ingredientId = ingredientsList.get(ingredientIndex).getIngID();
			
			return cook.addIngredientToMeal(mealId, ingredientId, myQuantity) ;

		}
	}

}  





