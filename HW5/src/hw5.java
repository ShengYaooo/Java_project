public class hw5{
	public static void main(String[] args) throws Exception{
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();//清除cmd
		MainApp myapp = new MainApp();
		myapp.Load();
	}
}