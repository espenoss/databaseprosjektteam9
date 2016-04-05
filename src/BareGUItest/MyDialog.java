package BareGUItest;

import java.awt.*; 
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JOptionPane.*;

public class MyDialog extends JDialog {
	private boolean ok = false; //Har brukeren trykket OK knappen?
	private JButton okButton = new JButton ("OK");
	private ButtonPanel buttonpanel = new ButtonPanel();
	
	protected MyDialog (JFrame parent, String title){
		super(parent, title, true);
		addWindowListener(new WindowListener());
		
		/*Vi vil programmere lukking av vinduet selv*/
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		/*Setter OK-knappen til standardknappen. Reagerer på Enter-tasten.*/
		JRootPane board = getRootPane();
		board.setDefaultButton(okButton);
	}
	
	/*Til bruk for subklasser som kan kontrollere verdien til OK*/
	protected boolean isOK(){
		return ok;
	}
	
	/*Til bruk for subklasser som kan sette verdien ok */
	protected void setOK(boolean value){
		ok = value;
	}
	
	/*Returnerer en referanse til knappepanelet. Subklassen må plassere dette panelet riktig i vinduet.*/
	protected JPanel getButtonPanel(){
		return buttonpanel;
	}  
	
	/*Subklasser skal ha sin egen utgave av metoden okData() dersom det er ønskelig at dataene skal kontrolleres før de eventuelt godtas.*/
	protected boolean okData(){
		return true;
	}
	
	/*privat klasse med panelet som inneholder de to knappene ok og avbryt.*/
	private class ButtonPanel extends JPanel{
		public ButtonPanel(){
			JButton cancelButton = new JButton ("Cancel");
			Commandlistener buttonlistener = new Commandlistener();
			add(okButton);
			add(cancelButton);
			okButton.addActionListener(buttonlistener);
			cancelButton.addActionListener(buttonlistener);
			
			/*Definerer akselerasjonstast til Avbryt-knappen*/
			KeyStroke escapeKey = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
			InputMap keystrokemap = cancelButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
			keystrokemap.put(escapeKey, "Cancel"); //Knytter en kommando til escape-tasten
			ActionMap actionmap = cancelButton.getActionMap();
			actionmap.put("Cancel", buttonlistener); //Definerer en aksjon
		}
	}
	
	//Se kommentarer s. 644 i boka
	private class Commandlistener extends AbstractAction{
		public void actionPerformed(ActionEvent event){
			String command = event.getActionCommand();
			if(command.equals("OK")){
				if(okData()){ //Skal bare lukke vinduet dersom OK data
					ok = true;
					setVisible(false);
				}
			}else{ //Brukeren har trykket Avbryt, lukker vinduet
				ok = false;
				setVisible(false);
			}
		}
	}
	
	/*Privat klasse som beskriver hva som skal skje når brukeren prøver å lukke vinduet. Vi spør om eventuelle data skal lagres.
	 * Hvis ja, blir de lagret dersom okData() returnerer true. */
	 
	private class WindowListener extends WindowAdapter{
		public void windowClosing(WindowEvent event){
			int answer = showConfirmDialog(MyDialog.this, "Do you want to save data?", "Closing dialog window", YES_NO_OPTION);
			if(answer == YES_OPTION){
				if(okData()){ //Skal bare lukke vinduet dersom ok data
					ok = true;
					setVisible(false);
				}
			}else{ //Data skal ikke lagres, lukker vinduet
				ok = false;
				setVisible(false);
			}
		}
	}
}