import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        List<GraphNode> list = new ArrayList<>();
        Graph graph = new Graph(list);
        NetworkView view = new NetworkView(graph);
        NetworkFrame frame = new NetworkFrame(view);
        frame.setVisible(true);
    }

}