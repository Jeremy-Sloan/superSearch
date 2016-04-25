package superSearch;
import java.util.Iterator;

public interface OrderedList<T extends Comparable> extends Iterable<T> {
	public void add(T element);

	public T remove();

	public boolean isEmpty();

	public T look();

	public int size();

	public Iterator<T> iterator();
}