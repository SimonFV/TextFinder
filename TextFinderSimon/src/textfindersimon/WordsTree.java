
package textfindersimon;

/**
 *
 * @author sfv02
 */
public class WordsTree {
    
    private TreeNode root;
    
    public WordsTree(){
        this.root=null;
    }
    
    public boolean isEmpty(){
        return this.root==null;
    }
    
    //Buscar en el arbol
    public Coincidences getCoincidences(String e){
	return this.getCoincidences(e,this.root);
    }
    private Coincidences getCoincidences(String e, TreeNode current){
	if(current == null){
            return null;
	}else if(SortLibrary.isSmaller(e.toCharArray(), current.getElement().toCharArray())
                && !e.toLowerCase().equals(current.getElement().toLowerCase())){
            return getCoincidences(e,current.getLeft());
	}else if(SortLibrary.isSmaller(current.getElement().toCharArray(), e.toCharArray())
                && !e.toLowerCase().equals(current.getElement().toLowerCase())){
            return getCoincidences(e,current.getRight());
	}else{
            return current.getCoincidences();
	}
    }
    
    //Insertar elemento
    public void insert(String top,String bot,String mid,String e,String direction,int line){
	this.root = this.insert(top,bot,mid,e,direction,line,this.root);
    }
    private TreeNode insert (String top,String bot,String mid,String e,String direction,int line, TreeNode current){
	if(current == null){
            TreeNode n = new TreeNode(e);
            n.getCoincidences().addC(top, bot, mid, e, direction, line);
            return n;
	}else if(SortLibrary.isSmaller(e.toCharArray(), current.getElement().toCharArray())
                && !e.toLowerCase().equals(current.getElement().toLowerCase())){
            current.setLeft(insert(top,bot,mid,e,direction,line, current.getLeft()));
	}else if(SortLibrary.isSmaller(current.getElement().toCharArray(), e.toCharArray())
                && !e.toLowerCase().equals(current.getElement().toLowerCase())){
            current.setRight(insert(top,bot,mid,e,direction,line, current.getRight()));
	}else{
            current.getCoincidences().addC(top, bot, mid, e, direction, line);
        }
        return current;
    }
}
