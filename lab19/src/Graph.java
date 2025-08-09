import java.util.*;
import java.util.stream.IntStream;

import org.checkerframework.checker.units.qual.s;

import edu.princeton.cs.algs4.In;

public class Graph {

    private LinkedList<Edge>[] adjLists;
    private int vertexCount;

    /* Initializes a graph with NUMVERTICES vertices and no Edges. */
    public Graph(int numVertices) {
        adjLists = (LinkedList<Edge>[]) new LinkedList[numVertices];
        for (int k = 0; k < numVertices; k++) {
            adjLists[k] = new LinkedList<Edge>();
        }
        vertexCount = numVertices;
    }

    /*
     * Adds a directed Edge (V1, V2) to the graph. That is, adds an edge
     * in ONE directions, from v1 to v2.
     */
    public void addEdge(int v1, int v2) {
        addEdge(v1, v2, 0);
    }

    /*
     * Adds an undirected Edge (V1, V2) to the graph. That is, adds an edge
     * in BOTH directions, from v1 to v2 and from v2 to v1.
     */
    public void addUndirectedEdge(int v1, int v2) {
        addUndirectedEdge(v1, v2, 0);
    }

    /*
     * Adds a directed Edge (V1, V2) to the graph with weight WEIGHT. If the
     * Edge already exists, replaces the current Edge with a new Edge with
     * weight WEIGHT.
     */
    public void addEdge(int v1, int v2, int weight) {
        LinkedList<Edge> fromList = adjLists[v1];
        Edge newEdge = new Edge(v1, v2, weight);
        int index = fromList.indexOf(newEdge);
        if (index < 0) {
            fromList.addFirst(newEdge);
        } else {
            fromList.set(index, newEdge);
        }
    }

    /*
     * Adds an undirected Edge (V1, V2) to the graph with weight WEIGHT. If the
     * Edge already exists, replaces the current Edge with a new Edge with
     * weight WEIGHT.
     */
    public void addUndirectedEdge(int v1, int v2, int weight) {
        addEdge(v1, v2, weight);
        addEdge(v2, v1, weight);
    }

    /*
     * Returns true if there exists an Edge from vertex FROM to vertex TO.
     * Returns false otherwise.
     */
    public boolean isAdjacent(int from, int to) {
        LinkedList<Edge> fromList = adjLists[from];
        for (Edge edge : fromList) {
            if (edge.to == to) {
                return true;
            }
        }
        return false;
    }

    /*
     * Returns a list of all the vertices u such that the Edge (V, u)
     * exists in the graph.
     */
    public List<Integer> neighbors(int v) {
        return adjLists[v].stream().map(Edge::to).sorted().toList();
    }

    /* Returns the number of incoming Edges for vertex V. */
    public int inDegree(int v) {
        return IntStream.range(0, adjLists.length)
                .map(i -> isAdjacent(i, v) ? 1 : 0)
                .sum();
    }

    /*
     * Returns a list of the vertices that lie on the shortest path
     * from start to stop.
     * If no such path exists, you should return an empty list. If START == STOP,
     * returns a List with START.
     */
    public ArrayList<Integer> shortestPath(int start, int stop) {
        int[] distance = new int[vertexCount];
        int[] predecessor = new int[vertexCount];
        boolean[] visited = new boolean[vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            distance[i] = Integer.MAX_VALUE;
            predecessor[i] = i;
            visited[i] = false;
        }

        distance[start] = 0;

        Comparator<Integer> vertexComparator = (a, b) -> {
            return Integer.compare(distance[a], distance[b]);
        };

        PriorityQueue<Integer> fringe = new PriorityQueue<>(vertexCount, vertexComparator);

        fringe.add(start);

        while (!fringe.isEmpty()) {
            int shortest = fringe.poll();
            if (shortest == stop) {
                break;
            } else {
                visited[shortest] = true;
            }

            // already set the initial neighbor distance to MAX_VALUE
            for (int neighbor : neighbors(shortest)) {
                Edge newEdge = getEdge(shortest, neighbor);
                if (newEdge.weight + distance[shortest] < distance[neighbor]) {
                    distance[neighbor] = newEdge.weight + distance[shortest];
                    predecessor[neighbor] = shortest;
                    visited[neighbor] = true;
                    fringe.add(neighbor);
                }
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        // not found or not connected.
        if (!visited[stop]) {
            return result;
        }

        result.add(stop);
        while (predecessor[stop] != start) {
            stop = predecessor[stop];
            result.add(0, stop);
        }

        result.add(0, start);
        return result;
    }

    private Edge getEdge(int v1, int v2) {
        for (Edge edge : adjLists[v1]) {
            if (edge.to == v2) {
                return edge;
            }
        }
        return null;
    }

    private class Edge {

        private int from;
        private int to;
        private int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public String toString() {
            return "(" + from + ", " + to + ", weight = " + weight + ")";
        }

        public int to() {
            return this.to;
        }

    }

}