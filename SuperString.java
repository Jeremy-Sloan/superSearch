package superSearch;
public class SuperString implements Countable, Comparable<SuperString> {
	private String[] tokens;
	private int count;

	public SuperString(String[] input) {
		tokens = new String[input.length];
		for (int i = 0; i < tokens.length; i++)
			tokens[i] = input[i];
		count = 1;
	}

	public String getToken(int i) {
		return tokens[i];
	}

	public void increment() {
		count++;
	}

	public void decrement() {
		count--;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int value) {
		count = value;
	}

	public int compareTo(SuperString s) {
		int value;
		for (int i = 0; i < tokens.length; i++) {
			value = tokens[i].compareTo(s.getToken(i));
			if (value != 0)
				return value;
		}
		return 0;
	}

	public String toString() {
		String output = "" + count + "   ";
		for (int i = 0; i < tokens.length; i++)
			output += tokens[i] + " ";
		return output;
	}

}