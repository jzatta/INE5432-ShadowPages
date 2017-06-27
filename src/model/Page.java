package model;

public class Page {
  private int data[];
  private final int indexInDB;
  private int indexInTable;
  private boolean used;
  
  // Tuple per page isnt really necessary
  protected Page(int tuplePerPage, int dbIndex) {
    data = new int[tuplePerPage];
    indexInDB = dbIndex;
    indexInTable = -1;
    used = false;
  }
  
  // Add in first tuple, useful when have only one tuple per page
  // Should be removed in future
  @Deprecated
  public void updateData(int t) {
    data[0] = t;
  }
  
  public void updateData(int t[]) {
    if (t.length == data.length) {
      // Maybe throw and exception
      return;
    }
    for (int i = 0; i < data.length; i++) {
      data[i] = t[i];
    }
  }
  
  public void updateData(int index, int t) {
    data[index] = t;
  }
  
  public int selectData(int index) {
    return data[index];
  }
  
  protected void copyData(Page input) {
    this.updateData(input.data);
    setTableIndex(input.getTableIndex());
  }
  
  protected int dbIndex() {
    return indexInDB;
  }
  
  protected int getTableIndex() {
    return indexInTable;
  }
  
  protected void setTableIndex(int i) {
    indexInTable = i;
  }
  
  public boolean isUsed() {
    return used;
  }
  
  public void setUsed(boolean b) {
    used = b;
  }
  
  public String toString() {
    boolean used = indexInTable >= 0;
    return "Page: " + indexInDB + " Stored value: " + data[0] + " isUsed: " + used;
  }
}
