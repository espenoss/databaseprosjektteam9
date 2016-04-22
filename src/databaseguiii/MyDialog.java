package databaseguiii;

import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JOptionPane.*;

public class MyDialog extends JDialog {
	private boolean ok = false; 
	private JButton okButton = new JButton ("OK");
	private ButtonPanel buttonpanel = new ButtonPanel();
	
	protected MyDialog (JFrame parent, String title){
		super(parent, title, true);
		addWindowListener(new WindowListener());
		
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		
		JRootPane board = getRootPane();
		board.setDefaultButton(okButton);
	}
	
	protected boolean isOK(){
		return ok;
	}
	
	
	protected void setOK(boolean value){
		ok = value;
	}
	
	
	protected JPanel getButtonPanel(){
		return buttonpanel;
	}  
	
	
	protected boolean okData(){
		return true;
	}
	
	private class ButtonPanel extends JPanel{
		public ButtonPanel(){
			JButton cancelButton = new JButton ("Cancel");
			Commandlistener buttonlistener = new Commandlistener();
			add(okButton);
			add(cancelButton);
			okButton.addActionListener(buttonlistener);
			cancelButton.addActionListener(buttonlistener);
			
			
			KeyStroke escapeKey = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
			InputMap keystrokemap = cancelButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
			keystrokemap.put(escapeKey, "Cancel"); 
			ActionMap actionmap = cancelButton.getActionMap();
			actionmap.put("Cancel", buttonlistener);
		}
	}
	
	private class Commandlistener extends AbstractAction{
		public void actionPerformed(ActionEvent event){
			String command = event.getActionCommand();
			if(command.equals("OK")){
				if(okData()){ 
					ok = true;
					setVisible(false);
				}
			}else{ 
				ok = false;
				setVisible(false);
			}
		}
	}
	 
	private class WindowListener extends WindowAdapter{
		public void windowClosing(WindowEvent event){
			setVisible(false);
		}
	}
}