//import java.util.*;
public class Solution {

    static class Graph{
        Node head;
        Node current , tail;
        int s;

        Graph(){
            head = new Node();
            tail = head;
            current = head;
            s = 0;

        }

        public void add(int d, int w){
           Node n = new Node(d,w);
           tail.next = n;
           tail = tail.next;
           s++;
           
        }

        public int get(){
            return current.data;
        }

        public int getWeight(){
            return current.weight;
        }

        public void next(){
            current = current.next;
        }

        public void resetGet(){
            current = head;
        }

        public int size(){
            return s;
        }

        class Node{
            int data;
            int weight;
            Node next;

            public Node(){
                data = 0;
                weight=0;
                next = null;
            }

            public Node(int d,int w){
                data = d;
                weight = w;
                next = null;
            }
        }

    }

    public static void tsp(int s,int home ,Graph[] graph,int[] visited,int distance){
        boolean all_visited = true;
        System.out.print( s + "s ");
        visited[s] = 1;
        //for(int j=0;j<visited.length;j++){
        //    System.out.print( visited[j] +" " );
        //}
        //System.out.println( " " );

        int lastDist=0;
        //int shortestDist = 0;
        //int curDist = 0;
        for(int i=0;i<graph[s].size();i++){
            graph[s].next();
            int vertex = graph[s].get();
            int weight = graph[s].getWeight();
            //System.out.println( weight  );
            if(visited[vertex] == 0){
                System.out.println(s+" n "+ vertex  );
                all_visited = false;
                //curDist =  tsp(vertex,home,graph,visited,distance+weight);
                int[] v = visited.clone();
                tsp(vertex,home,graph,v,distance+weight);
                /*if(shortestDist == 0){
                    shortestDist = curDist;
                }else if(shortestDist>curDist){
                    shortestDist = curDist;
                }*/
            }else if(vertex == home ){
                lastDist = weight;
            }
        }
        graph[s].resetGet();

        if(all_visited == true){
            System.out.println("last " + (distance+lastDist)  );
            return ;//shortestDist+lastDist;
        }

        return;
        /*else{
            System.out.println("home " + shortestDist );
            return shortestDist;
        }*/
        
    }


    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */

        Graph[] graph = new Graph[5];

        for(int i=0;i<5;i++){
            Graph g = new Graph();
            graph[i] = g;
        }

       
        graph[1].add(2,10);
        graph[1].add(3,15);
        graph[1].add(4,20);
        graph[2].add(1,10);
        graph[2].add(3,35);
        graph[2].add(4,25);
        graph[3].add(1,15);
        graph[3].add(2,35);
        graph[3].add(4,30);
        graph[4].add(1,20);
        graph[4].add(2,25);
        graph[4].add(3,30);
        

        /*System.out.println(graph[2].get());
        System.out.println(graph[2].get());
        graph[2].resetGet();
        System.out.println(graph[2].get());
        System.out.println(graph[2].get());
        */
        int[] visited = new int[5];
        visited[0]= 1;
        visited[1]= 1;
        for(int i=2;i<5;i++){
            graph[1].next();
            //System.out.println( graph[1].get()  );
            //System.out.println( graph[1].getWeight()  );
            //System.out.println("r " + tsp(i,1,graph,visited, graph[1].getWeight() ) );
            int[] v = visited.clone();
            tsp(i,1,graph,v, graph[1].getWeight() );
        }
       
    }
}
