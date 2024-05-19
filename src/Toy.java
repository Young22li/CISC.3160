import lex.*;
import par.*;
import eval.*;

import java.util.ArrayList;
import java.util.Scanner;

/*
   Toy Compiler
   Programmer: Ziyang Li
   Date: 5/19/2024
   Purpose: Compiler that enables assignment operations with only integers
   Input: User input
   Output: error, or if there is no error, the name of the variable and its value
 */

public class Toy {
    public static void main(String[] args) {
        if (args.length != 1){
            System.out.println("Usage: No input string");
            System.exit(0);
        }

        //Tokenizer
        ArrayList<Token> tokens = new ArrayList<>();
        Scanner sc = new Scanner(args[0]);
        Tokenizer t = new Tokenizer();
        t.tokenize(sc, tokens);// After that we got an array of token

        //Parsing
        par p = new par(tokens);
        ArrayList<Tree> ast = new ArrayList<Tree>();
        p.check(); // check syntax error
        ast = p.getArr(); // After that we got an AST

        //Evaluation based on the content of the abstract syntax tree
        ArrayList<ootd> res = new ArrayList<ootd>();
        eva e = new eva(ast);
        res = e.go();

        //Output result
        for(int i = 0; i < res.size(); i++){
            System.out.println(res.get(i).name + " = " + res.get(i).val);
        }

    }
}
