package cisco.java.challenge;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static cisco.java.challenge.GNodeUtils.paths;
import static cisco.java.challenge.GNodeUtils.walkGraph;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class GNodeUtilsTest {

    @Test
    void testWalkGraph() {
        final GNode SINGLE = new GNodeImpl("A");
        assertThat(walkGraph(SINGLE),
                contains(new GNodeImpl("A")));

        final GNode A = new GNodeImpl("A",
                new GNodeImpl("B", new GNodeImpl("E"), new GNodeImpl("F", new GNodeImpl("A"))),
                new GNodeImpl("D"),
                new GNodeImpl("A"));
        assertThat(walkGraph(A),
                containsInAnyOrder(new GNodeImpl("A"), new GNodeImpl("B"), new GNodeImpl("D"), new GNodeImpl("E"), new GNodeImpl("F")));
    }

    @Test
    void testSinglePaths() {
        final GNode SINGLE = new GNodeImpl("A");

        final List<List<GNode>> paths = paths(SINGLE);
        assertThat(paths, hasSize(equalTo(1)));

        List<GNode> path1 = Arrays.asList(new GNode[] {new GNodeImpl("A")});
        assertEquals(paths.get(0), path1);
    }

    @Test
    void testSingleCyclicPaths() {
        final GNode SINGLE = new GNodeImpl("A", new GNodeImpl("A"));

        final List<List<GNode>> paths = paths(SINGLE);
        assertThat(paths, hasSize(equalTo(1)));

        List<GNode> path1 = Arrays.asList(new GNode[] {new GNodeImpl("A")});
        assertEquals(paths.get(0), path1);
    }

    @Test
    void testPaths() {
        final GNode A = new GNodeImpl("A",
                new GNodeImpl("B", new GNodeImpl("E"), new GNodeImpl("F")),
                new GNodeImpl("C", new GNodeImpl("G"), new GNodeImpl("H"), new GNodeImpl("I")),
                new GNodeImpl("D"));

        final List<List<GNode>> paths = paths(A);
        assertThat(paths, hasSize(equalTo(6)));

        List<GNode> path1 = Arrays.asList(new GNode[] {new GNodeImpl("A"), new GNodeImpl("B"), new GNodeImpl("E")});
        assertEquals(paths.get(0), path1);

        List<GNode> path2 = Arrays.asList(new GNode[] {new GNodeImpl("A"), new GNodeImpl("B"), new GNodeImpl("F")});
        assertEquals(paths.get(1), path2);

        List<GNode> path3 = Arrays.asList(new GNode[] {new GNodeImpl("A"), new GNodeImpl("C"), new GNodeImpl("G")});
        assertEquals(paths.get(2), path3);

        List<GNode> path4 = Arrays.asList(new GNode[] {new GNodeImpl("A"), new GNodeImpl("C"), new GNodeImpl("H")});
        assertEquals(paths.get(3), path4);

        List<GNode> path5 = Arrays.asList(new GNode[] {new GNodeImpl("A"), new GNodeImpl("C"), new GNodeImpl("I")});
        assertEquals(paths.get(4), path5);

        List<GNode> path6 = Arrays.asList(new GNode[] {new GNodeImpl("A"), new GNodeImpl("D")});
        assertEquals(paths.get(5), path6);
    }

    @Test
    void testCyclicPaths() {
        final GNode A = new GNodeImpl("A",
                new GNodeImpl("B", new GNodeImpl("E"), new GNodeImpl("F", new GNodeImpl("A"))),
                new GNodeImpl("D"),
                new GNodeImpl("A"));

        final List<List<GNode>> paths = paths(A);
        assertThat(paths, hasSize(equalTo(4)));

        List<GNode> path1 = Arrays.asList(new GNode[] {new GNodeImpl("A"), new GNodeImpl("B"), new GNodeImpl("E")});
        assertEquals(paths.get(0), path1);

        List<GNode> path2 = Arrays.asList(new GNode[] {new GNodeImpl("A"), new GNodeImpl("B"), new GNodeImpl("F")});
        assertEquals(paths.get(1), path2);

        List<GNode> path3 = Arrays.asList(new GNode[] {new GNodeImpl("A"), new GNodeImpl("D")});
        assertEquals(paths.get(2), path3);

        List<GNode> path4 = Arrays.asList(new GNode[] {new GNodeImpl("A")});
        assertEquals(paths.get(3), path4);
    }
}