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

import model.*;
import control.*;

public class Mainpage {
  
  public JFrame frmShadowPages;
  public JTextField textField;
  public JTextField textField_1;
  public final ButtonGroup buttonGroup = new ButtonGroup();
  public static Mainpage ImThis;
  
  public static DefaultListModel<Page> disco = new DefaultListModel<Page>();
  public static DefaultListModel<Page> TPC = new DefaultListModel<Page>();
  public static DefaultListModel<Page> TPS = new DefaultListModel<Page>();
  /**
   * Create the application.
   */
  public Mainpage() {
    initialize();
    frmShadowPages.setVisible(true);
    ImThis = this;
  }
  
  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frmShadowPages = new JFrame();
    frmShadowPages.setTitle("Shadow Pages");
    frmShadowPages.setBounds(100, 100, 900, 837);
    frmShadowPages.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frmShadowPages.getContentPane().setLayout(null);
    
    JList list = new JList(TPC);
    list.setBounds(54, 72, 221, 425);
    frmShadowPages.getContentPane().add(list);
    
    JList list_1 = new JList(disco);
    list_1.setBounds(330, 72, 258, 425);
    frmShadowPages.getContentPane().add(list_1);
    
    JList list_2 = new JList(TPS);
    list_2.setBounds(630, 72, 221, 425);
    frmShadowPages.getContentPane().add(list_2);
    
    JPanel panel = new JPanel();
    panel.setBounds(33, 61, 263, 448);
    frmShadowPages.getContentPane().add(panel);
    
    JPanel panel_1 = new JPanel();
    panel_1.setBounds(306, 61, 299, 448);
    frmShadowPages.getContentPane().add(panel_1);
    
    JPanel panel_2 = new JPanel();
    panel_2.setBounds(615, 61, 256, 448);
    frmShadowPages.getContentPane().add(panel_2);
    
    JLabel lblTpc = new JLabel("TPC");
    lblTpc.setFont(new Font("Tahoma", Font.PLAIN, 22));
    lblTpc.setBounds(34, 25, 150, 36);
    frmShadowPages.getContentPane().add(lblTpc);
    
    JLabel lblDisco = new JLabel("Disco");
    lblDisco.setFont(new Font("Tahoma", Font.PLAIN, 22));
    lblDisco.setBounds(296, 25, 150, 36);
    frmShadowPages.getContentPane().add(lblDisco);
    
    JLabel lblTps = new JLabel("TPS");
    lblTps.setFont(new Font("Tahoma", Font.PLAIN, 22));
    lblTps.setBounds(615, 25, 150, 36);
    frmShadowPages.getContentPane().add(lblTps);
    
    JPanel panel_3 = new JPanel();
    panel_3.setBounds(31, 552, 450, 235);
    frmShadowPages.getContentPane().add(panel_3);
    panel_3.setLayout(null);
    
    JRadioButton rdbtnNewRadioButton = new JRadioButton("Update");
    buttonGroup.add(rdbtnNewRadioButton);
    rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
    rdbtnNewRadioButton.setBounds(10, 81, 109, 23);
    panel_3.add(rdbtnNewRadioButton);
    
    JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Commit");
    buttonGroup.add(rdbtnNewRadioButton_1);
    rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
    rdbtnNewRadioButton_1.setBounds(10, 107, 109, 23);
    panel_3.add(rdbtnNewRadioButton_1);
    
    JRadioButton rdbtnAbort = new JRadioButton("Abort");
    buttonGroup.add(rdbtnAbort);
    rdbtnAbort.setFont(new Font("Tahoma", Font.PLAIN, 14));
    rdbtnAbort.setBounds(10, 133, 109, 23);
    panel_3.add(rdbtnAbort);
    
    JRadioButton rdbtnCrash = new JRadioButton("Crash");
    buttonGroup.add(rdbtnCrash);
    rdbtnCrash.setFont(new Font("Tahoma", Font.PLAIN, 14));
    rdbtnCrash.setBounds(10, 159, 109, 23);
    panel_3.add(rdbtnCrash);
    
    JRadioButton rdbtnAdd = new JRadioButton("Add");
    buttonGroup.add(rdbtnAdd);
    rdbtnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
    rdbtnAdd.setBounds(10, 55, 109, 23);
    panel_3.add(rdbtnAdd);
    
    JLabel lblTipo = new JLabel("Tipo");
    lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblTipo.setBounds(10, 22, 46, 17);
    panel_3.add(lblTipo);
    
    textField = new JTextField();
    textField.setBounds(203, 43, 235, 23);
    panel_3.add(textField);
    textField.setColumns(10);
    
    JLabel lblDado = new JLabel("Dado");
    lblDado.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblDado.setBounds(203, 23, 46, 14);
    panel_3.add(lblDado);
    
    textField_1 = new JTextField();
    textField_1.setBounds(203, 98, 235, 23);
    panel_3.add(textField_1);
    textField_1.setColumns(10);
    
    JLabel lblValor = new JLabel("Valor");
    lblValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblValor.setBounds(203, 78, 46, 14);
    panel_3.add(lblValor);
    
    JButton btnNewButton = new JButton("Inserir Opera\u00E7\u00E3o");
    btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
    btnNewButton.setBounds(203, 152, 235, 56);
    panel_3.add(btnNewButton);
    
    JLabel lblNovaOperao = new JLabel("Nova Opera\u00E7\u00E3o");
    lblNovaOperao.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblNovaOperao.setBounds(33, 527, 111, 23);
    frmShadowPages.getContentPane().add(lblNovaOperao);
    
    JButton btnNewButton_1 = new JButton("Clear");
    btnNewButton_1.setBounds(713, 726, 138, 61);
    frmShadowPages.getContentPane().add(btnNewButton_1);
  }
}
