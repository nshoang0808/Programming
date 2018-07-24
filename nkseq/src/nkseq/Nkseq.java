package nkseq;

import java.io.*;
import java.util.*;

class Nkseq {
	FastScanner in;
	PrintWriter out;

	void solve() {
		int n = in.nextInt();
		int[] A = new int[n+5], sumLeft = new int[n+5], sumRight = new int[n+5], minLeft = new int[n+5], minRight = new int[n+5];
		sumLeft[0] = 0; sumRight[n+1] = 0; minLeft[0] = 2000000000; minRight[n+1] = 2000000000;
		for(int i=0; i<n; i++) {
			A[i+1] = in.nextInt();
			sumLeft[i+1] = sumLeft[i] + A[i+1];
			minLeft[i+1] = Math.min(minLeft[i], sumLeft[i+1]);
		}
		for(int i=n; i>0; i--) {
			sumRight[i] = sumRight[i+1] + A[i];
			minRight[i] = Math.min(minRight[i+1], sumLeft[i]);
		}
		int res=0; minLeft[0] = 0;
		for(int i=1; i<=n; i++) if (minRight[i] - sumLeft[i-1]>0 && minLeft[i-1] + sumRight[i]>0) res++;
		out.println(res);
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
		// TODO Auto-generated method stub
		new Nkseq().runIO();
	}

}
