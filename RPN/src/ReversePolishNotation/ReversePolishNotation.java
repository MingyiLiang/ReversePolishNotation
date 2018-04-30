package ReversePolishNotation;

import java.util.Scanner;
import java.math.BigInteger;

public class ReversePolishNotation {
	
	RedBlackTree rbt = new RedBlackTree();
	
	
	/**
	 * calculate the input string	
	 * @param input
	 * @return
	 */
	public  BigInteger calculateRPN(String input) {
		
		Stack stack = new Stack();
		String[]token = input.split(" ");
		
		for (int i = 0; i < token.length; i++) {
			if( token[i].matches("^[0-9]*$")){
				stack.push(BigInteger(token[i]));
			}else {
				switch(token[i]) {
				case"+":{
					BigInteger op2 =  (BigInteger) stack.pop();
	                BigInteger op1 =  (BigInteger) stack.pop();
	                BigInteger result = null;
	                result = op1.add(op2);
	                stack.push(result);
	                break;
				}case"-":{
					BigInteger op2 =  (BigInteger) stack.pop();
	                BigInteger op1 =  (BigInteger) stack.pop();
	                BigInteger result = null;
	                result = op1.min(op2);
	                stack.push(result);
	                break;
				}case"*":{
					BigInteger op2 =  (BigInteger) stack.pop();
	                BigInteger op1 =  (BigInteger) stack.pop();
	                BigInteger result = null;
	                result = op1.multiply(op2);
	                stack.push(result);
	                break;
				}case"/":{
					BigInteger op2 =  (BigInteger) stack.pop();
	                BigInteger op1 =  (BigInteger) stack.pop();
	                if (op2.longValue()==0) {
                        throw new IllegalArgumentException("attempted division by zero at token " + i);
                    }
	                BigInteger result = null;
	                result = op1.divide(op2);
	                stack.push(result);
	                break;
				}case"%":{
					BigInteger op2 =  (BigInteger) stack.pop();
	                BigInteger op1 =  (BigInteger) stack.pop();
	                BigInteger result = null;
	                result = op1.mod(op2);
	                stack.push(result);
	                break;

				}case"~":{
	                BigInteger op1 =  (BigInteger) stack.pop();
	                BigInteger result = null;
	                result = op1.negate();
	                stack.push(result);
	                break;

				}case"#":{
					
	                BigInteger op3 =  (BigInteger) stack.pop();
	                BigInteger op2 =  (BigInteger) stack.pop();
	                BigInteger op1 =  (BigInteger) stack.pop();
	                BigInteger result = null;
	                result = (op1.pow(op2.intValue())).mod(op3);
	                stack.push(result);
	                break;

				}case"=":{
					BigInteger op2 =  (BigInteger) stack.pop();
	                String op1 =  (String) stack.pop();
	                rbt.insert(op1, op2);
	                stack.push(op2);
	                break;
	                
				} default:{
					BigInteger op1 = rbt.get(token[i]);
					if (!rbt.contains(token[i])) {
						stack.push(token[i]);
					}else {
						stack.push(token[i]);
						String op2 = (String) stack.pop();
						stack.push(op1);
						break;
					}
				}
			}
		
		}
	
		}return (java.math.BigInteger) stack.pop();
	}		
	
	
	

	public Object BigInteger(String val) {
		// TODO Auto-generated method stub
		BigInteger output = new BigInteger(val);
		return output;
	}




	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReversePolishNotation r = new ReversePolishNotation();
		Scanner in = new Scanner(System.in);
        String input;
		while(true) {
			System.out.println("java ReversePolishNotation");
			input = in.nextLine();
			BigInteger pm = r.calculateRPN(input);
			System.out.println(pm);
			
		}
		
		
		
	}

}
