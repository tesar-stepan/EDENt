/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.utils;

import edent.view.forms.SettingsForm;

/**
 *
 * @author Stepan Tesar
 */
public final class SettingsButtonApplication extends SettingsButton{

    public SettingsButtonApplication(final SettingsForm form) {
        super(APPLICATION_LABEL);
        this.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                form.displayApplication();
                setSelected(true);
            }
        });
    }
    
}
