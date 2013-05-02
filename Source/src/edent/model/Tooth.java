/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.model;

import edent.controller.HibernateController;
import edent.model.utils.Organ;

/**
 *
 * @author Stepan Tesar
 */
public class Tooth extends Organ implements java.io.Serializable, Comparable{
    private ToothState state;
    private boolean milk;
    private boolean topFloor;
    private boolean leftSide;
    private boolean denture;
    private boolean braced;
    private int position;
    private Gum gum;

    public Tooth() {
        
    }
    
    public Tooth(Mouth mouth) {
        super(mouth);
        this.gum = new Gum(mouth);
    }
    
    public Tooth(boolean milk, boolean topFloor, boolean leftSide, int position, Mouth mouth) {
        super(mouth);
        this.state = null;
        this.milk = milk;
        this.topFloor = topFloor;
        this.leftSide = leftSide;
        this.denture = false;
        this.braced = false;
        this.position = position;
        this.gum = new Gum(mouth);
        HibernateController.update(this);
    }
    
    private void update(){
        HibernateController.update(this);
    }
    
    // getters and setters

    public ToothState getState() {
        return state;
    }

    public boolean isMilk() {
        return milk;
    }

    public boolean isTopFloor() {
        return topFloor;
    }

    public boolean isLeftSide() {
        return leftSide;
    }

    public boolean isDenture() {
        return denture;
    }

    public boolean isBraced() {
        return braced;
    }

    public int getPosition() {
        return position;
    }

    public Gum getGum() {
        return gum;
    }

    private void setState(ToothState state) {
        this.state = state;
    }

    private void setMilk(boolean milk) {
        this.milk = milk;
    }

    private void setTopFloor(boolean topFloor) {
        this.topFloor = topFloor;
    }

    private void setLeftSide(boolean leftSide) {
        this.leftSide = leftSide;
    }

    private void setDenture(boolean denture) {
        this.denture = denture;
    }

    private void setBraced(boolean braced) {
        this.braced = braced;
    }

    private void setPosition(int position) {
        this.position = position;
    }

    private void setGum(Gum gum) {
        this.gum = gum;
    }
    
    
    
    @Override
    public int compareTo(Object o){
        if(o.getClass().equals(this.getClass())){
            Tooth p = (Tooth)o;
            return this.position-p.position;
        }
        return 0;
    }

    public void changeBraced(boolean b) {
        this.setBraced(b);
        update();
    }
    
    public void changeMilk(boolean m) {
        this.setMilk(m);
        update();
    }

    public void changeState(ToothState state) {
        this.setState(state);
        update();
    }
    
}
