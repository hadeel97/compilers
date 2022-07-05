/**
 * Write your info here
 *
 * @name hadeel khalifa
 * @id 40-1187
 * @labNumber 13
 */

grammar Task9;

@members {
	/**
	 * Compares two integer numbers
	 *
	 * @param x the first number to compare
	 * @param y the second number to compare
	 * @return 1 if x is equal to y, and 0 otherwise
	 */
	public static int equals(int x, int y) {
	    return x == y ? 1 : 0;
	}
}

s returns [int check]: a c b {$check = ((equals($a.n,$b.n) == (equals($b.n, $c.n))) && (equals($a.n,$b.n) == 1))? 1: 0;}
 // Write the definition of parser rule "s" here
 ;

a returns [int n]:  {$n=0;} | 'a' a1=a  {$n = $a1.n +1;};
b returns [int n] : {$n=0;} | 'b' b1=b  {$n = $b1.n+1;} ;
c returns [int n]:  {$n=0;} |  'c' c1=c {$n = $c1.n+1;} ;

// Write additional lexer and parser rules here