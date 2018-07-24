import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LazyLoading {
	public static final int INF = 1000000000;
	public static final int MAXN = 500010;
	FastScanner in;
	PrintWriter out;

	void solve(int test) {
		int n = in.nextInt();
		int[] arr = new int[n];
		int total = 0;
		for(int i=0; i<n; i++) {
			arr[i] = in.nextInt();
			total += arr[i];
		}
		Arrays.sort(arr);
		int result = 0;
		int front = 0;
		int rear = n-1;
		while (front <= rear) {
			result++;
			int bag = arr[rear];
			while (bag<50) {
				bag += arr[rear];
				front++;
			}
			if (rear>0 && arr[rear-1]*(rear-front)<50) {
				front = rear;
			}
			rear--;
		}
		out.printf("Case #%d: %d\n", test, result);
	}

	void runIO() {

		in = new FastScanner(System.in);
		out = new PrintWriter(System.out);
		int test = in.nextInt();
		for (int t=1; t<=test; t++) {
			solve(t);
		}
		out.close();
	}

	void run() {
		try {
			in = new FastScanner(new File("test.in"));
			out = new PrintWriter(new File("test.out"));
			int test = in.nextInt();
			for (int t=1; t<=test; t++) {
				solve(t);
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	class FastScanner {
		BufferedReader br;
		StringTokenizer st;

		public FastScanner(File f) {
			try {
				br = new BufferedReader(new FileReader(f));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		public FastScanner(InputStream f) {
			br = new BufferedReader(new InputStreamReader(f));
		}

		String next() {
			while (st == null || !st.hasMoreTokens()) {
				String s = null;
				try {
					s = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (s == null)
					return null;
				st = new StringTokenizer(s);
			}
			return st.nextToken();
		}

		boolean hasMoreTokens() {
			while (st == null || !st.hasMoreTokens()) {
				String s = null;
				try {
					s = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (s == null)
					return false;
				st = new StringTokenizer(s);
			}
			return true;
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}
		String nextLine(){
          String str = "";
	  		try {
	     		str = br.readLine();
	  		} catch (IOException e) {
	     		e.printStackTrace();
	  		}
	  		return str;
      	}
	}

	public static void main(String[] args) {
		new LazyLoading().runIO();
	}
}