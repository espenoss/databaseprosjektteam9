package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.*;

/**
 * The Class UpdateSubPlanInfoGui.<br>
 * Used to update subscription plan name
 */
class UpdateSubPlanInfoGui extends JFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The cook user object. */
	private Cook cook = null; 

	/**
	 * Instantiates a new update sub plan info gui.
	 *
	 * @param cook User object
	 */
	public UpdateSubPlanInfoGui(Cook cook) { 
		this.cook = cook;
		UpdateSubPlanDialog dialog = new UpdateSubPlanDialog(this);
		setLayout(new FlowLayout());  
		pack();
		// Set window location in middle of screen
		dialog.setLocationRelativeTo(null);
		// Display window
		dialog.setVisible(true);
	} 

	/**
	 * The Class UpdateSubPlanDialog.
	 */
	private class UpdateSubPlanDialog extends MyDialog{

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;
		
		/** The sub plan list. */
		private ArrayList<SubPlan> subPlanList = new ArrayList<>();
		
		/** The sub plan select. */
		private JComboBox subPlanSelect;

		/** The subscription plan name field. */
		private JTextField sub_plan_nameField = new JTextField(10);
		
		/**
		 * Instantiates a new update sub plan dialog.
		 *
		 * @param parent Parent frame
		 */
		public UpdateSubPlanDialog(JFrame parent){
			super(parent, "Fill in new info about the subscription plan");

			add(new JPanel(), BorderLayout.NORTH);
			add(new UserDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);

			setSize(500, 200);
		}

		/**
		 * The Class UserDatapanel.
		 */
		private class UserDatapanel extends JPanel{
			
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			/**
			 * Instantiates a new user datapanel.
			 */
			public UserDatapanel(){
				setLayout(new GridLayout(4,1));
				
				// Fetch all subscription plans from database
				subPlanList=cook.viewAllSubPlans();

				// Convert to string list
				ArrayList<String> subList = new ArrayList<>();
				for(SubPlan c: subPlanList){
					subList.add(c.getName());
				}
				
				// Set up subscription plan selection box
				subPlanSelect = new JComboBox<>(subList.toArray());

				// Add components
				add(new JLabel("Choose subscription plan: ", JLabel.LEFT));
				add(subPlanSelect);

				add(new JLabel("New name of the supscription plan: ", JLabel.LEFT));
				add(sub_plan_nameField);
			}
		}


		/* (non-Javadoc)
		 * @see GUI.MyDialog#okData()
		 */
		public boolean okData(){

			// Get subscription plan
			int subPlanIndex = subPlanSelect.getSelectedIndex();
			SubPlan currSubPlan = subPlanList.get(subPlanIndex);

			// Update plan name in database
			currSubPlan.setName(sub_plan_nameField.getText());
			return currSubPlan.updateSubPlan(cook.getDatabase());
		}
	}
}
