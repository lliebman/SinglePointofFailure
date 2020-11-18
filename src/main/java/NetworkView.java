import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class NetworkView extends JComponent {

    private final Graph graph;
    private final int viewSize = 500;
    int nrNodes;

    List<GraphNode> listNodes = new ArrayList<>();

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
        int networkRadius = nrNodes * 10;
        double angleFactor = 2 * Math.PI / nrNodes;
        double angle;
        int i = 0;
        if (listNodes.isEmpty()) {
            g.setColor(Color.BLACK);
            g.drawString("No nodes found", viewSize / 2, viewSize / 2);
        }
        for (GraphNode node : listNodes) {
            if (node.isPof()) {
                g.setColor(Color.RED);
            } else g.setColor(Color.GREEN);
            angle = i * angleFactor;
            int networkCenterX = viewSize / 2;
            int x = (int) (networkCenterX + networkRadius * Math.cos(angle));
            int networkCenterY = viewSize / 2;
            int y = (int) (networkCenterY + networkRadius * Math.sin(angle));
            node.setX(x);
            node.setY(y);
            int nodeSize = 25;
            g.fillOval(x-9, y-16, nodeSize, nodeSize);
            g.setColor(Color.BLACK);
            g.drawString(node.getName(), x, y);
            i++;
        }
    }

    void paintConnections(Graphics g) {
        listNodes = graph.getGraph();
        HashMap<GraphNode, GraphNode> drawnConnections = new HashMap<>();
        g.setColor(Color.BLUE);
        for (GraphNode node : listNodes) {
            for (GraphNode connection : node.getConnections()) {
                if (drawnConnections.get(node) != connection) {
                    g.drawLine(node.getX(), node.getY(), connection.getX(), connection.getY());
                    drawnConnections.put(node, connection);
                }
            }
        }
    }
}
