import java.util.*;

public class Solution {

    static int minGlobal;
    static int N;
    static int gate[][];

    static void swap(int i, int j, int arr[]) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    static  boolean isSafe(int i, int spot[]) {
        return (i>=0 && i<N && spot[i] == 0);
    }

    static void flood(int i, int n, int arr1[], int arr2[]) {

        int spot[] = new int[N];
        int totalDistance = 0;

        for (int l=0; l<arr1.length; l++) {

            boolean stop = false;
            int position = gate[arr1[l]][0]-1;
            int jumlahFisher = gate[arr1[l]][1];
            int fisher = 0;
            int distance = 0;

            if(arr2[l] == 0) { // Kiri dulu

                while(!stop) {
                    
                    // Kiri
                    if(isSafe(position-distance, spot) && fisher<jumlahFisher) {

                        spot[position-distance] = 1;
                        totalDistance += distance+1;
                        fisher+=1;
                    }

                    // Kanan
                    if(isSafe(position+distance, spot) && fisher<jumlahFisher) {

                        spot[position+distance] = 1;
                        totalDistance += distance+1;
                        fisher+=1;
                    }

                    if(fisher >= jumlahFisher) {
                        stop = true;
                    }
                    distance+=1;

                }

                }else { // Kanan Dulu

                    while(!stop) {
                        // Kanan
                        if(isSafe(position+distance, spot) && fisher<jumlahFisher) {

                            spot[position+distance] = 1;
                            totalDistance += distance+1;
                            fisher+=1;

                        }

                        // Kiri
                        if(isSafe(position-distance, spot) && fisher<jumlahFisher) {

                            spot[position-distance] = 1;
                            totalDistance += distance+1;
                            fisher+=1;
                        }

                        if(fisher >= jumlahFisher) {
                            stop = true;
                        }

                        distance+=1;

                    }

                }

        }

        //System.out.println(totalDistance);

        if(totalDistance < minGlobal) {
            minGlobal = totalDistance;
        }

    }



    static  void combination(int i, int n, int arr1[], int arr2[]) {

        if (i == n) {
            //for (int l=0; l<arr2.length; l++) {
            //System.out.print(arr2[l] + " ");
            //}
            //System.out.println();
            flood(i, n, arr1, arr2);
            return;

        }



        for(int l=0; l<2; l++) {
            arr2[i] = l;
            combination(i+1, n, arr1, arr2);

        }



    }





    static void arrange(int i, int n, int arr[]) {

        if (i == n) {

            //for (int l=0; l<arr.length; l++) {

            //System.out.print(arr[l] + " ");

            //}

            //System.out.println();

            int arr2[] = new int[3];
            combination(0, n, arr, arr2);
            return;

        }



        for (int l=i; l<n; l++) {

            swap(i, l, arr);
            arrange(i+1, n, arr);
            swap(i, l, arr);

        }

    }



public static void main(String args[]) {

    Scanner sc = new Scanner(System.in);

    int test_case = sc.nextInt();

    for (int test=0; test < test_case; test++) {

        N = sc.nextInt();

        gate = new int[3][2]; 

        for(int i=0; i<3; i++) {

            for(int j=0; j<2; j++) {

                gate[i][j] = sc.nextInt();

            }

        }


        minGlobal = Integer.MAX_VALUE;

        //solve();

        int arr[] = new int[] {0, 1, 2};

        arrange(0, 3, arr);

            System.out.println(minGlobal);

    }

    }

}


 
 
