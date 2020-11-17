import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Graph {
    private final List<GraphNode> nodes;
    private final List<GraphNode> pointsOfFailure = new ArrayList<>();
    private final HashMap<GraphNode, Integer> subnetHashmap = new HashMap<>();
    private final HashMap<String, Boolean> visitedNodesMap = new HashMap<>();
    private int time = 0;

    Graph(List<GraphNode> nodes) {
        this.nodes = nodes;
        findSPF();
    }

    public List<GraphNode> getGraph() {
        return nodes;
    }

    public List<GraphNode> getSPF() {
        return pointsOfFailure;
    }

    public HashMap<GraphNode, Integer> getSubnets() {
        visitedNodesMap.clear();
        for (GraphNode node : pointsOfFailure) {
            List<GraphNode> deepCopyNodes = deepCopyNodes();
            deepCopyNodes.remove(node);
            int numSubnets = countSubnets(deepCopyNodes, node);
            subnetHashmap.put(node, numSubnets);
        }
        return subnetHashmap;
    }

    private List<GraphNode> deepCopyNodes() {
        List<GraphNode> deepCopyNodes = new ArrayList<>();
        for (GraphNode node : nodes) {
            List<GraphNode> connections = new ArrayList<>(node.getConnections());
            GraphNode newNode = new GraphNode(node.getName(), connections);
            deepCopyNodes.add(newNode);
            visitedNodesMap.put(newNode.getName(), false);
        }
        return deepCopyNodes;
    }

    private int countSubnets(List<GraphNode> nodeList, GraphNode spf)
    {
        int numSubnets = 0;
        for (GraphNode node : nodeList) {
            if (!visitedNodesMap.get(node.getName())) {
                depthFirstSearch(node, spf);
                numSubnets++;
            }
        }
        return numSubnets;
    }

   private void depthFirstSearch(GraphNode node, GraphNode spf)
    {
        node.setVisited(true);
        visitedNodesMap.replace(node.getName(), false, true);
        for (GraphNode connection : node.getConnections()) {
            if (!visitedNodesMap.get(connection.getName()) && !connection.equals(spf))
                depthFirstSearch(connection, spf);
        }
    }

    private void  findSPF()  {
        // Call the recursive helper function to find articulation
        // points in DFS tree rooted with vertex 'i'
        for (GraphNode node : nodes) {
            if (!node.isVisited()) {
                tarjanAlgorithm(node);
            }
        }
    }

    private void tarjanAlgorithm(GraphNode node)
    {
        int children = 0;
        node.setVisited(true);
        time++;
        node.setDiscovered(time);
        node.setLow(time);

        for (GraphNode connection : node.getConnections())
        {
            if (!connection.isVisited())
            {
                children++;
                connection.setParent(node);
                tarjanAlgorithm(connection);
                setNodeSPFMaybe(node, connection, children);
                node.setLow(Math.min(node.getLow(), connection.getLow()));
            }
            else if (connection != node.getParent()) {
                node.setLow(Math.min(node.getLow(), connection.getDiscovered()));
            }
        }
    }

    private void setNodeSPFMaybe(GraphNode node, GraphNode connection, int children) {
        // (1) node is root of DFS tree and has two or more children.
        if (node.getParent() == null && children > 1) {
            node.setPof(true);
            pointsOfFailure.add(node);
        }

        // (2) If node is not root and low value of one of its child
        // is more than node's discovery value.
        if (node.getParent() != null && connection.getLow() >= node.getDiscovered()) {
            node.setPof(true);
            pointsOfFailure.add(node);
        }
    }
}
