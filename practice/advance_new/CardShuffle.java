/*

[Input]
5
4
1 2 3 4
4
4 2 3 1
6
6 5 4 2 3 1
8
6 1 4 7 2 5 8 3
12
2 7 4 1 3 5 8 10 12 9 6 11

Output
0
1
-1
3
5
*/
import java.util.*;

public class Solution {
    static int totalTries;
    
    static boolean checkorder(int N,int[] card){
        boolean ordered = true;
        if(card[0]==1){
            for(int i=0;i<N;i++){
                if( card[i]!=(i+1) ){
                    ordered = false;
                    break;
                }
            }
        }else if(card[0]==N){
            for(int i=0;i<N;i++){
                //System.out.println("c"+i+" "+card[i]+" "+(N-i));
                if( card[i]!=(N-i) ){
                    ordered = false;
                    break;
                }
            }
        }else{
            ordered = false;
        }
        
        return ordered;
    }

    static int[] mix(int N,int[] setL,int[] setR,int x){
        int[] newCard = new int[N];
        int[] setS ,setE; 

        if(x<N/2){
           setS = setL;
           setE = setR;
        }else{
           x = (N-1)-x;
           setS = setR;
           setE = setL;
        }
       
        int mid = (N/2) - x;
        int jump = 1;
        for(int i=0;i<mid;i++){
            newCard[i] =  setS[i];
            newCard[(N-1)-i] = setE[((N/2)-1)-i];
        }
        for(int i=mid;i<N/2;i++){
            newCard[i+jump] =  setS[i];
            newCard[((N-1)-i)-jump] = setE[ ((N/2)-1)-i];
            jump++;
        }
        
       return newCard;
    }

    static void shuffle(int N,int[] card,int tries){
    
        if(tries<=5){

            int[] setL = new int[N/2];
            int[] setR = new int[N/2];
            for(int i=0;i<N;i++){
                if(i<(N/2)){
                    setL[i] = card[i];
                }else{
                    setR[i-(N/2)] = card[i];
                }
            }

            int[][] fullCard = new int[N][N];
            for(int x=0;x<N;x++){
                int[] newCard =  mix(N,setL,setR,x);
                boolean check = checkorder(N,newCard);
                
                if(check){
                    if(totalTries == -1 || totalTries > tries){
                        totalTries = tries;
                    }
                
                }

                fullCard[x] = newCard;
            }

            for(int x=0;x<N;x++){
                shuffle(N,fullCard[x],tries+1);
            }
        }
        
       

    }

    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        for(int t=0;t<T;t++){
            int N = sc.nextInt();
            int card[] = new int[N];

            for(int i=0;i<N;i++){
                card[i] = sc.nextInt();
            };

            boolean check = checkorder(N,card);
            totalTries = -1;
            if(!check){
                shuffle(N,card,1);
            }else{
                totalTries = 0;
            }
            
            
            System.out.println(totalTries);
                
        }

    }
}

