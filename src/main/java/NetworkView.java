import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class NetworkView extends JComponent {

    private final Graph graph;
    private int viewSize = 500;
    private int networkCenterX = viewSize / 2;
    private int networkCenterY = viewSize / 2;
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
        for (int i = 0; i < nrNodes; i++) {
            GraphNode node = listNodes.get(i);
            if (listNodes.get(i).isPof()) {
                g.setColor(Color.RED);
            } else g.setColor(Color.GREEN);
            angle = i * angleFactor;
            int x = (int) (networkCenterX + networkRadius * Math.cos(angle));
            int y = (int) (networkCenterY + networkRadius * Math.sin(angle));
            node.setX(x);
            node.setY(y);
            int nodeSize = 20;
            g.fillOval(x, y, nodeSize, nodeSize);
            g.setColor(Color.BLACK);
            g.drawString(node.getName(), x, y);
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
                if (drawnNodes.contains(node)) ;
                else {
                    g.drawLine(node.getX(), node.getY(), connection.getX(), connection.getY());
                    drawnNodes.add(node);
                }
            }
        }
    }
}
