package nl.cubicated.budgetapp.model;

import java.util.*;
import nl.cubicated.budgetapp.controller.BudgetArtWeekUtils;
import nl.cubicated.budgetapp.controller.BudgetAppCustomException;
/**
 * @author Tanja Bekker
 * 
 * Naam: BudgetArtWeek
 * Type: Klasse
 * Functionaliteit:
 * - Model voor weeklijst GUI onderdelen
 */
public class BudgetArtWeek {
    private String []supermarkten;
    private String artName;
    private String discount;
    private String bought;
    private int qtyArt;
    private static int chosenweek,chosenyear;
    private double subArtPrice;
    private double totArtPrice;
    private double totaalUitgaven, budgetVoor, budgetNa;
    private List<Double> weekuitgaven;
    private static Locale nLocale=new Locale("nl","NL");
    
    /**
     * columnNames
     */
    private static String[] columnNames=
        {"Supermarkt","Artikel","Aantal","Prijs per stuk","Totaalprijs","Aanbieding","Gekocht"};
    
    /**
     * jaren
     * Functionaliteit:
     * Integer[] van 0 tot 2049 voor combobox jaren
     * 0 als beginpunt om de user te triggeren om een keuze te maken.
     */
    private static Integer[] jaren = {
        0,2023,2024,2025,2026,2027,2028,2029,
        2030,2031,2032,2033,2034,2035,2036,2037,2038,2039,
        2040,2041,2042,2043,2044,2045,2046,2047,2048,2049
    };
    /**
     * wekenAnders
     * Functionaliteit:
     * Integer[] van 1 tot en met 53 voor weken in een schrikkeljaar
     * voor combobox weken
     */
    private static Integer[] wekenAnders = {
        1,2,3,4,5,6,7,8,9,
        10,11,12,13,14,15,16,17,18,19,
        20,21,22,23,24,25,26,27,28,29,
        30,31,32,33,34,35,36,37,38,39,
        40,41,42,43,44,45,46,47,48,49,
        50,51,52,53
    };
    
    /**
     * addQtyArtikel
     * Functionaliteit:
     * Integer [] van 0 tot maximaal 50 voor combobox qtyartikel
     * 0 als beginpunt om de user te triggeren om een keuze te maken.
     */
    private static Integer[] addQtyArtikel={
        0,1,2,3,4,5,6,7,8,9,
        10,11,12,13,14,15,16,17,18,19,
        20,21,22,23,24,25,26,27,28,29,
        30,31,32,33,34,35,36,37,38,39,
        40,41,42,43,44,45,46,47,48,49,
        50
    };
    /**
     * weken
     * Functionaliteit:
     * Integer[] van standaard aantal weken in een jaar
     * voor combobox weken
     */
    private static Integer[] weken = {
        1,2,3,4,5,6,7,8,9,
        10,11,12,13,14,15,16,17,18,19,
        20,21,22,23,24,25,26,27,28,29,
        30,31,32,33,34,35,36,37,38,39,
        40,41,42,43,44,45,46,47,48,49,
        50,51,52
    };
    
    
    // Getter en setters
    /**
     * 
     * @return getTotaalUitgaven()
     */
    public double getTotaalprijs() {
        return getTotaalUitgaven();
    }
    /**
     * 
     * @param totaalUitgaven 
     */
    public void setTotaalprijs(double totaalUitgaven) {
        this.setTotaalUitgaven(totaalUitgaven);
    }
    /**
     * 
     * @return budgetVoor
     */
    public double getBudgetVoor() {
        return budgetVoor;
    }
    /**
     * 
     * @param budgetVoor 
     */
    public void setBudgetVoor(double budgetVoor) {
        this.budgetVoor = budgetVoor;
    }
    /**
     * 
     * @return budgetNa
     */
    public double getBudgetNa() {
        return budgetNa;
    }
    /**
     * 
     * @param budgetNa 
     */
    public void setBudgetNa(double budgetNa) {
        this.budgetNa = budgetNa;
    }
    /**
     * 
     * @return weekuitgaven
     */
    public List<Double> getWeekuitgaven() {
        return weekuitgaven;
    }
    /**
     * 
     * @param weekuitgaven 
     */
    public void setWeekuitgaven(List<Double> weekuitgaven) {
        this.weekuitgaven = weekuitgaven;
    }
    /**
     * 
     * @return totaalUitgaven
     */
    public double getTotaalUitgaven() {
        return totaalUitgaven;
    }
    /**
     * 
     * @param totaalUitgaven 
     */
    public void setTotaalUitgaven(double totaalUitgaven) {
        this.totaalUitgaven = totaalUitgaven;
    }
   
