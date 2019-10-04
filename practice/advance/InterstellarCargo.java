import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static int shortest;
    static void shortestWormhole(int[] start,int[] finish,int[][] hole,int current,int set,int[] visited,int distance){
        //check finish
        int toFinish =  Math.abs(finish[0] - hole[current][set]) + Math.abs(finish[1] - hole[current][set+1]);
        if( distance+toFinish < shortest ){
            //System.out.println("f"+current+" "+(distance+toFinish));
            shortest = distance+toFinish;
        }/*else{
            return;
        }*/
        
        //try other wormhole
        for(int i=0;i<hole.length;i++){
            int nextHoleA  = Math.abs(hole[i][0] - hole[current][set]) + Math.abs(hole[i][1] - hole[current][set+1]);
            int nextHoleB  = Math.abs(hole[i][2] - hole[current][set]) + Math.abs(hole[i][3] - hole[current][set+1]);
            /*
            set=0;//BtoA
            int shortestDistance = nextHoleB;
            if(nextHoleA<nextHoleB ){
                set=2;//AtoB
                shortestDistance = nextHoleA;
            }*/
            if( visited[i]==0 ){//&& ((distance+shortestDistance)<shortest)
                //System.out.println("c "+current+" "+i+" "+(distance+nextHole)+" "+Arrays.toString(visited));
                visited[i] = 1;
                //shortestWormhole(start,finish,hole,i,set,visited,distance+shortestDistance);
                if((distance+nextHoleB)<shortest){
                    shortestWormhole(start,finish,hole,i,0,visited,distance+nextHoleB);
                }
                
                if((distance+nextHoleA)<shortest){
                    shortestWormhole(start,finish,hole,i,2,visited,distance+nextHoleA);
                }
                
                visited[i] = 0;
            }
        }
        
        return;
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        
        int[] start = new int[2];
        int[] finish = new int[2];
        
        for(int i=0;i<2;i++){
            start[i] = scanner.nextInt();
        }
        
        for(int i=0;i<2;i++){
            finish[i] = scanner.nextInt();
        }
        
        int n = scanner.nextInt();
        int[][] hole = new int[n][4];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<4;j++){
                hole[i][j] = scanner.nextInt();   
            }
        }
        
        shortest = Math.abs(finish[0] - start[0]) + Math.abs(finish[1] - start[1]);
        //System.out.println(start[0]+" "+finish[0]+" "+n+" "+hole[0][0]+" "+hole[1][3]);
        int[] visited = new int[n];
        for(int i=0;i<n;i++){
            int distanceA  = Math.abs(hole[i][0] - start[0]) + Math.abs(hole[i][1] - start[1]);
            int distanceB  = Math.abs(hole[i][2] - start[0]) + Math.abs(hole[i][3] - start[1]);
            /*
            int set=0;
            int shortestDistance = distanceB;
            
            if(distanceA<distanceB){
                set=2;
                shortestDistance = distanceA;
            }*/
            visited[i] = 1;
            //System.out.println("st "+i+" "+Arrays.toString(visited));
            //shortestWormhole(start,finish,hole,i,set,visited, shortestDistance);
            shortestWormhole(start,finish,hole,i,0,visited, distanceB);
            shortestWormhole(start,finish,hole,i,2,visited, distanceA);
            visited[i] = 0;
        }
        
        System.out.println(shortest);
        
    }
}
