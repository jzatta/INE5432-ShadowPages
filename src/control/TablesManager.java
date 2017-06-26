package control;

import model.*;
import java.util.LinkedList;

public class TablesManager {
  Disk database;
  PageTable tps;
  PageTable tpc;
  Transaction transaction;
  LinkedList<Page> garbageAbort;
  LinkedList<Page> garbageCommit;
  
  public TablesManager() {
    this.database = new Disk(8);
    tps = new PageTable(this.database);
    this.transaction = null;
  }
  
  public boolean start(Transaction t) {
    if (transaction != null) {
      return false;
    }
    tpc = tps.generateCopy();
    this.transaction = t;
    garbageAbort = new LinkedList<>();
    garbageCommit = new LinkedList<>();
    return true;
  }
  
  public boolean insert(Transaction t, int data) {
    boolean state;
    Page p;
    if (this.transaction != t) {
      return false;
    }
    p = database.getFreePage();
    p.updateData(data);
    state = tpc.newPage(p);
    // If cannot insert the tpc put page in tpl
    if (!state) {
      database.addPage(p);
    } else {
      garbageAbort.add(p);
    }
    return state;
  }
  
  public boolean update(Transaction t, int id, int data) {
    boolean state;
    Page newPage, oldPage;
    if (this.transaction != t) {
      return false;
    }
    newPage = database.getFreePage();
    oldPage = tpc.getPage(id);
    tpc.copyPage(newPage, oldPage);
    state = tpc.updatePage(newPage);
    // If cannot update the tpc put page in tpl
    if (!state) {
      database.addPage(newPage);
    } else {
      garbageAbort.add(newPage);
      garbageCommit.add(oldPage);
    }
    return state;
  }
  
  public boolean delete(Transaction t, int id) {
    boolean state;
    Page delPage;
    if (this.transaction != t) {
      return false;
    }
    delPage = tpc.getPage(id);
    state = tpc.deletePage(delPage);
    // If page was sucessful deleted delete at commit
    if (state) {
      garbageCommit.add(delPage);
    }
    return state;
  }
  
  public Page select(Transaction t, int id) {
    boolean state;
    Page p;
    if (this.transaction == null) {
      return tps.getPage(id);
    }
    if (this.transaction != t) {
      return null;
    }
    return tpc.getPage(id);
  }
  
  public boolean commit(Transaction t) {
    if (this.transaction != t) {
      return false;
    }
    tps = tpc;
    for (Page p: garbageCommit) {
      database.addPage(p);
    }
    transaction = null;
    return true;
  }
  
  public boolean abort(Transaction t) {
    if (this.transaction != t) {
      return false;
    }
    for (Page p: garbageAbort) {
      database.addPage(p);
    }
    tpc = null;
    transaction = null;
    return true;
  }
  
  public boolean checkRI() {
    return true;
  }
  
  public String toString() {
    String ret = database.toString(); 
    ret += "\nTPS:\n";
      ret += tps.toString();
    if (transaction == null) {
    } else {
      ret += "\nTPC:\n";
      ret += tpc.toString();
    }
    return ret;
  }
}