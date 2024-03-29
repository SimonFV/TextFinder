
package textfindersimon;

/**
 * Clase que ordena listas dependiendo del modo deseado.
 * @author sfv02
 */
public class SortLibrary {
    
    
    /**
    * Método que ordena la lista por nombre con QuickSort.
    * @param list lista a ordenar
    * @return list lista ordenada por nombre
    */
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
    public static boolean isSmaller(char[] node1, char[] node2){
        int i = 0;
        while(i<node1.length && i<node2.length){
            char n1 = node1[i];
            char n2 = node2[i];
            if(Character.isUpperCase(node1[i])){
                n1 = Character.toLowerCase(node1[i]);
            }
            if(Character.isUpperCase(node2[i])){
                n2 = Character.toLowerCase(node2[i]);
            }
            if(n1<n2){
                return true;
            }else if(n1>n2){
                return false;
            }else{
                i++;
            }
        }
        return node1.length<=node2.length;
    }
    
    /**
    * Método que ordena la lista por fecha con BubbleSort.
    * @param list lista a ordenar
    * @return list lista ordenada por nombre
    */
    public static LibraryList dateSort(LibraryList list){
        LibraryList sorted = list;
        if(list.getSize()>1){
            int i, j;
            for(i=0;i<list.getSize()-1;i++){
                for(j=0;j<list.getSize()-i-1;j++){
                    if(isNewer(list.getNode(j).getDate(),list.getNode(j+1).getDate())){
                        swap(list.getNode(j),list.getNode(j+1),list);
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
    
     
    /**
    * Método que ordena la lista por tamaño de archivo con RadixSort
    * @param list lista a ordenar
    * @return list lista ordenada por nombre
    */
    public static LibraryList sizeSort(LibraryList list){
        if(list.getSize()>1){
            ArchiveNode temp = list.getFirst();
            //Obtiene el numero con mayor cantidad de digitos
            int max = temp.getSize();
            while(temp.getNext()!=null){
                if(temp.getNext().getSize()>max){
                    max=temp.getNext().getSize();
                }
                temp=temp.getNext();
            }
            String[] maxS = Integer.toString(max).split("");
            max = maxS.length;
            int i = 0;
            while(i<max){
                RSBucketList bucketlist = new RSBucketList();
                temp = list.getFirst();
                
                while(temp!=null){
                    bucketlist.getBucket(conv(temp.getSize(),max)[i]).addNode(temp);
                    temp=temp.getNext();
                }
                
                LibraryList tempL = new LibraryList();
                int z = 0;
                while(z<10){
                    RSBucket tempB = bucketlist.getBucket(z);
                    while(tempB.bot!=null){
                        tempL.addLast(tempB.bot.node.getDirection(), 
                                tempB.bot.node.getName(), 
                                tempB.bot.node.getDate(), 
                                tempB.bot.node.getSize(), 
                                tempB.bot.node.getCheckBox());
                        tempB=tempB.bot;
                    }
                    z++;
                }
                list = tempL;
                i++;
            }
        }
        return list;
    }
    
    //Convierte un entero en una array de enteros
    public static int[] conv(int d, int max){
        String[] numberS = Integer.toString(d).split("");
        int[] number = new int[max];
        int i = 0;
        int j = numberS.length-1;
        while(i<max){
            if(j>=0){
                number[i]=Integer.parseInt(numberS[j]);
                j--;
            }else{
                number[i]=0;
            }
            i++;
        }
        return number;
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
    private static void swap(ArchiveNode node1, ArchiveNode node2, LibraryList list){
        ArchiveNode temp = new ArchiveNode(node1.getDirection(), node1.getName(), 
                node1.getDate(), node1.getSize(), node1.getCheckBox(), list);
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
