/**
 * A driver class to show our convertToPostFix and evaluatePostFix methods
 * They each use stacks to solve the problems of converting from infix to postfix
 * and to then evaluate the converted postfix expression
 * The convert method makes use of a linked stack
 * The evaluate method makes use of a resizable array stack
 */
public class Calculator {
    public static void main(String args[]){
        
        /**
         * This block tests the implementations convertToPostfix(String) and evaluatePostfix(String)
         */
        String inFixString = "A*B/(C-A)+D*E";
        System.out.println("\n************* DEMO *************\n\n");
        System.out.println("To demonstrate our linked and resizable array stacks we will convert an infix expression to postfix and evaluate it.");
        System.out.println("\n\tInfix expression: " + inFixString);
        System.out.println("\n(Task 2) We will now pass our infix string to our infix to postfix converter.");
        String postFix = convertToPostfix(inFixString);
        System.out.println("\n\tPostfix Expression: " + postFix);
        System.out.println("\n(Task 5) Now we will evaluate the postfix expression assuming all the values are equal to what task 4 stated in the project description.");
        System.out.println("\n\tThe value of the postfix expression is: " + evaluatePostfix(postFix));
        System.out.println("\n\n");
        
    }

    /**
     * Converts an infix expression to postfix expression
     * @param anEntry an infix expression represented as a String 
     * @return a postfix expression represented as a String
     */
    static String convertToPostfix(String anEntry){
        String output = "";//the Postfix expression that will be returned

        LinkedStack<Character> stack = new LinkedStack<>();//a stack to hold operands

        for(int i = 0; i < anEntry.length(); i++){//scan the input letter by letter
            char ch = anEntry.charAt(i);//the current letter (char) being scanned

            if(Character.isLetterOrDigit(ch)) //if the current letter is a variable (not an operand), simply add it to the output
                output += ch;
            
            else if (ch == '(') //if the current letter is a '(' push it to the stack, this will start building out the sequence of operands in the Postfix
                stack.push(ch);

            else if (ch == ')'){ //if the current letter is a ')', keep removing operands from the stack until the parantheses is closed
                while(!stack.isEmpty() && stack.peek() != '(')//keep popping until we reach a '('
                    output += stack.pop();
                
                stack.pop();//once we reach the '(', pop it, thereby closing the parentheses
                } 
            else{
                //if the current letter is not a letter, number or parentheses, it must be an operand.
                while(!stack.isEmpty() && Precedence(ch) <= Precedence(stack.peek()))//keep removing operands from the top of the stack until we reach an operand that -
                                                                                     //is of HIGHER precedence than the current letter.  
                    output += stack.pop();//each operand removed from the top of the stack is added to the output (they are of LOWER or EQUAL precedence than the current)

                stack.push(ch);//add the current operand to the top of the stack.  Because of the previous while loop, this operand must be of higher precedence
                                //than the one before it.
            }
        }

        //finally, after the entire string has been scanned, we move everything from the stack into our output
        //now all of the operands are at the end of the Postfix expression
        //with the exception that lower order operands have already been added to our output.
        while(!stack.isEmpty()){
            if(stack.peek() == '(') //if we find an extra '(', this means the expression is invalid since that parentheses cannot be closed
                return "Error, input was not a valid infix expression";

            output += stack.pop();
        }
        return output;

    }

    /**
     * Helper method for determining precedence of operands.
     * @param x the operand
     * @return the precidence of the operand
     */
    static int Precedence(char x){
        switch(x){
            case '+': case '-':
                return 1;
            case '*': case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }
    
    /**
     * Evaluate a postfix expression with pre-defined variable values 'A' - 'E'
     * @param postfix a postfix expression
     * @return the numerical value of the evaluated expression
     */
    static double evaluatePostfix(String postfix){
        // This stack will hold the intermediary answers
        ResizableArrayStack<Double> stack = new ResizableArrayStack<>();

        for(int i = 0; i < postfix.length(); i++){
            // to parse the string that we pass in
            char ch = postfix.charAt(i);

            // 
            if(Character.isLetter(ch))
                switch(ch){
                    case 'A':
                        stack.push((double)2);
                        break;
                    case 'B':
                        stack.push((double)3);
                        break;
                    case 'C':
                        stack.push((double)4);
                        break;
                    case 'D':
                        stack.push((double)5);
                        break;
                    case 'E':
                        stack.push((double)6);
                        break;
                    default:
                        break;
                }
            else{
                double x = stack.pop();
                double y = stack.pop();
                switch(ch){
                    case '+':
                        stack.push(y+x);
                        break;
                    case '-':
                        stack.push(y-x);
                        break;
                    case '*':
                        stack.push(y*x);
                        break;
                    case '/':
                        stack.push(y/x);
                        break;
                    case '^':
                        stack.push(Math.pow(y, x));
                        break;
                    }
                }
        }
    return stack.pop();
    }
}
