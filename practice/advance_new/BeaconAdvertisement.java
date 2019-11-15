
import java.util.*;
/* 
readjust later
2
7 1 2 3 1 2 3
2 2
6 4
3 3
7 2
1 1
2 1
1 10
4 3 2 1 6 4 3
1 5
1 3
2 4
2 2

ans
12
18
17
16
17998
*/
public class Solution {
static int Total;
static void OptimalAd(int start,int end,int[][] Cs,int[] L,int[] P,int[] group,int[] AdvTime,int current){//,int[] CsPoint
if(current>=3){
// for(int i=0;i<3;i++){
// System.out.print(AdvTime[i]+" ");
//}System.out.println();
//for(int i=0;i<3;i++){
// int now = group[i];
//System.out.print("(P"+P[now]+" L"+L[now]+") ");
// }System.out.println();
int[] Point = new int[Cs.length];
for(int c=0;c<Cs.length;c++){
for(int a=0;a<3;a++){
int now = group[a];
if( Cs[c][0] <= AdvTime[a] && (AdvTime[a]+L[now]) <= Cs[c][1] ){
if(Point[c] < P[now]){
// System.out.print( c+"/"+P[now]+"/"+Cs[c][0]+"->"+Cs[c][1]+"/"+AdvTime[a]+" "+(AdvTime[a]+L[now]) +" ");
Point[c] = P[now];
}
}

}
}

int tot = 0;
for(int i=0;i<Cs.length;i++){
// System.out.print(Point[i]+" ");
tot+=Point[i];
}//System.out.println();
if(Total<tot){
Total = tot;
}

}else{
int now = group[current];
for(int i=start;i<=end;i++){
AdvTime[current] = i;
OptimalAd(i+L[now],end,Cs,L,P,group,AdvTime,current+1);//,CsPoint
}
}

}

static void Permutation(int start,int end, int[][] Cs,int[] L,int[] P,int[] group,int[] visited,int dist){
if(dist>=3){
// int[] CsPoint = new int[Cs.length];
int[] AdvTime = new int[3];
OptimalAd(start,end,Cs,L,P,group,AdvTime,0);//,CsPoint
//for(int i=0;i<3;i++){
// System.out.print(group[i]+" ");
//}System.out.println();
}else{
for(int i=0;i<3;i++){
if(visited[i] == 0 ){
visited[i] = 1;
group[dist] = i;
Permutation(start,end,Cs,L,P,group,visited,dist+1);
visited[i] = 0;
}
}
}
}

public static void main(String[] args) {
/* Enter your code here. Print output to STDOUT. Your class should be named Solution. */

Scanner scanner = new Scanner(System.in);
int T = scanner.nextInt();
for(int t=0;t<T;t++){
int CsLen = scanner.nextInt();
int[] L = new int[3];
int[] P = new int[3];
int[][] Cs = new int[CsLen][2];
for(int i=0;i<3;i++){
L[i] = scanner.nextInt();
}

for(int i=0;i<3;i++){
P[i] = scanner.nextInt();
}
int start=50,end=0;
for(int i=0;i<CsLen;i++){
Cs[i][0] = scanner.nextInt();
Cs[i][1] = Cs[i][0] + scanner.nextInt();

if(start >Cs[i][0]){
start = Cs[i][0];
}

if(end < (Cs[i][1]) ){
end = Cs[i][1];
}
}
//Total = 0;
// for(int i=0;i<CsLen;i++){
// System.out.println(Cs[i][0]+" "+Cs[i][1]);
//}
//System.out.println(start+" "+end);
int[] group = new int[3];
int[] visited = new int[3];
Permutation(start,end,Cs,L,P,group,visited,0);

//System.out.println(Cs[0][0]);
System.out.println(Total);
}
}
}
