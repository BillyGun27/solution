import java.util.*;
public class Solution {

    static class Graph{
        Node head;
        Node current , tail;
        int s;

        Graph(){
            head = new Node();
            tail = head;
            current = head;
            s = 0;

        }

        public void add(int d){
           Node n = new Node(d);
           tail.next = n;
           tail = tail.next;
           s++;
           
        }

        public int get(){
            current = current.next;
            return current.data;
        }

        public void resetGet(){
            current = head;
        }

        public int size(){
            return s;
        }

        class Node{
            int data;
            Node next;

            public Node(){
                data = 0;
                next = null;
            }

            public Node(int d){
                data = d;
                next = null;
            }
        }

    }

    public static void tsp(){
        System.out.println();
    }


    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */

        Graph[] graph = new Graph[5];

        for(int i=0;i<5;i++){
            Graph g = new Graph();
            graph[i] = g;
        }

        for(int i=0;i<5;i++){
           graph[2].add(i);
        }

        System.out.println(graph[2].get());
        System.out.println(graph[2].get());
        graph[2].resetGet();
        System.out.println(graph[2].get());
        System.out.println(graph[2].get());

        tsp();
    }
}

