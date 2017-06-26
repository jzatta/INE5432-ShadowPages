
import model.*;
import control.*;
import java.awt.EventQueue;
import view.*;

public class Main {
  private static TablesManager tm;
  public static void main(String args[]) {
//     new Runnable() {
//       public void run() {
//         while (true)
//         tm.refreshGUI();
//       }
//     };
    gui(args);
    console(args);
  }
  
  public static void console(String args[]) {
    tm = new TablesManager();
    Transaction t = new Transaction();
    print(tm);
    tm.start(t);
    print(tm);
    tm.insert(t, 77);
    print(tm);
    tm.commit(t);
    print(tm);
    tm.start(t);
    print(tm);
    tm.update(t, 0, 10);
    print(tm);
    //     tm.commit(t);
    tm.abort(t);
    print(tm);
    tm.start(t);
    print(tm);
    tm.insert(t, 22);
    print(tm);
    tm.update(t, 0, 12);
    print(tm);
    tm.commit(t);
//     tm.abort(t);
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
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
  
  private static void print(TablesManager tm) {
    tm.refreshGUI();
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.println(tm.toString());
    System.out.println("Press Any Key To Continue...");
    new java.util.Scanner(System.in).nextLine();
  }
}
