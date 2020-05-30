package cphbusiness;

import java.util.Stack;

public interface IStack<T> {
    void add(T obj);
    T pop();
    T peek();
    int size();
    void removeAllElements();
    boolean isEmpty();
    Stack getJavaImplementation();
    
}