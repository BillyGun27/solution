import java.util.*; 
public class Solution {
    static void Permutation(int N, int[] Gate, int[] visited,int dist, String[] text){
        
        if(dist>=3){
            for(int i=0;i<3;i++){
                System.out.print(text[i]+" ");
            }
            System.out.println(" ");
            return;
        }

        for(int i=0;i<3;i++){
                if(visited[i] == 0){
                    visited[i] = 1;
                    text[dist] = i+"";
                    Permutation(N, Gate, visited, dist+1 ,text);
                    visited[i] = 0;
                }
        }
        
    };

    static void PermutationD(int N, int[] Gate, int[] visited,int dist, String[] text){
        
        if(dist>=3){
            for(int i=0;i<3;i++){
                System.out.print(text[i]+" ");
            }
            System.out.println(" ");
            return;
        }

        for(int i=0;i<3;i++){
                if(visited[i] == 0){
                    visited[i] = 1;
                    if(Gate[i] % 2 != 0){
                        text[dist] = i+"l";
                        PermutationD(N, Gate, visited, dist+1 ,text);

                        text[dist] = i+"r";
                        PermutationD(N, Gate, visited, dist+1 ,text);
                    }else{
                        text[dist] = i+"";
                        PermutationD(N, Gate, visited, dist+1 ,text);
                    }
                    
                    visited[i] = 0;
                }
        }
        
    };

    static void Combination(int N, int[] Gate, int cur,int dist, String[] text){
        
        if(cur<3){

            for(int i=cur;i<3;i++){
                    text[dist] = i+"";
                    Combination(N, Gate, i+1 ,dist+1 ,text);
            }
        }else{
            for(int i=0;i<dist;i++){
                System.out.print(text[i]+" ");
            }
            System.out.println(" ");
            
        }

        return;
        
    };

    static void PermutationO(int N, int[] Gate, int[] visited,int dist, String[] text){
        
        if(dist>=3){
            for(int i=0;i<3;i++){
                System.out.print(text[i]+" ");
            }
            System.out.println(" ");
            return;
        }

        for(int i=0;i<3;i++){
                if(visited[i] == 0){
                    visited[i] = 1;
                    
                        text[dist] = i+"l";
                        PermutationO(N, Gate, visited, dist+1 ,text);

                        text[dist] = i+"r";
                        PermutationO(N, Gate, visited, dist+1 ,text);
                   
                    
                    visited[i] = 0;
                }
        }
        
    };

    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int[] Gate = new int[3];
        
        /*
        10
        5
        2
        2
        */
        for(int i=0;i<3;i++){
            Gate[i] = scanner.nextInt();
        }
        
        String[] text = new String[3];
        int[] visited = new int[3];
        //Permutation(N ,Gate,visited,0,text);
        
        //System.out.println("----");

        //PermutationD(N ,Gate,visited,0,text);

        //PermutationO(N ,Gate,visited,0,text);

        Combination(N ,Gate,0,0,text);
    }
}
