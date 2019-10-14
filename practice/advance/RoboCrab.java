import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

//sample
/*
4
3 4
1 2
4 2
2 4

5
3 2
1 3
4 4
2 5
3 7

6
3 3
1 2
4 4
2 3
3 4
1 4
*/
public class Solution {
    static class Point{
        int x;
        int y;
        Point(int xx, int yy){
            x = xx;
            y = yy;
        }
    }
    
    static int orient(Point l1,Point l2,Point p){
        int val = ( (l2.y-l1.y) * (p.x-l2.x) ) - ( (l2.x-l1.x) * (p.y-l2.y) ) ; 
        if(val == 0){
            return 0;
        }else if(0 < val){
            return 1;
        }else{
            return -1;
        }
    }
    
    static boolean intersect(Point p,Point q,Point r,Point s){
         return orient(p,q,r) != orient(p,q,s) && orient(r,s,p) != orient(r,s,q) ;
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        
        //example
        //intersect
        /*Point p = new Point(1,5);
        Point q = new Point(4,2);
        Point r = new Point(4,4);
        Point s = new Point(1,1);*/
        
        /*Point p = new Point(5,11);
        Point q = new Point(10,11);
        Point r = new Point(5,12);
        Point s = new Point(5,10);*/
        
        //not intesect
        /*Point p = new Point(0,5);
        Point q = new Point(10,7);
        Point r = new Point(6,5);
        Point s = new Point(5,1);*/
        
        /*Point p = new Point(5,11);
        Point q = new Point(10,11);
        Point r = new Point(7,10);
        Point s = new Point(2,10);*/
        
        //System.out.println(intersect(p,q,r,s));
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int[][] command = new int[N][2];
        Point[] path = new Point[N+1];
        path[0] = new Point(0,0);
        for(int i=0;i<N;i++){
            command[i][0] = scanner.nextInt();
            command[i][1] = scanner.nextInt();
        }
        
        int progress = 0 ;
        //N=1 S=2 E=3 W=4
        int crash = -1;
        
        for(int i=0;i<N;i++){
            //add path
            if(command[i][0] == 1){//N
                path[i+1] = new Point(path[i].x, path[i].y + command[i][1] );
            }else if(command[i][0] == 2){//S
                path[i+1] = new Point(path[i].x, path[i].y - command[i][1] );
            }else if(command[i][0] == 3){//E
                path[i+1] = new Point(path[i].x + command[i][1] , path[i].y );
            }else if(command[i][0] == 4){//W
                path[i+1] = new Point(path[i].x - command[i][1] , path[i].y );
            }
            //System.out.println(path[i+1].x + " " + path[i+1].y);
            if(3 <= i){
                int prev = i-2 ; 
                //-3 //-2
                //System.out.println(prev);
               
                        while(0 < prev){
                        //System.out.println(path[i].x + " " + path[i].y+" x "+path[i+1].x + " " + path[i+1].y+" | "+" "+path[prev-1].x + " " + path[prev-1].y+" x "+path[prev].x + " " + path[prev].y);
                            if(intersect(path[i],path[i+1],path[prev-1],path[prev])){
                                crash = i+1;
                                break;
                            }
                        
                            prev-=2;
                        }        
                    
                
            }
            
            if(crash != -1){
                break;
            }
            
        }
        
        System.out.println(crash);
        
    }
}
