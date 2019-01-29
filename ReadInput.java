/* This class reads the input file, and stores them in ArrayList. It also parses them into their approriate data types
 * 
 * Name: Ahmed Mansour
 * Date: 12/9/18
 */
package JavaFXProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadInput <T> {

	
	public ArrayList<Object> readFile(int flag) throws FileNotFoundException {
		Scanner in = new Scanner(new File("C:\\input.txt")); //reads in from input file
    	ArrayList<Object> outputArray = new ArrayList<Object>(); //ArrayList is type Object so it can hold any datatype
    	int i = 0;
    	
    	if(flag == 1) {
    		while(i < 60){
    			if(in.hasNextInt()) { //if next token is an int, put in into the ArrayList as an Int
    				outputArray.add(in.nextInt());
    			}
    			else if(in.hasNextDouble()) {
    				outputArray.add(in.nextDouble());
    			}
    			else if (in.hasNext()) {
    				outputArray.add(in.next());
    			}
    			i++;
    		}
    	} 
    	else {
    		while(in.hasNextLine()) {
    			
    			String line = in.nextLine();
    			line = in.nextLine();
        	    String[] tokens = (String[]) line.split(" ");
        	    tokens = (String[]) line.split(" ");
        	    outputArray.add(tokens[0]);
        	    outputArray.add(tokens[1]);
        	    outputArray.add(tokens[2]);
        	    outputArray.add(tokens[3]);
    		}
    	}
		return outputArray;
	}
	


}
