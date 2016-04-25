package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.*;


public class GetMealsAsText extends JFrame {
	private SubPlan subplan = null;
	private Meal meal;
	private Admin admin = null;


	public GetMealsAsText(Admin admin) {
		this.admin = admin;
		MealsAsTextDialog dialog = new MealsAsTextDialog(this);
		setTitle("View meal in subplan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		pack();
		dialog.setLocationRelativeTo(null);;  
		dialog.setVisible(true);
	}

	private class MealsAsTextDialog extends MyDialog{


		private TextEditor editor = new TextEditor();
		private ArrayList<SubPlan> subList = new ArrayList<SubPlan>();
		private JComboBox subIdSelected;
		private int my_sub;



		public MealsAsTextDialog(JFrame parent){
			super(parent, "Choose a subscription plan");
			add(new JPanel(), BorderLayout.NORTH);
			add(new UserDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			pack();
		}

		private class UserDatapanel extends JPanel{
			public UserDatapanel(){
				setLayout(new GridLayout(1,2));

				subList = admin.viewAllSubPlans();

				ArrayList<String> my_list = new ArrayList<>();
				for(SubPlan m: subList){
					my_list.add(m.getName());
				}
				subIdSelected = new JComboBox<>(my_list.toArray());
				add(new JLabel("Meal Id: ", JLabel.RIGHT));
				add(subIdSelected);
			}
		}

		public boolean okData(){
			my_sub = subIdSelected.getSelectedIndex();
			subplan = subList.get(my_sub);
			SubPlan myPlan = new SubPlan(my_sub, subplan.getName());
			String s = myPlan.getMealsAsText();	

			JOptionPane.showMessageDialog(null, s, "Meals of the chosen subscription plan: ", JOptionPane.INFORMATION_MESSAGE );

			return true;
		}
	}

}
