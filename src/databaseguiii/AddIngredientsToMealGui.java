package databaseguiii;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.*;
import databasePackage.Database;

 class AddIngredientsToMeal extends JFrame {
	/*public boolean addIngredientToMeal(int mealID, int ingredientID, float ingredientQuantity) throws Exception{
		boolean res = QMFood.addIngredientToMeal(mealID, ingredientID, ingredientQuantity, database);
		return res;*/
	  private Database database = new Database ("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/espenme?user=espenme&password=16Sossosem06");
	  Sales sales = new Sales("", 0, "", "", database);
	
	private class AddIngredientsToMealDialog extends MyDialog{
		private TextEditor editor = new TextEditor();
		
		private ArrayList<Meal> mealList = new ArrayList<Meal>();
		private JComboBox mealIdSelected;
		

		private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		String[] my_ingredients = (String[]) ingredients.toArray();
		
		private JTextField ingredient_quantityField = new JTextField(20);
		
		public AddIngredientsToMealDialog(JFrame parent){
			super(parent, "Fill in new information about a customer");
			add(new JPanel(), BorderLayout.NORTH);
			add(new IngredientsDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			customerSelect.addActionListener(this);
			pack();
		}
		private class IngredientsDatapanel extends JPanel{
			public IngredientsDatapanel(){
				setLayout(new GridLayout(9,2));
		
				add(new JLabel("Surname: ", JLabel.RIGHT));
				add(surNameField);

				add(new JLabel("First name: ", JLabel.RIGHT));
				add(firstNameField);
				
				add(new JLabel("Phone number: ", JLabel.RIGHT));
				add(phoneNumberField);
	}
	
	

}
