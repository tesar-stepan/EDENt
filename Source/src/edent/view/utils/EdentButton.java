/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.utils;

import edent.view.MainFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author Stepan Tesar
 */
public class EdentButton extends JButton {
    protected static final int DEFAULT_FONT_SIZE = 25;
    private Color selectedLabelColor;
    private Color notSelectedLabelColor;
    private Color pressedLabelColor;
    private JLabel label;
    
    /**
     * 
     * @param text
     * @param size
     * @param fontSize
     * @param color color scheme
     * @param labelPosition use one of BorderLayout positioning constants.
     */
    public EdentButton(String text, Dimension size, int fontSize, EdentButtonColor color, String labelPosition){
        setMargin(new java.awt.Insets(20, 14, 20, 14));
        setAlignmentY(0.0F);
        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        switch(color){
            case blue:
                this.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edent/view/utils/blue.png")));
                this.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/edent/view/utils/white.png")));
                this.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/edent/view/utils/white.png")));
                this.selectedLabelColor = MainFrame.BACKGROUND;
                this.notSelectedLabelColor = MainFrame.FOREGROUND;
                this.pressedLabelColor = Color.BLACK;
                break;
            case white:
                this.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edent/view/utils/white.png")));
                this.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/edent/view/utils/gray.png")));
                this.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/edent/view/utils/gray.png")));
                this.selectedLabelColor = MainFrame.FOREGROUND;
                this.notSelectedLabelColor = MainFrame.BACKGROUND;
                this.pressedLabelColor = Color.WHITE;
                break;
            case gray:
                this.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edent/view/utils/gray.png")));
                this.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/edent/view/utils/white.png")));
                this.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/edent/view/utils/white.png")));
                this.selectedLabelColor = Color.BLACK;
                this.notSelectedLabelColor = Color.BLACK;
                this.pressedLabelColor = Color.BLACK;
                break;
            case red:
                this.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edent/view/utils/red.png")));
                this.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/edent/view/utils/darkred.png")));
                this.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/edent/view/utils/darkred.png")));
                this.selectedLabelColor = Color.WHITE;
                this.notSelectedLabelColor = Color.WHITE;
                this.pressedLabelColor = Color.WHITE;
                break;
            case green:
                this.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edent/view/utils/green.png")));
                this.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/edent/view/utils/darkgreen.png")));
                this.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/edent/view/utils/darkgreen.png")));
                this.selectedLabelColor = Color.WHITE;
                this.notSelectedLabelColor = Color.WHITE;
                this.pressedLabelColor = Color.WHITE;
                break;
                
        }
        
        this.setLayout(new BorderLayout());
        this.setFocusPainted(false);
        this.setBackground(null);
        this.setBorderPainted(false);
        
        this.setPreferredSize(size);
        this.setSize(size);
        
        label = new JLabel(text);
        label.setFont(new java.awt.Font("Verdana", 0, fontSize));
        label.setForeground(this.notSelectedLabelColor);
        int textPos = (labelPosition.equals(BorderLayout.EAST))?SwingConstants.LEFT:( (labelPosition.equals(BorderLayout.WEST))?SwingConstants.RIGHT:SwingConstants.CENTER  );
        label.setHorizontalTextPosition(textPos);
        this.add(label, labelPosition);
        
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                showPressed();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(isSelected()) {
                    showSelected();
                } else {
                    showNotSelected();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }
    
    private void showPressed(){
        this.label.setForeground(pressedLabelColor);
    }
    
    private void showSelected(){
        this.label.setForeground(selectedLabelColor);
    }
    
    private void showNotSelected(){
        this.label.setForeground(notSelectedLabelColor);
    }
    
    @Override
    public void setSelected(boolean selected){
        super.setSelected(selected);
        if(selected){
            this.showSelected();
        }else{
            this.showNotSelected();
        }
    }
    
    @Override
    public void setText(String text){
        this.label.setText(text);
    }
    
}
