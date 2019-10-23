import java.util.*;

public class Solution {
    static int maxScore;

    static class LinkList{
        int head, next, prev;
        LinkList(int h){
            head = h;
            next = 0;
            prev = 0;
        }
    }

    static LinkList[] CreateBallonLink(int N){
         LinkList[] ballonLink = new LinkList[N+2];
            ballonLink[0] = new LinkList(0);
            for(int i=0;i<N;i++){
                ballonLink[i+1] = new LinkList(i+1);

                ballonLink[i].next = i+1;
                ballonLink[i+1].prev = ballonLink[i].head;

            }
            ballonLink[N+1] = new LinkList(0);
            ballonLink[N+1].prev = ballonLink[N].head;

            return ballonLink;
    }

    static void observe(int N,int[] ballon,int[] visited,int[] order,int len){
        if(len >= N){
            /*
            for(int i=0;i<N;i++){
                System.out.print(order[i]+" ");
            }System.out.println();
            */
            LinkList[] ballonLink = CreateBallonLink(N);
            int Total = 0;
            for(int i=0;i<N;i++){
                int current = order[i]+1;
                if(ballonLink[current].prev == 0 && ballonLink[current].next == 0 ){
                    Total += ballon[ballonLink[current].head - 1] ;
                    //System.out.print( ballon[ballonLink[current].head - 1]+"h " );
                }else if(ballonLink[current].prev == 0){
                    Total += ballon[ballonLink[current].next - 1] ;
                    //System.out.print(  ballon[ballonLink[current].next - 1]+"n " );
                }else if(ballonLink[current].next == 0){
                    Total += ballon[ballonLink[current].prev - 1] ;
                    //System.out.print( ballon[ballonLink[current].prev - 1]+"p " );
                }else{
                    Total += (ballon[ballonLink[current].prev - 1] * ballon[ballonLink[current].next - 1]) ;
                    //System.out.print( (ballon[ballonLink[current].prev - 1] * ballon[ballonLink[current].next - 1])+"b " );
                }

                /*
                for(int jj=0;jj<ballonLink.length;jj++){
                    System.out.print("("+ballonLink[jj].prev+" "+ ballonLink[jj].head +" "+ ballonLink[jj].next+")");
                }System.out.println();*/

                ballonLink[ ballonLink[current].prev ].next = ballonLink[current].next ;
                ballonLink[ ballonLink[current].next ].prev = ballonLink[current].prev ;

            } //System.out.println(" "+maxScore+" | "+Total);

            if(maxScore<Total){
                maxScore = Total;
            }

            return;

        }

        if(len<N){
            for(int i=0;i<N;i++){
                if(visited[i] == 0){
                    visited[i] = 1;
                    order[len] = i;
                    observe(N,ballon,visited,order,len+1);
                    visited[i] = 0;
                }
            }
        }
        
    }
    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */

        Scanner scanner = new Scanner(System.in);
        int TC = scanner.nextInt();

        for(int tc=0;tc<TC;tc++){

            int N = scanner.nextInt();
            int[] ballon = new int[N];
            for(int i=0;i<N;i++){
                ballon[i] = scanner.nextInt();
            }

            int[] visited = new int[N];
            int[] order = new int[N];
            
            maxScore= 0;

            /*
            for(int i=0;i<ballonLink.length;i++){
                System.out.print("("+ballonLink[i].prev+" "+ ballonLink[i].head +" "+ ballonLink[i].next+")");
            }System.out.println();
            */
            observe(N,ballon,visited,order,0);
            
            System.out.println(maxScore);

        }

    }
}
