import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static com.google.common.truth.Truth.*;

public class TestGraph {

    @Test
    public void testPath1() {
        Graph g = new Graph(2);
        assertWithMessage("Expected pathExists()==false between 2 vertices, no edges.").that(g.pathExists(0, 1)).isFalse();
        assertWithMessage("Expected pathExists()==false between 2 vertices, no edges.").that(g.pathExists(1, 0)).isFalse();

        List<Integer> path1 = g.path(0, 1);
        List<Integer> path2 = g.path(1, 0);

        assertWithMessage("Expected size 0 path between 2 vertices, no edges.").that(path1.isEmpty());
        assertWithMessage("Expected size 0 path between 2 vertices, no edges.").that(path2.isEmpty());
    }

    // test graph
    private Graph g;

    // vertex number for G1, G2, G4
    private static final int N1 = 5;
    // vertex number for G3
    private static final int N3 = 7;

    // ----------- helper methods -----------
    private void generateG1() {
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 4);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(4, 3);
    }
    private void generateG2() {
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 4);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(4, 3);
    }
    private void generateG3() {
        g.addUndirectedEdge(0, 2);
        g.addUndirectedEdge(0, 3);
        g.addUndirectedEdge(1, 4);
        g.addUndirectedEdge(1, 5);
        g.addUndirectedEdge(2, 3);
        g.addUndirectedEdge(2, 6);
        g.addUndirectedEdge(4, 5);
    }
    private void generateG4() {
        g.addUndirectedEdge(0, 1);
        g.addUndirectedEdge(0, 2);
        g.addUndirectedEdge(1, 4);
    }

    /* ---------- basic init ---------- */

    @BeforeEach
    void init() {
        g = new Graph(N1);          // default 5 vertices
    }

    /* ---------- addEdge / isAdjacent / Undirected ---------- */

    @Test
    @DisplayName("Directed edge is one-way only")
    public void directedEdge_isOneWay() {
        g.addEdge(0, 1);
        assertThat(g.isAdjacent(0, 1)).isTrue();
        assertThat(g.isAdjacent(1, 0)).isFalse();
    }

    @Test
    @DisplayName("addEdge(v1,v2,weight) replaces existing edge")
    public void addEdge_weight_replaces() {
        g.addEdge(0, 1, 5);
        g.addEdge(0, 1, 10);       // override
        assertThat(g.isAdjacent(0, 1)).isTrue();
    }

    @Test
    @DisplayName("Undirected edge appears in both directions")
    public void undirectedEdge_isTwoWay() {
        g.addUndirectedEdge(2, 4);
        assertThat(g.isAdjacent(2, 4)).isTrue();
        assertThat(g.isAdjacent(4, 2)).isTrue();
    }

    /* ----------------- neighbors ----------------- */

    @Test
    public void neighbors_correctForVertex0_G1() {
        generateG1();
        assertThat(g.neighbors(0)).containsExactly(1, 2, 4).inOrder();
    }

    @Test
    public void neighbors_emptyWhenSinkVertex() {
        generateG1();
        assertThat(g.neighbors(3)).isEmpty();
    }

    /* ----------------- inDegree ----------------- */

    @Test
    public void inDegree_countsCorrectly_G1() {
        generateG1();
        assertThat(g.inDegree(0)).isEqualTo(1);  // from 2
        assertThat(g.inDegree(2)).isEqualTo(2);  // from 0,1
        assertThat(g.inDegree(3)).isEqualTo(2);  // from 2,4
    }

    @Test
    public void inDegree_zeroForIsolatedVertex() {
        Graph fresh = new Graph(3);
        assertThat(fresh.inDegree(2)).isEqualTo(0);
    }

    /* ----------------- pathExists ----------------- */

    @Test
    public void pathExists_trivialStartEqualsStop() {
        Graph single = new Graph(1);
        assertThat(single.pathExists(0, 0)).isTrue();
    }

    @Test
    public void pathExists_detectsExistingAndMissing_G1() {
        generateG1();
        assertThat(g.pathExists(0, 3)).isTrue();   // 0→2→3
        assertThat(g.pathExists(3, 0)).isFalse();
    }

    @Test
    public void pathExists_workInDAG_G2() {
        generateG2();
        assertThat(g.pathExists(0, 3)).isTrue();
        assertThat(g.pathExists(3, 0)).isFalse();
    }

    /* ----------------- path ----------------- */

    @Test
    public void path_trivialSingleVertex() {
        List<Integer> p = g.path(2, 2);
        assertThat(p).containsExactly(2).inOrder();
    }

    @Test
    public void path_emptyWhenNoPath() {
        generateG2();                // DAG 无 3→0
        assertThat(g.path(3, 0)).isEmpty();
    }

    @Test
    public void path_testTree() {
        generateG4();
        assertThat(g.path(0, 3)).isEmpty();
        assertThat(g.path(0, 4)).containsExactly(0,1,4).inOrder();
    }

    @Test
    public void path_returnsValidSequence_G1() {
        generateG1();                // 0 → 2 → 3
        List<Integer> p = g.path(0, 3);

        assertThat(p.get(0)).isEqualTo(0);
        assertThat(p.get(p.size() - 1)).isEqualTo(3);
        // adjacent vertices in path must be adjacent
        for (int i = 0; i < p.size() - 1; i++) {
            assertWithMessage(p.get(i) + "→" + p.get(i + 1) + " should be adjacent")
                    .that(g.isAdjacent(p.get(i), p.get(i + 1)))
                    .isTrue();
        }
    }

    /* ----------------- Undirected G3 ----------------- */

    @Test
    public void neighbors_symmetry_G3() {
        g = new Graph(N3);
        generateG3();
        assertThat(g.neighbors(0)).contains(2);
        assertThat(g.neighbors(2)).contains(0);
        assertThat(g.isAdjacent(0, 2)).isTrue();
        assertThat(g.isAdjacent(2, 0)).isTrue();
    }

    @Test
    public void pathExists_components_G3() {
        g = new Graph(N3);
        generateG3();
        assertThat(g.pathExists(0, 6)).isTrue();   // 0-2-6
        assertThat(g.pathExists(0, 4)).isFalse();  // Not connected
    }

    /* ------------------------------------------------------------------
     * Helper: assert that vertex u appears strictly before vertex v
     * ------------------------------------------------------------------ */
    private static void assertPrecedes(List<Integer> order, int u, int v) {
        assertThat(order.indexOf(u))
                .isLessThan(order.indexOf(v));
    }

    /* ------------------------------------------------------------------
     * Empty graph
     * ------------------------------------------------------------------ */
    @Test
    public void topoSort_emptyGraph_returnsEmptyList() {
        Graph g = new Graph(0);
        assertThat(g.topologicalSort()).isEmpty();
    }

    /* ------------------------------------------------------------------
     * No edges: any permutation is OK
     * ------------------------------------------------------------------ */
    @Test
    public void topoSort_isolatedVertices_containsAllVertices() {
        Graph g = new Graph(5);           // vertices 0–4, no edges
        List<Integer> order = g.topologicalSort();

        // Same elements, order irrelevant
        assertThat(order).containsExactly(0, 1, 2, 3, 4);
    }

    /* ------------------------------------------------------------------
     * Linear chain: 0 → 1 → 2 → 3 → 4 (unique ordering)
     * ------------------------------------------------------------------ */
    @Test
    public void topoSort_linearChain_uniqueOrder() {
        Graph g = new Graph(5);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);

        List<Integer> order = g.topologicalSort();
        assertThat(order).containsExactly(0, 1, 2, 3, 4).inOrder();
    }

    /* ------------------------------------------------------------------
     * Branching DAG: 0 → {1,2}, {1,2} → 3 (multiple valid answers)
     * ------------------------------------------------------------------ */
    @Test
    public void topoSort_branchingDAG_propertyChecks() {
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);

        List<Integer> order = g.topologicalSort();

        // Must contain every vertex exactly once
        assertThat(order).containsExactly(0, 1, 2, 3);

        // Each edge constraint must hold
        assertPrecedes(order, 0, 1);
        assertPrecedes(order, 0, 2);
        assertPrecedes(order, 1, 3);
        assertPrecedes(order, 2, 3);
    }

    /* ------------------------------------------------------------------
     * More complex DAG with multiple sources and sinks
     * ------------------------------------------------------------------ */
    @Test
    public void topoSort_complexDAG_propertyChecks() {
        Graph g = new Graph(6);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 4);
        g.addEdge(4, 5);

        List<Integer> order = g.topologicalSort();

        // 1) Contains every vertex
        assertThat(order).containsExactly(0, 1, 2, 3, 4, 5);

        // 2) Edge ordering constraints
        assertPrecedes(order, 0, 2);
        assertPrecedes(order, 1, 2);
        assertPrecedes(order, 1, 3);
        assertPrecedes(order, 2, 4);
        assertPrecedes(order, 3, 4);
        assertPrecedes(order, 4, 5);
    }
}
