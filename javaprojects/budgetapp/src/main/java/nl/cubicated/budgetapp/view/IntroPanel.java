package nl.cubicated.budgetapp.view;

import java.awt.*;
import javax.swing.*;
import nl.cubicated.budgetapp.model.CustomJPanel;

/**
 *
 * @author Tanja Bekker
 * Naam: IntroPanel
 * Type: Klasse
 * Functionaliteit:
 * - IntroPanel bevat een textarea met uitleg over de 
 *   functionaliteiten de invul- en keuzevelden in het InnerPanel en de buttons 
 *   in het buttonPanel van het ButtonPanel.
 */
public class IntroPanel extends CustomJPanel{
    private JTextArea introTekst;
    
    // Getter en setter
    /**
     * @return the introTekst
     */
    public JTextArea getIntroTekst() {
        return introTekst;
    }

    /**
     * @param introTekst the introTekst to set
     */
    public void setIntroTekst(JTextArea introTekst) {
        this.introTekst = introTekst;
    }
    /* Naam: IntroPanel
    *  Type: Constructor
    *  Functionaliteit: 
    *  - Init middels super()
       - Setting layout
       - Aanmaken JTextArea en vullen met tekst
       - Uitlijnen van tekst in JTextArea
       - Onderdelen toevoegen aan JPanel
    */
    public IntroPanel(){
        super();
        setBorder(BorderFactory.createEmptyBorder(8,8,8
                ,8));
        setLayout(new FlowLayout());
        setOpaque(false);
        introTekst=new JTextArea(3,60);
        introTekst.setBackground(Color.WHITE);
        introTekst.setText("Welkom, \nOm een nieuwe weeklijst te maken, kan je "
                + "links de velden invullen en vervolgens klik je op één van de onderstaande buttons.\n"+ 
                "Onderaan het venster staan de volgende buttons: \n"+
            "\nVoeg artikel toe:    \tVoegt het artikel toe aan de tabel in het vak aan de rechterkant.\n"+
            "\nDelete artikel:         \tDelete het geselecteerde artikel uit "
                + "de lijst.\n"+
            "\nExport weeklijst(en):   \tExporteert gemaakte lijst als txt "
                                        + "bestanden."+
                                       "\n\t\tDeze kan je terugvinden in de map: "
                                        +"\n\t\tsrc/main/weeklijsten/.\n"
        );
        introTekst.setWrapStyleWord(true);
        introTekst.setLineWrap(true);
        introTekst.setMargin(new Insets(5,5,5,5));
        introTekst.setToolTipText("Info over programma."); 
        introTekst.setEditable(false);
        add(introTekst);
    }  
}