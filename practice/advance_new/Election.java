/*
5
5
1 3 3 2 3 
3 2 1 1 1 
1 1 2 2 1 
3 2 1 3 1 
3 2 3 3 2 
8
1 3 3 3 2 2 2 3 
3 1 3 2 1 3 3 3 
2 3 3 1 1 2 3 1 
1 2 3 2 2 2 3 3 
2 1 3 3 2 1 2 3 
1 1 3 1 3 1 1 2
1 2 1 2 1 3 1 2 
2 3 2 2 1 3 3 1 
10
4 3 2 2 3 1 4 3 1 4 
1 2 1 2 2 1 3 3 1 2 
1 4 3 1 2 2 4 4 3 3 
3 2 3 2 3 3 3 3 4 3 
1 1 2 4 4 3 2 2 4 4 
2 1 4 2 4 1 1 2 1 1 
1 1 4 4 4 2 1 1 1 3 
2 1 1 1 1 3 3 4 2 3 
4 1 3 1 1 2 2 3 1 4 
1 2 4 3 1 2 4 3 3 1 
13
3 2 1 1 4 1 2 3 2 1 4 4 1 
3 3 2 3 4 4 4 1 3 3 2 1 1 
3 2 4 4 4 2 4 1 4 2 1 1 1 
3 4 2 2 1 4 1 4 4 4 3 3 1 
4 4 1 3 4 3 2 3 2 4 1 1 4 
2 2 4 4 1 4 2 2 3 1 3 3 4 
3 2 3 4 1 1 2 4 3 1 3 1 2 
2 1 4 4 4 1 1 2 2 3 3 2 1 
4 3 4 1 4 4 4 3 1 2 2 2 4 
2 4 4 2 2 3 4 2 2 4 1 4 1 
1 4 1 4 4 2 2 3 4 4 2 1 2 
4 4 3 1 4 4 4 4 4 1 3 4 3 
3 4 2 2 2 4 2 3 2 3 2 4 4 
20
2 1 4 4 1 5 4 1 3 4 3 4 3 5 3 1 2 2 4 4 
1 3 5 4 5 3 1 1 4 4 3 3 1 1 1 1 3 2 5 1 
5 2 2 3 4 5 4 5 5 3 3 1 2 3 4 3 5 1 4 1 
1 1 1 1 2 1 1 1 3 2 5 4 5 2 2 2 2 1 2 2 
2 5 3 2 2 4 1 3 2 3 5 4 2 4 2 4 5 5 1 1 
1 1 1 1 1 1 4 4 5 2 1 1 4 4 2 2 2 4 3 2 
2 2 3 3 5 1 1 4 3 4 5 3 4 1 4 3 2 2 2 2 
5 4 4 4 3 4 5 1 3 1 4 1 1 3 2 5 5 1 5 3 
5 3 4 3 2 3 2 3 3 5 4 1 5 5 3 5 2 2 1 2 
4 1 3 4 5 4 4 1 1 4 5 5 4 2 4 2 1 2 1 4 
1 1 2 2 3 3 2 1 5 1 4 3 5 2 1 5 2 1 3 4 
2 4 2 3 2 2 3 1 2 3 1 4 2 5 1 1 1 3 3 5 
2 2 4 1 5 2 3 3 1 2 2 5 4 4 1 5 5 4 3 4 
2 3 3 5 4 4 3 4 5 2 3 3 3 3 4 3 5 2 5 1 
5 1 4 5 4 4 1 1 5 2 1 4 2 4 2 4 2 1 3 4 
5 3 2 1 1 4 2 5 1 1 2 4 5 4 4 5 5 4 2 1 
2 2 3 1 2 4 4 1 1 3 1 5 3 3 2 1 1 3 4 1 
5 1 3 1 2 4 4 3 1 4 2 3 3 1 2 2 3 5 5 3 
1 3 5 3 2 2 2 5 2 3 5 1 2 5 2 4 5 3 3 4 
3 1 2 4 5 1 3 5 4 4 2 4 5 5 1 5 4 5 2 5

ans
3
2
19
0
42
*/
import java.util.*;

