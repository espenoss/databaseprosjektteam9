package databaseguiii;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controller.*;

public class RegisterOrderDialog extends MyDialog{
	private TextEditor editor = new TextEditor();
	private JTextField customerIdField = new JTextField(10);
	private JTextField dateField = new JTextField(10);
	private JTextField deliveryDateField = new JTextField(20);
	private JTextField infoField = new JTextField(20);
	private JTextField useIdField = new JTextField(20);
	private JTextField mealField = new JTextField(20);
	

	public RegisterOrderDialog(JFrame parent){
		super(parent, "New orer");
		add(new JPanel(), BorderLayout.NORTH);
		add(new UserDatapanel(),BorderLayout.CENTER);
		add(getButtonPanel(),BorderLayout.SOUTH);
		pack();
	}
	
	private class UserDatapanel extends JPanel{
		public UserDatapanel(){
			setLayout(new GridLayout(6,2));
			add(new JLabel("Customer ID: ", JLabel.RIGHT));
			add(customerIdField);
			
			add(new JLabel("Order date: ", JLabel.RIGHT));
			add(dateField);
			
			add(new JLabel("Delivery date: ", JLabel.RIGHT));
			add(deliveryDateField);
			

			add(new JLabel("Information: alergies, preferences.", JLabel.RIGHT));
			add(infoField);
			
			add(new JLabel("User ID: ", JLabel.RIGHT));
			add(useIdField);
			
			add(new JLabel("Meals: ", JLabel.RIGHT));
			add(mealField);
			
		}
	}
	public boolean showDialog(){
		
		String text = customerIdField.getText();
		editor.StringToInt(text);
		text.(order.getCustomerID());
		
		
		
		dateField.setText(order.getOrderDate());
		deliveryDateField.setText(order.getDeliveryDate());
		infoField.setText(order.getInfo());
		useIdField.setText(order.getUserID());
		mealField.setText(order.getMeals());
		
		setOK(false);
		pack();
		customerIdField.requestFocusInWindow();
		setVisible(true);
		if(isOK()){
/*			user.setUserType(userTypeField.getText()); her mangler vi set metoder fra klasser Order
	
			user.setPassword(passwordField.getText());
			user.setUserID(userIDfield.getText());*/
			return true;
		}else{
			return false;
		}
	}
	
	/*protected boolean okData(){
		String name = usernameField.getText().trim();
		String password = passwordField.getText().trim();
		if(name.equals("")|| password.equals("")){
			showMessageDialog(UserDialog.this, "Username, role and password must be given.");
			/*if(!username.equals("")){
				usernameField.requestFocusInWindow();
			}else{
				passwordField.requestFocusInWindow();
			}
			return false;
			}else{
				return true;
			}   
		return true;
		} 
		*/
	}