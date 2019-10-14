import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

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
        
        Point p = new Point(5,11);
        Point q = new Point(10,11);
        Point r = new Point(5,12);
        Point s = new Point(5,10);
        
        //not intesect
        /*Point p = new Point(0,5);
        Point q = new Point(10,7);
        Point r = new Point(6,5);
        Point s = new Point(5,1);*/
        
        /*Point p = new Point(5,11);
        Point q = new Point(10,11);
        Point r = new Point(7,10);
        Point s = new Point(2,10);*/
        
        System.out.println(intersect(p,q,r,s));
    }
}
