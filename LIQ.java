import java.io.*;
import java.util.*;
import java.lang.*;

public class LIQ {
	int n;
	public static final int MAXN = Integer.MAX_VALUE;
	int[] arr, st, f;
	int find(int x) {
		int l=1, r=n, mid, res = 0;
		while (l<=r) {
			mid = (l+r)/2;
			if (st[mid] != 0 && arr[st[mid]] > x) {
				if (mid > res) res = mid;
				l = mid+1;
			} else r = mid-1;
		}
		return st[res];
	}
	void solve() {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new int[n+5];		st = new int[n+5];	f = new int[n+5];
		Arrays.fill(st, 0);

		for(int i=0; i<n; i++) arr[i] = sc.nextInt();
		st[1] = n;	f[n] = 1; arr[n] = MAXN; arr[0] = 0;

		for(int i=n-1; i>=0; i--) {
			int j = find(arr[i]);
			f[i] = f[j] + 1;
			if (st[f[i]] == 0 || arr[j] > arr[st[f[i]]]) st[f[i]] = i;
		}
		System.out.println(f[0] - 2);
	}
	public static void main(String[] args) {
		new LIQ().solve();
	}
}