import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static void findHall(int N,int G,int[][] mine,int[][] path){
        int[][] shorted = new int[N][N];
        
        for(int g=0;g<G;g++){
            int[][] dist = new int[N][N];
            int[][] visited = new int[N][N];
            int[][] queue = new int[N*N][2];
            int start = 0;
            int end = 0;
            
            int y= mine[g][0]-1;
            int x= mine[g][1]-1;
            visited[y][x] = 1;
            shorted[y][x] = 0;
            
            queue[end][0] = y;
            queue[end][1] = x;
            end++;
            
            int[] ymove = {-1,1,0,0};
            int[] xmove = {0,0,-1,1};
            
            while(start < end){
                y = queue[start][0];
                x = queue[start][1];
                start++;
                //System.out.println( "ss"+start+" ee"+end);
                for(int m=0;m<4;m++){
                    int yy = y+ymove[m];
                    int xx = x+xmove[m];
                    //System.out.println(yy+" "+xx+" sc"+start+" ec"+end);
                    if( (0 <= yy && yy < N ) && ( 0 <= xx && xx < N) ){ 
                        if(visited[yy][xx] == 0 && path[yy][xx] == 1 ){
                            //System.out.println(yy+" "+xx+" s"+start+" e"+end);
                            visited[yy][xx] = 1;
                            
                            dist[yy][xx] =  dist[y][x]+1;
                            
                            //System.out.println( "ok"+(shorted[y][x]+1) );
                            if(shorted[yy][xx] <  dist[yy][xx] ){
                                shorted[yy][xx] =  dist[yy][xx] ;
                            }
                            
                            
                            queue[end][0] = yy;
                            queue[end][1] = xx;
                            end++;
                        }
                    }
                    
                }
            }
            /*
            for(int k=0;k<N;k++){
                for(int l=0;l<N;l++){
                    System.out.print(dist[k][l]+ " ");
                }System.out.println();
            }
            System.out.println(" ");*/
        }
        
        int min = N*N;
        for(int k=0;k<N;k++){
            for(int l=0;l<N;l++){
                if(shorted[k][l] < min && shorted[k][l]!=0){
                    min = shorted[k][l];
                }
                //System.out.print(shorted[k][l]+ " ");
            }//System.out.println();
        }
        
        System.out.println(min);
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int G = sc.nextInt();
        int[][] mine = new int[G][2];
        int[][] path = new int[N][N];
        
        for(int i=0;i<G;i++){
            mine[i][0] = sc.nextInt();
            mine[i][1] = sc.nextInt();
        }
        
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                   path[i][j] = sc.nextInt();
            }
        }
        
        
        findHall(N,G,mine,path);
        //System.out.println(N+" "+G+" "+mine[0][0]+" "+path[0][0]);
        
    }
}
