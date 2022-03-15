public interface StackInterface<T>{

    /**
     * Puts a new object on top of the stack
     * @param newEntry the object to be pushed
     */
    public void push(T newEntry);

    /**
     * Removes the top object of the stack and returns it
     * @return the object removed
     */
    public T pop();

    /**
     * Returns the object on top of the stack without removing it
     * @return the object on top of the stack
     */
    public T peek();

    /**
     * checks if the stack is empty
     * @return returns true if stack is empty
     */
    public boolean isEmpty();

    /**
     * Empties the stack of all elements
     */
    public void clear();
}
