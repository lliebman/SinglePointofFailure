import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph {
    private LinkedList<GraphNode> nodes;
    private List<GraphNode> pointsOfFailure = new ArrayList<>();
    private int time = 0;

    Graph(){ }

    Graph(LinkedList<GraphNode> nodes) {
        this.nodes = nodes;
    }

    public List<GraphNode> findSPF()  {
        // Mark all the vertices as not visited

        // Call the recursive helper function to find articulation
        // points in DFS tree rooted with vertex 'i'
        for (GraphNode node : nodes) {
            if (!node.isVisited()) {
                SPFUtil(node);
            }
        }

        // Now ap[] contains articulation points, print them
        return pointsOfFailure;
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

                // (1) u is root of DFS tree and has two or more chilren.
                if (node.getParent() == null && children > 1) {
                    pointsOfFailure.add(node);
                }

                // (2) If u is not root and low value of one of its child
                // is more than discovery value of u.
                if (node.getParent() != null && connection.getLow() >= node.getDiscovered()) {
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
