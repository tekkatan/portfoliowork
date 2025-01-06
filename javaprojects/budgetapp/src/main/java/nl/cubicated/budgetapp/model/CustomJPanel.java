package nl.cubicated.budgetapp.model;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author Tanja Bekker
 * Naam: CustomJPanel
 * Type: Klasse, JPanel
 * Functionaliteit: styling alle JPanels 
 */
public class CustomJPanel extends JPanel{
    /**
     * Naam: CustomJPanel
     * Functionaliteit: 
     * Middels de UIManager font,foreground en background stylen:
     *  - Panel
     *  - Label
     *  - TextField
     *  - TextArea
     *  - Combobox
     *  - CheckBox
     *  - Button
     *  - Table
     */
    public CustomJPanel(){
        // JPanel styling
        UIManager.put("Panel.font",new Font("Arial",Font.PLAIN,18));
        UIManager.put("Panel.background",new ColorUIResource(new Color(0xF4EFF9)));
        // Label styling
        UIManager.put("Label.font",new Font("Arial",Font.PLAIN,18));
        UIManager.put("Label.foreground",Color.black);
        // TextField styling
        UIManager.put("TextField.font",new Font("Arial",Font.PLAIN,18));
        UIManager.put("TextField.foreground",Color.BLACK);
        UIManager.put("TextField.background",Color.WHITE);
        // TextArea styling
        UIManager.put("TextArea.font",new Font("Arial",Font.PLAIN,18));
        UIManager.put("TextArea.background",new ColorUIResource(new Color(0xF4EFF9)));
        UIManager.put("TextArea.foreground",Color.BLACK);
        // Combobox styling
        UIManager.put("ComboBox.font",new Font("Arial",Font.PLAIN,18));
        UIManager.put("ComboBox.foreground",
            new ColorUIResource(UIManager.getColor("Label.foreground")));
        UIManager.put("ComboBox.background",
            new ColorUIResource(UIManager.getColor("TextField.background")));
        // Button styling
        UIManager.put("Button.font",new Font("Arial",Font.PLAIN,18));
        UIManager.put("Button.background",
            new ColorUIResource(new Color(0x1ea59f)));
        UIManager.put("Button.forground",Color.WHITE);
        // RadioButton
        UIManager.put("RadioButton.font",new Font("Arial",Font.PLAIN,18));
        UIManager.put("RadioButton.foreground",Color.BLACK);
        UIManager.put("RadioButton.background",new ColorUIResource(new Color(0xF4EFF9)));
        // Table
        UIManager.put("TableHeader.font",new Font("Arial",Font.BOLD,18));
        UIManager.put("Table.font",new Font("Arial",Font.BOLD,14));
        UIManager.put("TableHeader.background",new ColorUIResource(new Color(0x24BCB5)));
        UIManager.put("Table.selectionBackground",new ColorUIResource(new Color(0x693a96)));
    } 
}
