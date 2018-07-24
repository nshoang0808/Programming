import java.io.*;
import java.math.BigInteger;
import java.lang.*;
import java.util.*;

public class Problem_1 {
	public static final int INF = 1000000000;
	public static final int MAXN = 500010;
	FastScanner in;
	PrintWriter out;
	
	String winner(int[] andrea, int[] maria, String s) {
        int game_id=0;
        if (s.equals("Odd")) game_id=1;
        int n=andrea.length;
        int sum=0;
        for(int i=0; i+game_id<n; i+=2){
            sum += andrea[i+game_id]-maria[i+game_id];
        }
        if (sum==0) return "Tie";
		else if (sum>0) return "Andrea";
		else return "Maria";
    }
	
	boolean isTwin(String a, String b) {
		if (a.length() != b.length()) return false;
		int[] countEvenA = new int['z'+1];
		int[] countOddA = new int['z'+1];
		int[] countEvenB = new int['z'+1];
		int[] countOddB = new int['z'+1];
		int n=a.length();
		for(int i=0; i<n; i+=2) {
			countEvenA[a.charAt(i)]++;
			countEvenB[b.charAt(i)]++;
			if (i+1<n) {
				countOddA[a.charAt(i+1)]++;
				countOddB[b.charAt(i+1)]++;
			}
		}
		for (int x='a'; x<='z'; x++) {
			if (countEvenA[x] != countEvenB[x] || countOddA[x] != countOddB[x]) return false;
		}
		return true;
	}
	String[] twins(String[] a, String[] b) {
        int n=a.length;
        String[] res = new String[n];
        for(int i=0; i<n; i++) {
        	if(isTwin(a[i], b[i])) res[i] = "Yes";
        	else res[i] = "No";
        }
        return res;
    }
	int minRotate(String inscription) {
		int n = inscription.length();
		char smallest = 'z';
		for(int i=0; i<n; i++) if (inscription.charAt(i)<smallest) smallest = inscription.charAt(i);
		List<Integer> ids = new ArrayList<Integer>();
		for(int i=0; i<n; i++) {
			if (inscription.charAt(i)==smallest) ids.add(i);
		}
		int len = ids.size();
		if (len == 1) return ids.get(0);
		// define 2 pointers for traversing the string
		int res = 0;
		int j = 1;
		int i = 0;
		// extend the string for circular string
		inscription = inscription + inscription;
		//
		while (j<len && ids.get(j) < n && ids.get(j)+1<inscription.length()) {
			int curr_id = ids.get(res) + i+1;
			int compare_id = ids.get(j) + i+1;
			if (i==n) break;
			// if next characters equal to each other
			if (inscription.charAt(curr_id) == inscription.charAt(compare_id)) {
				i++;
			} else if (inscription.charAt(curr_id) > inscription.charAt(compare_id)) {
				res = j;
				j++;
				i=0;
			} else {
				j++;
				i=0;
			}
		}
		return ids.get(res);
	}
	void solve() {
//		int n=in.nextInt();
//		int a[] = new int[n];
//		int m[] = new int[n];
//		for(int i=0; i<n; i++) a[i] = in.nextInt();
//		in.nextInt();
//		for(int i=0; i<n; i++) m[i] = in.nextInt();
//		String game = in.next();
//		int game_id = 0;
//		if (game.equals("Odd")) game_id = 1;
//		int sum=0;
//		for(int i=0; i+game_id<n; i=i+2) {
//			out.println(i+game_id);
//			sum += a[i+game_id]-m[i+game_id]; 
//		}
//		if (sum==0) out.println("Tie");
//		else if (sum>0) out.println("Andrea");
//		else out.println("Maria");
//		out.println(winner(a, m, game));
		
//		String[] a = new String[2];
//		String[] b = new String[2];
//		a[0] = "adab"; a[1] = "dcba";
//		b[0] = "abcd"; b[1] = "abcd"; 
//		String[] res = twinscription(a,b);
//		for(int i=0; i<2; i++) {
//			out.println(res[i]);
//		}
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i=0; i<n; i++) {
			String s = sc.next();
			System.out.println(minRotate(s)+1);
		}
//		String s1 = "abaed";
//		out.println(minRotate(s1));
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
		new Problem_1().runIO();
	}
}