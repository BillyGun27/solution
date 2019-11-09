/*
5
5 6 2 1 3
0 0 5 3 6 0
0 0 2 0 2 0
3 3 1 3 7 0
0 0 0 0 0 0
0 0 0 0 0 0
5 6 2 2 6
3 0 0 0 0 3
2 0 0 0 0 6
1 3 1 1 3 1
2 0 2 0 0 2
0 0 4 3 1 1
10 10 4 3 9 //WRONG
0 0 0 0 0 0 0 0 0 0
0 0 0 7 5 0 5 0 0 0
0 0 3 2 2 6 0 0 0 0
0 4 7 2 2 2 7 0 0 4
0 3 0 1 1 2 2 0 0 5
7 4 1 2 0 0 4 6 0 0
5 3 1 7 0 2 2 6 5 7
7 3 2 1 1 7 1 0 2 7
3 4 0 0 4 0 5 1 0 1
20 20 13 11 13 //WRONG
0 0 0 1 4 4 4 0 0 0 0 0 0 0 0 1 2 3 1 0
0 0 0 0 0 0 0 0 0 0 0 0 4 2 7 7 2 0 1 1
0 0 0 0 0 0 0 0 0 6 2 4 4 2 0 4 7 0 6 0
0 0 0 7 5 5 3 0 0 7 5 0 5 6 4 2 6 3 1 5
0 0 0 1 2 6 3 3 7 0 3 6 2 4 5 6 7 7 5 7
0 0 0 3 7 6 1 5 3 3 4 5 7 6 0 4 3 3 1 1
0 1 2 1 5 6 1 6 1 6 5 1 6 0 0 3 4 1 7 6
0 2 3 2 2 7 3 0 0 3 2 5 2 1 0 6 5 1 6 5
0 2 5 7 0 7 1 3 3 4 1 3 3 0 2 3 3 2 4 1
4 0 0 7 2 4 2 2 1 3 1 6 5 5 6 2 5 1 1 6
5 6 4 0 3 6 5 2 2 6 1 2 0 1 7 5 7 2 2 2 
1 6 3 1 4 4 1 0 3 0 4 2 7 2 0 2 3 6 2 5
1 5 7 2 1 1 4 4 2 1 0 2 7 1 6 2 6 6 2 2
3 7 0 6 5 0 4 0 6 6 7 1 3 1 1 1 5 1 6 6
0 4 0 1 6 2 1 0 7 0 4 2 5 2 7 0 2 7 1 6
0 7 3 0 1 7 6 2 0 0 4 2 4 1 3 3 7 0 1 3
0 5 2 2 1 4 6 3 7 0 6 3 5 0 0 6 4 4 2 1
0 1 2 4 5 6 0 2 0 0 5 0 2 4 6 4 7 6 3 7
7 7 4 2 3 0 0 4 0 0 7 2 7 5 6 1 4 5 5 4
20 20 13 11 13 //WRONG
0 0 0 1 4 4 4 0 0 0 0 0 0 0 0 1 2 3 1 0
0 0 0 0 0 0 0 0 0 0 0 0 4 2 7 7 2 0 1 1
0 0 0 0 0 0 0 0 0 6 2 4 4 2 0 4 7 0 6 0
0 0 0 7 5 5 3 0 0 7 5 0 5 6 4 2 6 3 1 5
0 0 0 1 2 6 3 3 7 0 3 6 2 4 5 6 7 7 5 7
0 0 0 3 7 6 1 5 3 3 4 5 7 6 0 4 3 3 1 1
0 1 2 1 5 6 1 6 1 6 5 1 6 0 0 3 4 1 7 6
0 2 3 2 2 7 3 0 0 3 2 5 2 1 0 6 5 1 6 5
0 2 5 7 0 7 1 3 3 4 1 3 3 0 2 3 3 2 4 1
4 0 0 7 2 4 2 2 1 3 1 6 5 5 6 2 5 1 1 6
5 6 4 0 3 6 5 2 2 6 1 2 0 1 7 5 7 2 2 2 
1 6 3 1 4 4 1 0 3 0 4 2 7 2 0 2 3 6 2 5
1 5 7 2 1 1 4 4 2 1 0 2 7 1 6 2 6 6 2 2
3 7 0 6 5 0 4 0 6 6 7 1 3 1 1 1 5 1 6 6
0 4 0 1 6 2 1 0 7 0 4 2 5 2 7 0 2 7 1 6
0 7 3 0 1 7 6 2 0 0 4 2 4 1 3 3 7 0 1 3
0 5 2 2 1 4 6 3 7 0 6 3 5 0 0 6 4 4 2 1
0 1 2 4 5 6 0 2 0 0 5 0 2 4 6 4 7 6 3 3
7 7 4 2 3 0 0 4 0 0 7 2 7 5 6 1 4 5 5 4
*/
import java.util.*;

