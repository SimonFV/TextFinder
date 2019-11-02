
package textfindersimon;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.control.CheckBox;

/**
 *
 * @author sfv02
 */
public class AddToLibrary {
    
    public static void add(String direction, LibraryList list){
	try{
            File file = new File(direction);
            
            //Obtiene el nombre del archivo
            String nameS = file.getName();
            char[] name = nameS.toCharArray();
            
            //Obtiene la fecha del archivo
            BasicFileAttributes attrs;
            attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            FileTime time = attrs.creationTime();
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String formatted = simpleDateFormat.format( new Date( time.toMillis()) );
            String [] dateS = formatted.split("-");
            int[] date = {Integer.parseInt(dateS[0]),Integer.parseInt(dateS[1]),
                Integer.parseInt(dateS[2])};
            
            //Obtiene el tamaño del archivo
            double sizeD = file.length();
            int size = (int) Math.round(sizeD);
            //redondear: Math.round(1.1);   Math.ceil(2.7) "unidad superior"
            
            //Añade el archivo a la lista y lo guarda
            list.addLast(direction, name, date, size, new CheckBox());
            list.sortList();
            
	}catch (IOException e) {
            System.out.println("archivo no encontrado");
	}
    }
}
