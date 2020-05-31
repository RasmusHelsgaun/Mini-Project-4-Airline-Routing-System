package cphbusiness.iface;

import java.util.Queue;

public interface IQueue<T> {
    void add(T obj);
    T poll();
    T peek();
    boolean isEmpty();
    int size();
    Queue getJavaImplementation();
}