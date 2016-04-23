package databaseguiii;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;

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
		dialog.setLocationRelativeTo(null);
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
			pack();
		}
		
		private class DataPanel extends JPanel{
			public DataPanel(){
				
				setLayout(new GridLayout(3, 2));
				
				try {
					mealList = sales.viewAvailableMeals();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				ArrayList<String> mealNames = new ArrayList<>();
				
				for(Meal m:mealList){
					mealNames.add(m.getMealName());
				}
				mealSelect = new JComboBox<>(mealNames.toArray());
				
				add(new JLabel("Meal: ", JLabel.RIGHT));
				add(mealSelect);
				add(new JLabel("Delivery date: ", JLabel.RIGHT));
				add(dateSelect);
				add(new JLabel("Quantity: ", JLabel.RIGHT));
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
			try{
				sales.addMealToOrder(order.getOrderID(), currMeal.getMealID(), deliveryDate, quantity);
			}catch (Exception e) {
				System.out.println(e.toString());
				return false;
			}
			return true;
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
