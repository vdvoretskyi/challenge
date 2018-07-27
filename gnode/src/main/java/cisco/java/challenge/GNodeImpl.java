package cisco.java.challenge;

import java.util.*;

public class GNodeImpl implements GNode {

    private final String name;
    private final List<GNode> children = new ArrayList<>();

    public GNodeImpl(final String name) {
        this(name, Collections.EMPTY_LIST);
    }

    public GNodeImpl(final String name, final GNode... nodes) {
        this(name, Arrays.asList(nodes));
    }

    public GNodeImpl(final String name, final List<GNode> children) {
        this.name = name;
        this.children.addAll(children);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public GNode[] getChildren() {
        return children.toArray(new GNode[children.size()]);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof GNodeImpl)) return false;
        final GNodeImpl gNode = (GNodeImpl) o;
        return Objects.equals(name, gNode.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
