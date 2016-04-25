package controller;

import javax.swing.JOptionPane;

/**
 * The Class TextEditor.<br>
 * Contains convenience methods for String handling.
 */
public class TextEditor {
	
	/**
	 * Instantiates a new text editor.
	 */
	public TextEditor(){
	}

	/**
	 * String to int.<br>
	 * Removes blank spaces and parses string to an int.
	 *
	 * @param text Text to be parsed
	 * @return Integer representation of text, else -1 if not parseable
	 */
	public int stringToInt(String text){
		int i = 0;
		text.trim();

		if(text == null || text.isEmpty()){
			JOptionPane.showMessageDialog(null,"Input is empty.");
		}else{
			try{
				i = Integer.parseInt(text);
				return i;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Input is not an integer.");
			}
		}
		return -1;
	}
	
	/**
	 * String to float.<br>
	 * Removes blank spaces and parses a string to a float.
	 *
	 * @param text Text to be parsed
	 * @return Float representation of text, else -1 if not parseable
	 */
	public float stringToFloat(String text){
		float i = 0;
		text.trim();
		
		if(text == null || text.isEmpty()){
			JOptionPane.showMessageDialog(null,"Input is empty.");
		}else{
			try{
				i = Float.parseFloat(text);
				return i;
			} catch (NumberFormatException e){
				JOptionPane.showMessageDialog(null,"Input not a float");
			}
		}
		return -1;
	}
	
	
	/**
	 * Trim and lowercase.<br>
	 * Removes blank spaces and sets characters to lowercase.
	 *
	 * @param text Text to be processed
	 * @return Text with leading and trailing blankspace removed and all lowercase
	 */
	public String trimAndLowercase(String text){ // 
		if(!text.equals(null)){
			text.trim();
			text.toLowerCase();
		} else {
			JOptionPane.showMessageDialog(null,"Please enter a text");
		}
		return text;
	}
	
	/**
	 * Trim only.<br>
	 * Removes blank spaces at either end of string.
	 *
	 * @param text Text to be processed
	 * @return Text with leading and trailing blankspace removed	 */
	public String trimOnly(String text){
		if(!text.equals(null)){
			text.trim();
		} else{
			JOptionPane.showMessageDialog(null,"Please enter a text");
		}
		return text;
	}
	
	/**
	 * Check to see if string only contains letters.
	 *
	 * @param string String to check
	 * @return true, if string only contains letters
	 */
	public boolean isAlpha(String string) {
	    char[] chars = string.toCharArray();

	    for (char c : chars) {
	        if(!Character.isLetter(c) && c != ' ') {
	            return false;
	        }
	    }

	    return true;
	}
	
	/**
	 * Checks to see if string only contains numbers.
	 *
	 * @param numbers string to check
	 * @return true, if string only contains numbers
	 */
	public boolean isNumeric(String numbers) {
	    char[] chars = numbers.toCharArray();

	    for (char c : chars) {
	        if(!Character.isDigit(c)) {
	            return false;
	        }
	    }

	    return true;
	}
}