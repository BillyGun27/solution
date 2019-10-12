import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    static class spot{
        int value;
        int order;
    }
    static class point{
        int y;
        int x;
    }
    static int minDistance(int[] visited, int[] diff){
        int mindiff = Integer.MAX_VALUE;
        int point = 0;
        
        for(int i=0;i<diff.length;i++){
                if(diff[i]<mindiff && visited[i]==0){
                    mindiff = diff[i];
                    point = i;
                }
        }
        
        return point;
    }
    
    static spot getUpDiffY(spot[][] stage, point[] step, int current, int y, int[] visited, int[] diff){
     
        int cy = 1;
        spot nd = new spot();
        
        while(y <= step[current].y-cy){//y->0
            int val = stage[ step[current].y-cy ][ step[current].x ].value;
            int pos = stage[ step[current].y-cy ][ step[current].x ].order;

            if(  val != 0 && visited[pos] == 0 ){
                int nextDiff = cy;
                if( nextDiff < diff[current]){
                    nextDiff = diff[current];
                }

                if( nextDiff < diff[pos] ){
                    nd.value = nextDiff;
                    nd.order = pos; 
                }
                //System.out.println("Up val="+val+" next="+nextDiff+"/"+nd.value+" pos="+pos+"/"+nd.order);
                return nd; 
            }
            
            cy++;
            
        }
        
        nd.value = -1;
        nd.order = -1; 
        
        return nd;
    }
    
    static spot getDownDiffY(spot[][] stage, point[] step, int current, int y, int[] visited, int[] diff){
        
        int cy = 1;
        spot nd = new spot();
        
        while( step[current].y+cy <y ){//y->max y
            int val = stage[ step[current].y+cy ][ step[current].x ].value;
            int pos = stage[ step[current].y+cy ][ step[current].x ].order;

            if(  val != 0 && visited[pos] == 0 ){
                int nextDiff = cy;
                if( nextDiff < diff[current]){
                    nextDiff = diff[current];
                }

                if( nextDiff < diff[pos] ){
                    nd.value = nextDiff;
                    nd.order = pos; 
                }
                //System.out.println("Down val="+val+" next="+nextDiff+"/"+nd.value+" pos="+pos+"/"+nd.order);
                return nd; 
            }
            
            cy++;
            
        }
        
            nd.value = -1;
            nd.order = -1; 

            return nd;
        
    }
    
    static spot getDiffX(spot[][] stage, point[] step, int current,int xx , int[] visited, int[] diff){
        spot nd = new spot();
        int val = stage[ step[current].y ][ step[current].x+xx ].value;
        int pos = stage[ step[current].y ][ step[current].x+xx ].order;
        
        if(  val != 0 && visited[pos] == 0 ){
            int nextDiff = 1;
            if( nextDiff < diff[current]){
                nextDiff = diff[current];
            }

            if( nextDiff < diff[pos] ){
                nd.value = nextDiff;
                nd.order = pos; 
            }
            //System.out.println("LR val="+val+" next="+nextDiff+"/"+nd.value+" pos="+pos+"/"+nd.order);
            return nd;
        }
        
        nd.value = -1;
        nd.order = -1; 

        return nd;
        
    }
    
    static int climbing(spot[][] stage,point[] step, int totalStep,int y, int x, int start,int goal ){
        int[] visited = new int[totalStep];
        int[] diff = new int[totalStep];
        
        //all infinite
        for(int i=0;i<totalStep;i++){
            diff[i] = Integer.MAX_VALUE;   
        }
        diff[start] = 0;
        
        for(int i=0;i<totalStep;i++){
                int current = minDistance(visited,diff);
                visited[current] = 1;

                spot nd = new spot();
                //System.out.println("x="+step[current].x+" y="+step[current].y+" c="+current+" diff="+diff[current]);
            
                //up -1 0
                 nd = getUpDiffY(stage, step, current , 0, visited , diff);
                 if(nd.order != -1){
                     diff[nd.order] = nd.value; 
                     // System.out.println("up x="+step[nd.order].x+" y="+step[nd.order].y+" c="+nd.order+" diff="+diff[nd.order]);
                 }
                 
                     
                //down 1 0
                nd = getDownDiffY(stage, step, current , y, visited , diff);
                if(nd.order != -1){
                    diff[nd.order] = nd.value; 
                    //System.out.println("down x="+step[nd.order].x+" y="+step[nd.order].y+" c="+nd.order+" diff="+diff[nd.order]);
                }
                    
                if( 0 <= step[current].x-1 ){
                    //left 0 -1
                    nd = getDiffX(stage, step, current , -1, visited, diff );
                    if(nd.order != -1){
                    diff[nd.order] = nd.value;
                    //System.out.println("left x="+step[nd.order].x+" y="+step[nd.order].y+" c="+nd.order+" diff="+diff[nd.order]);
                    }
                }
                
                   
                if( step[current].x+1 < x ){
                    //right 0 1
                    nd = getDiffX(stage, step, current , 1, visited, diff );
                    if(nd.order != -1){
                    diff[nd.order] = nd.value;
                        //System.out.println("right x="+step[nd.order].x+" y="+step[nd.order].y+" c="+nd.order+" diff="+diff[nd.order]);
                    }
                }
                
                
        }
        
        return diff[goal];
        
        
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        spot[][] stage = new spot[y][x];
        
        int start = 0;//yx
        int goal = 0;//yx
        point[] step = new point[y*x];
        int totalStep = 0;
        for(int i=0;i<y;i++){
            for(int j=0;j<x;j++){
                stage[i][j] = new spot();
                stage[i][j].value = scanner.nextInt();
                
                if(stage[i][j].value != 0){
                    step[totalStep] = new point();
                    step[totalStep].y = i;
                    step[totalStep].x = j;  
                    stage[i][j].order = totalStep; 
                    
                    if(stage[i][j].value == 2){
                        start = totalStep;
                        
                    }

                    if(stage[i][j].value == 3){
                        goal = totalStep;                    
                    }
                    
                    totalStep++;
                }
                
                
            }
            
        }
        
        //System.out.println(x+" "+y+" "+stage[0][0]+" "+stage[y-1][0]+" "+stage[0][x-1]+" "+stage[1][6]);
        //System.out.println(totalStep);
        int level;
        level = climbing(stage,step,totalStep,y,x,start,goal);
        
        //System.out.println("f " + level);
        System.out.println(level);
    }
}
