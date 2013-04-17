/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.model;

/**
 *
 * @author Stepan Tesar
 */
public class GumState extends OrganState implements java.io.Serializable{
    /**
     * The little icon displayed near the gum in this state.
     */
    private String icon;

    public GumState(String icon, String name, String mark) {
        super(name, mark);
        this.icon = icon;
    }
    
    //getters and setters

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    
}
