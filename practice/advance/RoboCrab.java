/*
7
3 1
1 2
4 4
2 4
3 4
1 2
3 3

20
3 9
1 77
4 99
2 68
4 41
1 82
3 61
1 28
4 26
2 26
3 6
1 2
3 19
1 5
4 16
1 4
3 82
2 16
4 96
1 24

30
4 23
1 44
3 42
2 68
3 17
1 72
3 62
1 74
4 11
1 26
4 40
1 29
3 41
1 52
4 36
1 37
4 54
2 29
4 87
1 55
4 90
2 81
4 66
2 99
4 7
1 84
3 54
1 94
4 33
2 11

ans
-1
17
27
*/
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static int stop;
    public static boolean checkIntersect(int ax,int ay,int bx,int by,int cx,int cy,int dx,int dy){
        boolean ins=false;
        if(ay==by){
            //System.out.print(" ay==by");
            if( (Math.min(ax,bx) < cx && cx < Math.max(ax,bx)) && (Math.min(cy,dy) < ay && ay < Math.max(cy,dy)) ){
               // System.out.println("nope it was me");
                ins = true;
            }
        }
        
        if(ax==bx){
           // System.out.print(" ax==bx"+(Math.min(ay,by) < cy && cy < Math.min(ay,by)) +" "+(Math.min(cx,dx) < ax && ax < Math.max(cx,dx)));
            if( (Math.min(ay,by) < cy && cy < Math.max(ay,by)) && (Math.min(cx,dx) < ax && ax < Math.max(cx,dx)) ){
                //System.out.println("it was me");
                ins = true;
            }
        }
        
        if( Math.min(ay,by)==Math.min(cy,dy) && Math.max(ay,by)==Math.max(cy,dy) ){
           //System.out.println("ysame");
            if(Math.min(ax,bx) < cx && cx < Math.max(ax,bx)){
                //System.out.println("cx");
                ins = true;
            }else if(Math.min(ax,bx) < dx && dx < Math.max(ax,bx)){
                //System.out.println("dx");
                ins = true;
            }else if(Math.min(cx,dx) < ax && ax < Math.max(cx,dx) ){
                //System.out.println("ax");
                ins = true;
            }else if( Math.min(cx,dx) < bx && bx < Math.max(cx,dx) ){
                //System.out.println("bx");
                ins = true;
            }else if(Math.min(ax,bx) == Math.min(cx,dx) && Math.max(ax,bx) == Math.max(cx,dx)){
                //System.out.println("faily");
                ins = true;
            }
        }
        
         if(Math.min(ax,bx)==Math.min(cx,dx) && Math.max(ax,bx)==Math.max(cx,dx)){
             //System.out.println("xsame");
            if(Math.min(ay,by) < cy && cy < Math.max(ay,by)){
               // System.out.println("cy");
                ins = true;
            }else if( Math.min(ay,by) < dy && dy < Math.max(ay,by)){
               // System.out.println("dy");
                ins = true;
            }else if( Math.min(cy,dy) < ay && ay < Math.max(cy,dy)){
               // System.out.println("ay");
                ins = true;
            }else if( Math.min(cy,dy)< by && by < Math.max(cy,dy)){
               // System.out.println("by");
                ins = true;
            }else if(Math.min(ay,by) == Math.min(cy,dy) && Math.max(ay,by) == Math.max(cy,dy) ){
                //System.out.println("ally");
                ins = true;
            }
        }
        
        return ins;
    }
    
     public static void robotMove(int M,int[] dir,int[] len,int[][] coord ,int pos){   
            
            if(pos>=3 && stop == -1){
                int ax = coord[pos-1][0];
                int ay = coord[pos-1][1];
                int bx = coord[pos-2][0];
                int by = coord[pos-2][1];
                
                for(int i=0;i<pos-2;i++){
                    int cx = coord[i][0];
                    int cy = coord[i][1];
                    int dx = coord[i+1][0];
                    int dy = coord[i+1][1];
                    boolean intersect = checkIntersect(ax,ay,bx,by,cx,cy,dx,dy);
                    
                    if(intersect){
                        //System.out.println((pos-1)+"p "+ax+","+ay+" "+bx+","+by+" "+cx+","+cy+" "+dx+","+dy);
                        if(stop == -1 ){
                             stop = pos-1;
                        }
                        break;
                    }
                }
            }
         
            if(pos<M+1){
                if(dir[pos-1] == 1){
                        coord[pos][0] = coord[pos-1][0];
                        coord[pos][1] = coord[pos-1][1]+len[pos-1];
                }else if(dir[pos-1] == 2){
                        coord[pos][0] = coord[pos-1][0];
                        coord[pos][1] = coord[pos-1][1]-len[pos-1];
                }else if(dir[pos-1] == 3){
                        coord[pos][0] = coord[pos-1][0]+len[pos-1];
                        coord[pos][1] = coord[pos-1][1];
                }else if(dir[pos-1] == 4){
                        coord[pos][0] = coord[pos-1][0]-len[pos-1];
                        coord[pos][1] = coord[pos-1][1];
                }
                
                robotMove(M,dir,len,coord,pos+1);
            }
         
     }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        
        int M = sc.nextInt();
        int[] dir = new int[M];
        int[] len = new int[M];
        
        for(int i=0;i<M;i++){
            dir[i] = sc.nextInt();
            len[i] = sc.nextInt();
        }
        
        int[][] coord = new int[M+1][2];
        coord[0][0] = 0;//x
        coord[0][1] = 0;//y
        stop = -1;
        robotMove(M,dir,len,coord,1);
        //for(int i=0;i<M+1;i++){
        //    System.out.println(coord[i][0]+" "+coord[i][1]);
        //}
        System.out.println(stop);
        
        //System.out.println( checkIntersect(2,1,2,3,2,3,2,1) );
    }
}
