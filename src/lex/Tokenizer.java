package lex;
import java.util.*;
public class Tokenizer {
    public static void tokenize(Scanner sc, ArrayList<Token> tokens) {
        String line = "";
        while (sc.hasNext()) {
            line = sc.nextLine();
            line = line.replace(" ", "");//replace space
            String[] segs = line.split("(?<=;)");//Each statement is separated by a semicolon.
            for (String seg : segs) {
                tokenize(seg, tokens);
            }
        }
    }

    public static void tokenize(String str, ArrayList<Token> tokens) {
        int i = 0;
        int e;                                              // ending index of a token
        int n = str.length();
        int id = (int) str.charAt(0);
        if (str.charAt(n - 1) != ';') error("invalid, you miss a ;");
        else if (!((id >= 65 && id <= 90) || (id >= 97 && id <= 122))) error("invalid Identifier");

        while (i < n && str.charAt(i) != '=') {
            if (i >= n) return;

            switch (str.charAt(i)) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':

                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'W':
                case 'R':
                case 'T':
                case 'Y':
                case 'U':
                case 'S':
                case 'X':
                case 'V':
                case 'Z':

                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'w':
                case 'r':
                case 't':
                case 'y':
                case 'u':
                case 's':
                case 'x':
                case 'v':
                case 'z':
                case '_':
                    e = extractIdentifier(str, i);
                    tokens.add(new Token(Token.ID, str.substring(i, e)));
                    i = e;
                    break;

            }
        }

        while (i < n) {
            if (i >= n) return;

            switch (str.charAt(i)) {
                case '0':
                    tokens.add(new Token(Token.IL, "0"));
                    if (i < n - 1 && Character.isDigit(str.charAt(i + 1))) {
                        error("invalid literal");
                    }
                    break;
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    e = extractLiteral(str, i);
                    tokens.add(new Token(Token.IL, str.substring(i, e)));
                    i = e;
                    break;

                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'W':
                case 'R':
                case 'T':
                case 'Y':
                case 'U':
                case 'S':
                case 'X':
                case 'V':
                case 'Z':

                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'w':
                case 'r':
                case 't':
                case 'y':
                case 'u':
                case 's':
                case 'x':
                case 'v':
                case 'z':
                case '_':
                    e = extractIdentifier(str, i);
                    tokens.add(new Token(Token.ID, str.substring(i, e)));
                    i = e;
                    break;

                case '=':
                case '+':
                case '-':
                case '*':
                    tokens.add(new Token(Token.OP, Character.toString(str.charAt(i))));
                    i++;
                    break;
                case '(':
                    tokens.add(new Token(Token.LP, "("));
                    i++;
                    break;
                case ')':
                    tokens.add(new Token(Token.RP, ")"));
                    i++;
                    break;
                case ';':
                    tokens.add(new Token(Token.SM, ";"));
                    i++;
                    break;
                default:
                    error("unrecognized symbol:" + str);
            }
        }
    }

    static int extractLiteral(String str, int i) {
        if (i >= str.length()) return 0;
        //change char to ascii code
        //Interval determination by ASCII code
        while ((int) str.charAt(i) >= 48 && (int) str.charAt(i) <= 57) i++;
        return i;
    }

    static int extractIdentifier(String str, int i) {
        if (i >= str.length()) return 0;
        int id = (int) str.charAt(i);

        while ((id >= 65 && id <= 90) || (id >= 97 && id <= 122)
                || (id == 95) || (id >= 48 && id <= 57)) {
            i++;
            id = (int) str.charAt(i);
        }
        return i;
    }

    static void error(String str) {
        throw new RuntimeException(str);
    }
}
