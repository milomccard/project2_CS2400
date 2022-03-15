import java.util.EmptyStackException;

public class LinkedStack<T> implements StackInterface<T>{

    private Node topNode;

    /**
     * Node class for implementing a linked list
     */
    private class Node {
        private T data; //stores one  object
        private Node next; 
        //points to the "next" node.  initially points to null.  each time 1 element is added to the bag, a new Node is created to store the new element.
        //this new node becomes the new "head" or first node in the chain.  The @next of this new node points to the previous "head", extending the chain

        private Node(T data){//constructor for a node
            this(data, null);//constructor within a constructor (see next constructor)
        }

        private Node(T data, Node next){//constructor for a node that sets data and next simultaneously (this is odd but the class notes write it this way)
            this.data = data;
            this.next = next;
        }

        private T getData(){//returns the item stored in this node
            return data;
        }

        private void setData(T newData){//replaces the item stored in this node
            data = newData;
        }

        private Node getNextNode(){//returns the address of the next node
            return next;
        }

        private void setNextNode(Node nextNode){//sets the address of the next node
            next = nextNode;
        }
    }

    /**
     * Constructor of an empty stack
     */
    public LinkedStack(){
        topNode = null;
    }

    /**
     * Puts a new object on top of the stack
     * @param newEntry the object to be pushed
     */
    @Override
    public void push(T newEntry) {
        Node newNode = new Node(newEntry, topNode);
        topNode = newNode;
    }
    

    /**
     * Removes the top object of the stack and returns it
     * @return the object removed
     */
    @Override
    public T pop() {
        T top = peek();
        topNode = topNode.getNextNode();

        return top;
    }

    /**
     * Returns the object on top of the stack without removing it
     * @return the object on top of the stack
     */
    @Override
    public T peek() {
        if (isEmpty())
            throw new EmptyStackException();
        else    
            return topNode.getData();
    }

    /**
     * checks if the stack is empty
     * @return returns true if stack is empty
     */
    @Override
    public boolean isEmpty() {
        if (topNode == null)
            return true;
        else
            return false;
    }

    /**
     * Empties the stack of all elements
     */
    @Override
    public void clear() {
        topNode = null;
    }
}
