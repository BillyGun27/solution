import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    static int[][] visited;

    static int dfs(int x,int y,int[][] grid){
        
        visited[x][y] = 1;
        int total = 1;

        for(int i=-1;i<2;i++){
            for(int j=-1;j<2;j++){
                if((x+i >= 0 && y+j >= 0)&&(x+i < grid.length && y+j < grid[0].length)){
                    if( visited[x+i][y+j] == 0  &&  grid[x+i][y+j] == 1){
                        total += dfs(x+i,y+j, grid ) ;
                    }
                }
            }
        }
        return total;
    }

    // Complete the maxRegion function below.
    static int maxRegion(int[][] grid) {
        visited = new int[grid.length][grid[0].length];

        int largest = 0 ;
        int cell = 0 ; 
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length ; j++){
                if( visited[i][j] == 0 && grid[i][j] == 1){
                    cell = dfs(i,j,grid);
                    if(cell > largest){
                        largest = cell;
                    }
                }
                
            }
        }

        return largest;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] gridRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < m; j++) {
                int gridItem = Integer.parseInt(gridRowItems[j]);
                grid[i][j] = gridItem;
            }
        }

        int res = maxRegion(grid);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
