public class BruteForceIterative {
	private static int C(int[][] mat, int i, int j) {
		if(mat[i][j] == 0) return 0;
		
		boolean zero = false;
		int res = 0;
		int[] plus = new int[4];
		Iterator iterator = new Iterator(mat, i, j);
		while(iterator.hasNext()) {
			plus = iterator.next();
			for(int k = 0; k < plus.length; k++) {
				if(plus[k] == 0) zero = true;
			}
			if(!zero) res++;
		}
		return res;
	}
	
	public static int find(int[][] mat) {
		int n, res = 0;
		for(int i=0; i < mat.length; i++) {
			for(int j=0; j < mat[i].length; j++) {
				n = C(mat, i, j);
				if(res < n*4+mat[i][j]) res = n*4+mat[i][j];
			}
		}
		return res;
	}
}
