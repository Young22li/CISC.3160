package par;
import lex.*;

import java.util.ArrayList;

public class par
{

    public static ArrayList<Token> token = new ArrayList<>();

    public par(ArrayList token){
        this.token = token;
    }

    static final Tree[] eChildren = new Tree[]{};
    static int input_index;
    static String input_token;

    public static void next_token ()
    {
        input_index++;
        if (input_index >= token.size())
        {
            input_token = "$";
        }

        else
        {
            input_token = token.get(input_index).lexeme;
        }
    }

    public static void match (String expected_token)
    {
        if (input_token.equals(expected_token))
        {
            next_token ();
        }
        else if(input_token.equals(";")) {
            next_token();
            pro();
            match("$");
        }
        else
        {
            error (expected_token + "expected got " + input_token);
        }
    }

    public static ArrayList<Tree> res = new ArrayList<>();

    public ArrayList<Tree> getArr(){
        return res;
    }
    public static void pro(){
        if(input_index >= token.size()) return;
        //Program will have one or more tree
        else res.add(ass());
    }
    public static Tree ass() {

        //Catch the end
        if (input_index >= token.size()) {
            input_token = "$";
            return new Tree(new Token(7,"null"), eChildren);
        }

        Tree t1 = null;
        if (token.get(input_index).type != 0) error("No id");//Assignment must begin with ID
        else {
            t1 = new Tree(token.get(input_index), eChildren);
            next_token();
        }

        Tree t2 = null;
        Tree t3 = null;
        if (!input_token.equals("=")) error("ID follow =");// Then follow by =
        else {
            t2 = new Tree(token.get(input_index), eChildren);
            next_token();
            t3 = exp();
        }
        return new Tree(new Token(7,"ASS"), new Tree[]{t1, t2, t3});


    }

    static Tree exp ()
    {
        Tree t1 = term ();
        Tree t2 = exp_prime ();
        return new Tree(new Token(7,"E"), new Tree[]{t1, t2});
    }

    static Tree exp_prime ()
    {
        switch (input_token)
        {
            case "+":
                next_token();
                Tree t1 = term ();
                Tree t2 = exp_prime ();
                return new Tree(new Token(2,"+"), new Tree[]{t1, t2});
            case "-":
                next_token();
                Tree t3 = term ();
                Tree t4 = exp_prime ();
                return new Tree(new Token(2,"-"), new Tree[]{t3, t4});
            default:
                return new Tree(new Token(7,"null"), eChildren);
        }
    }

    static Tree term ()
    {
        Tree t1 = factor ();
        Tree t2 = term_prime ();
        return new Tree(new Token(7,"T"), new Tree[]{t1, t2});
    }

    static Tree term_prime ()
    {
        switch (input_token)
        {
            case "*":
                next_token ();
                Tree t1 = factor ();
                Tree t2 = term_prime ();
                return new Tree(new Token(2,"*"), new Tree[]{t1, t2});
            default:
                return new Tree(new Token(7,"null"), eChildren);
        }
    }


    static Tree factor ()
    {
        if(token.get(input_index).type == 1 || token.get(input_index).type == 0){
            Tree t = new Tree(token.get(input_index),eChildren);
            next_token();
            return t;
        }

        switch (input_token)
        {
            case "(":
                next_token ();
                Tree t1 = exp ();
                match (")");
                return new Tree(new Token(7,"F"), new Tree[]{new Tree(new Token(2,"("), eChildren),
                        t1,
                        new Tree(new Token(2,")"), eChildren)});

            case "+":
                next_token ();
                Tree t = factor();
                return t;
            case "-":
                next_token ();
                Tree t2 = new Tree(new Token(2,"-"), new Tree[]{factor()});
                return t2;
            default:
                error ("an operand or ( expected");
        }
        return new Tree(new Token(7,"null"),eChildren);

    }

    static void error (String msg)
    {
        throw new RuntimeException (msg);
    }

    public static void check ()
    {
        input_index = 0;
        pro ();
       match("$");
    }
}

