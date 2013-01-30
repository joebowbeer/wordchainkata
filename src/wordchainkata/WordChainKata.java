package wordchainkata;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import static java.util.stream.Collectors.toList;

public class WordChainKata {

    public static void main(String[] args) throws IOException {
        assert args.length == 3; // path-to-words start goal
        Path path = FileSystems.getDefault().getPath(args[0]);
        String start = args[1];
        String goal = args[2];
        int length = start.length();
        assert goal.length() == length;
        final Collection<String> words;
        try (BufferedReader r = Files.newBufferedReader(path, Charset.defaultCharset())) {
            words = r.lines().parallel().filter(s -> s.length() == length).collect(toList());
        }
        System.out.println(new WordChainKata(words, start, goal).solve());
    }

    private final Set<String> words;
    private final String start;
    private final String goal;

    public WordChainKata(Collection<String> words, String start, String goal) {
        this.words = new HashSet<>(words);
        this.start = start;
        this.goal = goal;
    }

    public List<String> solve() {
        if (start.equals(goal)) {
            return Arrays.asList(start);
        }
        Queue<Node> queue = new ArrayDeque<>();
        words.remove(start);
        queue.add(new Node(start, null));
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            String word = node.word;
            for (String neighbor : findNeighbors(word)) {
                words.remove(neighbor);
                Node next = new Node(neighbor, node);
                if (neighbor.equals(goal)) {
                    return next.toList();
                }
                queue.add(next);
            }
        }
        return Collections.emptyList();
    }

    protected List<String> findNeighbors(String word) {
        return words.parallelStream().filter(s -> adjacent(word, s)).collect(toList());
    }

    protected static boolean adjacent(String a, String b) {
        int diffs = 0;
        for (int i = 0, n = a.length(); i < n && diffs <= 1; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diffs++;
            }
        }
        return diffs == 1;
    }

    protected static class Node {
        public final String word;
        public final Node prev;
        public Node(String word, Node prev) {
            this.word = word;
            this.prev = prev;
        }
        public List<String> toList() {
            List<String> list = new ArrayList<>();
            for (Node next = this; next != null; next = next.prev) {
                list.add(0, next.word);
            }
            return list;
        }
    }
}
