/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package theradproject;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ttayfur
 */
public class GuiElements {
    private JPanel jPanel;
    private JLabel threadLabel;
    private JLabel baslangicLabel;
    private JLabel bitisLabel;
    private JLabel durumLabel;

    public GuiElements(JPanel jPanel, JLabel threadLabel, JLabel baslangicLabel, JLabel bitisLabel, JLabel durumLabel) {
        this.jPanel = jPanel;
        this.threadLabel = threadLabel;
        this.baslangicLabel = baslangicLabel;
        this.bitisLabel = bitisLabel;
        this.durumLabel = durumLabel;
    }

    public JPanel getjPanel() {
        return jPanel;
    }

    public void setjPanel(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    public JLabel getThreadLabel() {
        return threadLabel;
    }

    public void setThreadLabel(JLabel threadLabel) {
        this.threadLabel = threadLabel;
    }

    public JLabel getBaslangicLabel() {
        return baslangicLabel;
    }

    public void setBaslangicLabel(JLabel baslangicLabel) {
        this.baslangicLabel = baslangicLabel;
    }

    public JLabel getBitisLabel() {
        return bitisLabel;
    }

    public void setBitisLabel(JLabel bitisLabel) {
        this.bitisLabel = bitisLabel;
    }

    public JLabel getDurumLabel() {
        return durumLabel;
    }

    public void setDurumLabel(JLabel durumLabel) {
        this.durumLabel = durumLabel;
    }
    
    
    
}
