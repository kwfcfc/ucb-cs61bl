package wordnet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * An object that provides utility methods for making queries on the
 * WordNet dataset (or a subset thereof).

 * A WordNote stores pertinent data from a "synsets file". It stores
 * a key as node value and a list of Node as children.
 *
 * @author Pusen Yi
 */
public class WordNode {
    private List<String> nodeWords;
    private List<WordNode> children;

    public WordNode() {
        nodeWords = new ArrayList<>();
        children = new ArrayList<>();
    }

    public WordNode(String word) {
        nodeWords = List.of(word.split(" ")); // split the words by space.
        children = new ArrayList<>();
    }

    public boolean isSynonym(String word) {
        return nodeWords.contains(word);
    }

    public Set<String> getHyponyms() {
        Set<String> hyponyms = new HashSet<>(nodeWords);
        for (WordNode node : children) {
            // a recursive and dfs traversal, it assumes there will be no loops
            hyponyms.addAll(node.getHyponyms());
        }
        return hyponyms;
    }

    public void addChildren(WordNode child) {
        children.add(child);
    }
}
