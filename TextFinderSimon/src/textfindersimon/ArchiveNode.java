/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textfindersimon;

import javafx.scene.control.CheckBox;

/**
 *
 * @author sfv02
 */
public class ArchiveNode {
    
    private ArchiveNode next, prev;
    private String direction;
    private char[] name;
    private int[] date;
    private double size;
    private int position;
    private CheckBox checkBox;
    
    public ArchiveNode(String direction, char[] name, int[] date, double size, CheckBox checkBox){
        this.next = null;
        this.prev = null;
        this.direction = direction;
        this.name = name;
        this.date = date;
        this.size = size;
        this.checkBox = checkBox;
        this.position = 0;
    }
    
    //getters & setters
    
    public ArchiveNode getNext() {
        return next;
    }
    public void setNext(ArchiveNode next) {
        this.next = next;
    }

    public ArchiveNode getPrev() {
        return prev;
    }

    public void setPrev(ArchiveNode prev) {
        this.prev = prev;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public char[] getName() {
        return name;
    }

    public void setName(char[] name) {
        this.name = name;
    }

    public int[] getDate() {
        return date;
    }

    public void setDate(int[] date) {
        this.date = date;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(boolean selected) {
        this.checkBox.setSelected(selected);
    }
    
    
}
