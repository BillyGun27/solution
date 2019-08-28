import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the isBalanced function below.
    static String isBalanced(String s) {
        Stack<Character> br = new Stack<Character>();

        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == '{' || s.charAt(i) == '[' || s.charAt(i) == '(' ){
                br.push(s.charAt(i));
            }else if( br.size() > 0 && (s.charAt(i) == '}' || s.charAt(i) == ']' || s.charAt(i) == ')') ){
                char f = br.pop();
                if( f=='{' && s.charAt(i) != '}'){
                    return "NO";
                }else if( f=='[' && s.charAt(i) != ']'){
                    return "NO";
                }else if( f=='(' && s.charAt(i) != ')'){
                    return "NO";
                }
            }else{
                return "NO";
            }
        }

        if(br.size()==0){
            return "YES";
        }else{
            return "NO";
        }

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String s = scanner.nextLine();

            String result = isBalanced(s);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
