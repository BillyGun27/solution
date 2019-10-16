import java.util.*;

public class Solution {

    static void searchSpot(int[][] Gate,int TotalSpot,int[] visitedSpot,int[] visitedGate){
        
        for(int i=0;i<3;i++){
            int fish = 0;
            int pos = 0;

            if(visitedSpot[Gate[i][0]]==0){
                visitedSpot[ Gate[i][0] ] = pos+1;
                fish++;
            }
            pos++;
            
            while(fish<Gate[i][1]){
                if( ( (Gate[i][0] + pos)<=TotalSpot ) && fish<Gate[i][1] && visitedSpot[Gate[i][0] + pos ]==0 ){
                        visitedSpot[ Gate[i][0] + pos ] = pos+1;
                        fish++;
                }

                if( ( 0<(Gate[i][0] - pos) ) && fish<Gate[i][1] && visitedSpot[Gate[i][0] - pos ]==0){
                        visitedSpot[ Gate[i][0] - pos ] = pos+1;
                        fish++;
                }

                pos++;
            }
        }

        for(int i=1;i<visitedSpot.length;i++){
            System.out.print(visitedSpot[i]+" ");
        }

    }


    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */

        Scanner scanner = new Scanner(System.in);

        int TotalSpot = scanner.nextInt();
        int[][] Gate = new int[3][2];

        for(int i=0;i<3;i++){
            for(int j=0;j<2;j++){
                Gate[i][j] = scanner.nextInt();
            }
        }
        int[] visitedSpot = new int[TotalSpot+1];
        int[] visitedGate = new int[3];
        searchSpot(Gate,TotalSpot,visitedSpot,visitedGate);
        System.out.println(Gate[0][0]); 
    }
}
