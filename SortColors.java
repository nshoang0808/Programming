import java.util.*;
import java.io.*;

public class SortColors {
	public void sortColors(List<Integer> a) {
		int[] x =  new int[3];
		for (int i: a) {
			x[i]++;
		}
		int j=0;
		for (int i=0; i<3; i++) {
			while (x[i]>0) {
				a.set(j,i);
				j++;
				x[i]--;
			}
		}
	}
	public static void main(String[] args) {

	}
}