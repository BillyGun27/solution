/*

[Input]
5
4 4 1
1 4 2
2 1 3
2 4 3
1 3 4
6 4 2
2 4 1
2 1 4
2 4 1
2 4 3
2 4 3
4 3 2
7 3 3
1 2 3
2 3 1
1 2 3
1 2 3
1 3 2
3 1 2
1 3 2
8 4 2
3 1 2
4 2 3
2 4 1
2 4 3
3 4 2
3 1 4
3 2 1
4 1 2
9 5 2
3 4 1
1 3 4
1 3 2
3 4 1
3 4 1
4 3 1
3 2 1
1 2 3
3 1 4

Output
0
1
-1
3
5
*/
import java.util.*;

public class Solution {
    static int MaxHappiness;
    static void allocate(int N,int M,int K,int[][] choice,int[] cap,int[] employee,int current){
        if(current>=N){
            int happiness=0;
            for(int i=0;i<N;i++){
                for(int j=0;j<3;j++){
                    if( employee[i] == choice[i][j]){
                        if(j==0){
                            happiness+=5;
                        }else if(j==1){
                            happiness+=3;
                        }else if(j==2){
                            happiness+=1;
                        }
                    }
                }
            }

            if(MaxHappiness<happiness){
                MaxHappiness = happiness;
            }
        }else{
            for(int i=1;i<=M;i++){
                if(cap[i] < K){
                    cap[i]++;
                    employee[current] = i; 
                    allocate(N,M,K,choice,cap,employee,current+1);
                    cap[i]--;
                }
            }
            
        }

    } 
    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        for(int t=0;t<T;t++){
            int N = sc.nextInt();
            int M = sc.nextInt();
            int K = sc.nextInt();
            int[][] choice = new int[N][3];
            for(int i=0;i<N;i++){
                for(int c=0;c<3;c++){
                    choice[i][c] = sc.nextInt();
                }
            }

            MaxHappiness = 0;
            int[] cap= new int[M+1];
            int[] employee = new int[N];
            allocate(N,M,K,choice,cap,employee,0);
            System.out.println(MaxHappiness);
                
        }

    }
}

