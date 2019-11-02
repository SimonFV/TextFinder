
package textfindersimon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * Clase que realiza las lecturas de los archivos para extraer las palabras o administrar la biblioteca.
 * @author sfv02
 */
public class Searcher {
    
    static WordsTree tree = new WordsTree();
    
    public static void addWords(String direction, WordsTree tree){
        FileReader fileReader = null;
        BufferedReader br = null;
        String text = "";
        String[] textSplit;
        String lineS = "";
        String top = "";
        String bot = "";
        String[] lineSplit;
        int line = 1;
        try{
            //Se crea el objeto File con la ruta del archivo
            File archiveDoc = new File(direction);
            String extension = "";
            int m = direction.lastIndexOf('.');
            if (m > 0) {
                extension = direction.substring(m+1);
            }
            if(extension.equals("docx")){
                //DOCX
                FileInputStream fis = new FileInputStream(direction);
                XWPFDocument document = new XWPFDocument(fis);
                XWPFWordExtractor extractor = new XWPFWordExtractor(document);
                text = extractor.getText();
                extractor.close();
            }else if(extension.equals("txt")){
                //TXT
                fileReader = new FileReader(direction);
                br = new BufferedReader(fileReader);
                while((lineS=br.readLine())!=null){
                    text = text + lineS + "\n";
                }
            }else{
                //PDF
                PDDocument document = PDDocument.load(new File(direction));
                if (!document.isEncrypted()) {
                    PDFTextStripper stripper = new PDFTextStripper();
                    text = stripper.getText(document);
                }
                document.close();
            }
            
            textSplit = text.split("\n");
            while(true){
                if(line == textSplit.length){
                    lineSplit = separate(textSplit[line-1]);
                    int i = 0;
                    if(line == 1){
                        while(i<lineSplit.length){
                            top = "";
                            bot = "";
                            tree.insert(top, bot, textSplit[line-1], lineSplit[i], direction, line);
                            i++;
                        }
                    }else{
                        while(i<lineSplit.length){
                            top = textSplit[line-2];
                            bot = "";
                            tree.insert(top, bot, textSplit[line-1], lineSplit[i], direction, line);
                            i++;
                        }
                    }
                    break;
                }else{
                    lineSplit = separate(textSplit[line-1]);
                    int i = 0;
                    if(line == 1){
                        while(i<lineSplit.length){
                            bot = textSplit[line];
                            top = "";
                            tree.insert(top, bot, textSplit[line-1], lineSplit[i], direction, line);
                            i++;
                        }
                    }else{
                        while(i<lineSplit.length){
                            top = textSplit[line-2];
                            bot = textSplit[line];
                            tree.insert(top, bot, textSplit[line-1], lineSplit[i], direction, line);
                            i++;
                        }
                    }
                }
                line++;
            }
        }catch(IOException e){
            System.out.println("fileNotFound");
        }finally{
            try{
                if(fileReader!=null){
                    fileReader.close();
                }
                if(br!=null){
                    br.close();
                }
            }catch(IOException e){
                System.out.println("File not found");
            }
        }
    }
    
    //separa las lineas limpiando los caracteres que no son palabras.
    public static String[] separate(String lineS){
        char[] lineC = lineS.toCharArray();
        String spaced = "";
        for(char a : lineC){
            if(a!='-'&&a!=','&&a!='.'&&a!=';'&&a!='!'&&a!='¡'&&a!='¿'
                    &&a!='?'&&a!='('&&a!=')'&&a!='_'&&a!='/'){
                spaced = spaced + a;
            }
        }
        return spaced.split(" ");
    }
    
    public static void writeFile(String archive){
        FileReader fileReader = null;
        BufferedReader br = null;
        FileWriter fileWriter = null;
        try{
            fileReader = new FileReader("Library.txt");
            br = new BufferedReader(fileReader);
            String text = "";
            String line = "";
            while((line=br.readLine())!=null){
                text=text+line+"\n";
            }
            //sobreescribe todo
            fileWriter = new FileWriter("Library.txt");
            fileWriter.write(text+archive+"\n");
        }catch(IOException e){
            System.out.println("fileNotFound");
        }finally{
            try{
                if(fileReader!=null){
                    fileReader.close();
                }
                if(br!=null){
                    br.close();
                }
                if(fileWriter!=null){
                    fileWriter.close();
                }
            }catch(IOException e){
                System.out.println("File not found");
            }
        }
    }
    
    public static void loadLibrary(LibraryList list){
        FileReader fileReader = null;
        BufferedReader br = null;
        try{
            fileReader = new FileReader("Library.txt");
            br = new BufferedReader(fileReader);
            String line = "";
            while((line=br.readLine())!=null){
                if(!"".equals(line)){
                    AddToLibrary.add(line, list);
                }
            }
        }catch(IOException e){
            System.out.println("fileNotFound");
        }finally{
            try{
                if(fileReader!=null){
                    fileReader.close();
                }
                if(br!=null){
                    br.close();
                }
            }catch(IOException e){
                System.out.println("File not found");
            }
        }
    }
    
    /**
    * Método estático que elimina los archivos especificados por el usuario.
    * @param direction Direccion del archivo a borrar.
    */
    public static void deleteArchive(String direction){
        int i = 0;
        FileReader nameReader = null;
        BufferedReader br = null;
        FileWriter fileWriter = null;
        
        try{
            nameReader = new FileReader("Library.txt");
            br = new BufferedReader(nameReader);
            String text = "";
            String line = "";
            int j = 0;
            while((line=br.readLine())!=null){
                if(!line.equals(direction)){
                    text=text+line+"\n";
                }else{
                    i=j;
                }
                j++;
            }
            fileWriter = new FileWriter("Library.txt");
            fileWriter.write(text);
            fileWriter.close();
            
        }catch(IOException e){
            System.out.println("fileNotFound");
        }finally{
            try{
                if(nameReader!=null){
                    nameReader.close();
                }
                if(br!=null){
                    br.close();
                }
                if(fileWriter!=null){
                    fileWriter.close();
                }
                if(nameReader!=null){
                    nameReader.close();
                }
            }catch(IOException e){
                System.out.println("File not found");
            }
        }
    }
    
}
