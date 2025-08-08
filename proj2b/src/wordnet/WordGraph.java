package wordnet;

import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * An object that provides utility methods for making queries on the
 * WordNet dataset (or a subset thereof).

 * A WordGraph stores pertinent data from a "synsets file" and a "hyponyms
 * file". It is not a graph in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Pusen Yi
 */
public class WordGraph {
    private Map<Integer, WordNode> nodes;

    public WordGraph(String hyponymsFile, String synsetsFile) {
        In synsetsIn = new In(synsetsFile);
        In hyponymsIn = new In(hyponymsFile);
        nodes = new HashMap<>();
        synsetsFileHelper(synsetsIn, nodes);
        hyponymsFileHelper(hyponymsIn, nodes);
    }

    public Set<String> searchHyponyms(String word) {
        Set<String> hyponyms = new HashSet<>();
        for (WordNode node : nodes.values()) {
            if (node.isSynonym(word)) {
                hyponyms.addAll(node.getHyponyms());
            }
        }
        return hyponyms;
    }

    private static void synsetsFileHelper(In readIn, Map<Integer, WordNode> nodes) {
        while (readIn.hasNextLine()) {
            String[] line = readIn.readLine().split(",");
            int id = Integer.parseInt(line[0]);
            nodes.putIfAbsent(id, new WordNode(line[1])); // only insert new node
        }
    }

    private static void hyponymsFileHelper(In readIn, Map<Integer, WordNode> nodes) {
        while (readIn.hasNextLine()) {
            String[] line = readIn.readLine().split(",");
            int parent = Integer.parseInt(line[0]);
            for (int i = 1; i < line.length; i++) {
                int child = Integer.parseInt(line[i]);
                nodes.get(parent).addChildren(nodes.get(child));
            }
        }
    }
}
