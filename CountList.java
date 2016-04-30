package superSearch;
import java.util.Iterator;

public interface CountList<T extends Comparable & Countable> extends Iterable<T> {
	public void add(T element);

	public T remove();

	public boolean isEmpty();

	public T look();

	public int size();

	public Iterator<T> iterator();
}