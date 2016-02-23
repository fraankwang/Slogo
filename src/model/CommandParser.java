/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.util.ArrayList;
import java.util.Collection;

public class CommandParser {
	
	public CommandParser(){
		
	}
	
	public String[] parse (String input) {
		return input.split(" ");		
	}
	
	
	
}
