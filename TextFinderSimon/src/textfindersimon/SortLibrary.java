
package textfindersimon;

/**
 *
 * @author sfv02
 */
public class SortLibrary {
    
    //Ordena la lista por nombre con QuickSort
    public static LibraryList nameSort(LibraryList list){
        LibraryList sorted = list;
        if(list.getSize()>1){
            ArchiveNode pivot = list.getNode((list.getSize()-1)/2);
            System.out.println((list.getSize()-1)/2);
            LibraryList temp1 = new LibraryList();
            LibraryList temp2 = new LibraryList();
            ArchiveNode temp = list.getFirst();
            while(temp!=null){
                if(temp!=pivot){
                    if(isSmaller(temp.getName(), pivot.getName())){
                        temp1.addLast(temp.getDirection(), temp.getName(), temp.getDate(), temp.getSize());
                    }else{
                        temp2.addLast(temp.getDirection(), temp.getName(), temp.getDate(), temp.getSize());
                    }
                }
                temp = temp.getNext();
            }
            temp1 = nameSort(temp1);
            temp2 = nameSort(temp2);
            sorted = temp1;
            sorted.addLast(pivot.getDirection(), pivot.getName(), pivot.getDate(), pivot.getSize());
            if(temp2.getFirst()!=null){
                sorted.getLast().setNext(temp2.getFirst());
                temp2.getFirst().setPrev(sorted.getLast());
                sorted.setLast(temp2.getLast());
            }
        }
        return sorted;
        
    }
    
    //Compara los nombres
    private static boolean isSmaller(char[] node1, char[] node2){
        int i = 0;
        while(i<node1.length || i<node2.length){
            if(node1[i]<node2[i]){
                return true;
            }else if(node1[i]>node2[i]){
                return false;
            }else{
                i++;
            }
        }
        return node1.length<=node2.length;
    }
    
    //returna la lista menor al pivote
    private static LibraryList firstHalf(LibraryList list, ArchiveNode pivot){
        LibraryList firstHalf = new LibraryList();
        ArchiveNode temp = list.getFirst();
        while(true){
            if(temp.getPosition()!=pivot.getPosition()){
                firstHalf.addLast(temp.getDirection(), temp.getName(), temp.getDate(), temp.getSize());
                temp = temp.getNext();
            }else{
                break;
            }
        }
        return firstHalf;
    }
    //returna la lista mayor al pivote
    private static LibraryList secondHalf(ArchiveNode pivot){
        LibraryList secondHalf = new LibraryList();
        ArchiveNode temp = pivot;
        while(true){
            if(temp.getNext()!=null){
                temp = temp.getNext();
                secondHalf.addLast(temp.getDirection(), temp.getName(), temp.getDate(), temp.getSize());
            }else{
                break;
            }
        }
        return secondHalf;
    }
    
    //intercambia la informacion de los nodos
    public static void swap(ArchiveNode node1, ArchiveNode node2){
        ArchiveNode temp = new ArchiveNode(node1.getDirection(), node1.getName(), 
                node1.getDate(), node1.getSize());
        node1.setDirection(node2.getDirection());
        node1.setName(node2.getName());
        node1.setDate(node2.getDate());
        node1.setSize(node2.getSize());
        node2.setDirection(temp.getDirection());
        node2.setName(temp.getName());
        node2.setDate(temp.getDate());
        node2.setSize(temp.getSize());
    }
    
    
}
