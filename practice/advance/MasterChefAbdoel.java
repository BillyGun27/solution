import java.io.*;
import java.util.*;

public class Solution {
    static int Profit;
    static void maxProfit(int totalRecipe,int totalTry,int la,int lb,int lc,int[][] recipe,int curRecipe,int prof){
        
        //if( 0<la && 0<lb && 0<lc ){
        if(curRecipe<totalRecipe){
            //for(int i=curRecipe;i<totalRecipe;i++){
                for(int t=0;t<=totalTry;t++){
                    //System.out.println(i);
                    int ia = la - (recipe[curRecipe][0]*t);
                    int ib = lb - (recipe[curRecipe][1]*t);
                    int ic = lc - (recipe[curRecipe][2]*t);
                    int price = recipe[curRecipe][3] * t;
                    //System.out.println(prof+price);
                    if( ( Profit< prof+price ) && ( 0<=ia && 0<=ib && 0<=ic ) ){
                        Profit = prof+price;
                    }
                    
                    maxProfit(totalRecipe,totalTry,ia,ib,ic,recipe,curRecipe+1,prof+price);   
                    
                    
                }
            //}
        }
        //}
        
        
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        int[] ingredient = new int[3];
        int[][] recipe = new int[N][4];
        
        for(int i=0;i<3;i++){
            ingredient[i] = scanner.nextInt();
        }
        
        for(int i=0;i<N;i++){
            for(int j=0;j<4;j++){
                recipe[i][j] = scanner.nextInt();
            }
        }
        
        /*System.out.println(ingredient[0]+" "+recipe[0][3]);
         int price = recipe[0][3] * 1;
                System.out.println(price);*/
        
        Profit = 0;
        //for(int i=0;i<N;i++){
        //    for(int t=0;t<=K;t++){
                //int ia = ingredient[0] - recipe[i][0];
                //int ib = ingredient[1] - recipe[i][1];
                //int ic = ingredient[2] - recipe[i][2];
                //int price = recipe[i][3] * t;
                //System.out.println(t+" "+price);
                maxProfit(N,K,ingredient[0],ingredient[1],ingredient[2],recipe,0,0);       
         //   }
        //}
        
        System.out.println(Profit);
        
    }
}
