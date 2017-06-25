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
  
  protected Page getPage(int index) {
    return pages[index];
  }
  
  public int getSize() {
    return pages.length;
  }
  
  public int getFreePage() {
    return tpl.getPage();
  }
  
  public void addPage(Page p) {
    tpl.addPage(p);
  }
  
  protected int getCopy(int index) {
    int free = this.getFreePage();
    Page copy = getPage(free);
    copy.copyData(getPage(index));
    return free;
  }
}
