import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    //graph problem
    // Complete the findShortest function below.
    
    //Adjacent Matrix Version
    static int BFSx(int n,long[][] graph, int target , long[] ids ){
        //int currentLength=0;
        int shortestLength = graph.length;
        int found = 0;

        LinkedList<Integer> q = new LinkedList<Integer>();
        int[] visited = new int[graph.length];
        int[] distance = new int[graph.length];

        q.add(n);
        visited[n] = 1;

        while(q.size() != 0){
            n = q.poll();
            //currentLength++;
            //System.out.println("c "+n);

            for(int i=1;i<graph.length;i++){
                if(i!=n && visited[i] != 1 && graph[n][i] != 0 ){
                    q.add(i);
                    visited[i]=1;
                    distance[i] = distance[n]+ 1;
                    
                    if(ids[i-1]==target){
                        //found = 1;
                        if(distance[i]<shortestLength){
                            shortestLength = distance[i];
                            //System.out.println("s " + shortestLength);                
                        }

                         distance[i] = 0;
                    }
                    
                    
                }
            }

            //if(found == 1){
            //    currentLength = 0;
            //}




        }

        return shortestLength;
    }
        // Adjacent List Version
     static int BFS(int n,ArrayList<ArrayList<Integer>> graph, int target , long[] ids ){
        //int currentLength=0;
        int shortestLength = graph.size()+1;
        int found = 0;

        LinkedList<Integer> q = new LinkedList<Integer>();
        int[] visited = new int[graph.size()+1];
        int[] distance = new int[graph.size()+1];

        q.add(n);
        visited[n] = 1;

        while(q.size() != 0){
            n = q.poll();
            
            //System.out.println("s " + n);

            for(int i=0;i<graph.get(n).size();i++){
                int cn = graph.get(n).get(i);

                if( visited[cn] != 1 ){
                    q.add( cn );
                    visited[ cn ]=1;
                    distance[ cn ] = distance[n]+ 1;
                    
                    if(ids[cn-1]==target){
                        //found = 1;
                        if(distance[cn]<shortestLength){
                            shortestLength = distance[cn];
                            //System.out.println("s " + shortestLength);                
                        }

                         distance[cn] = 0;
                    }
                    
                    
                }
            }

    
        }

        return shortestLength;
    }

    /*
     * For the unweighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] to <name>To[i].
     *
     */
    static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, int val) {
        // solve here
        //long[][] graph = new long[graphNodes+1][graphNodes+1];
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        int totalC = 0 ;
        int start = 0;

        for(int i=0; i<graphNodes+1 ; i++){
            ArrayList<Integer> g = new ArrayList<Integer>(); 
            graph.add(g);

            if(i<ids.length){
                if(ids[i] == val){
                    totalC++;
                    start = i+1;
                }
            }
            
        }

        for(int i=0; i<graphFrom.length ; i++ ){
            graph.get(graphFrom[i]).add(graphTo[i]);
            graph.get(graphTo[i]).add(graphFrom[i]);
        }

        /*
        for(int i=0; i<graphFrom.length ; i++ ){
            graph[graphFrom[i]][graphTo[i]] = 1 ;
            graph[graphTo[i]][graphFrom[i]] = 1 ;  
        }

        for(int i=ids.length-1; i>=0  ; i--){
            if(ids[i] == val){
                totalC++;
                start = i+1;
            }
        }
        */

        if(totalC < 2){
            return -1 ;
        }else{
            return BFS(start, graph, val , ids);
        }
    

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] graphNodesEdges = scanner.nextLine().split(" ");
        int graphNodes = Integer.parseInt(graphNodesEdges[0].trim());
        int graphEdges = Integer.parseInt(graphNodesEdges[1].trim());

        int[] graphFrom = new int[graphEdges];
        int[] graphTo = new int[graphEdges];

        for (int i = 0; i < graphEdges; i++) {
            String[] graphFromTo = scanner.nextLine().split(" ");
            graphFrom[i] = Integer.parseInt(graphFromTo[0].trim());
            graphTo[i] = Integer.parseInt(graphFromTo[1].trim());
        }

        long[] ids = new long[graphNodes];

        String[] idsItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < graphNodes; i++) {
            long idsItem = Long.parseLong(idsItems[i]);
            ids[i] = idsItem;
        }

        int val = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int ans = findShortest(graphNodes, graphFrom, graphTo, ids, val);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
