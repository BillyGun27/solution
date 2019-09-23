import java.util.*; 
public class Solution {
    
    static int EdgeTotal = 5;
    static int VertexTotal = 4;

    static class Edge implements Comparable<Edge> {
        int src, dst, weight;

        Edge(int s, int d, int w){
            src = s;
            dst = d;
            weight =w;
        }

         // Comparator function used for sorting edges  
        // based on their weight 
        public int compareTo(Edge compareEdge) 
        { 
            return this.weight-compareEdge.weight; 
        } 
    }

    static class subset{
        int parent,rank;

        subset(int p, int r){
            parent = p;
            rank = r;
        }
    } 
    
    static int find(subset[] subsets , int i){
        if(subsets[i].parent == i){
            return subsets[i].parent;
        }

        return find(subsets, subsets[i].parent);
    }

    static void union(subset[] subsets,int x ,int y){
        int xroot = find(subsets, x); 
        int yroot = find(subsets, y); 
  
        if (subsets[xroot].rank < subsets[yroot].rank) 
            subsets[xroot].parent = yroot; 
        else if (subsets[yroot].rank < subsets[xroot].rank) 
            subsets[yroot].parent = xroot; 
        else
        { 
            subsets[xroot].parent = yroot; 
            subsets[yroot].rank++; 
        } 
    }

    static void graphKruskal(Edge[] graph){
        Edge[] result = new Edge[VertexTotal];

        subset[] subsets = new subset[VertexTotal];

        Arrays.sort(graph); 

        for(int i=0;i<subsets.length;i++){
            subsets[i] = new subset(i,0);
        }

        int l=0;
        int e=0;
        while( e < (VertexTotal-1) ){ 

            Edge next_edge = graph[l];
            l++;

            int x = find(subsets,next_edge.dst);
            int y = find(subsets,next_edge.src);

            if( x != y ){
                result[e] = next_edge;
                e++;
                union(subsets,x,y);
            }
            
        }

        System.out.println(e);
        for (int i = 0; i < e; ++i) 
            System.out.println(result[i].src+" -- " +  
                   result[i].dst+" == " + result[i].weight); 
       
    }

    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */

        
        Edge[] graph = new Edge[EdgeTotal];

        graph[0] = new Edge(0,1,10);
        graph[1] = new Edge(0,2,6);
        graph[2] = new Edge(0,3,5);
        graph[3] = new Edge(1,3,15);
        graph[4] = new Edge(2,3,4);
        
       
       graphKruskal(graph);
    }
}
