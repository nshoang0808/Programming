package cf750;

import java.util.Scanner;

public class ProblemA {

	public static int solve() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int total = 0;
		int result = 0;
		for (int i=1; i<=n; i++) {
			if (total + i*5 > 240-k) return i-1;
			total += i*5;
		}
		return n;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solve());
	}

}
