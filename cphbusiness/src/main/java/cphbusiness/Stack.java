package cphbusiness;

public class Stack<T> implements IStack<T> {
    private Node top;
    private int N;

    public Stack() {
        this.top = null;
        this.N = 0;
    }

    private class Node<T> {
        T obj;
        Node next;

        Node(T obj) {
            this.obj = obj;
        }
    }

    @Override
    public void add(T obj) {
        Node newNode = new Node(obj);
        newNode.next = top;
        top = newNode;
        N++;
    }

    @Override
    public T pop() {
        T res = (T) top.obj;
        top = top.next;
        N--;
        return res;
    }

    @Override
    public T peek() {
        return (T) top.obj;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public java.util.Stack getJavaImplementation() {
        java.util.Stack s1 = new java.util.Stack();
        while (!isEmpty()) {
            s1.push(pop());
        }

        java.util.Stack res = new java.util.Stack();
        while (!s1.isEmpty()) {
            res.push(s1.pop());
        }
        return res;
    }

    @Override
    public void removeAllElements() {
        top = null;
        N = 0;
    }

}