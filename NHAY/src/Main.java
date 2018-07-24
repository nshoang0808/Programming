import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
	public static final int INF = 1000000000;
	public static final int MAXN = 500010;
	FastScanner in;
	PrintWriter out;
	
	int[] pre_compute(String s) {
		int[] T = new int[s.length()];
		T[0] = -1; int k = -1;
		for(int i=1; i<s.length(); i++) {
			while (k>-1 && s.charAt(i) != s.charAt(k+1)) k = T[k];
			if (s.charAt(k+1) == s.charAt(i)) k++;
			T[i] = k;
		}
		return T;
	}
	void kmp(String source, String str) {
		int[] T = pre_compute(str);
		int k=-1;
		for(int i=0; i<source.length(); i++) {
			while(k>-1 && str.charAt(k+1) != source.charAt(i)) k = T[k];
			if (str.charAt(k+1) == source.charAt(i)) k++;
			if (k == str.length()-1) {
				out.println(i-k);
				k = -1;
			}
		}
	}
	void solve() {
		while(in.hasMoreTokens()) {
			int n = in.nextInt();
			//if (n == null) return;
			String str = in.nextLine();
			String source = in.nextLine();
			kmp(source, str);
			out.println();
			if(n == 0) return;
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
		new Main().runIO();
	}
}