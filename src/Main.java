
import model.*;

public class Main {
  public static void main(String args[]) {
    Disk d = new Disk(5);
    d.getPage(1).selectData(0);
  }
}