package csen1002.main.task5;

import java.util.Stack;

/**
 * Write your info here
 * 
 * @name hadeel khalifa
 * @id 40-1187
 * @labNumber 13
 */
public class CFG {

	String LRE;
	String [] CFG;
	static String output = ""; //the output after elimination
	String [] Vars;
	Stack<String> stackVars;
	int countRec;
	int CountVars;
	boolean F;
	int counter;

	/**
	 * CFG constructor
	 * 
	 * @param description is the string describing a CFG
	 */

	public CFG(String description) {
		// TODO Write Your Code Here

		this.LRE = description; //the input CFG
		this.CFG = LRE.split(";"); //the CFG after splitting using ;
		this.F = false; //recursion found boolean
		this.counter = 0; 
		stackVars = new Stack<String>(); 
		this.CountVars = CFG.length; //the number of Variables is equal to the CFG length
		this.countRec = 0; //the number of recursions found
		this.Vars = new String[CountVars]; // the array where the terminals will be stored

	}

	/**
	 * Returns a string of eliminated left recursion.
	 * 
	 * @param input is the string to simulate by the CFG.
	 * @return string of eliminated left recursion.
	 */

	public String lre() {

		output = "";

		for(int i = 0; i < CFG.length; i++) { //for loop on the CFG to eliminate left recursion

			String [] CurrentS = CFG[i].split(","); //current state is the split of every item in CFG 
			String variable = CurrentS[0]; //Variable is the first var in String 
			stackVars.push(variable);//then we push var to the stack of variables

			boolean recF = false;
			int countR = 0;

			String dash = ""; 
			String nodash = "";


			nodash += variable+",";
			
			Stack<String> stack = new Stack<String>();
			
			for(int j = CurrentS.length-1; j > 0; j--) {
				
				stack.add(CurrentS[j]);
				
			}
			//System.out.println("stack"+stack);
			output += variable;

			while(!stack.isEmpty()) {
				
				String PopS = stack.pop();
				//System.out.println("this is poped  "+ PopS);
				String firstChar = PopS.substring(0, 1);
				boolean FSub = checkSub(stackVars, firstChar, variable);
				if(FSub) {
					
					String res = SubState(firstChar);
					// System.out.println("this is substate for first char "+ res);
					if(! res.equals("")) {
						
						String [] str = res.split(",");
						for(int c = 0; c < str.length; c++) { //pushing the output from the replacing sub string output
							if(PopS.length() > 1) {   
								// to the stack from right to left 
								stack.push(str[str.length-1-c]+PopS.substring(1));
							}
							else {
								stack.push(str[str.length-1-c]);
							}
						}
					}
				}
				boolean checkNext = true;

				if(! FSub) {    //adding the poped item from the stack to the output 
					            //if check for substitution returns false
					
					output += "," + PopS;
					
				}

				if(FSub & ! checkNext) { //adding the next in the stack to the stack
					                     // if check for substitution returns true and checknext terurn false
					output += ","+stack.pop();
					
				}

				if(! stack.isEmpty()){ //if stack is not empty we check the next input if it should be substituted
					//stack.peek() to look at the next item to be poped
					
					checkNext = checkSub(stackVars, stack.peek().substring(0, 1), variable); 
				}

			}

			output += ";";

			String [] outputS = output.split(";"); 
			String [] CurrStates = new String[] {};

			CurrStates = outputS[counter].split(",");

			output = "";

			for(int k = 0; k < outputS.length-1; k++) {

				output += outputS[k]+";";

			}

			for(int j = 1; j < CurrStates.length; j++) {

				String Term = CurrStates[j].substring(0,1);

				if(variable.equals(Term)) {
					recF = true;
					countR++;
					if(countR == 1) {
						dash += variable+"'"+",";
					}
					dash += CurrStates[j].substring(1, CurrStates[j].length())+variable+"'"+",";		
				}
				else {
					if(recF == true) {
						nodash += CurrStates[j]+variable+"'"+",";
					}

					else {
						nodash += CurrStates[j]+",";
					}	
				}
			}

			if(recF == true) {
				boolean entered = false;
				String [] dachCheck = nodash.split(",");
				for(int k = 1; k < dachCheck.length; k++) {
					if(! dachCheck[k].substring(dachCheck[k].length()-1,dachCheck[k].length()).equals("'")) {
						entered = true;
						dachCheck[k] += variable+"'";
					}
				}

				if(entered) {
					nodash = "";
					for(int k = 0; k < dachCheck.length; k++) {
						nodash += dachCheck[k] + ",";
					}
				}

				if(nodash.equals(variable+",")) {
					output += dash + ";";
					stackVars.pop();
					counter -= 1;
				}

				else {
					output += nodash.substring(0, nodash.length()-1) + ";" + dash +  "e" + ";";
				}
				F = true;
				counter += 2;
			}

			else {
				output += nodash.substring(0, nodash.length()-1) + ";";
				F = false;
				counter += 1;
			}	
		}
		output = output.substring(0, output.length()-1);
		//System.out.println(output);
		return output;
	}

	//this function is called to check if there is a substring for the variable 
	public static boolean checkSub(Stack<String> stackVars, String C, String var) { 
		boolean x = false; // Initial value is set to false 
		@SuppressWarnings("unchecked")
		Stack<String> temp = (Stack<String>) stackVars.clone(); //temp variable set with all stach variables that where before first char
		while(! temp.isEmpty()) { //as long as temp is not empty we check if 
			//t which is the first char poped from temp is equals to C which is the character we sent to the function
			String t = temp.pop(); //and we also check if t is not equal to var then we set the boolean x to true if the conditions apply
			if(! t.equals(var) & t.equals(C)) {
				x = true;
				break;
			}
		}
		return x;
	}



	public static String SubState(String var) { //returning the string that will be concat instead of 
		//the var to eliminate recursion
		String res = ""; // the res string
		String [] s = output.split(";"); // split output 
		for(int i = 0; i < s.length; i++) { //loop on the string
			if(s[i].substring(0,1).equals(var)) { //check if the first var of the substring is equal to var
				if(s[i].length()>2) {  // if s[i] is greater than 2 we save res with the rest of the string 
					res = s[i].substring(2);
				}
				break;
			}
		}
		return res; // then we return the result
	}

	public static void main(String[] args) {
		//CFG cfg = new CFG("S,SaT,T;T,TzG,G;G,i");
		//CFG cfg1 = new CFG("S,ScT,Sm,T,n;T,mSn,imLn,i;L,SdL,S");
		//CFG cfg2 = new CFG("S,SpT,Sq,T,b;T,qSb,iqKb,i;K,SdK,S");
		//CFG cfg3 = new CFG("S,LW,Wd;L,SW,LS,m;W,SL,m");
		//CFG cfg4 = new CFG("S,ScT,Sq,T,b;T,qSb,iqHb,i;H,SdH,S");

		//System.out.println(cfg.lre());
		//System.out.println(cfg1.lre());
		//System.out.println(cfg2.lre());
		//System.out.println(cfg3.lre());
		//System.out.println(cfg4.lre());


	}
}
