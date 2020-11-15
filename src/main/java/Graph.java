import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Graph {
    private final List<GraphNode> nodes;
    private final List<GraphNode> pointsOfFailure = new ArrayList<>();
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
        for (GraphNode node : pointsOfFailure) {
            List<GraphNode> deepCopyNodes = deepCopyNodes();
            deepCopyNodes.remove(node);
           // removeNodeFromList(node, deepCopyNodes);
            int numSubnets = countSubnets(deepCopyNodes, node);
            subnetHashmap.put(node, numSubnets);
        }
        return subnetHashmap;
    }

    private List<GraphNode> deepCopyNodes() {
        List<GraphNode> deepCopyNodes = new ArrayList<>();
        for (GraphNode node : nodes) {
            List<GraphNode> connections = new ArrayList<>(node.getConnections());
            for (GraphNode connection : connections) {
                connection.setVisited(false);
            }
            deepCopyNodes.add(new GraphNode(node.getName(), connections));
        }
        return deepCopyNodes;
    }

    private int countSubnets(List<GraphNode> nodeList, GraphNode spf)
    {
        int numSubnets = 0;
        for (GraphNode node : nodeList) {
            if (!node.isVisited()) {
                depthFirstSearch(node, spf);
                numSubnets++;
            }
        }
        return numSubnets;
    }

   private void depthFirstSearch(GraphNode node, GraphNode spf)
    {
        node.setVisited(true);
        for (GraphNode connection : node.getConnections()) {
            boolean visited = !connection.isVisited();
            boolean isSPF = !connection.equals(spf);
            if (!connection.isVisited() && !connection.equals(spf))
                depthFirstSearch(connection, spf);
        }
    }

    private void  findSPF()  {
        // Call the recursive helper function to find articulation
        // points in DFS tree rooted with vertex 'i'
        for (GraphNode node : nodes) {
            if (!node.isVisited()) {
                SPFUtil(node);
            }
        }
    }

    private void SPFUtil(GraphNode node)
    {
        // Count of children in DFS Tree
        int children = 0;

        // Mark the current node as visited
        node.setVisited(true);

        // Initialize discovery time and low value
        time++;
        node.setDiscovered(time);
        node.setLow(time);

        // Go through all vertices adjacent to this
        for (GraphNode connection : node.getConnections())
        {

            // If connection (v) is not visited yet, then make it a child of currentNode (u)
            // in DFS tree and recur for it
            if (!connection.isVisited())
            {
                children++;
                connection.setParent(node);
                SPFUtil(connection);

                // Check if the subtree rooted with v has a connection to
                // one of the ancestors of u
                node.setLow(Math.min(node.getLow(), connection.getLow()));

                // u is an articulation point in following cases

                // (1) u is root of DFS tree and has two or more children.
                if (node.getParent() == null && children > 1) {
                    node.setPof(true);
                    pointsOfFailure.add(node);
                }
                // (2) If u is not root and low value of one of its child
                // is more than or equal to discovery value of u.
                if (node.getParent() != null && connection.getLow() >= node.getDiscovered()) {
                    node.setPof(true);
                    pointsOfFailure.add(node);
                }
            }

            // Update low value of u for parent function calls.
            else if (connection != node.getParent()) {
                node.setLow(Math.min(node.getLow(), connection.getDiscovered()));
            }
        }
    }
}
