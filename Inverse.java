import java.util.*;
import java.io.*;
import java.lang.*;

public class Inverse {
	public int inverse(int x,int y,int a,int b) {
		int tmp = a - (x/y)*b;
		if (x%y == 1) return tmp;
		return inverse(y, x%y, b, tmp);

	}
	public void solve() {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt(); int y = sc.nextInt();
		System.out.println(inverse(x,y,0,1));
	}
	public static void main(String[] args) {
		new Inverse().solve();
	}
}