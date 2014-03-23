package wordchainkata;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WordChainKataTest {

    /**
     * Test of {@link WordChainKata#solve()}.
     */
    @Test
    public void testSolve() {
        List<String> words = Arrays.asList("add", "aid", "cat", "cot", "did", "dog", "dot", "eel");
        assertEquals(Arrays.asList("cat", "cot", "dot", "dog"),
                new WordChainKata(words, "cat", "dog").solve());
        assertEquals(Arrays.asList("did", "aid", "add"),
                new WordChainKata(words, "did", "add").solve());
        assertEquals(Arrays.asList(), new WordChainKata(words, "did", "dog").solve());
        assertEquals(Arrays.asList(), new WordChainKata(words, "add", "eel").solve());
    }

    /**
     * Test of {@link WordChainKata#adjacent(String, String) adjacent} method.
     */
    @Test
    public void testAdjacent() {
        assertFalse(WordChainKata.adjacent("", ""));
        assertFalse(WordChainKata.adjacent("a", "a"));
        assertTrue(WordChainKata.adjacent("a", "b"));
        assertTrue(WordChainKata.adjacent("abc", "adc"));
        assertFalse(WordChainKata.adjacent("abc", "cab"));
    }
}
