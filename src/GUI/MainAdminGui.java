package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import controller.*;

/**
 * The Class MainAdminGui.<br>
 * The main menu for Admin users
 */
class MainAdminGui extends JFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The menu selection list. */
	private static final String [] CHOICES =
		{
				"Register new user",
				"Change user information", 
				"View all users", 
				"Register new customer",
				"Register new company", 
				"Change customer information", 
				"View private customers", 
				"View company customers",
				"Register new food order",
				"Change food order", 
				"Retrieve statistics",
				"View available ingredients", 
				"View available meals", 
				"Register new ingredient", 
				"Register new meal", 
				"Add ingredient to meal",
				"Register subscription plan", 
				"Add meal to sub plan", 
				"View food orders by date",
				"View ingredients in meal", 
				"View information about a single customer", 
				"Add meal or subscription to order", 
				"View all sub plans",
				"Update subscription plan"};

	/** The menu list. */
	private JList<String> choice_list = new JList<String>(CHOICES);  
	
	/** The admin user object. */
	private Admin admin = null;

	/** User menu selection constants. */
	private static final int REGISTER_NEW_USER = 0;
	private static final int CHANGE_USER_INFO = 1;
	private static final int VIEW_ALL_USERS = 2;
	private static final int REGISTER_NEW_CUSTOMER = 3;		
	private static final int REGISTER_NEW_COMPANY = 4;
	private static final int CHANGE_CUSTOMER_INFO = 5;
	private static final int VIEW_PRIVATE_CUSTOMERS = 6;		
	private static final int VIEW_COMPANY_CUSTOMERS = 7;		
	private static final int REGISTER_NEW_ORDER = 8;
	private static final int CHANGE_ORDER = 9;
	private static final int RETRIEVE_STATISTICS = 10;
	private static final int VIEW_INGREDIENTS = 11;
	private static final int VIEW_MEALS = 12;
	private static final int REGISTER_INGREDIENT = 13;
	private static final int REGISTER_MEAL = 14;
	private static final int ADD_INGREDIENT_TO_MEAL = 15;
	private static final int REGISTER_SUB_PLAN = 16;
	private static final int ADD_MEAL_TO_SUB_PLAN = 17;
	private static final int VIEW_FOOD_ORDERS = 18;
	private static final int VIEW_INGREDIENTS_IN_MEAL = 19;
	private static final int VIEW_INFO_ABOUT_SINGLE_CUST = 20;
	private static final int VIEW_FOOD_ORDER_OF_SINGLE_CUSTOMER = 21;
	private static final int VIEW_ALL_SUBPLANS = 22;
	private static final int UPDATE_SUB_PLAN = 23;


	/**
	 * Instantiates a new main admin gui.
	 *
	 * @param admin User object
	 */
	public MainAdminGui(Admin admin) {
		this.admin = admin;
		setTitle("Admin control panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Font bigText = new Font("SansSerif", Font.PLAIN, 25);

		JLabel topText = new JLabel("Choose one of the following options.");
		add(topText, BorderLayout.NORTH);

		JScrollPane scrollPaneWithList = new JScrollPane(choice_list);
		add(scrollPaneWithList, BorderLayout.CENTER);

		MenuListListener listener = new MenuListListener();
		choice_list.addListSelectionListener(listener);
		choice_list.setFont(bigText);
		setSize(700, 700);
		setLocationRelativeTo(null);
	}



	/**
	 * The Class MenuListListener.<br>
	 * Listens to menu choice and creates new windows based on selection.
	 */
	private class MenuListListener implements ListSelectionListener {
		
		/* (non-Javadoc)
		 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
		 */
		public void valueChanged(ListSelectionEvent hendelse) {

			int choices = choice_list.getSelectedIndex();

			choice_list.clearSelection();	

			if(choices == REGISTER_NEW_USER){
				new RegisterUserDialog(admin);

			}else if(choices==CHANGE_USER_INFO){
				new ChangeUserInfoDialog(admin);					
			}
			else if(choices==VIEW_ALL_USERS){
				ArrayList<User> userList = null;
				String s = "";
				userList = admin.viewUserList();

				for(int i = 0; i < userList.size(); i++ ){
					s +=  userList.get(i).toString() + " \n";
				}
				JOptionPane.showMessageDialog(null, s, "All users: ", JOptionPane.INFORMATION_MESSAGE );

			}else if(choices == REGISTER_NEW_CUSTOMER){
				new RegisterCustomerDialog(new Sales(admin.getUserID(), admin.getName(), admin.getDatabase()));

			}else if(choices==REGISTER_NEW_COMPANY){
				new RegisterCompanyToCustomerDialog(new Sales(admin.getUserID(), admin.getName(), admin.getDatabase()));

			}else if(choices==CHANGE_CUSTOMER_INFO){
				new ChangeCustomerInfoDialog(new Sales(admin.getUserID(), admin.getName(), admin.getDatabase()));

			}else if(choices==VIEW_PRIVATE_CUSTOMERS){
				ArrayList<Customer> c = null;
				c = admin.viewCustomerList();
				String[] s = new String[c.size()];
				for( int i = 0; i < c.size(); i++ ){
					s[i] = c.get(i).toString() + " ";
				}


				JScrollPane scrollpane = new JScrollPane(); 
				JList<String> list = new JList<String>(s);
				scrollpane = new JScrollPane(list);
				JPanel panel = new JPanel(); 
				panel.add(scrollpane);
				scrollpane.getViewport().add(list);		    	 
				JOptionPane.showMessageDialog(null, scrollpane, "All customers: ", JOptionPane.INFORMATION_MESSAGE );

			}
			else if(choices==VIEW_COMPANY_CUSTOMERS){
				ArrayList<Customer> c = null;
				c= admin.viewCompanyList(); 
				String[] s = new String[c.size()];
				for( int i = 0; i < c.size(); i++ ){
					Customer t = c.get(i);
					s[i] = t.getCustomerID() + ", " + t.getCompanyName() + ": " + t.getSurName() + ", " + t.getFirstName() + "\n";
				}

				JScrollPane scrollpane = new JScrollPane(); 
				JList list = new JList(s);
				scrollpane = new JScrollPane(list);
				JPanel panel = new JPanel(); 
				panel.add(scrollpane);
				scrollpane.getViewport().add(list);		    	 
				JOptionPane.showMessageDialog(null, scrollpane, "All customers: ", JOptionPane.INFORMATION_MESSAGE );

			}
			else if(choices==REGISTER_NEW_ORDER){
				new RegisterOrderDialog(new Sales(admin.getUserID(), admin.getName(), admin.getDatabase()));

			}else if(choices==CHANGE_ORDER){
				new ChangeOrderDialog(new Sales(admin.getUserID(), admin.getName(), admin.getDatabase()));

			}
			else if(choices==RETRIEVE_STATISTICS){
				new GetStatistics(new Admin(admin.getUserID(), admin.getName(), admin.getDatabase()));
			}else if(choices==VIEW_INGREDIENTS){
				ArrayList<Ingredient> ing = null;
				ing= admin.viewIngredients(); 
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

			}else if(choices==VIEW_MEALS){
				ArrayList<Meal> m = null;
				m= admin.viewAvailableMeals();
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
			}else if(choices==REGISTER_INGREDIENT){
				new RegisterNewIngredient(new Cook(admin.getUserID(), admin.getName(), admin.getDatabase()));
			}else if(choices==REGISTER_MEAL){
				new CreateMealDialog(new Cook(admin.getUserID(), admin.getName(), admin.getDatabase()));
			}else if (choices == ADD_INGREDIENT_TO_MEAL){
				new AddIngredientsToMealGui(new Cook(admin.getUserID(), admin.getName(), admin.getDatabase()));
			}else if(choices == REGISTER_SUB_PLAN){
				new RegisterSubscriptionPlan(new Cook(admin.getUserID(), admin.getName(), admin.getDatabase()));
			}else if(choices == ADD_MEAL_TO_SUB_PLAN){
				new AddMealToSubPlanDialog (new Cook(admin.getUserID(), admin.getName(), admin.getDatabase()));
			}else if (choices == VIEW_FOOD_ORDERS){
				new ViewFoodOrders (new Admin (admin.getUserID(), admin.getName(), admin.getDatabase()));
			}
			else if(choices==VIEW_INGREDIENTS_IN_MEAL){
				new IngredientsInMealGui(new Cook(admin.getUserID(), admin.getName(), admin.getDatabase()));

			}else if(choices==VIEW_INFO_ABOUT_SINGLE_CUST){
				new ViewSingleCustomerGui(new Sales(admin.getUserID(), admin.getName(), admin.getDatabase()));
			}else if(choices==VIEW_FOOD_ORDER_OF_SINGLE_CUSTOMER){
				new ViewFoodOrdersByCustomerGui(new Sales(admin.getUserID(), admin.getName(), admin.getDatabase()));
			}else if (choices == VIEW_ALL_SUBPLANS){
				ArrayList<SubPlan> sp = null;

				sp = admin.viewAllSubPlans();
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

			}else if(choices == UPDATE_SUB_PLAN){
				new UpdateSubPlanInfoGui(new Cook(admin.getUserID(), admin.getName(), admin.getDatabase()));
			}
		}
	}
}
