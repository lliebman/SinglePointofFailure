import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
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
            while (input.hasNextLine()) {
                String string = input.nextLine();
                if (string.length() == 1) { //line contains a single 0
                    //display SPF
                    if (!graphNodeList.isEmpty()) {
                        graph = new Graph(graphNodeList);
                        HashMap<GraphNode, Integer> spfAndSubnets = graph.getSubnets();
                        myWriter.write("Network #" + counter + "\n");
                        spfAndSubnets.forEach((key, value) -> {
                            try {
                                myWriter.write("\tSPF node " + key.toString()
                                        + " leaves " + value.toString() + " subnets" + "\n");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });


                        if (spfAndSubnets.isEmpty()) {
                            myWriter.write("\tNo SPF nodes\n");
                        }
                        graphNodeList.clear();
                        myWriter.write("\n");
                    }
                } else if (string.isBlank()) { //line is blank
                    counter++;
                    //myWriter.write("Network #" + counter + "\n");
                } else if (string.length() > 1) { //line contains a pair of numbers
                    String[] splitBySpace = string.split("\\s+");
                    GraphNode node1 = new GraphNode(splitBySpace[0]);
                    GraphNode node2 = new GraphNode(splitBySpace[1]);
                    if (graphNodeList.contains(node1)) {
                        node1 = graphNodeList.get(graphNodeList.indexOf(node1));
                    }
                    if (graphNodeList.contains(node2)) {
                        node2 = graphNodeList.get(graphNodeList.indexOf(node2));
                    }
                    if (!graphNodeList.contains(node1)) {
                        graphNodeList.add(node1);
                    }
                    if (!graphNodeList.contains(node2)) {
                        graphNodeList.add(node2);
                    }

                    node1.addConnection(node2);
                    node2.addConnection(node1);

                }
            }
            myWriter.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}