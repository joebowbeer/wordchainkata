package wordchainkata;

import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class WordChainKataTest {
    
    public WordChainKataTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of {@link WordChainKata#solve()}.
     */
    @Test
    public void testSolve() {
        System.out.println("solve");
        List<String> words = Arrays.asList("add", "aid", "cat", "cot", "did", "dog", "dot", "eel");
        assertEquals(Arrays.asList("cat", "cot", "dot", "dog"),
                new WordChainKata(words, "cat", "dog").solve());
        assertEquals(Arrays.asList("did", "aid", "add"),
                new WordChainKata(words, "did", "add").solve());
        assertEquals(Arrays.asList(), new WordChainKata(words, "did", "dog").solve());
        assertEquals(Arrays.asList(), new WordChainKata(words, "add", "eel").solve());
    }

    /**
     * Test of {@link WordChainKata#adjacent(java.lang.String, java.lang.String) adjacent} method.
     */
    @Test
    public void testAdjacent() {
        System.out.println("adjacent");
        assertFalse(WordChainKata.adjacent("", ""));
        assertFalse(WordChainKata.adjacent("a", "a"));
        assertTrue(WordChainKata.adjacent("a", "b"));
        assertTrue(WordChainKata.adjacent("abc", "adc"));
        assertFalse(WordChainKata.adjacent("abc", "cab"));
    }
}
