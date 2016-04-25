package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.*;
import controller.TextEditor;

class CreateMealDialog extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cook cook = null; 

	public CreateMealDialog(Cook cook) {
		this.cook = cook;
		DialogWindow dialog = new DialogWindow(this);
		setTitle("Create meal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);

	} 

	private class DialogWindow extends MyDialog{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private TextEditor editor = new TextEditor();
		private JTextField mealNameField = new JTextField(20);
		private JTextField instructionsField = new JTextField(100);
		private JTextField priceField = new JTextField(20);
		private String mealName;
		private String instructions;

		public DialogWindow(JFrame parent){
			super(parent, "New meal");
			add(new JPanel(), BorderLayout.NORTH);
			add(new CustomerDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			setSize(500,200);
			setLocationRelativeTo(null);
		}

		private class CustomerDatapanel extends JPanel{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public CustomerDatapanel(){
				GridLayout superGrid = new GridLayout(6,1);
				setLayout(superGrid);

				add(new JLabel("Name of meal: ", JLabel.LEFT));
				add(mealNameField);

				add(new JLabel("Instructions: ", JLabel.LEFT));
				add(instructionsField);

				add(new JLabel("Price: ", JLabel.LEFT));
				add(priceField);
			}
		}
		public boolean okData(){
			mealName = mealNameField.getText();	
			instructions = instructionsField.getText();
			String priceFieldText = priceField.getText();
			int myPrice = editor.stringToInt(priceFieldText);

			if(myPrice > 0){
				return cook.createMeal(mealName, instructions, myPrice) != null;
			}else{
				JOptionPane.showMessageDialog(null, "Price must be greater than zero","", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		}
	}

}  