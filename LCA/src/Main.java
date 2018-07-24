import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static final int INF = 1000000000;
	public static final int MAXN = 500010;
	FastScanner in;
	PrintWriter out;
	ArrayList<ArrayList<Integer>> adj;
	int N, high; int[][] parent;
	int[] level;
	
	/* dfs the whole tree to get the level of each vertex*/
	void dfs(int x) {
		for(int i=1; i<=adj.get(x).get(0); i++) {
			int y = adj.get(x).get(i);
			if(level[y] == -1) {
				level[y] = level[x]+1;
				dfs(y);
			}
		}
	}
	
	void prepare() {
		dfs(1);
		for(int i=1; i<=high; i++) {
			for(int j=1; j<=N; j++) 
				if(parent[j][i-1] != -1) {
				parent[j][i] = parent[parent[j][i-1]][i-1];
			}
		}
	}
	int log2(int x) {
		return (int)Math.round(Math.log(x)/Math.log(2));
	}
	int lca(int x, int y) {
		if(level[x]>level[y]) return lca(y,x);
		int dif = log2(level[y]);
		for (int i=dif; i>=0; i--) 
			if (level[y]-(1 << i) >= level[x]) {
				y = parent[y][i];
				if (level[y] == level[x]) break;
			}
		if (x == y) return x;
		dif = log2(level[x]);
		for(int i=dif; i>=0; i--) 
		if (parent[x][i] != -1 && parent[x][i] != parent[y][i]){
			x = parent[x][i];
			y = parent[y][i];
		}
		return parent[x][0];
	}
	void solve() {
		N = in.nextInt();
		adj = new ArrayList<ArrayList<Integer>>(N+1);
		high = log2(N);
		parent = new int[N+1][high+1];
		for (int[] arr : parent) {
			Arrays.fill(arr, -1);
		}
		level = new int[N+1];
		Arrays.fill(level, -1);
		
		for (int i=0; i<=N; i++) adj.add(new ArrayList<Integer>());
		for (int i=1; i<=N; i++) {
			adj.get(i).add(in.nextInt());
			for (int j=1; j<=adj.get(i).get(0); j++) {
				adj.get(i).add(in.nextInt());
				parent[adj.get(i).get(j)][0] = i;
			}
		}
		// Precompute array parent
		level[1] = 0;
		prepare();
		
		// Now read the query:
		int query = in.nextInt();
		for(query=query; query>0; query--) {
			int x = in.nextInt(); int y = in.nextInt();
			out.println(lca(x,y));
		}
	}

	void runIO() {

		in = new FastScanner(System.in);
		out = new PrintWriter(System.out);
		
		int test = in.nextInt();
		for(int i=1; i<=test; i++) {
			out.printf("Case %d:\n",i);
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