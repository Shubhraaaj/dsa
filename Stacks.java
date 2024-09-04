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
