
package textfindersimon;

/**
 *
 * @author sfv02
 */
public class Coincidences {
    
    protected Coincidence first;
    
    public Coincidences(){
        this.first = null;
    }
    
    public void addC(String topText,String botText,String midText,String word,String direction,int line){
        Coincidence temp = this.first;
        Coincidence n = new Coincidence(topText,botText,midText,word,direction,line);
        if(this.first== null){
            this.first = n;
        }else{
            while(true){
                if(temp.next==null){
                    temp.next = n;
                    break;
                }else{
                    temp = temp.next;
                }
            }
        }
    }
    
    public Coincidence isHere(String topText,String botText,String midText,String direction,int line){
        Coincidence temp = this.first;
        while(temp!=null){
            if(temp.topText.equals(topText)&&temp.botText.equals(botText)&&temp.midText.equals(midText)&&
                    temp.direction.equals(direction) && temp.line == line){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    public Coincidence getFirst() {
        return first;
    }

    public void setFirst(Coincidence first) {
        this.first = first;
    }
    
    
    
}
