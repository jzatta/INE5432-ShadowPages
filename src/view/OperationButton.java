package view;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import control.*;
import model.*;

public class OperationButton implements ActionListener {
  private ButtonGroup buttonGroup;
  private JTextField textId;
  private JTextField textValue;
  private TablesManager tm;
  private static Transaction t = new Transaction();
  
  public OperationButton(ButtonGroup buttons, TablesManager tm) {
    buttonGroup = buttons;
    this.tm = tm;
    tm.refreshGUI();
  }
  
  public void setTextBoxes(JTextField id, JTextField value) {
    textId = id;
    textValue = value;
  }
  
  public void actionPerformed(ActionEvent a) {
    String button = buttonGroup.getSelection().getActionCommand();
    String idT = textId.getText();
    String valueT = textValue.getText();
    java.awt.Toolkit.getDefaultToolkit().beep();
    int id, value;
    if (button == "Start") {
      try {
        tm.start(t);
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "");
      }
    }
    if (button == "Update") {
      try {
        id = Integer.valueOf(idT);
        value = Integer.valueOf(valueT);
        tm.update(t, id, value);
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Insert a valid number in id and value");
      }
    }
    else if (button == "Insert") {
      try {
        value = Integer.valueOf(valueT);
        tm.insert(t, value);
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Insert a valid number value");
      }
    }
    else if (button == "Commit") {
      try {
        tm.commit(t);
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "");
      }
    }
    else if (button == "Abort") {
      try {
        tm.abort(t);
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "");
      }
    }
    else if (button == "Crash") {
      try {
        tm.crash();
      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "");
      }
    }
    tm.refreshGUI();
  }
}
