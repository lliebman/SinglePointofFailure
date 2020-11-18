import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ReadAndWrite {

    private ArrayList<GraphNode> graphNodeList;
    private Graph graph;
    private Scanner input;
    private FileWriter myWriter;
    private int counter;

    public ReadAndWrite(ArrayList<GraphNode> graphNodeList, Scanner input, FileWriter myWriter, Graph graph) {
        this.graphNodeList = graphNodeList;
        this.input = input;
        this.myWriter = myWriter;
        this.graph = graph;

    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void parseAndWrite() {
        setCounter(1);
        try {
            while (input.hasNextLine()) {
                String string = input.nextLine();
                if (string.length() == 1) { //line contains a single 0
                    //display SPF
                    displaySPFandSubnets(graphNodeList, myWriter);
                } else if (string.isEmpty()) { //line is blank
                    setCounter(getCounter() + 1);
                } else if (string.length() > 1) { //line contains a pair of numbers
                    buildConnections(string, graphNodeList);
                }
            }
            myWriter.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void displaySPFandSubnets(ArrayList<GraphNode> graphNodeList, FileWriter myWriter) throws IOException {
        if (!graphNodeList.isEmpty()) {
            graph = new Graph(graphNodeList);
            displayView(graph);
            HashMap<GraphNode, Integer> spfAndSubnets = graph.getSubnets();
            myWriter.write("Network #" + getCounter() + "\n");
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
    }

    private void buildConnections(String string, ArrayList<GraphNode> graphNodeList) {
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

    private void displayView(Graph graph) {
        try {
            Thread.sleep(10);  //added sleep because otherwise it runs too fast, and views don't show
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        NetworkView view = new NetworkView(graph);
        NetworkFrame frame = new NetworkFrame(view);
        frame.setVisible(true);
    }
}

