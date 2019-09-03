import java.util.*;
public class Solution {

    static void DFS(int current, ArrayList<ArrayList<Integer>> graph, int[] visited ){

    }

    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */

        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

        int[][] inp = {{1,2},{2,3},{3,4},{4,1}};

        for(int i=0; i<4+1;i++){
            ArrayList<Integer> g = new ArrayList<Integer>();
            graph.add(g);
        }

        for(int i=0; i<inp.length;i++){
            graph.get(inp[i][0]).add(inp[i][1]);
        }

        System.out.println(graph.get(1).get(0));
    }
}

