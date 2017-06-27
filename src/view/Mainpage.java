package view;


import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import java.awt.event.ActionListener;

import model.*;
import control.*;

public class Mainpage {
  
  public JFrame frmShadowPages;
  public JTextField textFieldId;
  public JTextField textFieldValue;
  public final ButtonGroup buttonGroup = new ButtonGroup();
  public static Mainpage ImThis;
  private OperationButton btnOP;
  
  public static final int xRes = 800;
  public static final int yRes = 600;
  
  public static int getPercent(int res, double percent) {
    double ret;
    ret = res * percent / 100.0;
    if (ret > res)
      ret = res;
    return (int)ret;
  }
  
  public static DefaultListModel<Page> disco = new DefaultListModel<Page>();
  public static DefaultListModel<Page> TPC = new DefaultListModel<Page>();
  public static DefaultListModel<Page> TPS = new DefaultListModel<Page>();
  /**
   * Create the application.
   */
  public Mainpage(TablesManager tm) {
    btnOP = new OperationButton(buttonGroup, tm);
    initialize();
    btnOP.setTextBoxes(textFieldId, textFieldValue);
    frmShadowPages.setVisible(true);
    ImThis = this;
  }
  
  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frmShadowPages = new JFrame();
    frmShadowPages.setTitle("Shadow Pages");
    frmShadowPages.setBounds(1366-xRes, 0, xRes, yRes);
    frmShadowPages.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frmShadowPages.getContentPane().setLayout(null);
    
    int yLabelStart = getPercent(yRes, 1);
    int yLabelWidth = getPercent(yRes, 5)-yLabelStart;
    int yListStart  = getPercent(yRes, 7);
    int yListWidth  = getPercent(yRes, 70)-yListStart;
    
    int xLeftColumnStart   = getPercent(xRes, 2);
    int xLeftColumnWidth   = getPercent(xRes, 32)-xLeftColumnStart;
    int xCenterColumnStart = getPercent(xRes, 35);
    int xCenterColumnWidth = getPercent(xRes, 65)-xCenterColumnStart;
    int xRightColumnStart  = getPercent(xRes, 68);
    int xRightColumnWidth  = getPercent(xRes, 98)-xRightColumnStart;
    
    JLabel lblTpc = new JLabel("TPC");
    lblTpc.setFont(new Font("Tahoma", Font.PLAIN, 22));
    lblTpc.setBounds(xLeftColumnStart, yLabelStart, xLeftColumnWidth, yLabelWidth);
    frmShadowPages.getContentPane().add(lblTpc);
    
    JList listTpc = new JList(TPC);
    listTpc.setBounds(xLeftColumnStart, yListStart, xLeftColumnWidth, yListWidth);
    frmShadowPages.getContentPane().add(listTpc);
    
    JLabel lblDisco = new JLabel("Disco");
    lblDisco.setFont(new Font("Tahoma", Font.PLAIN, 22));
    lblDisco.setBounds(xCenterColumnStart, yLabelStart, xCenterColumnWidth, yLabelWidth);
    frmShadowPages.getContentPane().add(lblDisco);
    
    JList listDisco = new JList(disco);
    listDisco.setBounds(xCenterColumnStart, yListStart, xCenterColumnWidth, yListWidth);
    frmShadowPages.getContentPane().add(listDisco);
    
    JLabel lblTps = new JLabel("TPS");
    lblTps.setFont(new Font("Tahoma", Font.PLAIN, 22));
    lblTps.setBounds(xRightColumnStart, yLabelStart, xRightColumnWidth, yLabelWidth);
    frmShadowPages.getContentPane().add(lblTps);
    
    JList listTps = new JList(TPS);
    listTps.setBounds(xRightColumnStart, yListStart, xRightColumnWidth, yListWidth);
    frmShadowPages.getContentPane().add(listTps);
    
    
    int xInputPanelStart = getPercent(xRes, 5);
    int xInputPanelWidth = getPercent(xRes, 95)-xInputPanelStart;
    int yInputPanelStart = getPercent(yRes, 73);
    int yInputPanelWidth = getPercent(yRes, 98)-yInputPanelStart;
    
    
    JPanel panel = new JPanel();
    panel.setBounds(xInputPanelStart, yInputPanelStart, xInputPanelWidth, yInputPanelWidth);
    frmShadowPages.getContentPane().add(panel);
    panel.setLayout(null);
    
    int xRatioButtonsStart = getPercent(xInputPanelWidth, 12);
    int xRatioButtonsWidth = getPercent(xInputPanelWidth, 27)-xRatioButtonsStart;
    int yRatioButtonsStart = getPercent(yInputPanelWidth, 2);
    int yRatioButtonsWidth = getPercent(yInputPanelWidth, 15)-yRatioButtonsStart;
    
    JLabel lblTipo = new JLabel("Tipo:");
    lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblTipo.setBounds(getPercent(xInputPanelWidth, 1), yRatioButtonsStart, getPercent(xInputPanelWidth, 10), yRatioButtonsWidth);
    panel.add(lblTipo);
    
    JRadioButton rdbtnNewRadioButton;
    
