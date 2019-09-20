public class Solution {

    static int shortest ;

    static void tsp(int[] office,int[] home ,int[][] point ,int[] visited ,int current ,int distance){
        
        int all_visited = 1;
        visited[current] = 1;

        for(int i=0;i<point.length;i++){
            if(visited[i] == 0 && i != current ){
               
                int[] v = visited.clone();
            
                int xx = Math.abs(point[current][0]-point[i][0]) ;
                int yy = Math.abs(point[current][1]-point[i][1]);
                int walk = xx + yy;

                 //System.out.print("|"+current+" "+i);//+">"+walk+"|"
                tsp(office, home , point , v , i , distance+walk );

                all_visited = 0;
            }
        }

        if(all_visited == 1){
            int xx = Math.abs(point[current][0]-home[0]) ;
            int yy = Math.abs(point[current][1]-home[1]);
            int walk = xx + yy;
            //System.out.print("|"+current+" end >");//+walk+"|"
            //System.out.println(distance+walk);
            if(shortest == 0){
                shortest = distance+walk;
            }else if(shortest > (distance+walk) ){
                shortest = distance+walk;
            }
        }

        return;

    }

    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */
        /*
        int[] office = {0,0};
        int[] home = {100,100};

        //int[][] point = {{20,30},{50,50},{70,70}};
        int[][] point = {{70,40},{30,10},{10,5},{90,70},{50,20}};
        */
        
        //int[] office = {88,81};
        //int[] home = {85,80};

        //int[][] point = {{19,22},{31,15},{27,29},{31,10},{20,26},{5,14}};
        
        int[] office = {39,9};
        int[] home = {97,61};

        int[][] point = {{35,93},{62,64},{96,39},{36,36},{9,59},{59,96},{61,7},{64,43},{43,58},{1,36}};

        shortest = 0;

        for(int i=0;i<point.length;i++){
            int[] visited = new int[point.length];
            int xx = Math.abs(point[i][0]-office[0]) ;
            int yy = Math.abs(point[i][1]-office[1]);
            int distance = xx + yy;
            //System.out.println("s"+i);
            //System.out.print("|office "+i+" >");//+distance+"|"
            tsp(office, home , point , visited , i ,distance );
        }

        System.out.println("shortest "+shortest);
       
    }
}
