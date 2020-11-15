import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadFile {
    String filename;

    public ReadFile(String filename) {
        this.filename = filename;
    }

    public File convertFilenameToFile() {
        File file = new File(filename);
        return file;
    }

    public void parseFile(File file) {
        convertFilenameToFile();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String previous = null;
            int number;
            while ((number = br.read()) != 0) {
                String numberString = String.valueOf(number);
                //add numberString as graphNode;
                //prev = numberString;
                if (previous != null) {
                    //set connection previous to numberString
                    //set connection numberString to previous
                }


            }
        } catch (Exception ex) {

        }
    }
}
