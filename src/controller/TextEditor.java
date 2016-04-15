package controller;

public class TextEditor {
	
	public TextEditor(){
	}

	public int stringToInt(String text){ // removes blank spaces and parses string to an int
		int i = 0;
		if(!text.equals(null)){
			text.trim();
			try{
				i = Integer.parseInt(text);
			} catch (NumberFormatException e) {
				System.out.println("Input not an int");
			}
		}
		return i;
	}
	
	public float stringToFloat(String text){ // removes blank spaces and parses a string to a float
		float i = 0;
		if(!text.equals(null)){
			text.trim();
			try{
				i = Float.parseFloat(text);
			} catch (NumberFormatException e){
				System.out.println("Input not a float");
			}
		} else {
			System.out.println("Please enter a text");
		}
		return i;
	}
	
	public String trimAndLowercase(String text){ // removes blank spaces and sets characters to lowercase
		if(!text.equals(null)){
			text.trim();
			text.toLowerCase();
		} else {
			System.out.println("Please enter a text");
		}
		return text;
	}
	
	public String trimOnly(String text){ // removes blank spaces
		if(!text.equals(null)){
			text.trim();
		} else{
			System.out.println("Please enter a text");
		}
		return text;
	}
}