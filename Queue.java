package fractalIsland;

public interface Queue<T> {
	public void add(T element);

	public T remove();

	public T element();

	public boolean isEmpty();

	public int size();
}
