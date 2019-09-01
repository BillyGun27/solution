import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    
    private static ArrayList<ArrayList<Integer>> graph ;
    private static int[] visited;
    private static long totalStreet;

    static long countCluster(int c){
        long deadend = 0 ;
        visited[c] = 1;

        if(graph.get(c).size()<1){
            deadend = 1;
        }

        for(int i=0;i<graph.get(c).size();i++){
            
            if(visited[ graph.get(c).get(i) ] == 0){
                //totalStreet++;
           // System.out.println(graph.get(c).size()+" m "+c+" "+graph.get(c).get(i));
                deadend =  countCluster(graph.get(c).get(i));
            }else{
                deadend = 1;
            }

        }

        return deadend;
    }
    
    static long countClusterx(int c,long count){
        //long deadend = 0 ;
        visited[c] = 1;

        //if(graph.get(c).size()<1){
        //    deadend = 1;
        //}

        for(int i=0;i<graph.get(c).size();i++){
            
            if(visited[ graph.get(c).get(i) ] == 0){
                //totalStreet++;
           // System.out.println(graph.get(c).size()+" m "+c+" "+graph.get(c).get(i));
                //count = 1 + countCluster(graph.get(c).get(i),count);
            }//else{
            //    deadend = 1;
            //}

        }

        return count;
    }
    
    // Complete the roadsAndLibraries function below.
    static long roadsAndLibraries(int n, long c_lib, long c_road, int[][] cities) {

        graph = new ArrayList<ArrayList<Integer>>();

        if(c_lib > c_road ){
            for(int i=0;i<n+1;i++){
                ArrayList<Integer> g = new ArrayList<Integer>();
                graph.add(g);
            }


            //System.out.println(graph.size());
            for(int j=0;j<cities.length;j++){
                ////System.out.println(cities[j][0] +" "+cities[j][1]);
                graph.get(cities[j][0]).add(cities[j][1]);
            }

            visited = new int[n+1];
            //int group = 0 ; 
            long total = 0 ; 
            totalStreet = 0;
            for(int i=1;i<n+1;i++){
                if(visited[i] == 0){
                    total += countCluster(i);
                    //System.out.println(i+" lo "+countCluster(i,1));
                    //total +=(long) c_lib; //countCluster(i,1);
                    //total += (long) ( (countCluster(i,1)-1) * c_road );

                    
                    //long clusterSize = countCluster(i,1);
                    //if (c_lib > c_road) {
                    //    total += ((clusterSize - 1) * c_road);
                    //} else {
                    //    total += ((clusterSize - 1) * c_lib);
                    //}

                    
                }
                
            }
            
            //System.out.println("lll "+total +" "+totalStreet);
            //System.out.println("lll "+total);
            long street_build  = (long)((long)n-(long)total);
            return (long)((long)total*(long)c_lib + ( (long)street_build*(long)c_road ));
            //return total;
            

        }else{
            //System.out.println("cheap l");
            return (long)((long)n * (long)c_lib);
        }
        


        
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nmC_libC_road = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmC_libC_road[0]);

            int m = Integer.parseInt(nmC_libC_road[1]);

            long c_lib = Long.parseLong(nmC_libC_road[2]);

            long c_road = Long.parseLong(nmC_libC_road[3]);

            int[][] cities = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] citiesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int citiesItem = Integer.parseInt(citiesRowItems[j]);
                    cities[i][j] = citiesItem;
                }
            }

            long result = roadsAndLibraries(n, c_lib, c_road, cities);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

