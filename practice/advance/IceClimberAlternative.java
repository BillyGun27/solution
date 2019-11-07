import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static int Highest;
    public static int[] minDis(int N,int M,int[][] diff, int[][] visited){
        int[] path = new int[2];
        int shortest = Integer.MAX_VALUE;
        
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(diff[i][j] != -1 && visited[i][j] != 1 && diff[i][j]<shortest ){
                    shortest = diff[i][j];
                    path[0] = i;
                    path[1] = j;
                }
            }
        }
        
        return path;
    }
    
    public static void climbing(int N,int M,int[][] area,int[][] diff,int[][] visited,int totalPath,int[] target){
        
        for(int l=0;l<totalPath;l++){
            int[] currentPath = minDis(N,M,diff,visited);
            int Nc = currentPath[0];
            int Mc = currentPath[1];
            visited[Nc][Mc] = 1;
            
            //up
            int u = 1;
            while(0 <= Nc-u){
                if( area[Nc-u][Mc] != 0  && visited[Nc-u][Mc] != 1){
                       if( u < diff[Nc][Mc]){
                        diff[Nc-u][Mc] = diff[Nc][Mc];
                       }else{
                           diff[Nc-u][Mc] = u;
                       }
                       break;
                }
                u++;
            }
            
            //down
            int d = 1;
            while(Nc+d < N){
                if( area[Nc+d][Mc] != 0  && visited[Nc+d][Mc] != 1){
                        if( d < diff[Nc][Mc]){
                            diff[Nc+d][Mc] = diff[Nc][Mc];
                        }else{
                            diff[Nc+d][Mc] = d;
                        }
                        break;
                }
                d++;
            }
            
            //left
            if(0 <= Mc-1){
                if( area[Nc][Mc-1] != 0  && visited[Nc][Mc-1] != 1){
                       diff[Nc][Mc-1] = diff[Nc][Mc];
                }
            }
            
            //right
            if(Mc+1< M){
                if( area[Nc][Mc+1] != 0  && visited[Nc][Mc+1] != 1){
                       diff[Nc][Mc+1] = diff[Nc][Mc];
                }
            }
        
        }
        
        Highest = diff[target[0]][target[1]];
        /*for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                System.out.print(diff[i][j]+" ");
                if(Highest<diff[i][j]){
                    Highest = diff[i][j];
                }
            }
            System.out.println();
        }*/
        
        
        
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        
        int M = sc.nextInt();
        int N = sc.nextInt();
        
        int totalPath = 0;
        int[] target = new int[2];
        int[][] area = new int[N][M];
        int[][] diff= new int[N][M];
        int[][] visited = new int[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                area[i][j] = sc.nextInt();
                if(area[i][j] != 0){
                    totalPath++;
                    if(area[i][j] == 2){
                        diff[i][j] = 0;
                    }else{
                        diff[i][j] = Integer.MAX_VALUE;
                    }
                  
                    if(area[i][j] == 3 ){
                        target[0] = i;
                        target[1] = j;
                    }  
                }else{
                    diff[i][j] = -1;
                }
            }
        }
        
        climbing(N,M,area,diff,visited,totalPath,target);
        System.out.println(Highest);
    }
}
