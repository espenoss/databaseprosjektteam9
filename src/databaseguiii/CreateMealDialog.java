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

class CreateMealDialog extends JFrame {
	private Cook cook = null; 
	
  	public CreateMealDialog(Cook cook) {
  	  this.cook = cook;
	  DialogWindow dialog = new DialogWindow(this);
	  dialog.setVisible(true);
	  setTitle("Create meal");
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  setLayout(new FlowLayout());
	   
 } 
		
	 private class DialogWindow extends MyDialog{
			private TextEditor editor = new TextEditor();
			private JTextField mealNameField = new JTextField(20);
			private JTextField instructionsField = new JTextField(100);
			private JTextField priceField = new JTextField(20);
			private String mealName;
			private String instructions;
			private boolean available;
			private int price;
			
			
			
			public DialogWindow(JFrame parent){
				super(parent, "New meal");
				add(new JPanel(), BorderLayout.NORTH);
				add(new CustomerDatapanel(),BorderLayout.CENTER);
				add(getButtonPanel(),BorderLayout.SOUTH);
				setSize(500,200);
				setLocationRelativeTo(null);
			}
			
			private class CustomerDatapanel extends JPanel{
				public CustomerDatapanel(){
					setLayout(new GridLayout(3,2));
			
					add(new JLabel("Name of meal: ", JLabel.RIGHT));
					add(mealNameField);

					add(new JLabel("Instructions: ", JLabel.RIGHT));
					add(instructionsField);
					
					add(new JLabel("Price: ", JLabel.RIGHT));
					add(priceField);
				}
			}
			public boolean okData(){
				mealName = mealNameField.getText();	
				instructions = instructionsField.getText();
				String priceFieldText = priceField.getText();
				int myPrice = editor.stringToInt(priceFieldText);
				
				try {
					cook.createMeal(mealName, instructions, myPrice);

				} catch (Exception e) {
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
		new CreateMealDialog(new Cook("","", database));
	}
}  