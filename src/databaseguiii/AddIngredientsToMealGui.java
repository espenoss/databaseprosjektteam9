package databaseguiii;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.*;
import databasePackage.*;

 class AddIngredientsToMeal extends JFrame {
	 	private Database database = new Database ("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/espenme?user=espenme&password=16Sossosem06");
	 	Cook cook = new Cook("", "", database);
	 
	
	 private class AddIngredients extends MyDialog {
		 private TextEditor editor = new TextEditor();
		 private ArrayList<Meal> mealList = null;
		 private JComboBox mealIdSelected;
		
		 private ArrayList<Ingredient> ingredientsList = null;
		 private JComboBox ingredientSelected;
		
		 private JTextField ingredient_quantityField = new JTextField(20);
		 private float myQuantity;
		 int mealIndex;
		 int ingredientIndex;
		 
			
		 
		 public AddIngredients(JFrame parent){
			 super(parent, "Select ingredients ");
			 add(new JPanel(), BorderLayout.NORTH);
			 add(new IngredientsDatapanel(),BorderLayout.CENTER);
			 add(getButtonPanel(),BorderLayout.SOUTH);
			 pack();
		}
		   
		 private class IngredientsDatapanel extends JPanel{
			public IngredientsDatapanel(){
				setLayout(new GridLayout(3,2));
				try {
					mealList = cook.viewAvailableMeals();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ArrayList<String> meallist = new ArrayList<>();
				for(Meal m : mealList){
					meallist.add(m.getMealName());
				}
				mealIdSelected = new JComboBox<>(meallist.toArray());
			
				try {
					ingredientsList = cook.viewIngredients();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ArrayList<String> ingredientlist = new ArrayList<>();
				for(Ingredient i : ingredientsList){
					ingredientlist.add(i.getIngName());
				}
				ingredientSelected = new JComboBox<>(ingredientlist.toArray());
				
				
				add(new JLabel("Meal Id: ", JLabel.RIGHT));
				add(mealIdSelected);

				add(new JLabel("Ingredient Id: ", JLabel.RIGHT));
				add(ingredientSelected);
				
				add(new JLabel("Ingredient quantity: ", JLabel.RIGHT));
				add(ingredient_quantityField);
			}
		}
	
		 public boolean okData(){
			 mealIndex = mealIdSelected.getSelectedIndex();
			 Meal currMeal = mealList.get(mealIndex);
			 ingredientIndex = ingredientSelected.getSelectedIndex();
			 Ingredient currIngredient = ingredientsList.get(ingredientIndex);
		 
			 String ingredient_quantity = ingredient_quantityField.getText();
			 float my_quantity = editor.stringToFloat(ingredient_quantity);
			 myQuantity = my_quantity;
	 
			 try {
				 cook.addIngredientToMeal(mealIndex, ingredientIndex, myQuantity) ;
			 }catch (Exception e) {
				 System.out.println(e.toString());
			 }
			 return true;
		 }
	 }
	 public AddIngredientsToMeal(){ 
		 AddIngredients addIngredients = new AddIngredients(this);
		 addIngredients.setVisible(true);
		 setTitle("Add ingredients");
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setLayout(new FlowLayout());
		 setLocation(300, 300); 
		 addIngredients.setLocation(350, 350);  	 
	 }	 
}
 
 class AddIngredientsToMealGui {
	 static public void main(String[] args) {
		 AddIngredientsToMeal test = new AddIngredientsToMeal();
	 }   
} 
		
		
		  
 


