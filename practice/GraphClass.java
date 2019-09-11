import java.util.*;
public class Solution {

    static class AdjList{
        static Node head;
        static Node tail;
        static Node current;
        static int totalNode;

        AdjList(){
            totalNode = 0;
            head = new Node();
            tail = head;
            current = head;
        }

        public static void add(int data){
           totalNode++;
           Node n = new Node(data);
           tail.next = n;
           tail = tail.next;
        }

        public static int get(){
            current = current.next;
            return current.data;
        }
        
        public static void resetGet(){
            current = head;
        }

        public static int size(){
            return totalNode;
        }

        static class Node{
            int data;
            Node next;

            Node(){
                data = 0;
                next = null;
            }

            Node(int d){
                data = d;
                next = null;
            }
        }
    }

    


    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */

        AdjList graph = new AdjList();

        graph.add(3);
        graph.add(5);
        graph.add(7);
        
        System.out.println("size "+graph.size());
        System.out.println(graph.get());
        System.out.println(graph.get());

        graph.resetGet();
        System.out.println(graph.get());

        /*
        Node list = new Node();
        Node tail = list;

        //loop add
            //list.next = d;
            //list.next.next = d;

        for(int i=0;i<5;i++){
           Node d = new Node(i);
           tail.next = d;
           tail = tail.next;
        }
          
            
        //loop get
        //System.out.println(list.data);
        //System.out.println(list.next.data);
        //System.out.println(list.next.next.data);

        Node current = list;

        for(int i=0;i<5;i++){
            current = current.next;
            System.out.println(current.data);
        }
        */

        /*
           Node c = new Node(4); 
           tail.next = c;
           tail = tail.next;

           Node k = new Node(5); 
           tail.next = k;
           tail = tail.next;
        */

        /*
        current = current.next;
        System.out.println(current.data);

        current = current.next;
        System.out.println(current.data);
        */

        
        

    }
}
