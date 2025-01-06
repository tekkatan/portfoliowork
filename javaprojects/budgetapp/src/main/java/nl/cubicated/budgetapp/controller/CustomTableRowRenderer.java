package nl.cubicated.budgetapp.controller;

import java.awt.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Tanja Bekker
 * Naam: CustomTableRowRenderer
 * Type: Klasse, DefaultTableCellRenderer
 * Functionaliteiten:
 * - Klasse voor het uitlijnen en kleuren van de rows
 */

public class CustomTableRowRenderer extends DefaultTableCellRenderer{
    private BudgetArtWeekUtils utils=new BudgetArtWeekUtils();
    // Getter en setter
    /**
     * @return the utils
     */
    public BudgetArtWeekUtils getUtils() {
        return utils;
    }
    /**
     * @param utils the utils to set
     */
    public void setUtils(BudgetArtWeekUtils utils) {
        this.utils = utils;
    }
    /**
     * Naam: CustomTableRowRenderer
     * Type: Constructor
     * @param table 
     * Functionaliteit: 
     * - constructor
     * - align tabelkolomnamen links
     */
    public CustomTableRowRenderer(JTable table){
        DefaultTableCellRenderer dtc=(DefaultTableCellRenderer) table
                .getTableHeader().getDefaultRenderer();
        dtc.setHorizontalAlignment(LEFT); 
    }
    
    /**
     * 
     * @param table
     * @param inputValue
     * @param isSelected
     * @param hasFocus
     * @param row
     * @param col
     * @return 
     * Naam: getTableCellRendererComponent
     * Functionaliteit:
     * - Geselecteerde rij andere kleur geven, welke is gedefinieerd in
     *   UIManager in CustomJPanel.
     * - Even en oneven rijen verschillende kleuren geven.
     */
     @Override
     public Component getTableCellRendererComponent(JTable table, 
        Object inputValue,boolean isSelected,boolean hasFocus, int row,int col){
        try{
            Color evenRows=new Color(0xF4EFF9);
            Color oddRows=new Color(0xEDFCFC);
            Component comp= super.getTableCellRendererComponent(table,
                inputValue,isSelected,hasFocus,row,col);
            /* Check of row is geselecteerd
            * Zo ja, dan krijgt de row de kleur welke in CustomJPanel is 
            * gedefinieerd als Table.selectionBackground.
            */
            if(isSelected){
                comp.setBackground((Color)UIManager.get("Table.selectionBackground"));
            }else{
            /* Als row niet is geselecteerd
            * Check of row even of odd is dmv modulo
            * en kleur ze in.
            */
                if(row%2==0){
                    comp.setBackground(evenRows);
                }else{
                    comp.setBackground(oddRows);
                }
            }
        }catch(Exception ex){
            try {
                // Aanmaken StringBuilder
                // Ophalen stacktrace
                // Call om stacktrace toe te voegen aan logs.txt
                StringBuilder sb=new StringBuilder();
                sb.append(Arrays.toString(ex.getStackTrace()));
                getUtils().writeLogs(sb);
                // User info
                throw new BudgetAppCustomException("Exception - Budgetapp - "
                        + "CustomTableRowRenderer - getTableCellRendererComponent");
            }catch (BudgetAppCustomException ex1) {
                }
        }
        return this;
    }
}
