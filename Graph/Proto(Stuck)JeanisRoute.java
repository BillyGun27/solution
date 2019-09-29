import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
/*
15 3
1 14 15
13 14 10
14 7 7
12 7 1
7 3 9 
3 8 10
8 15 2
8 9 7
15 11 8
10 15 10
3 1 4
1 2 9
1 4 11
6 4 10
4 5 12

5 3
1 3 4
1 2 1
2 3 2
2 4 2
3 5 3
*/
public class Solution {

    public static class Graph{
        Node head;
        Node current, tail;
        int s;

        Graph(){
            head = new Node();
            current = head;
            tail = head;
            s = 0;
        }

        public void add(int d, int w){
            Node n = new Node(d,w);
            tail.next = n;
            tail = tail.next;
            s++;
        }

        public void next(){
            current = current.next;
            if(current == null){
                current = head;
            }
        }

        public void reset(){
            current = head;
        }

        public int get(){
            return current.data;
        }

        public int getWeight(){
            return current.weight;
        }
        

        public int size(){
            return s;
        }

        public class Node{
            int data;
            int weight;
            Node next;

            Node(){
                data = 0;
                weight = 0;
                next = null;
            }

            Node(int d, int w){
                data = d;
                weight = w;
                next = null;
            }
        }
    }

    static int dfs(int start,int[] city, Graph[] graph,int[] visited,int[] searched,int[] distance ){
        visited[start] = 1;
        int sum = 0;

        for(int i=0;i<graph[start].size();i++){
            graph[start].next();
            int next = graph[start].get();
            int weight = graph[start].getWeight();
            if(visited[next] == 0){
                distance[next] = distance[start]+weight;
                //System.out.println("next"+next);
                for(int j=0;j<city.length;j++){
                    if(city[j] == next && searched[j]==0){
                        searched[j] = 1;
                        sum+=weight;
                        //System.out.println("found" + city[j]+" "+distance[next]);
                        if(maxLink<distance[next]){
                            maxLink=distance[next];
                        }
                    }
                }

                int found = dfs(next,city,graph,visited,searched,distance);
                if(found>0){
                    sum += found+weight;
                }
            }
        }
        graph[start].reset();

        return sum;
    }

    static int maxLink ;
    /*
     * Complete the jeanisRoute function below.
     */
    static int jeanisRoute(int[] city, int[][] roads) {
        /*
        * Write your code here.
        */
        
        Graph[] graph = new Graph[roads.length+2];
        
        for(int i=0;i<graph.length;i++){
            Graph g = new Graph();
            graph[i] = g;    
        }

        for(int i=0;i<roads.length;i++){
            graph[roads[i][0]].add(roads[i][1],roads[i][2]);
            graph[roads[i][1]].add(roads[i][0],roads[i][2]);
        }
        
        int sum = 0;
        maxLink = 0;
        for(int i=0;i<city.length-1;i++){
            int[] visited = new int[graph.length];
            int[] searched = new int[city.length];
            int[] distance = new int[graph.length];
            for(int j=0;j<i+1;j++){
                searched[j] = 1;
            }
            //System.out.println("start "+city[i]);
            
            int fakeSum = dfs(city[i],city,graph,visited,searched,distance);
            if(i==0){
                sum = fakeSum;
            }
        }
        //System.out.println("maxLink"+maxLink);
        return (sum*2)-maxLink;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0].trim());

        int k = Integer.parseInt(nk[1].trim());

        int[] city = new int[k];

        String[] cityItems = scanner.nextLine().split(" ");

        for (int cityItr = 0; cityItr < k; cityItr++) {
            int cityItem = Integer.parseInt(cityItems[cityItr].trim());
            city[cityItr] = cityItem;
        }

        int[][] roads = new int[n-1][3];

        for (int roadsRowItr = 0; roadsRowItr < n-1; roadsRowItr++) {
            String[] roadsRowItems = scanner.nextLine().split(" ");

            for (int roadsColumnItr = 0; roadsColumnItr < 3; roadsColumnItr++) {
                int roadsItem = Integer.parseInt(roadsRowItems[roadsColumnItr].trim());
                roads[roadsRowItr][roadsColumnItr] = roadsItem;
            }
        }

        int result = jeanisRoute(city, roads);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
