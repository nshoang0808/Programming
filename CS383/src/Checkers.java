import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Checkers {
	public static final int INF = 1000000000;
	public static final int MAXN = 500010;
	public static final int[] dx_king = {1, 1, -1, -1};
	public static final int[] dy_king = {1, -1, 1, -1};
	public static final int[] dx = {-1, -1};
	public static final int[] dy = {-1, 1};
	
	FastScanner in;
	PrintWriter out;
	int[][] initialBoard;
	ArrayList<int[][]> nextBoards;
	ArrayList<Integer> jumpPos;
	
	class Pair {
		String s;		//Represent the move
		int val;
		public Pair(String move, int val) {
			this.s = move;
			this.val = val;
		}
	}
	
	/**
	 * Initialize the board
	 */
	void init() {
		initialBoard = new int[8][8];
		for(int i=0; i<8; i++) {
			String s = in.next();
			if (s.charAt(0) == ',') {
				initialBoard[i][0] = -1;
			} else {
				initialBoard[i][0] = Integer.parseInt(String.valueOf(s.charAt(0)));
			}
			int id = 1;
			for(int j=1; j<s.length(); j++) {
				char c = s.charAt(j);
				if (c == ',') {
					if (s.charAt(j-1) == ',') {
						initialBoard[i][id] = -1;
						id++;
					}
				} else {
					initialBoard[i][id] = Integer.parseInt(String.valueOf(c));
					id++;
				}
			}
		}
	}
	int toPos(int x, int y) {
		return x*4+y/2+1;
	}
	
	boolean jump(int[][] board, int i, int j, int player, int ct) {
		boolean hasJump = false;
		//If this is the king piece of current player
		if ((board[i][j]+1)/2 > player) {
			for(int dir=0; dir<4; dir++) {
				int x = i+dx_king[dir];
				int y = j+dy_king[dir];
				if (!inBoard(x,y)) continue;
				if (board[x][y] >= 0 && board[x][y]/2 == 1-player && inBoard(x+dx_king[dir], y+dy_king[dir]) && board[x+dx_king[dir]][y+dy_king[dir]] < 0) {
					int[][] nextBoard = same(board);
					hasJump = true;
					nextBoard[x+dx_king[dir]][y+dy_king[dir]] = nextBoard[i][j];
					nextBoard[i][j] = -1;
					nextBoard[x][y] = -1;
					jump(nextBoard, x+dx_king[dir], y+dy_king[dir], player, ct);
				}
			}
		} else {		//just a normal piece of current player
			for(int dir=0; dir<2; dir++) {
				int x = i+dx[dir]*ct;
				int y = j+dy[dir]*ct;
				if (!inBoard(x,y)) continue;
				if (board[x][y] >= 0 && board[x][y]/2 == 1-player && inBoard(x+dx[dir]*ct, y+dy[dir]*ct) && board[x+dx[dir]*ct][y+dy[dir]*ct] < 0) {
					int[][] nextBoard = same(board);
					hasJump = true;
					nextBoard[x+dx[dir]*ct][y+dy[dir]*ct] = nextBoard[i][j];
					nextBoard[i][j] = -1;
					nextBoard[x][y] = -1;
					jump(nextBoard, x+dx[dir]*ct, y+dy[dir]*ct, player, ct);
				}
			}
		}
		
		//If there is no jump move from this board, stop and add them to the nextBoard list
		if (!hasJump) {
			nextBoards.add(board);
			jumpPos.add(toPos(i,j));			//Save the final position of the move for printing moves
			return false;
		}
		return true;
	}
	
	/**
	 * Check if the coordinates are within the board
	 * @param x
	 * @param y
	 * @return
	 */
	boolean inBoard(int x, int y) {
		return x>=0 && y>=0 && x<8 && y<8;
	}
	
	boolean lostState(int[][] board, int player, int ct) {
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if (board[i][j] >= 0 && board[i][j]/2 == player) {
//					//If this is the king piece of current player
//					if ((board[i][j]+1)/2 > 1-player) {
//						for(int dir=0; dir<4; dir++) {
//							int x = i+dx_king[dir];
//							int y = j+dy_king[dir];
//							if (!inBoard(x,y)) continue;
//							if (board[x][y] < 0) return false;
////							if (board[x][y] >= 0 && board[x][y]/2 == player && inBoard(x+dx_king[dir], y+dy_king[dir]) && board[x+dx_king[dir]][y+dy_king[dir]] < 0) return false;
//						}
//					} else {		//just a normal piece of current player
//						for(int dir=0; dir<2; dir++) {
//							int x = i-dx[dir]*ct;
//							int y = j-dy[dir]*ct;
//							if (!inBoard(x,y)) continue;
//							if (board[x][y] < 0) return false;
////							if (board[x][y] >= 0 && board[x][y]/2 == player && inBoard(x+dx[dir]*ct, y+dy[dir]*ct) && board[x+dx[dir]*ct][y+dy[dir]*ct] < 0) return false;
//						}
//					}
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param board
	 * @param player
	 * @return
	 */
	Pair minimax(int[][] board, int player, int ct, int count) {
		int curr = -2;
		String moveString = "";
		if (lostState(board, player, ct)) return new Pair("", -ct);
		if (count>9) return new Pair("", ct*5);
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if (board[i][j] >= 0 && board[i][j]/2 == player) {
					nextBoards = new ArrayList<int[][]>();
					jumpPos = new ArrayList<Integer>();
					int[][] tmpBoard = same(board);
					if (jump(tmpBoard, i, j, player, ct)) {
						ArrayList<int[][]> list = new ArrayList<int[][]>(nextBoards);
						ArrayList<Integer> posList = new ArrayList<Integer>(jumpPos);
						for(int id=0; id<list.size(); id++) {
							int[][] nextBoard = list.get(id);
							Pair move = minimax(nextBoard, 1-player, -ct, count+1);
							if (move.val*ct>curr) {
								moveString = String.valueOf(toPos(i, j)) + "x" + String.valueOf(posList.get(id)) + "," +move.s;
								curr = move.val*ct;
							}
							if (curr == 1) return new Pair(moveString, 1);
						}
					} else {
						//If this is the king piece of current player
						if ((board[i][j]+1)/2 > player) {
							for(int dir=0; dir<4; dir++) {
								int x = i+dx_king[dir];
								int y = j+dy_king[dir];
								if (!inBoard(x,y)) continue;
								if (board[x][y] < 0) {
									int[][] nextBoard = same(board);
									nextBoard[x][y] = nextBoard[i][j];
									nextBoard[i][j] = -1;
									Pair move = minimax(nextBoard, 1-player, -ct, count+1);
									if (move.val*ct>curr) {
										moveString = String.valueOf(toPos(i, j)) + "-" + String.valueOf(toPos(x,y)) + "," +move.s;
										curr = move.val*ct;
									}
									if (curr == 1) return new Pair(moveString, 1);
								}
							}
						} else {		//just a normal piece of current player
							for(int dir=0; dir<2; dir++) {
								int x = i+dx[dir]*ct;
								int y = j+dy[dir]*ct;
								if (!inBoard(x,y)) continue;
								if (board[x][y] < 0) {
									int[][] nextBoard = same(board);
									nextBoard[x][y] = nextBoard[i][j];
									nextBoard[i][j] = -1;
									Pair move = minimax(nextBoard, 1-player, -ct, count+1);
									if (move.val*ct>curr) {
										moveString = String.valueOf(toPos(i, j)) + "-" + String.valueOf(toPos(x,y)) + "," +move.s;
										curr = move.val*ct;
									}
									if (curr == 1) return new Pair(moveString, 1);
								}
							}
						}
					}
				}
			}
		}
		return new Pair(moveString, curr);
	}
	private int[][] same(int[][] board) {
		int[][] newBoard = new int[8][8];
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) newBoard[i][j] = board[i][j];
		}
		return newBoard;
	}
	void solve() {
		init();
		Pair result = minimax(initialBoard, 0, 1, 0);
		out.println(result.s.substring(0, result.s.length()-1));
	}
	
	void runIO() {

		in = new FastScanner(System.in);
		out = new PrintWriter(System.out);

		solve();
		
		out.close();
	}

	void run(String filename) {
		in = new FastScanner(new File(filename));
		out = new PrintWriter(System.out);

		solve();

		out.close();
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
		new Checkers().run(args[0]);
	}

}
