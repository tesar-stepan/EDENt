/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.model;

import edent.model.utils.Organ;

/**
 *
 * @author Stepan Tesar
 */
public class Tooth extends Organ implements java.io.Serializable{
    private ToothState state;
    private boolean milk;
    private boolean topFloor;
    private boolean leftSide;
    private boolean denture;
    private boolean braced;
    private int position;
    private Gum gum;
    

    public Tooth(ToothState state, boolean milk, boolean topFloor, boolean leftSide, int position, Gum gum, Mouth mouth) {
        this(state, milk, topFloor, leftSide, false, false, position, gum, mouth);
    }

    public Tooth(ToothState state, boolean milk, boolean topFloor, boolean leftSide, boolean denture, boolean braced, int position, Gum gum, Mouth mouth) {
        super(mouth);
        this.state = state;
        this.milk = milk;
        this.topFloor = topFloor;
        this.leftSide = leftSide;
        this.denture = denture;
        this.braced = braced;
        this.position = position;
        this.gum = gum;
    }
    
    // getters and setters

    public boolean isDenture() {
        return denture;
    }

    public void setDenture(boolean denture) {
        this.denture = denture;
    }

    public boolean isBraced() {
        return braced;
    }

    public void setBraced(boolean braced) {
        this.braced = braced;
    }

    public ToothState getState() {
        return state;
    }

    public void setState(ToothState state) {
        this.state = state;
    }

    public boolean isMilk() {
        return milk;
    }

    public void setMilk(boolean milk) {
        this.milk = milk;
    }

    public boolean isTopFloor() {
        return topFloor;
    }

    private void setTopFloor(boolean topFloor) {
        this.topFloor = topFloor;
    }

    public boolean isLeftSide() {
        return leftSide;
    }

    private void setLeftSide(boolean leftSide) {
        this.leftSide = leftSide;
    }

    public int getPosition() {
        return position;
    }

    private void setPosition(int position) {
        this.position = position;
    }

    public Gum getGum() {
        return gum;
    }

    private void setGum(Gum gum) {
        this.gum = gum;
    }
    
}
