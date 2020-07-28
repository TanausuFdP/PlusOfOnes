import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Control {
	String[] args;
	boolean bool_f = false;
	boolean bool_n = false;
	boolean bool_fr = false;
	boolean bool_fi = false;
	boolean bool_br = false;
	boolean bool_bi = false;
	boolean bool_m = false;
	boolean bool_t = false;
	boolean bool_dt = false;
	boolean bool_di = false;
	boolean bool_do = false;
	int number = -1;
	int sum = -1;
	String filename = "";
	int[][] arr;
	
	public Control(String[] args) {
		this.args = args;
	}
	
	public void initialize() {
		if(args.length < 1) showError("Incorrect number of arguments");
		if(args[0].equals("-h")){
			System.out.println("Analysis of algorithms");
			System.out.println("-----------------------");
			System.out.println("Input Switches");
			System.out.println("-f filename: Input data from file");
			System.out.println("-n number: Matrix's length");
			System.out.println("Algorithms:");
			System.out.println("-fr : Brute Force recursive");
			System.out.println("-fi : Brute Force iterative");
			System.out.println("-br : Bakctracking recursive");
			System.out.println("-bi : Backtracking iterative");
			System.out.println("-m : Memoization");
			System.out.println("-t : Tabulation");
			System.out.println("Display Switches");
			System.out.println("-dt : Display time in seconds");
			System.out.println("-di : Display input");
			System.out.println("-do : Display output");
			System.exit(0);
		}
		for(String str : args) {
			if(str.equals("-f")) bool_f = true;
			else if(str.equals("-n")) bool_n = true;
			else if(str.equals("-fr")) bool_fr = true;
			else if(str.equals("-fi")) bool_fi = true;
			else if(str.equals("-br")) bool_br = true;
			else if(str.equals("-bi")) bool_bi = true;
			else if(str.equals("-m")) bool_m = true;
			else if(str.equals("-t")) bool_t = true;
			else if(str.equals("-dt")) bool_dt = true;
			else if(str.equals("-di")) bool_di = true;
			else if(str.equals("-do")) bool_do = true;
			else {
				if(bool_f && filename.length() == 0) filename = str;
				else if(bool_n && number == -1) number = Integer.valueOf(str);
				else showError("Incorrect argument: " + str);
			}
		}
	}
	
	public void parameters() {
		if(!bool_fr && !bool_fi && !bool_br && !bool_bi && !bool_m && !bool_t)
			showError("Must select an algorithm");
		if(!bool_f)showError("Must select the -f option");
		if(filename.length() == 0) showError("No filename passed");
		if(bool_n && number < 0) showError("Incorrect number or no number passed: " + number);
	}
	
	public void act() {
		File file = new File(filename);
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			if(!bool_n) number = Integer.valueOf(br.readLine());
			else br.readLine();
			arr = new int[number][number];
			String line;
			int i = 0;
			int j = 0;
			while(i < arr.length) {
				while( j < arr.length && (line = br.readLine()) != null) {
					if (bool_di) System.out.print(line + " ");
					arr[i][j] = Integer.valueOf(line);
					j++;
				}
				if(bool_di) System.out.println();
				i++;
				j=0;
			}
			br.close();
			if (bool_fr) BFR();
			if (bool_fi) BFI();
			if (bool_br) BR();
			if (bool_bi) BI();
			if (bool_m) M();
			if (bool_t) T();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR Main:: main (FileNotFoundException)" + e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("ERROR Main:: main (NumberFormatException)" + e.getMessage());
		} catch (IOException e) {
			System.out.println("ERROR Main:: main (IOException)" + e.getMessage());
		}
	}
	
	private void BFR() {
		int result;
		double inicio = 0;
		double total = 0;
		if (bool_dt) {
			inicio = System.currentTimeMillis();
		}
		result = BruteForceRecursive.find(arr);
		if(bool_do) System.out.println("BFR result: " + result);
		if(bool_dt) {
			total = (System.currentTimeMillis() - inicio);
			System.out.println("Brute Force Recursive: " + String.format("%.3f", total/1000.) + " seconds");
		}
	}
	
	private void BFI() {
		int result;
		double inicio = 0;
		double total = 0;
		if (bool_dt) {
			inicio = System.currentTimeMillis();
		}
		result = BruteForceIterative.find(arr);
		if(bool_do) System.out.println("BFI result: " + result);
		if(bool_dt) {
			total = (System.currentTimeMillis() - inicio);
			System.out.println("Brute Force Iterative: " + String.format("%.3f", total/1000.) + " seconds");
		}
	}
	
	private void BR() {
		int result;
		double inicio = 0;
		double total = 0;
		if (bool_dt) {
			inicio = System.currentTimeMillis();
		}
		result = BacktrackingRecursive.find(arr);
		if(bool_do) System.out.println("BR result: " + result);
		if(bool_dt) {
			total = (System.currentTimeMillis() - inicio);
			System.out.println("Backtracking Recursive: " + String.format("%.3f", total/1000.) + " seconds");
		}
	}
	
	private void BI() {
		int result;
		double inicio = 0;
		double total = 0;
		if (bool_dt) {
			inicio = System.currentTimeMillis();
		}
		result = BacktrackingIterative.find(arr);
		if(bool_do) System.out.println("BI result: " + result);
		if(bool_dt) {
			total = (System.currentTimeMillis() - inicio);
			System.out.println("Backtracking Iterative: " + String.format("%.3f", total/1000.) + " seconds");
		}
	}
	
	private void M() {
		int result;
		Memoization mem = new Memoization();
		double inicio = 0;
		double total = 0;
		if (bool_dt) {
			inicio = System.currentTimeMillis();
		}
		result = mem.find(arr);
		if(bool_do) System.out.println("M result: " + result);
		if(bool_dt) {
			total = (System.currentTimeMillis() - inicio);
			System.out.println("Memoization: " + String.format("%.3f", total/1000.) + " seconds");
		}
	}
	
	private void T() {
		int result;
		double inicio = 0;
		double total = 0;
		if (bool_dt) {
			inicio = System.currentTimeMillis();
		}
		result = Tabulation.find(arr);
		if(bool_do) System.out.println("T result: " + result);
		if(bool_dt) {
			total = (System.currentTimeMillis() - inicio);
			System.out.println("Tabulation: " + String.format("%.3f", total/1000.) + " seconds");
		}
	}
	
	private static void showError(String error) {
		System.out.println("ERROR: " +  error);
		System.out.println("Help: #java -jar Main.jar -h");
		System.exit(1);
	}
}
