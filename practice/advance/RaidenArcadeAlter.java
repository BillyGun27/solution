import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static int MaxCoin ;
    
    static int[][] DeployBomb(int N, int[][] space, int start){
         int[][] EmptySpace  = new int[N][5];
                    
                    for(int l=N-1;l>=0;l--){
                        for(int m=0;m<5;m++){
                            int square = space[l][m];
                            if( start-5 < l && l <= start ){
                               if(space[l][m] == 2){
                                   square=0;
                               }
                            }

                             EmptySpace[l][m] = square; 
                        }

                    }
        /*
        for(int i=0;i<EmptySpace.length;i++){
            for(int j=0;j<EmptySpace[i].length;j++){
                System.out.print(EmptySpace[i][j] + " ");
            }System.out.println(" ");
            
        }
        System.out.println("_");*/
                           
            
        
        return EmptySpace;
    }
    
    static void Raid(int N,int[][] space, int level,int plane, int coin){
        if(level < 0){
            //System.out.println("stop "+coin);
            if(MaxCoin<coin){
                MaxCoin = coin;
            }
            return;
        }
        //0 empty, 1 coin, 2 enemy
        if(level >= 0){
            
           for(int i=-1;i<2;i++){
               int move = plane + i;
               //System.out.println("t "+level+" "+move+" "+coin);
               if(0 <= move && move < 5){
                   
                   if(space[level][move] == 1){
                       //System.out.println("getCoin "+level+" "+move+" "+coin);
                        Raid(N,space,level-1,move,coin+1);
                        
                        
                    }else if(space[level][move] == 2){
                       //System.out.println("Enemy "+level+" "+move+" "+space[level][move]+" "+coin);
                       int ccoin = coin;
                       ccoin--;
                       if(ccoin>=0){
                           Raid(N,space,level-1,move,ccoin);
                       }
                   }else{
                        Raid(N,space,level-1,move,coin);
                   }
                    
               }
                
        
            } 
        }
    
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        
        while (!scanner.hasNextInt()) {
            System.out.println(-1);
            return ;
         
        }
        
        int N = scanner.nextInt();
        int[][] space = new int[N][5];
        for(int i=0;i<N;i++){
            for(int j=0;j<5;j++){
                space[i][j] = scanner.nextInt();   
            }
        }
        MaxCoin = -N ;
        //int visited[] = new int[N];
        
        Raid(N,space,N-1,2,0);
        
        for(int i=N-1;i>=4;i--){
            int[][] BombedSpace = DeployBomb(N, space, i );
            //System.out.println(i+" bomb");
            Raid(N,BombedSpace,N-1,2,0);
        }
        
        if(MaxCoin < 0){
            System.out.println(-1);
        }else{
            System.out.println(MaxCoin);
        }
        
    }
}
