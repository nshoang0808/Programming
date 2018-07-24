import java.io.*;
import java.util.*;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
 	  TreeNode(int x) { val = x; }
  }
public class Flatten {
	public static TreeNode flatten(TreeNode a) {
		Stack<TreeNode> st = new Stack<TreeNode>();
		TreeNode p = a;
	    while (a != null || !st.empty()) {
	    	if (a.right != null) st.push(a.right);
	    	if (a.left != null) {
	    		a.right = a.left;
	    		a.left = null;
	    	} else if (!st.empty()){
	    		a.right = st.pop();
	    	}
	    	a = a.right;
	    } 
	    return p;
	}
	public static void main(String[] args) {
		TreeNode x = new TreeNode(47);
		x.left = null; x.right = null;
		flatten(x);
		System.out.println(x.val);
	}
}