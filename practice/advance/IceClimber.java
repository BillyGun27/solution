import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static int level;
    static void climbing(int[][] stage, int x,int y,int[] current,int[] goal, int[][] visited, int difficult){
        int cy = current[0];
        int cx = current[1];
        
        int[] next = new int[2];
        for(int i=-1;i<2;i++){//y
            for(int j=-1;j<2;j++){//x
                int ny = cy+i;
                int nx = cx+j;
                if( (0 <= ny && ny < y) && (0 <= nx && nx < x) ){
                    if( (i==0 || j==0) && i != j  && visited[ny][nx] == 0){
                        //System.out.println("coor "+j + " " + i);
                        
                        next[0] = ny;
                        next[1] = nx;
                        visited[ny][nx] = 1;
                        if(stage[cy][cx] != 0){//normal
                            if( stage[ny][nx] == 3 ){
                                //System.out.println("found" + cy+" "+cx+" f "+ny+" "+nx +" diff " + difficult);
                                if(difficult<level){
                                    level = difficult;
                                }
                            }else if(stage[ny][nx] == 1){
                                //System.out.println(cy+" "+cx+" -> "+ny+" "+nx);
                                climbing(stage,x, y,next ,goal, visited, difficult);
                            }else if(stage[ny][nx] == 0 && j == 0 ){//climb up
                                int climb = 0;
                                int cur = cy; 
                                if(i==1){//down
                                    while( cur < y-1 ){
                                        cur+=i;
                                        climb++;
                                        
                                        if(stage[cur][cx] != 0){
                                            break;
                                        }
                                    }
                                }else if(i==-1){//up
                                    while( 0 < cur ){
                                        cur+=i;
                                        climb++;
                                        
                                        if(stage[cur][cx] != 0){
                                            break;
                                        }
                                        
                                    }
                                }
                                
                               
                                if( stage[cur][cx] == 3 ){
                                        //System.out.println("found sp" + cy+" "+cx+" f "+cur+" "+cx+" diff "+climb);
                                        if(climb<level){
                                            level = climb;
                                        }
                                }else{
                                    next[0] = cur;
                                    next[1] = cx;
                                    //System.out.println(cy+" "+cx+" climb up-> "+cur+" "+cx+" diff "+climb);
                                    climbing(stage,x, y,next ,goal, visited, climb);
                                }
                            }
                        }
                        
                        
                        visited[ny][nx] = 0;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int[][] stage = new int[y][x];
        int[][] visited = new int[y][x];
        
        int[] start = new int[2];//yx
        int[] goal = new int[2];//yx
        for(int i=0;i<y;i++){
            for(int j=0;j<x;j++){
                stage[i][j] = scanner.nextInt();
                if(stage[i][j] == 2){
                    start[0] = i;
                    start[1] = j;             
                    visited[i][j] = 1;
                }
                
                if(stage[i][j] == 3){
                    goal[0] = i;
                    goal[1] = j;                    
                }
            }
            
        }
        level = Integer.MAX_VALUE;
        
        //System.out.println(x+" "+y+" "+stage[0][0]+" "+stage[y-1][0]+" "+stage[0][x-1]+" "+stage[1][6]);
    
        climbing(stage,x,y,start,goal,visited,1);
        
        System.out.println("f " + level);
    }
}
