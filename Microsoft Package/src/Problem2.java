import java.io.*;
import java.math.BigInteger;
import java.lang.*;
import java.util.*;

public class Problem2 {
	public static final int INF = 1000000000;
	public static final int MAXN = 500010;
	FastScanner in;
	PrintWriter out;

	void solve() {
		int n=0, m=0;
		List<List<Integer>> a = new ArrayList<List<Integer>>();
		do {
			String s = in.nextLine();
			if (s == null) break;
			String[] arr = s.split(" ");
			if (m==0) m = arr.length;
			a.add(new ArrayList<Integer>());
			for(int i=0; i<m; i++) a.get(n).add(Integer.parseInt(arr[i]));
			n++;
		} while (true);
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				out.printf("%d ",a.get(j).get(i));
			}
			if(i<m-1) out.println();
		}
	}

	void runIO() {

		in = new FastScanner(System.in);
		out = new PrintWriter(System.out);

		solve();

		out.close();
	}

	void run() {
		try {
			in = new FastScanner(new File("Matrix-Transpose_InputForSubmission_1.txt"));
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
		new Problem2().run();
	}
}