package Graph;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

/**
 * A Graph wrapper class that utilizes a jGraphT graph
 */
public class Graph {

    /** A graph with a custom vertex class and a default weighted edge */
    public SimpleDirectedWeightedGraph<Vertex, DefaultWeightedEdge> graph;
    /** A map of id-s to vertex */
    public Map<String, Vertex> idToVertex;

    /**
     * Creates a default SimpleDirectedWeightedGraph<Vertex, DefaultWeightedEdge> 
     * and initalizes the hashmap
     */
    public Graph() {
        graph = new SimpleDirectedWeightedGraph<Vertex, DefaultWeightedEdge>(DefaultWeightedEdge.class);
        idToVertex = new LinkedHashMap<String, Vertex>();
    }
    
    /**
     * Adds a vertex to the graph and hashmap
     * 
     * @param id of vertex
     * @param x pos
     * @param y pos
     */
    public void addVertex(String id, int x, int y) {
        Vertex v = new Vertex(id, x, y);
        idToVertex.put(id, v);
        graph.addVertex(v);
    }

    /**
     * Creates an edge from v1-->v2 with a weight
     * 
     * @param v1 source vertex
     * @param v2 target vertex
     * @param weight of the edge
     */
    public void addEdgeWithWeight(Vertex v1, Vertex v2, double weight) {    	
    	// create an edge to that vertex only if it doesn't already exist
    	if( graph.getEdge(v1,  v2) == null ){
    		DefaultWeightedEdge newEdge = graph.addEdge(v1, v2);
    		graph.setEdgeWeight(newEdge, weight);
    	}
    }

    /**
     * Returns an array of edges outgoing from the given node PG 213 on look up tables
     * 
     * @param fromNode
     * @return list of connections of the node
     */
    public ArrayList<DefaultWeightedEdge> getConnections(Vertex fromNode) {
        ArrayList<DefaultWeightedEdge> outgoingEdgesOfNode = new ArrayList<DefaultWeightedEdge>();
        outgoingEdgesOfNode.addAll(graph.outgoingEdgesOf(fromNode));
        return outgoingEdgesOfNode;
    }
    
}