public class Solution {
    
    static void observe(int N,int M,int eN,int eM,int eLen, int[][] pipe,int[][] visited,int[][] path){
        //System.out.println("sss"+eN+" "+eM);
        visited[eN][eM] = 1;
        //up down left right
        int[][][] move = {
            {},
            {{-1,0},{1,0},{0,-1},{0,1}},
            {{-1,0},{1,0},{0,0},{0,0}},
            {{0,0},{0,0},{0,-1},{0,1}},
            {{-1,0},{0,0},{0,0},{0,1}},
            {{0,0},{1,0},{0,0},{0,1}},
            {{0,0},{1,0},{0,-1},{0,0}},
            {{-1,0},{0,0},{0,-1},{0,0}},
        };

        int[][] checkOut = {
            {},
            {1,1,2,2},
            {1,1,0,0},
            {0,0,2,2},
            {1,0,0,2},
            {0,1,0,2},
            {0,1,2,0},
            {1,0,2,0}
        };

        int[][] checkIn = {
            {},
            {1,1,2,2},
            {1,1,0,0},
            {0,0,2,2},
            {0,1,2,0},
            {1,0,2,0},
            {1,0,0,2},
            {0,1,0,2}
        };

        if(eLen<=1){
            return;
        }
        
        
        int pathType = pipe[eN][eM]; 
        for(int i=0;i<move[pathType].length;i++){
            int nN = move[pathType][i][0];
            int nM = move[pathType][i][1];
            //System.out.println("b"+(eN+nN)+" "+(eM+nM));
            if( (0<=eN+nN && eN+nN<N) &&  (0<=eM+nM && eM+nM<M)  ) {
                int nextPathType = pipe[eN+nN][eM+nM]; 
                //System.out.println("c"+(eN+nN)+" "+(eM+nM));
                if(path[eN+nN][eM+nM]==0 && pipe[eN+nN][eM+nM] != 0 && checkOut[pathType][i] == checkIn[nextPathType][i] ){
                    path[eN+nN][eM+nM]=1;
                    //System.out.println("k"+(eN+nN)+" "+(eM+nM));
                    observe(N,M,eN+nN,eM+nM,eLen-1,pipe,visited,path);
                    path[eN+nN][eM+nM]=0;
                }
            }
                
            
        }

    }

  
    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */

        Scanner scanner = new Scanner(System.in);
        int TC = scanner.nextInt();

        for(int tc=0;tc<TC;tc++){

            int N = scanner.nextInt();
            int M = scanner.nextInt();
            int eN = scanner.nextInt();
            int eM = scanner.nextInt();
            int eLen = scanner.nextInt(); 

            int[][] pipe = new int[N][M];

            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    pipe[i][j] = scanner.nextInt();
                }
            }

            int[][] visited = new int[N][M];
            int[][] path = new int[N][M];
            
            observe(N,M,eN,eM,eLen,pipe,visited,path);
            int totalType = 0;
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    totalType += visited[i][j];
                }
            }
            System.out.println(totalType);

        }

    }
}
