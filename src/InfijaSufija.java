import java.util.Scanner;
import java.util.Stack;

public class InfijaSufija {

    public static void main(String[] args) {

        System.out.println("Ejemplo:  (A+B) * C - (D-E) ");

        Scanner input = new Scanner(System.in);
        System.out.print("Introduce una expresión infija :");

        String text = input.nextLine();
        System.out.println(infiAPostfi(text));


    }

    public static String infiAPostfi(String infixExpression) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> operatorStack = new Stack<>();

        for (char token : infixExpression.toCharArray()) {
            if (Character.isLetterOrDigit(token)) {
                postfix.append(token);
            } else if (token == '(') {
                operatorStack.push(token);
            } else if (token == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    postfix.append(operatorStack.pop());
                }
                if (!operatorStack.isEmpty() && operatorStack.peek() == '(') {
                    operatorStack.pop(); // Eliminar '(' del stack
                }
            } else {
                while (!operatorStack.isEmpty() && precedence(token) <= precedence(operatorStack.peek())) {
                    postfix.append(operatorStack.pop());
                }
                operatorStack.push(token);
            }
        }
        while (!operatorStack.isEmpty()) {
            postfix.append(operatorStack.pop());
        }
        return postfix.toString();
    }

    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1; // Operador no válido
        }
    }
}//JDSD

