/*
4
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
60
57 12
31 19
38 16
*/

import java.util.*;

public class Solution {
    static int shortest ;
    static void SpotPlacement(int[][] Gate,int TotalSpot,int[] visitedSpot, int current , int lastchoice){//lastchoice 0=do nothing 1=right 2=left
        
            int fish = 0;
            int pos = 0;
            if(visitedSpot[Gate[current][0]]==0){
                visitedSpot[ Gate[current][0] ] = pos+1;
                fish++;
            }
            pos++;
            
            while(fish<Gate[current][1]){
                if( fish == Gate[current][1]-1 ){
                    //System.out.println(current+" "+Gate[current][0]+" "+pos);
                    if(lastchoice == 1 && (Gate[current][0] + pos)<=TotalSpot  && visitedSpot[Gate[current][0] + pos ]==0){
                        visitedSpot[ Gate[current][0] + pos ] = pos+1;
                        fish++;
                    }
                    
                    if(lastchoice == 2 && 0<(Gate[current][0] - pos)  && visitedSpot[Gate[current][0] - pos ]==0){
                        visitedSpot[ Gate[current][0] - pos ] = pos+1;
                        fish++;
                    }
                }

                if( ( (Gate[current][0] + pos)<=TotalSpot ) && fish<Gate[current][1] && visitedSpot[Gate[current][0] + pos ]==0 ){
                        visitedSpot[ Gate[current][0] + pos ] = pos+1;
                        fish++;
                }
                if( ( 0<(Gate[current][0] - pos) ) && fish<Gate[current][1] && visitedSpot[Gate[current][0] - pos ]==0){
                        visitedSpot[ Gate[current][0] - pos ] = pos+1;
                        fish++;
                }
                pos++;
            }
            
            
       // for(int i=1;i<visitedSpot.length;i++){
        //    System.out.print(visitedSpot[i]+" ");
        //} System.out.println(" ");
    }


    static void searchSpot(int[][] Gate,int TotalSpot,int[] visitedSpot,int[] visitedGate, int dist){
        if(dist >= 3){
            int total = 0;
            for(int i=1;i<visitedSpot.length;i++){
                total+=visitedSpot[i];
            } 
            //System.out.println(total);
            //System.out.println(" finish");
            if(total<shortest){
                shortest = total ;
            }
            return;
        }
        

        for(int i=0;i<3;i++){
            if(visitedGate[i] == 0){
                //System.out.println(i);
                visitedGate[i] = 1;
               
                if( Gate[i][1] % 2 == 0 ){
                    int[] leftvisited = visitedSpot.clone();
                    SpotPlacement(Gate,TotalSpot,leftvisited, i , 2);
                    searchSpot(Gate,TotalSpot,leftvisited,visitedGate,dist+1);

                    int[] rightvisited = visitedSpot.clone();
                    SpotPlacement(Gate,TotalSpot,rightvisited, i , 1);
                    searchSpot(Gate,TotalSpot,rightvisited,visitedGate,dist+1);
                }else{
                    int[] centervisited = visitedSpot.clone();
                    SpotPlacement(Gate,TotalSpot,centervisited, i , 0);
                    searchSpot(Gate,TotalSpot,centervisited,visitedGate,dist+1);
                }
                visitedGate[i] = 0;

            }


        }
        
        

    }


    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */

        Scanner scanner = new Scanner(System.in);
        int CC = scanner.nextInt();

        for(int ck=0;ck<CC;ck++){
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
            shortest = TotalSpot*TotalSpot;
            searchSpot(Gate,TotalSpot,visitedSpot,visitedGate,dist);
            
            System.out.println(shortest); 

        }

    }
}
