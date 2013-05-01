/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.model.utils;

import edent.model.Mouth;

/**
 *
 * @author Stepan Tesar
 */
public abstract class Organ extends Diagnosable{
    protected Mouth mouth;

    public Organ(Mouth mouth) {
        super(mouth.owner);
        this.mouth = mouth;
    }
    
    //getters and setters

    public Mouth getMouth() {
        return mouth;
    }

    private void setMouth(Mouth mouth) {
        this.mouth = mouth;
    }
    
}
