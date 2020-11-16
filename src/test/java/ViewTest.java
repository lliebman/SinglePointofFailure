import org.junit.Test;

import static org.mockito.Mockito.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewTest {

    @Test
    public void paintNodes() {
        //given
        Graph graph = mock(Graph.class);
        NetworkView view = new NetworkView(graph);

        final int nodeSize = 20;
        int networkRadius = 0;
        int viewSize = networkRadius + 300;
        int networkCenterX = viewSize / 2;
        int networkCenterY = viewSize / 2;
        int x = networkCenterX;
        int y = networkCenterY;
        List<GraphNode> listNodes = new ArrayList<>();
        int[] xyValues = new int[2];
        HashMap<GraphNode, int[]> nodeValues = new HashMap<>();
        int nrNodes = graph.getGraph().size();
        double angleFactor = 2 * Math.PI / nrNodes;
        double angle;

        Graphics g = mock(Graphics.class);

        GraphNode node1 = new GraphNode("1");
        GraphNode node2 = new GraphNode("2");
        GraphNode node3 = new GraphNode("3");
        GraphNode node4 = new GraphNode("4");
        GraphNode node5 = new GraphNode("5");

        node1.addConnection(node2);
        node1.addConnection(node3);

        node2.addConnection(node1);
        node2.addConnection(node3);

        node3.addConnection(node1);
        node3.addConnection(node2);
        node3.addConnection(node3);
        node3.addConnection(node4);

        node4.addConnection(node3);
        node4.addConnection(node5);

        node5.addConnection(node3);
        node5.addConnection(node4);

        listNodes.add(node1);
        listNodes.add(node2);
        listNodes.add(node3);
        listNodes.add(node4);
        listNodes.add(node5);

        //when
        view.paintNodes(g);

        //then
        for (int i = 0; i < nrNodes; i++) {
            angle = i * angleFactor;
            networkRadius += 10;
            x = (int) (networkCenterX + networkRadius * Math.cos(angle));
            y = (int) (networkCenterY + networkRadius * Math.sin(angle));
            xyValues[0] = x;
            xyValues[1] = y;
            nodeValues.put(listNodes.get(i), xyValues);
            g.drawOval(x, y, nodeSize, nodeSize);
        }
    }

    @Test
    public void paintConnections() {
        //given
        Graph graph = mock(Graph.class);
        NetworkView view = new NetworkView(graph);

        final int nodeSize = 20;
        int networkRadius = 0;
        int viewSize = networkRadius + 300;
        int networkCenterX = viewSize / 2;
        int networkCenterY = viewSize / 2;
        int x = networkCenterX;
        int y = networkCenterY;
        List<GraphNode> listNodes = new ArrayList<>();
        int[] xyValues = new int[2];
        HashMap<GraphNode, int[]> nodeValues = new HashMap<>();
        int nrNodes = graph.getGraph().size();
        List<GraphNode> drawnNodes = new ArrayList<>();
        double angleFactor = 2 * Math.PI / nrNodes;
        double angle;

        Graphics g = mock(Graphics.class);

        GraphNode node1 = new GraphNode("1");
        GraphNode node2 = new GraphNode("2");
        GraphNode node3 = new GraphNode("3");
        GraphNode node4 = new GraphNode("4");
        GraphNode node5 = new GraphNode("5");

        node1.addConnection(node2);
        node1.addConnection(node3);

        node2.addConnection(node1);
        node2.addConnection(node3);

        node3.addConnection(node1);
        node3.addConnection(node2);
        node3.addConnection(node3);
        node3.addConnection(node4);

        node4.addConnection(node3);
        node4.addConnection(node5);

        node5.addConnection(node3);
        node5.addConnection(node4);

        listNodes.add(node1);
        listNodes.add(node2);
        listNodes.add(node3);
        listNodes.add(node4);
        listNodes.add(node5);

        //when
        view.paintConnections(g);

        //then
        for (GraphNode node : listNodes) {
            for (GraphNode connection : node.getConnections()) {
                try {
                    verify(g).drawLine(nodeValues.get(node)[0], nodeValues.get(node)[1],
                            nodeValues.get(connection)[0], nodeValues.get(connection)[1]);
                    verify(drawnNodes.add(node));
                } catch (Exception ignored) {
                }
            }
        }
    }
}

