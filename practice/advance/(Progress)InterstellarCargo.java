import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static int shortest;
    static void shortestWormhole(int[] start,int[] finish,int[][] hole,int current,int[] visited,int distance){
        //check finish
        int toFinish =  Math.abs(finish[0] - hole[current][2]) + Math.abs(finish[1] - hole[current][3]);
        if( distance+toFinish < shortest ){
            //System.out.println("f"+current+" "+(distance+toFinish));
            shortest = distance+toFinish;
        }else{
            return;
        }
        
        //try other wormhole
        for(int i=0;i<hole.length;i++){
            int nextHole  = Math.abs(hole[i][0] - hole[current][2]) + Math.abs(hole[i][1] - hole[current][3]);
            if( visited[i]==0  && ((distance+nextHole)<shortest) ){//
                //System.out.println("c "+current+" "+i+" "+(distance+nextHole)+" "+Arrays.toString(visited));
                visited[i] = 1;
                shortestWormhole(start,finish,hole,i,visited,distance+nextHole);
                visited[i] = 0;
            }
        }
        
        return;
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        
        int[] start = new int[2];
        int[] finish = new int[2];
        
        for(int i=0;i<2;i++){
            start[i] = scanner.nextInt();
        }
        
        for(int i=0;i<2;i++){
            finish[i] = scanner.nextInt();
        }
        
        int n = scanner.nextInt();
        int[][] hole = new int[n][4];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<4;j++){
                hole[i][j] = scanner.nextInt();   
            }
        }
        
        shortest = Math.abs(finish[0] - start[0]) + Math.abs(finish[1] - start[1]);
        //System.out.println(start[0]+" "+finish[0]+" "+n+" "+hole[0][0]+" "+hole[1][3]);
        int[] visited = new int[n];
        for(int i=0;i<n;i++){
            int distance  = Math.abs(hole[i][0] - start[0]) + Math.abs(hole[i][1] - start[1]);
            visited[i] = 1;
            //System.out.println("st "+i+" "+Arrays.toString(visited));
           
            shortestWormhole(start,finish,hole,i,visited,distance);
            visited[i] = 0;
        }
        
        System.out.println(shortest);
        
    }
}
