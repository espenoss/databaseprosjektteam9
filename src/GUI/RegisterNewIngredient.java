package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.*;
import controller.TextEditor;

/**
 * The Class RegisterNewIngredient.<br>
 * Used to register new order in database
 */
class RegisterNewIngredient extends JFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The cook user object. */
	private Cook cook = null; 

	/**
	 * Instantiates a new register new ingredient.
	 *
	 * @param cook User object
	 */
	public RegisterNewIngredient(Cook cook) {
		this.cook = cook;
		DialogWindow dialog = new DialogWindow(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		pack();
		// Set window location in middle of screen
		dialog.setLocationRelativeTo(null);
		// Display window
		dialog.setVisible(true);
	} 

	/**
	 * The Class DialogWindow.
	 */
	private class DialogWindow extends MyDialog{
		
		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;
		
		/** Convenience string handling methods*/
		private TextEditor editor = new TextEditor();
		
		/** The ingredient name field. */
		private JTextField ingredientNameField = new JTextField(20);			
		
		/** The ingredient unit. */
		private final String ingredientUnit[] = {"Kilo", "Gramme", "Litre", "Decilitre", "Pieces"}; 
		
		/** The ingredient list. */
		private JComboBox<String> ingredientList = new JComboBox<String>(ingredientUnit);	
		
		/** The quantity field. */
		private JTextField quantityField = new JTextField(20);

		/**
		 * Instantiates a new dialog window.
		 *
		 * @param parent the parent
		 */
		public DialogWindow(JFrame parent){
			super(parent, "New ingredient");
			add(new JPanel(), BorderLayout.NORTH);
			add(new CustomerDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			setSize(550,200);
		}

		/**
		 * The Class CustomerDatapanel.
		 */
		private class CustomerDatapanel extends JPanel{
			
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * Instantiates a new customer datapanel.
			 */
			public CustomerDatapanel(){
				setLayout(new GridLayout(6,1));

				add(new JLabel("Name of ingredient: ", JLabel.LEFT));
				add(ingredientNameField);
				add(new JLabel("Quantity of ingredient: ", JLabel.LEFT));
				add(quantityField);
				add(new JLabel("Unit of ingredient: ", JLabel.LEFT));
				add(ingredientList);

			}
		}
		
		/* (non-Javadoc)
		 * @see GUI.MyDialog#okData()
		 */
		public boolean okData(){

			// Get values from field
			String ingredient = ingredientNameField.getText();

			String ingredientQuantity = quantityField.getText();
			float myQuantity = editor.stringToFloat(ingredientQuantity);
			String unit = ingredientList.getSelectedItem().toString();

			// Register to database if valid
			if(myQuantity > 0){
				return cook.addNewIngredient(ingredient, myQuantity, unit) != null;
			}else{
				JOptionPane.showMessageDialog(null, "Quantity must be greater than zero","", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		}
	}
}  

