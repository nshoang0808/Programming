import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

public class Converter {
	// Input and output for the program.
	FastScanner in;
	PrintWriter out;
	
	HashMap<String, ArrayList<String>> var;					//maps a variable to its domain represented as an array of string
	HashMap<String, HashMap<String, Integer>> var2;			//maps a variable to its domain represented as another hashmap
															//(which map each value in the domain to its ID)-to access easier later
	String inputFile;
	int n, allDiffN;
	ArrayList<ArrayList<String>> constraintGiven;
	ArrayList<ArrayList<Boolean>> constraintValues;
	
	ArrayList<Boolean> tmp_table;
	
	public Converter(String inputFile) {
		this.inputFile = inputFile;
		var = new HashMap<String, ArrayList<String>>();
		var2 = new HashMap<String, HashMap<String, Integer>>();
		constraintGiven = new ArrayList<ArrayList<String>>();
		constraintValues = new ArrayList<ArrayList<Boolean>>();
	}
	
	/**
	 * Method to draw the final xml file.
	 */
	void draw() {
		out.println("<CSPIF VERSION=\"0.01\">\n<CSP>\n<NAME>Untitled</NAME>\n<DESCRIPTION>\n\t<SHORT></SHORT>\n\t<DETAILED></DETAILED>\n</DESCRIPTION>");
		for(Entry<String, ArrayList<String>> entry: var.entrySet()) {
			out.println("<VARIABLE TYPE=\"String\">");
			out.printf("\t<NAME>%s</NAME>\n", entry.getKey());
			ArrayList<String> list = entry.getValue();
			for(int j=0; j<list.size(); j++) {
				out.printf("\t<VALUE>%s</VALUE>\n", list.get(j));
			}
			out.println("</VARIABLE>");
		}
		int constraintN = constraintGiven.size();
		for(int i=0; i<constraintN; i++) {
			out.println("<CONSTRAINT TYPE=\"Custom\">");
			out.printf("\t<CUSTOMNAME>C%d</CUSTOMNAME>\n", i+1);
			for(int j=0; j<constraintGiven.get(i).size(); j++) {
				out.printf("\t<GIVEN>%s</GIVEN>\n",constraintGiven.get(i).get(j));
			}
			out.println("\t<TABLE>");
			out.print("\t\t");
			for(int j=0; j<constraintValues.get(i).size(); j++) {
				if (constraintValues.get(i).get(j)) out.print("T ");
				else out.print("F ");
			}
			out.println("\n\t</TABLE>");
			out.println("</CONSTRAINT>");
		}
		out.println("</CSP>");
		out.println("</CSPIF>");
	}
	
	/**
	 * Add all required variables with their values to HashMaps.
	 */
	void addVariables() {
		//Number of variables.
		n = Integer.parseInt(in.next().split(",")[0]);
		for(int i=0; i<n; i++) {
			String[] s = in.next().split(",");
			int domain_size = Integer.parseInt(s[1]);
			//the first domain is for the list of domain String values.
			ArrayList<String> domain = new ArrayList<String>();
			//the second domain is for the list of domain hashmap which maps a String value to its index
			HashMap<String, Integer> domain2 = new HashMap<String, Integer>();
			for(int j=0; j<domain_size; j++) {
				domain.add(s[j+2]);
				domain2.put(s[j+2], domain.size()-1);
			}
			var.put(s[0], domain);
			var2.put(s[0], domain2);
		}
	}
	
	/**
	 * Add the all different constraints to all variables specified in the input.
	 */
	void addAllDiffConstraints() {
		//get the number of allDiff groups
		allDiffN = Integer.parseInt(in.next().split(",")[0]);
		for(int i=0; i<allDiffN; i++) {
			//String array s contains all variables that need to be uniquely different
			String[] s = in.next().split(",");
			
			//Add constraints by pairs
			for(int j=0; j<s.length-1; j++) {
				for (int k=j+1; k<s.length; k++) {
					//given array to display to output, including 2 variables
					ArrayList<String> given = new ArrayList<String>();
					given.add(s[j]);
					given.add(s[k]);
					ArrayList<Boolean> table = new ArrayList<Boolean>();
					ArrayList<String> value1 = var.get(s[j]);
					ArrayList<String> value2 = var.get(s[k]);
					
					//true/false table only has false values when both have the same value.
					for(int id1=0; id1<value1.size(); id1++)
						for(int id2=0; id2<value2.size(); id2++) {
							table.add(!value1.get(id1).equals(value2.get(id2)));
						}
					constraintGiven.add(given);
					constraintValues.add(table);
				}
			}
		}
	}
	
