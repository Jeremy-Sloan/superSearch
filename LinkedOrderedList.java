package superSearch;

import java.util.Iterator;

public class LinkedOrderedList<T extends Comparable> implements OrderedList<T> {
	private Node<T> front;
	private int size;

	public LinkedOrderedList() {
		front = null;
		size = 0;
	}

	public void add(T element) {
		size++;
		Node<T> node = new Node(element);
		if (isEmpty()) {
			front = node;
			return;
		}

		if (element.compareTo(front.getElement()) <= 0) {
			node.setNext(front);
			front = node;
			return;
		}
		Node<T> current = front;
		while (current.getNext() != null && element.compareTo(current.getNext().getElement()) > 0)
			current = current.getNext();
		node.setNext(current.getNext());
		current.setNext(node);
	}

	public T remove() {
		T element = front.getElement();
		size--;
		front = front.getNext();
		return element;
	}

	public boolean isEmpty() {
		return front == null;
	}

	public T look() {
		return front.getElement();
	}

	public int size() {
		return size;
	}

	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Node<T> current = front;

			public boolean hasNext() {
				return current != null;
			}

			public T next() {
				T element = current.getElement();
				current = current.getNext();
				return element;
			}
		};
	}
}