    /**
     * 
     * @return supermarkten
     */
    public String[] getSupermarkten() {
        return supermarkten;
    }
    /**
     * 
     * @param supermarketNames 
     */
    public void setSupermarkten(String [] supermarketNames) {
        this.supermarkten = supermarketNames;
    }
    /**
     * 
     * @return artName
     */
    public String getArtName() {
        return artName;
    }
    /**
     * 
     * @param artName 
     */
    public void setArtName(String artName) {
        this.artName = artName;
    }
    /**
     * 
     * @return qtyArt
     */
    public int getQtyArt() {
        return qtyArt;
    }
    /**
     * 
     * @param qtyArt 
     */
    public void setQtyArt(int qtyArt) {
        this.qtyArt = qtyArt;
    }
    /**
     * 
     * @return subArtPrice
     */
    public double getSubArtPrice() {
        return subArtPrice;
    }
    /**
     * 
     * @param subArtPrice 
     */
    public void setSubArtPrice(double subArtPrice) {
        this.subArtPrice = subArtPrice;
    }
    
    /** 
     * @return the totArtPrice
     */
    public double getTotArtPrice() {
        return totArtPrice;
    }
    /**
     * 
     * @param totArtPrice 
     */
    public void setTotArtPrice(double totArtPrice) {
        this.totArtPrice = totArtPrice;
    }
    /**
     * 
     * @return discount
     */
    public String getDiscount() {
        return discount;
    }
    /**
     * 
     * @param discount 
     */
    public void setDiscount(String discount) {
        this.discount = discount;
    }
    /**
     * 
     * @return chosenweek
     */
    public static int getChosenweek() {
        return chosenweek;
    }
    /**
     * 
     * @param chosenwk 
     */
    public static void setChosenweek(int chosenwk) {
        chosenweek = chosenwk;
    }
    /**
     * 
     * @return chosenyear
     */
    public static int getChosenyear() {
        return chosenyear;
    }
    /**
     * 
     * @param chosenyr 
     */
    public static void setChosenyear(int chosenyr) {
        chosenyear = chosenyr;
    }
    /**
     * 
     * @return bought
     */
    public String getBought() {
        return bought;
    }
    /**
     * 
     * @param bought 
     */
    public void setBought(String bought) {
        this.bought = bought;
    }
    
    /**
     * @return the nLocale
     */
    public static Locale getnLocale() {
        return nLocale;
    }

    /**
     * @param anLocale the nLocale to set
     */
    public static void setnLocale(Locale anLocale) {
        nLocale = anLocale;
    }
    /**
     * @return the columnNames
     */
    public static String[] getColumnNames() {
        return columnNames;
    }

    /**
     * @param aColumnNames the columnNames to set
     */
    public static void setColumnNames(String[] aColumnNames) {
        columnNames = aColumnNames;
    }

    /**
     * @return the jaren
     */
    public static Integer[] getJaren() {
        return jaren;
    }

    /**
     * @param aJaren the jaren to set
     */
    public static void setJaren(Integer[] aJaren) {
        jaren = aJaren;
    }

    /**
     * @return the wekenAnders
     */
    public static Integer[] getWekenAnders() {
        return wekenAnders;
    }

