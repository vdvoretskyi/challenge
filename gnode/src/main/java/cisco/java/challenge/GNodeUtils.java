package cisco.java.challenge;

import java.util.*;

public final class GNodeUtils {

    private GNodeUtils() {
    }

    public static List<GNode> walkGraph(GNode node) {
        Objects.requireNonNull(node, "node can not be null");

        final Set<GNode> nodes = new HashSet<>();

        final Queue<GNode> nodesToVisit = new LinkedList<>();
        nodesToVisit.add(node);
        while(!nodesToVisit.isEmpty()) {
            final GNode visitedNode = nodesToVisit.remove();

            if (!nodes.contains(visitedNode)) {
                for (GNode child : visitedNode.getChildren()) {
                    nodesToVisit.add(child);
                }
            }

            nodes.add(visitedNode);
        }

        return new ArrayList<>(nodes);
    }

    public static List<List<GNode>> paths(final GNode node) {
        Objects.requireNonNull(node, "node can not be null");

        final List<List<GNode>> listOfPaths = new ArrayList<>();
        pathsRecursive(node, listOfPaths, new LinkedHashSet<>());
        return listOfPaths;
    }

    private static void pathsRecursive(final GNode node, final List<List<GNode>> paths, final Set<GNode> path) {
        path.add(node);
        if (node.getChildren().length != 0) {
            for (GNode child : node.getChildren()) {
                if (path.contains(child)) {
                    paths.add(new ArrayList<>(path));
                } else {
                    pathsRecursive(child, paths, new LinkedHashSet<>(path));
                }
            }
        } else {
            paths.add(new ArrayList<>(path));
        }
    }

}
