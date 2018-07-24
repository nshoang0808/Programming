import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author nshoa
 *
 */
public class RockPaperScissors {
	Scanner in;
	int K = 8;
	int N = 1;
	int[] P_count;
	float[] P_i;
	int[] count;
	ArrayList<Integer> history;

	/**
	 * Return the decimal representation of a number in base-3,
	 * which represented by an array with value in each element is either 0, 1, or 2
	 * @param arr
	 * @return
	 */
	int toDecimal(ArrayList<Integer> arr) {
		int exp3 = 1;
		int res = 0;
		for (int i=0; i<=K; i++) {
			res += exp3*arr.get(i);
			exp3 *= 3;
		}
		return res;
	}
	
	/**
	 * @param k
	 */
	void solve(int k) {
		K = k;
		in = new Scanner(System.in);
		init();
		for(int turn=1; turn<=10; turn++) {
			int move = 0;
			if (turn<=K+1) {
				move = makeMove(P_i[0], P_i[1], P_i[2]);
			} else {
				float c_rock = getCount(0);
				float c_paper = getCount(1);
				float c_scissors = getCount(2);
				float total = c_rock + c_paper + c_scissors;
				move = makeMove(c_rock*P_i[0]/total, c_paper*P_i[1]/total, c_scissors*P_i[2]/total);
			}
			System.out.println(move);
			int opp_move = in.nextInt();
			update(opp_move);
		}
		in.close();
	}
	
	/**
	 * Returns the count of P(I|In = move)
	 * @param move: our next move
	 * @return
	 */
	private float getCount(int move) {
		history.add(move);
		//the history array contains a list of moves I(n-k -> n), with history[i] = 0, 1, or 2
		//the array is encoded as base-3 number to store values in array of 3^(k+1) easier
		//e.g. history = (1, 0, 1, 2) (move 2 is the latest move) => index = 2101 in base-3 = 1*3^0+0*3^1+1*3^2+1*3^3 = 64
		float c = (float)P_count[toDecimal(history)]+0.1f;
		history.remove(history.size()-1);
		return c;
	}

	/**
	 * Returns the best moves based on given probabilities
	 * by calculating each utility and find the maximum utility.
	 * @param p_rock
	 * @param p_paper
	 * @param p_scissors
	 * @return
	 */
	private int makeMove(float p_rock, float p_paper, float p_scissors) {
		float u_paper = p_rock-p_scissors;
		float u_rock = p_scissors-p_paper;
		float u_scissors = p_paper-p_rock;
		if (u_paper < u_rock) {
			if (u_rock < u_scissors) return 2;
			if (u_rock > u_scissors) return 0;
			double rand = Math.random()*2;			//utility of scissors == utility of rock, so random between them
			if (rand>1) return 2;
			return 0;
		} else if (u_paper > u_rock){
			if (u_paper < u_scissors) return 2; 
			if (u_paper > u_scissors) return 1;
			double rand = Math.random()*2;			//utility of scissors == utility of paper, so random between them
			if (rand>1) return 2;
			return 1;
		} else {
			if (u_paper < u_scissors) return 2;
			if (u_paper > u_scissors) {
				double rand = Math.random()*2;		//utility of rock == utility of paper, so random between them
				if (rand>1) return 1;
				return 0;
			}
			double rand = Math.random()*3;			//utility of scissors == utility of paper == utility of rock, so random between them
			if (rand>2) return 2;
			if (rand>1) return 1;
			return 0;
		}
	}

	/**
	 * Initialize all values in the games
	 */
	private void init() {
		//Initialize the problem with K, and an array of 3^(k+1) elements, 
		//represented by base-3 index number
		for (int i=0; i<=K; i++) N *= 3;	//N = 3^(K+1);
		P_i = new float[3]; 
		P_count = new int[N];
		count = new int[3];
		history = new ArrayList<Integer>();
	}

	/**
	 * 
	 * @param opp_move opponent's move this turn
	 */
	private void update(int opp_move) {
		count[opp_move]++;
		int num_move=0;
		for(int i=0; i<3; i++) num_move += count[i];
		for(int i=0; i<3; i++) P_i[i] = ((float)count[i]+0.1f)/((float)num_move+0.3f);
		history.add(opp_move);
		if (history.size()>K) {
			int index = toDecimal(history);
			P_count[index]++;
			history.remove(0);
		}
	}

	public static void main(String[] args) {
		new RockPaperScissors().solve(Integer.parseInt(args[0]));
	}

}
