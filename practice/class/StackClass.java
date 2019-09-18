import java.util.*;
public class Solution {

    static class Stack{
        int[] data;
        int cur;

        Stack(int size){
            data = new int[size];
            cur = 0;
        }

        public void push(int d){
            data[cur] = d;
            cur++;
        }

        public int pop(){
            if(cur>0){
                cur--;
                return data[cur];
            }else{
                return data[0];
            }
            
        }

        public boolean empty(){
            if(cur == 0 ){
                return true;
            }else{
                return false;
            }
        }

    }


    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */

        Stack st = new Stack(10);

        System.out.println(st.empty());
        st.push(5);
        st.push(6);
        st.push(13);

        System.out.println(st.empty());

        System.out.println(st.pop());
        System.out.println(st.pop());
        
        st.push(9);

        System.out.println(st.pop());
        System.out.println(st.pop());

        System.out.println(st.empty());

    }
}

