
package textfindersimon;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author sfv02
 */
public class LibraryList {
    
    private ArchiveNode first, last;
    private int size;
    
    public LibraryList(){
        this.first = null;
        this.last = null;
        this.size = 0;
    }
    
    //Añadir archivos a la lista enlazada
    public void addLast(String direction){
        if(this.first==null){
            this.first = new ArchiveNode(direction);
            this.last = this.first;
        }else{
            this.last.setNext(new ArchiveNode(direction));
            this.last.getNext().setPrev(this.last);
            this.last = this.last.getNext();
        }
        
        this.size++;
    }
    
    //Añadir labels al grid de la biblioteca
    public void uptdateLibrary(GridPane libraryPane){
        int i = 0;
        if(this.first!=null){
            ArchiveNode temp = this.first;
            while(true){
                if(temp.getNext()==null){
                    Label name = new Label(temp.getDirection());
                    GridPane.setConstraints(name, 0, i);
                    libraryPane.getChildren().add(name);
                    break;
                }else{
                    Label name = new Label(temp.getDirection());
                    GridPane.setConstraints(name, 0, i);
                    libraryPane.getChildren().add(name);
                    temp = temp.getNext();
                    i++;
                }
            }
        }
    }
    
    
    
    //Getters & Setters

    public ArchiveNode getFirst() {
        return first;
    }

    public void setFirst(ArchiveNode first) {
        this.first = first;
    }

    public ArchiveNode getLast() {
        return last;
    }

    public void setLast(ArchiveNode last) {
        this.last = last;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    
    //
    
    
}
