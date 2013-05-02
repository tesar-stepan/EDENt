/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.model;

import edent.controller.HibernateController;
import edent.model.utils.Organ;

/**
 *
 * @author Stepan Tesar
 */
public class Gum extends Organ implements java.io.Serializable{
    private GumState state;

    public Gum() {
        
    }

    public Gum(Mouth mouth) {
        super(mouth);
//        System.out.println("Gum construct finished");
    }
    
    private void update(){
        HibernateController.update(this);
    }
    
    //getters and setters

    public GumState getState() {
        return state;
    }

    private void setState(GumState state) {
        this.state = state;
    }
    
}
