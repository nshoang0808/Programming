import java.io.*;
import java.math.BigInteger;
import java.lang.*;
import java.util.*;

public class Problem4 {
	public static final int INF = 1000000000;
	public static final int MAXN = 500010;
	FastScanner in;
	PrintWriter out;

	void solve() {
		int n=0, m=0;
		int[] hx = {1, 0, -1, -1, -1, 0, 1, 1};
		int[] hy = {1, 1, 1, 0, -1, -1, -1, 0};
		List<List<Character>> a = new ArrayList<List<Character>>();
		Queue<Integer> qx = new LinkedList<Integer>();
		Queue<Integer> qy = new LinkedList<Integer>();
		
		do {
			String s = in.nextLine();
			if (s == null) break;
			if (m==0) m = s.length();
			a.add(new ArrayList<Character>());
			for(int i=0; i<m; i++) {
				a.get(n).add(s.charAt(i));
				if (a.get(n).get(i) == '1') {
					qx.add(n);
					qy.add(i);
				}
			}
			n++;
		} while (true);
		
		while (!qx.isEmpty()) {
			int x = qx.poll();
			int y = qy.poll();
			for(int i=0; i<8; i++) {
				int dx = x+hx[i];
				int dy = y+hy[i];
				if (dx>=0 && dy>=0 && dx<n && dy<m && a.get(dx).get(dy) == '0')
				for(int j=0; j<8; j++) {
					int ex = x+hx[i];
					int ey = y+hy[i];
					if (dx>=0 && dy>=0 && dx<n && dy<m && (ex != dx || ey != dy) && a.get(ex).get(ey) == '1') {
						qx.add(dx); qy.add(dy);
						a.get(dx).set(dy, '1');
					}
				}
			}
		}
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				out.print(a.get(i).get(j));
			}
			out.println();
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
		new Problem4().run();
	}
}