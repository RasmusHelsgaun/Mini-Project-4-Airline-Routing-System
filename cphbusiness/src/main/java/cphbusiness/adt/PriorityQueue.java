package cphbusiness.adt;

import cphbusiness.iface.IArrayList;
import cphbusiness.iface.IPriorityQueue;

public class PriorityQueue<T extends Comparable<T>> implements IPriorityQueue<T> {
    private IArrayList<T> pq;
    private int N = 0;

    public PriorityQueue() {
        this.pq = new ArrayList<>();
    }

    @Override
    public void add(T obj) {
        pq.set(++N, obj);
        swim(N);
    }

    @Override
    public T poll() {
        T min = pq.get(1); 
        swap(1, N--);
        pq.set(N + 1, null);
        sink(1);
        return min;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public int size() {
        return N;
    }

    private void swim(int i) {
        int iParent = parent(i);
        while (i > 1 && bigger(iParent, i)) {
            swap(iParent, i);
            i = iParent;
        }
    }

    private void sink(int i) {
        while (leftChild(i) <= N) {
            int pointer = leftChild(i);
            int iRight = rightChild(i);

            if (pointer < N && bigger(pointer, iRight)) {
                pointer++; // moves pointer to right sibling
            }

            if (!bigger(i, pointer))
                break;

            swap(i, pointer);

            i = pointer;
        }
    }

    private boolean bigger(int i, int j) {
        return pq.get(i).compareTo(pq.get(j)) > 0;
    }

    private void swap(int i, int j) {
        T temp = pq.get(i);
        pq.set(i, pq.get(j));
        pq.set(j, temp);
    }

    private int parent(int pos) {
        return pos / 2;
    }

    private int leftChild(int pos) {
        return (2 * pos);
    }

    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }

}