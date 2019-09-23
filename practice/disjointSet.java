public class Solution {
    
    static int VertexTotal = 3;

    static class Edge{
        int src, dst;

        Edge(int s, int d){
            src = s;
            dst = d;
        }
    }
    
    static int find(int[] parent , int i){
        if(parent[i] == -1){
            return i;
        }

        return find(parent, parent[i]);
    }

    static void union(int[] parent,int x ,int y){
        int xx = find(parent,x);
        int yy = find(parent,y);

        parent[xx] = yy;
    }

    static boolean isCycle(Edge[] graph){
        int[] parent = new int[VertexTotal];

        for(int i=0;i<parent.length;i++){
            parent[i] = -1;
        }

        for(int i=0;i<graph.length;i++){
            int x = find(parent,graph[i].dst);
            int y = find(parent,graph[i].src);

            if( x == y ){
                return true;
            }

            union(parent,x,y);
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
