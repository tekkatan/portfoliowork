package nl.cubicated.budgetapp.controller;

import java.awt.event.*;
import java.util.Arrays;
import javax.swing.JComboBox;
import nl.cubicated.budgetapp.view.InnerPanel;

/**
 *
 * @author Tanja Bekker
 * Naam: BudgetWeekListener
 * Type: klasse, ItemListener
 * Functionaliteiten:
 * - ItemListener voor week combobox
 * Per catch in een try-catch en try-catch-with-resources:
 * - Create StringBuilder en append stacktrace van fout
 * - Call naar writeLogs met StringBuilder
 * - Throw new BudgetAppCustomException met cutom text voor user in output.
*/
public class BudgetWeekListener implements ItemListener {
    private InnerPanel ip;
    private BudgetArtWeekUtils utils=new BudgetArtWeekUtils();
    // Getter en setters
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
     * Contructor
     * @param ip 
     */
    public BudgetWeekListener(InnerPanel ip){
        this.ip = ip;
    }
    
    /**
     * Naam: itemStateChanged
     * Return type: void
     * Functionaliteiten:
     * - Get selectedItem en convert naar int value
     * - Get given year en convert naar int value
     * - Pas datum aan van(maandag) tot(zondag) call naar
     *   method displayGivenWeekDate in class BudgetArtWeekUtils
     * - Set resultaat in lbl_3_wk
     * Catches:
     * - NullPointerException
     * - BudgetAppCustomException
     * Per catch:
     * - Create StringBuilder en append stacktrace van fout
     * - Call naar writeLogs met StringBuilder
     * - Throw new BudgetAppCustomException met cutom text voor user in output.
     * @param e 
     */
    @Override
    public void itemStateChanged(ItemEvent e){
        // Handle selection event 
        String converted="";
        try{
            if(e.getStateChange() ==ItemEvent.SELECTED){
            Object g=e.getSource();
            if(g instanceof JComboBox<?>){
                // Get selectedItem en convert naar int value
                int convert=(int)InnerPanel.getWeekcbb().getSelectedItem();
                // Get given year en convert naar int value
                int convertYear=(int)InnerPanel.getJaarcbb().getSelectedItem();
                // Pas datum aan van(maandag) tot(zondag)
                    converted+=getUtils()
                        .displayGivenWeekDate(convertYear,convert);
                } 
                // Set resultaat in lbl_3_wk
                getIp().getLbl_3_wk().setText(converted);
            }             
        }catch(NullPointerException ex){
            try {
                // Aanmaken StringBuilder
                // Ophalen stacktrace
                // Call om stacktrace toe te voegen aan logs.txt
                StringBuilder sb=new StringBuilder();
                sb.append(Arrays.toString(ex.getStackTrace()));
                getUtils().writeLogs(sb);
                throw new BudgetAppCustomException(
                "NullPointerException - Budgetapp - in BudgetWeekListener - "
                + "itemStateChanged");
            } catch (BudgetAppCustomException ex1) {
                }
        }catch(BudgetAppCustomException ex){
            try {
                // Aanmaken StringBuilder
                // Ophalen stacktrace
                // Call om stacktrace toe te voegen aan logs.txt
                StringBuilder sb=new StringBuilder();
                sb.append(Arrays.toString(ex.getStackTrace()));
                getUtils().writeLogs(sb);
                throw new BudgetAppCustomException(
                "Exception - Budgetapp - in BudgetWeekListener - "
                + "itemStateChanged");
            } catch (BudgetAppCustomException ex1) {
                }
        }
   }  
}