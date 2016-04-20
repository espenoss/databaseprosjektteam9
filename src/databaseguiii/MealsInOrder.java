package databaseguiii;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MealsInOrder extends JFrame {
	
	private class addMealDialog extends MyDialog{
		
		public addMealDialog(JFrame parent){
			super(parent, "Add meal to order");
			add(new JPanel(), BorderLayout.NORTH);
			add(new MealOrderDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			pack();			
		}
		
		private class MealOrderDatapanel extends JPanel{
			public MealOrderDatapanel(){
				
			}
		}

	}
	
	public MealsInOrder(){
		addMealDialog dialog = new addMealDialog(this);
		dialog.setVisible(true);
		setTitle("Register new order");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setLocation(300, 300); 
		dialog.setLocation(350, 350);
			
	}

	public static void main(String[] args){	
		MealsInOrder a = new MealsInOrder();
	}
}