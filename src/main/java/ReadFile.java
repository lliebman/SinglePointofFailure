import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
    String filename;

    public ReadFile(String filename) {
        this.filename = filename;
    }

    public File convertFilenameToFile() {
        File file = new File(filename);
        return file;
    }

    public void parseFile() {
        try {
            Scanner scanner = new Scanner(convertFilenameToFile());
            int first;
            int second;
            int n = scanner.nextInt();
            for (int i = 0; i < n; i++) {
                if ((first = scanner.nextInt()) != 0) {
                    String numberString1 = String.valueOf(first);
                    GraphNode node1 = new GraphNode(numberString1);
                    second = scanner.nextInt();
                    String numberString2 = String.valueOf(second);
                    GraphNode node2 = new GraphNode(numberString2);
                    node1.addConnection(node2);
                    node2.addConnection(node1);
                } else {
                    break;
                }
            }
        } catch (Exception e) {
        }

    }
}
