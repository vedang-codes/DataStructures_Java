/** This is a Graph class represented by a set of Edges.
 * V and E are the number of nodes and edges respectively. 
 **/
import java.io.*;
import java.util.*;

public class Graph {
	public int V;
	public int E;
	public Set<Edge>[] edgesOf;
	public int[] indegree;

	public Graph(int v, int e) {
		V = v;
		E = e;
	}

	/** Construct a graph from a text file that looks like below:
5
9
0 1 10
0 2 3
1 2 1
1 3 2
2 1 4
2 3 8
2 4 3
3 4 7
4 3 9

Meaning that there are 5 nodes, 9 edges, and the first edge goes from node0 to node1 with weight 10.  	 
The constructor reads the integers, and store the edges in a Set.
	**/ 
	public Graph(Scanner in) throws Exception {
		V = in.nextInt();
		indegree = new int[V];
		edgesOf = (Set<Edge>[]) new HashSet[V];
		for (int v = 0; v < V; v++) {
			edgesOf[v] = new HashSet<Edge>();
		}

		E = in.nextInt();
		for (int i = 0; i < E; i++) {
			int v = in.nextInt();
			int w = in.nextInt();
			double weight = in.nextDouble();
			edgesOf[v].add(new Edge(v, w, weight));
			indegree[w]++;
		}
	}

	public void add(Edge e) {
		edgesOf[e.from()].add(e);
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " " + E + "\n");
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (Edge e : edgesOf[v]) {
				s.append(e + "  ");
			}
			s.append("\n");
		}
		return s.toString();
	}

	
	public void main(String[] args) throws Exception {
		Graph G = new Graph(new Scanner(new File("graph1.txt")));
		
		System.out.println(G);
		
	}
}