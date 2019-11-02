
package textfindersimon;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author sfv02
 */
public class Coincidence {
    
    protected Coincidence next;
    protected String topText, botText, midText, word;
    protected String direction;
    protected int line;
    protected GridPane body = new GridPane();
    protected TextFlow midLine = new TextFlow();
    
    public Coincidence(String topText,String botText,String midText,String word,
            String direction,int line){
        this.next = null;
        this.line = line;
        this.direction = direction;
        this.topText = topText;
        this.botText = botText;
        this.midText = midText;
        this.word = word;
    }

    public Coincidence getNext() {
        return next;
    }

    public void setNext(Coincidence next) {
        this.next = next;
    }

    public String getMidText() {
        return midText;
    }

    public void setMidText(String midText) {
        this.midText = midText;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }
    
    public String getTopText() {
        return topText;
    }

    public void setTopText(String topText) {
        this.topText = topText;
    }

    public String getBotText() {
        return botText;
    }

    public void setBotText(String botText) {
        this.botText = botText;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
    
    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public GridPane getBody() {
        return body;
    }
    
    public void setBody(String top, String bot, String mid, String word){
        body.getChildren().clear();
        Text topTextT = new Text(top);
        GridPane.setConstraints(topTextT, 0, 0);
        Text midTextT = new Text(mid);
        GridPane.setConstraints(midTextT, 0, 1);
        Text botTextT = new Text(bot);
        GridPane.setConstraints(botTextT, 0, 2);
        Label lineL = new Label("Línea: "+Integer.toString(line));
        GridPane.setConstraints(lineL, 1, 0);
        Button OpenFile = new Button("Abrir Archivo");
        OpenFile.setOnMouseClicked(e->{
            try {
                File objetofile = new File(this.direction);
                Desktop.getDesktop().open(objetofile);
            }catch (IOException ex) {
                System.out.println("No se encontro el archivo de origen archivo");
            }
        });
        GridPane.setConstraints(OpenFile, 1, 1);
        this.body.setVgap(5);
        this.body.setStyle("-fx-background-color: rgba(100, 100, 100, 0.5);");
        this.body.getChildren().addAll(topTextT, midTextT, botTextT, lineL, OpenFile);
    }
    
}
