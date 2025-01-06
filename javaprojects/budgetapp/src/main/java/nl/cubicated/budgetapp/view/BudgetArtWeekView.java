package nl.cubicated.budgetapp.view;
/**
 * @author Tanja Bekker
 */
import java.awt.*;
import javax.swing.*;
import nl.cubicated.budgetapp.controller.BudgetAppCustomException;

/**
 * 
 * Naam: BudgetArtWeekView
 * Type: Klasse
 * Functionaliteiten:
 * - Aanmaken JFrame
 * - Init CustomJPanels introPanel, InnerPanel, ButtonPanel 
 *   en toevoegen aan JFrame.
 */
public class BudgetArtWeekView {
    private JFrame budgetFrame;
    private ButtonPanel buttonPanel;
    private IntroPanel introPanel;
    private InnerPanel innerPanel;
    
    
    /**
     * Naam: initWireFrame
     * Type: void method
     * @throws BudgetAppCustomException 
     * Layout frame: BorderLayout
     * Layout innerPanel: GridBagLayout
     * Layout buttonPanel: FlowLayout
     */
    private void initWireFrame() throws BudgetAppCustomException{
        // Nieuw frame
        budgetFrame=new JFrame("Budgetapp");
        innerPanel=new InnerPanel();
        buttonPanel=new ButtonPanel(innerPanel);
        getBudgetFrame().setSize(1400,800);
        budgetFrame.setLayout(new BorderLayout());
        budgetFrame.setBackground(new Color(0xF4EFF9));
        // introPanel
        introPanel=new IntroPanel();
        // Uitlijnen
        budgetFrame.add(getIntroPanel(),BorderLayout.NORTH);
        budgetFrame.add(getInnerPanel(),BorderLayout.WEST);
        budgetFrame.add(innerPanel.getScroll(),BorderLayout.CENTER);
        budgetFrame.add(buttonPanel,BorderLayout.SOUTH);
        budgetFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        budgetFrame.pack();
        budgetFrame.setLocationByPlatform(true);
        budgetFrame.setVisible(true);
        budgetFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    /**
     * Naam: BudgetArtWeekView
     * Type: Contructor
     * @throws BudgetAppCustomException
     * Functionaliteit:
     * - Call naar initWireFrame
     */
    public BudgetArtWeekView() throws BudgetAppCustomException{
        // Call naar initWireFrame
        initWireFrame();
    }
    // Getters en setters
    /**
     * @return the budgetFrame
     */
    public JFrame getBudgetFrame() {
        return budgetFrame;
    }
    /**
     * @param budgetFrame the budgetFrame to set
     */
    public void setBudgetFrame(JFrame budgetFrame) {
        this.budgetFrame = budgetFrame;
    }
    /**
     * @return the innerPanel
     */
    public InnerPanel getInnerPanel() {
        return innerPanel;
    }
    /**
     * @param innerPanel the innerPanel to set
     */
    public void setInnerPanel(InnerPanel innerPanel) {
        this.innerPanel = innerPanel;
    }
    /**
     * @return the buttonPanel
     */
    public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }
    /**
     * @param buttonPanel the buttonPanel to set
     */
    public void setButtonPanel(ButtonPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }
    /**
     * @return the introPanel
     */
    public JPanel getIntroPanel() {
        return introPanel;
    }
    /**
     * @param introPanel the introPanel to set
     */
    public void setIntroPanel(IntroPanel introPanel) {
        this.introPanel = introPanel;
    } 
}