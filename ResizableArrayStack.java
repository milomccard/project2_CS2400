import java.util.Arrays;
import java.util.EmptyStackException;

public class ResizableArrayStack<T> implements StackInterface{

    private T[] stack;
    private int topIndex;
    private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    public ResizableArrayStack(){
        this(DEFAULT_CAPACITY);
    }

    public ResiableArrayStack(int initialCapacity){
        integrityOK = false;
        checkCapacity(initialCapacity);

        @SupressWarnings("unchecked");
        T[] tempStack = (T[])new Object[initialCapacity];
        stack = tempStack;
        topIndex = -1;
        integrityOK = true;
    }

    @Override
    public void push(T newEntry) {
        checkIntegrity();
        ensureCapacity();
        stack[topIndex + 1] = newEntry;
        topIndex++;
    }

    private void ensureCapacity(){
        if (topIndex >= stack.length - 1){
            int newLength = 2 * stack.length;
            checkCapacity(newLength);
            stack = Arrays.copyOf(stack, newLength);
        }
    }

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

    @Override
    public T peek() {
        checkIntegrity();
        if(isEmpty())
            throw new EmptyStackException();
        else
            return stack[topIndex];
    }

    @Override
    public boolean isEmpty() {
        return topIndex < 0;
    }

    @Override
    public void clear() {
        checkIntegrity();

        while (topIndex > -1){
            stack[topIndex] = null;
            topIndex--;
        }
    }

    private void checkIntegrity(){
        if(!integrityOK)
            throw new SecurityException("ArrayList Object is corrupt.");
    }

    private void checkCapacity(){
        if(topIndex > MAX_CAPACITY)
            throw new ArrayIndexOutOfBoundsException("Array capacity has exceeded maximum capacity.");
    }

    public T evaluatePostfix(T anEntryT){}

}