import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static void findGroup(int N,int[][] map,int h,int w,int[][] visitedBooth,int product){
        int[][] move = {//h,w
                {-1,0},{1,0},{0,-1},{0,1}
            };
        
        for(int m=0;m<4;m++){
            int hh = h+move[m][0];
            int ww = w+move[m][1];
            
            if( (0<=hh && hh<N) && (0<=ww && ww <N) ){
                if(visitedBooth[hh][ww] == 0 && map[hh][ww] == product){
                    visitedBooth[hh][ww] = 1;
                     findGroup(N,map,hh,ww,visitedBooth,product);
                }
            }
        
        }
        
    }
    
    public static void checkAdj(int N,int[][] map,int h,int w,int[][] visitedAdj,int[] adjbooth,int product){
          int[][] move = {//h,w
                {-1,0},{1,0},{0,-1},{0,1}
            };
        
        for(int m=0;m<4;m++){
            int hh = h+move[m][0];
            int ww = w+move[m][1];
            if( (0<=hh && hh<N) && (0<=ww && ww <N) ){
                if(visitedAdj[hh][ww] == 0 && map[hh][ww] == product){
                    visitedAdj[hh][ww] = 1;
                    adjbooth[map[hh][ww]]++;
                    checkAdj(N,map,hh,ww,visitedAdj,adjbooth,map[hh][ww]);
                }
            }
            
        }
    }
    
    public static void  convertZero(int N,int[][] map,int h,int w,int[][] visited,int[][] visitedAdj,int[] adjbooth){
            int[][] move = {//h,w
                {-1,0},{1,0},{0,-1},{0,1}
            };
        
        for(int m=0;m<4;m++){
            int hh = h+move[m][0];
            int ww = w+move[m][1];
            if( (0<=hh && hh<N) && (0<=ww && ww <N) ){
                if(visited[hh][ww] == 0 && map[hh][ww] == 0){
                    visited[hh][ww] = 10;
                    convertZero(N,map,hh,ww,visited,visitedAdj,adjbooth);
                }else if(visitedAdj[hh][ww] ==0 && map[hh][ww] != 0){
                    visitedAdj[hh][ww] = 1;
                    adjbooth[map[hh][ww]]++;
                    checkAdj(N,map,hh,ww,visitedAdj,adjbooth,map[hh][ww]);
                }
            }
            
        }
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] map = new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j] = sc.nextInt();
            }
        }
        
        int[][] visited = new int[N][N];
        //int[][] newbooth = new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
               int[][] visitedAdj = new int[N][N];
               int[] adjbooth = new int[6];
                if( visited[i][j] == 0 && map[i][j] == 0 ){
                    visited[i][j] = 10;
                    convertZero(N,map,i,j,visited,visitedAdj,adjbooth);
                    
                    int maxProd = 0;
                    int ProdNum = 0;
                    for(int a=0;a<6;a++){
                        if(maxProd<=adjbooth[a]){
                            maxProd = adjbooth[a];
                            ProdNum = a;
                        }
                        //System.out.print(adjbooth[a]+" ");
                    }//System.out.println();
                    
                    for(int k=0;k<N;k++){
                        for(int l=0;l<N;l++){
                            if(visited[k][l] == 10){
                                visited[k][l] = ProdNum;
                            }
                            
                        }
                    }
                }
                
                
                
            }
        }
        
        //merge
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j] == 0){
                    map[i][j] = visited[i][j];
                }
                //System.out.print(map[i][j]+" ");
            }
            //System.out.println();
        }
        
        //count group
        int[][] visitedBooth = new int[N][N];
        int[] insbooth = new int[6];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(visitedBooth[i][j] == 0){
                    //System.out.println("kl "+map[i][j]+" "+i+" "+j);
                    visitedBooth[i][j] = 1;
                    findGroup(N,map,i,j,visitedBooth,map[i][j]);
                    insbooth[map[i][j]]++;
                }
            }
        }
        
        //System.out.println("f");
        int totalBooth = 0;
         for(int a=1;a<6;a++){
             //System.out.println(insbooth[a] + " ");
                totalBooth += insbooth[a];
          }
        
       
       System.out.println(totalBooth); 
    }
}
