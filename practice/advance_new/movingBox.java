/*

[INPUT]
5
5 5
0 1 0 0 0
0 0 0 0 0
0 2 0 9 0
0 0 0 0 0 
0 9 -1 0 -2
4 6
-1 -2 1 0 9 0 
0 2 0 0 0 0 
0 0 0 0 0 0
0 0 0 0 0 0
7 4
0 0 0 0
0 0 0 9
2 0 1 0
0 0 9 0
0 9 0 0
0 0 0 0
-1 0 -2 0
4 7
0 0 0 0 0 0 0
0 0 1 9 0 0 0
0 0 0 0 0 0 0
9 2 -1 9 0 0 -2
10 10
0 0 0 0 0 0 0 9 -1 0
0 9 -2 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 9
0 0 0 9 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0
0 0 0 9 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 0 0 0
0 0 0 2 0 0 0 0 9 9
9 0 0 0 0 0 9 0 0 0 

[OUTPUT]
3
4
-1
5
8
*/
import java.util.*;

public class Solution {
    static int ht1,wt1,ht2,wt2;
    static int totalTry;
    public static int[] move(int H,int W,int[][] box,int hb,int wb,int hdir, int wdir){
        int[] dest = new int[2];
        int end = 0;
        
        if(hdir == -1){
            end = hb;
        }else if(hdir == 1){
            end = H-hb;
            end--;
        }else if(wdir == -1){
            end = wb;
        }else if(wdir == 1){
            end = W-wb;
            end--;
        }
        
        int nextH = hb;
        int nextW = wb;
        //System.out.println(nextW+" "+end);
        for(int i=0;i<end;i++){
            nextH += hdir;
            nextW += wdir;
            //System.out.println(nextW+" "+end);
            if(box[nextH][nextW] > 0){
                nextH -= hdir;
                nextW -= wdir;
            }
        }
        
        dest[0] = nextH;
        dest[1] = nextW;
        return dest;
    }

    public static void tilt(int H,int W,int[][] box,int hb1,int wb1,int hb2,int wb2,int tries){
        
        if(tries<=8){
             
            if(hb1==ht1 && wb1==wt1 && hb2==ht2 && wb2 == wt2){
               //System.out.println(tries);
                if(totalTry == -1 || totalTry>tries){
                    //System.out.println(tries+"1b"+hb1+"="+ht1+" "+wb1+"="+wt1+" 2b"+hb2+"="+ht2+" "+wb2+"="+wt2);
                    totalTry = tries;
                }
                //return;
            }

            int[][] moveList = {{1,0},{-1,0},{0,1},{0,-1}};
            int[] nextMove1;
            int[] nextMove2;
            
            for(int m=0;m<4;m++){
               //
                if( (hb1==hb2 && wb1<wb2 && moveList[m][1]==1) || (wb1==wb2 && hb1<hb2 && moveList[m][0]==1) || (hb1==hb2 && wb1>wb2 && moveList[m][1]==-1) || (wb1==wb2 && hb1>hb2 && moveList[m][0]==-1) ){
                    
                    //2 first
                    //System.out.println("trigger");
                    box[hb2][wb2] = 0; 
                    nextMove2 =  move(H,W,box,hb2,wb2,moveList[m][0],moveList[m][1]);
                    box[nextMove2[0]][nextMove2[1]] = 2;

                    box[hb1][wb1] = 0; 
                    nextMove1 =  move(H,W,box,hb1,wb1,moveList[m][0],moveList[m][1]);
                    box[nextMove1[0]][nextMove1[1]] = 1;
                }else{
                    box[hb1][wb1] = 0; 
                    nextMove1 =  move(H,W,box,hb1,wb1,moveList[m][0],moveList[m][1]);
                    box[nextMove1[0]][nextMove1[1]] = 1;

                    box[hb2][wb2] = 0; 
                    nextMove2 =  move(H,W,box,hb2,wb2,moveList[m][0],moveList[m][1]);
                    box[nextMove2[0]][nextMove2[1]] = 2;
                }

                //if(tries==1){
                //System.out.println("ok"+tries+" "+moveList[m][0]+" "+moveList[m][1]);
                //for(int i=0;i<H;i++){
                //    for(int j=0;j<W;j++){
                //        System.out.print(box[i][j]+" ");
                //    }System.out.println();
                //}
                //}
                
                //System.out.println("loop"+nextMove1[0]+" "+nextMove1[1]+" "+
                //nextMove2[0]+" "+nextMove2[1]);
                tilt(H,W,box,nextMove1[0],nextMove1[1],nextMove2[0],nextMove2[1],tries+1); 
                box[nextMove1[0]][nextMove1[1]] = 0;
                box[nextMove2[0]][nextMove2[1]] = 0;
            }

        }        


    }

    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        for(int t=0;t<T;t++){
            int H = sc.nextInt();
            int W = sc.nextInt();
            int[][] box = new int[H][W];
            int hb1 = 0;
            int wb1 = 0;
            int hb2 = 0;
            int wb2 = 0;
            ht1 = 0;
            wt1 = 0;
            ht2 = 0;
            wt2 = 0;
            for(int i=0;i<H;i++){
                for(int j=0;j<W;j++){
                    box[i][j] = sc.nextInt();
                    if(box[i][j] == 1){
                        hb1 = i;
                        wb1 = j;
                    }else if(box[i][j] == 2){
                        hb2 = i;
                        wb2 = j;
                    }else if(box[i][j] == -1){
                        ht1 = i;
                        wt1 = j;
                    }else if(box[i][j] == -2){
                        ht2 = i;
                        wt2 = j;
                    }
                }
            }

            totalTry = -1;
            tilt(H,W,box,hb1,wb1,hb2,wb2,0);
            System.out.println(totalTry);

        }

    }
}