    rdbtnNewRadioButton = new JRadioButton("Start", true);
    buttonGroup.add(rdbtnNewRadioButton);
    rdbtnNewRadioButton.setActionCommand("Start");
    rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
    rdbtnNewRadioButton.setBounds(xRatioButtonsStart, yRatioButtonsStart, xRatioButtonsWidth, yRatioButtonsWidth);
    panel.add(rdbtnNewRadioButton);
    
    yRatioButtonsStart += yRatioButtonsWidth;
    
    rdbtnNewRadioButton = new JRadioButton("Update", true);
    buttonGroup.add(rdbtnNewRadioButton);
    rdbtnNewRadioButton.setActionCommand("Update");
    rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
    rdbtnNewRadioButton.setBounds(xRatioButtonsStart, yRatioButtonsStart, xRatioButtonsWidth, yRatioButtonsWidth);
    panel.add(rdbtnNewRadioButton);
    
    yRatioButtonsStart += yRatioButtonsWidth;
    
    rdbtnNewRadioButton = new JRadioButton("Insert");
    buttonGroup.add(rdbtnNewRadioButton);
    rdbtnNewRadioButton.setActionCommand("Insert");
    rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
    rdbtnNewRadioButton.setBounds(xRatioButtonsStart, yRatioButtonsStart, xRatioButtonsWidth, yRatioButtonsWidth);
    panel.add(rdbtnNewRadioButton);
    
    yRatioButtonsStart += yRatioButtonsWidth;
    
    rdbtnNewRadioButton = new JRadioButton("Commit");
    buttonGroup.add(rdbtnNewRadioButton);
    rdbtnNewRadioButton.setActionCommand("Commit");
    rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
    rdbtnNewRadioButton.setBounds(xRatioButtonsStart, yRatioButtonsStart, xRatioButtonsWidth, yRatioButtonsWidth);
    panel.add(rdbtnNewRadioButton);
    
    yRatioButtonsStart += yRatioButtonsWidth;
    
    rdbtnNewRadioButton = new JRadioButton("Abort");
    buttonGroup.add(rdbtnNewRadioButton);
    rdbtnNewRadioButton.setActionCommand("Abort");
    rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
    rdbtnNewRadioButton.setBounds(xRatioButtonsStart, yRatioButtonsStart, xRatioButtonsWidth, yRatioButtonsWidth);
    panel.add(rdbtnNewRadioButton);
    
//     yRatioButtonsStart += yRatioButtonsWidth;
//     
//     rdbtnNewRadioButton = new JRadioButton("Crash");
//     buttonGroup.add(rdbtnNewRadioButton);
//     rdbtnNewRadioButton.setActionCommand("Crash");
//     rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
//     rdbtnNewRadioButton.setBounds(xRatioButtonsStart, yRatioButtonsStart, xRatioButtonsWidth, yRatioButtonsWidth);
//     panel.add(rdbtnNewRadioButton);
    
    
    
    int xTextStart = getPercent(xInputPanelWidth, 30);
    int xTextWidth = getPercent(xInputPanelWidth, 60)-xTextStart;
    int yTextStart = getPercent(yInputPanelWidth, 2);
    int yTextWidth = getPercent(yInputPanelWidth, 15)-yTextStart;
    
    JLabel lblDado = new JLabel("Id");
    lblDado.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblDado.setBounds(xTextStart, yTextStart, xTextWidth, yTextWidth);
    panel.add(lblDado);
    
    yTextStart += yTextWidth;
    
    textFieldId = new JTextField();
    textFieldId.setBounds(xTextStart, yTextStart, xTextWidth, yTextWidth);
    panel.add(textFieldId);
    textFieldId.setColumns(10);
    
    yTextStart += yTextWidth;
    
    JLabel lblValor = new JLabel("Valor");
    lblValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblValor.setBounds(xTextStart, yTextStart, xTextWidth, yTextWidth);
    panel.add(lblValor);
    
    yTextStart += yTextWidth;
    
    textFieldValue = new JTextField();
    textFieldValue.setBounds(xTextStart, yTextStart, xTextWidth, yTextWidth);
    panel.add(textFieldValue);
    textFieldValue.setColumns(10);
    
    
    int xButtonStart = getPercent(xInputPanelWidth, 65);
    int xButtonWidth = getPercent(xInputPanelWidth, 100)-xButtonStart;
    int yButtonStart = getPercent(yInputPanelWidth, 2);
    int yButtonWidth = getPercent(yInputPanelWidth, 30)-yButtonStart;
    
    JButton btnNewButton = new JButton("Inserir Opera\u00E7\u00E3o");
    btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
    btnNewButton.setBounds(xButtonStart, yButtonStart, xButtonWidth, yButtonWidth);
    panel.add(btnNewButton);
    btnNewButton.addActionListener(btnOP);
    
    yButtonStart +=yButtonWidth + 10;
    
    JButton btnNewButton_1 = new JButton("Clear");
    btnNewButton_1.setBounds(xButtonStart, yButtonStart, xButtonWidth, yButtonWidth);
    panel.add(btnNewButton_1);
    
//     JLabel lblNovaOperao = new JLabel("Nova Opera\u00E7\u00E3o");
//     lblNovaOperao.setFont(new Font("Tahoma", Font.PLAIN, 16));
//     lblNovaOperao.setBounds(33, 527, 111, 23);
//     frmShadowPages.getContentPane().add(lblNovaOperao);
  }
}
