package nl.cubicated.budgetapp.controller;

import java.awt.Color;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.JComboBox;
import nl.cubicated.budgetapp.view.InnerPanel;
/**
 *
 * @author Tanja Bekker
 * 
 * Naam: BudgetQtyArtListener
 * Type: Klasse, ItemListener
 * Functionaliteit:
 * Is een ItemListener voor combobox qtyArt.
 * Per catch in een try-catch en try-catch-with-resources:
 * - Create StringBuilder en append stacktrace van fout
 * - Call naar writeLogs met StringBuilder
 * - Throw new BudgetAppCustomException met cutom text voor user in output.
 */
public class BudgetQtyArtListener implements ItemListener {
    private InnerPanel ip;
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
     * Naam: BudgetQtyArtListener
     * Type: Constructor
     * @param ip van type InnerPanel
     */
    public BudgetQtyArtListener(InnerPanel ip){
        this.ip = ip;
    }
    /**
     * Naam: itemStateChanged
     * Type: void method
     * Functionaliteiten:
     * - Convert geselecteerd item naar int
     * - If/else voor controle op waarde 0 
     * - Als 0 dan een x voor lbl_3_artQty_checker ter verduidelijking dat 
     *   de user iets fout heeft gedaan.
     * - Als niet 0 dan een v voor lbl_3_artQty_checker ter verduidelijking dat 
     *   de user iets goed heeft gedaan.
     *   - Set qtyart naar geselecteerde waarde
     *   - Call calculateTotPrijs method met geselecteerde qty waarde
     * Catches:
     * - NullPointerException
     * - Exception
     * @param e 
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        try{
            if(e.getStateChange()==ItemEvent.SELECTED){
                    Object g=e.getSource();
                    if(g instanceof JComboBox<?>){
                        int convert=(int)InnerPanel.getQtyArt().getSelectedItem();
                        // Controle op waarde
                        if(convert==0){
                            // Fout
                            getIp().getLbl_3_artQty_checker().setText("x");
                            getIp().getLbl_3_artQty_checker()
                                    .setForeground(new Color(0xba2525));
                        }else{
                            // Goed
                            getIp().getLbl_3_artQty_checker().setText("v");
                            getIp().getLbl_3_artQty_checker()
                                    .setForeground(new Color(0x1ea59f));
                            getIp().getBaw().setQtyArt(convert);
                            // Bereken totprijs
                            getUtils().calculateTotPrijs(ip,convert);
                        } 
                    }
                }
        }catch(NullPointerException ex){
            try {
                // Aanmaken StringBuilder
                // Ophalen stacktrace
                // Call om stacktrace toe te voegen aan logs.txt
                StringBuilder sb=new StringBuilder();
                sb.append(Arrays.toString(ex.getStackTrace()));
                getUtils().writeLogs(sb);
                // User info
                throw new BudgetAppCustomException("NullPointerException - Budgetapp - "
                                + "BudgetQtyArtListener - itemStateChanged");
            } catch (BudgetAppCustomException ex1) {
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
                                + "BudgetQtyArtListener - itemStateChanged");
            } catch (BudgetAppCustomException ex1) {
                }
        }          
    }   
}