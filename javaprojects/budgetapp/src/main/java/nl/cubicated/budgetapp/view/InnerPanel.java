package nl.cubicated.budgetapp.view;

import java.awt.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import nl.cubicated.budgetapp.controller.BudgetArtNameDocFilter;
import nl.cubicated.budgetapp.controller.BudgetArtNameDocListener;
import nl.cubicated.budgetapp.controller.BudgetArtPriceDocListener;
import nl.cubicated.budgetapp.controller.BudgetArtWeekUtils;
import nl.cubicated.budgetapp.controller.BudgetBoughtListener;
import nl.cubicated.budgetapp.controller.BudgetDiscountListener;
import nl.cubicated.budgetapp.controller.BudgetJaarListener;
import nl.cubicated.budgetapp.controller.BudgetQtyArtListener;
import nl.cubicated.budgetapp.controller.BudgetWeekListener;
import nl.cubicated.budgetapp.controller.BudgetWeekVoorDocListener;
import nl.cubicated.budgetapp.controller.CustomTableRowRenderer;
import nl.cubicated.budgetapp.controller.BudgetAppCustomException;
import nl.cubicated.budgetapp.model.BudgetArtWeek;
import nl.cubicated.budgetapp.model.CustomJPanel;
/**
 *
 * @author Tanja Bekker
 * Naam: InnerPanel
 * Type: Klasse
 * Functionaliteit:
 * - Aanmaken JPanel InnerPanel met invulvelden, radiobuttons,
 *   JLabels, JTextFields, JTextArea, JComboBoxen en een JTable.
 * - Bovenstaande onderdelen voorzien van ActionListeners, ItemListeners,
 *   DocumentListeners en een DocumentFilter.
 */
public class InnerPanel extends CustomJPanel {
    
    private JLabel lbl_1_header,lbl_1_weekbudget,
            lbl_titel_totaalUitgaven,lbl_titel_saldoOver,
            lbl_3_weekbudget_checker,lbl_3_artPrice_checker,
            lbl_3_wk_titel,lbl_autom;
    private JLabel lbl_1_jr,lbl_3_artName_checker,lbl_3_artQty_checker;
    private JLabel lbl_2_wk;
    private JLabel lbl_3_wk;
    private JLabel lbl_1_supermarketName,lbl_2_artName;
    private JLabel lbl_1_totArtPrice,lbl_2_totArtPrice;
    private JLabel lbl_1_subArtPrice,
            lbl_1_qty,lbl_1_bought,lbl_1_discount,
            lbl_1_discount_ans, lbl_1_bought_ans;
    private JTextField tf_1_weekBudgetVoor;
    private JTextField tf_1_artName, tf_2_artPrice
            ,tf_saldoOver,tf_totaalUitgaven;
    private JTextArea ta_1_checker,ta_2_checker;
    private static JComboBox<Integer> weekcbb;
    private static JComboBox<Integer> jaarcbb;
    private static JComboBox<String> supermarketNames;
    private static JComboBox<Integer> qtyArt;
    private JRadioButton rb_discountYes, rb_boughtYes;
    private JRadioButton rb_discountNo, rb_boughtNo;
    private String converted;
    private JTable table;
    private JScrollPane scroll;
    private BudgetArtWeek baw;
    private BudgetArtWeekUtils utils;
    private DefaultTableModel dtm;
    private ButtonGroup bg_discount,bg_bought;
    private JSeparator separator, separator_1,separator_2,separator_3,
            separator_4;
    
