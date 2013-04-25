/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.utils;

/**
 *
 * @author Stepan Tesar
 */
public interface EdentGridDisplayable {
    
    /**
     * 
     * @return number of collumns in data grid required to display this objects
     * properties.
     */
    public int getColumnsCount();
    
    /**
     * 
     * @return values of collumns to be displayed in the data grid.
     */
    public String[] getColumnsData();
    
    /**
     * @return the name of class of EdentForm used for editing of this object.
     */
    public EdentGridDisplaybleForms getEditingForm();
    
    public long getId();
    
}
