package nl.cubicated.budgetapp.controller;

import java.awt.event.*;
import java.util.Arrays;
import nl.cubicated.budgetapp.view.ButtonPanel;
import nl.cubicated.budgetapp.view.InnerPanel;
/**
 *
 * @author Tanja Bekker
 * Naam: BudgetSaveTXTListener
 * Type: klasse, ActionListener
 * Functionaliteiten:
 * - ActionListener voor button btn_save_txt in ButtonPanel.
 * - Convert naar NL nummerformat met twee decimalen achter de komma
 * - Sum uitgaven per gegeven supermarkt
 * - Ophalen supermarktnamen welke aanwezig zijn in 
 *   een row van de JTable
 * - Export lijst per supermarkt (meerdere lijsten) 
 *   en alle supermarkten (1 grote lijst)
 * - Maak lijst van alle supermarkten
 * - Uitlijnen txt bestand : check if input is 16 char long
                             if not dan voeg spaces toe tot 16 char long
 * Per catch in een try-catch en try-catch-with-resources:
 * - Create StringBuilder en append stacktrace van fout
 * - Call naar writeLogs met StringBuilder
 * - Throw new BudgetAppCustomException met cutom text voor user in output.
 */
public class BudgetSaveTXTListener implements ActionListener {
    private BudgetArtWeekUtils utils;
    private BudgetWeekListUtils utilsList;
    private InnerPanel ip;
    private ButtonPanel btnP;
    // Getters en setters
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
     * @return the utilsList
     */
    public BudgetWeekListUtils getUtilsList() {
        return utilsList;
    }
    /**
     * @param utilsList the utilsList to set
     */
    public void setUtilsList(BudgetWeekListUtils utilsList) {
        this.utilsList = utilsList;
    }
    /**
     * Naam: BudgetSaveTXTListener
     * Type: Constructor
     * @param btnP
     * @param ip
    */
    public BudgetSaveTXTListener(ButtonPanel btnP, InnerPanel ip){
        this.btnP=btnP;
        this.ip=ip;
    }

    /**
     * Naam: actionPerformed
     * Type: void method
     * Functionaliteiten: 
     * - Ophalen gekozen jaar
     * - Ophalen gekozen week
     * - Call naar getSupermarketsList() en sla ze op in String []
     * - Call naar exportTXTAlleSupermarkten(gekozenJaar,gekozenWeek)
     * - Middels for loop per supermarkt call exportTXTPerSupermarket(gekozenJaar,
                    gekozenWeek,s);
     * catches:
     * - BudgetAppCustomException
     * - Exception
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            //check of tabel niet leeg is
            if(getIp().getTable().getRowCount()==0){
                getBtnP().getBtn_delete_row().setEnabled(false);
                getBtnP().getBtn_save_txt().setEnabled(false);
            }else{
                setUtils(new BudgetArtWeekUtils());
                setUtilsList(new BudgetWeekListUtils(ip));
                // Get gekozen jaar
                String gekozenJaar= String.valueOf(InnerPanel.getJaarcbb()
                    .getSelectedItem());
                String gekozenWeek=String.valueOf(InnerPanel.getWeekcbb()
                    .getSelectedItem());
                // Maak lijst van alle supermarkten
                String[] supermarketList=getUtilsList().getSupermarketsList();
                // 1 Lijst export van alle supermarkten
                getUtilsList().exportTXTAlleSupermarkten(gekozenJaar,gekozenWeek);
                // Per supermarkt export
                for(String s: supermarketList){
                    getUtilsList().exportTXTPerSupermarket(gekozenJaar,
                    gekozenWeek,s);
                }
            }
        }catch(BudgetAppCustomException ex){
            // Aanmaken StringBuilder
            // Ophalen stacktrace
            // Call om stacktrace toe te voegen aan logs.txt
            StringBuilder sb=new StringBuilder();
            sb.append(Arrays.toString(ex.getStackTrace()));
            getUtils().writeLogs(sb);
        }catch(Exception ex){
            // Aanmaken StringBuilder
            // Ophalen stacktrace
            // Call om stacktrace toe te voegen aan logs.txt
            StringBuilder sb=new StringBuilder();
            sb.append(Arrays.toString(ex.getStackTrace()));
            getUtils().writeLogs(sb);
        }  
     } 
}