package nl.cubicated.budgetapp.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
/**
 * 
 * @author Tanja Bekker
 * 
 * Naam: CustomButtonGUI 
 * Type: Klasse
 * Functionaliteit:
 * - Klasse extends BasicButtonUI en is gemaakt om het 
 *   design van de buttons aan te passen naar de stijl van de opdrachtgever
 *   GGZ Lister.
 * 
 */
public class CustomButtonGUI extends BasicButtonUI{
    /**
     * Naam: makeBackground
     * Type: void method
     * Functionaliteiten:
     * - Button styling background
     * @param graphics
     * @param jc
     * @param yOffset 
     */
    private void makeBackground(Graphics graphics, JComponent jc, int yOffset){
        Dimension size= jc.getSize();
        Graphics2D graph2D=(Graphics2D)graphics;
        graph2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(jc.getBackground().darker());
        graphics.fillRoundRect(0, yOffset, size.width, size.height-yOffset,60,50);
        graphics.setColor(jc.getBackground());
        graphics.fillRoundRect(0,yOffset,size.width, size.height+yOffset-3,60,50);
    }
    /**
     * Naam: installUI
     * Type: void method
     * Functionaliteiten:
     * - Activeren custom design button
     * @param jc 
     */
    @Override
    public void installUI(JComponent jc){
        super.installUI(jc);
        AbstractButton abstractButton=(AbstractButton)jc ;
        abstractButton.setOpaque(false);
        abstractButton.setBorder(new EmptyBorder(15,15,15,15));
    }
    /**
     * Naam: paint
     * Type: void method
     * Functionaliteiten:
     * - Paint het nieuwe design op de button(s)
     * @param graphics
     * @param jc 
     */
    @Override
    public void paint(Graphics graphics, JComponent jc){
        AbstractButton b=(AbstractButton)jc;
        makeBackground(graphics,b,b.getModel().isPressed()?3:0);
        super.paint(graphics,jc);
    }
}