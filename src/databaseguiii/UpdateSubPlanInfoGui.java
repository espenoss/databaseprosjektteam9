package databaseguiii;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.*;
import databasePackage.Database;

class UpdateSubPlanInfoGui extends JFrame {
	private Cook cook = null; 
  
	public UpdateSubPlanInfoGui(Cook cook) { 
		this.cook = cook;
		UpdateSubPlanDialog dialog = new UpdateSubPlanDialog(this);
		dialog.setVisible(true);
		dialog.setLocation(350, 350); 
		setTitle("Registrer user");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setLocation(300, 300);	    
	} 

	private class UpdateSubPlanDialog extends MyDialog{

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
			
			pack();
		}
				
		private class UserDatapanel extends JPanel{
			public UserDatapanel(){
				setLayout(new GridLayout(2,2));
				try {
					subPlanList=cook.viewAllSubPlans();
				} catch (Exception e) {
					e.printStackTrace();
				}
				ArrayList<String> subList = new ArrayList<>();
				for(SubPlan c: subPlanList){
					subList.add(c.getName());
				}
				subPlanSelect = new JComboBox<>(subList.toArray());
				add(new JLabel("Choose subscription plan: ", JLabel.RIGHT));
				add(subPlanSelect);
				
				add(new JLabel("New name of the supscription plan: ", JLabel.RIGHT));
				add(sub_plan_nameField);
			}
		}
		
		
		public boolean okData(){
			
			
			int subPlanIndex = subPlanSelect.getSelectedIndex();
			SubPlan currSubPlan = subPlanList.get(subPlanIndex);
			
			currSubPlan.setName(sub_plan_nameField.getText());
			try {
				currSubPlan.updateSubPlan(cook.getDatabase());
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			return true;		
		}
	}
	
}
