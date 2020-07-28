public class Main {

	public static void main(String[] args) {
		Control control = new Control(args);
		control.initialize();
		control.parameters();
		control.act();
	}
}