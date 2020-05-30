package cphbusiness;

public interface IPriorityQueue<T extends Comparable<T>> {
    void add(T obj);

    T poll();

    boolean isEmpty();

    int size();
}