/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.utils.settings;

import edent.view.utils.EdentButton;
import edent.view.utils.EdentButtonColor;
import java.awt.BorderLayout;

/**
 *
 * @author Stepan Tesar
 */
public class SettingsButton extends EdentButton {
    protected static final String USERS_LABEL = "Uživatelé";
    protected static final String PATIENTS_LABEL = "Pacienti";
    protected static final String APPLICATION_LABEL = "Zuby a dásně";
    
    /**
     * Creates a new button with given text as label
     * @param text the label of the button.
     */
    public SettingsButton(String text) {
        super(text, new java.awt.Dimension(240, 90), EdentButton.DEFAULT_FONT_SIZE, EdentButtonColor.blue, BorderLayout.EAST);
    }

}
