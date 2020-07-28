public class Iterator {

    final int[][] elements;
    final int i, j;
    int up, down, left, right;

    public Iterator(int[][] elements, int i, int j) {
        this.elements = elements;
        this.i = i;
        this.j = j;
        this.up = i-1;
        this.down = i+1;
        this.left = j-1;
        this.right = j+1;
    }
    
    public boolean hasNext() {
        if(down >= elements.length || up < 0 || right >= elements[i].length
        		|| left < 0) return false;
        return true;
    }

    public int[] next() {
        int[] res = new int[4];
        res[0] = elements[up][j];
        res[1] = elements[down][j];
        res[2] = elements[i][right];
        res[3] = elements[i][left];
        up--;
        down++;
        left--;
        right++;
        return res;
    }
}