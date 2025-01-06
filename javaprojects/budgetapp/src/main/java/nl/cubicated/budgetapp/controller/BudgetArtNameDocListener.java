package nl.cubicated.budgetapp.controller;

import java.awt.Color;
import java.util.Arrays;
import javax.swing.event.*;
import nl.cubicated.budgetapp.view.ButtonPanel;
import nl.cubicated.budgetapp.view.InnerPanel;

/**
 *
 * @author Tanja Bekker
 * 
 * Naam: BudgetArtNameDocListener
 * Type: Klasse, DocumentListener
 * Functionaliteiten:
 * - DocumentListener ter controle van invoerveld tf_1_artName in InnerPanel
 * Per catch in een try-catch en try-catch-with-resources:
 * - Create StringBuilder en append stacktrace van fout
 * - Call naar writeLogs met StringBuilder
 * - Throw new BudgetAppCustomException met custom text voor user in output.
*/
public class BudgetArtNameDocListener implements DocumentListener {
    private BudgetArtWeekUtils utils=new BudgetArtWeekUtils();
    private InnerPanel ip;
    private ButtonPanel btnP;
    // Getters en setters
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
     * Naam: BudgetArtNameDocListener
     * Type: Controller
     * @param ip 
     */
    public BudgetArtNameDocListener(InnerPanel ip){
        this.ip = ip;
    }
    // Controle bij invoeren tekst, edit of weghalen tekst en verandering
    /**
     *
     * Naam: insertUpdate
     * Type: void method
     * Functionaliteit: 
     * - Call to checker() method
     * Catches:  BudgetAppCustomException
    */
    @Override
    public void insertUpdate(DocumentEvent e) {
        try {
            checker();
        } catch (BudgetAppCustomException ex) {
            System.out.println("BudgetAppCustomException - "
                    + "BudgetArtNameDocListener - "
                    + "insertUpdate");
        }
    }
    /**
     *
     * Naam: removeUpdate
     * Type: void method
     * Functionaliteit: 
     * - Call to checker() method
     * Catches:  BudgetAppCustomException
    */
    @Override
    public void removeUpdate(DocumentEvent e) {
        try {
            checker();
        } catch (BudgetAppCustomException ex) {
            System.out.println("BudgetAppCustomException - "
                    + "BudgetArtNameDocListener - "
                    + "removeUpdate");
        }
    }
    /**
     *
     * Naam: changedUpdate
     * Type: void method
     * Functionaliteit: 
     * - Call to checker() method
     * Catches:  BudgetAppCustomException
    */
    @Override
    public void changedUpdate(DocumentEvent e) {
        try {
            checker();
        } catch (BudgetAppCustomException ex) {
           System.out.println("BudgetAppCustomException - "
                    + "BudgetArtNameDocListener - "
                    + "changedUpdate");
        }
    }
    
    /**
     * 
     * Naam: checker
     * Type: void method
     * Functionaliteiten: 
     * - Controle op letters/symbolen en nulwaarden
     * - Als Exception dan call BudgetArtWeekUtils.writeLogs()
     * 
     * @throws BudgetAppCustomException
     * catches BudgetAppCustomException
    */
    public void checker() throws BudgetAppCustomException{
        try{
            btnP=new ButtonPanel(ip);
            String input=getIp().getTf_1_artName().getText().trim();
            // Controle op rare tekens )(*&^%$#@!?//[//]}{><~`
            boolean invalidCharacter=getUtils().checkSpecialCharacter(input);
            boolean checkWordsValues=getUtils().checkWordsValues(input);
            if(invalidCharacter){
                getIp().getLbl_3_artName_checker().setText("x");
                getIp().getLbl_3_artName_checker()
                        .setForeground(new Color(0xba2525));
                // Disable the buttons
                getBtnP().getBtn_addRow().setEnabled(false);
                getBtnP().getBtn_delete_row().setEnabled(false);
                getBtnP().getBtn_save_txt().setEnabled(false);
            }
            else if(checkWordsValues){
                getIp().getLbl_3_artName_checker().setText("v");
                getIp().getLbl_3_artName_checker()
                        .setForeground(new Color(0x1ea59f));
                getBtnP().getBtn_addRow().setEnabled(true);
                getBtnP().getBtn_delete_row().setEnabled(true);
                getBtnP().getBtn_save_txt().setEnabled(true);
                // Get Innerpanel getBaw en set model artName 
                // naar input
                getIp().getBaw().setArtName(input);
            }
            else{
                getIp().getLbl_3_artName_checker().setText("x");
                getIp().getLbl_3_artName_checker()
                        .setForeground(new Color(0xba2525));
                // Disable the buttons
                getBtnP().getBtn_addRow().setEnabled(false);
                getBtnP().getBtn_delete_row().setEnabled(false);
                getBtnP().getBtn_save_txt().setEnabled(false);
            }
        }catch(BudgetAppCustomException ex){
            // Aanmaken StringBuilder
            // Ophalen stacktrace
            // Call om stacktrace toe te voegen aan logs.txt
            StringBuilder sb=new StringBuilder();
            sb.append(Arrays.toString(ex.getStackTrace()));
            getUtils().writeLogs(sb);
            throw new BudgetAppCustomException("Exception - BudgetArtNameListener - checker"); 
        } 
    }   
}