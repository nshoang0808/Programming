import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main {
	public static final int INF = 1000000000;
	public static final int MAXN = 500010;
	public ArrayList<Integer> heights, infronts;
	FastScanner in;
	PrintWriter out;
	class SegmentTree {
		private ArrayList<Integer> A;
		private long[] st,mark;
		private int n;
		private int left(int p) { return (p << 1);}
		private int right(int p) { return (p << 1)+1;}
		private long max(int x, int y) {if (x>y) return x; return y;}
		private long min(int x, int y) {if (x<y) return x; return y;}
		
		public SegmentTree(ArrayList<Integer> arr) {
			 A = arr; n = A.size();
			 st = new long[4*n]; mark = new long[4*n];
			 for(int i=0; i<4*n; i++){
				 st[i]=0; mark[i]=0;
			 }
			 build_tree(1, 0, n-1);
		}
		
		private void build_tree(int x, int l, int r){
			if (l==r) st[x]=A.get(l);
			else {
				build_tree(left(x), l, (l+r)/2);
				build_tree(right(x), ((l+r)/2)+1, r);
				st[x] = st[left(x)]+ st[right(x)];
			}
		}
		private long query(int x, int l, int r, int i, int j){
			if (l>r || i>r || j<l) return 0;
			if (mark[x] != 0) {
				st[x] += mark[x]*(long)(r-l+1);
				if (l<r) {
					mark[left(x)] += mark[x];
					mark[right(x)] += mark[x];
				}
				mark[x] = 0;
			}
			if (i<=l && r<=j) return st[x];
			long x1 = query(left(x), l, (l+r)/2, i, j);
			long x2 = query(right(x), (l+r)/2 +1, r, i, j);
			return x1+x2;
		}
		public void print_tree(int x, int l, int r) {
			System.out.printf("[%d, %d] = %d\n", l, r, st[x]);
			if (l<r) {
				print_tree(left(x), l, (l+r)/2);
				print_tree(right(x), (l+r)/2 +1, r);
			}
		}
		
		private void update(int x, int l, int r, int i, int j, long new_val){
			if (mark[x] != 0) {
				st[x] += mark[x]*(long)(r-l+1);
				if (l<r) {
					mark[left(x)] += mark[x];
					mark[right(x)] += mark[x];
				}
				mark[x] = 0;
			}
			if (l>r || i>r || j<l) return;
			if (i<=l && r<=j) {
				st[x] += (r-l+1)*new_val;
				if(l<r) {
					mark[left(x)] += new_val;
					mark[right(x)] += new_val;
				}
				return;
			}
			update(left(x), l, (l+r)/2, i, j, new_val);
			update(right(x), (l+r)/2 +1, r, i, j, new_val);
			st[x] = st[left(x)]+st[right(x)];
		}
		public long query(int l, int r) {
			return query(1, 0, n-1, l, r);
		}
		public void update(int l, int r, int new_val) {
			update(1, 0, n-1, l, r, new_val);
		}
	}
	void solve() {
		heights = new ArrayList<Integer>();
		infronts = new ArrayList<Integer>();
		int n = in.nextInt();
		for (int i=0; i<n; i++) {
			heights.add(0);
		}
		for (int i=0; i<n; i++) {
			infronts.add(0);
		}
		SegmentTree st = new SegmentTree(heights);
		int query = in.nextInt();
		for(int i=0; i<query; i++) {
			int k = in.nextInt(); int p = in.nextInt(); int q = in.nextInt();
			if (k==1) out.println(st.query(p-1, q-1));
			else st.update(p-1, q-1, in.nextInt());
			//st.print_tree(1, 0, n-1);
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