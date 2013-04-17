/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.model;

/**
 *
 * @author Stepan Tesar
 */
public abstract class OrganState {
    private long id;
    private String name;
    private String mark;

    public OrganState(String name, String mark) {
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
    
}
