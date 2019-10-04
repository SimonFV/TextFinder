
package textfindersimon;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author sfv02
 */
public class TextFinderMain extends Application{
    
    Stage window;
    Scene scene;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage){
        window = primaryStage;
        window.setTitle("Text Finder (By SimonFV)");
        
        BorderPane root = new BorderPane();
        Insets insetsRoot = new Insets(6);
        ScrollPane libraryScroll = new ScrollPane();
        ScrollPane resultScroll = new ScrollPane();
        GridPane libraryPane = new GridPane();
        GridPane resultPane = new GridPane();
        
        //Titulos de las columnas de la biblioteca
        Label name = new Label("Nombre");
        GridPane.setConstraints(name, 0, 0);
        Label date = new Label("Fecha");
        GridPane.setConstraints(date, 1, 0);
        Label size = new Label("Tamaño");
        GridPane.setConstraints(size, 2, 0);
        libraryPane.getChildren().addAll(name, date, size);
        
        //Label prueba de resultados
        Label result1 = new Label("resultado 1");
        GridPane.setConstraints(result1, 0, 0);
        resultPane.getChildren().add(result1);
        
        //Estilo del panel principal
        root.setStyle("-fx-background-color: rgba(20, 20, 20, 0.5);");
        
        //Estilo de la biblioteca
        libraryPane.setStyle("-fx-background-color: rgba(100, 100, 100, 0.5);");
        libraryPane.setHgap(5);
        libraryPane.setVgap(5);
        
        //Estilo de los paneles scroll
        libraryScroll.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        resultScroll.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        
        
        //Añadido los paneles de contenido a los paneles de scroll
        libraryScroll.setContent(libraryPane);
        BorderPane.setMargin(libraryScroll, insetsRoot);
        resultScroll.setContent(resultPane);
        BorderPane.setMargin(resultScroll, insetsRoot);
        
        root.setLeft(libraryScroll);
        root.setCenter(resultScroll);
        
        this.scene = new Scene(root,800,600);
        this.window.setScene(scene);
        this.window.show();
        
    }
    
}
