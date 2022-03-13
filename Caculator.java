public class Caculator {

    public static void main(String args[]){
        LinkedStack<String> tester = new LinkedStack<String>();

        String input = "a*(1+2^2)";
        System.out.println("\nInfix Expression: " + input);
        
        System.out.print("Postfix Expression: ");
        System.out.println(convertToPostfix(input));
        
        
    }

    static String convertToPostfix(String anEntry){
        String output = "";
        LinkedStack<Character> stack = new LinkedStack<>();
        for(int i = 0; i < anEntry.length(); i++){
            char ch = anEntry.charAt(i);

            if(Character.isLetterOrDigit(ch))
                output += ch;
            
            else if (ch == '(')
                stack.push(ch);

            else if (ch == ')'){
                while(!stack.isEmpty() && stack.peek() != '(')
                    output += stack.pop();
                
                stack.pop();
                } 
            else{
                while(!stack.isEmpty() && Precedence(ch) <= Precedence(stack.peek()))
                    output += stack.pop();

                stack.push(ch);
            }
        }

        while(!stack.isEmpty()){
            if(stack.peek() == '(')
                return "Error, input was not a valid infix expression";

            output += stack.pop();
        }
        return output;

    }

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
