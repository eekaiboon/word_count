import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.BeforeClass;
import org.junit.Test;


public class ContentTokenizerTest {
    
    private static ContentTokenizer contentTokenizer;
    
    @BeforeClass
    public static void setUp() {
        contentTokenizer = new SimpleContentTokenizer();
    }
    
    @Test
    public void testTokenize() {
        
        assertEquals(Arrays.asList("Sentence", "one", "Sentence", "two"), 
                contentTokenizer.tokenize("Sentence one. Sentence two."));
        
        assertEquals(Arrays.asList("U", "S", "A"), 
                contentTokenizer.tokenize("U.S.A."));
        
        assertEquals(Arrays.asList("Brad", "s", "car", "Tom", "s", "bicyle"), 
                contentTokenizer.tokenize("Brad's car. Tom's bicyle."));
    }
    
    @Test
    public void testReturnEmptyList() {
        
        assertEquals(Collections.EMPTY_LIST, 
                contentTokenizer.tokenize(""));

        assertEquals(Collections.EMPTY_LIST, 
                contentTokenizer.tokenize("     "));
        
        assertEquals(Collections.EMPTY_LIST, 
                contentTokenizer.tokenize("!@#$%^&*()"));
        
        assertEquals(Collections.EMPTY_LIST, 
                contentTokenizer.tokenize("12 3 4 5 6 7"));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testTokenizeNull() {
        contentTokenizer.tokenize(null);
    }
}
