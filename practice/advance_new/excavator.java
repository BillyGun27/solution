/*
4
1 3 1
42 3 99
4 19 3
4 1 5
1 5 1
1 4 1 3 11
7 2 3
2 6 4
2 5 4
1 4 1 3 11
13 5 1 4 11
1 2 3 4 5
99 2 47 3 31
7 2 3
2 6 5
1 4 1
1 2 3 4
7 3 3
2 9 4

ans
576
57
170
34
*/
import java.util.*;

public class Solution {
   static int[] minH;
   static int minV;
   static int N,H,V;
   static int C1,R1,M1,C2,R2,M2;
   
   static void ExcavateVertical(int n,int current,int cost){
        if(n>=N){
            //System.out.println("c"+cost);
            if(minV>cost){
                minV = cost;
            }
        }else{
            for(int v=current;v<V;v++){
                if( v-current >= 2 ){
                    int movingCost = (M1*M1 + M2*M2)*(v-current);
                    //System.out.println("m"+movingCost+" h"+minH[v]);
                    ExcavateVertical(n+1,v,cost+movingCost+minH[v]);
                }else if(n == 0){
                    //System.out.println("h"+minH[v]);
                    ExcavateVertical(n+1,v,cost+minH[v]);
                }
               
            }
        }
   }

    static void ExcavateHorizontal(int[] SH,int v,int prevExcav,int Lpos,int Rpos,int pos,int cost){
        if(pos>=H){
            //System.out.println("c"+cost);
            if(minH[v]>cost){
                minH[v] = cost;
            }
        }else if(minH[v]>cost){
            int cost1,cost2;
            
            if(prevExcav == 1){
                //System.out.println("1");
                cost1 = SH[Lpos] * C1 + R1 ;
                cost2 = SH[Rpos] * C2 ;
            }else if(prevExcav == 2){
                //System.out.println("2");
                cost1= SH[Lpos] * C1 ;
                cost2= SH[Rpos] * C2 + R2 ;
            }else{
                //System.out.println("0");
                cost1= SH[Lpos] * C1 ;
                cost2= SH[Rpos] * C2 ;
            }
            //System.out.println(prevExcav+" "+Lpos+" "+Rpos+" "+cost1+" "+cost2);
            ExcavateHorizontal(SH,v,1,Lpos+1,Rpos,pos+1,cost+cost1);
            ExcavateHorizontal(SH,v,2,Lpos,Rpos-1,pos+1,cost+cost2);

        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */
        
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int t=0;t<T;t++){
            N = sc.nextInt();
            H = sc.nextInt();
            V = sc.nextInt();
            int[][] S = new int[V][H];
            
            for(int v=0;v<V;v++){
                for(int h=0;h<H;h++){
                    S[v][h] = sc.nextInt();
                }
            }

            C1 = sc.nextInt();
            R1 = sc.nextInt();
            M1 = sc.nextInt();

            C2 = sc.nextInt();
            R2 = sc.nextInt();
            M2 = sc.nextInt();

            minH = new int[V];
            for(int v=0;v<V;v++){
                minH[v] = Integer.MAX_VALUE;
                ExcavateHorizontal(S[v],v,0,0,H-1,0,0);
                
                //System.out.println(minH[v]);
            }
            if(V==1){
                System.out.println(minH[0]);
            }else{
                minV = Integer.MAX_VALUE;
                ExcavateVertical(0,0,0);
                System.out.println(minV);
            }
            

             //System.out.println("--");
            //System.out.println(construct[0][0]+" "+construct[0][1] );
        }

    }
}
