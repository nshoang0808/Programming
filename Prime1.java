import java.util.*;
import java.lang.*;
import java.io.*;

public class Prime1 {
	static boolean isPrime(int x) {
		if (x == 1) return false;
		for(int i=2; i<=Math.sqrt(x); i++) {
			if (x%i == 0) return false;
		}
		return true;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); int x,y;
		for (int i=0; i<n; i++) {
			x = sc.nextInt();
			y = sc.nextInt();
			for(int j=x; j<=y; j++) {
				if (isPrime(j)) System.out.println(j);
			}
			if (i < n-1) System.out.println();
		}
	}
}