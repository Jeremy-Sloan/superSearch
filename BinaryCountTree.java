package superSearch;

import java.util.Iterator;

public class BinaryCountTree<T extends Comparable<T> & Countable> extends BinaryTree<T> implements Iterable<T> {
	public void add(T element) {
		if (isEmpty()) {
			setRoot(new BNode<T>(element));
			return;
		}

		BNode<T> current = getRoot();
		while (true) {
			int value = element.compareTo(current.getElement());
			if (value == 0) {
				current.getElement().increment();
				return;
			}
			if (value > 0) {
				if (current.hasRight())
					current = current.getRight();
				else {
					current.setRight(new BNode<T>(element));
					return;
				}
			} else {
				if (current.hasLeft())
					current = current.getLeft();
				else {
					current.setLeft(new BNode<T>(element));
					return;
				}
			}
		}
	}

	public Iterator<T> iterator() {
		return inOrderTraversal().iterator();
	}
}