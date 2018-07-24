import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
	
    public static void main(String[] args) {
    	HashMap<String, HashMap<String, ArrayList<Integer>>> map = new HashMap<String, HashMap<String, ArrayList<Integer>>>();
    	HashMap<String, HashMap<String, ArrayList<Integer>>> map_count = new HashMap<String, HashMap<String, ArrayList<Integer>>>();
        String csvFile = "pollution_us_2000_2016.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
    	PrintWriter out;
        try {

            br = new BufferedReader(new FileReader(csvFile));
    		out = new PrintWriter("uspollution.csv");
            br.readLine();
            out.println("Year,State,NO2,O3,SO2,CO");
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] a = line.split(cvsSplitBy);
                HashMap<String, ArrayList<Integer>> hm = new HashMap<String, ArrayList<Integer>>();
                HashMap<String, ArrayList<Integer>> hm2 = new HashMap<String, ArrayList<Integer>>();
                if(map.containsKey(a[8].split("-")[0])) {
                	hm = map.get(a[8].split("-")[0]);
                	hm2 = map_count.get(a[8].split("-")[0]);
                }
                ArrayList<Integer> array = new ArrayList<Integer>();
                array.add(0);
                array.add(0);
                array.add(0);
                array.add(0);
                ArrayList<Integer> c = new ArrayList<Integer>();
                c.add(0);
                c.add(0);
                c.add(0);
                c.add(0);
                if(hm.containsKey(a[5])) {
                	array = hm.get(a[5]);
                	c = hm2.get(a[5]);
                }
                array.set(0, array.get(0)+Integer.parseInt(a[13]));
                c.set(0, c.get(0)+1);
                array.set(0, array.get(1)+Integer.parseInt(a[18]));
                c.set(1, c.get(1)+1);
                array.set(0, array.get(2)+Integer.parseInt(a[23]));
                c.set(2, c.get(2)+1);
                array.set(0, array.get(3)+Integer.parseInt(a[28]));
                c.set(3, c.get(3)+1);
                hm.put(a[5], array);
                hm2.put(a[5], c);
                map.put(a[8].split("-")[0], hm);
                map_count.put(a[8].split("-")[0], hm2);
            }
            for(Map.Entry<String, HashMap<String, ArrayList<Integer>>> entry : map.entrySet()) {
            	String key = entry.getKey();
            	HashMap<String, ArrayList<Integer>> aa = entry.getValue();
            	HashMap<String, ArrayList<Integer>> bb = map_count.get(key);
            	for(Map.Entry<String, ArrayList<Integer>> ee : aa){
 
            	}
            }
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}