package par;
import java.util.*;
import lex.*;

public class Tree {
    public Token val;
    public Tree[] children;
    public Tree(Token val, Tree[] children){
        this.val = val;
        this.children = children;
    }

    public String toString(){
        ArrayList<Tree> arr = new ArrayList<>(Arrays.asList(children));
        return  val.lexeme + '(' + arr.toString() + ')';
    }
}
