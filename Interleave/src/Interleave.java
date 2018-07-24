public class Interleave {
	public static int isInterleave(String a, String b, String c) {
	    a = " " +a;
	    b = " " +b;
	    c = " " +c;
	    boolean f[][] = new boolean[a.length()+1][b.length()+1];
	    f[0][0] = true;
	    for (int i=0; i<a.length(); i++) {
	        for (int j=0; j<b.length(); j++) {
	            if (i+j>0) {
	                if (i>0) f[i][j] = f[i-1][j] && (a.charAt(i) == c.charAt(i+j));
	                if (j>0) f[i][j] = f[i][j] || (f[i][j-1] && (b.charAt(j) == c.charAt(i+j)));
	                System.out.printf("(%d, %d) = %s\n", i, j, f[i][j]);
	            }
	        }
	    }
	    if (f[a.length()-1][b.length()-1]) return 1;
	    return 0;
	}
	
	public static void main(String[] args) {
		
		System.out.println(isInterleave("B", "e", "Be"));
	}
}