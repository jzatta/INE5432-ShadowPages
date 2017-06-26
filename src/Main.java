
import model.*;
import control.*;

public class Main {
  public static void main(String args[]) {
    gui(args);
    console(args);
  }
  public static void console(String args[]) {
    TablesManager tm = new TablesManager();
    Transaction t = new Transaction();
    tm.start(t);
    tm.insert(t, 77);
    tm.commit(t);
    print(tm);
    tm.start(t);
    tm.update(t, 0, 10);
    print(tm);
//     tm.commit(t);
    tm.abort(t);
    print(tm);
  }
  
  /**
	 * Launch the application.
	 */
	public static void gui(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainpage window = new Mainpage();
					window.frmShadowPages.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
  
  private static void print(TablesManager tm) {
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.println(tm.toString());
    System.out.println("Press Any Key To Continue...");
    new java.util.Scanner(System.in).nextLine();
  }
}
