/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.model;

import edent.model.utils.OrganState;

/**
 *
 * @author Stepan Tesar
 */
public class ToothState extends OrganState implements java.io.Serializable{
    
    /**
     * The overlay image to display over the tooth in this state.
     */
    private String over;
    
    public ToothState(){
        
    }

    public ToothState(String name, String mark, String icon, String over) {
        super(name, mark, icon);
        this.over = over;
    }
    
    
    //getters and setters

    public String getOver() {
        return over;
    }

    public void setOver(String over) {
        this.over = over;
    }
    
}
