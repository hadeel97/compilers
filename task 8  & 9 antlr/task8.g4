grammar task8;

//Parser Rule start to parse the input and check whether it is accepted or rejected
start: (Q2 | Q3 | Q4) + EOF ;


//Lexer Rule Q2 which has the Regular Expression of the accepted state Q2


Q2: (ONE* ZERO (ONE+ ZERO)* ZERO) (ZERO (ONE+ ZERO) ZERO ONE* ZERO (ONE+ ZERO)* ZERO)* |
    (ONE* ZERO (ONE+ ZERO)* ZERO ONE+ ZERO (ONE+ ZERO)* ZERO ONE* ZERO (ONE+ZERO)* ZERO);
// Lexer Rule Q3 which has the Regular Expression of the accepted state Q3
Q3: (ONE* ZERO (ONE+ ZERO)* ZERO ZERO) (ONE+ ZERO)* (ZERO (ONE* ZERO (ONE+ ZERO)* ZERO (ZERO| (ONE+ ZERO)*)))*|
    (ONE* ZERO (ONE+ ZERO)* ZERO ONE+ ZERO (ONE+ ZERO)*) |
    (ONE* ZERO (ONE+ ZERO)* ZERO ONE ((ONE+ ZERO)+| ZERO) ZERO) (ONE* ZERO (ONE+ ZERO)* ZERO (ONE+ ZERO)*) |
        (ONE* ZERO (ONE+ ZERO)* ZERO ONE ((ONE+ ZERO)+| ZERO) ZERO) (ONE* ZERO (ONE+ ZERO)* ZERO ZERO (ONE+ ZERO)*);

//Lexer Rule Q4 which has the Regular Expression of the accepted state Q4
Q4: (ONE* ZERO (ONE+ ZERO)* ZERO ZERO ZERO (ONE*) ZERO (ONE+ ZERO)* ZERO (ONE+))|
    (ONE* ZERO (ONE+ ZERO)* ZERO ONE+) | ((ONE* ZERO (ONE+ ZERO)* ZERO (ONE+))(ZERO ONE+)*)|
    (ONE* ZERO (ONE+ ZERO)* ZERO ZERO (ONE+) (ZERO ONE+)*) |
    (ONE* ZERO (ONE+ ZERO)* ZERO ONE ((ONE+ ZERO)+| ZERO) ZERO)(ONE* ZERO (ONE+ ZERO)* ZERO ZERO (ONE+ ZERO)*) ONE+;


//Fragments representing the zeros and ones
fragment ZERO: '0' ;
fragment ONE: '1' ;

////Lexer Rule Q2 which has the Regular Expression of the accepted state Q2
  //Q2: (ONE* ZERO (ONE+ ZERO)* ZERO) (ZERO (ONE+ ZERO) ZERO ONE* ZERO (ONE+ ZERO)* ZERO)* |
  //    (ONE* ZERO (ONE+ ZERO)* ZERO ONE+ ZERO (ONE+ ZERO)* ZERO ONE* ZERO (ONE+ZERO)* ZERO);
  ////Lexer Rule Q3 which has the Regular Expression of the accepted state Q3
  //Q3: (ONE* ZERO (ONE+ ZERO)* ZERO ZERO) (ONE+ ZERO)* (ZERO (ONE* ZERO (ONE+ ZERO)* ZERO ZERO))*|
  //    (ONE* ZERO (ONE+ ZERO)* ZERO ONE+ ZERO (ONE+ ZERO)*) |
  //    (ONE* ZERO (ONE+ ZERO)* ZERO ONE ((ONE+ ZERO)+| ZERO) ZERO)(ONE* ZERO (ONE+ ZERO)* ZERO ZERO (ONE+ ZERO)*);
  //
  ////Lexer Rule Q4 which has the Regular Expression of the accepted state Q4
  //Q4: (ONE* ZERO (ONE+ ZERO)* ZERO ZERO ZERO (ONE*) ZERO (ONE+ ZERO)* ZERO (ONE+))|
  //    (ONE* ZERO (ONE+ ZERO)* ZERO ONE+) | ((ONE* ZERO (ONE+ ZERO)* ZERO (ONE+))(ZERO ONE+)*)|
  //   (ONE* ZERO (ONE+ ZERO)* ZERO ZERO (ONE+) (ZERO ONE+)*);