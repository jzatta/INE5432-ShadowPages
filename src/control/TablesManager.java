package control;

import model.PageTable;
import model.Disk;

public class TablesManager {
  Disk database;
  PageTable tps;
  PageTable tpc;
  PageTable tpl;
  
  public TablesManager() {
    this.database = new Disk(16);
    tpc = new PageTable(this.database);
  }
}