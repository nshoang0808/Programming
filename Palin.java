import java.io.*;
import java.math.BigInteger;
import java.lang.*;
import java.util.*;

public class Palin {
	public static final int INF = 1000000000;
	public static final int MAXN = 500010;
	FastScanner in;
	PrintWriter out;

	void solve() {
		int test = in.nextInt();
		for (test = test; test>0; test--) {
			String x = in.next();
			int first = 0;
			int pt = (x.length()/2)-1;
			while (pt>=0) {
				if (x.charAt(pt)<x.charAt(x.length()-1-pt)) break;
				if (x.charAt(pt)>x.charAt(x.length()-1-pt) && first == 0) first = pt;
				--pt;
			}
			String y = "";
			if (pt<0) {
				pt++;
				while (pt<x.length()/2) {
					if (x.charAt(pt)!=x.charAt(x.length()-1-pt)) break;
					pt++;
				}
				if (pt==x.length()/2) {
					if (x.length()%2 == 0) pt--;
					while(pt>=0) {
						if (x.charAt(pt)<'9') break;
						pt--;
					}
					if (pt < 0) {
						y = "1";
						for (int i = 0; i<x.length()-1; i++) y = y + "0";
						y = y + "1";
					} else {
						y = x.substring(0,pt) + (char)(x.charAt(pt)+1);
						for (int i = pt+1; i<x.length()/2; i++) y = y+"0";
						if (x.length()%2 == 1 && pt < x.length()/2) y = y + "0";
						for(int i=x.length()/2 -1; i>=0; i--) y = y + y.charAt(i);
					}
				} else {
					y = x.substring(0,pt+1);
					for(int i=pt+1; i<x.length()/2; i++) y = y+x.charAt(i);
					if (x.length()%2 == 1) y = y+x.charAt(x.length()/2);
					for(int i=x.length()/2 -1; i>=0; i--) y = y + y.charAt(i);
				}
			} else {
				y = x.substring(0,pt) + (char)(x.charAt(pt)+1);
				for(int i = pt+1; i<x.length()/2; i++) y = y+ "0";
				if (x.length()%2 == 1) y = y+"0";
				for(int i = pt; i>=0; i--) y = y+y.charAt(i);
			}
		out.println(y);
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
	}

	public static void main(String[] args) {
		new Palin().runIO();
	}
}