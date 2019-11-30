import java.io.*;
import java.util.*;

public class Solution {
    static void press(int n,int m,char[][] map,int[][] stamped,int size){
        boolean stamable = true;
        for(int i=n;i<n+size;i++){
            for(int j=m;j<m+size;j++){
                if(map[i][j]=='#'){
                    stamable = false;
                    break;
                }        
            }
        }
        
        if(stamable){
            for(int i=n;i<n+size;i++){
                for(int j=m;j<m+size;j++){
                    if(map[i][j]=='_'){
                        stamped[i][j] = 1;
                    }else if(map[i][j]=='?'){
                        stamped[i][j] = 2;
                    }        
                }
            }
        }
    }
    
    static boolean Stamp(int N,int M,int totalSym ,char[][] map, int[][] stamped,int size){
        int ss = size-1;
        int limN= N-ss;
        int limM= M-ss;
        for(int i=0;i<limN;i++){
            for(int j=0;j<limM;j++){
                press(i,j,map,stamped,size);   
            }
        }
        
        int sym = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(stamped[i][j] == 1){
                   sym++; 
                }
            }
        }
        //System.out.println(sym+" "+totalSym+" "+size);
        if(sym>=totalSym){
            return true;
        }else{
            return false;
        }
    }    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        char[][] map = new char[N][M];
        int totalSym = 0;
        
        
        for(int i=0;i<N;i++){
            String line = sc.next();
            //System.out.println(line);
            for(int j=0;j<M;j++){
                map[i][j] = line.charAt(j); 
                if(map[i][j] == '_'){
                    totalSym++;
                }
                
            }
        }
        /*
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                System.out.print(map[i][j]+" ");
                
            }System.out.println();
        }*/
        
        int Max = N;
        if(M>Max){
            Max = M;
        }
        int StampSize = 0;
        for(int s=Max;s>0;s--){
            //System.out.println(s);
            int[][] stamped = new int[N][M];
            boolean possible =  Stamp(N,M,totalSym,map,stamped,s);    
            if(possible){
                StampSize = s;
                break;
            }
        }
        
        System.out.println(StampSize);
           
    }
}
