/*
5
10
4 5
6 2
10 2
10
8 5
9 1
10 2
24
15 3
20 4
23 7
39
17 8
30 5
31 9
*/

import java.util.*;

public class Solution {

    static void searchSpot(int[][] Gate,int TotalSpot,int[] visitedSpot,int[] visitedGate, String[] orderText , int dist){
        if(dist >= 3){
            for(int i=0;i<3;i++){
                System.out.print(orderText[i]+" ");
            }
            System.out.println(" finish");
            return;
        }
        

        for(int i=0;i<3;i++){
            if(visitedGate[i] == 0){

                visitedGate[i] = 1;
                /*
                if( Gate[i][1] % 2 == 0 ){
                    System.out.println(current+" "+i+" right left");
                }else{
                    System.out.println(current+" "+i);
                }*/
                orderText[dist] = i+"r";
                searchSpot(Gate,TotalSpot,visitedSpot,visitedGate,orderText,dist+1);

                orderText[dist] = i+"l";
                searchSpot(Gate,TotalSpot,visitedSpot,visitedGate,orderText,dist+1);
                visitedGate[i] = 0;

            }

            /*
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
            */

        }
        
        /*
        for(int i=1;i<visitedSpot.length;i++){
            System.out.print(visitedSpot[i]+" ");
        }*/

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
        String[] orderText = new String[3];
        int dist = 0 ;
        searchSpot(Gate,TotalSpot,visitedSpot,visitedGate,orderText, dist);
        System.out.println(Gate[0][0]); 
    }
}
