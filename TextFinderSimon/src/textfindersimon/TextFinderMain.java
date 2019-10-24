
package textfindersimon;

import java.io.File;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author sfv02
 */
public class TextFinderMain extends Application{
    
    private Stage window;
    private Scene scene;
    private LibraryList list = new LibraryList();
    
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
        
        //Paneles de la interfaz
        BorderPane root = new BorderPane();
        Insets insetsRoot = new Insets(6);
        ScrollPane libraryScroll = new ScrollPane();
        ScrollPane resultScroll = new ScrollPane();
        GridPane libraryPane = new GridPane();
        Group libraryPaneRoot = new Group();
        GridPane resultPane = new GridPane();
        GridPane searchPane = new GridPane();
        
        //Titulos de las columnas de la biblioteca
        Label name = new Label("Nombre");
        name.setLayoutX(5);
        name.setLayoutY(5);
        Label date = new Label("Fecha");
        date.setLayoutX(150);
        date.setLayoutY(5);
        Label size = new Label("Tamaño");
        size.setLayoutX(230);
        size.setLayoutY(5);
        CheckBox selectAll = new CheckBox();
        selectAll.setLayoutX(300);
        selectAll.setLayoutY(5);
        libraryPaneRoot.getChildren().addAll(name, date, size, selectAll);
        
        //Label prueba de resultados
        Label result1 = new Label("resultado 1");
        GridPane.setConstraints(result1, 0, 0);
        resultPane.getChildren().add(result1);
        
        //Contenido del panel de busqueda
        Label searchHere = new Label("                        Ingrese la palabra o frase a buscar: ");
        TextField getWord = new TextField();
        getWord.setMinWidth(150);
        GridPane.setConstraints(searchHere, 1, 0);
        GridPane.setConstraints(getWord, 2, 0);
        
        Button searchButton = new Button("Buscar");  //Boton para buscar texto
        GridPane.setConstraints(searchButton, 3, 0);
        
        Button fileButton = new Button("Añadir archivo");  //Boton para buscar archivos
        GridPane.setConstraints(fileButton, 0, 0);
        fileButton.setOnMouseClicked(e->{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Buscar Documento");
            // Agregar filtros para facilitar la busqueda
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Word", "*.docx"),
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("Texto", "*.txt")
            );
            File textFile = fileChooser.showOpenDialog(null);
            if(textFile != null){
                String direction = textFile.getPath();
                System.out.println(direction);

                AddToLibrary.add(direction, this.list);
                list.uptdateLibrary(libraryPane);
            }
        });
        
        
        searchPane.getChildren().addAll(searchHere, getWord, searchButton, fileButton);
        
                
        
        //Estilo del panel principal
        root.setStyle("-fx-background-color: rgba(20, 20, 20, 0.5);");
        
        //Estilo de la biblioteca
        libraryPane.setStyle("-fx-background-color: rgba(100, 100, 100, 0.5);");
        libraryPane.setHgap(5);
        libraryPane.setVgap(5);
        
        //Estilo de los paneles scroll
        libraryScroll.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        resultScroll.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        
        //Estilo del panel de búsqueda
        searchPane.setStyle("-fx-background-color: rgba(200, 200, 200, 0.5);");
        searchPane.setHgap(5);
        searchPane.setVgap(5);
        
        
        //Añadido los paneles de contenido a los paneles de scroll
        libraryScroll.setContent(libraryPane);
        libraryScroll.setLayoutX(5);
        libraryScroll.setLayoutY(20);
        libraryScroll.setPrefSize(320, 590);
        libraryPaneRoot.getChildren().add(libraryScroll);
        BorderPane.setMargin(libraryPaneRoot, insetsRoot);
        
        resultScroll.setContent(resultPane);
        BorderPane.setMargin(resultScroll, insetsRoot);
        
        BorderPane.setMargin(searchPane, insetsRoot);
        
        root.setLeft(libraryPaneRoot);
        root.setCenter(resultScroll);
        root.setTop(searchPane);
        
        //TEST
        
        this.list.addLast("asd", new char[]{'m','c','j'}, new int[]{2019, 3, 5}, 43, new CheckBox());
        this.list.addLast("asd", new char[]{'a','f','3'}, new int[]{2018, 03, 10}, 50, new CheckBox());
        this.list.addLast("asd", new char[]{'3','a'}, new int[]{2019, 3, 6}, 20, new CheckBox());
        this.list.addLast("asd", new char[]{'3','a', 'b'}, new int[]{2018, 3, 5}, 10, new CheckBox());
        this.list.sortList();
        this.list.uptdateLibrary(libraryPane);
        
        
        
        this.scene = new Scene(root,1200,700);
        this.window.setScene(scene);
        this.window.show();
        
        
    }
    
}
