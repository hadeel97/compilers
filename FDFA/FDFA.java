package csen1002.main.task4;

import java.util.Stack;

/**
 * Write your info here
 * 
 * @name hadeel khalifa
 * @id 40-1187
 * @labNumber 13
 */


public class FDFA {

	String FDFA;
	static String result;
	String[] GoalStates;
	String[] Transitions;
	String[] TT;
	String output;
	int l;
	int r;
	//int NumberOfStates;
	int CurrState;

	/**
	 * FDFA constructor
	 * 
	 * @param description is the string describing a FDFA
	 */

	public FDFA(String description) {
		// TODO Write Your Code Here
		FDFA = description;
		Transitions = FDFA.split("#");
		GoalStates = Transitions[1].split(",");
		TT= Transitions[0].split(";");
		//NumberOfStates = TT.length;
		//System.out.println(NumberOfStates);

	}

	public static boolean check(String[] arr, int currState2) {
		for (int i= 0; i<arr.length;i++) {

			if (Integer.parseInt(arr[i]) == (currState2)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Returns a string of actions.
	 * 
	 * @param input is the string to simulate by the FDFA.
	 * @return string of actions.
	 */

	public String GetStackA(String input){
		int l =0;
		int r=0;

		Stack<String> M = new Stack<String>();
		int CurrInp;
		String [] temp;
		for(int i =0;i<input.length();i++) {
			CurrInp = Integer.parseInt(input.substring(i, i+1));
			//System.out.println("this is input  "+CurrInp);
			temp = TT[l].split(",");
			if (CurrInp == 0) {
				l= Integer.parseInt(temp[1]);
			}
			if (CurrInp == 1) {
				l= Integer.parseInt(temp[2]);
			}

			boolean x = check(GoalStates, l);
			if(x) {
				M.push(l+"A");
				r=l;
			}
			else {
				M.push(l+"R");
			}
		}

		output += returnOutput(M, input,r, l);
		return output;
	}


	public String returnOutput(Stack<String> S, String input, int r, int l) {
		output = "";
		output = "";
		String [] temp;

		int c = S.size();
		if (!S.empty()) {
			String LastState = S.pop();
			S.push(LastState);
		}
		for(int p = 0; p < c; p++) {
			//System.out.println(S.peek().substring(1) + "  peek  " + p);
			if (S.peek().substring(1).equals("R")) {
				S.pop();
			}
			else {
				if (S.peek().substring(1).equals("A")) {
					break;
				}
			}
		}

		temp = TT[r].split(",");
		//System.out.println("this is temps  " +tempS);
		if(S.empty()) {
			temp = TT[l].split(",");
			output += input + "," + temp[3] + ";";
			//System.out.println( output);
		}
		else {

			output += input.substring(0, S.size())+ "," + temp[3] + ";";
			input =  input.substring(S.size());
			if (input!="") {
				GetStackA(input);
			}
		}

		return output;
	}


	public String run(String input) {
		output = "";
		output = GetStackA(input);
		return output;
	}



	public static void main(String[] args) {
		//FDFA fdfa1 = new FDFA("0,3,1,N;1,8,2,O;2,8,8,A;3,4,6,P;4,8,5,Q;5,8,8,B;6,7,8,R;7,8,8,C;8,8,8,S#2,5,7");

		//System.out.println(fdfa1.run("11001010"));
	}
}