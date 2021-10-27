import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    private static final Stack<String> operations = new Stack<>();
    private static final Stack<Integer> numbers = new Stack<>();


    public static void main(String[] args) {
        System.out.println("Input data can be only integer!");
        System.out.println("For example : 2+2*(5-7)");
        System.out.println("Enter your equation to obtain solutions : ");

        try (Scanner sc = new Scanner(System.in)) {
            while (sc.hasNextLine()) {
                String equation = sc.nextLine().replaceAll("\\s", "");
                calculate(equation);
                System.out.println(equation + "=" + numbers.pop());
            }
        } catch (NoSuchElementException ex) {
            System.err.println("ERROR: Please enter your equation!");
        }
    }

    private static void calculate(String equation) {
        int index = 0;

        while (index < equation.length()) {
            if (equation.charAt(index) == '(') {

            } else if (equation.charAt(index) == '*' || equation.charAt(index) == '/' ||
                    equation.charAt(index) == '+' || equation.charAt(index) == '-') {
                operations.push(String.valueOf(equation.charAt(index)));
            } else if (equation.charAt(index) == ')') {
                doOperation();
            } else {
                StringBuilder value = new StringBuilder(String.valueOf(equation.charAt(index)));

                while ((index + 1) < equation.length() &&
                        Character.isDigit(equation.charAt(index + 1))) {
                    value.append(equation.charAt(++index));
                }
                numbers.push(Integer.parseInt(value.toString()));
            }
            index++;
        }

        while (!operations.empty() && !numbers.empty()) {
            doOperation();
        }
    }

    private static void doOperation() {
        String op = operations.pop();
        switch (op) {
            case "*":
                numbers.push(numbers.pop() * numbers.pop());
                break;
            case "/":
                try {
                    int first = numbers.pop();
                    int second = numbers.pop();
                    numbers.push(second / first);
                } catch (ArithmeticException ex) {
                    System.err.println("Your equation fail, you can't divide by zero!");
                }
                break;
            case "+":
                numbers.push(numbers.pop() + numbers.pop());
                break;
            case "-":
                numbers.push(numbers.pop() - numbers.pop());
                break;
            default:
                System.out.println("You enter wrong equation");
        }
    }
}
