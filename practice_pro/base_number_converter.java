import java.io.*;
import java.util.*;

public class Solution {

    static final int MAX_LEN = 10;
    static final char BASE_ALPHA = '0';
//
    static char chOf(int v){
        return (char)(v + BASE_ALPHA);
    }
    static int intOf(char c){
        return c - BASE_ALPHA;
    }
    static void copy(int src, char srcArr[], char dstArr[]){
        for(int i = 0;; i++){
            dstArr[i] = srcArr[src++];
            if (0 == dstArr[i])
                break;
        }
    }
//



static void encode(int value10, int base, char arr[]){        
  // encodes the given base-10 number into the provided char array as the specified base number.
    int i=MAX_LEN-1;
    for(i=MAX_LEN-1;i>=0;i--){
         
        int v = value10 % base; 
        arr[i] = chOf(v);
        
        value10 /= base;
        
        if(value10 <= 0){
            break;
        }
        
    }
    //System.out.println(i);
    copy(i, arr, arr);

}

static int decode(int base, char arr[]){
  // returns a base-10 numeric value by decoding the given string (char array) as the specified base number.
    int dec = 0;
    for(int i=0;i<MAX_LEN;i++){
        int val = intOf(arr[i]);
        if(0 == arr[i]){
            
            break;
        }
        
        dec *= base;
        dec+= val;
    }
    return dec;
}



//
// DON'T CHANGE CODES BELOW
//

    static int base;
    static char inp[] = new char[MAX_LEN];
    static int parse(char arr[]){
        Scanner sc = new Scanner(System.in);
        
        int len = sc.nextInt();
        base = sc.nextInt();
        int target = sc.nextInt();
        sc.nextLine();

        String str = sc.nextLine();
        for(int i = 0; len > i; i++){
            arr[i] = str.charAt(i*2);
        }
        arr[len] = 0;
	    return target;
    }

    static void result(int value, char arr[]){
        System.out.println(value);
        String separator = "";
        for(int i = 0; MAX_LEN > i; i++){
            char c = arr[i];
            if (0 == c)
                break;
            System.out.print(separator);
            System.out.print(c);
            separator = " ";
        }
    }

    static char out[] = asChars(new char[MAX_LEN + 1], "..........");
    static char[] asChars(char out[], String inp){
        int limit = inp.length();
        for(int i = 0; limit > i; i++){
            out[i] = inp.charAt(i);
        }
        out[limit] = 0;
        return out;
    }
    public static void main(String[] args) {
        int target = parse(inp);
        int value = decode(base, inp);
        encode(value, target, out);
        result(value, out);
    }
}
