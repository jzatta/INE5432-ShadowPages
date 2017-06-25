package model;

public class PageTable {
  private Disk base;
  private int[] pagesIndex;
  
  private PageTable(PageTable tpc) {
    this.pagesIndex = new int[tpc.pagesIndex.length];
    for (int i = 0; i < tpc.pagesIndex.length; i++) {
      this.pagesIndex[i] = tpc.pagesIndex[i];
    }
  }
  
  public PageTable generateShadow() {
    PageTable tps = new PageTable(this);
    return tps;
  }
}
