package searchtree;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by trutgers on 4/1/16.
 */
public class SearchTreeTest {

    @Test
    public void testTree() {
        Searchable s = TreeFactory.getTree();

        assertEquals("foo", s.search(4));
        assertEquals("bat", s.search(14));
        assertEquals("baz", s.search(2000));
    }
}