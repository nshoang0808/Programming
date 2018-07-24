import java.io.*;
import java.lang.*;
import java.util.*;

public class Problem1 {
	public static final int INF = 1000000000;
	public static final int MAXN = 500010;
	FastScanner in;
	PrintWriter out;
	
    String stringForm(int x) {
        String s = String.valueOf(x);
        if (s.length() == 1) s = "0"+s;
        return s;
    }
    public String solution(String S) {
        // write your code in Java SE 8
        // Get the hour and minute separately
        String[] time = S.split(":");
        Set<Character> cset = new HashSet<Character>();
        cset.add(S.charAt(0));
        cset.add(S.charAt(1));
        cset.add(S.charAt(3));
        cset.add(S.charAt(4));
        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);
        // Loop till 23:59 to find the first valid time
        for(int i=hour; i<24; i++)
            for(int j=0; j<60; j++)
            if (i>hour || j>minute) {
                String hour_s = stringForm(i);
                String minute_s = stringForm(j);
                if (cset.contains(hour_s.charAt(0)) && cset.contains(hour_s.charAt(1)) && cset.contains(minute_s.charAt(0)) && cset.contains(minute_s.charAt(1))) {
                    return hour_s+":"+minute_s;
                }
            }
        // Loop from 00:00 to current time if cannot find the time from the same day
        for(int i=0; i<=hour; i++)
            for(int j=0; j<60; j++)
            if (i<hour || j<minute) {
                String hour_s = stringForm(i);
                String minute_s = stringForm(j);
                if (cset.contains(hour_s.charAt(0)) && cset.contains(hour_s.charAt(1)) && cset.contains(minute_s.charAt(0)) && cset.contains(minute_s.charAt(1))) {
                    return hour_s+":"+minute_s;
                }
            }
        return S;
    }
    
    public int solution(int[] P, int K) {
        // write your code in Java SE 8
        int n = P.length;
        // Reverse the P array, getting array D, with D[i] = the day the flower i-th blooms
        // Array P has n+2 elements with 2 added elements at 2 ends to prevent overflow
        int[] D = new int[n+2];
        for(int i=0; i<n; i++) {
            int day = P[i];
            D[day] = i+1;
        }
        D[0] = 0;
        D[n+1] = 0;
        int left = 0;
        int right = K+1;
        int daymin = n+4;
        for(int i=1; right<=n+1; i++) {
            if (D[i]<D[left] || D[i]<=D[right]) {
                if (i == right) {
                    int day = Math.max(D[left], D[right]);
                    daymin = Math.min(day, daymin);
                }
                left = i;
                right = i+K+1;
            }
        }
        if (daymin == n+4) return -1;
        return daymin;
    }
    
    public int solution1(int[] P, int K) {
        // write your code in Java SE 8
        int n = P.length;
        // Reverse the P array, getting array D, with D[i] = the day the flower i-th blooms
        int[] D = new int[n];
        for(int i=0; i<n; i++) {
            int day = P[i];
            D[day-1] = i+1;
        }
        int left = 0;
        int right = K+1;
        int daymin = n+4;
        for(int i=0; right<n; i++) {
            if (D[i]<D[left] || D[i]<=D[right]) {
                
                if (i == right) {
                    int day = Math.max(D[left], D[right]);
                    daymin = Math.min(day, daymin);
                }
                left = i;
                right = i+K+1;
            }
        }
        if (daymin == n+4) return -1;
        return daymin;
    }
	void solve() {
//		out.println(solution("2-4A0r7-4k", 4));
//		String s = "23:32";
//		out.println(solution(s));
		int[] arr = {2, 4, 3, 1, 5};
		int k = 2;
		out.println(solution(arr, k));
		out.println(solution1(arr, k));
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
		new Problem1().runIO();
	}
}