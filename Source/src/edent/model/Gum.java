/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.model;

import edent.model.utils.Organ;

/**
 *
 * @author Stepan Tesar
 */
public class Gum extends Organ implements java.io.Serializable{
    private GumState state;

    public Gum(GumState state, Mouth mouth) {
        super(mouth);
        this.state = state;
//        System.out.println("Gum construct finished");
    }
    
    //getters and setters

    public GumState getState() {
        return state;
    }

    public void setState(GumState state) {
        this.state = state;
    }
    
}
