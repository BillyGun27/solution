
/* 
readjust later
2
7 1 2 3 1 2 3
2 2
6 4
3 3
7 2
1 1
2 1
1 10
4 3 2 1 6 4 3
1 5
1 3
2 4
2 2

ans
12
18
17
16
17998
*/
import java.util.*;

public class Solution {
    static int Total;
    static void AdvTime(int[][] Cs,int[] L,int[] P,int start,int end,int[] group,int[] startTime,int dist){
        if(dist>=3){
            int CsPoint[] = new int[Cs.length];
            for(int i=0;i<3;i++){
                int n = group[i];
                for(int c=0;c<Cs.length;c++){
                    if( (Cs[c][0]<=startTime[i]) && (startTime[i]+L[n]<=Cs[c][1]) ){
                        if(CsPoint[c]<P[n]){
                            CsPoint[c] = P[n];
                        }
                    }
                }
            }

            int tot=0;
            for(int c=0;c<Cs.length;c++){
                tot+=CsPoint[c];
            }

            if(Total<tot){
                Total = tot;
            }

        }else{
            for(int i=start;i<=end;i++){
                int n = group[dist];
                startTime[dist] = i;
                AdvTime(Cs,L,P,i+L[n],end,group,startTime,dist+1);
            }
        }
    }

    static void AdvOrder(int[][] Cs,int[] L ,int[] P,int start,int end,int[] group,int[] visited,int dist){
        if(dist>=3){
            int[] startTime = new int[3]; 
            AdvTime(Cs,L,P,start,end,group,startTime,0);
            //for(int i=0;i<3;i++){
            //    System.out.print(group[i]+" ");
            //}System.out.println();
          
        }else{
            for(int i=0;i<3;i++){
                if(visited[i] == 0){
                    visited[i] = 1;
                    group[dist] = i;
                    AdvOrder(Cs,L,P,start,end,group, visited,dist+1);
                    visited[i] = 0;
                }
            }
        }

         
    }

    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int t=0;t<T;t++){
            int CsLen = sc.nextInt();
            int[] L = new int[3];
            int[] P = new int[3];
            int[][] Cs = new int[CsLen][2];

            for(int i=0;i<3;i++){
                L[i] = sc.nextInt();
            }
            for(int i=0;i<3;i++){
                P[i] = sc.nextInt();
            }

            int start = 50 , end = 0;
            for(int i=0;i<CsLen;i++){
                Cs[i][0] = sc.nextInt();
                Cs[i][1] = Cs[i][0]+sc.nextInt();

                if(start>Cs[i][0]){
                    start = Cs[i][0];
                }
                if(end<Cs[i][1]){
                    end = Cs[i][1];
                }
            }

            int[] group = new int[3];
            int[] visited = new int[3];
            
            Total = 0;
            AdvOrder(Cs,L,P,start,end,group,visited,0);
            System.out.println(Total);
        }

    }
}
