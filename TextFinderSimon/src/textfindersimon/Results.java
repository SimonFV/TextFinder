
package textfindersimon;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author sfv02
 */
public class Results {
    
    public static void generate(String[] words, WordsTree tree, GridPane resultPane){
        resultPane.getChildren().clear();
        Coincidence temp = null;
        for(String word : words){
            if(tree.getCoincidences(word)!=null){
                temp = tree.getCoincidences(word).first;
            }
            int j = 0;
            while(temp!=null){
                temp.setBody(temp.topText, temp.botText, temp.midText, word);
                GridPane.setConstraints(temp.getBody(), 0,j);
                resultPane.getChildren().add(temp.getBody());
                j++;
                temp = temp.next;
            }
        }
    }
    
}
