import java.util.*;

public class Solution {
    static int MaxUsers ;

    static void QuadraSearch(int H,int W,int[][] Quad,int[] current,int distance, int users,int[][] visited,int[] member){
        if(distance == 4){
            /*if(member[0]==440){
            
            }*/
            if(MaxUsers<users){
                for(int q=0;q<distance;q++){
                    System.out.print(member[q]+" ");
                } System.out.println(users*users);
                MaxUsers = users; 
            }
        }else{
            int[][] movement;
            //{W,H}
            if(current[1] % 2 == 0){//even
                int[][] m = { {-1,-1},{0,-1},{1,-1},{-1,0},{1,0},{0,1} };
                movement = m;
            }else{
                int[][] m = { {-1,1},{0,1},{1,1},{-1,0},{1,0},{0,-1} };
                movement = m;
            }

            for(int q=0;q<6;q++){
                int Hn = current[0] + movement[q][1];
                int Wn = current[1] + movement[q][0];
                if( (0 <= Hn && Hn < H) && (0 <= Wn && Wn < W) ){
                    if(visited[Hn][Wn] == 0 ){
                        visited[Hn][Wn] = 1;
                        int[] start={Hn,Wn};
                        member[distance] = Quad[Hn][Wn];
                       // System.out.println(Hn+" "+Wn);
                    QuadraSearch(H,W,Quad,start,distance+1,users+Quad[Hn][Wn],visited,member);
                        visited[Hn][Wn] = 0;
                    }
                }
            }            
        }

    }

    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */

        Scanner sc = new Scanner(System.in);
        int W = sc.nextInt();
        int H = sc.nextInt();
        int[][] Quad = new int[H][W] ;
        

        for(int i=0;i<H;i++){
            for(int j=0;j<W;j++){
                Quad[i][j] = sc.nextInt();
            }
        }
        
        int[][] visited = new int[H][W] ;
        int[] member = new int[4];
        MaxUsers = 0;
        
        for(int i=0;i<H;i++){
            for(int j=0;j<W;j++){
                int[] start={i,j};//h,n
                //System.out.println("hh"+i+" "+j);
                member[0] = Quad[i][j];
                visited[i][j] = 1;
                QuadraSearch(H,W,Quad,start,1,Quad[i][j],visited,member);
                visited[i][j] = 0;
            }
        }
        
        System.out.println(MaxUsers*MaxUsers);
    }
}
