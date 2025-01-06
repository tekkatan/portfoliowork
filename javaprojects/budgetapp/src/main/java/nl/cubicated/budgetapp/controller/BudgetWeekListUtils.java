package nl.cubicated.budgetapp.controller;

import java.io.*;
import java.text.*;
import java.util.*;
import nl.cubicated.budgetapp.view.InnerPanel;
/**
 *
 * @author Tanja Bekker
 * Naam: BudgetWeekListUtils
 * Type: Klasse
 * Functionaliteiten:
 * - Utility klasse voor export van boodschappenlijsten in .txt formaat.
 */
public class BudgetWeekListUtils implements BudgetWeekListInterface{
    private static final DecimalFormat decf=new DecimalFormat("0.00");
    private BudgetArtWeekUtils utils=new BudgetArtWeekUtils();
    private InnerPanel ip;
    
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
     * Naam: BudgetWeekListUtils
     * Type: Controller
     * Functionaliteit: 
     * - Assign param InnerPanel aan klasse eigen InnerPanel param.
     * @param ip 
     */
    public BudgetWeekListUtils(InnerPanel ip){
        this.ip=ip;
    }
    
    /**
     * Naam: printDutchNumberFormat
     * Type: method
     * Functionaliteit: 
     * - Convert naar Nederlands nummerformaat met twee decimalen achter de komma
     * @param number
     * @return String
     * @throws BudgetAppCustomException 
     */
    @Override
    public String printDutchNumberFormat(double number) 
            throws BudgetAppCustomException {
       String result="";
        try{
            // format number
            result+=decf.format(number);
        }catch(NumberFormatException e){
            try {
                throw new BudgetAppCustomException("Exception in "
                        + "BudgetSaveTXTListener - printDutchNumberFormat");
            } catch (BudgetAppCustomException ex) {
                // Aanmaken StringBuilder
                // Ophalen stacktrace
                // Call om stacktrace toe te voegen aan logs.txt
                StringBuilder sb=new StringBuilder();
                sb.append(Arrays.toString(ex.getStackTrace()));
                getUtils().writeLogs(sb);
            }
        }catch(Exception ex){
            // Aanmaken StringBuilder
            // Ophalen stacktrace
            // Call om stacktrace toe te voegen aan logs.txt
            StringBuilder sb=new StringBuilder();
            sb.append(Arrays.toString(ex.getStackTrace()));
            getUtils().writeLogs(sb);
        }
        return result; 
    }
    /**
    * Naam: getTotalUitgavenSupermarkt
    * Type: method
    * @return String
    * Functionaliteit: sum uitgaven per gegeven supermarkt
    * @param givenSupermarket
    * @throws BudgetAppCustomException
    */
    @Override
    public String getTotalUitgavenSupermarkt(String givenSupermarket) throws BudgetAppCustomException {
        String totalUitgaven="";
        double sum=0;
        try{
            // Loop door alle rows van table
            for(int i=0;i<getIp().getTable().getRowCount();i++){
                // Als row i column 0 gelijk is aan givenSupermarket
                // parse totalsub uit row 1 column 4 naar Number format
                // Voeg gevonden totalsub toe aan double sum
                if(getIp().getTable().getModel().getValueAt(i,0)
                        .equals(givenSupermarket)){
                    Number num1;
                    num1=NumberFormat.getInstance().parse((String)getIp()
                            .getTable().getValueAt(i, 4));
                    sum+=num1.doubleValue();  
                }
            }
            // Zet sum om naar Nederlands nummerformaat en voeg toe aan 
            // String totalUitgaven
            totalUitgaven+=printDutchNumberFormat(sum);
        }catch(ParseException ex){
            // Aanmaken StringBuilder
            // Ophalen stacktrace
            // Call om stacktrace toe te voegen aan logs.txt
            StringBuilder sb=new StringBuilder();
            sb.append(Arrays.toString(ex.getStackTrace()));
            getUtils().writeLogs(sb);
        }
        // Return de String totalUitgaven met sum
        return totalUitgaven;
    }
    /**
    * Naam: addExtraSpaces
    * Type: method
    * Functionaliteit: check if input is 16 char long
                        if not dan voeg spaces toe tot 16 char long
    * Catches Exception
    * @param input
    * @param space
    * @throws BudgetAppCustomException
    * @return String
    */
    @Override
    public String addExtraSpaces(String input, int space) throws BudgetAppCustomException {
        String result="";
        try{
            // String voor spaces
            String spaces="";
            // Als input kleiner is dan space 
                    if(input.length()<space){
                        // Bereken input.length en assign aan int len
                        int len=input.length();
                        // Bereken spaces to add en assign aan int 
                        // SpacesToAdd 
                        int spacesToAdd=space-len;
                        // Loop door spacesToAdd en voeg per loop een spatie
                        // toe aan spaces String
                        for(int x=0;x<spacesToAdd;x++){
                            spaces+=" ";
                        }
                    }
                    // Add spaces to artname middels een StringBuilder
                    StringBuilder sbSpaces=new StringBuilder();
                    sbSpaces.append(input).append(spaces);
                    result=sbSpaces.toString();
        }catch(Exception ex){
            // Aanmaken StringBuilder
            // Ophalen stacktrace
            // Call om stacktrace toe te voegen aan logs.txt
            StringBuilder sb=new StringBuilder();
            sb.append(Arrays.toString(ex.getStackTrace()));
            getUtils().writeLogs(sb);
            throw new BudgetAppCustomException("Exception - Budgetapp - "
                    + "BudgetSaveTXTListener - addExtraSpaces");
        }
        return result;
    }
    /**
     * Naam: exportTXTPerSupermarket
     * Type: method
     * Functionaliteit: 
     * - Maak lijst van alle supermarkten
     * - export lijst per supermarkt
     * Catches: IOException, Exception
     * @param givenJaar
     * @param givenWeek
     * @param givenSupermarket
     * @throws BudgetAppCustomException 
     */
    @Override
    public void exportTXTPerSupermarket(String givenJaar, String givenWeek, String givenSupermarket) throws BudgetAppCustomException {
        String weekSaldo="";
        String totuitgavenSupermarkt="";
        String totuitgaven="";
        String totSaldoNa="";
        String text_totUitgavenSupermarkt="";
        String text_totUitgaven="";
        String text_saldoOver="";
        String text_datum="";
        String text_supermarket="";
        
        try(BufferedWriter bf=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                "src/main/resources/weeklijsten/"+
                givenJaar+""+givenWeek+givenSupermarket+".txt",false),"UTF-8" ))){
            // Header
            text_datum+="Datum:      "+String.valueOf(getIp().getLbl_3_wk().getText());
            text_supermarket+="Supermarkt: "+givenSupermarket;
            bf.write(text_supermarket);
            bf.newLine();
            bf.write(text_datum);
            bf.newLine();
            bf.write("-----------------------------------------------------"
                    + "-------------------------------------------------");
            
            // Add column names
            StringJoiner join=new StringJoiner(" | ");
            for(int i=1;i<getIp().getTable().getColumnCount();i++){
                StringBuilder  colName=new StringBuilder();
                colName.append(getIp().getTable().getColumnName(i)).append("        ");
                join.add(colName);
            }
            bf.newLine();
            bf.write(join.toString());
            bf.newLine();
            
            // Voegt alle rows toe aan de file
            for(int i=0;i<getIp().getTable().getRowCount();i++){
                join=new StringJoiner(";");
                // If supermarket is found
                if(getIp().getTable().getModel().getValueAt(i,0)
                        .equals(givenSupermarket)){
                    String str1=String.valueOf(getIp().getTable().getValueAt(i,1));
                    String str2=String.valueOf(getIp().getTable().getValueAt(i,2));
                    String str3=String.valueOf(getIp().getTable().getValueAt(i,3));
                    String str4=String.valueOf(getIp().getTable().getValueAt(i,4));
                    String str5=String.valueOf(getIp().getTable().getValueAt(i,5));
                    String str6=String.valueOf(getIp().getTable().getValueAt(i,6));
                
                    // Check if str1 aka artikelnaam 16 als length heeft,
                    // zo niet dan voeg spaties toe
                    // voor uitlijning lijst
                    String artNameStr=addExtraSpaces(str1,16);
                    String aantalStr=addExtraSpaces(str2,11);
                    String subTotStr=addExtraSpaces(str3,16);
                    String totStr=addExtraSpaces(str4,19);
                    String aanbiedStr=addExtraSpaces(str5,19);
                    // Create StringBuilder en append values of row
                    StringBuilder sb=new StringBuilder();
                    sb.append(artNameStr).append("");
                    sb.append("| ").append(aantalStr).append(" stk").append("");
                    sb.append("| ").append("\u20AC").append(subTotStr).append("");
                    sb.append("| ").append("\u20AC").append(totStr).append("");
                    sb.append("| ").append(aanbiedStr).append("");
                    sb.append("| ").append(str6).append("    ");
                    // Combine them
                    join.add(sb);
                // Write
                bf.newLine();
                bf.write(join.toString());
                bf.newLine();
                }
            }
            // Ophalen statistieken
            totuitgavenSupermarkt+=getTotalUitgavenSupermarkt(givenSupermarket);
            totuitgaven+=String.valueOf(getIp().getTf_totaalUitgaven().getText());
            totSaldoNa+=String.valueOf(getIp().getTf_saldoOver().getText());
            weekSaldo+="Weekbudget:                                     "
                    +"\u20AC"+String.valueOf(getIp().getTf_1_weekBudgetVoor().getText());
            text_totUitgavenSupermarkt+="Totaal uitgaven deze supermarkt:                "+
                    "\u20AC"+totuitgavenSupermarkt;
            text_totUitgaven+="Totaal uitgaven (andere supermarkten):          "+
                    "\u20AC"+totuitgaven;
            text_saldoOver+="Saldo over:                                     "+
                    "\u20AC"+totSaldoNa;
            // Statistieken schrijven
            bf.write("------------------------------------------------------"
                    + "------------------------------------------------");
            bf.newLine();
            bf.write(weekSaldo);
            bf.newLine();
            bf.write(text_totUitgavenSupermarkt);
            bf.newLine();
                bf.write(text_totUitgaven);
                bf.newLine();
                bf.write(text_saldoOver);
         }catch(IOException ex){
            // Aanmaken StringBuilder
            // Ophalen stacktrace
            // Call om stacktrace toe te voegen aan logs.txt
            StringBuilder sb=new StringBuilder();
            sb.append(Arrays.toString(ex.getStackTrace()));
            getUtils().writeLogs(sb);
            // User info
            throw new BudgetAppCustomException("IOException - Budgetapp - BudgetSaveTXTListener - exportTXTPerSupermarket");
        }catch(Exception ex){
            // Aanmaken StringBuilder
            // Ophalen stacktrace
            // Call om stacktrace toe te voegen aan logs.txt
            StringBuilder sb=new StringBuilder();
            sb.append(Arrays.toString(ex.getStackTrace()));
            getUtils().writeLogs(sb);
            // User info
            throw new BudgetAppCustomException("Exception - Budgetapp - BudgetSaveTXTListener - exportTXTPerSupermarket");
        }
    }
    /**
     * Naam: getSupermarketsList
     * Type: method
     * Functionaliteit: ophalen supermarktnamen welke aanwezig zijn in 
     *                  de rows van de JTable
     * catches: Exception
     * @return String[]
     * @throws BudgetAppCustomException 
     */
    @Override
    public String[] getSupermarketsList() throws BudgetAppCustomException {
        List<String> list=new ArrayList<>();
        String[] supermarketlist={};
        try{
            // Retrieve supermarketNames from table
            for(int i=0;i<getIp().getTable().getRowCount();i++){
                list.add((String) getIp().getTable().getModel().getValueAt(i, 0));
            }
            // Convert ArrayList to String[]
            supermarketlist=list.toArray(new String[0]);
            
        }catch(Exception ex){
            // Aanmaken StringBuilder
            // Ophalen stacktrace
            // Call om stacktrace toe te voegen aan logs.txt
            StringBuilder sb=new StringBuilder();
            sb.append(Arrays.toString(ex.getStackTrace()));
            getUtils().writeLogs(sb);
            // User info
            throw new BudgetAppCustomException("Exception - Budgetapp - BudgetSaveTXTListener - getSupermarketsList");
        }
        return supermarketlist;
    }
    /**
     * Naam: exportTXTAlleSupermarkten
     * Type: void method
     * Functionaliteit: 
     * - Maak aan txt bestand 
     * - Vul txt bestand met inhoud afkomstig van JTable
     * - Sla txt bestand op in resources
     * Catches: IOException
     * @param givenJaar
     * @param givenWeek
     * @throws BudgetAppCustomException 
     * 
     */
    @Override
    public void exportTXTAlleSupermarkten(String givenJaar, String givenWeek) throws BudgetAppCustomException {
         try(BufferedWriter bf=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                "src/main/resources/weeklijsten/"+
                givenJaar+""+givenWeek+"AlleSupermarkten"+".txt",false),"UTF-8" ))){
             
            String weekSaldo="";
            String text_totUitgaven;
            String text_saldoOver;
            String text_datum="";
            // Header
            text_datum+="Datum:      "+String.valueOf(getIp().getLbl_3_wk().getText());
            bf.write(text_datum);
            bf.newLine();
            bf.write("---------------------------------------------------------------------------------------------------------------------------------------");
            bf.newLine();
            // Add column names
            StringJoiner join=new StringJoiner(" | ");
            for(int i=0;i<getIp().getTable().getColumnCount();i++){
                StringBuilder  colName=new StringBuilder();
                switch (i) {
                    case 0:
                        colName.append(getIp().getTable().getColumnName(i)).append("                 ");
                        break;
                    case 1:
                        colName.append(getIp().getTable().getColumnName(i)).append("        ");
                        break;
                    case 2:
                        colName.append(getIp().getTable().getColumnName(i)).append("        ");
                        break;
                    case 3:
                        colName.append(getIp().getTable().getColumnName(i)).append("        ");
                        break;
                    case 4:
                        colName.append(getIp().getTable().getColumnName(i)).append("        ");
                        break;
                    default:
                        colName.append(getIp().getTable().getColumnName(i)).append("        ");
                        break;
                }
                join.add(colName);
            }
            bf.write(join.toString());
            bf.newLine();
            
            // Voegt alle rows toe aan de file
            for(int i=0;i<getIp().getTable().getRowCount();i++){
                join=new StringJoiner(";");
                    String str1=String.valueOf(getIp().getTable().getValueAt(i,0));
                    String str2=String.valueOf(getIp().getTable().getValueAt(i,1));
                    String str3=String.valueOf(getIp().getTable().getValueAt(i,2));
                    String str4=String.valueOf(getIp().getTable().getValueAt(i,3));
                    String str5=String.valueOf(getIp().getTable().getValueAt(i,4));
                    String str6=String.valueOf(getIp().getTable().getValueAt(i,5));
                    String str7=String.valueOf(getIp().getTable().getValueAt(i,6));
                    // Check if str1 aka artikelnaam 16 als length heeft,
                    // zo niet dan voeg spaties toe
                    // voor uitlijning lijst
                    String supermarketStr=addExtraSpaces(str1,28);
                    String artNameStr=addExtraSpaces(str2,16);
                    String aantalStr=addExtraSpaces(str3,11);
                    String subTotStr=addExtraSpaces(str4,16);
                    String totStr=addExtraSpaces(str5,19);
                    String aanbiedStr=addExtraSpaces(str6,19);
                    // Create StringBuilder en append values of row
                    StringBuilder sb=new StringBuilder();
                    sb.append(supermarketStr).append("");
                    sb.append("| ").append(artNameStr).append("");
                    sb.append("| ").append(aantalStr).append(" stk").append("");
                    sb.append("| ").append("\u20AC").append(subTotStr).append("");
                    sb.append("| ").append("\u20AC").append(totStr).append("");
                    sb.append("| ").append(aanbiedStr).append("");
                    sb.append("| ").append(str7).append("    ");
                    
                    // Combine them
                    join.add(sb);
                // Write
                bf.write(join.toString());
                bf.newLine();
            }
            // Statistieken ophalen en schrijven
            weekSaldo+="Weekbudget:        "+"\u20AC"+String.valueOf(getIp().getTf_1_weekBudgetVoor().getText());
            text_totUitgaven="Totaal uitgaven:   \u20AC"+getIp().getTf_totaalUitgaven().getText();
            text_saldoOver="Saldo over:        \u20AC"+getIp().getTf_saldoOver().getText();
            bf.write("---------------------------------------------------------------------------------------------------------------------------------------");
            bf.newLine();
                bf.write(weekSaldo);
                bf.newLine();
                bf.write(text_totUitgaven);
                bf.newLine();
                bf.write(text_saldoOver);
         }catch(IOException ex){
            // Aanmaken StringBuilder
            // Ophalen stacktrace
            // Call om stacktrace toe te voegen aan logs.txt
            StringBuilder sb=new StringBuilder();
            sb.append(Arrays.toString(ex.getStackTrace()));
            getUtils().writeLogs(sb);
            // User info
            throw new BudgetAppCustomException("IOException - Budgetapp - BudgetSaveTXTListener - exportTXTAlleSupermarkten");
           
        }catch(Exception ex){
            // Aanmaken StringBuilder
            // Ophalen stacktrace
            // Call om stacktrace toe te voegen aan logs.txt
            StringBuilder sb=new StringBuilder();
            sb.append(Arrays.toString(ex.getStackTrace()));
            getUtils().writeLogs(sb);
            // User info
            throw new BudgetAppCustomException("Exception - Budgetapp - BudgetSaveTXTListener - exportTXTAlleSupermarkten");
        }
    }
}