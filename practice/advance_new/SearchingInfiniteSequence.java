import java.util.*;
/*
1 1 0 861
1 1 0 721
5 9 0 860608
14 9 0 24638004
5 8 0 210115785
81 12 0 8192945703123
16 2 0 612158099651800
13 7 0 167155674009324
73 41 1 14679146125920
12 43 72 2984953283617
*/
public class Solution {
    static long Log2int(long x){
        if(x==1) return 0;
        else return Log2int(x/2)+1;
    }

    static long seq(long a, long b, long c, long k){
        long start = 1;
        long finish = k/6;
        long n = finish/2;
        long ans = 0;
        long cn = 0;
        while(ans != k){
            ans = (long) (a*n+b*n*Log2int(n)+c*Math.pow(n,3));
            //System.out.println(start+" "+n+"->"+ans+"/"+k+" "+finish);
            if(ans == k){
                cn = n;
                break;
            }
             /*
            else if(ans>k){
                cn = -1;
                break;
            }*/
            if(ans<k){
                //System.out.println("start");
                start = n+1;
            }else if(ans>k){
                //System.out.println("finish");
                finish = n-1;
            }
            
            if(start > finish){
                //System.out.println("stop");
                cn = -1;
                break;

            }
            n = start+((finish-start)/2) ;
            //System.out.println(n+" "+start+" "+finish+" "+( start+(finish-start/2) ));
            //n++;
        }

        return cn;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        for(int i=0;i<10;i++){
            long a = scan.nextLong();
            long b = scan.nextLong();
            long c = scan.nextLong();
            long k = scan.nextLong();

            //System.out.println(a+" "+b+" "+c+" "+k);
            System.out.println(seq(a,b,c,k));
        }
    }
}

