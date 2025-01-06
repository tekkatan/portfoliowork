package nl.cubicated.budgetapp.controller;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import nl.cubicated.budgetapp.view.InnerPanel;
/**
 *
 * @author Tanja Bekker
 * 
 * Naam: BudgetArtWeekUtils
 * Type: Klasse
 * Functionaliteiten:
 * - Implements abstract methoden uit BudgetArtUtilsInterface
 * - Utility class voor veelgebruikte methoden in de app
 * Per catch in een try-catch en try-catch-with-resources:
 * - Create StringBuilder en append stacktrace van fout
 * - Call naar writeLogs met StringBuilder
 * - Throw new BudgetAppCustomException met cutom text voor user in output.
 */

public class BudgetArtWeekUtils implements BudgetArtUtilsInterface{
    /**
     * Naam: displayGivenWeekDate
     * Type: method
     * Functionaliteiten:
     * - Weergeef datum in Nederlands formaat
     * - Aanmaken datum van ma(ingegeven week en jaar) t/m zo(ingegeven week
     *   en jaar) in formaat dd/MM/yyyy
     * @param givenYear
     * @param givenWeek
     * @return static String
     * @throws BudgetAppCustomException 
     */
    @Override
    public String displayGivenWeekDate(int givenYear,long givenWeek)throws BudgetAppCustomException{
        String d1="";
        String d2="";

        try{
            if(givenYear==0){
                return "Datum niet gevonden";
            }else if(givenWeek==0){
                return "Datum niet gevonden";
            }else{
                // Maandag
                WeekFields weekFields=WeekFields.of(Locale.getDefault());
                LocalDateTime dt1=LocalDateTime.now()
                .withYear(givenYear)
                .with(weekFields.weekOfYear(),givenWeek)
                .with(weekFields.dayOfWeek(),1);
                // Zondag
                LocalDateTime dt2=LocalDateTime.now()
                .withYear(givenYear)
                .with(weekFields.weekOfYear(),givenWeek)
                .with(weekFields.dayOfWeek(),7);
                //Formatted for the Netherlands
                d1+=dt1.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                d2+=dt2.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                }
            }
            catch(Exception ex){
                // Aanmaken StringBuilder
                // Ophalen stacktrace
                // Call om stacktrace toe te voegen aan logs.txt
                StringBuilder sb=new StringBuilder();
                sb.append(Arrays.toString(ex.getStackTrace()));
                writeLogs(sb);
                throw new BudgetAppCustomException("Exception - BudgetArtWeekUtils - displayGivenWeekDate");
            }
            return d1+" t/m "+ d2;
    }

    /**
     * Naam: checkSpecialCharacter
     * Type: method
     * Functionaliteiten:
     * - Controle input op aanwezigheid van speciale characters
     *   middels regex pattern en pattern.matcher
     * @param input
     * @return boolean
     * @throws BudgetAppCustomException 
     */
    @Override
    public boolean checkSpecialCharacter(String input) throws BudgetAppCustomException{
        boolean flag=false;
        try{
            Pattern patternCpecialChar=Pattern
                    .compile("[)(*&^%$#@!?//[//]}{><~`+=|/\\\\]");
            Matcher foundSpecialChar=patternCpecialChar.matcher(input);
            flag=foundSpecialChar.find();
        }catch(Exception ex){
            // Aanmaken StringBuilder
            // Ophalen stacktrace
            // Call om stacktrace toe te voegen aan logs.txt
            StringBuilder sb=new StringBuilder();
            sb.append(Arrays.toString(ex.getStackTrace()));
            writeLogs(sb);
            // User info
            throw new BudgetAppCustomException("Exception: BudgetArtWeekUtils "
                    + "- checkSpecialCharacter ");
            
        }
        return flag;
    }
    // Pattern checker for two word input with space character
    /**
     * Naam: checkWordsValues
     * Type: method
     * Functionaliteiten:
     * - Controle input op woord met alfabetische tekens en een spatie
     *   middels regex pattern en pattern.matcher
     * @param input
     * @return boolean
     * @throws BudgetAppCustomException 
     */
    @Override
    public boolean checkWordsValues(String input) throws BudgetAppCustomException{
        boolean flag=false;
        try{
            Pattern patternWords=Pattern.compile("[a-zA-Z]|[a-zA-Z]+\\s+[a-zA-Z]+");
            Matcher foundSpecialChar=patternWords.matcher(input);
            flag=foundSpecialChar.find();
        }catch(Exception ex){
            // Aanmaken StringBuilder
            // Ophalen stacktrace
            // Call om stacktrace toe te voegen aan logs.txt
            StringBuilder sb=new StringBuilder();
            sb.append(Arrays.toString(ex.getStackTrace()));
            writeLogs(sb);
            // User info
            throw new BudgetAppCustomException("Exception: BudgetArtWeekUtils - checkWordsValues ");
        }
        return flag;
    }
    
