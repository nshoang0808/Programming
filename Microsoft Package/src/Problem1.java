import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Problem1 {
	public static final int INF = 1000000000;
	public static final int MAXN = 500010;
	FastScanner in;
	PrintWriter out;

	void solve() {
		String s = in.next();
		String[] arr = s.split(",");
		int sum = 0;
		for(int i=0; i<arr.length; i++) {
			sum+=Integer.parseInt(arr[i]);
		}
		out.println(Math.round(sum*1.0/arr.length));
	}

	void runIO() {

		in = new FastScanner(System.in);
		out = new PrintWriter(System.out);

		solve();

		out.close();
	}

	void run() {
		try {
			in = new FastScanner(new File("Data-consolidation_InputForSubmission_1.txt"));
			out = new PrintWriter(new File("out.txt"));

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
		new Problem1().run();
	}
}
