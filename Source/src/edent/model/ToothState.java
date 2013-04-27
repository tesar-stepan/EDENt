/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.model;

/**
 *
 * @author Stepan Tesar
 */
public class ToothState extends OrganState implements java.io.Serializable{   
    /**
     * The little icon displayed near the tooth in this state.
     */
    private String icon;
    
    /**
     * The overlay image to display over the tooth in this state.
     */
    private String over;
    
    public ToothState(){
        
    }

    public ToothState(String name, String mark, String icon, String over) {
        super(name, mark);
        this.icon = icon;
        this.over = over;
    }
    
    
    //getters and setters
    
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getOver() {
        return over;
    }

    public void setOver(String over) {
        this.over = over;
    }
    
}
