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
    private String direction, name, date, size;
    private boolean selected;
    
    public ArchiveNode(String direction){
        this.next = null;
        this.prev = null;
        this.direction = direction;
        this.selected = false;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    
    
}
