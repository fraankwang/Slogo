/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action;
/**
 * The Action class is an abstract super-class which extends into an extensive hierarchy of sub-class comamnds
 * Every user command is its own sub class which extends from Action.
 */
public abstract class Action {	
	public Action(){
	}
	
	

	/**
	 * Abstract method which every sub-class of Action must implement
	 * Every user command is its own Action, and each Action's rule is the method which changes the back-end of the slogo program
	 * For example, the rules() method for forward is the method which actually moves the turtle forward.
	 */
	public abstract double rule();
	
}
