package model;

public class TablePage {
  private Disk base;
  private int[] pages;
  
  private TablePage(TablePage tpc) {
    this.pages = new Page[tpc.pages.length];
    for (int i = 0; i < tpc.pages.length; i++) {
      this.pages[i] = tpc.pages[i];
    }
  }
  
  public TablePage generateShadow(void) {
    TablePage tps = new TablePage(this);
    return tps;
  }
  
  
}