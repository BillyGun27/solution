ans
2250000 (440+190+450+420)
3748096 (528+461+477+470)
3928324 (552+418+541+471)
7236100 (716+713+579+682)
13104400 (977+954+855+834)
/*
5
5 3
300 410 150 55 370
120 185 440 190 450
165 70 95 420 50
5 5
356 55 41 453 12
401 506 274 506 379
360 281 421 311 489
425 74 276 371 164
138 528 461 477 470
13 3
197 51 443 274 471 552 160 96 501 102 469 318 308
516 128 506 471 381 418 328 517 380 78 569 58 90
113 238 79 444 541 27 444 62 264 93 245 353 37
7 11
292 182 586 607 259 190 239
511 716 425 367 511 462 714
593 713 231 60 118 442 82
626 577 579 682 136 176 681
240 23 410 193 230 729 109
453 231 287 383 444 578 409
729 401 408 330 213 574 54
684 224 75 62 660 472 227
606 37 473 487 222 185 476
84 477 158 94 141 484 122
616 333 302 626 29 99 674
15 15
488 923 92 659 783 908 167 332 467 205 457 871 536 189 642
676 729 520 687 276 13 709 305 315 621 19 606 201 722 671
631 829 973 318 487 140 411 633 530 981 594 372 787 586 895
520 938 376 770 495 310 59 820 840 785 457 454 967 178 507
498 368 377 326 247 79 875 38 778 800 205 186 131 543 948
672 530 848 342 397 751 192 265 163 447 869 223 950 636 34
669 929 802 500 979 978 322 185 598 618 663 192 746 289 44
77 271 943 874 211 532 441 567 396 141 527 286 755 95 206
458 803 319 490 384 736 328 977 954 651 975 472 405 344 189
624 725 838 159 624 269 400 855 63 924 349 711 473 115 446
937 359 820 851 629 698 437 834 18 257 632 534 153 478 908
205 875 185 508 373 826 432 487 522 10 663 870 711 56 941
773 663 954 237 166 957 722 198 346 337 708 592 443 809 41
634 174 193 733 800 227 418 503 903 405 261 805 234 461 191
176 891 203 825 575 508 627 845 610 814 263 159 719 459 903
*/
import java.util.*;

public class Solution {
static int Total;
static int totalHive = 4;
static int[][][] path = {//W,H
{{-1,-1},{0,-1},{1,-1},{-1,0},{1,0},{0,1}},//even
{{0,-1},{-1,0},{1,0},{-1,1},{0,1},{1,1}}//odd
};
static void LinearGroup(int H,int W,int[][] hive,int[][] member,int dist , int curH,int curW, int[][] visited){
if(dist>=totalHive){
int tot=0;
for(int i=0;i<totalHive;i++){
tot+= hive[member[i][0]][member[i][1]];
}
tot = tot*tot;
if(Total<tot){
for(int i=0;i<totalHive;i++){
System.out.print( hive[member[i][0]][member[i][1]] +" ");
}System.out.println("l"+tot);
Total = tot;
}

}else{
/*
System.out.println(" ")
for(int i=0;i<H;i++){
for(int j=0;j<W;j++){
System.out.print(visited[ nextH ][ nextH ]+" ")
}
}
System.out.println(" ")*/
int Par = curW % 2;
int n = 0;
for(int i=0;i<path[Par].length;i++){
int nextH = curH + path[Par][i][1];
int nextW = curW + path[Par][i][0];
if( (0 <= nextH && nextH < H) && (0 <= nextW && nextW < W) ){
member[dist][0] = nextH;
member[dist][1] = nextW;
if(visited[ nextH ][ nextW ] == 0){
//System.out.print("("+visited[ nextH ][ nextW ]+" "+hive[nextH][nextW]+" "+nextH+","+nextW+" vv)");
visited[ nextH ][ nextW ] = 1;
LinearGroup(H,W,hive,member,dist+1, nextH , nextW ,visited);
visited[ nextH ][ nextW ] = 0;
}
}
}
}
}
static void NeighborGroup(int H,int W,int[][] hive,int[][] member,int dist,int[][] neighbor,int n,int nprog, int[][] visited){
if(dist>=totalHive){
int tot=0;
for(int i=0;i<totalHive;i++){
tot+= hive[member[i][0]][member[i][1]];
}
tot = tot*tot;
if(Total<tot){
for(int i=0;i<totalHive;i++){
System.out.print( hive[member[i][0]][member[i][1]] +" ");
}System.out.println("n"+tot);
Total = tot;
}

}else{
// for(int i=0;i<dist;i++){
// System.out.print( hive[member[i][0]][member[i][1]] +" ");
// }System.out.println("s nprog"+nprog+" n"+n);

//System.out.print("("+ visited[member[0][0]][member[0][1]]+" "+hive[member[0][0]][member[0][1]]+" "+member[0][0]+","+member[0][1]+" v)");
for(int i=nprog;i<n;i++){
//System.out.println(i+" nprog"+nprog+" n"+n+" dist"+dist);
int nH = neighbor[i][0];//H
int nW = neighbor[i][1];//W
member[dist][0] = nH;
member[dist][1] = nW;
//if(visited[ nH ][ nW ] == 0){
NeighborGroup(H,W,hive,member,dist+1,neighbor,n,i+1,visited);
if(dist+1 < totalHive){
//visited[ nH ][ nW ] = 1;
//System.out.print("("+visited[ nH ][ nW ]+" "+hive[nH][nW]+" "+nH+","+nW+" v)");
LinearGroup(H,W,hive,member,dist+1, nH , nW ,visited);
//visited[ nH ][ nW ] = 0;
//System.out.println(" ");
}
//}
//System.out.println("new");
}
}
}

static void NeighborFind(int H,int W,int[][] hive,int curH,int curW, int[][] member,int dist){
int[][] neighbor = new int[6][2];//H,W
//x%2 0=even 1=odd

int Par = curW % 2;
int n = 0;
int visited[][] = new int[H][W];
visited[curH][curW] = 1;
for(int i=0;i<path[Par].length;i++){
int nextH = curH + path[Par][i][1];
int nextW = curW + path[Par][i][0];
//System.out.println("b "+nextH+","+nextW);
if( (0 <= nextH && nextH < H) && (0 <= nextW && nextW < W) ){
//System.out.println("a "+nextH+","+nextW);
neighbor[n][0] = nextH;
neighbor[n][1] = nextW;

visited[nextH][nextW] = 1;
n++;
}
}

NeighborGroup(H,W,hive,member,dist,neighbor,n,0,visited);
//for(int i=0;i<n;i++){
// System.out.print(neighbor[i][0]+","+neighbor[i][1]+" | ");
//}System.out.println();


}
public static void main(String[] args) {
/* Enter your code here. Print output to STDOUT. Your class should be named Solution. */

Scanner scanner = new Scanner(System.in);
int T = scanner.nextInt();
for(int t=0;t<T;t++){
int W = scanner.nextInt();
int H = scanner.nextInt();
int[][] hive = new int[H][W];

for(int i=0;i<H;i++){
for(int j=0;j<W;j++){
hive[i][j] = scanner.nextInt();
}
}

Total = 0;
for(int i=0;i<H;i++){
for(int j=0;j<W;j++){
int curH = i;
int curW = j;
int[][] member = new int[4][2];
int dist = 0;
member[dist][0] = curH;//hive[0][0];
member[dist][1] = curW;//hive[0][1];
NeighborFind(H,W,hive,curH,curW,member,dist+1);
}
}
System.out.println(Total);
}
}
}

