package eval;

import java.util.Scanner;

public class ArithmeticParser {

    private static String input;
    private static int index;

    public static int cal(String s) {
        Scanner scanner = new Scanner(s);
        input = scanner.nextLine();
        index = 0;
        int result = parseExpression();
        return result;
    }

    // parsing expression
    private static int parseExpression() {
        int term = parseTerm();
        while (index < input.length()) {
            char operator = input.charAt(index);
            if (operator == '+' || operator == '-') {
                index++; // skip
                int nextTerm = parseTerm();
                if (operator == '+') {
                    term += nextTerm;
                } else { // operator == '-'
                    term -= nextTerm;
                }
            } else {
                break;
            }
        }
        return term;
    }

    // *
    private static int parseTerm() {
        int factor = parseFactor();
        while (index < input.length()) {
            char operator = input.charAt(index);
            if (operator == '*') {
                index++; // skip
                int nextFactor = parseFactor();
                factor *= nextFactor;
            } else {
                break; // if not *, stop
            }
        }
        return factor;
    }

    // () and -num
    private static int parseFactor() {

        char firstChar = input.charAt(index);
        if (Character.isDigit(firstChar)) {
            // parsing num
            StringBuilder numBuilder = new StringBuilder();
            while (index < input.length() && Character.isDigit(input.charAt(index))) {
                numBuilder.append(input.charAt(index));
                index++;
            }
            int number = Integer.parseInt(numBuilder.toString());
            return number;
        } else if (firstChar == '-') {
            // parsing -num
            index++;
            int factor = parseFactor();
            return -factor; // return - num
        } else if (firstChar == '(') {
            // ()
            index++; // skip (
            int result = parseExpression();
            if (index >= input.length() || input.charAt(index) != ')') {
                throw new IllegalArgumentException(" ) expected");
            }
            index++; // skip )
            return result;
        } else {
            throw new IllegalArgumentException("invail char: " + firstChar);
        }
    }
}