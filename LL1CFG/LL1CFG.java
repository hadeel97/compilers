package csen1002.main.task7;

import java.util.Stack;

/**
 * Write your info here
 *
 * @name hadeel khalifa
 * @id 40-1187
 * @labNumber 13
 */
public class LL1CFG {

        /**
         * LL1 CFG constructor
         *
         * @param description is the string describing an LL(1) CFG, first, and follow as represented in the task description.
         */

        String cfg;
        String first;
        String follow;
        String [] CFG;

        public LL1CFG(String description) {
                cfg = description;
                CFG = cfg.split("#");
                first = CFG[1];
                //System.out.println(first);
                follow = CFG[2];
        }

        /**
         * Parses the input string using the parsing table
         *
         * @param s The string to parse using the parsing table
         * @return A string representation of a left most derivation
         */
        public String parse(String input) {
                String output = "";
                String rst = "";
                Stack <String> x = new Stack<String>();
                String f = "";
                boolean error = false;
                x.push("$");
                x.push("S");
                output += "S";
                while(input.length()!=0) {
                        String temp = x.pop();
                        String chars = input.substring(0,1);
                        if(chars.charAt(0) == temp.charAt(0)) {
                                input = input.substring(1);
                                rst += chars;
                        }
                        else {
                                f = checkTable(first, chars, temp);
                                if(f!="") {
                                        x.push(f);
                                        output += "," + rst +  getOutputAndRestack(x); 
                                }
                                else {
                                        boolean eps = checkEpsilon(temp,first,chars);
                                        //System.out.println(eps);
                                        if(eps) {
                                                output += "," + rst +  getOutputAndRestack(x);
                                        }
                                        else {
                                                output += "," + "ERROR";
                                                error = true;
                                                break;
                                        }
                                }
                        }
                }

                if(!error) {
                        while(x.size() >= 1) {
                                String top = x.pop();
                                String t= checkTable(first, "e", top);
                                //System.out.println("this is t" + t);

                                if(top !=  "$" && (t == "" || t.charAt(0) != 'e')) {
                                        output += "," + "ERROR";
                                        break;
                                }
                                else if(top != "$") {
                                        output += "," + rst +  getOutputAndRestack(x);
                                }
                        }
                }
                return output;
        }

        public boolean checkEpsilon(String temp, String first2, String chars) {
                boolean x = false;
                String [] var= first2.split(";");
                for(int i =0; i<var.length;i++) {
                        String [] secStrings = var[i].split(",");
                        if(temp.charAt(0) == secStrings[0].charAt(0)) {
                                //System.out.println("xxxxana henak");
                                for(int j=0;j<secStrings.length;j++) {
                                        if(secStrings[j].charAt(0)== 'e') {
                                                return true;
                                        }
                                }
                        }
                }

                return x;
        }

        public String getOutputAndRestack(Stack<String> stack) {
                String output = "";

                while(stack.size() > 1) {
                        output += stack.pop();
                }

                for(int i = output.length() - 1; i >= 0; i--) {
                        stack.push(output.substring(i, i+1));
                }

                return output;
        }


       public String checkTable(String first, String chars, String temp) {
                String t= "";
                String [] var =first.split(";");
                //System.out.println(var.length);
                for(int i =0; i< var.length;i++) {
                        String [] Second = var[i].split(",");
                        //System.out.println(Second.length);
                        if(temp.charAt(0) == Second[0].charAt(0)) {
                                for(int j = 1; j<Second.length;j++) {
                                	   for(int x=0; x <Second[j].length();x++) {
                                		   if (chars.charAt(0) == Second[j].charAt(x)) {
                                               return CFG[0].split(";")[i].split(",")[j];
                                       }
                                	   }
         
                                }
                        }
                }
                return t;
        }


        public static void main(String[] args) {


        	//LL1CFG g = new LL1CFG("S,zToS,n,e;T,zTo,No;N,n,e#S,z,n,e;T,z,no;N,n,e#S,$;T,o;N,o");
    		
           // System.out.println(g.parse("zzooo"));

                //System.out.println(checkEpsilon("S","S,i,e;T,c,a", "a"));

        }

}