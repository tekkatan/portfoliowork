package nl.cubicated.budgetapp.controller;

import java.awt.event.*;
import nl.cubicated.budgetapp.view.InnerPanel;

/**
 *
 * @author Tanja Bekker
 * 
 * Naam: BudgetBoughtListener 
 * Type: Klasse, ActionListener 
 * Functionaliteiten:
 * - ActionListener voor de radioButtons gekocht: ja en nee
 * Per catch in een try-catch en try-catch-with-resources:
 * - Create StringBuilder en append stacktrace van fout
 * - Call naar writeLogs met StringBuilder
 * - Throw new BudgetAppCustomException met cutom text voor user in output.
 * 
 */
public class BudgetBoughtListener implements ActionListener {
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
     * Naam: BudgetBoughtListener
     * Type: Constructor
     * @param ip can type BudgetArtWeekView
     */
    public BudgetBoughtListener(InnerPanel ip){
        this.ip=ip;
    }
    
    /**
     * Naam: actionPerformed
     * Type: void method
     * Functie: ActionEvent voor rb_boughtNo en rb_boughtYes. 
     * Bij een selectie van één wordt de lbl_1_bought_ans tekst 
     * gezet naar respectievelijk Ja of Nee.
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(getIp().getRb_boughtNo().isSelected()){
            // Nee is selected
            // Set lbl_1_bought_ans naar Nee
            getIp().getBaw().setBought("Nee");
            getIp().getLbl_1_bought_ans().setText("Nee");
        }else if(getIp().getRb_boughtYes().isSelected()){
            // Ja is selected
            // Set lbl_1_bought_ans naar Ja
            getIp().getBaw().setBought("Ja");
            getIp().getLbl_1_bought_ans().setText("Ja");
        }
    }  
}