
class IntervalTree {
	private int[] st, A, lazy;
	private int n;
	private int left(int p) { return p << 1;}
	private int right(int p) { return (p << 1) +1;}
	
	public void init(int p, int L, int R) {
		if (L == R) {
			st[p] = L;
		} else {
			init(left(p), L, (L+R) >> 1);
			init(right(p), ((L+R) >> 1)+1, R);
			if (A[left(p)] <= A[right(p)]) st[p] = left(p);
			else st[p] = right(p);
		}
	}
	
	public int rmq(int p, int L, int R, int i, int j) {
		if( (i>R) || (j<L) ) return -1;
		if( (L>=i) && (R<=j)) return st[p];
		int p1 = rmq(left(p), L, (L+R) >> 1, i, j);
		int p2 = rmq(right(p), ((L+R) >> 1)+1, R, i, j);
		if (A[p1] <= A[p2]) return p1; else return p2;
	}
	
	public void updateRange(int p, int L, int R, int i, int j, int newValue) {
		if (lazy[p] != 0) {
			st[p] += lazy[p];
				if (L != R) {
					lazy[left(p)] += lazy[p];
					lazy[right(p)] += lazy[p];
				}
				lazy[p] = 0;
		}
		if (L>j || R<i) return;
		if (L>=i && R<=j) {
			st[p] += newValue;
			if (L != R) {
				lazy[left(p)] += newValue;
				lazy[right(p)] += newValue;
			}
		}
	}
}
