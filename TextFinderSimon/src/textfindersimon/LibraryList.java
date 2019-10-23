
package textfindersimon;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author sfv02
 */
public class LibraryList {
    
    private ArchiveNode first, last;
    private String sortMode;
    private int size;
    
    public LibraryList(){
        this.first = null;
        this.last = null;
        this.size = 0;
        this.sortMode = "NAME_TO_BOT";
    }
    
    //Añadir archivos a la lista enlazada
    public void addLast(String direction, char[] name, double[] date, double size){
        if(this.first==null){
            this.first = new ArchiveNode(direction, name, date, size);
            this.last = this.first;
        }else{
            this.last.setNext(new ArchiveNode(direction, name, date, size));
            this.last.getNext().setPrev(this.last);
            this.last = this.last.getNext();
        }
        this.last.setPosition(this.size);
        this.size++;
    }
    
    //Añadir labels al grid de la biblioteca
    public void uptdateLibrary(GridPane libraryPane){
        int i = 0;
        if(this.first!=null){
            ArchiveNode temp = this.first;
            while(true){
                if(temp.getNext()==null){
                    Label name = new Label(new String(temp.getName()));
                    GridPane.setConstraints(name, 0, i);
                    libraryPane.getChildren().add(name);
                    break;
                }else{
                    Label name = new Label(new String(temp.getName()));
                    GridPane.setConstraints(name, 0, i);
                    libraryPane.getChildren().add(name);
                    temp = temp.getNext();
                    i++;
                }
            }
        }
    }
    
    //Ordenar la lista
    public void sortList(){
        LibraryList temp = new LibraryList();
        if(sortMode.equals("NAME_TO_BOT")){
            temp = SortLibrary.nameSort(this);
            this.first = temp.first;
            this.last = temp.last;
            
        }
    }
    
    /**
    * Método que reestablece los valores de posición de todos los elemtos de la lista
    * en base a la lista actual.
    */
    public void updatePosition(){
        int i = 0;
        if(this.first!=null){
            ArchiveNode temp = this.first;
            while(true){
                if(temp.getNext() == null){
                    temp.setPosition(i);
                    i++;
                    break;
                }else{
                    temp.setPosition(i);
                    temp = temp.getNext();
                    i++;
                }
            }
        }
        this.size = i;
    }
    
    /**
    * Método que retorna el nodo que se encuentre en la posición ingresada como parámetro.
    * @param i Posición del nodo solicitado.
    * @return temp ArchiveNode
    */
    public ArchiveNode getNode(int i){
        ArchiveNode temp = this.first;
        while(i>0){
            temp = temp.getNext();
            i--;
        }
        return temp;
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
    
    public String getSortMode() {
        return sortMode;
    }

    public void setSortMode(String sortMode) {
        this.sortMode = sortMode;
    }
    
    
    
    
}
