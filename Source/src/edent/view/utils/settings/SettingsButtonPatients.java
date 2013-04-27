/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.utils.settings;

import edent.view.forms.SettingsForm;

/**
 *
 * @author Stepan Tesar
 */
public final class SettingsButtonPatients extends SettingsButton{

    public SettingsButtonPatients(final SettingsForm form) {
        super(PATIENTS_LABEL);
        this.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                form.displayPatients();
                setSelected(true);
            }
        });
    }
    
}
