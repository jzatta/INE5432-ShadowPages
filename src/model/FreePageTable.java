package model;

import java.util.LinkedList;

public class FreePageTable {
  private LinkedList<Integer> freePages;
  
  protected FreePageTable(int maxPages) {
    freePages = new LinkedList<Integer>();
    // At creation dont have any used page, so set all as free page
    for (int i = 0; i < maxPages; i++) {
        freePages.add(new Integer(i));
    }
  }
  
  protected int getPage() {
    return freePages.remove().intValue();
  }
  
  protected void addPage(Page p) {
    freePages.add(new Integer(p.dbIndex()));
  }
}
