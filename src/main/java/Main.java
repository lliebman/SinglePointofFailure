import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
            ArrayList<GraphNode> graphNodeList = new ArrayList<>();
            Graph graph = new Graph(graphNodeList);
            File fileInput = new File(args[0]);
            try {
                Scanner input = new Scanner(fileInput);
                File output = new File("SPFoutput.txt");
                FileWriter myWriter = new FileWriter(output, false);
                ReadAndWrite readAndWrite = new ReadAndWrite(graphNodeList, input, myWriter, graph);
                readAndWrite.parseAndWrite();
            }catch (Exception e) {
                    e.printStackTrace();
            }
        }
}