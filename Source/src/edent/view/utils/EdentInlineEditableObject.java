/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.utils;

/**
 * Interface that every object that can be edited by inline editing in settings
 * should implement.
 * @author Stepan Tesar
 */
public interface EdentInlineEditableObject {
    public static final String INSERT_VALUE = "Povinná hodnota";
    
    public void setStringValue(String name, String value);
    
}
