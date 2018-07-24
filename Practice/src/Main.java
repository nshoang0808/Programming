import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	FastScanner in;
	PrintWriter out;
	class Person implements Comparable<Person>{
		public int id, infronts;
		public Person(int id, int infronts) {
			this.id = id;
			this.infronts = infronts;
		}
		public int compareTo(Person x) {
			return infronts-x.infronts;
		}
	} 
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public final List<Character> operater = Arrays.asList('+','-','*','/','^');
	
	public TreeNode buildTree(List<Integer> inorder, List<Integer> postorder) {
	    int val = postorder.get(postorder.size()-1);
	    int id = 0;
	    for (int i=0; i<inorder.size(); i++) if (inorder.get(i) == val) {
	        id = i;
	        break;
	    }
	    TreeNode curr = new TreeNode(val);
	    if (postorder.size() == 1) return curr;
	    if (id > 0) curr.left = buildTree(inorder.subList(0, id), postorder.subList(0, id));
	    if (id < inorder.size()-1) curr.right = buildTree(inorder.subList(id+1, inorder.size()), postorder.subList(id, postorder.size()-1));
	    return curr;
	}
	public int exist(ArrayList<String> a, String b) {
	    if (a.size() == 0) return 0;
	    int n = a.size(), m = a.get(0).length();
	    boolean[][] f = new boolean[n+1][m+1];
	    for(int i=0; i<n; i++)
	    for(int j=0; j<m; j++) f[i][j] =true;
	    for (int t=0; t<b.length(); t++) {
		    boolean[][] f1 = new boolean[n+1][m+1];
	        for(int i=0; i<n; i++) {
	            for(int j=0; j<m; j++) {
	            	if ((t == 3 || t==2) && i==0 && j==2) {
	            		int s = 3;
	            	}
	                if (a.get(i).charAt(j) == b.charAt(t)) f1[i][j] = true;
	                else f1[i][j] = false;
	                boolean adj = false;
	                if (i>0) adj = adj || f[i-1][j];
	                if (j>0) adj = adj || f[i][j-1];
	                if (i<n-1) adj = adj || f[i+1][j];
	                if (j<m-1) adj = adj || f[i][j+1];
	                f1[i][j] = f1[i][j] && adj;
	            }
	        }
	        f = f1;
	        System.out.printf("%s %s %s\n", f[1][3], f[0][3], f[0][2]);
	    }
	    for(int i=0; i<n; i++)
	    for(int j=0; j<m; j++) if (f[i][j]) return 1;
	    return 0;
	}
	void solve() {
		List<Integer> a = new ArrayList<Integer>();
		List<Integer> b = new ArrayList<Integer>();
		a.add(2); a.add(1); a.add(3);
		b.add(2); b.add(3); b.add(1);
		TreeNode x = buildTree(a, b);
		ArrayList<String> c = new ArrayList<String>();
		c.add("FEDCBECD");
		c.add("FABBGACG");
		c.add("CDEDGAEC");
		c.add("BFFEGGBA");
		c.add("FCEEAFDA");
		c.add("AGFADEAC");
		c.add("ADGDCBAA");
		c.add("EAABDDFF");
		String d = "BCDCB";
		System.out.println(exist(c, d));
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
		new Main().runIO();

	}

}
