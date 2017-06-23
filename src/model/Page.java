package model;

public class Page {
  private int data[];
  
  public Page(int tuplePerPage) {
    data = new int[tuplePerPage];
  }
  
  public void updateData(int index, int t) {
    data[index] = t;
  }
  
  public int selectData(int index) {
    return data[index];
  }
}