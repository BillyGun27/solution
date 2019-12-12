/*
5
5
18211 0 3
29790 1 1
31307 2 1
22294 0 1
28334 0 3
8
33940 0 2
35911 0 1
32827 1 2
41251 0 2
11856 1 0
17549 1 2
24639 3 0
27357 1 2
10
71676 1 2
31920 0 0
40528 1 0
15897 1 1
41588 1 0
30002 0 0
23741 0 1
10553 1 0
37658 1 2
40297 1 0
7
11074 0 1
18098 0 1
32327 1 2
13579 1 2
18092 0 2
17583 0 2
29664 2 1
1
10448 1 0

ans
3
0
2
1
9452
*/
import java.util.*;

public class Solution {
    
    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        for(int t=0;t<T;t++){
            int N = sc.nextInt();
            String[] B = new String[N];
            int[] C = new int[N];
            int[] D = new int[N];

            for(int i=0;i<N;i++){
                B[i] = Integer.toString(sc.nextInt());
                C[i] = sc.nextInt();
                D[i] = sc.nextInt();
            }
            int totalPossible=0;
            for(int a=10000;a<=99999;a++){
                //int a = 31728;
                boolean match = true;
                for(int i=0;i<N;i++){
                    String Ai = Integer.toString(a);
                    String Bi = B[i];
                    int Ci = C[i];
                    int Di = D[i];

                    //System.out.print(Ci+" "+Di+" ");

                    int[] nA = new int[10];
                    int[] nB = new int[10];
                    for(int cc=0;cc<5;cc++){
                        if( Ai.charAt(cc) == Bi.charAt(cc) ){
                                Ci--;                            
                        }else{
                            //System.out.println("j"+Ai.charAt(cc));
                            int an =  Ai.charAt(cc) - '0';
                            int bn =  Bi.charAt(cc) - '0';
                            nA[an]++;
                            nB[bn]++;
                        }
                        
                    }

                    for(int l=0;l<=9;l++){
                        if(nA[l]<nB[l]){
                            Di-=nA[l];
                        }else{
                            Di-=nB[l];
                        }
                    }

                   // System.out.println(Ci+" "+Di+" b"+match);
                    if(Ci != 0 || Di != 0){
                        match=false;
                        //System.out.print("t");
                    }
                    //System.out.println(Ci+" "+Di+" "+match);
                }

                if(match){
                    //System.out.println(a);
                    totalPossible++;
                }
            }

            System.out.println(totalPossible);
        }

    }
}

