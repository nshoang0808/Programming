import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Try {
	FastScanner in;
	PrintWriter out;
  	class ListNode {
      	public int val;
      	public ListNode next;
      	ListNode(int x) { val = x; next = null;}
    }

	public ListNode merge(ListNode a, ListNode b) {
        if (a == null) return b; 
        if (b == null) return a;
        ListNode x;
        if (a.val < b.val) { x = new ListNode(a.val); a = a.next;}
        else { x = new ListNode(b.val); b = b.next; }
        ListNode y = x;
        while(a!=null || b!=null) {
            if(a == null) {
                x.next = new ListNode(b.val);
                x = x.next;
                b = b.next;
            } else
            if(b == null) {
                x.next = new ListNode(a.val);
                x = x.next;
                a = a.next;
            } else
            if(a.val<b.val) {
                x.next = new ListNode(a.val);
                x = x.next;
                a = a.next;
            } else {
                x.next = new ListNode(b.val);
                x = x.next;
                b = b.next;
            }
            if (y.next == null) y.next = x;
        }
        return y;
    }
	public ListNode mergeKLists(ArrayList<ListNode> a) {
	    int n = a.size();
	    for(int i=1; i<n; i++) {
	        ArrayList<ListNode> tmp = new ArrayList<ListNode>();
	        for(int j=0; j<a.size()-1; j++) {
	            tmp.add(merge(a.get(j),a.get(j+1)));
	        }
	        a = new ArrayList<ListNode>(tmp);
	    }
	    return a.get(0);
	}
	
 class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
 }
	public int isSymmetric(TreeNode a) {
	    if(a == null) return 1;
	    out.println(2);
	    if(a.left != a.right) return 0;
	    out.println(2);
	    if(a.left == null) return 1;
	    out.println(2);
	    TreeNode x = new TreeNode(0);
	    x.left = a.left.left; x.right = a.right.right;
	    if(isSymmetric(x)==0) return 0;
	    x.left = a.left.right; x.right = a.right.left;
	    return isSymmetric(x);
	}
/**********************************************/
 class UndirectedGraphNode {
     int label;
     ArrayList<UndirectedGraphNode> neighbors;
     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 }
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        LinkedList<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        queue.add(node);
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        map.put(node,clone);
        while(!queue.isEmpty()) {
			UndirectedGraphNode front = queue.pop();
			for (UndirectedGraphNode next : front.neighbors) {
				if(!map.containsKey(next)) {
					UndirectedGraphNode x = new UndirectedGraphNode(next.label);
					queue.add(next);
					map.put(next,x);
					map.get(front).neighbors.add(x);
				} else {
					map.get(front).neighbors.add(map.get(next));
				}
			}
        }
        return clone;
    }
/*************************************************/
 class Node{
    int key;
    int val;
    Node pre;
    Node next;
 
    public Node(int key, int val){
        this.key = key;
        this.val = val;
    }
}
	Node head; Node tail; HashMap<Integer, Node> map;
	int cap;
    public int get(int key) {
    	if(!map.containsKey(key)) {
    		Node x = map.get(key);
    		remove(x);
    		putHead(x);
    		return x.val;
    	} else return -1;

    }

    public void remove(Node x) {
    	if (x.pre == null) {
    		head = x.next;
    	} else x.pre.next = x.next;
    	if (x.next == null) {
    		tail = x.pre;
    	} else x.next.pre = x.pre;
    }

   	public void putHead(Node x) {
   		x.next = head;
   		x.pre = null;
   		if (head != null) head.pre = x;
   		head = x;
   		if (tail == null) tail = head;
   	}
    public void set(int key, int value) {
    	if(!map.containsKey(key)) {
    		Node x = new Node(key, value);
    		if (map.size() >= cap) {
    			map.remove(tail.key);
    			remove(tail);
    		}
    		putHead(x);
    		map.put(key,x);
    	} else {
    		Node x = map.get(key);
    		x.val = value;
    		remove(x);
    		putHead(x);
    	}
    }

    public void Solution(int capacity) {
    	map = new HashMap<Integer, Node>();
    	cap = capacity;
    }

	void solve() {

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
		new Try().runIO();
	}
}