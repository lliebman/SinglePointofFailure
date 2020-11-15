import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        ReadFile readFile = new ReadFile(args[0]);
        readFile.parseFile();
        //STUFF HAPPENS HERE
        try {
            File output = new File("SPFoutput.txt");
            System.out.println("File created: " + output.getName());
                FileWriter myWriter = new FileWriter("SPFoutput.txt", false); //overwrite if file exists
                myWriter.write("Here's what you write"); //INCLUDE STUFF TO BE WRITTEN HERE
                myWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}