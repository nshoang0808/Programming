package main;

import java.io.*;
import java.math.BigInteger;
import java.lang.*;
import java.util.*;

class UnionFind {                                              // OOP style
	  private Vector<Integer> p, rank, setSize;
	  private int numSets;

	  public UnionFind(int N) {
	    p = new Vector<Integer>(N);
	    rank = new Vector<Integer>(N);
	    setSize = new Vector<Integer>(N);
	    numSets = N;
	    for (int i = 0; i <= N; i++) {
	      p.add(i);
	      rank.add(0);
	      setSize.add(1);
	    }
	  }

	  public int findSet(int i) { 
	    if (p.get(i) == i) return i;
	    else {
	      int ret = findSet(p.get(i)); p.set(i, ret);
	      return ret; } }

	  public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

	  public void unionSet(int i, int j) { 
	    if (!isSameSet(i, j)) { numSets--; 
	    int x = findSet(i), y = findSet(j);
	    // rank is used to keep the tree short
	    if (rank.get(x) > rank.get(y)) { p.set(y, x); setSize.set(x, setSize.get(x) + setSize.get(y)); }
	    else                           { p.set(x, y); setSize.set(y, setSize.get(y) + setSize.get(x));
	                                     if (rank.get(x) == rank.get(y)) rank.set(y, rank.get(y) + 1); } } }
	  public int numDisjointSets() { return numSets; }
	  public int sizeOfSet(int i) { return setSize.get(findSet(i)); }
}
class TripInt implements Comparable {
	  Integer _first, _second, _third;

	  public TripInt(Integer f, Integer s, Integer t) {
	    _first = f;
	    _second = s;
	    _third = t;
	  }

	  public int compareTo(Object o) {
	    if (!this.first().equals(((TripInt)o).first()))
	      return this.first() - ((TripInt)o).first();
	    else if (!this.second().equals(((TripInt)o).second()))
	      return this.second() - ((TripInt)o).second();
	    else
	      return this.third() - ((TripInt)o).third();
	  }

	  Integer first() { return _first; }
	  Integer second() { return _second; }
	  Integer third() { return _third; }

	  public String toString() { return first() + " " + second() + " " + third(); }
	}

class PairInt implements Comparable {
	  Integer _first, _second;

	  public PairInt(Integer f, Integer s) {
	    _first = f;
	    _second = s;
	  }

	  public int compareTo(Object o) {
	    if (!this.first().equals(((PairInt)o).first()))
	      return this.first() - ((PairInt)o).first();
	    else
	      return this.second() - ((PairInt)o).second();
	  }

	  Integer first() { return _first; }
	  Integer second() { return _second; }
}
public class Main {
	public static final int INF = 1000000000;
	public static final int MAXN = 500010;
	int n = 0, u = 0, v = 0, w = 0, mst = 0;
	Vector<TripInt> Edges = new Vector<TripInt>();
	
	FastScanner in;
	PrintWriter out;
	
	void solve() {
		int test = in.nextInt();
		for (test = test; test>0; test--) {
			n = in.nextInt();
			Edges.clear();
			for (u=1; u<=n; u++) {
				String s = in.next();
				int num_adj = in.nextInt();
				for (int j=1; j<=num_adj; j++) {
					v = in.nextInt(); w = in.nextInt();
					Edges.add(new TripInt(w,u,v));
					}
				}
			UnionFind x = new UnionFind(n);
			Collections.sort(Edges);
			mst=0; int num_edge = 0;
			Iterator y = Edges.iterator();
			while (num_edge < n-1) {
				TripInt next = (TripInt)y.next();
				if (!x.isSameSet(next.second(),next.third())) {
					num_edge++;
					mst += next.first();
					x.unionSet(next.second(),next.third());
				}
			}
			out.println(mst);
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
		new Main().runIO();
	}
}