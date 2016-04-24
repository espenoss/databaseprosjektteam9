package databaseguiii;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;
import controller.*;
import databasePackage.Database;

class MainCookGui extends JFrame {
	private static final String [] CHOICES =
		{"View food orders","Mark order as ready for delivery","View available ingredients","Register new ingredient",
			 "View available meals", "Register new meal","View ingregredients in meal", "Add ingredient to meal"};
	private JList<String> choice_list = new JList<String>(CHOICES);
	Cook cook = null;
	
	public static final int VIEW_FOOD_ORDERS = 0;
	public static final int MARK_AS_READY_FOR_DELIVERY = 1;
	public static final int VIEW_INGREDIENTS = 2;
	public static final int REGISTER_INGREDIENT = 3;
	public static final int VIEW_MEALS = 4;
	public static final int REGISTER_MEAL = 5;
	public static final int VIEW_INGREDIENTS_IN_MEAL = 6;
	public static final int ADD_INGREDIENT_TO_MEAL = 7;
	
	
	

	public MainCookGui(Cook cook) {
		this.cook = cook;
		setTitle("Cook control panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Font bigText = new Font("SansSerif", Font.PLAIN, 25);
    
		JLabel ledetekst = new JLabel("Choose one of the following options.");
		add(ledetekst, BorderLayout.NORTH);

		JScrollPane rullefeltMedListe = new JScrollPane(choice_list);
		add(rullefeltMedListe, BorderLayout.CENTER);

		ListeboksLytter lytter = new ListeboksLytter();
    	choice_list.addListSelectionListener(lytter);
    	choice_list.setFont(bigText);

    	setSize(500,500);
		setLocationRelativeTo(null);
    	
	}

	private class ListeboksLytter implements ListSelectionListener {

		private SpinnerDateModel dateSelectModel = new SpinnerDateModel();
		private JSpinner dateSelect = new JSpinner(dateSelectModel);
		
		public void valueChanged(ListSelectionEvent hendelse) {
			
			Object[] values = choice_list.getSelectedValuesList().toArray();
			int choices = choice_list.getSelectedIndex();
			
			if(choices == VIEW_FOOD_ORDERS){
				new ViewFoodOrders (new Admin (cook.getUserID(), cook.getName(), cook.getDatabase()));
			}else if(choices==MARK_AS_READY_FOR_DELIVERY){
				new MarkOrderAsReadyDialog(new Cook(cook.getUserID(), cook.getName(), cook.getDatabase()));
			}else if(choices==VIEW_INGREDIENTS){
				ArrayList<Ingredient> ing = null;
		    	  try {
		    		  ing= cook.viewIngredients(); 
				} catch (Exception e) {
					e.printStackTrace();
				}
		    	  String[] s = new String[ing.size()];
			    	 for( int i = 0; i < ing.size(); i++ ){
			    		 Ingredient ingredient = ing.get(i);
			    		 s[i] = ingredient.ingName+ "\n";
			    	 }
			    	 
			    	 JScrollPane scrollpane = new JScrollPane(); 
			         JList list = new JList(s);
			         scrollpane = new JScrollPane(list);
			         JPanel panel = new JPanel(); 
			         panel.add(scrollpane);
			         scrollpane.getViewport().add(list);		    	 
			    	 JOptionPane.showMessageDialog(null, scrollpane, "All ingredients: ", JOptionPane.INFORMATION_MESSAGE );
			}else if(choices == REGISTER_INGREDIENT){
				new RegisterNewIngredient(new Cook(cook.getUserID(), cook.getName(), cook.getDatabase()));
			}else if(choices==VIEW_MEALS){
				ArrayList<Meal> m = null;
		    	  try {
		    		  m= cook.viewAvailableMeals();
				} catch (Exception e) {
					e.printStackTrace();
				}
		    	  String[] s = new String[m.size()];
			    	 for( int i = 0; i < m.size(); i++ ){
			    		 Meal meal = m.get(i);
			    		 s[i] = meal.getMealName()+ "\n";
			    	 }
			    	 
			    	 JScrollPane scrollpane = new JScrollPane(); 
			         JList list = new JList(s);
			         scrollpane = new JScrollPane(list);
			         JPanel panel = new JPanel(); 
			         panel.add(scrollpane);
			         scrollpane.getViewport().add(list);		    	 
			    	 JOptionPane.showMessageDialog(null, scrollpane, "All meals: ", JOptionPane.INFORMATION_MESSAGE );
				
			}else if(choices==REGISTER_MEAL){
				new CreateMealDialog(new Cook(cook.getUserID(), cook.getName(), cook.getDatabase()));
			}else if(choices==VIEW_INGREDIENTS_IN_MEAL){
				new IngredientsInMealGui(new Cook(cook.getUserID(), cook.getName(), cook.getDatabase()));
			}else if(choices==ADD_INGREDIENT_TO_MEAL){
				new AddIngredientsToMealGui(new Cook(cook.getUserID(), cook.getName(), cook.getDatabase()));
			
			}
			
		}
	}
	public static void main(String[] args){
 		String username = "espenme";
 		String passingword = "16Sossosem06";
 		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + passingword;	
 		Database database = new Database("com.mysql.jdbc.Driver", databasename);
 		MainCookGui mag = new MainCookGui(new Cook("","", database));
		mag.setVisible(true);
 	}
	
}

	
