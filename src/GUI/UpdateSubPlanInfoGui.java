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

class UpdateSubPlanInfoGui extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cook cook = null; 

	public UpdateSubPlanInfoGui(Cook cook) { 
		this.cook = cook;
		UpdateSubPlanDialog dialog = new UpdateSubPlanDialog(this);
		setTitle("Registrer user");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());  
		pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	} 

	private class UpdateSubPlanDialog extends MyDialog{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private TextEditor editor = new TextEditor();
		private ArrayList<SubPlan> subPlanList = new ArrayList<>();
		private JComboBox subPlanSelect;

		private JTextField sub_plan_nameField = new JTextField(10);
		String sub_plan;

		public UpdateSubPlanDialog(JFrame parent){
			super(parent, "Fill in new info about the subscription plan");

			add(new JPanel(), BorderLayout.NORTH);
			add(new UserDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);

			setSize(500, 200);
		}

		private class UserDatapanel extends JPanel{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public UserDatapanel(){
				GridLayout superGrid = new GridLayout(4,1);
				setLayout(superGrid);
				subPlanList=cook.viewAllSubPlans();

				ArrayList<String> subList = new ArrayList<>();
				for(SubPlan c: subPlanList){
					subList.add(c.getName());
				}
				subPlanSelect = new JComboBox<>(subList.toArray());
				add(new JLabel("Choose subscription plan: ", JLabel.LEFT));
				add(subPlanSelect);

				add(new JLabel("New name of the supscription plan: ", JLabel.LEFT));
				add(sub_plan_nameField);
			}
		}


		public boolean okData(){


			int subPlanIndex = subPlanSelect.getSelectedIndex();
			SubPlan currSubPlan = subPlanList.get(subPlanIndex);

			currSubPlan.setName(sub_plan_nameField.getText());
			return currSubPlan.updateSubPlan(cook.getDatabase());
		}
	}

}
