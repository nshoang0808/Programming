package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Maze {
	FastScanner in;
	PrintWriter out;
	int w,h,m,n,step;
	String c;
	
	void solve() {
		w = in.nextInt(); h = in.nextInt(); 
		if (in.hasMoreTokens()) c = in.next();
		if (c.charAt(0) == '>' || c.charAt(0) == '<') {
			step = Integer.parseInt(c.substring(1, c.length())) +1;
			if (c.charAt(0) == '>') step++;
			else step--;
		} else step = Integer.parseInt(c) + 1;
		n = w*2+1; m = h*2+1;
		int [][] maze = new int [n+2][m+2];
		for(int i=0; i<w; i++)
			for (int j=0; j<h; j++) {
				maze[j][2*i] = 1;
			}
		for(int i=1; i<n; i++) {
			maze[0][i] = 2;
			maze[m-1][i] = 2;
		}
		for(int i=0; i<w-1; i++) {
			if (i%2 == 0) {
				maze[m-2][(i+1)*2] = 0;
			} else maze[1][(i+1)*2] = 0;
		}
		int stepEachColumn = step/n, numColumnBegin = end
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
		new Maze().runIO();
	}
}