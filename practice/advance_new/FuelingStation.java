/*
5
3
2 1 2
5
1 2 1 2 1
5 
2 1 1 2 1
8
2 2 1 1 1 2 1 1
8
2 2 2 2 2 2 2 2

output

 7
12
14
35
41
*/
import java.util.*;

public class Solution {
    static int maxStep;
    //static int found;

    static void FuelFill(int[] Cars,int Clen,int fill,int step,int pos,int type,int fuel,int[] visited){
        if(fill>=Clen){
            //System.out.println("finish"+step+" "+fill+"/"+Clen);
            if(maxStep>step){
                //found = 1;
                maxStep = step;
            }
        }else{
            if(pos == 0){//refill gasoline
                //System.out.println("refill gasoline 0 "+fill+"/"+Clen);
                FuelFill(Cars,Clen,fill,step+1,pos+1,1,2,visited);
            }else if(pos == Cars.length-1){//refill diesel
                //System.out.println("refill diesel"+(Cars.length-1)+" "+fill+"/"+Clen);
                FuelFill(Cars,Clen,fill,step+1,pos-1,2,2,visited);
            }else if(fuel == 0){//go to fuel station
                //System.out.println("to refill"+fill+"/"+Clen);
                //to gasoline
                FuelFill(Cars,Clen,fill,step+pos,0,type,fuel,visited);
                //to diesel
                FuelFill(Cars,Clen,fill,step+(Cars.length-(pos+1)),Cars.length-1,type,fuel,visited);
            }else if( visited[pos] == 0 && Cars[pos] == type ){
                visited[pos] = 1;
                //fill car
                //System.out.println("fill car"+pos+" "+(fill+1)+"/"+Clen);
                FuelFill(Cars,Clen,fill+1,step,pos,type,fuel-1,visited);
                
                //System.out.println("to optional refill"+fill+"/"+Clen);
                //to gasoline
                FuelFill(Cars,Clen,fill+1,step+pos,0,type,fuel,visited);
                //to diesel
                FuelFill(Cars,Clen,fill+1,step+(Cars.length-(pos+1)),Cars.length-1,type,fuel,visited);

                visited[pos] = 0;
            }

            
            if(fill < Clen && step<maxStep && ( (0 < pos) && (pos < Cars.length-1) ) ){
                if(type == 1){
                    //System.out.println("to right"+(pos+1)+" "+fill+"/"+Clen);
                    FuelFill(Cars,Clen,fill,step+1,pos+1,type,fuel,visited);
                }else if(type == 2 ){
                    //System.out.println("to left"+(pos-1)+" "+fill+"/"+Clen);
                    FuelFill(Cars,Clen,fill,step+1,pos-1,type,fuel,visited);
                }
            }
        }
    }
   
    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */

        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for(int t=0;t<T;t++){
            int Clen = scanner.nextInt();
            int[] Cars = new int[Clen+2];
            Cars[0] = 0;
            Cars[Cars.length-1] = 0;

            //int GasTot=0,DisTot=0;
            for(int i=1;i<Clen+1;i++){
                Cars[i] = scanner.nextInt();
            }
            
            int fuel = 2;
            int type = 1;
            int pos = 0;
            int step = 0;
            int fill = 0;
            int[] visited = new int[Clen+2];
            maxStep=Integer.MAX_VALUE;
            //found = 0;
            FuelFill(Cars,Clen,fill,step,pos,type,fuel,visited);
            System.out.println(maxStep);

        }
    }
}
