// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int[] solution(String A, String B) {
        // write your code in Java SE 8
        String[] arrA = A.split(" ");
        String[] arrB = B.split(" ");
        //Create an array to store all solution values
        int[] freq;
        int[] dp = new int[11];
        for(int i=0; i<arrA.length; i++) {
            String curr = arrA[i];
            freq = new int['z'+1];
            for(int j=0; j<curr.length(); j++) {
                freq[curr.charAt(j)]++;
            }
            for(int x='a'; x<='z'; x++)
                if (freq[x]>0) {
                    dp[freq[x]]++; //increase the number of strings in A that has x as the smallest character with frequency
                                    // freq[x] by increase F[x][freq[x]]
                    break;
                }
        }
        //Cumulative number of given strings that has frequency of the smallest character in the string less than i
        for(int i=2; i<=10; i++) dp[i] += dp[i-1];
        
        int[] res = new int[arrB.length];
        for(int i=0; i<arrB.length; i++) {
            String curr = arrB[i];
            freq = new int['z'+1];
            for(int j=0; j<curr.length(); j++) {
                freq[curr.charAt(j)]++;
            }
            for(int x='a'; x<='z'; x++)
                if (freq[x]>0) {
                    res[i] = dp[freq[x]-1];
                    break;
                }
        }
        return res;
    }
}