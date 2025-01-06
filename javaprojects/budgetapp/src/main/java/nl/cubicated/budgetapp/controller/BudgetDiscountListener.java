package nl.cubicated.budgetapp.controller;

import java.awt.event.*;
import nl.cubicated.budgetapp.view.InnerPanel;

/**
 *
 * @author Tanja Bekker
 * 
 * Naam: BudgetDiscountListener 
 * Type: Klasse, ActionListener 
 * Functionaliteiten:
 * - ActionListener voor de radioButtons aanbieding: ja en nee
 * Per catch in een try-catch en try-catch-with-resources:
 * - Create StringBuilder en append stacktrace van fout
 * - Call naar writeLogs met StringBuilder
 * - Throw new BudgetAppCustomException met cutom text voor user in output.
 * 
 */
public class BudgetDiscountListener implements ActionListener {
    private InnerPanel ip;
    // Getter en setter
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
     * Naam: BudgetDiscountListener
     * Type: Constructor
     * @param ip van type InnerPanel
     */
    public BudgetDiscountListener(InnerPanel ip){
        this.ip=ip;
    }
    
    /**
     * Naam: actionPerformed
     * Type: void method
     * Functionaliteiten:
     * - Als getRb_discountNo isSelected dan getLbl_1_discount_ans is Nee
     * - Als getRb_discountYes isSelected dan getLbl_1_discount_ans is Ja
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(getIp().getRb_discountNo().isSelected()){
            // Nee is selected
            // Set lbl_1_discount_ans naar Nee
            getIp().getBaw().setDiscount("Nee");
            getIp().getLbl_1_discount_ans().setText("Nee");
        }else if(getIp().getRb_discountYes().isSelected()){
            // Ja is selected
            // Set lbl_1_discount_ans naar Ja
            getIp().getBaw().setDiscount("Ja");
            getIp().getLbl_1_discount_ans().setText("Ja");
        }
    }   
}