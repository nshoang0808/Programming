import java.io.*;
import java.math.BigInteger;
import java.lang.*;
import java.util.*;

public class Problem3 {
	public static final int INF = 1000000000;
	public static final int MAXN = 500010;
	FastScanner in;
	PrintWriter out;

	void solve() {
		int test = in.nextInt();
		for(int t=0; t<test; t++) {
			String s = in.nextLine();
			String[] arr = s.split(" ");
			String ans = "";
			if (arr[0].charAt(0) == arr[1].charAt(0)) ans += arr[0].charAt(0);
			else ans += Integer.toString(6-arr[0].charAt(0)-arr[1].charAt(0)+2*'0');
			char next = (char)('E'+'F'+'S'-arr[0].charAt(1)-arr[1].charAt(1));
			if (arr[0].charAt(1) == arr[1].charAt(1)) ans += arr[0].charAt(1);
			else ans += next;
			next = (char)('G'+'P'+'R'-arr[0].charAt(2)-arr[1].charAt(2));
			if (arr[0].charAt(2) == arr[1].charAt(2)) ans += arr[0].charAt(2);
			else ans += next;
			next = (char)('O'+'D'+'S'-arr[0].charAt(3)-arr[1].charAt(3));
			if (arr[0].charAt(3) == arr[1].charAt(3)) ans += arr[0].charAt(3);
			else ans += next;
			out.printf("Group %d: %s\n", t+1, ans);
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
			in = new FastScanner(new File("Set-Game_InputForSubmission_1.txt"));
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
		new Problem3().run();
	}
}