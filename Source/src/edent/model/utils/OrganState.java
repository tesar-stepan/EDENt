/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.model.utils;

import edent.controller.HibernateController;
import edent.view.utils.EdentInlineEditableObject;

/**
 *
 * @author Stepan Tesar
 */
public abstract class OrganState implements EdentInlineEditableObject {
    private long id;
    private String name = "";
    private String mark = "";
    /**
     * The little icon displayed near the tooth in this state.
     */
    private String icon = "";
    
    public OrganState(){
        
    }

    public OrganState(String name, String mark, String icon) {
        this.name = name;
        this.mark = mark;
    }
    
    //getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    @Override
    public void setStringValue(String name, String value) {
        switch(name){
            case "name": this.setName(value); break;
            case "mark": this.setMark(value); break;
            case "icon": this.setIcon(value); break;
            default: break;
        }
        HibernateController.update(this);
    }
    
    @Override
    public String toString(){
        return this.getMark()+"  "+this.getName();
    }
    
}