    // Pattern checker voor numerieke input
    /**
     * Naam: checkNumericValues
     * Type: method
     * Functionaliteiten:
     * - Controle input op numerieke tekens
     *   middels regex pattern en pattern.matcher
     * @param input
     * @return boolean
     * @throws BudgetAppCustomException 
     */
    @Override
    public boolean checkNumericValues(String input) throws BudgetAppCustomException{
        boolean flag=false;
        try{
            Pattern patternNumeric=Pattern.compile("[0-9]+");
            Matcher foundNumeric=patternNumeric.matcher(input);
            flag=foundNumeric.find();
        }catch(Exception ex){
            // Aanmaken StringBuilder
            // Ophalen stacktrace
            // Call om stacktrace toe te voegen aan logs.txt
            StringBuilder sb=new StringBuilder();
            sb.append(Arrays.toString(ex.getStackTrace()));
            writeLogs(sb);
            // User info
            throw new BudgetAppCustomException("Exception: BudgetArtWeekUtils - checkNumericValues ");
        }
        return flag;
    }
    // Pattern checker voor bedragen met comma
    /**
     * Naam: commaChecker
     * Type: method
     * Functionaliteiten:
     * - Controle input op een prijs met een komma
     * - Controle input op lege waarde en nul-waarden
     * - Prijs mag 3 tekens voor de komma en 2 tekens achter te komma bevatten.
     * - Controle middels regex pattern en pattern.matcher
     * @param weekbudgetvoor
     * @return boolean
     * @throws BudgetAppCustomException 
     */
    @Override
    public boolean commaChecker(String weekbudgetvoor) throws BudgetAppCustomException{
        boolean flag=false;
        try{
            /* Als weekbudgetvoor een length heeft van meer dan 0
            *  Check dan of er tekens anders dan een komma en numerieke waarden
            *  in zitten.
            */ 
            if(weekbudgetvoor.length()>0){
                Pattern  letterChar=Pattern.
                    compile("^\\d{1,3}(,\\d{1,2})?$");
                Matcher foundLetterChar=letterChar.matcher(weekbudgetvoor);
                flag=foundLetterChar.find();
                return flag;
            // Als waarde leeg is
            }else{
                return flag;
            }
        }catch(Exception ex){
            // Aanmaken StringBuilder
            // Ophalen stacktrace
            // Call om stacktrace toe te voegen aan logs.txt
            StringBuilder sb=new StringBuilder();
            sb.append(Arrays.toString(ex.getStackTrace()));
            writeLogs(sb);
            // User info
            throw new BudgetAppCustomException("Exception: BudgetArtWeekUtils - commaChecker "); 
        }
    }
    
