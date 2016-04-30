package superSearch;
public class Node<T> {
	private T element;
	private Node<T> next;

	public Node(T element) {
		this.element = element;
		next = null;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public T getElement() {
		return element;
	}

	public void setNext(Node<T> node) {
		next = node;
	}

	public Node<T> getNext() {
		return next;
	}

	public boolean hasNext() {
		return next != null;
	}
}