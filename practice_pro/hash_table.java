import java.io.*;
import java.util.*;

public class Solution
{
    static final int MAX_POIN 	= 8;
    static final int CAPACITY 	= 32003;
    static final int MAX_USERS	= (1 << 15);
    static final int MAX_ID 	= (1 << 29);
    static int debug = 0;

//
// DON'T CHANGE CONSTANTS ABOVE
//

	static
    class Token{
        int key, value = 0;
        Token next;
    };
    static Token tokens[] = new Token[CAPACITY];
    static Token
    getLog(int key){
        Token existing, added;

        int cell = key % CAPACITY ; //0;
        
        //for(;; cell++) {
         for(existing = tokens[cell];; existing = existing.next) {
            

            if (null == existing)			break;
            if (existing.key != key)		continue;
            return existing;
        }
        debug++;
        added = new Token();
        added.key = key;
        added.next = tokens[cell];

        tokens[cell] = added;
        return added;
    }//


    static int maxPoint = 0;
    static int mostPoin(){
        // Find customer with the most accumulated loyalty point, then returns the loyalty point of the customer.
        return maxPoint;
    }
    static void record(int id, int poin){
	    // Adds the given loyalty point for the given customerID.
        Token customer =  getLog(id);
        customer.value +=poin;
        if( customer.value > maxPoint ){
            maxPoint = customer.value;
        }
    }



//
// DON'T CHANGE CODES BELOW
//

    static int seed = 0;
    static int random(int bound) {
        long ret=(seed*214013L+2531011L);
        seed=(int)(ret>>16);
        seed&=0x1fffffff;
        return(int)(ret%bound);
    }

	static Scanner sc = new Scanner(System.in);
    static int parse(int arr[]){
        seed = sc.nextInt();
        int limit = sc.nextInt();
        for(int i = 0; limit > i; i++)
        {
            arr[i] = (random(MAX_ID) +1);
        }
        return limit;
    }
    static void run(int max, int arr[]){
        int limit = sc.nextInt();
        for(int i = 0; limit > i; i++)
        {
            int user = arr[random(max)];
            record(user, random(MAX_POIN) +1);
        }
        System.out.println(mostPoin());
    }


    
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int users[] = new int[MAX_USERS];
        int count_users = parse(users);
        run(count_users, users);
        
        start -= System.currentTimeMillis();
        // System.out.println(debug);
        // System.out.println(start);
    }
}
