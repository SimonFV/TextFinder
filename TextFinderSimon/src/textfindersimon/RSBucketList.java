
package textfindersimon;

/**
 *
 * @author sfv02
 */
public class RSBucketList {
    
    RSBucket first;
    
    public RSBucketList(){
        this.first = new RSBucket();
        int i = 1;
        RSBucket temp = this.first;
        while(i<10){
            temp.next = new RSBucket();
            temp = temp.next;
            i++;
        }
    }
    
    /**
    * Método que retorna el bucket que se encuentre en la posición ingresada como parámetro.
    * @param i Posición del nodo solicitado.
    * @return temp RSBucket
    */
    public RSBucket getBucket(int i){
        RSBucket temp = this.first;
        while(i>0){
            temp = temp.next;
            i--;
        }
        return temp;
    }
}
