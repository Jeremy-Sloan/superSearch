package superSearch;

import java.util.Arrays;

public class MatchTools {
	public static double norm(SuperString[] v) {
		double total = 0.0;
		for (SuperString element : v) {
			double x = element.getCount();
			total += x * x;
		}
		return Math.sqrt(total);
	}

	public static double match(SuperString[] V, SuperString[] W) {
		double v = norm(V);
		double w = norm(W);

		double dot = 0.0;

		Arrays.sort(V);
		Arrays.sort(W);

		int i = 0, j = 0;

		while (i < V.length) {
			while (V[i].compareTo(W[j]) > 0) {
				j++;
				if (j == W.length)
					return dot / (v * w);
			}
			if (V[i].compareTo(W[j]) == 0) {
				dot += V[i].getCount() * W[j].getCount();
				i++;
				j++;
			} else
				i++;
		}
		return dot / (v * w);
	}

}