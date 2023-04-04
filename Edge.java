/** Edge class store the from  node (v) and to node (w) and the weight. 
 *
 **/
public class Edge { 
    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

     public double weight() {
        return weight;
    }

    public String toString() {
        return "(" + v +" "+ String.format("%5.2f", weight)+" "+w+")";
    }
}