import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class NodeView extends JComponent {

    private final Graph graph;
    private final int nodeSize = 20;
    private int viewSize = 100;
    List<GraphNode> listNodes = new ArrayList<>();
    int xyValues[] = new int[2];
    HashMap<GraphNode, int[]> nodeValues = new HashMap<>();

    public NodeView(Graph graph) {
        this.graph = graph;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintNodes(g);
        paintConnections(g);
    }

    void paintNodes(Graphics g) {
        listNodes = graph.getGraph();
        int nrNodes = graph.getGraph().size();
        double angleFactor = 2 * Math.PI / nrNodes;
        double angle = 0;
        int networkRadius = 0;
        int networkCenterX = viewSize / 2;
        int networkCenterY = viewSize / 2;
        int x = networkCenterX;
        int y = networkCenterY;
        xyValues[0] = x;
        xyValues[1] = y;
        for (int i = 0; i < nrNodes; i++) {
            if (listNodes.get(i).isPof()) {
                g.setColor(Color.RED);
            } else g.setColor(Color.GREEN);
            angle = i * angleFactor;
            x = (int) (networkCenterX + networkRadius * Math.cos(angle));
            y = (int) (networkCenterY + networkRadius * Math.sin(angle));
            xyValues[0] = x;
            xyValues[1] = y;
            nodeValues.put(listNodes.get(i), xyValues);
            g.drawOval(x, y, nodeSize, nodeSize);
        }
    }

    void paintConnections(Graphics g) {
        listNodes = graph.getGraph();
        g.setColor(Color.BLUE);
        for (GraphNode node : listNodes) {
            for (GraphNode connection : node.getConnections()) {
                for (GraphNode possibleDuplicate : listNodes) {
                    if (connection.equals(possibleDuplicate)) continue;
                    else g.drawLine(nodeValues.get(node)[0], nodeValues.get(node)[1],
                            nodeValues.get(connection)[0], nodeValues.get(connection)[1]);
                }
            }
        }
    }
}
