import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            ArrayList<GraphNode> graphNodeList = new ArrayList<>();
            Graph graph;
            System.out.println("here 1");
            File fileInput = new File(args[0]);
            Scanner input = new Scanner(fileInput);
            File output = new File("SPFoutput.txt");
            FileWriter myWriter = new FileWriter(output, false);
            int counter = 1;
            System.out.println("here 2");
            myWriter.write("Network #" + counter);
            while (input.hasNextLine()) {
                String string = input.nextLine();
                if (string.length() == 1) { //line contains a single 0
                    System.out.println("here 3");
                    //display SPF
                    graph = new Graph(graphNodeList);
                    System.out.println("-----------"+graph.getGraph().toArray().length);
                    List<GraphNode> pointsOfFailure = graph.getSPF();
                    for (GraphNode gn : pointsOfFailure) {
                        System.out.println("I see you");
                        myWriter.write("\tSPF node " + gn.toString());
                    }
                    if(graphNodeList.isEmpty()) {
                        myWriter.write("\tNo SPF nodes");
                    }
                    graphNodeList.clear();
                    myWriter.write("\n");
                } else if (string.isBlank()) { //line is blank
                    System.out.println("here 4");
                    counter++;
                    myWriter.write("Network #" + counter);
                } else if (string.length() > 1){ //line contains a pair of numbers
                    System.out.println("here 5");
                    String[] splitBySpace = string.split("\\s+");
                    GraphNode node1 = new GraphNode(splitBySpace[0]);
                    GraphNode node2 = new GraphNode(splitBySpace[1]);
                    node1.addConnection(node2);
                    node2.addConnection(node1);
                    System.out.println(node1);
                    System.out.println(node2);
                    graphNodeList.add(node1);
                    graphNodeList.add(node2);
                }
            }
            myWriter.close();
            System.out.println("here 6");
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}