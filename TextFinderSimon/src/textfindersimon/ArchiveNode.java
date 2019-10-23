/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textfindersimon;

/**
 *
 * @author sfv02
 */
public class ArchiveNode {
    
    private ArchiveNode next, prev;
    private String direction;
    private char[] name;
    private double[] date;
    private double size;
    private int position;
    private boolean selected;
    
    public ArchiveNode(String direction, char[] name, double[] date, double size){
        this.next = null;
        this.prev = null;
        this.direction = direction;
        this.name = name;
        this.date = date;
        this.size = size;
        this.selected = false;
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

    public double[] getDate() {
        return date;
    }

    public void setDate(double[] date) {
        this.date = date;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    
    
    
}