    /**
     * @param aWekenAnders the wekenAnders to set
     */
    public static void setWekenAnders(Integer[] aWekenAnders) {
        wekenAnders = aWekenAnders;
    }

    /**
     * @return the addQtyArtikel
     */
    public static Integer[] getAddQtyArtikel() {
        return addQtyArtikel;
    }

    /**
     * @param aAddQtyArtikel the addQtyArtikel to set
     */
    public static void setAddQtyArtikel(Integer[] aAddQtyArtikel) {
        addQtyArtikel = aAddQtyArtikel;
    }

    /**
     * @return the weken
     */
    public static Integer[] getWeken() {
        return weken;
    }

    /**
     * @param aWeken the weken to set
     */
    public static void setWeken(Integer[] aWeken) {
        weken = aWeken;
    }
    
    /*
     * Name: BudgetArtWeek
     * Type: constructor
    */
    public BudgetArtWeek(){
        this.supermarkten=addSupermarketNames();
    }

    // methods
    /**
     * naam: addSupermarketNames
     * type: static final String[]
     * functionaliteiten:
     * - aanmaken String[22]
     * - aanmaken ArrayList van type String
     * - sort op alfabetische volgorde
     * @return static final String[] met supermarktnamen 
     */
    public final String[] addSupermarketNames(){
        // default supermarketNames getting added.
        BudgetArtWeekUtils utils=new BudgetArtWeekUtils();
        String []convert=new String[22];
        ArrayList<String> s=new ArrayList<>();
        
        try{
            s.add("Albert Heijn");
            s.add("Aldi");
            s.add("An-Da - Asian Foodmarket");
            s.add("Amazing Oriental");
            s.add("Boni");
            s.add("Boon's Markt");
            s.add("Budget Food");
            s.add("Crisp");
            s.add("Dagwinkel");
            s.add("DekaMarkt");
            s.add("De Pers");
            s.add("Dirk");
            s.add("EkoPlaza");
            s.add("Garoodi Supermarkt");
            s.add("Gümüs Market");
            s.add("Hira");
            s.add("Hoogvliet");
            s.add("Ideal Market");
            s.add("Jumbo");
            s.add("Kardelen Plaza Overvecht");
            s.add("Landmarkt");
            s.add("Lidl");
            s.add("Makro");
            s.add("Mantra Food Store");
            s.add("Marketim");
            s.add("Marqt");
            s.add("MCD");
            s.add("Medine Market Utrecht");
            s.add("Meltem Vers Expres");
            s.add("Nettorama");
            s.add("Odin");
            s.add("OZDE Foodcenter B.V.");
            s.add("Persepolis");
            s.add("Picnic");
            s.add("PLUS");
            s.add("Poiesz");
            s.add("Sligro");
            s.add("Spar");
            s.add("Sultan Food Center Overvecht");
            s.add("Supermarkt Bereket");
            s.add("Tanger Markt Utrecht");
            s.add("Target Mart");
            s.add("Toko Centraal Utrecht");
            s.add("Toko Indradjaja");
            s.add("Toko Goedendag");
            s.add("Toko Tjiau Jiang");
            s.add("Toko Tjiau Jiang Utecht");
            s.add("Vomar");
            
            // sort on alfabetical order
            s.sort(String::compareToIgnoreCase);
            convert=new String[s.size()];
                for(int x=0;x<s.size();x++){
                    convert[x]=s.get(x);
                }
            }catch(Exception ex){
                // aanmaken StringBuilder
                // ophalen stacktrace
                // call om stacktrace toe te voegen aan logs.txt
                StringBuilder sb=new StringBuilder();
                sb.append(Arrays.toString(ex.getStackTrace()));
                utils.writeLogs(sb);
                try { 
                    throw new BudgetAppCustomException("Exception - BudgetArtWeekUtils - addSupermarketNames");
                } catch (BudgetAppCustomException ex1) {
                    System.out.println("Exception - BudgetArtWeekUtils - addSupermarketNames");
                }
            }
            return convert;
        }   
}