import java.io.*;
import java.math.BigInteger;
import java.lang.*;
import java.util.*;

public class ProgressPie {
	public static final int INF = 1000000000;
	public static final int MAXN = 500010;
	public static final double exp = 1e-6;
	FastScanner in;
	PrintWriter out;

	double distance(int x, int y) {
		return Math.sqrt((x*1.0-50)*(x*1.0-50)+(y*1.0-50)*(y*1.0-50));
	}
	void solve() {
		int test = in.nextInt();
		for(int t=1; t<=test; t++) {
			int p = in.nextInt();
			int x = in.nextInt();
			int y = in.nextInt();
			if (p == 0) {
				out.printf("Case #%d: white\n", t);
				continue;
			}
			double d = distance(x,y);
			if (d-50 > exp) {
				out.printf("Case #%d: white\n", t);
				continue;
			}
			if (d < exp) {
				out.printf("Case #%d: black\n", t);
				continue;
			}
			double progress = p*2.0*Math.PI/100;
			double angle = Math.acos((y*1.0-50)/d);
			if (x<50) angle = 2*Math.PI-angle;
			if (angle-progress<exp) out.printf("Case #%d: black\n", t);
			else out.printf("Case #%d: white\n", t);
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
			in = new FastScanner(new File("progress_pie.txt"));
			out = new PrintWriter(new File("progress_pie.out"));

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
		new ProgressPie().runIO();
	}
}