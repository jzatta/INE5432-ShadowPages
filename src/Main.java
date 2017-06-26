
import model.*;
import control.*;

public class Main {
  public static void main(String args[]) {
    TablesManager tm = new TablesManager();
    Transaction t = new Transaction();
    tm.start(t);
    tm.insert(t, 77);
    tm.commit(t);
    System.out.println(tm.toString());
  }
}
