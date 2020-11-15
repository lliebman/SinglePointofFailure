import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class GraphTest {

    @Test
    public void findSPF() {
        //given
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

        List<GraphNode> nodeList = new ArrayList<>();
        nodeList.add(node1);
        nodeList.add(node2);
        nodeList.add(node3);
        nodeList.add(node4);
        nodeList.add(node5);

        Graph graph = new Graph(nodeList);
        List<GraphNode> expectedList = new ArrayList<>();
        expectedList.add(node3);

        //when
        List<GraphNode> spfNodes = graph.getSPF();

        //then
        assertEquals(expectedList, spfNodes);
    }


    @Test
    public void getNumSubnets() {
        //given
        GraphNode node1 = new GraphNode("1");
        GraphNode node2 = new GraphNode("2");
        GraphNode node3 = new GraphNode("3");
        GraphNode node4 = new GraphNode("4");
        GraphNode node5 = new GraphNode("5");

        node3.addConnection(node2);
        node3.addConnection(node1);

        node2.addConnection(node1);
        node2.addConnection(node3);

        node1.addConnection(node1);
        node1.addConnection(node2);
        node1.addConnection(node3);
        node1.addConnection(node4);

        node4.addConnection(node1);
        node4.addConnection(node5);

        node5.addConnection(node1);
        node5.addConnection(node4);

        List<GraphNode> nodeList = new ArrayList<>();
        nodeList.add(node1);
        nodeList.add(node2);
        nodeList.add(node3);
        nodeList.add(node4);
        nodeList.add(node5);

        Graph graph = new Graph(nodeList);
        HashMap<GraphNode, Integer> expectedSubnets = new HashMap<>();
        expectedSubnets.put(node1, 2);

        //when
        HashMap<GraphNode, Integer> subnets = graph.getSubnets();

        //then
        assertEquals(1, subnets.size());
        assertEquals(expectedSubnets, subnets);
    }

    @Test
    public void getGraph() {
        //given
        GraphNode node1 = new GraphNode("1");
        GraphNode node2 = new GraphNode("2");
        GraphNode node3 = new GraphNode("3");
        GraphNode node4 = new GraphNode("4");
        GraphNode node5 = new GraphNode("5");

        node3.addConnection(node2);
        node3.addConnection(node1);

        node2.addConnection(node1);
        node2.addConnection(node3);

        node1.addConnection(node1);
        node1.addConnection(node2);
        node1.addConnection(node3);
        node1.addConnection(node4);

        node4.addConnection(node1);
        node4.addConnection(node5);

        node5.addConnection(node1);
        node5.addConnection(node4);

        List<GraphNode> nodeList = new ArrayList<>();
        nodeList.add(node1);
        nodeList.add(node2);
        nodeList.add(node3);
        nodeList.add(node4);
        nodeList.add(node5);

        Graph graph = new Graph(nodeList);

        //when
        List<GraphNode> graphNodes = graph.getGraph();

        //then
        assertTrue(graphNodes.contains(node1));
        assertTrue(graphNodes.contains(node2));
        assertTrue(graphNodes.contains(node3));
        assertTrue(graphNodes.contains(node4));
        assertTrue(graphNodes.contains(node5));
        assertTrue(graphNodes.get(graphNodes.indexOf(node1)).isPof());
    }
}