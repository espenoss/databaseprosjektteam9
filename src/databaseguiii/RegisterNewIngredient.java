package databaseguiii;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
 } 
		
	 private class DialogWindow extends MyDialog{
			private TextEditor editor = new TextEditor();
			private JTextField ingredientNameField = new JTextField(20);			
			private final String ingredientUnit[] = {"Kilo", "Gramme", "Litre", "Decilitre", "Pieces"}; 
			private JComboBox<String> ingredientList = new JComboBox<String>(ingredientUnit);	
			private JTextField quantityField = new JTextField(20);
			private String ingredient;
	 		private float myQuantity;
	 		private String unit;
			
			
			public DialogWindow(JFrame parent){
				super(parent, "New ingredient");
				add(new JPanel(), BorderLayout.NORTH);
				add(new CustomerDatapanel(),BorderLayout.CENTER);
				add(getButtonPanel(),BorderLayout.SOUTH);
				setSize(550,200);
				setLocationRelativeTo(null);
			}
			
			private class CustomerDatapanel extends JPanel{
				public CustomerDatapanel(){
					GridLayout superGrid = new GridLayout(6,1);
					setLayout(superGrid);
			
					add(new JLabel("Name of ingredient: ", JLabel.LEFT));
					add(ingredientNameField);
					add(new JLabel("Quantity of ingredient: ", JLabel.LEFT));
					add(quantityField);
					add(new JLabel("Unit of ingredient: ", JLabel.LEFT));
					add(ingredientList);

				}
			}
			public boolean okData(){
				
				ingredient = ingredientNameField.getText();
				
				String ingredient_quantity = quantityField.getText();
	 			float my_quantity = editor.stringToFloat(ingredient_quantity);
			 	myQuantity = my_quantity;
			   	unit = ingredientList.getSelectedItem().toString();
			 	
			   	if(myQuantity > 0){
			   		try {
					cook.addNewIngredient(ingredient, myQuantity, unit, cook.getDatabase());
					} catch (Exception e) {
					System.out.println(e.toString());
					}
			   		return true;
			   		}else{
					JOptionPane.showMessageDialog(null, "Quantity must be greater than zero","", JOptionPane.INFORMATION_MESSAGE);
					return false;
				}
			}
		}
	}  

