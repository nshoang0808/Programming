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

public class PieProgress {
	public static final int INF = 1000000000;
	public static final int MAXN = 500010;
	FastScanner in;
	PrintWriter out;

	void solve() {
		int test = in.nextInt();
		for (int t=1; t<=test; t++) {
			int n = in.nextInt();
			int m = in.nextInt();
			int[][] price = new int[n][m+1];
			int[][] arr = new int[n][n+1];
			for(int i=0; i<n; i++)
				for(int j=0; j<=n; j++) arr[i][j] = INF;
			
			//read input, sort, and process
			for(int i=0; i<n; i++) {
				for(int j=1; j<=m; j++) {
					price[i][j] = in.nextInt();
				}
				Arrays.sort(price[i]);
				arr[i][0] = 0;
				for(int j=1; j<=n; j++) {
					if (i==0) {
						if (j>m) break;
						arr[i][j] = arr[i][j-1] + price[i][j] + j*j - (j-1)*(j-1);
					} else {
						int daily_total = 0;
						arr[i][j] = arr[i-1][j];
						for(int k=1; k<=m; k++) {
							if (k>j) break;
							daily_total += price[i][k];
							arr[i][j] = Math.min(arr[i][j], daily_total+k*k+arr[i-1][j-k]);
						}
					}
				}
			}
			out.printf("Case #%d: %d\n", t, arr[n-1][n]);
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
			in = new FastScanner(new File("pie_progress.txt"));
			out = new PrintWriter(new File("pie_progress.out"));

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
		new PieProgress().runIO();
	}
}