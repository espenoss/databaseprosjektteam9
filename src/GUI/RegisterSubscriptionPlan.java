package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controller.*;

/**
 * The Class RegisterSubscriptionPlan.<br>
 * Used to register new subscription plan
 */
class RegisterSubscriptionPlan extends JFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The cook  user object. */
	private Cook cook = null; 

	/**
	 * Instantiates a new register subscription plan.
	 *
	 * @param cook User object
	 */
	public RegisterSubscriptionPlan(Cook cook) {
		this.cook = cook;
		DialogWindow dialog = new DialogWindow(this);
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

		/** The sub_plan_name field. */
		private JTextField sub_plan_nameField = new JTextField(10);
		
		/** The sub_plan. */
		String sub_plan;


		/**
		 * Instantiates a new dialog window.
		 *
		 * @param parent the parent
		 */
		public DialogWindow(JFrame parent){
			super(parent, "New subscpiption plan");
			add(new JPanel(), BorderLayout.NORTH);
			add(new OrderDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			setSize(500,140);
			setLocationRelativeTo(null);
		}

		/**
		 * The Class OrderDatapanel.
		 */
		private class OrderDatapanel extends JPanel{
			
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * Instantiates a new order datapanel.
			 */
			public OrderDatapanel(){
				GridLayout superGrid = new GridLayout(2,1);
				setLayout(superGrid);

				add(new JLabel("Name of the supscription plan: ", JLabel.LEFT));
				add(sub_plan_nameField);

			}
		}

		/* (non-Javadoc)
		 * @see GUI.MyDialog#okData()
		 */
		public boolean okData(){
			sub_plan = sub_plan_nameField.getText();

			try {
				cook.registerSubPlan(sub_plan);
			}catch (Exception e) {
				System.out.println(e.toString());
			}
			return true;
		}	
	}
}
