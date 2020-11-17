import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GraphNode {
    private String name;
    private List<GraphNode> connections = new ArrayList<>();
    private boolean pof; //point of failure
    private boolean visited;
    private int discovered;
    private int low;
    private GraphNode parent;
    private int x;
    private int y;

    GraphNode (String name) {
        this.name = name;
    }

    GraphNode (String name, List<GraphNode> connections) {
        this.name = name;
        this.connections = connections;
    }

    public String getName(GraphNode node){ return name;}

    public void addConnection(GraphNode node) {
        this.connections.add(node);
    }

    public List<GraphNode> getConnections() {
        return connections;
    }

    public GraphNode getParent() {
        return parent;
    }

    public void setParent(GraphNode parent) {
        this.parent = parent;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isPof() {
        return pof;
    }

    public void setPof(boolean pof) {  this.pof = pof; }

    public int getDiscovered() {
        return discovered;
    }

    public void setDiscovered(int discovered) {
        this.discovered = discovered;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x){this.x = x;}

    public void setY(int y){this.y = y;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphNode node = (GraphNode) o;
        return Objects.equals(name, node.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
