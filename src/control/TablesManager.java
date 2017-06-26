package control;

import model.*;
import java.util.LinkedList;
import view.*;

public class TablesManager {
  Disk database;
  PageTable tps;
  Transaction transaction;
  PageTable tpc;
  LinkedList<Page> garbageAbort;
  LinkedList<Page> garbageCommit;
  boolean systemCrash;
  
  public TablesManager() {
    this.database = new Disk(8);
    tpc = new PageTable(this.database);
    this.transaction = null;
    systemCrash = false;
    tps = null;
  }
  
  public void refreshGUI() {
    Mainpage.ImThis.disco.clear();
    Mainpage.ImThis.TPS.clear();
    Mainpage.ImThis.TPC.clear();
    for (int i=0; i < database.getSize(); i++) {
      Mainpage.ImThis.disco.add(i, database.getPage(i));
    }
    if (tps != null) {
      for (int i=0; i < tps.maxPages(); i++) {
        Mainpage.ImThis.TPS.add(i, tps.getPage(i));
      }
    }
    for (int i=0; i < tpc.maxPages(); i++) {
      Mainpage.ImThis.TPC.add(i, tpc.getPage(i));
    }
//     if (Mainpage.ImThis != null) {
//       Mainpage.ImThis.frmShadowPages.update(null);
//     }
  }
  
  public boolean start(Transaction t) {
    if (systemCrash) {
      restore();
    }
    if (transaction != null) {
      return false;
    }
    tps = tpc.generateCopy();
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
    if (id < 0) {
      insert(t, data);
    }
    if (this.transaction != t) {
      return false;
    }
    oldPage = tpc.getPage(id);
    newPage = tpc.getPage(id);
    if (oldPage == newPage) {
      newPage = database.getFreePage();
      PageTable.copyPage(newPage, oldPage);
      newPage.updateData(data);
      state = tpc.updatePage(newPage);
      if (!state) {
        // If cannot update the tpc put page in tpl
        database.addPage(newPage);
      } else {
        garbageAbort.add(newPage);
        garbageCommit.add(oldPage);
      }
      return state;
    } else {
      newPage.updateData(data);
      return true;
    }
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
      return tpc.getPage(id);
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
    
    // A lazy approach to garbage colector
    Page qwerty;
    for (int i=0; i < tps.maxPages(); i++) {
      qwerty = tps.getPage(i);
      if (qwerty != null)
        qwerty.setUsed(false);
    }
    tps = null;
    for (int i=0; i < tpc.maxPages(); i++) {
      qwerty = tpc.getPage(i);
      if (qwerty != null)
        qwerty.setUsed(false);
    }
    
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
    tpc = tps;
    tps = null;
    transaction = null;
    return true;
  }
  
  public void crash() {
    tpc = null;
    transaction = null;
    garbageAbort = null;
    garbageCommit = null;
    systemCrash = true;
  }
  
  public void restore() {
    Page p;
    tpc = tps;
    try {
      while (true)
        database.getFreePage();
    } catch (Exception e) {}
    for (int i=0; i < database.getSize(); i++) {
      p = database.getPage(i);
      if (!p.isUsed()) {
        database.addPage(p);
      }
    }
    systemCrash = false;
  }
  
  public boolean checkRI() {
    return true;
  }
  
  public String toString() {
    String ret = database.toString(); 
    if (transaction == null) {
    } else {
      ret += "\nTPS:\n";
      ret += tps.toString();
    }
    ret += "\nTPC:\n";
    ret += tpc.toString();
    return ret;
  }
}