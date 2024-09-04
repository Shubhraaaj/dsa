import java.util.*;

public class Stacks {
    public static void main(String[] args) {
        // System.out.println(isValid("{[()]}")); // Output: true
        // System.out.println(isValid("{[(])}")); // Output: false

        // MinStack minStack = new MinStack();
        // minStack.push(-2);
        // minStack.push(0);
        // minStack.push(-3);
        // System.out.println(minStack.getMin()); // Output: -3
        // minStack.pop();
        // System.out.println(minStack.top()); // Output: 0
        // System.out.println(minStack.getMin()); // Output: -2

        System.out.println(removeConscutive("aabbccc", 2)); // Output: "c"
        System.out.println(removeConscutive("abbccaa", 2)); // Output: "a"
        System.out.println(removeConscutive("aaacbbbcc", 3)); // Output:

    }

    // Check for balance paranthesis
    public static boolean isValid(String str) {
        HashMap<Character, Character> charMap = new HashMap<Character, Character>();
        charMap.put(')', '(');
        charMap.put('}', '{');
        charMap.put(']', '[');
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (charMap.containsKey(c)) {
                char topElement = stack.isEmpty() ? '#' : stack.pop();
                if (charMap.get(c) != topElement) {
                    return false;
                }
            } else if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public static int reversePolish(String[] arr) {
        Stack<Integer> stack = new Stack<>();
        int num1, num2;
        for (String s : arr) {
            switch (s) {
                case "+":
                    num2 = stack.pop();
                    num1 = stack.pop();
                    stack.push(num1 + num2);
                    break;
                case "-":
                    num2 = stack.pop();
                    num1 = stack.pop();
                    stack.push(num1 - num2);
                    break;
                case "*":
                    num2 = stack.pop();
                    num1 = stack.pop();
                    stack.push(num1 * num2);
                    break;
                case "/":
                    num2 = stack.pop();
                    num1 = stack.pop();
                    stack.push(num1 / num2);
                    break;
                default:
                    stack.push(Integer.parseInt(s));
                    break;
            }
        }
        return stack.pop();
    }

    public static String removeConscutive(String s, int k) {
        Stack<Pair> stack = new Stack<Pair>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek().character == c) {
                Pair top = stack.pop();
                top.count++;
                if (top.count < k) {
                    stack.push(top);
                }
            } else {
                stack.push(new Pair(c, 1));
            }
        }
        // Build the result string from the stack
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            Pair top = stack.pop();
            for (int i = 0; i < top.count; i++) {
                result.append(top.character);
            }
        }
        return result.reverse().toString();
    }

}

class MinStack {
    private Stack<Integer> mainStack;
    private Stack<Integer> minStack;

    public MinStack() {
        mainStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        mainStack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    public void pop() {
        if (mainStack.pop().equals(minStack.peek()))
            minStack.pop();
    }

    public int top() {
        return mainStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

}

class Pair {
    char character;
    int count;

    Pair(char character, int count) {
        this.character = character;
        this.count = count;
    }

}
