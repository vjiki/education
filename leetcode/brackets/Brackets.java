import java.util.Stack;

public class Brackets {

    static boolean isBracketMatches(char c1, char c2) {
        if (c1 == '(' && c2 == ')') {
            return true;
        } else if (c1 == '{' && c2 == '}') {
            return true;
        } else return c1 == '[' && c2 == ']';
    }

    public static boolean isValid(String s) {

        if (s.length() % 2 != 0) {
            return false;
        }

        char[] brackets= s.toCharArray();
        Stack<Character> bracketsStack = new Stack<>();

        for (char c : brackets) {
            if (c == '(' || c == '{' || c == '[') {
                bracketsStack.push(c);
            } else {
                if (bracketsStack.isEmpty()) {
                    return false;
                }
                char topCharacter = bracketsStack.pop();
                if (!isBracketMatches(topCharacter, c)) {
                    return false;
                }
            }

        }

        if (!bracketsStack.isEmpty()) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {

        System.out.println(isValid("(]"));
    }

}
