package nl.cubicated.budgetapp.controller;

import java.text.DecimalFormat;
import nl.cubicated.budgetapp.view.InnerPanel;

/**
 *
 * @author Tanja Bekker
 * 
 * Naam: BudgetArtUtilsInterface
 * Type: Interface
 * Functionaliteiten:
 * - Abstract methods voor klasse BudgetArtWeekUtils
 */
public interface BudgetArtUtilsInterface {
    public String displayGivenWeekDate(int givenYear,long givenWeek)throws BudgetAppCustomException;
    public boolean checkSpecialCharacter(String input)throws BudgetAppCustomException;
    public boolean checkWordsValues(String input)throws BudgetAppCustomException;
    public boolean checkNumericValues(String input)throws BudgetAppCustomException;
    public boolean commaChecker(String weekbudgetvoor)throws BudgetAppCustomException;
    public void saldoOver(DecimalFormat decf,InnerPanel ip,
            double uitgavenTotaal, double saldoWeek)throws BudgetAppCustomException;
    public void calcTotPrice(DecimalFormat decf, InnerPanel ip,
            int qty,double artSubPrice)throws BudgetAppCustomException;
    public void totUitgaven(DecimalFormat decf,InnerPanel ip,double sum)throws BudgetAppCustomException;
    public void checkSavingOrNot(InnerPanel ip,double sum, double saldoWeek)throws BudgetAppCustomException;
    public boolean checkZeroValues(String input)throws BudgetAppCustomException;
    public void calculateTotPrijs(InnerPanel ip,int convert)throws BudgetAppCustomException;
    public String printDutchNumberFormat(DecimalFormat decf,double number)throws BudgetAppCustomException;
    public void writeLogs(StringBuilder sb)throws BudgetAppCustomException;
}
