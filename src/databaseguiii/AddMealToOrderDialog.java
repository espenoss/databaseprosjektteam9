package databaseguiii;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import controller.*;
import databasePackage.Database;

public class AddMealToOrderDialog extends JFrame{

	TextEditor edit = new TextEditor();
	private Sales sales = null;
	private Order order = null;
	
	public AddMealToOrderDialog(Sales sales, Order order){
		this.sales = sales;
		this.order = order;
		DialogContent dialog = new DialogContent(this);
		dialog.setVisible(true);
	}
	
	private class DialogContent extends MyDialog{
		private ArrayList<Meal> mealList = null;
		private JComboBox mealSelect;
		private SpinnerDateModel dateSelectModel = new SpinnerDateModel();
		private JSpinner dateSelect = new JSpinner(dateSelectModel);
		private SpinnerNumberModel quantitySelectModel = new SpinnerNumberModel();
		private JSpinner quantitySelect = new JSpinner(quantitySelectModel);		
		
		public DialogContent(JFrame parent){
			super(parent, "Add meal to order");
			add(new JPanel(), BorderLayout.NORTH);
			add(new DataPanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			setSize(500,300);
			setLocationRelativeTo(null);
		}
		
		private class DataPanel extends JPanel{
			public DataPanel(){

				GridLayout superGrid = new GridLayout(6,1);
				setLayout(superGrid);
				
				mealList = sales.viewAvailableMeals();

				ArrayList<String> mealNames = new ArrayList<>();
				
				for(Meal m:mealList){
					mealNames.add(m.getMealName());
				}
				mealSelect = new JComboBox<>(mealNames.toArray());
				
				add(new JLabel("Meal: ", JLabel.LEFT));
				add(mealSelect);
				add(new JLabel("Delivery date: ", JLabel.LEFT));
				add(dateSelect);
				add(new JLabel("Quantity: ", JLabel.LEFT));
				add(quantitySelect);
			}
		}
		
		
		public boolean okData(){
			int mealIndex = mealSelect.getSelectedIndex();
			Meal currMeal= mealList.get(mealIndex);	
			
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			String deliveryDate = s.format(dateSelect.getValue());

			int quantity = (int)quantitySelect.getValue();
			if(quantity < 1){
				JOptionPane.showMessageDialog(null, "Quantity must be greater than zero");
				return false;
			}
			
			return sales.addMealToOrder(order.getOrderID(), currMeal.getMealID(), deliveryDate, quantity);
		}
	}
	
	public static void main(String[] args){
		
 		String username = "espenme";
 		String passingword = "16Sossosem06";
 		String databasename = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + username + "?user=" + username + "&password=" + passingword;	
 		Database database = new Database("com.mysql.jdbc.Driver", databasename);
		AddMealToOrderDialog addMeal = new AddMealToOrderDialog(new Sales("","", database), new Order(10010, "", 10005, "", ""));
		
	}
}
