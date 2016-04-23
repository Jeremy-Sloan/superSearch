package superSearch;
import java.util.Comparator;

public class SuperStringComparator implements Comparator<SuperString> {
	public int compare(SuperString s, SuperString t) {
		int value = t.getCount() - s.getCount();
		if (value != 0)
			return value;
		return s.compareTo(t);
	}
}