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

 class AddIngredientsToMealGui extends JFrame {
	 	//private Database database = new Database ("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/espenme?user=espenme&password=16Sossosem06");
	 	Cook cook = null;
	 	
	 	
	 	public AddIngredientsToMealGui(Cook cook){
	 		this.cook = cook;
	 		AddIngredientsDialog dialog = new AddIngredientsDialog(this);
	 		dialog.setVisible(true);
	  		setTitle("Choose the meal and its ingrediets");
	  		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  		setLayout(new FlowLayout());
	  		setLocation(300, 300); 
	  		dialog.setLocation(350, 350); 
	 	}
	
	 	private class AddIngredientsDialog extends MyDialog {
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
	 			pack();
	 		}
		   
	 		private class IngredientsDatapanel extends JPanel{
	 			public IngredientsDatapanel(){
	 				setLayout(new GridLayout(3,2));
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
	 				
	 				
	 				try {
						ingredientsList = cook.viewIngredients();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					ArrayList<String> my_ingr_list = new ArrayList<>();
					for(Ingredient i: ingredientsList){
						my_ingr_list.add(i.getIngName());
					}
					ingredientSelected = new JComboBox<>(my_ingr_list.toArray());
					
	 				add(new JLabel("Ingredient Id: ", JLabel.RIGHT));
	 				add(ingredientSelected);
				
	 				add(new JLabel("Ingredient quantity: ", JLabel.RIGHT));
	 				add(ingredient_quantityField);
	 			}
	 		}
	
	 		public boolean okData(){
	 			mealIndex = mealIdSelected.getSelectedIndex();
	 			ingredientIndex =ingredientSelected.getSelectedIndex();
	 			
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
	
	 	public static void main(String[] args){
	 		String username = "espenme";
	 		String passingword = "16Sossosem06";
	 		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + passingword;	
	 		Database database = new Database("com.mysql.jdbc.Driver", databasename);
			new AddIngredientsToMealGui(new Cook("","", database));
	 	}
 }  
		
		  
 


