
import java.io.*;
import java.math.BigInteger;
import java.lang.*;
import java.util.*;

public class Main {
	public static final int INF = 1000000000;
	public static final int MAXN = 500010;
	private Vector< Vector< IntegerPair > > AdjList;
	int[] dist;
	Comparator<IntegerPair> cmp = new Comparator<IntegerPair>() {
		public int compare(IntegerPair x, IntegerPair y) {
			return x.second() - y.second();
		}
	};
	PriorityQueue<IntegerPair> pq;
	int N, M, s, t;
	FastScanner in;
	PrintWriter out;
	
	void solve() {
		//Read the number of vertices and edges
		N = in.nextInt();
		M = in.nextInt();
		AdjList = new Vector< Vector< IntegerPair > >();
		//Reset distance array
		dist = new int[N+10];
		//Reset AdjList
		for(int i=0; i<N+10; i++) {
			Vector<IntegerPair> next = new Vector<IntegerPair>();
			AdjList.add(next);
			dist[i] = INF;
		}
		
		//Add edge into AdjList
		for(int i=0; i<M; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			AdjList.get(x).add(new IntegerPair(y,0));
			AdjList.get(y).add(new IntegerPair(x,1));
		}
		
		//Read the start and finish vertices
		int s = 1; int t = N;
		
		//First vertex has distance of 0
		dist[s] = 0;
		
		//Make a heap to store value
		int u,v,w;
		pq = new PriorityQueue<IntegerPair>(1, cmp);
		pq.add(new IntegerPair(s,0));
		while(!pq.isEmpty()) {
			IntegerPair start = pq.remove();
			u = start.first();
			if (start.second() > dist[u]) continue;
			if(u == t) break;
			Iterator it = AdjList.get(u).iterator();
			while(it.hasNext()) {
				IntegerPair p = (IntegerPair) it.next();
				v = p.first(); w = p.second();
				if (dist[v] > dist[u] + w) {
					dist[v] = dist[u] + w;
					pq.add(new IntegerPair(v, dist[v]));
				}
			}
		}
		if (dist[t] == INF) out.println("-1");
		else out.println(dist[t]);
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
		new Main().runIO();
	}
}