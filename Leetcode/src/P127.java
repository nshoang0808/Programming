import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class P127 {
    static boolean nextTo(String u, String v) {
        int diff = 0;
        for(int i=0; i<u.length(); i++) {
            if(u.charAt(i) != v.charAt(i)) diff++;
        }
        return (diff == 1);
    }
    public static int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        Set<String> set = new HashSet<String>();
        for(String u: wordList) if (!set.contains(u)) set.add(u);
        for(String u: set) {
            map.put(u, new ArrayList<String>());
            for(String v: set) {
                if (nextTo(u, v)) {
                    map.get(u).add(v);
                }
            }
        }
        Queue<String> q1 = new LinkedList<String>();
        q1.add(beginWord);
        set.remove(beginWord);
        for(int count=1; count<=set.size()+1; count++) {
            if (q1.isEmpty()) return 0;
            Queue<String> q2 = new LinkedList<String>();
            while (!q1.isEmpty()) {
	            String curr = q1.poll();
	            List<String> neighbor = map.get(curr);
	            for(int i=0; i<neighbor.size(); i++) {
                    String next = neighbor.get(i);
                    if (set.contains(next)) {
                        set.remove(next);
                        q2.add(next);
                        if (next.equals(endWord)) return ++count;
                    }
	            }
            }
            q1 = q2;
        }
        return 0;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<String> set = new HashSet<String>();
		set.add("hot");
		set.add("cog");
		set.add("dot");
		set.add("dog");
		set.add("hit");
		set.add("lot");
		set.add("log");
		Set<String> set1 = new HashSet<String>();
		set1.add("a");
		set1.add("b");
		set1.add("c");
		System.out.println(ladderLength("a", "b", set1));
	}

}
