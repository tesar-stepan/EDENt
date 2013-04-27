/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent;

import edent.controller.ModelController;
import edent.controller.ViewController;

/**
 *
 * @author Stepan Tesar
 */
public class EDENt {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ModelController modelC = ModelController.getInstance();
        ViewController viewC = ViewController.getInstance();
    }
}
