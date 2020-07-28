public class BacktrackingRecursive {

	private static int min(int a, int b, int c, int d) {
		int res = a;
		if(res > b) res = b;
		if(res > c) res = c;
		if(res > d) res = d;
		return res;
	}
	
	private static int L(int[][] mat, int i, int j) {
		if(mat[i][j] == 0) return 0;
		
		if(i == 0 || j == 0 || i == mat.length-1 || j == mat[i].length-1) return mat[i][j];
		return L(mat, i, j-1)*mat[i][j] + mat[i][j];
	}
	
	private static int T(int[][] mat, int i, int j) {
		if(mat[i][j] == 0) return 0;
		
		if(i == 0 || j == 0 || i == mat.length-1 || j == mat[i].length-1) return mat[i][j];
		return T(mat, i-1, j)*mat[i][j] + mat[i][j];
	}
	
	private static int B(int[][] mat, int i, int j) {
		if(mat[i][j] == 0) return 0;
		
		if(i == 0 || j == 0 || i == mat.length-1 || j == mat[i].length-1) return mat[i][j];
		return B(mat, i+1, j)*mat[i][j] + mat[i][j];
	}
	
	private static int R(int[][] mat, int i, int j) {
		if(mat[i][j] == 0) return 0;
		
		if(i == 0 || j == 0 || i == mat.length-1 || j == mat[i].length-1) return mat[i][j];
		return R(mat, i, j+1)*mat[i][j] + mat[i][j];
	}
	
	private static int C(int[][] mat, int i, int j) {
		if(mat[i][j] == 0) return 0;
		
		if(i == 0 || j == 0 || i == mat.length-1 || j == mat[i].length-1) return mat[i][j];
		return min(R(mat, i, j+1), B(mat, i+1, j), T(mat, i-1, j), L(mat, i, j-1))*mat[i][j] *4 + mat[i][j];
	}
	
	public static int find(int[][] mat) {
		int n, res = 0;
		for(int i=0; i < mat.length; i++) {
			for(int j=0; j < mat[i].length; j++) {
				n = C(mat, i, j);
				if(res < n) {
					res = n;
				}
			}
		}
		return res;
	}
}
