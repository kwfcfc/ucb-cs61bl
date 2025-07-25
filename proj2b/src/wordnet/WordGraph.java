package wordnet;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashSet;
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
    private ArrayList<WordNode> nodes;

    public WordGraph(String hyponymsFile, String synsetsFile) {
        In synsetsIn = new In(synsetsFile);
        In hyponymsIn = new In(hyponymsFile);
        nodes = synsetsFileHelper(synsetsIn);
        hyponymsFileHelper(hyponymsIn, nodes);
    }

    public Set<String> searchHyponyms(String word) {
        Set<String> hyponyms = new HashSet<>();
        for (WordNode node : nodes) {
            if (node.isSynonym(word)) {
                hyponyms.addAll(node.getHyponyms());
            }
        }
        return hyponyms;
    }

    private static ArrayList<WordNode> synsetsFileHelper(In readIn) {
        ArrayList<WordNode> nodes = new ArrayList<>();
        while (readIn.hasNextLine()) {
            String[] line = readIn.readLine().split(",");
            nodes.add(new WordNode(line[1]));
        }
        return nodes;
    }

    private static void hyponymsFileHelper(In readIn, ArrayList<WordNode> nodes) {
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
