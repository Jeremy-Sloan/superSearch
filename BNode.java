package superSearch;
public class BNode<T> {
	private T element;
	private BNode<T> left;
	private BNode<T> right;

	public BNode(T element) {
		this.element = element;
		left = null;
		right = null;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public T getElement() {
		return element;
	}

	public void setLeft(BNode<T> node) {
		left = node;
	}

	public BNode<T> getLeft() {
		return left;
	}

	public void setRight(BNode<T> node) {
		right = node;
	}

	public BNode<T> getRight() {
		return right;
	}

	public boolean hasLeft() {
		return left != null;
	}

	public boolean hasRight() {
		return right != null;
	}

	public boolean isLeaf() {
		return !(hasLeft() || hasRight());
	}
}