    /*
    * Naam: InnerPanel
    * Type: Constructor
    * Functionaliteit: 
    * - call naar initWirePanelContents method
    */
    public InnerPanel(){
        initWirePanelContents();
    }
    /*
    * Naam: initWirePanelContents
    * Type: void method
    * Functionaliteiten:
    * - Nieuwe instantie van BudgetArtWeekUtils aanmaken
    * - Nieuwe instantie van BudgetArtWeek aanmaken
    * - Layout en contraints aanmaken
    * - Custom background 
    * - GUI aanmaken met JLabels, JTextArea, JTextFields, JComboBoxen en 
        een JTable
    * - GUI onderdelen voorzien van link met ItemListeners, DocumentListeners,
        ActionListeners en een DocumentFilter.
    * - JTable voorzien van DefaultTableModel waarbij de cells niet geedit kunnen
        worden middels een genestelde overridden method isCellEditable() bij het
        aanmaken van een DefaultTableModel.
    * - catches BudgetAppCustomException, welke in de gelinkte eventlisteners kan 
        worden gethrowed.
    */
    private void initWirePanelContents(){
        try{
            // Main input panel
            utils=new BudgetArtWeekUtils();
            // Init textfield values
            baw=new BudgetArtWeek();
            GridBagConstraints con=new GridBagConstraints();
            con.insets=new Insets(5,5,5,5);
            GridBagLayout layoutGB=new GridBagLayout();
            setLayout(layoutGB);
            setOpaque(true);
            setBackground(new Color(0xF4EFF9));
            // Invulvelden en comboxen
            con.gridx=0;
            con.gridy=0;
            con.gridwidth=1;
            con.weightx=0;
            con.fill=GridBagConstraints.BOTH;
            lbl_1_header=new JLabel("Vul in: ",SwingConstants.LEFT);
            lbl_1_header.setVisible(true);
            layoutGB.setConstraints(lbl_1_header, con);
            add(lbl_1_header,con);
            // Weekbudget
            con.fill=GridBagConstraints.HORIZONTAL;
            con.gridx=0;
            con.gridy=2;
            con.weightx=0;
            con.anchor=GridBagConstraints.WEST;
            lbl_1_weekbudget=new JLabel("Weekbudget: ");
            lbl_1_weekbudget.setVisible(true);
            layoutGB.setConstraints(lbl_1_weekbudget, con);
            add(lbl_1_weekbudget,con);
            con.fill=GridBagConstraints.HORIZONTAL;
            con.gridx=0;
            con.gridy=4;
            con.weightx=0;
            con.anchor=GridBagConstraints.WEST;
            tf_1_weekBudgetVoor=new JTextField("00,00");
            tf_1_weekBudgetVoor.setToolTipText("Bijvoorbeeld: 5,50  of 7");
            tf_1_weekBudgetVoor.setVisible(true);
            // Document listener voor textfield
            tf_1_weekBudgetVoor.getDocument()
                .addDocumentListener(new BudgetWeekVoorDocListener(this));
            add(tf_1_weekBudgetVoor,con);
            // Weekbudget checker
            con.fill=GridBagConstraints.HORIZONTAL;
            con.gridx=0;
            con.gridy=3;
            con.weightx=0;
            con.anchor=GridBagConstraints.WEST;
            lbl_3_weekbudget_checker=new JLabel("x");
            lbl_3_weekbudget_checker.setForeground(Color.red);
            lbl_3_weekbudget_checker.setVisible(true);
            add(lbl_3_weekbudget_checker,con);
            // Jaar
            con.fill=GridBagConstraints.HORIZONTAL;
            con.gridx=1;
            con.gridy=2;
            con.weightx=0;
            con.anchor=GridBagConstraints.WEST;
            lbl_1_jr=new JLabel("Jaar: ");
            lbl_1_jr.setVisible(true);
            add(lbl_1_jr,con);
            con.fill=GridBagConstraints.HORIZONTAL;
            con.gridx=1;
            con.gridy=4;
            con.weightx=0;
            jaarcbb=new JComboBox<>(BudgetArtWeek.getJaren());
            jaarcbb.setSelectedItem(2023);
            jaarcbb.setVisible(true);
            add(jaarcbb,con);
            // Week
            con.fill=GridBagConstraints.HORIZONTAL;
            con.gridx=2;
            con.gridy=2;
            con.weightx=0;
            lbl_2_wk=new JLabel("Weeknummer: ");
            lbl_2_wk.setVisible(true);
            add(lbl_2_wk,con);
            con.fill=GridBagConstraints.HORIZONTAL;
            con.gridx=2;
            con.gridy=4;
            con.weightx=0;
            weekcbb=new JComboBox<>(BudgetArtWeek.getWeken());
            weekcbb.setSelectedItem(1);
            weekcbb.setVisible(true);
            add(weekcbb,con);
            // Weergeef gekozen datum
            con.fill=GridBagConstraints.HORIZONTAL;
            con.gridx=3;
            con.gridy=2;
            con.weightx=0;
            con.anchor=GridBagConstraints.WEST;
            lbl_3_wk_titel=new JLabel("Datum: ");
            lbl_3_wk_titel.setVisible(true);
            add(lbl_3_wk_titel,con);
            con.fill=GridBagConstraints.HORIZONTAL;
            con.gridx=3;
            con.gridy=4;
            con.weightx=0;
            con.anchor=GridBagConstraints.WEST;
            lbl_3_wk=new JLabel("..........");
            converted=utils.
                displayGivenWeekDate(2023,1);
            lbl_3_wk.setText(converted);
            lbl_3_wk.setVisible(true);
            add(lbl_3_wk,con);
            // Listener for given year
            jaarcbb.addItemListener(new BudgetJaarListener(this));
            // Listener for combobox week
            // Adjust label output week from monday till sunday  
            weekcbb.addItemListener(new BudgetWeekListener(this));
            // Supermarkten
            con.anchor=GridBagConstraints.WEST;
            con.fill=GridBagConstraints.HORIZONTAL;
            con.gridx=0;
            con.gridy=5;
            con.weightx=0;
            lbl_1_supermarketName=new JLabel("Supermarkt: ");
            lbl_1_supermarketName.setVisible(true);
            add(lbl_1_supermarketName,con);
            con.fill=GridBagConstraints.HORIZONTAL;
            con.gridx=0;
            con.gridy=7;
            con.weightx=0;
            con.anchor=GridBagConstraints.WEST;
            supermarketNames=new JComboBox<>(baw.getSupermarkten());
            supermarketNames.setSelectedItem("Albert Heijn");
            supermarketNames.setVisible(true);
            add(supermarketNames,con);
            // Artikelnaam
            con.fill=GridBagConstraints.HORIZONTAL;
            con.gridx=1;
            con.gridy=5;
            con.weightx=0;
            con.anchor=GridBagConstraints.WEST;
            lbl_2_artName=new JLabel("Artikelnaam: ");
            lbl_2_artName.setVisible(true);
            add(lbl_2_artName,con);
            // Invulveld artikelnaam
            con.gridx=1;
            con.gridy=7;
            con.weightx=0;
            con.fill=GridBagConstraints.HORIZONTAL;
            con.anchor=GridBagConstraints.WEST;
            tf_1_artName=new JTextField(10);
            tf_1_artName.setText("Artikelnaam");
            tf_1_artName.setToolTipText("Bijvoorbeeld: appels, 7-up of gebakk. peren");
            // Document listener
            tf_1_artName.getDocument()
                .addDocumentListener(new BudgetArtNameDocListener(this));
            tf_1_artName.setMinimumSize(tf_1_artName.getPreferredSize());
            AbstractDocument d=(AbstractDocument)tf_1_artName.getDocument();
            // Max length artnaam is 16 characters
            d.setDocumentFilter(new BudgetArtNameDocFilter());
            add(tf_1_artName,con);
            // Artname checker
            con.gridx=1;
            con.gridy=6;
            con.weightx=0;
            con.fill=GridBagConstraints.HORIZONTAL;
            con.anchor=GridBagConstraints.WEST;
            lbl_3_artName_checker=new JLabel("x");
            lbl_3_artName_checker.setForeground(Color.red);
            lbl_3_artName_checker.setVisible(true);
            add(lbl_3_artName_checker,con);
            // Artikel prijs
            con.gridx=2;
            con.gridy=5;
            con.weightx=0;
            con.fill=GridBagConstraints.HORIZONTAL;
            con.anchor=GridBagConstraints.WEST;
            lbl_1_subArtPrice=new JLabel("Prijs (per stuk): ");
            lbl_1_subArtPrice.setVisible(true);
            add(lbl_1_subArtPrice,con);
            // Invulveld artikel prijs
            con.gridx=2;
            con.gridy=7;
            con.weightx=0;
            con.fill=GridBagConstraints.HORIZONTAL;
            con.anchor=GridBagConstraints.WEST;
            tf_2_artPrice=new JTextField("0,00");
            tf_2_artPrice.setToolTipText("Bijvoorbeeld: 5,50  of 7");
            tf_2_artPrice.setBounds(50,50,50,50);
            tf_2_artPrice.getDocument()
                .addDocumentListener(new BudgetArtPriceDocListener(this));
            add(tf_2_artPrice,con);
            // Checker artikel prijs
            con.gridx=2;
            con.gridy=6;
            con.weightx=0;
            con.fill=GridBagConstraints.HORIZONTAL;
            con.anchor=GridBagConstraints.WEST;
            lbl_3_artPrice_checker=new JLabel("x");
            lbl_3_artPrice_checker.setForeground(Color.red);
            lbl_3_artPrice_checker.setVisible(true);
            add(lbl_3_artPrice_checker,con);
            // Qty
            con.gridx=3;
            con.gridy=5;
            con.weightx=0;
            con.fill=GridBagConstraints.HORIZONTAL;
            con.anchor=GridBagConstraints.WEST;
            lbl_1_qty=new JLabel("Aantal:");
            lbl_1_qty.setVisible(true);
            add(lbl_1_qty,con);
            // Combobox qty 
            con.gridx=3;
            con.gridy=7;
            con.weightx=0;
            con.fill=GridBagConstraints.HORIZONTAL;
            con.anchor=GridBagConstraints.WEST;
            qtyArt=new JComboBox<>(BudgetArtWeek.getAddQtyArtikel());
            qtyArt.setSelectedItem(0);
            qtyArt.setToolTipText("Kies een andere waarde dan 0.");
            qtyArt.setVisible(true);
            add(qtyArt,con);
            // Checker Qty
            con.gridx=3;
            con.gridy=6;
            con.weightx=0;
            con.fill=GridBagConstraints.HORIZONTAL;
            con.anchor=GridBagConstraints.WEST;
            lbl_3_artQty_checker=new JLabel("x");
            lbl_3_artQty_checker.setForeground(Color.red);
            lbl_3_artQty_checker.setVisible(true);
            add(lbl_3_artQty_checker,con);
            // Event listener
            qtyArt.addItemListener(new BudgetQtyArtListener(this));
            // Aanbieding
            con.gridx=0;
            con.gridy=8;
            con.weightx=0;
            con.fill=GridBagConstraints.HORIZONTAL;
            con.anchor=GridBagConstraints.WEST;
            lbl_1_discount=new JLabel("Aanbieding: ");
            add(lbl_1_discount,con);
            // Aanbieding radiobutton ja
            con.gridx=0;
            con.gridy=9;
            con.weightx=0;
            con.fill=GridBagConstraints.HORIZONTAL;
            con.anchor=GridBagConstraints.WEST;
            rb_discountYes=new JRadioButton("Ja");
            // Antwoord voor radiobuttons aanbieding
            lbl_1_discount_ans=new JLabel("Nee");
            lbl_1_discount_ans.setVisible(false);
            lbl_1_discount_ans.setBounds(70,50,10,40);
            add(lbl_1_discount_ans,con);
            add(rb_discountYes,con);
            // Action listener
            rb_discountYes.addActionListener(new BudgetDiscountListener(this));
            // Aanbieding radiobutton nee
            con.gridx=0;
            con.gridy=10;
            con.weightx=0;
            con.fill=GridBagConstraints.HORIZONTAL;
            con.anchor=GridBagConstraints.WEST;
            rb_discountNo=new JRadioButton("Nee");
            rb_discountNo.setBounds(70,50,10,40);
            bg_discount=new ButtonGroup();
            bg_discount.add(getRb_discountYes());
            bg_discount.add(getRb_discountNo());
            rb_discountNo.setSelected(true);
            // Action listener
            rb_discountNo.addActionListener(new BudgetDiscountListener(this));
            add(rb_discountNo,con);
            // Gekocht
            con.gridx=1;
            con.gridy=8;
            con.weightx=0;
            con.fill=GridBagConstraints.HORIZONTAL;
            con.anchor=GridBagConstraints.WEST;
            lbl_1_bought=new JLabel("Gekocht: ");
            add(lbl_1_bought,con);
            con.gridx=1;
            con.gridy=9;
            con.weightx=0;
            con.fill=GridBagConstraints.HORIZONTAL;
            con.anchor=GridBagConstraints.WEST;
            lbl_1_bought_ans=new JLabel("Nee");
            lbl_1_bought_ans.setVisible(false);
            rb_boughtYes=new JRadioButton("Ja");
            rb_boughtYes.setBounds(0,50,10,0);
            // Action listener
            rb_boughtYes.addActionListener(new BudgetBoughtListener(this));
            add(rb_boughtYes,con);
            con.gridx=1;
            con.gridy=10;
            con.weightx=0;
            con.fill=GridBagConstraints.HORIZONTAL;
            con.anchor=GridBagConstraints.WEST;
            rb_boughtNo=new JRadioButton("Nee");
            rb_boughtNo.setBounds(70,50,10,40);
            bg_bought=new ButtonGroup();
            bg_bought.add(getRb_boughtYes());
            bg_bought.add(getRb_boughtNo());
            // Auto select 
            rb_boughtNo.setSelected(true);
            // Action listener
            rb_boughtNo.addActionListener(new BudgetBoughtListener(this));
            add(rb_boughtNo,con);
            // totaalprijs
            con.gridx=4;
            con.gridy=5;
            con.weightx=0;
            con.fill=GridBagConstraints.HORIZONTAL;
            con.anchor=GridBagConstraints.WEST;
            lbl_1_totArtPrice=new JLabel("Totaalprijs:");
            lbl_1_totArtPrice.setVisible(true);
            add(lbl_1_totArtPrice,con);
            con.gridx=4;
            con.gridy=7;
            con.weightx=0;
            con.fill=GridBagConstraints.HORIZONTAL;
            con.anchor=GridBagConstraints.WEST;
            lbl_2_totArtPrice=new JLabel("0,00");
            lbl_2_totArtPrice.setVisible(true);
            add(lbl_2_totArtPrice,con);
            // Separator invulvelden and resultaten/automatisch gegenereerd
            con.gridx=0;
            con.gridy=11;
            con.weightx=0;
            separator=new JSeparator();
            separator.setOrientation(SwingConstants.HORIZONTAL);
            add(separator,con);
            con.gridx=1;
            con.gridy=11;
            con.weightx=0;
            separator_1=new JSeparator();
            separator.setOrientation(SwingConstants.HORIZONTAL);
            add(separator_1,con);
            con.gridx=2;
            con.gridy=11;
            con.weightx=0;
            separator_2=new JSeparator();
            separator.setOrientation(SwingConstants.HORIZONTAL);
            add(separator_2,con);
            con.gridx=3;
            con.gridy=11;
            con.weightx=0;
            separator_3=new JSeparator();
            separator.setOrientation(SwingConstants.HORIZONTAL);
            add(separator_3,con);
            con.gridx=4;
            con.gridy=11;
            con.weightx=0;
            separator_4=new JSeparator();
            separator.setOrientation(SwingConstants.HORIZONTAL);
            add(separator_4,con);
            // Titel automatisch gegenereerd
            con.gridx=0;
            con.gridy=12;
            con.weightx=0;
            con.fill=GridBagConstraints.HORIZONTAL;
            con.anchor=GridBagConstraints.WEST;
            lbl_autom=new JLabel("Automatisch gegenereerd: ");
            lbl_autom.setVisible(true);
            add(lbl_autom,con);
            // Totaaluitgaven opgeteld uit table
            con.gridx=0;
            con.gridy=13;
            con.weightx=0;
            con.fill=GridBagConstraints.HORIZONTAL;
            con.anchor=GridBagConstraints.WEST;
            lbl_titel_totaalUitgaven=new JLabel("Totaal uitgaven: ");
            lbl_titel_totaalUitgaven.setVisible(true);
            add(lbl_titel_totaalUitgaven,con);
            con.gridx=0;
            con.gridy=14;
            con.weightx=0;
            con.fill=GridBagConstraints.HORIZONTAL;
            con.anchor=GridBagConstraints.WEST;
            tf_totaalUitgaven=new JTextField("...");
            tf_totaalUitgaven.setVisible(true);
            tf_totaalUitgaven.setEditable(false);
            tf_totaalUitgaven.setOpaque(false);
            add(tf_totaalUitgaven,con);
            // Saldo over
            con.gridx=1;
            con.gridy=13;
            con.weightx=0;
            con.fill=GridBagConstraints.HORIZONTAL;
            con.anchor=GridBagConstraints.WEST;
            lbl_titel_saldoOver=new JLabel("Saldo over: ");
            lbl_titel_saldoOver.setVisible(true);
            add(lbl_titel_saldoOver,con);
            con.gridx=1;
            con.gridy=14;
            con.weightx=0;
            con.fill=GridBagConstraints.HORIZONTAL;
            con.anchor=GridBagConstraints.WEST;
            tf_saldoOver=new JTextField("...");
            tf_saldoOver.setVisible(true);
            tf_saldoOver.setEditable(false);
            tf_saldoOver.setOpaque(false);
            add(tf_saldoOver,con);
            // Checker voor lege waarden
            con.gridx=0;
            con.gridy=15;
            con.weightx=0;
            con.fill=GridBagConstraints.HORIZONTAL;
            con.anchor=GridBagConstraints.WEST;
            ta_1_checker=new JTextArea("...");
            ta_1_checker.setVisible(true);
            ta_1_checker.setEditable(false);
            ta_1_checker.setLineWrap(true);
            ta_1_checker.setWrapStyleWord(true);
            ta_1_checker.setPreferredSize(new Dimension(150,150));
            add(ta_1_checker,con);
            // Checker voor add button
            con.gridx=0;
            con.gridy=16;
            con.weightx=0;
            con.fill=GridBagConstraints.HORIZONTAL;
            ta_2_checker=new JTextArea("...");
            ta_2_checker.setEditable(false);
            ta_2_checker.setLineWrap(true);
            ta_2_checker.setWrapStyleWord(true);
            ta_2_checker.setPreferredSize(new Dimension(150,150));
            ta_2_checker.setVisible(true);
            add(ta_2_checker,con);
            // Preview table
            con.gridx=0;
            con.gridy=20;
            con.weightx=0.0;
            con.ipady=80;
            con.ipadx=150;
            con.gridwidth=30;
            con.weightx=1;
            con.fill=GridBagConstraints.HORIZONTAL;
            table=new JTable();
            table.setFillsViewportHeight(true);
            setDtm(new DefaultTableModel(0,0){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
                }
            });
            // Voeg toe columns
            dtm.setColumnIdentifiers(BudgetArtWeek.getColumnNames());
            // Voeg toe scroll mogelijkheid
            table.setOpaque(true);
            table.setRowSelectionAllowed(true);
            table.setModel(getDtm());
            table.setDefaultRenderer(Object.class,new CustomTableRowRenderer(table));
            scroll=new JScrollPane(getTable());
            scroll.setBounds(50,200, 800, 900);
        }catch(BudgetAppCustomException ex){
            try {
                // Aanmaken StringBuilder
                // Ophalen stacktrace
                // Call om stacktrace toe te voegen aan logs.txt
                StringBuilder sb=new StringBuilder();
                sb.append(Arrays.toString(ex.getStackTrace()));
                utils.writeLogs(sb);
                // User info
                throw new BudgetAppCustomException("Exception - Budgetapp - "
                        + "BudgetWeekVoorDocListener - checker");
            } catch (BudgetAppCustomException ex1) {
            }
        }   
    }
    
    // Getter en setters
    /**
     * @return the lbl_1_header
     */
    public JLabel getLbl_1_header(){
        return lbl_1_header;
    }
    /**
     * @param lbl_1_header the lbl_1_header to set
     */
    public void setLbl_1_header(JLabel lbl_1_header) {
        this.lbl_1_header = lbl_1_header;
    }
    /**
     * @return the lbl_1_weekbudget
     */
    public JLabel getLbl_1_weekbudget() {
        return lbl_1_weekbudget;
    }
    /**
     * @param lbl_1_weekbudget the lbl_1_weekbudget to set
     */
    public void setLbl_1_weekbudget(JLabel lbl_1_weekbudget) {
        this.lbl_1_weekbudget = lbl_1_weekbudget;
    }
    /**
     * @return the lbl_titel_totaalUitgaven
     */
    public JLabel getLbl_titel_totaalUitgaven() {
        return lbl_titel_totaalUitgaven;
    }
    /**
     * @param lbl_titel_totaalUitgaven the lbl_titel_totaalUitgaven to set
     */
    public void setLbl_titel_totaalUitgaven(JLabel lbl_titel_totaalUitgaven) {
        this.lbl_titel_totaalUitgaven = lbl_titel_totaalUitgaven;
    }
    /**
     * @return the lbl_titel_saldoOver
     */
    public JLabel getLbl_titel_saldoOver() {
        return lbl_titel_saldoOver;
    }
    /**
     * @param lbl_titel_saldoOver the lbl_titel_saldoOver to set
     */
    public void setLbl_titel_saldoOver(JLabel lbl_titel_saldoOver) {
        this.lbl_titel_saldoOver = lbl_titel_saldoOver;
    }
    /**
     * @return the lbl_3_weekbudget_checker
     */
    public JLabel getLbl_3_weekbudget_checker() {
        return lbl_3_weekbudget_checker;
    }
    /**
     * @param lbl_3_weekbudget_checker the lbl_3_weekbudget_checker to set
     */
    public void setLbl_3_weekbudget_checker(JLabel lbl_3_weekbudget_checker) {
        this.lbl_3_weekbudget_checker = lbl_3_weekbudget_checker;
    }
    /**
     * @return the lbl_3_artPrice_checker
     */
    public JLabel getLbl_3_artPrice_checker() {
        return lbl_3_artPrice_checker;
    }
    /**
     * @param lbl_3_artPrice_checker the lbl_3_artPrice_checker to set
     */
    public void setLbl_3_artPrice_checker(JLabel lbl_3_artPrice_checker) {
        this.lbl_3_artPrice_checker = lbl_3_artPrice_checker;
    }
    /**
     * @return the lbl_3_wk_titel
     */
    public JLabel getLbl_3_wk_titel() {
        return lbl_3_wk_titel;
    }
    /**
     * @param lbl_3_wk_titel the lbl_3_wk_titel to set
     */
    public void setLbl_3_wk_titel(JLabel lbl_3_wk_titel) {
        this.lbl_3_wk_titel = lbl_3_wk_titel;
    }
    /**
     * @return the lbl_autom
     */
    public JLabel getLbl_autom() {
        return lbl_autom;
    }
    /**
     * @param lbl_autom the lbl_autom to set
     */
    public void setLbl_autom(JLabel lbl_autom) {
        this.lbl_autom = lbl_autom;
    }    
    /**
     * @return the tf_1_weekBudgetVoor
     */
    public JTextField getTf_1_weekBudgetVoor() {
        return tf_1_weekBudgetVoor;
    }
    /**
     * @param tf_1_weekBudgetVoor the tf_1_weekBudgetVoor to set
     */
    public void setTf_1_weekBudgetVoor(JTextField tf_1_weekBudgetVoor) {
        this.tf_1_weekBudgetVoor = tf_1_weekBudgetVoor;
    }
    /**
     * @return the lbl_1_jr
     */
    public JLabel getLbl_1_jr() {
        return lbl_1_jr;
    }
    /**
     * @param lbl_1_jr the lbl_1_jr to set
     */
    public void setLbl_1_jr(JLabel lbl_1_jr) {
        this.lbl_1_jr = lbl_1_jr;
    }
    /**
     * @return the lbl_3_artName_checker
     */
    public JLabel getLbl_3_artName_checker() {
        return lbl_3_artName_checker;
    }
    /**
     * @param lbl_3_artName_checker the lbl_3_artName_checker to set
     */
    public void setLbl_3_artName_checker(JLabel lbl_3_artName_checker) {
        this.lbl_3_artName_checker = lbl_3_artName_checker;
    }
    /**
     * @return the lbl_3_artQty_checker
     */
    public JLabel getLbl_3_artQty_checker() {
        return lbl_3_artQty_checker;
    }
    /**
     * @param lbl_3_artQty_checker the lbl_3_artQty_checker to set
     */
    public void setLbl_3_artQty_checker(JLabel lbl_3_artQty_checker) {
        this.lbl_3_artQty_checker = lbl_3_artQty_checker;
    }
    /**
     * @return the lbl_2_wk
     */
    public JLabel getLbl_2_wk() {
        return lbl_2_wk;
    }
    /**
     * @param lbl_2_wk the lbl_2_wk to set
     */
    public void setLbl_2_wk(JLabel lbl_2_wk) {
        this.lbl_2_wk = lbl_2_wk;
    }
    /**
     * @return the lbl_3_wk
     */
    public JLabel getLbl_3_wk() {
        return lbl_3_wk;
    }
    /**
     * @param lbl_3_wk the lbl_3_wk to set
     */
    public void setLbl_3_wk(JLabel lbl_3_wk) {
        this.lbl_3_wk = lbl_3_wk;
    }
    /**
     * @return the weekcbb
     */
    public static JComboBox<Integer> getWeekcbb() {
        return weekcbb;
    }
    /**
     * @param aWeekcbb the weekcbb to set
     */
    public static void setWeekcbb(JComboBox<Integer> aWeekcbb) {
        weekcbb = aWeekcbb;
    }
    /**
     * @return the jaarcbb
     */
    public static JComboBox<Integer> getJaarcbb() {
        return jaarcbb;
    }
    /**
     * @param aJaarcbb the jaarcbb to set
     */
    public static void setJaarcbb(JComboBox<Integer> aJaarcbb) {
        jaarcbb = aJaarcbb;
    }
    /**
     * @return the baw
     */
    public BudgetArtWeek getBaw() {
        return baw;
    }
    /**
     * @param baw the baw to set
     */
    public void setBaw(BudgetArtWeek baw) {
        this.baw = baw;
    }
    /**
     * @return the dtm
     */
    public DefaultTableModel getDtm() {
        return dtm;
    }
    /**
     * @param dtm the dtm to set
     */
    public void setDtm(DefaultTableModel dtm) {
        this.dtm = dtm;
    }
    /**
     * @return the lbl_1_supermarketName
     */
    public JLabel getLbl_1_supermarketName() {
        return lbl_1_supermarketName;
    }
    /**
     * @param lbl_1_supermarketName the lbl_1_supermarketName to set
     */
    public void setLbl_1_supermarketName(JLabel lbl_1_supermarketName) {
        this.lbl_1_supermarketName = lbl_1_supermarketName;
    }
    /**
     * @return the lbl_2_artName
     */
    public JLabel getLbl_2_artName() {
        return lbl_2_artName;
    }
    /**
     * @param lbl_2_artName the lbl_2_artName to set
     */
    public void setLbl_2_artName(JLabel lbl_2_artName) {
        this.lbl_2_artName = lbl_2_artName;
    }
    /**
     * @return the tf_1_artName
     */
    public JTextField getTf_1_artName() {
        return tf_1_artName;
    }
    /**
     * @param tf_1_artName the tf_1_artName to set
     */
    public void setTf_1_artName(JTextField tf_1_artName) {
        this.tf_1_artName = tf_1_artName;
    }
    /**
     * @return the tf_2_artPrice
     */
    public JTextField getTf_2_artPrice() {
        return tf_2_artPrice;
    }
    /**
     * @param tf_2_artPrice the tf_2_artPrice to set
     */
    public void setTf_2_artPrice(JTextField tf_2_artPrice) {
        this.tf_2_artPrice = tf_2_artPrice;
    }
    /**
     * @return the tf_saldoOver
     */
    public JTextField getTf_saldoOver() {
        return tf_saldoOver;
    }
    /**
     * @param tf_saldoOver the tf_saldoOver to set
     */
    public void setTf_saldoOver(JTextField tf_saldoOver) {
        this.tf_saldoOver = tf_saldoOver;
    }
    /**
     * @return the tf_totaalUitgaven
     */
    public JTextField getTf_totaalUitgaven() {
        return tf_totaalUitgaven;
    }
    /**
     * @param tf_totaalUitgaven the tf_totaalUitgaven to set
     */
    public void setTf_totaalUitgaven(JTextField tf_totaalUitgaven) {
        this.tf_totaalUitgaven = tf_totaalUitgaven;
    }
    /**
     * @return the lbl_1_subArtPrice
     */
    public JLabel getLbl_1_subArtPrice() {
        return lbl_1_subArtPrice;
    }
    /**
     * @param lbl_1_subArtPrice the lbl_1_subArtPrice to set
     */
    public void setLbl_1_subArtPrice(JLabel lbl_1_subArtPrice) {
        this.lbl_1_subArtPrice = lbl_1_subArtPrice;
    }
    /**
     * @return the lbl_1_qty
     */
    public JLabel getLbl_1_qty() {
        return lbl_1_qty;
    }
    /**
     * @param lbl_1_qty the lbl_1_qty to set
     */
    public void setLbl_1_qty(JLabel lbl_1_qty) {
        this.lbl_1_qty = lbl_1_qty;
    }
    /**
     * @return the lbl_1_bought
     */
    public JLabel getLbl_1_bought() {
        return lbl_1_bought;
    }
    /**
     * @param lbl_1_bought the lbl_1_bought to set
     */
    public void setLbl_1_bought(JLabel lbl_1_bought) {
        this.lbl_1_bought = lbl_1_bought;
    }
    /**
     * @return the lbl_1_discount
     */
    public JLabel getLbl_1_discount() {
        return lbl_1_discount;
    }
    /**
     * @param lbl_1_discount the lbl_1_discount to set
     */
    public void setLbl_1_discount(JLabel lbl_1_discount) {
        this.lbl_1_discount = lbl_1_discount;
    }
    /**
     * @return the lbl_1_discount_ans
     */
    public JLabel getLbl_1_discount_ans() {
        return lbl_1_discount_ans;
    }
    /**
     * @param lbl_1_discount_ans the lbl_1_discount_ans to set
     */
    public void setLbl_1_discount_ans(JLabel lbl_1_discount_ans) {
        this.lbl_1_discount_ans = lbl_1_discount_ans;
    }
    /**
     * @return the lbl_1_bought_ans
     */
    public JLabel getLbl_1_bought_ans() {
        return lbl_1_bought_ans;
    }
    /**
     * @param lbl_1_bought_ans the lbl_1_bought_ans to set
     */
    public void setLbl_1_bought_ans(JLabel lbl_1_bought_ans) {
        this.lbl_1_bought_ans = lbl_1_bought_ans;
    }
    /**
     * @return the lbl_1_totArtPrice
     */
    public JLabel getLbl_1_totArtPrice() {
        return lbl_1_totArtPrice;
    }
    /**
     * @param lbl_1_totArtPrice the lbl_1_totArtPrice to set
     */
    public void setLbl_1_totArtPrice(JLabel lbl_1_totArtPrice) {
        this.lbl_1_totArtPrice = lbl_1_totArtPrice;
    }
    /**
     * @return the lbl_2_totArtPrice
     */
    public JLabel getLbl_2_totArtPrice() {
        return lbl_2_totArtPrice;
    }
    /**
     * @param lbl_2_totArtPrice the lbl_2_totArtPrice to set
     */
    public void setLbl_2_totArtPrice(JLabel lbl_2_totArtPrice) {
        this.lbl_2_totArtPrice = lbl_2_totArtPrice;
    }
    /**
     * @return the ta_2_checker
     */
    public JTextArea getTa_2_checker() {
        return ta_2_checker;
    }
    /**
     * @param ta_2_checker the ta_2_checker to set
     */
    public void setLbl_checker(JTextArea ta_2_checker) {
        this.ta_2_checker = ta_2_checker;
    }
    /**
     * @return the supermarketNames
     */
    public static JComboBox<String> getSupermarketNames() {
        return supermarketNames;
    }
    /**
     * @param aSupermarketNames the supermarketNames to set
     */
    public static void setSupermarketNames(JComboBox<String> aSupermarketNames) {
        supermarketNames = aSupermarketNames;
    }
    /**
     * @return the qtyArt
     */
    public static JComboBox<Integer> getQtyArt() {
        return qtyArt;
    }
    /**
     * @param aQtyArt the qtyArt to set
     */
    public static void setQtyArt(JComboBox<Integer> aQtyArt) {
        qtyArt = aQtyArt;
    }
    /**
     * @return the rb_discountYes
     */
    public JRadioButton getRb_discountYes() {
        return rb_discountYes;
    }
    /**
     * @param rb_discountYes the rb_discountYes to set
     */
    public void setRb_discountYes(JRadioButton rb_discountYes) {
        this.rb_discountYes = rb_discountYes;
    }
    /**
     * @return the rb_boughtYes
     */
    public JRadioButton getRb_boughtYes() {
        return rb_boughtYes;
    }
    /**
     * @param rb_boughtYes the rb_boughtYes to set
     */
    public void setRb_boughtYes(JRadioButton rb_boughtYes) {
        this.rb_boughtYes = rb_boughtYes;
    }
    /**
     * @return the rb_discountNo
     */
    public JRadioButton getRb_discountNo() {
        return rb_discountNo;
    }
    /**
     * @param rb_discountNo the rb_discountNo to set
     */
    public void setRb_discountNo(JRadioButton rb_discountNo) {
        this.rb_discountNo = rb_discountNo;
    }
    /**
     * @return the rb_boughtNo
     */
    public JRadioButton getRb_boughtNo() {
        return rb_boughtNo;
    }
    /**
     * @param rb_boughtNo the rb_boughtNo to set
     */
    public void setRb_boughtNo(JRadioButton rb_boughtNo) {
        this.rb_boughtNo = rb_boughtNo;
    }
    /**
     * @return the table
     */
    public JTable getTable() {
        return table;
    }
    /**
     * @param table the table to set
     */
    public void setTable(JTable table) {
        this.table = table;
    }
    /**
     * @return the scroll
     */
    public JScrollPane getScroll() {
        return scroll;
    }
    /**
     * @param scroll the scroll to set
     */
    public void setScroll(JScrollPane scroll) {
        this.scroll = scroll;
    }
    /**
     * @return the ta_1_checker
     */
    public JTextArea getTa_1_checker() {
        return ta_1_checker;
    }
    /**
     * @param ta_1_checker the ta_1_checker to set
     */
    public void setTa_1_checker(JTextArea ta_1_checker) {
        this.ta_1_checker = ta_1_checker;
    }
    /**
     * @return the converted
     */
    public String getConverted() {
        return converted;
    }
    /**
     * @param converted the converted to set
     */
    public void setConverted(String converted) {
        this.converted = converted;
    }
    /**
     * @return the bg_discount
     */
    public ButtonGroup getBg_discount() {
        return bg_discount;
    }
    /**
     * @param bg_discount the bg_discount to set
     */
    public void setBg_discount(ButtonGroup bg_discount) {
        this.bg_discount = bg_discount;
    }
    /**
     * @return the bg_bought
     */
    public ButtonGroup getBg_bought() {
        return bg_bought;
    }
    /**
     * @param bg_bought the bg_bought to set
     */
    public void setBg_bought(ButtonGroup bg_bought) {
        this.bg_bought = bg_bought;
    }
    /**
     * @return the separator
     */
    public JSeparator getSeparator() {
        return separator;
    }
    /**
     * @param separator the separator to set
     */
    public void setSeparator(JSeparator separator) {
        this.separator = separator;
    }
    /**
     * @return the separator_1
     */
    public JSeparator getSeparator_1() {
        return separator_1;
    }
    /**
     * @param separator_1 the separator_1 to set
     */
    public void setSeparator_1(JSeparator separator_1) {
        this.separator_1 = separator_1;
    }
    /**
     * @return the separator_2
     */
    public JSeparator getSeparator_2() {
        return separator_2;
    }
    /**
     * @param separator_2 the separator_2 to set
     */
    public void setSeparator_2(JSeparator separator_2) {
        this.separator_2 = separator_2;
    }
    /**
     * @return the separator_3
     */
    public JSeparator getSeparator_3() {
        return separator_3;
    }
    /**
     * @param separator_3 the separator_3 to set
     */
    public void setSeparator_3(JSeparator separator_3) {
        this.separator_3 = separator_3;
    }
    /**
     * @return the separator_4
     */
    public JSeparator getSeparator_4() {
        return separator_4;
    }
    /**
     * @param separator_4 the separator_4 to set
     */
    public void setSeparator_4(JSeparator separator_4) {
        this.separator_4 = separator_4;
    }
}