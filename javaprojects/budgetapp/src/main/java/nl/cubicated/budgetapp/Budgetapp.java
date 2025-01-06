package nl.cubicated.budgetapp;

import java.util.Arrays;
import javax.swing.*;
import nl.cubicated.budgetapp.controller.BudgetArtWeekUtils;
import nl.cubicated.budgetapp.controller.BudgetAppCustomException;
import nl.cubicated.budgetapp.view.BudgetArtWeekView;
/**
 *
 * @author Tanja Bekker
 * 
 * Naam: Budgetapp
 * Type: Klasse
 * Functionaliteiten:
 * - Beginpunt van de applicatie
 */
public class Budgetapp {
    
    /**
     * Naam: main method
     * Functionaliteiten:
     * - Opstarten van GUI met Motif GUI of Default GUI
     * @param args 
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable(){
            @Override 
            public void run(){
                BudgetArtWeekUtils utils=new BudgetArtWeekUtils();
                try{
                    // try setting Modif UI
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                 }catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e){
                     try{
                     // Als Motif UI niet beschikbaar is
                     // load de default UI
                     UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                     }catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex){
                     }
                 }
                try{
                    // Starten new PrintStream voor een logger,
                    // voorkomt het lezen van foutmeldingen door users
                    // en bundelt de foutmeldingen in een logfile
                    BudgetArtWeekView bawv=new BudgetArtWeekView();
                } catch (BudgetAppCustomException ex) {
                    // aanmaken StringBuilder
                    // ophalen stacktrace
                    // call om stacktrace toe te voegen aan logs.txt
                    StringBuilder sb=new StringBuilder();
                    sb.append(Arrays.toString(ex.getStackTrace()));
                    utils.writeLogs(sb);
                }
            }
        });
    }
}