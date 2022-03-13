public class Caculator {

    public static void main(String args[]){
        LinkedStack<String> tester = new LinkedStack<String>();

        String input = "a*(1+2^2)";
        System.out.println("\nInfix Expression: " + input);
        
        System.out.print("Postfix Expression: ");
        System.out.println(convertToPostfix(input));
        
        
    }

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

    //a quick helper utility to define the precedence of terms.
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
    
}
