package nl.cubicated.budgetapp.controller;

import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import nl.cubicated.budgetapp.model.BudgetArtWeek;
import nl.cubicated.budgetapp.view.ButtonPanel;
import nl.cubicated.budgetapp.view.InnerPanel;
/**
 *
 * @author Tanja Bekker
 * 
 * Naam: BudgetAddBTNListener
 * Type: Klasse, ActionListener
 * Functionaliteiten:
 * - ActionListener voor de button addBTN in InnerPanel.
 * Per catch in een try-catch en try-catch-with-resources:
 * - Create StringBuilder en append stacktrace van fout
 * - Call naar writeLogs met StringBuilder
 * - Throw new BudgetAppCustomException met custom text voor user in output.
 */

public class BudgetAddBTNListener implements ActionListener {
    private BudgetArtWeekUtils utils=new BudgetArtWeekUtils();
    private static DecimalFormat decf=new DecimalFormat("0.00");
    private Locale localeNL=BudgetArtWeek.getnLocale();
    private BudgetArtWeek model;
    private ButtonPanel btnP;
    private InnerPanel ip;
    // Getters and setters
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
     * @return the model
     */
    public BudgetArtWeek getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(BudgetArtWeek model) {
        this.model = model;
    }
    
    /**
     * Naam: BudgetAddBTNListener
     * Type: Constructor
     * Functionaliteit: 
     * - Twee argumenten constructor
     * @param btnP
     * @param ip 
     */
    public BudgetAddBTNListener(ButtonPanel btnP, InnerPanel ip){
        this.btnP = btnP;
        this.ip=ip;
    }
    
