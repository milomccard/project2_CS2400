public interface StackInterface<T>{

/**Adds a new entry to the top of this stack*/
    public void push(T newEntry);

    public T pop();

    public T peek();

    public boolean isEmpty();

    public void clear();
}