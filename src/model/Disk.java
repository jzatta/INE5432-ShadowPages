package model;

public class Disk {
  private Page pages[];
  private FreePageTable tpl;
  
  public static final int tuplePerPage = 1;
  
  public Disk(int maxPages) {
    pages = new Page[maxPages];
    for (int i = 0; i < pages.length; i++) {
      pages[i] = new Page(tuplePerPage, i);
    }
    tpl = new FreePageTable(maxPages);
  }
  
  public Page getPage(int index) {
    return pages[index];
  }
  
  public int getSize() {
    return pages.length;
  }
  
  public Page getFreePage() {
    return this.pages[tpl.getPage()];
  }
  
  public void addPage(Page p) {
    p.setTableIndex(-1);
    tpl.addPage(p);
  }
  
  public String toString() {
    String ret = "Disk Content:\n";
    for (Page p: pages) {
      ret += "DBId: " + p.dbIndex() + " \tTableId: " + p.getTableIndex() + "\tData: " + p.selectData(0) + "\n";
    }
    return ret;
  }
}
