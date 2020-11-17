import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class NetworkView extends JComponent {

    private final Graph graph;
    private final int nodeSize = 20;
    private int networkRadius = 0;
    private int viewSize = networkRadius + 300;
    private int networkCenterX = viewSize / 2;
    private int networkCenterY = viewSize / 2;
    int nrNodes;
    int[] xyValues = new int[2];

    List<GraphNode> listNodes = new ArrayList<>();

    HashMap<GraphNode, int[]> nodeValues = new HashMap<>();

    public NetworkView(Graph graph) {
        this.graph = graph;
    }

    public int getViewSize() {
        return viewSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintNodes(g);
        paintConnections(g);
    }

    void paintNodes(Graphics g) {
        listNodes = graph.getGraph();
        nrNodes = graph.getGraph().size();

        double angleFactor = 2 * Math.PI / nrNodes;
        double angle;
        for (int i = 0; i < nrNodes; i++) {
            if (listNodes.get(i).isPof()) {
                g.setColor(Color.RED);
            } else g.setColor(Color.GREEN);
            angle = i * angleFactor;
            networkRadius += 5;
            int x = (int) (networkCenterX + networkRadius * Math.cos(angle));
            int y = (int) (networkCenterY + networkRadius * Math.sin(angle));
            xyValues[0] = x;
            xyValues[1] = y;
            nodeValues.put(listNodes.get(i), xyValues);
            g.fillOval(x, y, nodeSize, nodeSize);
            //g.drawChars();
        }
    }

    void paintConnections(Graphics g) {
        listNodes = graph.getGraph();
        nrNodes = graph.getGraph().size();
        List<GraphNode> drawnNodes = new ArrayList<>();
        g.setColor(Color.BLUE);
        for (int i = 0; i < nrNodes; i++) {
            GraphNode node = listNodes.get(i);
            for (GraphNode connection : node.getConnections()) {
                try {
                    g.drawLine(nodeValues.get(node)[0], nodeValues.get(node)[0],
                            nodeValues.get(connection)[0], nodeValues.get(connection)[1]);
                    drawnNodes.add(node);
                    System.out.println(nodeValues.get(node)[0]);
                    System.out.println(nodeValues.get(node)[1]);
                    System.out.println(nodeValues.get(connection)[0]);
                    System.out.println(nodeValues.get(connection)[1]);
                } catch (Exception ignored) {
                }
                /*
                for (GraphNode possibleDuplicate : drawnNodes) {
                    if (connection.equals(possibleDuplicate)) ;
                    else {
                        g.drawLine(nodeValues.get(node)[0], nodeValues.get(node)[1],
                                nodeValues.get(connection)[0], nodeValues.get(connection)[1]);
                        drawnNodes.add(node);
                    }
                }*/

            }
        }
    }
}