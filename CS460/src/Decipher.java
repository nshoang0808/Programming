import java.io.*;
import java.math.BigInteger;
import java.lang.*;
import java.util.*;

public class Decipher {
	public static final int INF = 1000000000;
	public static final int MAXN = 500010;
	FastScanner in;
	PrintWriter out;

	void solve() {
		String s = in.next();
		int n = s.length();
		int[] arr = new int[26];
		for(int i=0; i<n; i++) arr[s.charAt(i)-'A']++;
		for(int i=0; i<26; i++) out.printf("%d ", arr[i]);
		out.println();
		double ic = 0.0;
		for(int i=0; i<26; i++) if (arr[i] != 0) {
			ic += arr[i]*(arr[i]-1)*1.0;
		}
		ic = ic/(n*(n-1)*1.0);
		out.println(ic);
		for(int l=3; l<5; l++) {
			HashMap<String, Integer> hm = new HashMap<String, Integer>();
			for(int i=0; i<s.length()-l+1; i++) {
				String pr = s.substring(i, i+l);
				if (hm.containsKey(pr)) hm.put(pr, hm.get(pr)+1);
				else hm.put(pr, 1);
			}
			for(String k: hm.keySet()) {
				if (hm.get(k)>1) out.printf("%s %d\n", k, hm.get(k));
			}
		}
//		for(int j=0; j<5; j++) {
//			for(int i=0; i<140/5; i++) {
//				out.print(s.charAt(i*5+j));
//			}
//			out.println();
//		}
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
		new Decipher().runIO();
	}
}