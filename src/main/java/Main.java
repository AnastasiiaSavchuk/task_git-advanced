import java.math.BigInteger;
import java.util.Stack;

public class Main {
    private static final Stack<String> operations = new Stack<>();
    private static final Stack<BigInteger> numbers = new Stack<>();

    public static void main(String[] args) {
//        System.out.println("Enter your equation, for example 2+2*(5-7) :");
//        try (Scanner sc = new Scanner(System.in)) {
//            while (sc.hasNextLine()){
//                String equation = sc.nextLine().replaceAll("\\s", "");
//                calculate(equation);
//                System.out.println(equation + "=" + numbers.pop());
//                System.out.println("Enter your equation :");
//            }
//        } catch (NoSuchElementException ex) {
//            System.err.println("ERROR: You didn't enter your equation!");
//        }
        String equation = "";
        for (String str : args) {
            equation += str;
        }
        calculate(equation);
        System.out.println(equation + "=" + numbers.pop());
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
                numbers.push(BigInteger.valueOf(Long.parseLong(value.toString())));
            }
            index++;
        }

        while (!operations.empty() && !numbers.empty()) {
            doOperation();
        }
    }

    private static void doOperation() {
        String op = operations.pop();
        BigInteger lastElement = numbers.pop();
        BigInteger previousElement = numbers.pop();
        switch (op) {
            case "*":
                System.out.println(previousElement + "*" + lastElement + "=" + numbers.push(previousElement.multiply(lastElement)));
                break;
            case "/":
                try {
                    System.out.println(previousElement + "/" + lastElement + "=" +numbers.push(previousElement.divide(lastElement)));
                } catch (ArithmeticException ex) {
                    System.err.println("ERROR: Your equation fail, you can't divide by zero!");
                }
                break;
            case "+":
                System.out.println(previousElement + "+" + lastElement + "=" +numbers.push(previousElement.add(lastElement)));
                break;
            case "-":
                System.out.println(previousElement + "-" + lastElement + "=" +numbers.push(previousElement.subtract(lastElement)));
                break;
            default:
                System.err.println("ERROR: You enter wrong options");
        }
    }
}
