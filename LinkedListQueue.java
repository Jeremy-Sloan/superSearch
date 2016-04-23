package superSearch;

public class LinkedListQueue<T> implements Queue<T> {
	private Node<T> front;
	private Node<T> rear;
	private int size;

	public LinkedListQueue() {
		front = null;
		rear = null;
		size = 0;
	}

	public void add(T element) {
		Node<T> node = new Node<T>(element);
		if (isEmpty())
			front = node;
		else
			rear.setNext(node);
		rear = node;
		size++;
		return;
	}

	public T remove() {
		T element = front.getElement();
		front = front.getNext();
		size--;
		return element;
	}

	public T element() {
		return front.getElement();
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

}