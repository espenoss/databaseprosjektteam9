package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controller.*;

class RegisterSubscriptionPlan extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cook cook = null; 

	public RegisterSubscriptionPlan(Cook cook) {
		this.cook = cook;
		DialogWindow dialog = new DialogWindow(this);
		setTitle("Register new subscription plan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout()); 
		pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}

	private class DialogWindow extends MyDialog{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private TextEditor editor = new TextEditor();

		private JTextField sub_plan_nameField = new JTextField(10);
		String sub_plan;


		public DialogWindow(JFrame parent){
			super(parent, "New subscpiption plan");
			add(new JPanel(), BorderLayout.NORTH);
			add(new OrderDatapanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			setSize(500,140);
			setLocationRelativeTo(null);
		}

		private class OrderDatapanel extends JPanel{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public OrderDatapanel(){
				GridLayout superGrid = new GridLayout(2,1);
				setLayout(superGrid);

				add(new JLabel("Name of the supscription plan: ", JLabel.LEFT));
				add(sub_plan_nameField);

			}
		}

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
