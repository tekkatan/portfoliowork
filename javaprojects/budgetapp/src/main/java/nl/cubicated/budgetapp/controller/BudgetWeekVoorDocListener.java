package nl.cubicated.budgetapp.controller;

import java.awt.Color;
import java.util.Arrays;
import javax.swing.event.*;
import nl.cubicated.budgetapp.view.ButtonPanel;
import nl.cubicated.budgetapp.view.InnerPanel;
/**
 *
 * @author Tanja Bekker
 * Naam: BudgetWeekVoorDocListener
 * Type: Klasse, DocumentListener
 * Functionaliteiten:
 * - DocumentListener voor tf_1_weekBudgetVoor
 * Per catch in een try-catch en try-catch-with-resources:
 * - create StringBuilder en append stacktrace van fout
 * - call naar writeLogs met StringBuilder
 * - Throw new BudgetAppCustomException met cutom text voor user in output.
 */
public class BudgetWeekVoorDocListener implements DocumentListener {
    private BudgetArtWeekUtils utils=new BudgetArtWeekUtils();
    private InnerPanel ip;
    private ButtonPanel btnP;
    
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
     * @param btnP the ip to set
     */
    public void setBtnP(ButtonPanel btnP){
        this.btnP=btnP;
    }
    /**
     * @return the btnP
     */
    public ButtonPanel getBtnP(){
        return btnP;
    }
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
     * Naam: BudgetWeekVoorDocListener
     * Type: Constructor
     * @param ip 
     */
    public BudgetWeekVoorDocListener(InnerPanel ip){
        this.ip = ip;
    }
    // Check when inserting text, edit of delete text
    /**
     *
    Naam: insertUpdate
    Type: void
    Functionaliteit: call to checker() method
    */
    @Override
    public void insertUpdate(DocumentEvent e) {
        checker();
    }
    /**
     *
    Naam: removeUpdate
    Type: void
    Functionaliteit: call to checker() method
    */
    @Override
    public void removeUpdate(DocumentEvent e) {
        checker();
    }
    /**
     *
    Naam: changedUpdate
    Type: void
    Functionaliteit: call to checker() method
    */
    @Override
    public void changedUpdate(DocumentEvent e) {
        checker();
    }
    /**
     * Naam: checker
     * Type: void method
     * Functionaliteit: 
     * - Controleren tf_1_weekBudgetVoor op lege of 0,00 waarden
     *   geen symbolen of een . toegestaan
     * - Controleren tf_1_weekBudgetVoor op goed nummer format middels regex
     * - Als nummerformat goed is, enable buttons in GUI
     * Catches: BudgetAppCustomException
     */
    public void checker(){
        
        try{
            // Maak nieuw ButtonPannel object met huidige invulwaarden 
            // van InnerPanel invulvelden en keuzeboxen
            btnP=new ButtonPanel(ip);
            String input=getIp().getTf_1_weekBudgetVoor().getText();
            // Check for weird symbols )(*&^%$#@!?//[//]}{><~`
            boolean validNumberFormat=getUtils().commaChecker(input);
            boolean checkZeroValues=getUtils().checkZeroValues(input);
            if(checkZeroValues){
                // Value is not allowed to be zero
                getIp().getLbl_3_weekbudget_checker().setText("x");
                getIp().getLbl_3_weekbudget_checker()
                        .setForeground(new Color(0xba2525));
                // Disable de buttons
                getBtnP().disableAddBTN();
                getBtnP().getBtn_delete_row().setEnabled(false);
                getBtnP().getBtn_save_txt().setEnabled(false);
            }else if(validNumberFormat){
                getIp().getLbl_3_weekbudget_checker().setText("v");
                getIp().getLbl_3_weekbudget_checker()
                        .setForeground(new Color(0x1ea59f));
                // enable buttons
                getBtnP().enableAddBTN();
                getBtnP().getBtn_delete_row().setEnabled(true);
                getBtnP().getBtn_save_txt().setEnabled(true);
            }else{
                getIp().getLbl_3_weekbudget_checker().setText("x");
                getIp().getLbl_3_weekbudget_checker()
                        .setForeground(new Color(0xba2525));
                // Disable the buttons
                getBtnP().getBtn_addRow().setEnabled(false);
                getBtnP().getBtn_delete_row().setEnabled(false);
                getBtnP().getBtn_save_txt().setEnabled(false);
            }
        }catch(BudgetAppCustomException ex){
            try {
                // Aanmaken StringBuilder
                // Ophalen stacktrace
                // Call om stacktrace toe te voegen aan logs.txt
                StringBuilder sb=new StringBuilder();
                sb.append(Arrays.toString(ex.getStackTrace()));
                getUtils().writeLogs(sb);
                // User info
                throw new BudgetAppCustomException("Exception - Budgetapp - "
                        + "BudgetWeekVoorDocListener - checker");
            } catch (BudgetAppCustomException ex1) {
            }
        }
    }    
}