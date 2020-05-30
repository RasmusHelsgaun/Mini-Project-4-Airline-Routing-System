package cphbusiness;

import java.util.ArrayList;
import java.util.List;

public interface IArrayList<T> extends Iterable<T> {
    void add(T obj);
    T get(int i);
    void set(int i, T obj);
    T remove(int i); 
    int size();
    boolean isEmpty();
    List getJavaImplementation();
}
