import java.io.*;
import java.math.BigInteger;
import java.lang.*;
import java.util.*;

public class Problem1 {
	class Job implements Comparable<Job> {
		public String name;
		public int task, t_time, b_time, prio;
		
		public Job(String name, int task, int t_time, int b_time, int prio) {
			this.name = name;
			this.task = task;
			this.t_time = t_time;
			this.b_time = b_time;
			this.prio 	= prio;
		}
		
		public int compareTo(Job b) {
			if (this.b_time < b.b_time) return -1;
			if (this.b_time > b.b_time) return 1;
			if (this.prio < b.prio) return -1;
			if (this.prio > b.prio) return 1;
			if (this.t_time < b.t_time) return -1;
			if (this.t_time > b.t_time) return 1;
			return 0;
		}
	}
	class Assignment {
		public int time;
		public String worker, job;
		public Assignment(int time, String worker, String job) {
			this.time = time;
			this.worker = worker;
			this.job = job;
		}
	}
	class Worker {
		public String name;
		public int begin, task;
		public Worker(String name, int task, int begin) {
			this.name = name;
			this.task = task;
			this.begin = begin;
		}
		public void setTask(int num) {
			this.task = num;
		}
		public void setBegin(int num) {
			this.begin = num;
		}
	}
	public static final int INF = 1000000000;
	public static final int MAXN = 500010;
	FastScanner in;
	PrintWriter out;
	List<Worker> workers = new ArrayList<Worker>();
	List<Job> jobs = new ArrayList<Job>();
	List<Assignment> result = new ArrayList<Assignment>();
	
	void readFile() {
		while (in.hasMoreTokens()) {
			String typ = in.next();
			if (typ == "job") {
				String name = in.next();
				int task = in.nextInt();
				int t_time = in.nextInt();
				int b_time = in.nextInt();
				int prio = in.nextInt();
				jobs.add(new Job(name, task, t_time, b_time, prio));
			} else {
				workers.add(new Worker(in.next(),-1,-1));
			}
		}
	}
	void solve() {
		readFile();
		Collections.sort(jobs);
		int N = jobs.size();
		int M = workers.size();
		int[] tasks_done = new int[N+1];
		Arrays.fill(tasks_done, 0);
		
		for (int i=0; i<N; i++) {
			Job current = jobs.get(i);
			for (int j=0; j<avail.length; j++) {
				if (avail[j] > -1) {
					tasks_done[avail[j]] = 
				}
			}
			if (M>0) {
				for (int j=0; j<workers.size(); j++) {
					Worker work = workers.get(j);
					if (work.task == -1) {
						result.add(new Assignment(current.b_time, workers.get(j).name, current.name));
						workers.get(j).setTask(i);
						workers.get(j).setBegin()
					}
				}
			}
		}
	}

	void runIO() {

		in = new FastScanner(System.in);
		out = new PrintWriter(System.out);

		solve();

		out.close();
	}

	void run() {
		try {
			in = new FastScanner(new File("test.in"));
			out = new PrintWriter(new File("test.out"));

			solve();

			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

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
		new Problem1().runIO();
	}
}