import java.util.*;
import java.io.*;
import java.lang.*;
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
public class work {
	int left
	public void build_tree()

	public ArrayList<Integer> order(ArrayList<Integer> heights, ArrayList<Integer> infronts) {
		ArrayList<Person> arr = new ArrayList<Person>();
		for (int i=0; i<heights.size(); i++) {
			arr.add(new Person(i, infronts.get(i)));
		}
		Collections.sort(arr);
		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int i=0; i<heights.size(); i++) {
			res.add(heights.get(arr.get(i).id));
		}
		return res;
	}
	public static void main(String[] args) {
		
	}
}