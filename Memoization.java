import java.util.HashMap;

public class Memoization {

	HashMap<String, Integer> dictR;
	HashMap<String, Integer> dictL;
	HashMap<String, Integer> dictT; 
	HashMap<String, Integer> dictB;
	
	public Memoization() {
		dictR = new HashMap<>();
		dictL = new HashMap<>();
		dictT = new HashMap<>();
		dictB = new HashMap<>();
	}
	
	private int min(int a, int b, int c, int d) {
		int res = a;
		if(res > b) res = b;
		if(res > c) res = c;
		if(res > d) res = d;
		return res;
	}
	
	private int L(int[][] mat, int i, int j) {
		String key = i + " " + j;
		int result = 0;
		
		if(dictL.containsKey(key)) return dictL.get(key);
		
		if(mat[i][j] == 0) result = 0;
		else if(i == 0 || j == 0 || i == mat.length-1 || j == mat[i].length-1) result = mat[i][j];
		else result = L(mat, i, j-1)*mat[i][j] + mat[i][j];
		
		dictL.put(key, result);
		return result;
	}
	
	private int T(int[][] mat, int i, int j) {
		String key = i + " " + j;
		int result = 0;
		
		if(dictT.containsKey(key)) return dictT.get(key);
		
		if(mat[i][j] == 0) result = 0;
		else if(i == 0 || j == 0 || i == mat.length-1 || j == mat[i].length-1) result = mat[i][j];
		else result = T(mat, i-1, j)*mat[i][j] + mat[i][j];
		
		dictT.put(key, result);
		return result;
	}
	
	private int B(int[][] mat, int i, int j) {
		String key = i + " " + j;
		int result = 0;
		
		if(dictB.containsKey(key)) return dictB.get(key);
		
		if(mat[i][j] == 0) result = 0;
		else if(i == 0 || j == 0 || i == mat.length-1 || j == mat[i].length-1) result = mat[i][j];
		else result = B(mat, i+1, j)*mat[i][j] + mat[i][j];
		
		dictB.put(key, result);
		return result;
	}
	
	private int R(int[][] mat, int i, int j) {
		String key = i + " " + j;
		int result = 0;
		
		if(dictR.containsKey(key)) return dictR.get(key);
		
		if(mat[i][j] == 0) result = 0;
		else if(i == 0 || j == 0 || i == mat.length-1 || j == mat[i].length-1) result = mat[i][j];
		else result = R(mat, i, j+1)*mat[i][j] + mat[i][j];
		
		dictR.put(key, result);
		return result;
	}
	
	private int C(int[][] mat, int i, int j) {
		if(mat[i][j] == 0) return 0;
		
		if(i == 0 || j == 0 || i == mat.length-1 || j == mat[i].length-1) return mat[i][j];
		return min(R(mat, i, j+1), B(mat, i+1, j), T(mat, i-1, j), L(mat, i, j-1))*mat[i][j] *4 + mat[i][j];
	}
	
	public int find(int[][] mat) {
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
