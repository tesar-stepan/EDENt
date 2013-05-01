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
public class GumState extends OrganState implements java.io.Serializable{

    public GumState() {
        
    }
    
    public GumState(String name, String mark, String icon) {
        super(name, mark, icon);
    }
    
    //getters and setters
    
}
