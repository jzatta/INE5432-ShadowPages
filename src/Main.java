
import model.*;

public class Main {
  public static void main(String args[]) {
    Disk d = new Disk(5);
    Page p = d.getPage(1);
    p.selectData(0);
  }
}
