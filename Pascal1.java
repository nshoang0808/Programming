import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Pascal1 {
	public static final int MAXN = 5005;
	public static List<List<Integer>> write_Pascal(int a) {
		List<List<Integer>> b = new ArrayList<List<Integer>>();
		for (int i=0; i<a; i++) {
			b.add(new ArrayList<Integer>());
			b.get(i).add(1);
			b.get(0).add(0);
		}
		for (int i = 1; i<a; i++) {
			b.add(new ArrayList<Integer>());
			for(int j=1; j<a; j++) {
				b.get(i).add(b.get(i-1).get(j) + b.get(i-1).get(j-1));
			}
		}
		return b;
	}
	public static void main(String[] args) {
		write_Pascal(5);
	}
}