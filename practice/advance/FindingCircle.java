import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static boolean cycle;
    static void dfs(int start,int prev,int[][] graph,int[] visited){
        visited[start] = 1;
        
         for(int i=1;i<graph[start].length;i++){
            if( visited[i] == 0 && graph[start][i] == 1){
                //System.out.println("path "+start+" "+i);
                dfs(i,start,graph,visited);
            }else if( visited[i] == 1 && graph[start][i] == 1 ){//&& i != prev
                //System.out.println("YES"+start+" "+i);
                cycle = true;
                return;
            } 
        }
        
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        
        int[][] graph= new int[N+1][N+1];
        for(int i=0;i<M;i++){
            int src = scanner.nextInt();
            int dst = scanner.nextInt();
            
            graph[src][dst] = 1;
            //graph[dst][src] = 1;
        }
        cycle = false;
        for(int i=1;i<N+1;i++){
            int[] visited = new int[N+1];
            visited[i] = 1;
            if(!cycle){
                dfs(i,0,graph,visited);
            }
            
        }
        
        if(cycle){
            System.out.println("Yes");
        }else{
             System.out.println("No");
        }
       
    }
}
