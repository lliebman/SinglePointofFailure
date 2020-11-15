import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {
    private final List<GraphNode> nodes;
    private final List<GraphNode> pointsOfFailure = new ArrayList<>();
    private int numSubnets = 0;
    private final HashMap<GraphNode, Integer> subnetHashmap = new HashMap<>();
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

        return subnetHashmap;
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
            // If connection is not visited yet, then make it a child of currentNode
            // in DFS tree and recur for it
            if (!connection.isVisited())
            {
                children++;
                connection.setParent(node);
                tarjanAlgorithm(connection);
                setNodeSPFMaybe(node, connection, children);

                // Check if the subtree rooted with v has a connection to
                // one of the ancestors of u
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
