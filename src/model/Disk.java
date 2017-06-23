package model;

import model.*;

public class Disk {
  private Page pages[];
  
  public Disk(int maxPages) {
    pages = new Page[maxPages];
  }
  
  public Page getPage(int index) {
    return pages[index];
  }
}