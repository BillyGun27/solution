import java.util.*;
public class Solution {

    static class Queue{
        int[] data;
        int start, end;

        Queue(int size){
            data = new int[size];
            start = 0;
            end = 0;
        }

        public void add(int d){
            data[end] = d;
            end++;
        }

        public int remove(){
            if(start != end){
                return data[start++];
            }else{
                return data[0];
            }
            
        }

        public boolean empty(){
            if(start == end ){
                return true;
            }else{
                return false;
            }
        }

        public int size(){
            return end-start;
        }

    }


    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */

        Queue qu = new Queue(10);

        System.out.println(qu.empty());
        qu.add(5);
        qu.add(6);
        qu.add(13);

        System.out.println(qu.empty());
        System.out.println(qu.size()+" s");

        System.out.println(qu.remove());
        System.out.println(qu.remove());
        
        System.out.println(qu.size()+" s");

        qu.add(9);

        System.out.println(qu.remove());
        System.out.println(qu.remove());

        System.out.println(qu.empty());

    }
}

