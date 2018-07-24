import java.util.*;
import java.io.*;

public class primesum {
	public boolean isPrime(int A) {
	    if (A<2) return false;
		int upperLimit = (int)(Math.sqrt(A));
		for (int i = 2; i <= upperLimit; i++) {
			if (i < A && A % i == 0) return false;
		}
		return true;
	}
    public List<Integer> prime(int a) {
        for(int x = 2; x<a/2; x++) 
        if (isPrime(x) && isPrime(a-x)) return new ArrayList<Integer>(Arrays.asList(x,a-x));
    	return null;
    }
    public static void main(String args[]) {
    	new primesum().prime(5);
    }
}