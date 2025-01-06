package nl.cubicated.budgetapp.controller;

import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import nl.cubicated.budgetapp.model.BudgetArtWeek;
import nl.cubicated.budgetapp.view.ButtonPanel;
import nl.cubicated.budgetapp.view.InnerPanel;
/**
 *
 * @author Tanja Bekker
 * 
 * Naam: BudgetDeleteBTNListener
 * Type: Klasse, ActionListener 
 * Functionaliteit: 
 * - ActionListener voor de delete button in ButtonPanel.
 * - Verwijderen van selecteerde rij(en) in tabel. 
 * - Updaten van totaal uitgaven en saldo over.
 * Per catch in een try-catch en try-catch-with-resources:
 * - Create StringBuilder en append stacktrace van fout
 * - Call naar writeLogs met StringBuilder
 * - Throw new BudgetAppCustomException met cutom text voor user in output.
 */
public class BudgetDeleteBTNListener implements ActionListener {
    private BudgetArtWeekUtils utils=new BudgetArtWeekUtils();
    private static final DecimalFormat decf=new DecimalFormat("0.00");
    private Locale localeNL=BudgetArtWeek.getnLocale();
    private BudgetArtWeek model=new BudgetArtWeek();
    private ButtonPanel btnP;
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
     * Naam: BudgetDeleteBTNListener
     * Type: Constructor
     * @param btnP 
     * @param ip 
     */
    public BudgetDeleteBTNListener(ButtonPanel btnP, InnerPanel ip){
        this.btnP = btnP;
        this.ip=ip;
    }
    
    /**
     * Naam: actionPerformed
     * Type: void method
     * @param e
     * Functionaliteiten: 
     * - Verwijderen van selecteerde rij(en) in tabel. 
     * - Updaten van totaal uitgaven en saldo over.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Check of tabel niet leeg is
        if(getIp().getTable().getRowCount()==0){
            getBtnP().getBtn_delete_row().setEnabled(false);
            getBtnP().getBtn_save_txt().setEnabled(false);
            
        }else{
            // Er staat tenminste 1 rij in de tabel
            removeRowsSelected(getIp().getTable());
            // Update totaal uitgaven en saldo over
            checker();
        } 
    }
    
    /**
     * Naam: removeRowsSelected
     * Type: void method
     * Functionaliteit: 
     * - Verwijder geselecteerde rij(en).
     * Catches: Exception met BudgetAppCustomException
     * @param table 
     */
    public void removeRowsSelected(JTable table){
        try{
            DefaultTableModel dtm=(DefaultTableModel)table.getModel();
            int[] row=table.getSelectedRows();
            if(row.length==0){
                // Geen rows selected
                getIp().getTa_2_checker().setText("Status inputvelden: Als je een rij wilt deleten "
                        + "selecteer deze dan eerst in de tabel en probeer vervolgens opnieuw.");
            }
            else{
                getIp().getTa_2_checker().setText("Status inputvelden:OK.");
                for(int i=0;i<row.length;i++){
                    dtm.removeRow(row[i]-i);
                }
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
                                + "BudgetDeleteBTNListener - removeRowsSelected");
            } catch (BudgetAppCustomException ex1) {
                }
        }
    }
    /**
     * Naam: checker
     * Type: void method
     * Functionaliteiten: 
     * - Ophalen inputwaarden 
     * - Connect met model (setWeekuitgaven,setBudgetVoor)
     * - Call saldoOver()
     * - Call totUitgaven()
     * - Call checkSavingOrNot()
     * - Als Exception dan call BudgetArtWeekUtils.writeLogs()
     */
    public void checker(){
                NumberFormat format=NumberFormat.getInstance(getLocaleNL());
                List<Double> result=new ArrayList<>();
                try {
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
                    getUtils().saldoOver(decf,ip,sum,weekBudgetVoor);
                    // format totaal uitgaven
                    getUtils().totUitgaven(decf,ip,sum);
                    // Info for user
                    getUtils().checkSavingOrNot(ip,sum,weekBudgetVoor);
                } catch (ParseException ex) {
                    try {
                    // Aanmaken StringBuilder
                    // Ophalen stacktrace
                    // call om stacktrace toe te voegen aan logs.txt
                    StringBuilder sb=new StringBuilder();
                    sb.append(Arrays.toString(ex.getStackTrace()));
                    getUtils().writeLogs(sb);
                    // User info
                    throw new BudgetAppCustomException("ParseException - Budgetapp - "
                                + "BudgetDeleteBTNListener - checker");
                    } catch (BudgetAppCustomException ex1) {
                        }
                }
    } 
}