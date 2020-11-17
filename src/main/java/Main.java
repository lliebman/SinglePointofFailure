import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            ArrayList<GraphNode> graphNodeList = new ArrayList<>();
            Graph graph;
            File fileInput = new File(args[0]);
            Scanner input = new Scanner(fileInput);
            File output = new File("SPFoutput.txt");
            FileWriter myWriter = new FileWriter(output, false);
            int counter = 1;
            myWriter.write("Network #" + counter + "\n");
            while (input.hasNextLine()) {
                String string = input.nextLine();
                if (string.length() == 1) { //line contains a single 0
                    //display SPF
                    graph = new Graph(graphNodeList);
                    System.out.println(graphNodeList.toArray().length);
                    System.out.println("-----------"+graph.getGraph().toArray().length);
                    System.out.println("look up^^^^^^^^^^");
                    List<GraphNode> pointsOfFailure = graph.getSPF();
                    for (GraphNode gn : pointsOfFailure) {
                        myWriter.write("\tSPF node " + gn.toString() + "\n");
                    }
                    if(pointsOfFailure.isEmpty()) {
                        myWriter.write("\tNo SPF nodes\n");
                    }
                    graphNodeList.clear();
                    myWriter.write("\n");
                } else if (string.isBlank()) { //line is blank
                    counter++;
                    myWriter.write("Network #" + counter + "\n");
                } else if (string.length() > 1){ //line contains a pair of numbers
                    String[] splitBySpace = string.split("\\s+");
                    GraphNode node1 = new GraphNode(splitBySpace[0]);
                    GraphNode node2 = new GraphNode(splitBySpace[1]);
                    for (GraphNode gn : graphNodeList) {
                        if (gn.equals(node1)) {
                            node1 = gn;
                        } else if (gn.equals(node2)) {
                            node2 = gn;
                        }
                        node1.addConnection(node2);
                        node2.addConnection(node1);
                        break;
                    }
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