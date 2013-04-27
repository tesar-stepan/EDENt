/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.controller;

import edent.view.MainFrame;

/**
 *
 * @author Stepan Tesar
 */
public class ViewController {
    private static final MainFrame mainFrame = new MainFrame();
    private static ViewController instance = new ViewController();

    private ViewController() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
//                String lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
//                try {
//                    UIManager.setLookAndFeel(lookAndFeel);
//                    MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
//                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//                    System.out.println("LookAndFeelException");
//                }
                mainFrame.setVisible(true);
                mainFrame.showUnlogged();
            }
        });
    }
    
    public static ViewController getInstance(){
        return instance;
    }
    
    public static void shutDown(){
        mainFrame.dispose();//TODO ask user for confirmation
        System.exit(0); //TODO inform other controllers first!
    }
    
    public static void showPrevious(){
        mainFrame.showPrevious();
    }
    
    public static void showSettings() {
        mainFrame.showSettings();
    }
    
    public static void showUnlogged() {
        mainFrame.showUnlogged();
    }
    
    /**
     *  MODEL COMMUNICATION
     */
    
    public static ModelController modelFacade(){
        return ModelController.getInstance();
    }
    
}