    /**
     * Naam: saldoOver
     * Type: void method
     * Functionaliteiten: 
     * - Bereken saldo na aftrek uitgaven, returns double saldo
     * Catches: Exception en BudgetAppCustomException
     * @param decf
     * @param ip
     * @param uitgavenTotaal
     * @param saldoWeek 
     */
    @Override
    public void saldoOver(DecimalFormat decf,InnerPanel ip,
            double uitgavenTotaal, double saldoWeek){
        double saldoOver;
        try{ 
            saldoOver=saldoWeek-uitgavenTotaal;
            // Saldo over
            // Convert to Dutch format
            String str=printDutchNumberFormat(decf,saldoOver);
            ip.getTf_saldoOver().setText(str);
        }catch(Exception ex){
            try {
                // Aanmaken StringBuilder
                // Ophalen stacktrace
                // Call om stacktrace toe te voegen aan logs.txt
                StringBuilder sb=new StringBuilder();
                sb.append(Arrays.toString(ex.getStackTrace()));
                writeLogs(sb);
                // User info
                throw new BudgetAppCustomException("Exception - Budgetapp - "
                                + "BudgetDeleteBTNListener - saldoOver");
            } catch (BudgetAppCustomException ex1) {
                }
        }
    }
    /**
     *
    Naam: calcTotPrice
    Type: void method
    Functionaliteiten:
    - Berekenen totaalprijs
    - Assignen aan totaalprijslabel in gui
     * @param decf
     * @param ip
    @throws BudgetAppCustomException
    @param qty
    @param artSubPrice
    catches: ArithmeticException| BudgetAppCustomException
    */
    @Override
    public void calcTotPrice(DecimalFormat decf, InnerPanel ip,
            int qty,double artSubPrice)throws BudgetAppCustomException{
        
        try{
            double totPrice=qty*artSubPrice;
            // Convert to Dutch format
            String str=printDutchNumberFormat(decf,totPrice);
            ip.getLbl_2_totArtPrice().setText(str);
        }catch(NumberFormatException ex){
            // Aanmaken StringBuilder
            // Ophalen stacktrace
            // Call om stacktrace toe te voegen aan logs.txt
            StringBuilder sb=new StringBuilder();
            sb.append(Arrays.toString(ex.getStackTrace()));
            writeLogs(sb);
            throw new BudgetAppCustomException("NumberFormatException - "
                    + "BudgetArtPriceListener - "
                    + "calcTotPrice");
        }catch(ArithmeticException ex){
            // Aanmaken StringBuilder
            // Ophalen stacktrace
            // Call om stacktrace toe te voegen aan logs.txt
            StringBuilder sb=new StringBuilder();
            sb.append(Arrays.toString(ex.getStackTrace()));
            writeLogs(sb);
            throw new BudgetAppCustomException("ArithmeticException - "
                    + "BudgetArtPriceListener - "
                    + "calcTotPrice");
        } 
    }
    /**
     * Naam: totUitgaven
     * Type: void method
     * Functionaliteiten: 
     * - Format totaal uitgaven
     * - Call printDutchNumberFormat()
     * - Als Exception dan call BudgetArtWeekUtils.writeLogs()
     * Return type: void
     * Catch: Exception en BudgetAppCustomException
     * @param decf
     * @param ip
     * @param sum 
     */
    @Override
    public void totUitgaven(DecimalFormat decf,InnerPanel ip,double sum){
        try{
            String str=printDutchNumberFormat(decf,sum);
            ip.getTf_totaalUitgaven().setText(str);
        }catch(Exception ex){
            try {
                // Aanmaken StringBuilder
                // Ophalen stacktrace
                // Call om stacktrace toe te voegen aan logs.txt
                StringBuilder sb=new StringBuilder();
                sb.append(Arrays.toString(ex.getStackTrace()));
                writeLogs(sb);
                // User info
                throw new BudgetAppCustomException("Exception - Budgetapp - "
                    + "BudgetDeleteBTNListener - checker");
            } catch (BudgetAppCustomException ex1) {
                }
        }
    }
    /**
     * Naam: checkSavingOrNot
     * Type: void method
     * Functionaliteiten: 
     * - Controleer of user kan sparen of niet
     * Catch: Exception en BudgetAppCustomException
     * @param ip
     * @param sum
     * @param saldoWeek 
     */
    @Override
    public void checkSavingOrNot(InnerPanel ip,double sum, double saldoWeek){
        try{
            double saldoOver=saldoWeek-sum;
            if(saldoOver==0){
                // Sparen niet mogelijk
                ip.getTa_1_checker().setText("Het is niet mogelijk om nu "
                        + "te sparen, kijk of er ergens een aanbieding is van een artikel.");
            }else if(saldoOver<0){
                // Teveel uitgegeven, kijk eens of er een aanbieding ergens is
                // of pas je lijst aan totdat je saldo positief is.
                ip.getTa_1_checker().setText("Uw saldo is negatief. Kijk of er ergens een aanbieding is van een artikel of verwijder "
                        + "een artikel uit de lijst om een positief saldo te krijgen.");
            }else{
                // User kan sparen
                ip.getTa_1_checker().setText("Geweldig, u kunt sparen deze week!");
            }
            
        }catch(Exception ex){
            try {
                // Aanmaken StringBuilder
                // Ophalen stacktrace
                // Call om stacktrace toe te voegen aan logs.txt
                StringBuilder sb=new StringBuilder();
                sb.append(Arrays.toString(ex.getStackTrace()));
                writeLogs(sb);
                // User info
                throw new BudgetAppCustomException("Exception - Budgetapp - "
                                + "BudgetDeleteBTNListener - checkSavingOrNot");
            } catch (BudgetAppCustomException ex1) {
                }
        }
    }
    // Switch voor het checken op zero-based values
    /**
     * Naam: checkZeroValues
     * Type: method
     * Functionaliteiten:
     * - Controle String input op "",  00,00 en 0,00 
     * @param input
     * @return boolean
     * @throws BudgetAppCustomException 
     */
    @Override
    public boolean checkZeroValues(String input) throws BudgetAppCustomException{
        boolean flag=false;
        try{
            switch (input) {
                case "":
                    // Value is not allowed to be zero
                    flag=true;
                    break;
                case "00,00":
                    // Value is not allowed to be zero
                    flag=true;
                    break;
                case "0,00":
                    // Value is not allowed to be zero
                    flag=true;
                    break;
                default:
                    break;
            }
        }
        catch(Exception ex){
            // Aanmaken StringBuilder
            // Ophalen stacktrace
            // Call om stacktrace toe te voegen aan logs.txt
            StringBuilder sb=new StringBuilder();
            sb.append(Arrays.toString(ex.getStackTrace()));
            writeLogs(sb);
            // User info
            throw new BudgetAppCustomException("Exception - BudgetArtWeekUtils - checkZeroValues ");
        }
        return flag;
    }
    /**
     * Naam: calculateTotPrijs
     * Type: void method
     * Functionaliteiten: 
     * - Parameter convert is aantal stuks
     * - Get artikelprijs, zet komma's over naar een punt 
     *   om later berekeningen in double format mogeljk te maken.- parse artikelprijs naar double
     * - Bereken subprijs
     * - Bereken totprijs door subprijs * convert
     * Catches:
     * - NumberFormatException
     * @param ip
     * @param convert 
     */
    @Override
    public void calculateTotPrijs(InnerPanel ip,int convert){
        // Calculate total price
        try{
            String convertedD=ip.getTf_2_artPrice().getText()
                .replace(",", ".");
            // Parse to double
            // Bereken subPrijs
            double subPrice=Double.parseDouble(convertedD);
            // Bereken totPrijs
            double totPrice=convert*subPrice;
            // Sla op totPrijs
            ip.getBaw().setTotArtPrice(totPrice);
            String totPriceStr=String.format("%.2f", totPrice);
            //  Laat totPrijs zien in GUI
            ip.getLbl_2_totArtPrice().setText(totPriceStr);
        }catch(NumberFormatException ex){
            try {
                // Aanmaken StringBuilder
                // Ophalen stacktrace
                // Call om stacktrace toe te voegen aan logs.txt
                StringBuilder sb=new StringBuilder();
                sb.append(Arrays.toString(ex.getStackTrace()));
                writeLogs(sb);
                // User info
                throw new BudgetAppCustomException("NullPointerException - Budgetapp - "
                                + "BudgetQtyArtListener - itemStateChanged");
            } catch (BudgetAppCustomException ex1) {
                }
        }
    }
    /**
     * Naam: printDutchNumberFormat
     * Type: method
     * Functionaliteiten: 
     * - Convert to NL numberformat met twee decimalen achter de komma
     * Catches:
     * - NumberFormatException
     * - Exception
     * Per catch:
     * - Create StringBuilder en append stacktrace van fout
     * - Call naar writeLogs met StringBuilder
     * - Throw new BudgetAppCustomException met cutom text voor user in output.
     * @param decf
     * @param number
     * @return String
     */
    @Override
    public String printDutchNumberFormat(DecimalFormat decf,double number){
        String result="";
        try{
            result+=decf.format(number);
        }catch(NumberFormatException ex){
            try {
                // Aanmaken StringBuilder
                // Ophalen stacktrace
                // Call om stacktrace toe te voegen aan logs.txt
                StringBuilder sb=new StringBuilder();
                sb.append(Arrays.toString(ex.getStackTrace()));
                writeLogs(sb);
                // User info
                throw new BudgetAppCustomException("NumberFormatException - Budgetapp - "
                                + "BudgetAddBTNListener - printDutchNumberFormat");
            } catch (BudgetAppCustomException ex1) {
                }
        }catch(Exception ex){
            try {
                // Aanmaken StringBuilder
                // Ophalen stacktrace
                // Call om stacktrace toe te voegen aan logs.txt
                StringBuilder sb=new StringBuilder();
                sb.append(Arrays.toString(ex.getStackTrace()));
                writeLogs(sb);
                // User info
                throw new BudgetAppCustomException("Exception - Budgetapp - "
                                + "BudgetAddBTNListener - printDutchNumberFormat");
            } catch (BudgetAppCustomException ex1) {
                }
        }
        return result;
    } 
  
