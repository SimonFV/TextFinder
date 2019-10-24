
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
            LibraryList temp1 = new LibraryList();
            LibraryList temp2 = new LibraryList();
            ArchiveNode temp = list.getFirst();
            while(temp!=null){
                if(temp!=pivot){
                    if(isSmaller(temp.getName(), pivot.getName())){
                        temp1.addLast(temp.getDirection(), temp.getName(), temp.getDate(), 
                                temp.getSize(), temp.getCheckBox());
                    }else{
                        temp2.addLast(temp.getDirection(), temp.getName(), temp.getDate(),
                                temp.getSize(), temp.getCheckBox());
                    }
                }
                temp = temp.getNext();
            }
            temp1 = nameSort(temp1);
            temp2 = nameSort(temp2);
            sorted = temp1;
            sorted.addLast(pivot.getDirection(), pivot.getName(), pivot.getDate(), 
                    pivot.getSize(), pivot.getCheckBox());
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
        while(i<node1.length && i<node2.length){
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
    
    //Ordena la lista por fecha con bubblesort
    public static LibraryList dateSort(LibraryList list){
        LibraryList sorted = list;
        if(list.getSize()>1){
            int i, j;
            for(i=0;i<list.getSize()-1;i++){
                for(j=0;j<list.getSize()-i-1;j++){
                    if(isNewer(list.getNode(j).getDate(),list.getNode(j+1).getDate())){
                        swap(list.getNode(j),list.getNode(j+1));
                    }
                }
            }
        }
        return sorted;
    }
    
    //Compara las fechas
    private static boolean isNewer(int[] date1, int[] date2){
        System.out.println("asd");
        if(date1[0]>date2[0]){
            return true;
        }else if(date1[0]<date2[0]){
            return false;
        }else{
            if(date1[1]>date2[1]){
                return true;
            }else if(date1[1]<date2[1]){
                return false;
            }else{
                return date1[2]>date2[2];
            }
        }
    }
    
    //returna la lista menor al pivote
    private static LibraryList firstHalf(LibraryList list, ArchiveNode pivot){
        LibraryList firstHalf = new LibraryList();
        ArchiveNode temp = list.getFirst();
        while(true){
            if(temp.getPosition()!=pivot.getPosition()){
                firstHalf.addLast(temp.getDirection(), temp.getName(), temp.getDate(),
                        temp.getSize(),temp.getCheckBox());
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
                secondHalf.addLast(temp.getDirection(), temp.getName(), temp.getDate(),
                        temp.getSize(), temp.getCheckBox());
            }else{
                break;
            }
        }
        return secondHalf;
    }
    
    //intercambia la informacion de los nodos
    private static void swap(ArchiveNode node1, ArchiveNode node2){
        ArchiveNode temp = new ArchiveNode(node1.getDirection(), node1.getName(), 
                node1.getDate(), node1.getSize(), node1.getCheckBox());
        node1.setDirection(node2.getDirection());
        node1.setName(node2.getName());
        node1.setDate(node2.getDate());
        node1.setSize(node2.getSize());
        node1.setCheckBox(node2.getCheckBox().isSelected());
        node2.setDirection(temp.getDirection());
        node2.setName(temp.getName());
        node2.setDate(temp.getDate());
        node2.setSize(temp.getSize());
        node2.setCheckBox(temp.getCheckBox().isSelected());
    }
    
    
}
