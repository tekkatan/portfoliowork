package nl.cubicated.budgetapp.controller;

/**
 *
 * @author Tanja Bekker
 * Naam: BudgetWeekListInterface
 * Type: Interface
 * Functionaliteiten:
 * - Abstract methods voor klasse BudgetArtWeekUtils
 */
public interface BudgetWeekListInterface {
    public String printDutchNumberFormat(double number)
            throws BudgetAppCustomException;
    public String getTotalUitgavenSupermarkt(String givenSupermarket)
            throws BudgetAppCustomException;
    public String addExtraSpaces(String input, int space)
            throws BudgetAppCustomException;
    public void exportTXTPerSupermarket(String givenJaar,String givenWeek,
            String givenSupermarket)throws BudgetAppCustomException;
    public String[] getSupermarketsList()throws BudgetAppCustomException;
    public void exportTXTAlleSupermarkten(String givenJaar,String givenWeek)
            throws BudgetAppCustomException;
}
