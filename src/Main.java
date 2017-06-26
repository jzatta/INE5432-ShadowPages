
import model.*;
import control.*;

public class Main {
  public static void main(String args[]) {
    TablesManager tm = new TablesManager();
    Transaction t = new Transaction();
    tm.start(t);
    tm.insert(t, 77);
    tm.commit(t);
    print(tm);
    tm.start(t);
    tm.update(t, 0, 10);
    print(tm);
    tm.commit(t);
//     tm.abort(t);
    print(tm);
  }
  
  private static void print(TablesManager tm) {
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.println(tm.toString());
    System.out.println("Press Any Key To Continue...");
    new java.util.Scanner(System.in).nextLine();
  }
}
