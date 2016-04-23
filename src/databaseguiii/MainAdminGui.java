package databaseguiii;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.SpinnerDateModel;


import controller.*;
import databasePackage.Database;

class MainAdminGui extends JFrame {
		private JList list = new JList();
		private static final String [] CHOICES =
		    {"Register new user","Change user information", "View all users", "Register new customer",
		    "Register new company", "Change customer information", "View private customers", 
		   	"View company customers","Register new food order","Change food order", 
		    "Retrieve statistics","View available ingredients", 
		    "View available meals", "Register new ingredient", "Register new meal", "Add ingredient to meal",
		    "Register subscription plan", "Add meal to sub plan", "View food orders",
		    "View ingredients in meal", "View information about a single customer", "View food orders of a single customer", 
		    "View all sub plans", "View info about single subscription plan","Update subscription plan"};

		private JList<String> choice_list = new JList<String>(CHOICES);  
		Admin admin = null;
		Meal meal=null;
		
		public static final int REGISTER_NEW_USER = 0;
		public static final int CHANGE_USER_INFO = 1;
		public static final int VIEW_ALL_USERS = 2;
		public static final int REGISTER_NEW_CUSTOMER = 3;		
		public static final int REGISTER_NEW_COMPANY = 4;
		public static final int CHANGE_CUSTOMER_INFO = 5;
		public static final int VIEW_PRIVATE_CUSTOMERS = 6;		
		public static final int VIEW_COMPANY_CUSTOMERS = 7;		
		public static final int REGISTER_NEW_ORDER = 8;
		public static final int CHANGE_ORDER = 9;
		public static final int RETRIEVE_STATISTICS = 10;
		public static final int VIEW_INGREDIENTS = 11;
		public static final int VIEW_MEALS = 12;
		public static final int REGISTER_INGREDIENT = 13;
		public static final int REGISTER_MEAL = 14;
		public static final int ADD_INGREDIENT_TO_MEAL = 15;
		public static final int REGISTER_SUB_PLAN = 16;
		public static final int ADD_MEAL_TO_SUB_PLAN = 17;
		public static final int VIEW_FOOD_ORDERS = 18;
		public static final int VIEW_INGREDIENTS_IN_MEAL = 19;
		public static final int VIEW_INFO_ABOUT_SINGLE_CUST = 20;
		public static final int VIEW_FOOD_ORDER_OF_SINGLE_CUSTOMER = 21;
		public static final int VIEW_ALL_SUBPLANS = 22;
		public static final int LIST_SUBSCRIPTION_PLAN = 23;
		public static final int UPDATE_SUP_PLAN = 24;

		
	
		
		public MainAdminGui(Admin admin) {
			this.admin = admin;
			setTitle("Admin control panel");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Font bigText = new Font("SansSerif", Font.PLAIN, 30);

			JLabel ledetekst = new JLabel("Choose one of the following options.");
			add(ledetekst, BorderLayout.NORTH);
	  
			JScrollPane rullefeltMedListe = new JScrollPane(choice_list);
			add(rullefeltMedListe, BorderLayout.CENTER);

			ListeboksLytter lytter = new ListeboksLytter();
			choice_list.addListSelectionListener(lytter);
			choice_list.setFont(bigText);
			setSize(700, 700);
		}
		


