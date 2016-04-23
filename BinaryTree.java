package superSearch;

import java.util.List;
import java.util.ArrayList;

public class BinaryTree<T> {
	private BNode<T> root;

	public BinaryTree() {
		root = null;
	}

	public BinaryTree(BNode<T> node) {
		root = node;
	}

	public BinaryTree(T element) {
		root = new BNode<T>(element);
	}

	public BNode<T> getRoot() {
		return root;
	}

	public void setRoot(BNode<T> node) {
		root = node;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public boolean hasLeft() {
		return root.hasLeft();
	}

	public boolean hasRight() {
		return root.hasRight();
	}

	public BinaryTree<T> leftSubTree() {
		return new BinaryTree<T>(root.getLeft());
	}

	public BinaryTree<T> rightSubTree() {
		return new BinaryTree<T>(root.getRight());
	}

	public int size() {
		if (root == null)
			return 0;
		int size = 1;
		if (hasLeft())
			size += leftSubTree().size();
		if (hasRight())
			size += rightSubTree().size();
		return size;
	}

	public int height() {
		if (root.isLeaf())
			return 0;

		int left = 0;
		int right = 0;
		if (hasLeft())
			left = leftSubTree().height();
		if (hasRight())
			right = rightSubTree().height();

		return 1 + (left > right ? left : right);
	}

	public int leafCount() {
		if (root.isLeaf())
			return 1;
		int count = 0;
		if (hasLeft())
			count += leftSubTree().leafCount();
		if (hasRight())
			count += rightSubTree().leafCount();
		return count;
	}

	public List<T> preOrderTraversal() {
		ArrayList<T> list = new ArrayList<T>();
		preOrderTraversal(list);
		return list;
	}

	public List<T> preOrderTraversal(List<T> list) {
		list.add(root.getElement());
		if (hasLeft())
			leftSubTree().preOrderTraversal(list);
		if (hasRight())
			rightSubTree().preOrderTraversal(list);
		return list;
	}

	public List<T> inOrderTraversal() {
		ArrayList<T> list = new ArrayList<T>();
		inOrderTraversal(list);
		return list;
	}

	public List<T> inOrderTraversal(List<T> list) {

		if (hasLeft())
			leftSubTree().inOrderTraversal(list);
		list.add(root.getElement());
		if (hasRight())
			rightSubTree().inOrderTraversal(list);
		return list;
	}

	public List<T> postOrderTraversal() {
		ArrayList<T> list = new ArrayList<T>();
		postOrderTraversal(list);
		return list;
	}

	public List<T> postOrderTraversal(List<T> list) {

		if (hasLeft())
			leftSubTree().postOrderTraversal(list);

		if (hasRight())
			rightSubTree().postOrderTraversal(list);
		list.add(root.getElement());
		return list;
	}

	public List<T> levelOrderTraversal() {
		List<T> list = new ArrayList<T>();
		Queue<BNode<T>> queue = new LinkedListQueue<BNode<T>>();
		queue.add(root);
		while (!queue.isEmpty()) {
			BNode<T> node = queue.remove();
			list.add(node.getElement());
			if (node.hasLeft())
				queue.add(node.getLeft());
			if (node.hasRight())
				queue.add(node.getRight());
		}
		return list;
	}

}