/*
5
3 100
75 45 80
30 55 95
2 100
65 90
20 30
5 150
35 105 100 45 75
115 75 55 35 105
7 150
70 95 15 65 85 75 55
105 80 10 90 115 110 45
8 200
35 30 50 80 70 15 10 40
70 20 20 85 65 40 25 50


answer
15
-1
25
-1
45
*/

import java.util.*;

public class Solution {
    static int initial,maxT,minT,N,K;
    static int[] inflate,deflate;

    static void tireRush(int[] visited,int inAir,int currentAir,int n){
        if(n>=N){
            initial  = inAir;
        }else{
            for(int i=0;i<N;i++){
                if(visited[i] == 0){
                    visited[i] = 1;
                    int infAir = currentAir+inflate[i];
                    int DefAir = infAir-deflate[i];
                    if( infAir <= K && DefAir >= 0  ){
                        tireRush(visited,inAir,DefAir,n+1);
                    }
                    visited[i] = 0;
                }

            }
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int t=0;t<T;t++){
            N = sc.nextInt();
            K = sc.nextInt();
            inflate = new int[N];
            deflate = new int[N]; 

            for(int i=0;i<N;i++){
                inflate[i] = sc.nextInt();
            }

            for(int i=0;i<N;i++){
                deflate[i] = sc.nextInt();
            }

            maxT = K;
            for(int i=0;i<N;i++){
                int max = K-inflate[i]; 
                if(maxT>max){
                    maxT = max;
                }
            }

            minT = 0;//K;
            //for(int i=0;i<N;i++){
            //    int min = deflate[i]-inflate[i]; 
            //   if(minT>min){
            //        minT = min;
            //  }
            //}
            //if(minT<0){
            //    minT = 0;
            //}

            initial = -1;
            for(int i=minT;i<=maxT;i++){
                int[] visited = new int[N];
                tireRush(visited,i,i,0);
                if(initial != -1){
                    break;
                }
            }
            //System.out.println(maxT);
            //System.out.println(minT);
            System.out.println(initial);
        }

    }
}
