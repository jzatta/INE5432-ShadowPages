package model;

public class Disk {
  private Page pages[];
  
  public static final int tuplePerPage = 1;
  
  public Disk(int maxPages) {
    pages = new Page[maxPages];
    for (int i = 0; i < pages.length; i++) {
      pages[i] = new Page(tuplePerPage);
    }
  }
  
  public Page getPage(int index) {
    return pages[index];
  }
}