public class Solution {
    static int winnerFirstTotal;
    static boolean checkAllowed(int[] constit){
        int max=0,min=500;
        for(int i=0;i<3;i++){
            if(max<constit[i]){
                max = constit[i];
            }
            if(min>constit[i]){
                min = constit[i];
            }
        }

        if(max <= min*2){
            return true;
        }else{
            return false;
        }
    }
    static int checkWinner(int N,int[][] town,int[][] coordStart,int[][] coordEnd){
        int[][] vote = new int[3][5];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if((coordStart[0][0]<=i && i<coordEnd[0][0]) && (coordStart[0][1]<=j && j<coordEnd[0][1]) ){
                    vote[0][ town[i][j]-1 ] = vote[0][ town[i][j]-1 ] + 1;
                    //System.out.print("|1"+town[i][j]+" "+i+" "+j+"|");
                }else if( (coordStart[1][0]<=i && i<coordEnd[1][0]) && (coordStart[1][1]<=j && j<coordEnd[1][1]) ){
                     vote[1][ town[i][j]-1 ] = vote[1][ town[i][j]-1 ] + 1;
                }else if( (coordStart[2][0]<=i && i<coordEnd[2][0]) && (coordStart[2][1]<=j && j<coordEnd[2][1]) ){
                     vote[2][ town[i][j]-1 ] = vote[2][ town[i][j]-1 ] + 1;
                }
            }
        }
        //System.out.println( coordStart[1][0]+" "+coordEnd[1][0]+" "+coordStart[1][1]+" "+coordEnd[1][1] );

        int winConsFirst = 0;
       
        for(int c=0;c<3;c++){
            //System.out.print(vote[c][0]+" ");
            boolean win = true;
            for(int e=1;e<5;e++){
                //System.out.print(vote[c][e]+" ");
                if(vote[c][0] <=  vote[c][e]){
                    win = false;
                }
            }
            
            if(win){
                   winConsFirst++; 
            }
            //System.out.print(win+"|");
        }
        //System.out.println(" n");
        
        if(winConsFirst>1){
            return 1;
        }else{
            return 0;
        }
        
    }
    static void split(int N,int[][] town,int h,int w){
        int[] constit = new int[3];
        boolean allowed;
        //int winnerFirst = 0;
        int[][][] coordStart = {
            { {0,0},{h,0},{h,w} },
            { {0,0},{0,w},{h,0} },
            { {0,0},{0,w},{h,w} },
            { {0,0},{h,0},{0,w} }
        };

        int[][][] coordEnd = {
            { {h,N},{N,w},{N,N} },
            { {h,w},{h,N},{N,N} },
            { {N,w},{h,N},{N,N} },
            { {h,w},{N,w},{N,N} }
        };
        
        //i j -
        constit[0] = h * N;
        constit[1] = (N-h) * w;
        constit[2] = (N-h) * (N-w);
        
        allowed = checkAllowed(constit);
        if(allowed){
            winnerFirstTotal += checkWinner(N,town,coordStart[0],coordEnd[0]);
            //System.out.println("four = "+constit[0]+" "+constit[1]+" "+constit[2]+" "+h+" "+w);
        }
        
        
        //i j -
        constit[0] = h * w;
        constit[1] = h * (N-w);
        constit[2] = (N-h) * N;

        allowed = checkAllowed(constit);
        if(allowed){
            winnerFirstTotal += checkWinner(N,town,coordStart[1],coordEnd[1]);
        //System.out.println("four = "+constit[0]+" "+constit[1]+" "+constit[2]+" "+h+" "+w);
        }

        //i j |
        constit[0] = N * w;
        constit[1] = h * (N-w);
        constit[2] = (N-h) * (N-w);

        allowed = checkAllowed(constit);
        if(allowed){
           winnerFirstTotal += checkWinner(N,town,coordStart[2],coordEnd[2]);  
        //System.out.println("four | "+constit[0]+" "+constit[1]+" "+constit[2]+" "+h+" "+w);
        }

        //i j |
        constit[0] = h * w;
        constit[1] = (N-h) * w;
        constit[2] = N * (N-w);

        allowed = checkAllowed(constit);
        if(allowed){
             winnerFirstTotal += checkWinner(N,town,coordStart[3],coordEnd[3]);
        //System.out.println("four | "+constit[0]+" "+constit[1]+" "+constit[2]+" "+h+" "+w);
        }
        
      
    }

    static void splitThree(int N,int[][] town,int i,int j){
        int[] constit = new int[3];
        boolean allowed;
        int[][][] coordStart = {
            { {0,0},{i,0},{i+j,0} },
            { {0,0},{0,i},{0,i+j} }
        };

        int[][][] coordEnd = {
            { {i,N},{i+j,N},{N,N} },
            { {N,i},{N,i+j},{N,N} }
        };

        // =
        constit[0] = i * N;
        constit[1] = j * N;
        constit[2] = (N-(i+j)) * N;

        allowed = checkAllowed(constit);
        if(allowed){
            winnerFirstTotal += checkWinner(N,town,coordStart[0],coordEnd[0]);
            //System.out.println("three || "+constit[0]+" "+constit[1]+" "+constit[2]+" "+i+" "+j);
        }
        //||
        constit[0] = N * i;
        constit[1] = N * j;
        constit[2] = N * (N-(i+j));
        
        allowed = checkAllowed(constit);
        if(allowed){
            winnerFirstTotal += checkWinner(N,town,coordStart[1],coordEnd[1]);
            //System.out.println("three = "+constit[0]+" "+constit[1]+" "+constit[2]+" "+i+" "+j);    
            }
        
    }

    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int t=0;t<T;t++){
           int N = sc.nextInt();
           int[][] town = new int[N][N];
           for(int i=0;i<N;i++){
               for(int j=0;j<N;j++){
                   town[i][j] = sc.nextInt();
               }
           }

            winnerFirstTotal = 0;
            for(int i=1;i<N;i++){
                for(int j=1;j<N;j++){
                    split(N,town,i,j);
                    if( i < ((N/2)+1) && j < ((N/2)+1) ){
                        splitThree(N,town,i,j);
                    }
                    
                }
            }

            System.out.println(winnerFirstTotal);
            
        }


    }
}
