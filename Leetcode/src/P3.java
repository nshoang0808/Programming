import java.util.HashMap;

public class P3 {
    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int rear = 0;
        int result = 0;
        for(int front=0; front<s.length(); front++) {
            if (map.containsKey(s.charAt(front))) {
                result = Math.max(result, front-rear);
                int pos = map.get(s.charAt(front));
                for(rear = rear; rear<=pos; rear++) {
                	map.remove(s.charAt(rear));
                }
            }
            map.put(s.charAt(front), front);
        }
        return Math.max(result, s.length()-rear);
    }
	public static void main (String[] args) {
		// your code goes here
		String s = "abba";
		System.out.println(lengthOfLongestSubstring(s));
	}

}