    /**
     * Naam: checkInputValues
     * Type: method
     * Functionaliteit: 
     * 1)controle van input velden en comboboxen:
     * - getTf_1_artName
     * - getTf_2_artPrice
     * - getQtyArt
     * 2) bij fout - laat button disabled 
     * 3) bij goed - enable buttons (addRow, deleteRow,save_txt 
     * 4) - als Exception dan call BudgetArtWeekUtils.writeLogs()
     * catch: 
     * - Exception
     * per catch:
     * - create StringBuilder en append stacktrace van fout
     * - call naar writeLogs met StringBuilder
     * - throw new BudgetAppCustomException met cutom text voor user in output.
     * @param ip
     * @return boolean
     */
    public boolean checkInputValues(InnerPanel ip){
        boolean flag=false;
        try{
            /* Controle op weekbudget, als weekbudgetchecker == x
               dan weergeef fout in textfield,
               maak buttons delete en save text niet klikbaar
               en zet flag op true.
                
            */
            if(getIp().getLbl_3_weekbudget_checker().getText().equals("x")){
                getIp().getTa_2_checker().setText("Status inputvelden: Vul weekbudget in.");
                getBtnP().getBtn_delete_row().setEnabled(false);
                getBtnP().getBtn_save_txt().setEnabled(false);
                flag=true;
            }
            // Controle artName inputveld
            /*
                Als artNamechecker == x
                dan weergeef fout in textfield,
                maak buttons delete en save text niet klikbaar
                en zet flag op true.
            */
            else if((getIp().getLbl_3_artName_checker().getText()).equals("x")){
                getIp().getTa_2_checker().setText("Status inputvelden: Vul correcte artikelnaam in.");
                getBtnP().getBtn_delete_row().setEnabled(false);
                getBtnP().getBtn_save_txt().setEnabled(false);
                flag=true;
            }
            // ArtName is leeg
            /*
                Als artName text equals "" 
                dan weergeef fout in textfield,
                maak buttons delete en save text niet klikbaar
                en zet flag op true.
            */
            else if((getIp().getTf_1_artName().getText()).equals("")){
                getIp().getTa_2_checker().setText("Status inputvelden: Vul correcte artikelnaam in.");
                getBtnP().getBtn_delete_row().setEnabled(false);
                getBtnP().getBtn_save_txt().setEnabled(false);
                flag=true; 
            }
            // Artprice is niet goed
            /*
                Als artprijschecker == x
                dan weergeef fout in textfield,
                maak buttons delete en save text niet klikbaar
                en zet flag op true.
            */
            else if(getIp().getLbl_3_artPrice_checker().getText().equals("x")){
                getIp().getTa_2_checker().setText("Status inputvelden: Vul prijs(per stuk) in.");
                // Set btn delete en export op niet selectable
                getBtnP().getBtn_delete_row().setEnabled(false);
                getBtnP().getBtn_save_txt().setEnabled(false);
            flag=true;
            }
            // Aantal is niet goed
            else if(InnerPanel.getQtyArt().getSelectedItem().equals(0)){
                getIp().getTa_2_checker().setText("Status inputvelden: Kies aantal.");
                // Set btn delete en export op niet selectable
                getBtnP().getBtn_delete_row().setEnabled(false);
                getBtnP().getBtn_save_txt().setEnabled(false);
                flag=true;
            }else{
            // Alle velden zijn goed
                getIp().getTa_2_checker().setText("Status inputvelden: OK.");
                getBtnP().getBtn_addRow().setEnabled(true);
                getBtnP().getBtn_delete_row().setEnabled(true);
                getBtnP().getBtn_save_txt().setEnabled(true);
                // Add a row
                DefaultTableModel modelDTM=(DefaultTableModel)getIp().getTable().getModel();
                modelDTM.addRow(new Object[]{
                    InnerPanel.getSupermarketNames().getSelectedItem(),
                    getIp().getTf_1_artName().getText(), 
                    InnerPanel.getQtyArt().getSelectedItem(),
                    getIp().getTf_2_artPrice().getText(),
                    getIp().getLbl_2_totArtPrice().getText(),
                    getIp().getLbl_1_discount_ans().getText(),
                    getIp().getLbl_1_bought_ans().getText()
                });
                flag=false;
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
                        + "BudgetAddBTNListener - checkInputValues");
            } catch (BudgetAppCustomException ex1) {
            }
        }
        return flag;
    }
    
    /**
     * Naam: actionPerformed
     * Type: void method
     * Functionaliteit: 
     * - Check inputvalue
     * - Als geen fout dan call checker()
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // check inputvalues
        boolean isInputFout=checkInputValues(ip);
        if(!isInputFout){
            // update totaal uitgaven en saldo over
            checker();
        } 
    } 
    
    /**
     * Naam: checker
     * Type: void method
     * Functionaliteit: 
     * - Ophalen inputwaarden 
     * - Connect met model (setWeekuitgaven,setBudgetVoor)
     * - Call saldoOver()
     * - Call totUitgaven()
     * - Call checkSavingOrNot()
     * Catches:
     * - Exception
     * Per catch:
     * - Create StringBuilder en append stacktrace van fout
     * - Call naar writeLogs met StringBuilder
     * - Throw new BudgetAppCustomException met cutom text voor user in output.
     */
    public void checker(){
        NumberFormat format=NumberFormat.getInstance(getLocaleNL());
        List<Double> result=new ArrayList<>();
        try {
            setModel(new BudgetArtWeek());
            // Alle totaal bedragen uit tabel inladen
            double sum=0;
            for(int i=0;i<getIp().getTable().getRowCount();i++){
                Number num1;
                num1 = NumberFormat.getInstance().parse((String) getIp().getTable().getValueAt(i, 4));
                sum+=num1.doubleValue(); 
            }
            Number num_4=format.parse(getIp().getTf_1_weekBudgetVoor().getText());
            // Parse uitgaven
            double weekBudgetVoor=num_4.doubleValue();
            getModel().setWeekuitgaven(result);
            getModel().setBudgetVoor(weekBudgetVoor);
            // Bereken totaal saldo over
            getUtils().saldoOver(getDecf(),ip,sum,weekBudgetVoor);
            // Format totaal uitgaven
            getUtils().totUitgaven(getDecf(),ip,sum);
            // Info for user
            getUtils().checkSavingOrNot(ip,sum,weekBudgetVoor);
        } catch (ParseException ex) {
            try {
                // Aanmaken StringBuilder
                // Ophalen stacktrace
                // Call om stacktrace toe te voegen aan logs.txt
                StringBuilder sb=new StringBuilder();
                sb.append(Arrays.toString(ex.getStackTrace()));
                getUtils().writeLogs(sb);
                // User info
                throw new BudgetAppCustomException("ParseException - Budgetapp - "
                                + "BudgetAddBTNListener - checker");
            } catch (BudgetAppCustomException ex1) {
                }
        }
    }  
}