public class Solution {
    
    static int VertexTotal = 3;

    static class Edge{
        int src, dst;

        Edge(int s, int d){
            src = s;
            dst = d;
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

    static boolean isCycle(Edge[] graph){
        subset[] subsets = new subset[VertexTotal];

        for(int i=0;i<subsets.length;i++){
            subsets[i] = new subset(i,0);
        }

        for(int i=0;i<graph.length;i++){
            int x = find(subsets,graph[i].dst);
            int y = find(subsets,graph[i].src);

            if( x == y ){
                return true;
            }

            union(subsets,x,y);
        }
        return false;
    }

    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */

        
        Edge[] graph = new Edge[VertexTotal];

        graph[0] = new Edge(0,1);
        graph[1] = new Edge(1,2);
        graph[2] = new Edge(2,0);
        
       
       if(isCycle(graph)){
           System.out.println("Cycle");
       }else{
           System.out.println("Not Cycle");
       }
    }
}
