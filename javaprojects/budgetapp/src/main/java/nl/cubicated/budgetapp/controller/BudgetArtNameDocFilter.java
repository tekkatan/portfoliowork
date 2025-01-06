package nl.cubicated.budgetapp.controller;

import javax.swing.text.*;

/**
 *
 * @author Tanja Bekker
 * Naam: BudgetArtNameDocFilter
 * Type: Klasse, DocumentFilter
 * Functionaliteiten:
 * - Document filter voor tf_1_artName
 * - Om een String langer dan gegeven max count char van 16
     niet toe te staan. Dit in verband met de export en opmaak van 
     weeklijsten als .txt bestanden. 
*/
public class BudgetArtNameDocFilter extends DocumentFilter{
    
    private int maxCountCharacters=16;
    // Getter en setter
    /**
     * @return the maxCountCharacters
     */
    public int getMaxCountCharacters() {
        return maxCountCharacters;
    }
    /**
     * @param maxCountCharacters the maxCountCharacters to set
     */
    public void setMaxCountCharacters(int maxCountCharacters) {
        this.maxCountCharacters = maxCountCharacters;
    }
    
    // Contructor
    /* Naam: BudgetArtNameDocFilter
    *  Type: Contructor
    */
    public BudgetArtNameDocFilter(){
    }
    
    /**
     * Naam: insertString
     * Type: void method
     * Functionaliteiten:
     * - Controle op lengte van input
     * - input van meer characters tegenhouden als lengte meer is dan 16 
     *   middels het herplaatsen van de string tot en met een lengte can 16
     *   characters lang.
     * @param fb
     * @param offs
     * @param input
     * @param a
     * @throws BadLocationException
     */
    @Override
    public void insertString(FilterBypass fb,int offs, String input, 
            AttributeSet a)throws BadLocationException
    {
        if((fb.getDocument().getLength()+input.length())<=getMaxCountCharacters()){
            super.insertString(fb,offs, input, a);
        }
    }

    /**
     * Naam: replace
     * Type: void method
     * Functionaliteiten:
     * - Replacen van string tot een lengte van 16 characters.
     * @param fb
     * @param offs
     * @param length
     * @param input
     * @param a
     * @throws BadLocationException
     */
    @Override
    public void replace(FilterBypass fb, int offs, int length, 
            String input, AttributeSet a)throws BadLocationException
    {
        if((fb.getDocument().getLength()+input.length()-length)<=getMaxCountCharacters()){
            super.replace(fb,offs,length,input,a);
        }
    }   
}
