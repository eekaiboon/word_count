import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;


public class SolutionTest {
    
    @Test
    public void test() {
        
        assertEquals(Arrays.asList("a", "b", "c"),
                Solution.getTopNWords("a a a a b b b c c d e f", 3));

        assertTrue(containsInExcepted(Arrays.asList("a", "b", "c"),
                Solution.getTopNWords("a a a a b b b b c c c c", 3), 3));
        
        assertTrue(containsInExcepted(Arrays.asList("a", "b", "c"),
                Solution.getTopNWords("a a a a b b b b c c c c", 2), 2));
        
        assertEquals(Arrays.asList("a", "b", "c", "d", "e"),
                Solution.getTopNWords("a a a a b b b c c d e f", 100));
        
        assertEquals(Arrays.asList("a"),
                Solution.getTopNWords("a a a a a a a a a a a a", 100));
    }
    
    @Test
    public void testEmptyResult() {

        assertEquals(Collections.EMPTY_LIST, 
                Solution.getTopNWords("a a a a b b b c c d e f", 0));
        
        assertEquals(Collections.EMPTY_LIST, 
                Solution.getTopNWords("", 3));


        assertEquals(Collections.EMPTY_LIST, 
                Solution.getTopNWords("     ", 3));
        
        assertEquals(Collections.EMPTY_LIST, 
                Solution.getTopNWords("!@# #$% ^&* ()", 3));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testNullContent() {
        Solution.getTopNWords(null, 3);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testInvalidN() {
        Solution.getTopNWords("a a a a b b b c c d e f", -1);
    }

    /**
     * Elements of actual should contains in expected but their appearance
     * could be in any order. Also, number of elements of actual should be
     * equal to numOfMatches.
     * 
     * @param expected
     * @param actual
     * @param numOfMatches
     * @return
     */
    private boolean containsInExcepted(List<String> expected, 
            List<String> actual, int numOfMatches) {
        
        Set<String> expectedSet = new HashSet<>(expected);
        
        if (actual.size() < numOfMatches) {
            return false;
        }
        
        for (String a : actual) {
            if (!expectedSet.contains(a)) {
                return false;
            }
        }
        
        return true;
    }
}
