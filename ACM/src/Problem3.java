import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Problem3 {
	public static final int INF = 1000000000;
	public static final int MAXN = 100005;
	FastScanner in;
	PrintWriter out;
	int N, high, root; 
	int[][] parent;
	int[] level;
	Set<Integer> nodes;

	void dfs(int x) {
		int y = parent[x][0];
		out.println(y);
		if (level[y] > -1) {
			level[x] = level[y] + 1;
		} else {
			dfs(y);
			level[x] = level[y] + 1;
		}
	}
	
	void prepare() {
		for(int node: nodes) {
			if (level[node] == -1) dfs(node);
		}
		for(int i=1; i<=high; i++) {
			for(int j=1; j<=N; j++) 
				if(parent[j][i-1] != -1) {
				parent[j][i] = parent[parent[j][i-1]][i-1];
			}
		}
	}
	int log2(int x) {
		return (int)Math.round(Math.log(x)/Math.log(2)) + 1;
	}
	int find_parent(int x, int k) {
		int dif = log2(k);
		for(int i=dif; i>=0; i--){
			if (((k >> dif) & 1 )== 1) x = parent[x][dif];
		}
		return x;
	}
	
	void solve() {
		int T =  in.nextInt();
		for(int test = 1; test<=T; test++) {
			int N = in.nextInt();
			int[] edges_node = new int[N];
			int[] edges_parent = new int[N];
			nodes = new HashSet<Integer>();
			for(int i=0; i<N-1; i++) {
				int x = in.nextInt();
				int y = in.nextInt();
				if (y == 0) root = x;
				if (!nodes.contains(x)) nodes.add(x);
				if (!nodes.contains(y) && y != 0) nodes.add(y);
				edges_node[i] = x;
				edges_parent[i] = y;
			}
			out.print(edges_node.length);
			int Q = in.nextInt();
			high = log2(2*MAXN);
			parent = new int[MAXN][high+1];
			for (int[] arr : parent) {
				Arrays.fill(arr, -1);
			}
			level = new int[MAXN];
			Arrays.fill(level, -1);
			
			for (int i=0; i<N-1; i++) {
				int x = edges_node[i];
				int y = edges_parent[i];
				parent[x][0] = y;
			}
			level[root] = 0;
			prepare();
			
			for(int i=0; i<Q; i++) {
				int ct = in.nextInt();
				if (ct == 0) {
					int y = in.nextInt();
					int x = in.nextInt();
					level[x] = level[y] +1;
					parent[x][0] = y;
					for(int j=1; j<=high; j++) {
						if(parent[x][j-1] != -1) {
							parent[x][j] = parent[parent[x][j-1]][j-1];
						} else break;
					}
				} else if (ct == 1) {
					N--;
					int x = in.nextInt();
					level[x] = -1;
					parent[x] = new int[high+1];
				} else {
					out.println(find_parent(in.nextInt(),in.nextInt()));
				}
			}
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
		new Problem3().runIO();
	}
}