import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ProblemA {
	public static final int INF = 1000000000;
	public static final int MAXN = 500000000;
	FastScanner in;
	PrintWriter out;

	void solve() {
		String s = in.next();
		s = ' '+s;
		int n = s.length()-1;
		int k = in.nextInt();
		int[] sum = new int[n+1];
		sum[0] = 0;
		for(int i=1; i<=n; i++) 
			if (s.charAt(i) == '+') sum[i] = sum[i-1] + 1;
			else sum[i] = sum[i-1] - 1;
		int[] f = new int[n+1];
		f[0] = 0;
		for(int i=1; i<=n; i++) {
			f[i] = MAXN;
			for(int j=i-k; j>=0; j++) {
//				if (sum[i]-sum[j] == j-i) f[i] = min(f[i], f[j]+1);
			}
		}
		
	}

	void runIO() {

		in = new FastScanner(System.in);
		out = new PrintWriter(System.out);
		int test = in.nextInt();
		for(int i=0; i<test; i++) {
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
		new ProblemA().runIO();
	}
}