package superSearch;
public interface Countable<T> {
	public void increment();

	public void decrement();

	public int getCount();

	public void setCount(int count);

}