import java.util.HashMap;

class TrieNode {
	HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
	char c; 
	boolean isLeaf = false;
	public TrieNode() {}
	public TrieNode(char x) {
		this.c = x;
	}
}
public class Trie {
	private TrieNode root;
	
    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
    	TrieNode child;
    	HashMap<Character, TrieNode> map = root.children;
    	for(int i=0; i<word.length(); i++) {
    		char c = word.charAt(i);
    		if(!map.containsKey(c)) {
    			child = new TrieNode(c);
    			map.put(c, child);
    		} else child = map.get(c);
    		map = child.children;
    		if (i == word.length()-1) child.isLeaf = true;
    	}
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode x = findNode(word);
        if(x != null && x.isLeaf) return true;
        else return false;
    }
    
    public TrieNode findNode(String word) {
    	HashMap<Character, TrieNode> map = root.children;
    	TrieNode res = null;
    	for(int i=0; i<word.length(); i++) {
    		char c = word.charAt(i);
    		if(!map.containsKey(c)) return null;
    		res = map.get(c);
    		map = res.children;
    	}
    	return res;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if (findNode(prefix) != null) return true;
        else return false;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
