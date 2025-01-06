package nl.cubicated.budgetapp.view;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import nl.cubicated.budgetapp.controller.BudgetAddBTNListener;
import nl.cubicated.budgetapp.controller.BudgetDeleteBTNListener;
import nl.cubicated.budgetapp.controller.BudgetSaveTXTListener;
import nl.cubicated.budgetapp.model.CustomJPanel;

/**
 *
 * @author Tanja Bekker
 * Naam: ButtonPanel
 * Type: Klasse
 * Functionaliteiten:
 * - Toevoegen JButtons add, delete en save en hen linken met 
 *   de bijbehorende actionListeners.
 * - Aparte methoden om addButton enable op true of false te zetten.
 */
public class ButtonPanel extends CustomJPanel{
    private JButton btn_addRow,
            btn_delete_row,btn_save_txt;
    private InnerPanel ip;
    // Getters en setters
    /**
     * @return the ip
     */
    public InnerPanel getIp() {
        return ip;
    }
    /**
     * @param ip the ip to set
     */
    public void setIp(InnerPanel ip) {
        this.ip = ip;
    }
    /**
     * @return the btn_addRow
     */
    public JButton getBtn_addRow() {
        return btn_addRow;
    }
    /**
     * @param btn_addRow the btn_addRow to set
     */
    public void setBtn_addRow(JButton btn_addRow) {
        this.btn_addRow = btn_addRow;
    }
    /**
     * @return the btn_delete_row
     */
    public JButton getBtn_delete_row() {
        return btn_delete_row;
    }
    /**
     * @param btn_delete_row the btn_delete_row to set
     */
    public void setBtn_delete_row(JButton btn_delete_row) {
        this.btn_delete_row = btn_delete_row;
    }
    /**
     * @return the btn_save_txt
     */
    public JButton getBtn_save_txt() {
        return btn_save_txt;
    }
    /**
     * @param btn_save_txt the btn_save_txt to set
     */
    public void setBtn_save_txt(JButton btn_save_txt) {
        this.btn_save_txt = btn_save_txt;
    }
    /*
    * Naam: disableAddBTN
    * Type: void method
    * Functionaleit:
    * - disable AddBtn
    */
    public void disableAddBTN(){
        getBtn_addRow().setEnabled(false);
    }
    /*
    * Naam: enableAddBTN
    * Type: void method
    * Functioneliteit
    * - enable AddBtn
    */
    public void enableAddBTN(){
        getBtn_addRow().setEnabled(true);
    }
    
    /**
     * Naam: ButtonPanel
     * Type: Constructor
     * Functionaliteiten:
     * - InnerPanel parameter linken aan parameter in ButtonPanel
     * - Call naar initButtonPanel
     * @param ip 
     */
    public ButtonPanel(InnerPanel ip){
        this.ip=ip;
        initButtonPanel(ip);
    }
    /**
     * Naam: initButtonPanel
     * @param ip 
     * Type: void method
     * Functionaliteiten:
     * - Setting background
     * - Setting Layout
     * - Aanmaken button Voeg artikel toe, Delete artikel en Export weeklijst(en)
     * - Bovengenoemde buttons voorzien van ActionListeners
     * - Toevoegen van onderdelen aan ButtonPanel
     */
    private void initButtonPanel(InnerPanel ip){
        // Button panel
        setBackground(new Color(0xEDFCFC));
        setLayout(new FlowLayout(FlowLayout.CENTER));
        // AddRow button
        btn_addRow=new JButton("Voeg artikel toe");
        btn_addRow.setUI(new CustomButtonGUI());
        btn_addRow.setForeground(Color.white);
        btn_addRow.setVisible(true);
        btn_addRow.setEnabled(true);
        // Action listener
        btn_addRow.addActionListener(new BudgetAddBTNListener(this,ip));
        add(btn_addRow);
        // Delete artrow button
        btn_delete_row=new JButton("Delete artikel");
        btn_delete_row.setUI(new CustomButtonGUI());
        btn_delete_row.setForeground(Color.white);
        btn_delete_row.setVisible(true);
        btn_delete_row.setEnabled(false);
        // Action listener
        btn_delete_row.addActionListener(new BudgetDeleteBTNListener(this,ip));
        add(btn_delete_row);
        // Save txt button
        // ---- Button ---- save all rows to Weeklist(to txt)
        btn_save_txt=new JButton("Export weeklijst(en)");
        btn_save_txt.setUI(new CustomButtonGUI());
        btn_save_txt.setForeground(Color.white);
        btn_save_txt.setVisible(true);
        btn_save_txt.setEnabled(false);
        // Action listener
        btn_save_txt.addActionListener(new BudgetSaveTXTListener(this,ip));
        add(btn_save_txt);
    }
}