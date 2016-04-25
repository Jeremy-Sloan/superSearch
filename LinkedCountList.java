package superSearch;
import java.util.Iterator;

public class LinkedCountList<T extends Comparable & Countable> implements CountList<T> {
	private Node<T> front;
	private int size;

	public LinkedCountList() {
		front = null;
		size = 0;
	}

	public void add(T element) {
		Node<T> node = new Node(element);
		if (isEmpty()) {
			front = node;
			size = 1;
			return;
		}
		int value = element.compareTo(front.getElement());
		if (value == 0) {
			front.getElement().increment();
			return;
		}
		if (value < 0) {
			node.setNext(front);
			front = node;
			size++;
			return;
		}
		Node<T> current = front;
		while (current.getNext() != null && element.compareTo(current.getNext().getElement()) > 0) {
			current = current.getNext();
		}
		if (current.getNext() != null && element.compareTo(current.getNext().getElement()) == 0) {
			current.getNext().getElement().increment();
			return;
		}
		size++;
		node.setNext(current.getNext());
		current.setNext(node);
		return;
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