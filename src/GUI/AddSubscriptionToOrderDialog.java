package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

import controller.SubPlan;
import database.Database;
import controller.Order;
import controller.Sales;

public class AddSubscriptionToOrderDialog extends JFrame{
	private Sales sales = null;
	private Order order = null;

	public AddSubscriptionToOrderDialog(Sales sales, Order order){
		this.sales = sales;
		this.order = order;
		DialogContent dialog = new DialogContent(this);
		pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}

	private class DialogContent extends MyDialog{
		private ArrayList<SubPlan> subList = null;
		private JComboBox subSelect;
		private SpinnerDateModel fromDateSelectModel = new SpinnerDateModel();
		private SpinnerDateModel toDateSelectModel = new SpinnerDateModel();
		private JSpinner fromDateSelect = new JSpinner(fromDateSelectModel);
		private JSpinner toDateSelect = new JSpinner(toDateSelectModel);
		private SpinnerNumberModel quantitySelectModel = new SpinnerNumberModel();
		private JSpinner quantitySelect = new JSpinner(quantitySelectModel);		

		public DialogContent(JFrame parent){
			super(parent, "Add meal to order");
			add(new JPanel(), BorderLayout.NORTH);
			add(new DataPanel(),BorderLayout.CENTER);
			add(getButtonPanel(),BorderLayout.SOUTH);
			setSize(500,200);
		}

		private class DataPanel extends JPanel{
			public DataPanel(){

				GridLayout superGrid = new GridLayout(8,1);
				setLayout(superGrid);

				try {
					subList = sales.viewAllSubPlans();
				} catch (Exception e) {
					e.printStackTrace();
				}

				ArrayList<String> subNames = new ArrayList<>();

				for(SubPlan s:subList){
					subNames.add(s.getName());
				}
				subSelect = new JComboBox<>(subNames.toArray());

				add(new JLabel("Plan: ", JLabel.LEFT));
				add(subSelect);
				add(new JLabel("From date: ", JLabel.LEFT));
				add(fromDateSelect);
				add(new JLabel("To date: ", JLabel.LEFT));
				add(toDateSelect);				
				add(new JLabel("Quantity: ", JLabel.LEFT));
				add(quantitySelect);
			}
		}


		public boolean okData(){
			int subIndex = subSelect.getSelectedIndex();
			SubPlan currSub= subList.get(subIndex);	

			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			String fromDate = s.format(fromDateSelect.getValue());
			String toDate = s.format(toDateSelect.getValue());

			int quantity = (int)quantitySelect.getValue();

			return sales.registerSubscriptionOrder(order, quantity, fromDate, toDate, currSub.getSubPlanID());
		}
	}
}

