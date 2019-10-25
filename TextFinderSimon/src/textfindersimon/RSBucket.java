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
public class RSBucket {
    
    protected RSBucket next;
    protected ArchiveNode node;
    protected RSBucket bot;
    
    public RSBucket(){
        this.bot = null;
        this.next = null;
    }
    
    public void addNode(ArchiveNode node){
        RSBucket temp = this;
        while(temp.bot!=null){
            temp=temp.bot;
        }
        temp.bot=new RSBucket();
        temp.bot.node = node;
    }
    
    public ArchiveNode getNode2(int i){
        RSBucket temp = this.bot;
        while(i>0){
            temp = temp.bot;
            i--;
        }
        return temp.node;
    }
}
