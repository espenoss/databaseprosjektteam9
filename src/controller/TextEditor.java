package controller;

import javax.swing.JOptionPane;

public class TextEditor {
	
	public TextEditor(){
	}

	public int stringToInt(String text){ // removes blank spaces and parses string to an int
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
	
	public float stringToFloat(String text){ // removes blank spaces and parses a string to a float
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
	
	
	public String trimAndLowercase(String text){ // removes blank spaces and sets characters to lowercase
		if(!text.equals(null)){
			text.trim();
			text.toLowerCase();
		} else {
			JOptionPane.showMessageDialog(null,"Please enter a text");
		}
		return text;
	}
	
	public String trimOnly(String text){ // removes blank spaces
		if(!text.equals(null)){
			text.trim();
		} else{
			JOptionPane.showMessageDialog(null,"Please enter a text");
		}
		return text;
	}
	
	// Check to see if string only contains letters
	public boolean isAlpha(String name) {
	    char[] chars = name.toCharArray();

	    for (char c : chars) {
	        if(!Character.isLetter(c) && c != ' ') {
	            return false;
	        }
	    }

	    return true;
	}
	
	public boolean isNumeric(String numbers) {
	    char[] chars = numbers.toCharArray();

	    for (char c : chars) {
	        if(!Character.isDigit(c)) {
	            return false;
	        }
	    }

	    return true;
	}
	/*
	public static void main(String[] args){
		TextEditor t = new TextEditor();
		
		System.out.println("float ok: "+t.stringToFloat("1.2"));
		System.out.println("float not ok: "+t.stringToFloat("hei"));
		System.out.println("float empty: "+t.stringToFloat(""));
		
		System.out.println("int ok: "+t.stringToInt("1"));
		System.out.println("int not ok: "+t.stringToInt("hei"));
		System.out.println("int empty: "+t.stringToInt(""));
		
	}
	*/
}