	/**
	 * A recursive method to add all values to the true/false table
	 * The number of elements in true/false table = the product of all those variables'domain size.
	 * @param keys: the list of variables
	 * @param keyId: the current recursive index of the array (keys)
	 * @param symbol: a hashmap, mapping a variable to all symbols in its constraints (array of symbols)
	 * @param values: a hashmap, mapping a variable to all values in its constraints, 
	 * 					and the index of values are correlating with the index of symbols array 
	 * @param isValid: a boolean value, to check if the current sentence is satisfied or not
	 * 					(a sentence is a disjunction of literals, which is true when at least one of the literals is true,
	 * 					so after a literal is true, the sentence should remain true regardless of other variables'values)
	 */
	void backtrack(ArrayList<String> keys, int keyId, HashMap<String, ArrayList<Character>> symbol, HashMap<String, ArrayList<String>> values, boolean isValid) {
		String v = keys.get(keyId);						//current variable
		ArrayList<String> local_values = var.get(v);	//all its values in its domain
		ArrayList<String> condition_values = values.get(v);	//all its values in constraint conditions
		ArrayList<Character> condition_operators = symbol.get(v);	//all its symbol in constraint condition
		
		//loop all its values in the domain
		for (int i=0; i<local_values.size(); i++) {
			//reset isValid after recursive call returns by using a temp value to store current isValid's value.
			boolean tmp_isValid = isValid;
			
			String val = local_values.get(i);
			if (!isValid) {
				boolean check = false;
				//check all constraints
				for(int j=0; j<condition_values.size(); j++) {
					//if one constraint has a value that equals to the current value:
					if (val.equals(condition_values.get(j))) {
						check = true;
						//...check for the symbol to see if constraint is satisfied or not
						if (condition_operators.get(j) == '=') isValid = true;
					}
				}
				//if all constraints do not mention this values...
				if (!check) {
					//...and all operators (symbols) are "not equals", then it is satisfied.
					for(int j=0; j<condition_operators.size(); j++) 
						if (condition_operators.get(j) == '/') isValid = true;
				}
			}
			//add values to true/false table at the end of array.
			if (keyId == keys.size()-1) {
				if (isValid) tmp_table.add(true);
				else tmp_table.add(false);
			} else {
				backtrack(keys, keyId+1, symbol, values, isValid);
			}
			isValid = tmp_isValid;
		}
	}
	
	/**
	 * Add all constraints given by input
	 */
	void addAllConstraints() {
		//Number of constraints
		int numCS = Integer.parseInt(in.next().split(",")[0]);
		for(int i=0; i<numCS; i++) {
			//2 hashmaps, symbol and values as mentioned above
			HashMap<String, ArrayList<Character>> symbol = new HashMap<String, ArrayList<Character>>();
			HashMap<String, ArrayList<String>> values = new HashMap<String, ArrayList<String>>();
			String[] s = in.next().split(",");
			for (int j=0; j<s.length; j++) {
				
				//String array (aa) contains 2 side of the constraints: variable and value.
				String[] aa = s[j].split("/|=");
				char sym = (char) s[j].charAt(aa[0].length());
				
				//Add this to the hashmap, where all constraints of a variable is mapped to that variable's name
				if (symbol.containsKey(aa[0])) {
					symbol.get(aa[0]).add(sym);
					values.get(aa[0]).add(aa[1]);
				} else {
					symbol.put(aa[0], new ArrayList<Character>());
					symbol.get(aa[0]).add(sym);
					values.put(aa[0], new ArrayList<String>());
					values.get(aa[0]).add(aa[1]);
				}
			}
			ArrayList<String> given = new ArrayList<String>(symbol.keySet());
			tmp_table = new ArrayList<Boolean>();
			backtrack(given, 0, symbol, values, false);
			ArrayList<Boolean> table = new ArrayList<Boolean>();
			table.addAll(tmp_table);
			constraintGiven.add(given);
			constraintValues.add(table);
			
		}
	}
	/**
	 * Main method, including sub method to add variables, 
	 * allDiff constraints, other constraints and draw to output
	 */
	void solve() {
		addVariables();
		addAllDiffConstraints();
		addAllConstraints();
		draw();
	}

	/**
	 * Method to run IO (standard input/output)
	 */
	void runIO() {

		in = new FastScanner(System.in);
		out = new PrintWriter(System.out);

		solve();

		out.close();
	}

	/**
	 * Method to run on external input/output
	 */
	void run() {
		try {
			in = new FastScanner(new File(inputFile));
			out = new PrintWriter(new File("output.xml"));

			solve();

			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Class designed to read file fast using BufferedReader and StringTokenizer.
	 * @author HoangNguyen
	 *
	 */
	class FastScanner {
		BufferedReader br;
		StringTokenizer st;

		public FastScanner(File f) {
			try {
				br = new BufferedReader(new FileReader(f));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		public FastScanner(InputStream f) {
			br = new BufferedReader(new InputStreamReader(f));
		}

		String next() {
			while (st == null || !st.hasMoreTokens()) {
				String s = null;
				try {
					s = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (s == null)
					return null;
				st = new StringTokenizer(s);
			}
			return st.nextToken();
		}

		boolean hasMoreTokens() {
			while (st == null || !st.hasMoreTokens()) {
				String s = null;
				try {
					s = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (s == null)
					return false;
				st = new StringTokenizer(s);
			}
			return true;
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}
		String nextLine(){
          String str = "";
	  		try {
	     		str = br.readLine();
	  		} catch (IOException e) {
	     		e.printStackTrace();
	  		}
	  		return str;
      	}
	}
	public static void main(String[] args) {
		System.out.println(args[0]);
		new Converter(args[0]).run();
	}

}
