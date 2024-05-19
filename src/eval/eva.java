package eval;
import java.util.ArrayList;
import java.util.regex.Pattern;
import par.*;

public class eva {

    public static ArrayList<Tree> ast = new ArrayList<>();

    public eva(ArrayList ast){
        this.ast = ast;
    }

    static ArrayList<ootd> res = new ArrayList<>();//Storing a variable and its value

    public static String exp = "";

    //traversing tree
    public static void Ttree(Tree a){

        //Turning an expression into an expression consisting entirely of numbers and operation symbols

        if (a.val.type == 0 || a.val.type == 1 || a.val.type == 2){
            if(a.val.type == 0){
                //If there is an ID in the expression, then replace that ID with its value
                if(match(a.val.lexeme)) exp += findVal(a.val.lexeme);
                else {
                    throw new RuntimeException( a.val.lexeme + " uninitialized.");
                }
            }
            else{
                exp += a.val.lexeme;//Only ID, number and operator
            }
        }

        if (a.children.length != 0) {
            if (a.children.length == 1) Ttree(a.children[0]);//One node
            else if (a.children.length == 2) {
                //two node
                Ttree(a.children[0]);
                Ttree(a.children[1]);
            }
            else { //Three node
                Ttree(a.children[0]);
                Ttree(a.children[1]);
                Ttree(a.children[2]);
            }
        }

    }

    public static boolean match(String s) {
        //A variable whose name is in res is also assigned a value return true
        for(int i = 0; i< res.size(); i++){
            if (s.equals(res.get(i).name)){
                if(res.get(i).isNull()) return true;
            }
        }
        return false;
    }

    public static String findVal(String s){
        for(int i = 0; i< res.size(); i++){
            if (s.equals(res.get(i).name)){
                return Integer.toString(res.get(i).val);
            }
        }
        return "null";
    }

    public static ArrayList<ootd> go(){
        ootd o = new ootd();
        //Add the first assignment statement to the result
        //In the tree first node must an ID
        o.setName(ast.get(0).children[0].val.lexeme);
        res.add(o);
        //Third node must an expression
        Ttree(ast.get(0).children[2]);
        ArithmeticParser a = new ArithmeticParser();
        res.get(0).val = a.cal(exp);//parsing num exp

        //Dispose of the remaining trees
        for(int i = 1; i < ast.size(); i++){
            exp = "";
            ootd o1 = new ootd();
            o1.setName(ast.get(i).children[0].val.lexeme);
            res.add(o1);
            Ttree(ast.get(i).children[2]);
            res.get(i).val = a.cal(exp);
        }

        return res;
    }

}





