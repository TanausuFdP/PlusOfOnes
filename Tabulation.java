public class Tabulation {
	private static int[][] up, down, left, right;
	
	private static int min(int a, int b, int c, int d) {
		int res = a;
		if(res > b) res = b;
		if(res > c) res = c;
		if(res > d) res = d;
		return res;
	}
	
	public static int find(int[][] mat) {
		int res = 0;
		int n= mat.length;
		up = new int[n][n];
		down = new int[n][n];
		left = new int[n][n];
		right = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			up[0][i] = mat[0][i];
			down[n-1][i] = mat[n-1][i];
			left[i][0] = mat[i][0];
			right[i][n-1]=mat[i][n-1];
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 1; j < n; j++) {
				if(mat[i][j] == 1) 
					left[i][j] = left[i][j-1] + 1;
				if(mat[j][i] == 1)
					up[j][i] = up[j-1][i] + 1;
				if(mat[n-1-j][i] == 1)
					down[n-j-1][i] = down[n-j][i] + 1;
				if(mat[i][n-1-j] == 1)
					right[i][n-j-1] = right[i][n-j] + 1;
			}
		}
		
		int aux = 0;
		for(int i = 0; i < mat.length; i++) {
			for(int j = 0; j < mat[i].length; j++){
				aux = (min(up[i][j], down[i][j], right[i][j], left[i][j])-1)*mat[i][j]*4+mat[i][j];
				if(aux > res) {
					res = aux;
				}
			}
		}
		return res;
	}
}
