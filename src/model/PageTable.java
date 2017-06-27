package model;

public class PageTable {
  private Disk database;
  private int[] pagesIndex;
  
  /*
   * @param emptyPages: true if is tpl, false otherwise
   */
  public PageTable(Disk database) {
    this.database = database;
      // Max of half size of disk due to we need space to copy uptating pages
    int tableSize = database.getSize()/2;
    pagesIndex = new int[tableSize];
    for (int i = 0; i < pagesIndex.length; i++) {
        pagesIndex[i] = -1;
    }
  }
  
  private PageTable(PageTable tpc) {
    this.database = tpc.database;
    this.pagesIndex = new int[tpc.pagesIndex.length];
    for (int i = 0; i < tpc.pagesIndex.length; i++) {
      this.pagesIndex[i] = tpc.pagesIndex[i];
    }
  }
  
  public int maxPages() {
    return pagesIndex.length;
  }
  
  public PageTable generateCopy() {
    PageTable tps = new PageTable(this);
    return tps;
  }
  
  public Page getPage(int id) {
    int indexInDisk = pagesIndex[id];
    if (indexInDisk < 0) {
      // In case of not allocated page, maybe should throw an exception
      // Doenst have data to this index
      return null;
    }
    return database.getPage(indexInDisk);
  }
  
  public static void copyPage(Page to, Page from) {
    to.copyData(from);
  }
  
  public boolean newPage(Page p) {
    for (int i = 0; i < pagesIndex.length; i++) {
      if (pagesIndex[i] < 0) {
        pagesIndex[i] = p.dbIndex();
        p.setTableIndex(i);
        return true;
      }
    }
    // Database are full
    return false;
  }
  
  public boolean updatePage(Page p) {
    int id = p.getTableIndex();
    if (id < 0) {
      return false;
    }
    pagesIndex[id] = p.dbIndex();
    return true;
  }
  
  public boolean deletePage(Page p) {
    int id = p.getTableIndex();
    if (id < 0) {
      return false;
    }
    pagesIndex[id] = -1;
    return true;
  }
  
  public String toString() {
    String ret = "";
    Page p;
    for (int i = 0; i < pagesIndex.length; i++) {
      ret += i + " --> " + pagesIndex[i];
      if (pagesIndex[i] >= 0) {
        p = database.getPage(pagesIndex[i]);
        ret += " (" + p.selectData(0) + ")";
      }
      if (i % 4 == 3) {
        ret += "\n";
      } else {
        ret += "\t";
      }
    }
    return ret;
  }
  
  public String getPageInfo(int i) {
    String ret = "";
    if (pagesIndex[i] < 0) {
      return ret;
    }
    ret += "Id: " + i;
    ret += " DiskPage: " + pagesIndex[i];
    ret += " Value: " + database.getPage(pagesIndex[i]).selectData(0);
    return ret;
  }
}
