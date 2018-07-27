package cisco.java.challenge;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class GNodeImplTest {

    @Test
    void testGetName() {
        final GNode gNode = new GNodeImpl("A");
        assertEquals("A", gNode.getName());
        final GNode gNodeEmptyName = new GNodeImpl("");
        assertEquals("", gNodeEmptyName.getName());
    }

    @Test
    void testGetChildren() {
        final GNode gNodeNonChildren = new GNodeImpl("A");
        assertTrue(Arrays.equals(gNodeNonChildren.getChildren(), new String[] {}));

        final GNode gNodeOneChildren = new GNodeImpl("A", new GNodeImpl("B"));
        assertTrue(Arrays.equals(gNodeOneChildren.getChildren(), new GNodeImpl[] {new GNodeImpl("B")}));

        final GNode gNodeMultipleChildren = new GNodeImpl("A", new GNodeImpl("B"), new GNodeImpl("C"));
        assertTrue(Arrays.equals(gNodeMultipleChildren.getChildren(), new GNodeImpl[] {new GNodeImpl("B"), new GNodeImpl("C")}));
    }

    @Test
    void testEquals() {
        final GNode gNodeA1 = new GNodeImpl("A");
        final GNode gNodeA2 = new GNodeImpl("A", new GNodeImpl("B"));
        final GNode gNodeB = new GNodeImpl("B");

        assertTrue(gNodeA1.equals(gNodeA1));
        assertTrue(gNodeA1.equals(gNodeA2));

        assertFalse(gNodeA1.equals(gNodeB));
        assertFalse(gNodeB.equals(gNodeA1));
        assertFalse(gNodeB.equals(null));
    }
}