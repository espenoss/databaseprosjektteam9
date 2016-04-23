package databaseguiii;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.*;
import controller.TextEditor;
import databasePackage.Database;

class RegisterNewIngredient extends JFrame {
	private Cook cook = null; 
	
  	public RegisterNewIngredient(Cook cook) {
  	  this.cook = cook;
	  DialogWindow dialog = new DialogWindow(this);
	  dialog.setVisible(true);
	  setTitle("Register new ingredient");
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  setLayout(new FlowLayout());
	  setLocation(300, 300); 
	  dialog.setLocation(350, 350);  
 } 
		
	 private class DialogWindow extends MyDialog{
			private TextEditor editor = new TextEditor();
			private JTextField ingredientNameField = new JTextField(20);
			private JTextField ingredientUnitField = new JTextField(20);
			private JTextField quantityField = new JTextField(20);
			private String ingredient;
	 		private float myQuantity;
	 		private String unit;
			
			
			public DialogWindow(JFrame parent){
				super(parent, "New ingredient");
				add(new JPanel(), BorderLayout.NORTH);
				add(new CustomerDatapanel(),BorderLayout.CENTER);
				add(getButtonPanel(),BorderLayout.SOUTH);
				pack();
			}
			
			private class CustomerDatapanel extends JPanel{
				public CustomerDatapanel(){
					setLayout(new GridLayout(3,2));
			
					add(new JLabel("Name of ingredient: ", JLabel.RIGHT));
					add(ingredientNameField);
					add(new JLabel("Quantity of ingredient: ", JLabel.RIGHT));
					add(quantityField);

					add(new JLabel("Unit of ingredient: ", JLabel.RIGHT));
					add(ingredientUnitField);

				}
			}
			public boolean okData(){
				
				ingredient = ingredientNameField.getText();
				
				String ingredient_quantity = quantityField.getText();
	 			float my_quantity = editor.stringToFloat(ingredient_quantity);
			 	myQuantity = my_quantity;
			 	
			 	unit=ingredientUnitField.getText();
				
				try {
					cook.addNewIngredient(ingredient, myQuantity, unit, cook.getDatabase());

				} catch (Exception e) {
					System.out.println(e.toString());
				}
				return true;		
			}
	 	}
	}  