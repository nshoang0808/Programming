import java.util.*;
import java.io.*;

public class MAXP3 {
	public int maxp3(List<Integer> a) {
		Collections.sort(a);
		int n = a.size() - 1;
		if (a.get(n) == 0) return 0;
		if (a.get(n)*a.get(n-1)*a.get(n-2) < a.get(n)*a.get(0)*a.get(1)) return a.get(n)*a.get(0)*a.get(1);
		else return a.get(n)*a.get(n-1)*a.get(n-2);
    }
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	}
}