import java.io.*;
import java.util.*;
//import java.text.*;
//import java.math.*;
//import java.util.regex.*;
/*
6
2 2 2 2 2
0 0 0 0 0
0 0 2 0 0
2 0 0 0 2
0 0 0 0 0
1 2 2 2 1

5
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0

8
1 1 1 1 1
2 2 2 2 2
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
2 2 2 2 2
0 0 0 0 0

12
0 2 2 2 2
0 0 0 0 0
2 2 2 2 0
0 0 0 0 0
0 2 2 2 2
0 0 0 0 0
2 2 2 2 0
0 0 0 0 0
0 2 2 2 2
0 0 0 0 0
2 2 2 2 0
0 0 0 0 0

*/
public class Solution {
    
    static int Raid(int N,int[][] space){
        int Profit = -1 ;
        int FakeProfit = -1;
        /*
        for(int i=0;i<N;i++){
            for(int j=0;j<5;j++){
                System.out.print(space[i][j]+" ");
            }
             System.out.println();
        }
         System.out.println("______");*/
        for(int i=1;i<N;i++){
            for(int j=0;j<5;j++){
                int max = -1;
                if(j==0){
                    if(space[i-1][j] < space[i-1][j+1]  ){
                        max = space[i-1][j+1];
                    }else{
                        max = space[i-1][j];
                    }
                    space[i][j] += max;
                }else if(j==4){
                        if(space[i-1][j-1] < space[i-1][j]  ){
                            max = space[i-1][j];
                        }else{
                            max = space[i-1][j-1];
                        }
                        space[i][j] += max;
                }else{
                    
                        if(space[i-1][j-1] < space[i-1][j]  ){
                            max = space[i-1][j];
                        }else{
                            max = space[i-1][j-1];
                        }
                    
                        if(max < space[i-1][j+1]  ){
                            max = space[i-1][j+1];
                        }
                       
                       
                        space[i][j] += max;
                }
                
                if(FakeProfit<space[i][j] && i != (N-1) ){
                    FakeProfit = space[i][j];
                }    
            }
            
        }
        
         for(int j=1;j<4;j++){
                if(Profit<space[N-1][j]){
                        Profit = space[N-1][j];
                    }
         }
        
        if(Profit<FakeProfit){
            Profit = -1;
        }
        
        /*
        for(int i=0;i<N-1;i++){
            for(int j=0;j<5;j++){
                if(Profit<space[i][j]){
                        Profit = -1;
                        break;
                }
            }
            
        }*/
        /*
         for(int i=0;i<N;i++){
            for(int j=0;j<5;j++){
                System.out.print(space[i][j]+" ");
            }
             System.out.println();
             
        }
         System.out.println("end"+Profit);*/
         
        
       return Profit;
    
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
        int MaxCoin =-1;
        
        for(int c=-1;c<=N-5;c++){
        //int c=1;
            int[][] replica = new int[N][5];
            //System.out.println(c);
            for(int i=0;i<N;i++){
                for(int j=0;j<5;j++){
                    int num = space[i][j] ;
                    
                    if( space[i][j]==2 ){
                        if( c <= i && i < c+5 && c != -1 ){
                            num = 0 ;
                        }else{
                            num = -1 ;
                        }
                            
                    }
                    
                
                     replica[i][j] = num;
                }
            }
            /*
             for(int j=0;j<5;j++){
                 
             }*/
            
            
            int coin = Raid(N,replica);
            if(MaxCoin < coin){
                MaxCoin = coin ;
            }
            
        }
        
        
        if(MaxCoin < 0){
            System.out.println(-1);
        }else{
            System.out.println(MaxCoin);
        }
        
    }
}
