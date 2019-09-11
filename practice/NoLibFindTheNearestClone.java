import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    static class AdjList{
        Node head;
        Node tail;
        Node current;
        int totalNode;

        AdjList(){
            totalNode = 0;
            head = new Node();
            tail = head;
            current = head;
        }

        public void add(int data){
           totalNode++;
           Node n = new Node(data);
           tail.next = n;
           tail = tail.next;
        }

        public int get(){
            current = current.next;
            return current.data;
        }
        
        public void resetGet(){
            current = head;
        }

        public int size(){
            return totalNode;
        }

        class Node{
            int data;
            Node next;

            Node(){
                data = 0;
                next = null;
            }

            Node(int d){
                data = d;
                next = null;
            }
        }
    }

    // Complete the findShortest function below.
    static int BFS(int s, AdjList[] graph , long[] ids, int val){
        int sizelen = graph.length+1 ;
        int[] fakeQueue = new int[sizelen];
        int start = 0, end = 1 ;
        
        int min_distance = sizelen;
        int[] visited = new int[sizelen]; 
        int[] distance = new int[sizelen]; 
        visited[s] = 1 ; 
        
        fakeQueue[start] = s;

        while(start<end){
            s = fakeQueue[start];
            start++;
           
            for(int i=0;i<graph[s].size();i++){
                int next = graph[s].get();

                if(visited[next]==0){
                    visited[next] = 1;
                    distance[next] = distance[s] + 1 ;

                    if(ids[next-1] == val){
                        if(distance[next]<min_distance){
                            min_distance = distance[next];
                        }
                        distance[next] = 0;
                    }
                    
                    //queue.add(next);
                    fakeQueue[start] = next;
                    end++;
                }
            }  

        }

        return min_distance;

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

        int totVal= 0, start=0;
        AdjList[] graph = new AdjList[graphNodes+1];
        for(int i=0;i<graphNodes+1;i++){
            AdjList g = new AdjList();
            graph[i] = g;
            if(i<ids.length){
                if(ids[i] == val){
                    totVal++;
                    start = i+1;
                }
            }
        }
        
        for(int i=0; i<graphFrom.length ; i++ ){
            graph[graphFrom[i]].add(graphTo[i]);
            graph[graphTo[i]].add(graphFrom[i]);
        }
       
        if(totVal<2){
            return -1;
        }else{
        
            return BFS(start,graph,ids,val);
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
