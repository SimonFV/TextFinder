
package textfindersimon;

import java.util.Arrays;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * Lista doblemente enlazada que almacena los datos de los archivos de la biblioteca.
 * @author: Simon Fallas V.
 */
public class LibraryList {
    
    private ArchiveNode first, last;
    private String sortMode;
    private int size;
    
     /**
    * Método constructor de la lista biblioteca.
    */
    public LibraryList(){
        this.first = null;
        this.last = null;
        this.size = 0;
        this.sortMode = "NAME_TO_BOT";
    }
    
    /**
    * Método que añade un nodo al final de la lista.
    * @param direction Dirección del archivo en el disco.
    * @param name nombre del archivo.
    * @param date fecha de creación del acrhivo.
    * @param size tamaño del acrhivo.
    */
    public void addLast(String direction, char[] name, int[] date, int size, 
            CheckBox checkBox){
        if(this.first==null){
            this.first = new ArchiveNode(direction, name, date, size, checkBox,this);
            this.last = this.first;
        }else{
            this.last.setNext(new ArchiveNode(direction, name, date, size, checkBox,this));
            this.last.getNext().setPrev(this.last);
            this.last = this.last.getNext();
        }
        this.last.setPosition(this.size);
        this.size++;
    }
    
    /**
    * Método que agrega los Labels de la Biblioteca.
    * @param libraryPane Panel biblioteca donde se agregaran los labels.
    */
    public void uptdateLibrary(GridPane libraryPane){
        libraryPane.getChildren().clear();
        int i = 0;
        if(this.first!=null){
            ArchiveNode temp = this.first;
            while(temp!=null){
                Label nameL = new Label(new String(temp.getName()));
                nameL.setMaxSize(140, 20);
                nameL.setMinSize(140, 20);
                GridPane.setConstraints(nameL, 0, i);
                Label dateL = new Label(Arrays.toString(temp.getDate()));
                dateL.setMaxSize(75, 20);
                dateL.setMinSize(75, 20);
                GridPane.setConstraints(dateL, 1, i);
                int sizeN = temp.getSize();
                String sizeS;
                if(sizeN>1024 && sizeN<1024*1024){
                    sizeN = Math.round(sizeN/1024);
                    sizeS = Integer.toString(sizeN)+" KB";
                }else if(sizeN>1024*1024){
                    sizeN = Math.round(sizeN/(1024*1024));
                    sizeS = Integer.toString(sizeN)+" MB";
                }else{
                    sizeS = Integer.toString(sizeN)+" B";
                }
                Label sizeL = new Label(sizeS);
                sizeL.setMaxSize(67, 20);
                sizeL.setMinSize(67, 20);
                GridPane.setConstraints(sizeL, 2, i);
                GridPane.setConstraints(temp.getCheckBox(), 3, i);
                libraryPane.getChildren().addAll(nameL, dateL, sizeL, temp.getCheckBox());
                temp = temp.getNext();
                i++;
            }
        }
    }
    
    /**
    * Método que reordena la lista en forma ascendente por el tipo de orden actual.
    */
    public void sortList(){
        LibraryList temp;
        if(sortMode.equals("NAME_TO_BOT")){
            temp = SortLibrary.nameSort(this);
            this.first = temp.first;
            this.last = temp.last;
        }else if(sortMode.equals("DATE_TO_BOT")){
            temp = SortLibrary.dateSort(this);
            this.first = temp.first;
            this.last = temp.last;
        }else if(sortMode.equals("SIZE_TO_BOT")){
            temp = SortLibrary.sizeSort(this);
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
    
    /**
    * Método que agrega las palabras de los archivos seleccionados al árbol de búsqueda.
    * @param tree Árbol donde se agregarán las palabras con su información.
    */
    public void fillTree(WordsTree tree){
        ArchiveNode temp = this.first;
        while(temp != null){
            if(temp.getCheckBox().isSelected()){
                Searcher.addWords(temp.getDirection(), tree);
            }
            temp = temp.getNext();
        }
    }
    
    /**
    * Método que elimina los archivos seleccionados.
    */
    public void delete(){
        ArchiveNode temp = this.first;
        while(temp != null){
            if(temp.getCheckBox().isSelected()){
                Searcher.deleteArchive(temp.getDirection());
            }
            temp = temp.getNext();
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
    
    public String getSortMode() {
        return sortMode;
    }

    public void setSortMode(String sortMode) {
        this.sortMode = sortMode;
    }
    
    
    
    
}
