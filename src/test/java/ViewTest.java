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
        int viewSize = 500;
        int networkCenterX = viewSize / 2;
        int networkCenterY = viewSize / 2;
        int nrNodes = graph.getGraph().size();
        double angleFactor = 2 * Math.PI / nrNodes;
        double angle;

        Graphics g = mock(Graphics.class);

        GraphNode node1 = new GraphNode("1");
        GraphNode node2 = new GraphNode("2");
        GraphNode node3 = new GraphNode("3");
        GraphNode node4 = new GraphNode("4");
        GraphNode node5 = new GraphNode("5");
        GraphNode node6 = new GraphNode("6");
        GraphNode node7 = new GraphNode("7");
        GraphNode node8 = new GraphNode("8");
        GraphNode node9 = new GraphNode("9");
        GraphNode node10 = new GraphNode("10");
        GraphNode node11 = new GraphNode("11");
        GraphNode node12 = new GraphNode("12");

        node1.addConnection(node2);
        node1.addConnection(node3);
        node2.addConnection(node1);
        node2.addConnection(node3);
        node3.addConnection(node10);
        node3.addConnection(node8);
        node4.addConnection(node6);
        node4.addConnection(node3);
        node5.addConnection(node5);
        node6.addConnection(node6);
        node7.addConnection(node3);
        node7.addConnection(node4);
        node10.addConnection(node4);
        node12.addConnection(node11);

        List<GraphNode> listNodes = new ArrayList<>();
        listNodes.add(node1);
        listNodes.add(node2);
        listNodes.add(node3);
        listNodes.add(node4);
        listNodes.add(node5);
        listNodes.add(node6);
        listNodes.add(node7);
        listNodes.add(node8);
        listNodes.add(node9);
        listNodes.add(node10);
        listNodes.add(node11);
        listNodes.add(node12);

        //when
        view.paintNodes(g);

        //then
        for (int i = 0; i < nrNodes; i++) {
            GraphNode node = listNodes.get(i);
            angle = i * angleFactor;
            networkRadius += 10;
            int x = (int) (networkCenterX + networkRadius * Math.cos(angle));
            int y = (int) (networkCenterY + networkRadius * Math.sin(angle));
            verify(g).fillOval(x, y, nodeSize, nodeSize);
            verify(g).setColor(Color.BLACK);
            verify(g).drawString(node.getName(), x, y);
        }
    }

    @Test
    public void paintConnections() {
        //given
        Graph graph = mock(Graph.class);
        NetworkView view = new NetworkView(graph);

        HashMap<GraphNode, GraphNode> drawnConnections = new HashMap<>();

        Graphics g = mock(Graphics.class);

        GraphNode node1 = new GraphNode("1");
        GraphNode node2 = new GraphNode("2");
        GraphNode node3 = new GraphNode("3");
        GraphNode node4 = new GraphNode("4");
        GraphNode node5 = new GraphNode("5");
        GraphNode node6 = new GraphNode("6");
        GraphNode node7 = new GraphNode("7");
        GraphNode node8 = new GraphNode("8");
        GraphNode node9 = new GraphNode("9");
        GraphNode node10 = new GraphNode("10");
        GraphNode node11 = new GraphNode("11");
        GraphNode node12 = new GraphNode("12");

        node1.addConnection(node2);
        node1.addConnection(node3);
        node2.addConnection(node1);
        node2.addConnection(node3);
        node3.addConnection(node10);
        node3.addConnection(node8);
        node4.addConnection(node6);
        node4.addConnection(node3);
        node5.addConnection(node2);
        node6.addConnection(node1);
        node7.addConnection(node3);
        node7.addConnection(node4);
        node10.addConnection(node4);
        node12.addConnection(node11);

        List<GraphNode> listNodes = new ArrayList<>();
        listNodes.add(node1);
        listNodes.add(node2);
        listNodes.add(node3);
        listNodes.add(node4);
        listNodes.add(node5);
        listNodes.add(node6);
        listNodes.add(node7);
        listNodes.add(node8);
        listNodes.add(node9);
        listNodes.add(node10);
        listNodes.add(node11);
        listNodes.add(node12);

        when(graph.getGraph()).thenReturn(listNodes);

        view.paintNodes(g);

        //when
        view.paintConnections(g);

        //then
        for (GraphNode node : listNodes) {
            for (GraphNode connection : node.getConnections()) {
                if (drawnConnections.containsKey(node)) {
                    if (drawnConnections.get(node).equals(connection)) ;
                } else {
                    verify(g).drawLine(node.getX(), node.getY(), connection.getX(), connection.getY());
                    drawnConnections.put(node, connection);
                }
            }
        }
    }
}
