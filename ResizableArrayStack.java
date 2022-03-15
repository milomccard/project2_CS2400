import java.util.Arrays;
import java.util.EmptyStackException;

public class ResizableArrayStack<T> implements StackInterface<T>{

    private T[] stack;
    private int topIndex;
    private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    /**
     * Constructor of an stack with default capacity
     */
    public ResizableArrayStack(){
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructor of a stack with a predefined capacity
     * @param initialCapacity the capacity
     */
    public ResizableArrayStack(int initialCapacity){
        integrityOK = false;
        checkCapacity();

        @SuppressWarnings("unchecked")
        T[] tempStack = (T[])new Object[initialCapacity];
        stack = tempStack;
        topIndex = -1;
        integrityOK = true;
    }

    /**
     * Puts a new object on top of the stack
     * @param newEntry the object to be pushed
     */
    @Override
    public void push(T newEntry) {
        checkIntegrity();
        ensureCapacity();
        stack[topIndex + 1] = newEntry;
        topIndex++;
    }

    /**
     * If stack has reached capacity, this function doubles the capacity
     */
    private void ensureCapacity(){
        if (topIndex >= stack.length - 1){
            int newLength = 2 * stack.length;
            checkCapacity();
            stack = Arrays.copyOf(stack, newLength);
        }
    }

    /**
     * Removes the top object of the stack and returns it
     * @return the object removed
     */
    @Override
    public T pop() {
        checkIntegrity();
        if (isEmpty())
            throw new EmptyStackException();
        else{
            T top = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;
            return top;
        }
    }

    /**
     * Returns the object on top of the stack without removing it
     * @return the object on top of the stack
     */
    @Override
    public T peek() {
        checkIntegrity();
        if(isEmpty())
            throw new EmptyStackException();
        else
            return stack[topIndex];
    }

    /**
     * checks if the stack is empty
     * @return returns true if stack is empty
     */
    @Override
    public boolean isEmpty() {
        return topIndex < 0;
    }

    /**
     * Empties the stack of all elements
     */
    @Override
    public void clear() {
        checkIntegrity();

        while (topIndex > -1){
            stack[topIndex] = null;
            topIndex--;
        }
    }

    /**
     * Ensures array is not out of bounds
     */
    private void checkIntegrity(){
        if(!integrityOK)
            throw new SecurityException("ArrayList Object is corrupt.");
    }

    /**
     * Ensurs array has not exceeded maximum capacity
     */
    private void checkCapacity(){
        if(topIndex > MAX_CAPACITY)
            throw new ArrayIndexOutOfBoundsException("Array capacity has exceeded maximum capacity.");
    }
}
