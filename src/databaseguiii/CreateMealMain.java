package databaseguiii;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Cook;
import controller.TextEditor;
import databasePackage.Database;

class CreateMeal extends JFrame {
	private Database database = new Database("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/espenme?user=espenme&password=16Sossosem06");
	private Cook cook = new Cook("",0, "","", database); 
		
	 private class CustomerDialog extends MyDialog{
			private TextEditor editor = new TextEditor();
		//	String mealName, String instructions, boolean available, int price
			
			private JTextField mealNameField = new JTextField(20);
			private JTextField instructionsField = new JTextField(100);
			private JTextField priceField = new JTextField(20);
			private String mealName;
			private String instructions;
			private boolean available;
			private int price;
			
			
			public CustomerDialog(JFrame parent){
				super(parent, "New meal");
				add(new JPanel(), BorderLayout.NORTH);
				add(new CustomerDatapanel(),BorderLayout.CENTER);
				add(getButtonPanel(),BorderLayout.SOUTH);
				pack();
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
					cook.createMeal(mealName, instructions, available, myPrice);
				} catch (Exception e) {
					System.out.println(e.toString());
				}
				return true;		
			}
		}
			
	  	public CreateMeal() {
		  CustomerDialog dialog = new CustomerDialog(this);
		  dialog.setVisible(true);
		  setTitle("Create meal");
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  setLayout(new FlowLayout());
		  setLocation(300, 300); 
		  dialog.setLocation(350, 350);  
	 } 
}  


	class CreateMealMain {
	  static public void main(String[] args) {
		  CreateMeal test = new CreateMeal();
	  }   
	} 