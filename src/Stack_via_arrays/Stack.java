package Stack_via_arrays;

import java.util.Scanner;
import java.util.Dictionary;
import java.util.Hashtable;

public class Stack {

	int N = 0;
	char[] data; 
	
	private Stack(int i) {
		data = new char[i];
	}
	
	private void push(char c) {
		data[N] = c;
		N++;
	}
	
	private char pop() {
		N = N - 1;
		return data[N];
	}
	
	private char peek() {
		if(isEmpty()) {return '0';}
		int n = N;
		return data[--n];
	}
	
	private Boolean isEmpty() {
		if(N == 0) {return true;}
		return false;
	}
	
	public static void main(String[] args) {
		//[()]{}{[()()]()}}}}}}}}
		
		char[] openBrackets = {'(', '{', '['};
		char[] closedBrackets = {')', '}', ']'};
		
		Dictionary<Character, Character> brackets = new Hashtable<Character, Character>();
		
		brackets.put(')', '(');
		brackets.put('}', '{');
		brackets.put(']', '[');
		brackets.put('(', ')');
		brackets.put('{', '}');
		brackets.put('[', ']');
		
		Scanner myObj = new Scanner(System.in);
		System.out.print("Enter string of parenthese:" );
		
		String input = myObj.nextLine();
		System.out.print("Input was '" + input + "'");
		
		myObj.close();
		
		Stack balanceChecker = new Stack(input.length());
		
		Boolean isBracket = false;
		Boolean isOpen = false;
		Boolean isClosed = false;
		Boolean peekWhenEmpty = false;
		
		for(int i = 0; i < input.length(); i++) { //loops over string entered
			isBracket = false;
			isOpen = false;
			isClosed = false;
			
			for(int j = 0; j < openBrackets.length; j++) {	
				if(openBrackets[j] == input.charAt(i)) {
					isBracket = true;
					isOpen = true;
					break;
				}
				if(closedBrackets[j] == input.charAt(i)) {
					isBracket = true;
					isClosed = true;
					break;
				}
			}
			
			if(!isBracket) {
				throw new RuntimeException("Invalid characters within user-inputted string. (Must only have the following characters: (, {, [, ], }, )");
			}
			
			//pushes open brackets
			if(isBracket && isOpen) {
				balanceChecker.push(input.charAt(i));
			}
			
			else if(isBracket && isClosed) {
				//pretty unruly, could make this nicer
				//checks if the closed bracket and top of the stack correspond to each other.
				
				if(balanceChecker.peek() == (char)brackets.get(input.charAt(i))) { 
					balanceChecker.pop();
				}else {
					if(balanceChecker.peek() == '0') { peekWhenEmpty = true; }
				}
				
			}
		}
		
		if(balanceChecker.isEmpty() && !peekWhenEmpty) {
			System.out.print("\n\nThe brackets are balanced");
		}else{ 
			System.out.print("\n\nThe brackets are not balanced.");
		}
	}
}