		private class ListeboksLytter implements ListSelectionListener {
			private Database database = new Database("com.mysql.jdbc.Driver", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/espenme?user=espenme&password=16Sossosem06");
			
			private SpinnerDateModel dateSelectModel = new SpinnerDateModel();
			private JSpinner dateSelect = new JSpinner(dateSelectModel);
			

			public void valueChanged(ListSelectionEvent hendelse) {
			  
			Object[] values = choice_list.getSelectedValuesList().toArray();
			int choices = choice_list.getSelectedIndex();
			
			if(choices == REGISTER_NEW_USER){
				try {
					new RegisterUserDialog(admin);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}else if(choices==CHANGE_USER_INFO){
				try {
					new ChangeUserInfoDialog(admin);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(choices==VIEW_ALL_USERS){
				ArrayList<User> userList = null;
				String s = "";
				try {
					userList = admin.viewUserList();
				} catch (Exception e) {
					e.printStackTrace();
				}
				for(int i = 0; i < userList.size(); i++ ){
					s +=  userList.get(i).toString() + " \n";
				}
				JOptionPane.showMessageDialog(null, s, "All users: ", JOptionPane.INFORMATION_MESSAGE );
				
	      	}else if(choices == REGISTER_NEW_CUSTOMER){
	      		new RegisterCustomerDialog(new Sales(admin.getUserID(), admin.getName(), database));
	      		
	      	}else if(choices==REGISTER_NEW_COMPANY){
	      		new RegisterCompanyToCustomerDialog(new Sales(admin.getUserID(), admin.getName(), database));
	      		
	      	}else if(choices==CHANGE_CUSTOMER_INFO){
	      		 new ChangeCustomerInfoDialog(new Sales(admin.getUserID(), admin.getName(), database));
	      		
	      	}else if(choices==VIEW_PRIVATE_CUSTOMERS){
	      		ArrayList<Customer> c = null;
	      		try {
	    		 	c = admin.viewCustomerList();
				}
	    	 	catch (Exception e) {
	    	 		e.printStackTrace();
	    	 	}
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
	    	  try {
	    		  c= admin.viewCompanyList(); 
			} catch (Exception e) {
				e.printStackTrace();
			}
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
	    	  new RegisterOrderDialog(new Sales(admin.getUserID(), admin.getName(), database));

	      }else if(choices==CHANGE_ORDER){
	    	  try {
				new ChangeOrderDialog(new Sales(admin.getUserID(), admin.getName(), database));
			} catch (Exception e) {
				e.printStackTrace();
			}
	      }
	      else if(choices==RETRIEVE_STATISTICS){
	    	  new GetStatistics(new Admin(admin.getUserID(), admin.getName(), database));
	    }else if(choices==VIEW_INGREDIENTS){
	    	ArrayList<Ingredient> ing = null;
	    	  try {
	    		  ing= admin.viewIngredients(); 
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
	    	
	    	}else if(choices==VIEW_MEALS){
	    		ArrayList<Meal> m = null;
		    	  try {
		    		  m= admin.viewAvailableMeals();
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
	    		}else if(choices==REGISTER_INGREDIENT){
	    			new RegisterNewIngredient(new Cook(admin.getUserID(), admin.getName(), database));
	    		}else if(choices==REGISTER_MEAL){
	    			new CreateMealDialog(new Cook(admin.getUserID(), admin.getName(), database));
	    		}else if (choices == ADD_INGREDIENT_TO_MEAL){
	    			new AddIngredientsToMealGui(new Cook(admin.getUserID(), admin.getName(), database));
	    		}else if(choices == REGISTER_SUB_PLAN){
	    			new RegisterSubscriptionPlan(new Cook(admin.getUserID(), admin.getName(), database));
	    		}else if(choices == ADD_MEAL_TO_SUB_PLAN){
	    			new AddMealToSubPlanDialog (new Cook(admin.getUserID(), admin.getName(), database));
	    		}else if (choices == VIEW_FOOD_ORDERS){
	    			new ViewFoodOrders (new Admin (admin.getUserID(), admin.getName(), database));
	    		}
	    		else if(choices==VIEW_INGREDIENTS_IN_MEAL){
	    			new IngredientsInMealGui(new Cook(admin.getUserID(), admin.getName(), database));
	    				
	    		}else if(choices==VIEW_INFO_ABOUT_SINGLE_CUST){
	    			new ViewSingleCustomerGui(new Sales(admin.getUserID(), admin.getName(), database));
	    		}else if(choices==VIEW_FOOD_ORDER_OF_SINGLE_CUSTOMER){
	    			new ViewFoodOrdersByCustomerGui(new Sales(admin.getUserID(), admin.getName(), database));
	    		}else if (choices == VIEW_ALL_SUBPLANS){
	    			ArrayList<SubPlan> sp = null;
		      		try {
		    		 	sp = admin.viewAllSubPlans();
					}
		    	 	catch (Exception e) {
		    	 		e.printStackTrace();
		    	 	}
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
	    			}else if (choices == LIST_SUBSCRIPTION_PLAN){
//	    				new GetMealsAsText(new Admin(admin.getUserID(), admin.getName(), database));
	    			}else if(choices == UPDATE_SUP_PLAN){
	    				new UpdateSubPlanInfoGui(new Cook(admin.getUserID(), admin.getName(), database));
	    			}
			}
		}
		
	 	public static void main(String[] args){
	 		String username = "espenme";
	 		String passingword = "16Sossosem06";
	 		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + passingword;	
	 		Database database = new Database("com.mysql.jdbc.Driver", databasename);
			MainAdminGui mag = new MainAdminGui(new Admin("","", database));
			mag.setVisible(true);
	 	}
		
	}
	