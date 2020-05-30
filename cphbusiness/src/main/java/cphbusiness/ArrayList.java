
package cphbusiness;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayList<T> implements IArrayList<T> {
    private static final int INITIAL_ARRAY_SIZE = 10;
    T[] al;
    int last;

    public ArrayList() {
        this.al = (T[]) new Object[INITIAL_ARRAY_SIZE];
        this.last = -1;
    }

    @Override
    public void add(T obj) {
        if (last + 2 == al.length) {
            resize(al.length * 2);
        }

        al[++last] = obj;
    }

    @Override
    public T get(int i) {
        return al[i];
    }

    @Override
    public void set(int i, T obj) {
        if (i > al.length - 1) {
            resize(al.length * 2);
        }
        if (i > last) {
            last = i;
        }
        al[i] = obj;
    }

    @Override
    public T remove(int i) {
        T res = al[i];

        // Moving all after one to the left
        for (int j = i + 1; j <= last; j++) {
            al[j - 1] = al[j];
        }

        if (last-- == al.length / 4) {
            resize(al.length / 2);
        }

        return res;
    }

    @Override
    public int size() {
        return last + 1;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void resize(int newCapacity) {
        T[] save = al;
        al = (T[]) new Object[newCapacity];
        for (int i = 0; i <= last; i++) {
            al[i] = save[i];
        }
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            int index = 0;

            @Override
            public boolean hasNext() {
                return index < al.length;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T value = al[index];
                    index++;
                    return value;
                }
                throw new NoSuchElementException("No more positions available");
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Removals are not supported");
            }
        };
    }

    @Override
    public List getJavaImplementation() {
        List<T> res = new java.util.ArrayList<>();
        for (T t : al) {
            if(t != null){
                res.add(t);
            }
        }
        return res;
    }

}