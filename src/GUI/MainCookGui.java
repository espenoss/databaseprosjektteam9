package GUI;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

import controller.*;
import database.Database;

class MainCookGui extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String [] CHOICES =
		{"View food orders","Mark order as ready for delivery","View available ingredients","Register new ingredient",
				"View available meals", "Register new meal","View ingredients in meal", "Add ingredient to meal", 
				"View all subscription plans", "Register new subscription plan", "Add meal to subscription plan","Rename subscription plan"};
	private JList<String> choice_list = new JList<String>(CHOICES);
	private Cook cook = null;

	private static final int VIEW_FOOD_ORDERS = 0;
	private static final int MARK_AS_READY_FOR_DELIVERY = 1;
	private static final int VIEW_INGREDIENTS = 2;
	private static final int REGISTER_INGREDIENT = 3;
	private static final int VIEW_MEALS = 4;
	private static final int REGISTER_MEAL = 5;
	private static final int VIEW_INGREDIENTS_IN_MEAL = 6;
	private static final int ADD_INGREDIENT_TO_MEAL = 7;
	private static final int VIEW_ALL_SUBPLANS = 8;
	private static final int REGISTER_SUB_PLAN = 9;
	private static final int ADD_MEAL_TO_SUB_PLAN = 10;
	private static final int RENAME_SUP_PLAN = 11;


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

		public void valueChanged(ListSelectionEvent hendelse) {

			int choices = choice_list.getSelectedIndex();

			choice_list.clearSelection();
			
			if(choices == VIEW_FOOD_ORDERS){
				new ViewFoodOrders (new Admin (cook.getUserID(), cook.getName(), cook.getDatabase()));
			}else if(choices==MARK_AS_READY_FOR_DELIVERY){
				new MarkOrderAsReadyDialog(new Cook(cook.getUserID(), cook.getName(), cook.getDatabase()));
			}else if(choices==VIEW_INGREDIENTS){
				ArrayList<Ingredient> ing = null;
				ing= cook.viewIngredients(); 

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
				m= cook.viewAvailableMeals();
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
			}else if (choices == VIEW_ALL_SUBPLANS){
				ArrayList<SubPlan> sp = null;
				sp = cook.viewAllSubPlans();
				String[] s = new String[sp.size()];
				for( int i = 0; i < sp.size(); i++ ){
					s[i] = sp.get(i).toString() + " ";
				}
				JScrollPane scrollpane = new JScrollPane(); 
				JList<String> list = new JList<String>(s);
				scrollpane = new JScrollPane(list);
				JPanel panel = new JPanel(); 
				panel.add(scrollpane);
				scrollpane.getViewport().add(list);		    	 
				JOptionPane.showMessageDialog(null, scrollpane, "All sub plans: ", JOptionPane.INFORMATION_MESSAGE );

			}else if(choices == REGISTER_SUB_PLAN){
				new RegisterSubscriptionPlan(new Cook(cook.getUserID(), cook.getName(), cook.getDatabase()));
			}else if(choices == ADD_MEAL_TO_SUB_PLAN){
				new AddMealToSubPlanDialog (new Cook(cook.getUserID(), cook.getName(), cook.getDatabase()));
			}else if(choices == RENAME_SUP_PLAN){
				new UpdateSubPlanInfoGui(new Cook(cook.getUserID(), cook.getName(), cook.getDatabase()));
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


