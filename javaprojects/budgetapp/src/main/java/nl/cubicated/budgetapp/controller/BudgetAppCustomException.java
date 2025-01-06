package nl.cubicated.budgetapp.controller;

/**
 *
 * @author Tanja Bekker
 * 
 * Naam: BudgetAppCustomException
 * Type: Exception
 * Functionaliteiten:
 * - Custom Exception class
 * - Twee contructors,waarvan 1 met een parameter String en 1 zonder een
 *   parameter.
 * - Is gemaakt om het printen van een custom bericht beschikbaar te maken
 *   voor heel de applicatie. De daadwerkelijke stacktrace wordt geprint in
 *   het logs.txt bestand omdat de user dit niet per se hoeft te zien.
 *   Een kort bericht voor de user is voldoende, maar mocht de user meer infomatie
 *   nodig hebben dan kan hij of zij deze vinden in het logs.txt bestand.
 * 
 * 
 */
public class BudgetAppCustomException extends Exception{
    // Basic constructor
    public BudgetAppCustomException(){
        super("Exception");
        
    }
   
    /**
     * Constructor met String parameter
     * Funtionaliteit: print msg parameter uit
     * @param msg 
     */
    public BudgetAppCustomException(String msg){
       super(msg);
       System.out.print(msg);
    }
}
