package GUI;

import java.awt.event.*;
import javax.swing.*;

public class MyDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

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
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

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