    /**
     * Naam: writeLogs
     * Type: void method
     * Functionaliteiten:
     * - Schrijft logs naar logs file middels een BufferedWriter,
         OutputStreamWriter en FileOutputStream
     * - Output is UTF-8 file met .txt extensie
     * @param sb
     */
    @Override
    public void writeLogs(StringBuilder sb){
        try(BufferedWriter logs=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
      "logs.txt",true),"UTF-8" ));){
                    
            String[] sa=sb.toString().split(",");
            // Print to file per line
            for(String s:sa){
                logs.write(s);
                logs.newLine();
            }
        }catch(UnsupportedEncodingException ex) {
            try {
                // User info
                throw new BudgetAppCustomException("UnsupportedEncodingException - Budgetapp - BudgetArtWeekUtils - writeLogs");
            } catch (BudgetAppCustomException ex1) {
                System.out.println("UnsupportedEncodingException - Budgetapp - BudgetArtWeekUtils - writeLogs");
            }
        }catch(IOException ex){
            try {
                // User info
                throw new BudgetAppCustomException("IOException - Budgetapp - BudgetArtWeekUtils - writeLogs");
            } catch (BudgetAppCustomException ex1) {
                System.out.println("IOException - Budgetapp - BudgetArtWeekUtils - writeLogs");
            }
        }catch(Exception ex){
            try {
                // User info
                throw new BudgetAppCustomException("Exception - Budgetapp - BudgetArtWeekUtils - writeLogs");
            } catch (BudgetAppCustomException ex1) {
                System.out.println("Exception - Budgetapp - BudgetArtWeekUtils - writeLogs");
            }
        }
    }   
}