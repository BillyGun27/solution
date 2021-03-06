/*
3
3 3 2 4
1 1 1
1 0 2
1 1 0
2 10 10 10
3 2 2
1 1 1
2 10 10 10
4 2 2
1 1 3
*/

import java.util.*;

public class Solution {
    static int Max;

    static void Craft(int TotalDec,int R,int B,int Y,int[][] decModel,int current, int total){

        if(current == TotalDec){
            if(Max < total){
                Max = total;
            }

            return;
        }
                    
        if(current<TotalDec){
            for(int i=0;i<=3;i++){
                int Rl = R - decModel[current][0]*i; 
                int Bl = B - decModel[current][1]*i; 
                int Yl = Y - decModel[current][2]*i; 
                
                if( Rl>=0 && Bl>=0 && Y>=0 ){
                    Craft(TotalDec, Rl,Bl,Yl, decModel, current+1, total+i);
                }
                
            }
        }
    }
    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        for(int ck=0;ck<N;ck++){
            int TotalDec = scanner.nextInt();
            int R = scanner.nextInt();
            int B = scanner.nextInt();
            int Y = scanner.nextInt();

            int[][] decModel = new int[TotalDec][3];

            for(int i=0;i<TotalDec;i++){
                for(int j=0;j<3;j++){
                    decModel[i][j] = scanner.nextInt();
                }
            }

            Max = 0;
            int[] box = new int[3];
            Craft(TotalDec, R,B,Y, decModel, 0, 0);
            System.out.println(Max);

        }

    }
}
