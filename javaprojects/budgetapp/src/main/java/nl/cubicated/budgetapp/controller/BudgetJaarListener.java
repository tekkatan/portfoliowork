package nl.cubicated.budgetapp.controller;

import java.awt.event.*;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import nl.cubicated.budgetapp.model.BudgetArtWeek;
import nl.cubicated.budgetapp.view.InnerPanel;

/**
 *
 * @author Tanja Bekker
 * 
 * Naam: BudgetJaarListener
 * Type: Klasse, ItemListener
 * Functionaliteiten:
 * - ItemListener voor jaarcombobox
 * Per catch in een try-catch en try-catch-with-resources:
 * - Create StringBuilder en append stacktrace van fout
 * - Call naar writeLogs met StringBuilder
 * - Throw new BudgetAppCustomException met cutom text voor user in output.
 */
public class BudgetJaarListener implements ItemListener {
    private BudgetArtWeekUtils utils=new BudgetArtWeekUtils();
    private InnerPanel ip;
    
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
     * Naam:BudgetJaarListener
     * Type: Constructor
     * @param ip 
     */
    public BudgetJaarListener(InnerPanel ip){
        this.ip = ip;
    }

    /**
     * Naam: itemStateChanged
     * Type: void method
     * Functionaliteit:
     * - Bij verandering in combobox wordt het geselecteerde jaar omgezet naar
     *   int value
     * - Jaar wordt middels switch case opgezocht en weken worden toegekend.
     * Catches:
     * - NullPointerException
     * - Exception
     * @param e 
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        // Handle selection event
        try{
            if(e.getStateChange() ==ItemEvent.SELECTED){
                    Object g=e.getSource();
                    if(g instanceof JComboBox<?>){
                        int convert=(int)InnerPanel.getJaarcbb().getSelectedItem();
                        // Based on year value change amount of weeks
                        switch(convert){
                            case 2024:
                                InnerPanel.getWeekcbb().setModel(new DefaultComboBoxModel<>(BudgetArtWeek.getWekenAnders()));
                                break;
                            case 2028:
                                InnerPanel.getWeekcbb().setModel(new DefaultComboBoxModel<>(BudgetArtWeek.getWekenAnders()));
                                break;
                            case 2032:
                                InnerPanel.getWeekcbb().setModel(new DefaultComboBoxModel<>(BudgetArtWeek.getWekenAnders()));
                                break;
                            case 2036:
                                InnerPanel.getWeekcbb().setModel(new DefaultComboBoxModel<>(BudgetArtWeek.getWekenAnders()));
                                break;
                            case 2040:
                                InnerPanel.getWeekcbb().setModel(new DefaultComboBoxModel<>(BudgetArtWeek.getWekenAnders()));
                                break;
                            default:
                                InnerPanel.getWeekcbb().setModel(new DefaultComboBoxModel<>(BudgetArtWeek.getWeken()));
                                break;
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
                                + "BudgetJaarListener - itemStateChanged");
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
                                + "BudgetJaarListener - itemStateChanged");
            } catch (BudgetAppCustomException ex1) {
                }
        }          
    } 
}