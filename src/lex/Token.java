package lex;

public class Token {
    public static int
            ID = 0,     // Identifier
            IL = 1,     // Integer literal
            OP = 2,     // operator
            PM = 3,     // punctuation mark
            LP = 4,     // left parenthesis
            RP = 5,     // right parenthesis

            SM = 6,
            TNode = 7;


    public int type;
    public String lexeme;

    public Token(int type, String lexeme){
        this.type = type;
        this.lexeme = lexeme;
    }
    public Token(String lexeme){

        this.lexeme = lexeme;
    }

    public String toString(){
        return type + lexeme;
    }
}

