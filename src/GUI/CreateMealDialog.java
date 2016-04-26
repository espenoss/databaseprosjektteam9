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

/**
 * The Class CreateMealDialog.<br>
 * Used to register new meal in database
 */
class CreateMealDialog extends JFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The cook user object. */
	private Cook cook = null; 

	/**
	 * Instantiates a new frame
	 *
	 * @param cook User object
	 */
	public CreateMealDialog(Cook cook) {
		this.cook = cook;
		DialogWindow dialog = new DialogWindow(this);
		setTitle("Create meal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
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
		
		/** The editor. */
		private TextEditor editor = new TextEditor();
		
		/** The meal name field. */
		private JTextField mealNameField = new JTextField(20);
		
		/** The instructions field. */
		private JTextField instructionsField = new JTextField(100);
		
		/** The price field. */
		private JTextField priceField = new JTextField(20);
		
		/** The meal name. */
		private String mealName;
		
		/** The instructions. */
		private String instructions;

		/**
		 * Instantiates a new dialog window.
		 *
		 * @param parent the parent
		 */
		public DialogWindow(JFrame parent){
			super(parent, "New meal");
			add(new JPanel(), BorderLayout.NORTH);
			add(new MealDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			setSize(500,200);
		}

		/**
		 * The Class CustomerDatapanel.
		 */
		private class MealDatapanel extends JPanel{
			
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * Instantiates a new customer datapanel.
			 */
			public MealDatapanel(){
				setLayout(new GridLayout(6,1));

				add(new JLabel("Name of meal: ", JLabel.LEFT));
				add(mealNameField);

				add(new JLabel("Instructions: ", JLabel.LEFT));
				add(instructionsField);

				add(new JLabel("Price: ", JLabel.LEFT));
				add(priceField);
			}
		}
		
		/* (non-Javadoc)
		 * @see GUI.MyDialog#okData()
		 */
		public boolean okData(){
			// Get meal information
			mealName = mealNameField.getText();	
			instructions = instructionsField.getText();
			String priceFieldText = priceField.getText();
			int myPrice = editor.stringToInt(priceFieldText);

			
			// If data is valid and register if it is
			if(mealName.equals("")){
				JOptionPane.showMessageDialog(null, "Name can not be blank");
				return false;
			}
			
			if(myPrice > 0){
				return cook.createMeal(mealName, instructions, myPrice) != null;
			}else{
				JOptionPane.showMessageDialog(null, "Price must be greater than zero","", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		}
	}

}  