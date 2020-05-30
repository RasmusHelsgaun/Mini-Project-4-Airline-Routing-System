package cphbusiness;

import java.util.concurrent.ArrayBlockingQueue;

public class Queue<T> implements IQueue<T> {
    private Node start;
    private Node end;
    private int N;

    public Queue(){
        start = null;
        end = null;
        N = 0;
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
        if(end != null){
            end.next = newNode;
        }

        end = newNode;

        if(start == null){
            start = newNode;
        }
        N++;
	}

	@Override
	public T poll() {
        T obj = (T) start.obj;
        start = start.next;
        if(start == null){
            end = null;
        }
        N--;
        return obj;
	}

	@Override
	public T peek() {
        if(start == null) return null;
		return (T) start.obj;
	}

	@Override
	public boolean isEmpty() {
		return N == 0;
	}

	@Override
	public int size() {
		return N;
	}

	@Override
	public java.util.Queue getJavaImplementation() {
        java.util.Queue<T> res = new ArrayBlockingQueue<>(N);
		while(!isEmpty()){
            res.add(poll());
        }
        return res;
	}

    
}