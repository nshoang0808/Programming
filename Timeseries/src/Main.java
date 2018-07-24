import java.util.Comparator;
import java.util.Hashtable;
import java.util.Scanner;


public class Main {
	String[] interval, list;
	Hashtable<String, Integer> engagement = new Hashtable<String, Integer>();
	
    class DataPoint implements Comparator<DataPoint> {
        public int year, month, num;
        public String type;
        public DataPoint (String date, String type, int num) {
            this.year = Integer.parseInt(date.substring(0, 4));
            this.month = Integer.parseInt(date.substring(5, 2));
            this.type = type;
            this.num = num;
        }
        public int compare(DataPoint a, DataPoint b) {
            if (a.year<b.year) return -1;
            if (a.year>b.year) return 1;
            if (a.month<b.month) return -1;
            if (a.month>b.month) return 1;
            if (engagement.get())
            return 0;
        }
    }
    public void readInput() {
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        interval = line.split(", ");
    }
    
    public void solve() {
        
    }
    
    public void writeOutput() {
        
    }
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        readInput();
        solve();
        writeOutput();
    }
}
