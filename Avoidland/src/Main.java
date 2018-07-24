import java.io.*;
import java.math.BigInteger;
import java.lang.*;
import java.util.*;

public class Main {
	public static final int INF = 1000000000;
	public static final int MAXN = 500010;
	FastScanner in;
	PrintWriter out;
	
	
	void solve() {
		int N; int[] row, col;
		N = in.nextInt();
		row = new int[N+1]; col = new int[N+1];
		Arrays.fill(row, 0);
		Arrays.fill(col, 0);
		for(int i=1; i<=N; i++) {
			int x = in.nextInt(); int y = in.nextInt();
			row[x] += 1; col[y] += 1;
		}
		int curr = 1;
		int res = 0;
		for(int i=1; i<=N; i++) {
			for(int j=0; j<row[i]; j++) {
				res += Math.abs(i-curr);
				curr++;
			}
		}
		curr = 1;
		for(int i=1; i<=N; i++) {
			for(int j=0; j<col[i]; j++) {
				res += Math.abs(i-curr);
				curr++;
			}
		}
		out.println(res);
	}

	void runIO() {

		in = new FastScanner(System.in);
		out = new PrintWriter(System.out);
		int test = in.nextInt();
		for(test = test; test>0; test--) {
			solve();
		}

		out.close();
	}

	void run() {
		try {
			in = new FastScanner(new File("test.in"));
			out = new PrintWriter(new File("test.out"));

			solve();

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
		new Main().runIO();
	}
}