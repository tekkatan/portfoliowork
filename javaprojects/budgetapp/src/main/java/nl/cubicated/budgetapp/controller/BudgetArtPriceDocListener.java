package nl.cubicated.budgetapp.controller;

import java.awt.Color;
import java.text.*;
import java.util.*;
import javax.swing.event.*;
import nl.cubicated.budgetapp.model.BudgetArtWeek;
import nl.cubicated.budgetapp.view.ButtonPanel;
import nl.cubicated.budgetapp.view.InnerPanel;

/**
 *
 * @author Tanja Bekker
 * Naam: BudgetArtPriceDocListener
 * Type: Klasse, DocumentListener
 * Functionaliteit:
 * - DocumentListener voor tf_2_artPrice in InnerPanel
 * Per catch in een try-catch en try-catch-with-resources:
 * - Create StringBuilder en append stacktrace van fout
 * - Call naar writeLogs met StringBuilder
 * - Throw new BudgetAppCustomException met cutom text voor user in output.
 */
public class BudgetArtPriceDocListener implements DocumentListener {
    private BudgetArtWeekUtils utils=new BudgetArtWeekUtils();
    private Locale localeNL=BudgetArtWeek.getnLocale();
    private static DecimalFormat decf=new DecimalFormat("0.00");
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
     * @return the localeNL
     */
    public Locale getLocaleNL() {
        return localeNL;
    }
    /**
     * @param localeNL the localeNL to set
     */
    public void setLocaleNL(Locale localeNL) {
        this.localeNL = localeNL;
    }

    /**
     * @return the decf
     */
    public static DecimalFormat getDecf() {
        return decf;
    }

    /**
     * @param aDecf the decf to set
     */
    public static void setDecf(DecimalFormat aDecf) {
        decf = aDecf;
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
     * Naam: BudgetArtPriceDocListener
     * Type: Constructor
     * @param ip 
     */
    public BudgetArtPriceDocListener(InnerPanel ip){
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
                    + "BudgetArtPriceListener - "
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
                    + "BudgetArtPriceListener - "
                    + "removeUpdate");
        }
    }

    /**
     *
     * Naam: changedUpdate
     * Type: void method
     * Functionaliteit: call to checker() method
     * Catches:  BudgetAppCustomException
    */
    @Override
    public void changedUpdate(DocumentEvent e) {
        try {
            checker();
        } catch (BudgetAppCustomException ex) {
            System.out.println("BudgetAppCustomException - "
                    + "BudgetArtPriceListener - "
                    + "changedUpdate");
        }
    }
    /**
     * 
     * Naam: acceptDutchCommaAndParseToDouble
     * Type: void method
     * Functionaliteit: 
     * - Parse input fields
     * @throws BudgetAppCustomException
     * Catches ParseException with BudgetAppCustomException
    */
    public void acceptDutchCommaAndParseToDouble() 
            throws BudgetAppCustomException{
        try{
            NumberFormat format=NumberFormat.getInstance(getLocaleNL());
            // Get art price and current qty
            int convert=(int)InnerPanel.getQtyArt().getSelectedItem();
                        getIp().getBaw().setQtyArt(convert);
            Number numPrice=format.parse(getIp().getTf_2_artPrice()
                    .getText());
            // Parse subprijs
            double artSubPrice=numPrice.doubleValue();
            // Bereken totaal artikel prijs
            getUtils().calcTotPrice(getDecf(),ip,convert,artSubPrice);
        }catch(ParseException ex){
            // Aanmaken StringBuilder
            // Ophalen stacktrace
            // Call om stacktrace toe te voegen aan logs.txt
            StringBuilder sb=new StringBuilder();
            sb.append(Arrays.toString(ex.getStackTrace()));
            getUtils().writeLogs(sb);
            throw new BudgetAppCustomException("Exception - "
                    + "BudgetArtPriceListener - "
                    + "acceptDutchCommaAndParseToDouble"); 
        }
    }
    /**
     * 
     * Naam: checker
     * Type: void method
     * Functionaliteit: Controle op letters/symbolen en nulwaarden
     * @throws BudgetAppCustomException
     * Catches BudgetAppCustomException
    */
    public void checker()throws BudgetAppCustomException{
        try{
            btnP=new ButtonPanel(ip);
            String input=getIp().getTf_2_artPrice().getText();
            // controle op rare tekens )(*&^%$#@!?//[//]}{><~`
            boolean validNumberFormat=getUtils()
                    .commaChecker(input);
            boolean checkZeroValues=getUtils().checkZeroValues(input);
            if(checkZeroValues){
                getIp().getLbl_3_artPrice_checker()
                        .setText("x:");
                getIp().getLbl_3_artPrice_checker()
                        .setForeground(new Color(0xba2525));
                // Disable the buttons
                getBtnP().getBtn_addRow().setEnabled(false);
                getBtnP().getBtn_delete_row().setEnabled(false);
                getBtnP().getBtn_save_txt().setEnabled(false);
            }else if(validNumberFormat){
                getIp().getLbl_3_artPrice_checker().setText("v");
                getIp().getLbl_3_artPrice_checker()
                        .setForeground(new Color(0x1ea59f));
                // Enable the buttons
                getBtnP().getBtn_addRow().setEnabled(true);
                getBtnP().getBtn_delete_row().setEnabled(true);
                getBtnP().getBtn_save_txt().setEnabled(true);
                // Calculate total price
                acceptDutchCommaAndParseToDouble();
            }else{
                getIp().getLbl_3_artPrice_checker()
                        .setText("x");
                getIp().getLbl_3_artPrice_checker()
                        .setForeground(new Color(0xba2525));
                // Disable the buttons
                getBtnP().getBtn_addRow().setEnabled(false);
                getBtnP().getBtn_delete_row().setEnabled(false);
                getBtnP().getBtn_save_txt().setEnabled(false);
            }
        }catch(BudgetAppCustomException ex){
            // Aanmaken StringBuilder
            // Ophalen stacktrace
            // call om stacktrace toe te voegen aan logs.txt
            StringBuilder sb=new StringBuilder();
            sb.append(Arrays.toString(ex.getStackTrace()));
            getUtils().writeLogs(sb);
            throw new BudgetAppCustomException("Exception - "
                    + "BudgetArtPriceListener - checker"); 
        }
    }     
}