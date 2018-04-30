package ReversePolishNotation;

import java.util.NoSuchElementException;

/**
 * stack class
 * @author mingyiliang
 *
 */
public class Stack {
	private Object stackarray[] = new Object[6];
	private int index;
	RedBlackTree rbt = new RedBlackTree();
	
    public Stack() {    	     
    	     index = -1;
    }
    
    /**
     * check the stack is empty
     * @return
     * O(1)
     */
    public boolean isEmpty() {
    	   return index == -1;
    }
    /**
     * push a data into the stack
     * @param element
     * O(1)
     */
    public void push(Object element) {
    	   if (index == stackarray.length-1) {
    		   resize(2*stackarray.length);	   
    	   }
    	   stackarray[++index] = element;
    }
    /**
     * twice the size as the old array
     * @param size
     */
    private void resize(int size) {
    	    Object[] temp =  new Object[size];
    	    for (int i = 0; i < index+1; i++) {
    	    	    temp[i] = stackarray[i];
    	    }
    	    stackarray = temp;
    }
    
    
    /**
     * pop a data into a stack
     * @return
     * O(1);
     */
    public Object pop() {
//			if(isEmpty()) {
//    	    	     //throw new NoSuchElementException("Stack underflow");
//    	    	     return null;
//    	    }else if(!isNumeric((String) stackarray[index])){
//    	    	     return rbt.get((String) stackarray[index--]);
//    	    }else {
//    	        	return stackarray[index--];
//    	    }
    	        if(isEmpty()) {
    	         	throw new NoSuchElementException("Stack underflow");
    	        }else {
    	        	    return stackarray[index--];
    	        }
    }
    /**
     * check if the string is number
     * @param s
     * @return
     */
    public final static boolean isNumeric(String s) {  
        if (s != null && !"".equals(s.trim()))  
            return s.matches("^[0-9]*$");  
        else  
            return false;  
    } 
    
    public static void main(String[] args) {
    	   Stack newStack = new Stack();
    	   for (int i = 0; i < 1000; i++) {
    		   newStack.push(i);
    	   }
    	   
    	   for(int i = 0; i < 1000; i++) {
    		   System.out.println(newStack.pop());
    	   }
    }
}
