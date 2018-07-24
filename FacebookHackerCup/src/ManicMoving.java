import java.io.*;
import java.math.BigInteger;
import java.lang.*;
import java.util.*;

public class ManicMoving {
	public static final long INF = (long)100000000*50000000;
	public static final int MAXN = 500010;
	FastScanner in;
	PrintWriter out;

	void solve() {
		int test = in.nextInt();
		for(int t=1; t<=test; t++) {
			int n = in.nextInt();
			int m = in.nextInt();
			int k = in.nextInt();
			int[][] map = new int[n+1][n+1];
			long[][] d = new long[n+1][n+1];
			for(int a=1; a<=n; a++)
				for(int b=1; b<=n; b++) d[a][b] = INF;
			for(int a=1; a<=n; a++) d[a][a] = 0;
			for(int i=0; i<m; i++) {
				int x = in.nextInt();
				int y = in.nextInt();
				int g = in.nextInt();
				if (map[x][y] == 0) map[x][y] = g;
				map[x][y] = Math.min(map[x][y], g);
				map[y][x] = map[x][y];
				d[x][y] = map[x][y];
				d[y][x] = map[y][x];
			}
			for(int b=1; b<=n; b++) {
				for(int a=1; a<=n; a++) {
					for(int c=1; c<=n; c++) {
						d[a][c] = Math.min(d[a][c], d[a][b]+d[b][c]);
					}
				}
			}
			int[] town_x = new int[k+1];
			int[] town_y = new int[k+1];
			for(int i=1; i<=k; i++) {
				town_x[i] = in.nextInt();
				town_y[i] = in.nextInt();
			}
			long[][] f = new long[k+1][2];
			f[1][0] = d[1][town_x[1]] + d[town_x[1]][town_y[1]];
			if (k == 1) {
				if (f[1][0] >=INF) out.printf("Case #%d: -1\n", t); 
				else out.printf("Case #%d: %d\n", t, f[1][0]);
				continue;
			}
			f[1][1] = d[1][town_x[1]] + d[town_x[1]][town_x[2]] + d[town_x[2]][town_y[1]];
			f[2][0] = Math.min(f[1][0] + d[town_y[1]][town_x[2]] + d[town_x[2]][town_y[2]], d[1][town_x[1]] + d[town_x[1]][town_x[2]] + d[town_x[2]][town_y[1]] + d[town_y[1]][town_y[2]]);
			if (k == 2) {
				if (f[2][0] >=INF) out.printf("Case #%d: -1\n", t); 
				else out.printf("Case #%d: %d\n", t, f[2][0]);
				continue;
			}
			f[2][1] = f[1][1] + d[town_y[1]][town_x[3]] + d[town_x[3]][town_y[2]];
			for(int i=3; i<=k; i++) {
				f[i][0] = Math.min(f[i-1][1] + d[town_y[i-1]][town_y[i]], Math.min(f[i-1][0] + d[town_y[i-1]][town_x[i]] + d[town_x[i]][town_y[i]], f[i-2][0] + d[town_y[i-2]][town_x[i-1]] + d[town_x[i-1]][town_x[i]] + d[town_x[i]][town_y[i-1]] + d[town_y[i-1]][town_y[i]]));
				if (i<k) {
					f[i][1] = f[i-1][1] + d[town_y[i-1]][town_x[i+1]] + d[town_x[i+1]][town_y[i]];
				}
			}
			if (f[k][0] >= INF) out.printf("Case #%d: -1\n", t); 
			else out.printf("Case #%d: %d\n", t, f[k][0]);
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
			in = new FastScanner(new File("manic_moving.txt"));
			out = new PrintWriter(new File("manic_moving.out"));

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
		new ManicMoving().runIO();
	}
}