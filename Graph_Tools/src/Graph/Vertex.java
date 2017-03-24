package Graph;

public class Vertex implements Comparable<Vertex>  {
    public String id;
    public float x;
    public float y;
    
    public Vertex(String id, int x, int y){
        this.id = id;
        this.x = x;
        this.y = y;
    }
    
    public String toString(){
        return id;
    }
    
    public boolean equals(Object other){
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Vertex)) return false;
        
        Vertex otherVertex = (Vertex)other;
        if( otherVertex.id.equals(this.id) ) return true;
        
        return false;
    }

    @Override
    public int compareTo(Vertex otherV) {
        return this.id.compareTo(otherV.id);
    }
}
