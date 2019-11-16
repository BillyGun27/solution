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

ans
2250000 (440+190+450+420)
3748096 (528+461+477+470)
3928324 (552+418+541+471)
7236100 (716+713+579+682)
13104400 (977+954+855+834)
*/
import java.util.*;

public class Solution {
    static int Total;
    static int totalHive = 4;
    static int[][][] path = {//W,H
        {{-1,-1},{0,-1},{1,-1},{-1,0},{1,0},{0,1}},//even
        {{0,-1},{-1,0},{1,0},{-1,1},{0,1},{1,1}}//odd
    };

    static void NeighLin(int H,int W,int[][] Quad,int cH,int cW,int[][] visited,int[] userNum,int dist){
        if(dist>=totalHive){
            int tot = 0;
            for(int i=0;i<totalHive;i++){
                tot+=userNum[i];
            }
            tot = tot*tot;
            if( Total < tot ){
            //    for(int i=0;i<totalHive;i++){
            //        System.out.print(userNum[i]+" ");
            //    }System.out.println("l "+ (tot) );
                Total = tot;
            }
        }else{
            int par = cW % 2;
            for(int p=0;p<6;p++){
                int nH = cH + path[par][p][1];
                int nW = cW + path[par][p][0];
                if( (0<=nH && nH<H) && (0<=nW && nW<W) ){
                    if(visited[nH][nW]==0){
                        visited[nH][nW] = 1;
                        userNum[dist] = Quad[nH][nW];
                        NeighLin(H,W,Quad,nH,nW,visited,userNum,dist+1);
                        visited[nH][nW] = 0;
                    }
                }
            }
        }
    }

    static void NeighFind(int H,int W,int[][] Quad,int cH,int cW,int[][] neighbor,int n,int c,int[][] visited,int[] userNum,int dist){
        if(dist>=totalHive){
            int tot = 0;
            for(int i=0;i<totalHive;i++){
                //System.out.print(userNum[i]+" ");
                tot+=userNum[i];
            }//System.out.println("n "+(tot*tot));
            
            tot = tot*tot;
            if( Total < tot ){
                Total = tot;
            }
        }else{
       
            for(int i=c;i<n;i++){
                userNum[dist] = Quad[neighbor[i][0]][neighbor[i][1]];
                NeighFind(H,W,Quad,neighbor[i][0],neighbor[i][1],neighbor,n,i+1,visited,userNum,dist+1);
                if(dist+1 < totalHive){
                    NeighLin(H,W,Quad,neighbor[i][0],neighbor[i][1],visited,userNum,dist+1);
                }
            }
        }
        
    }

    static void NeighGroup(int H,int W,int[][] Quad,int cH,int cW){
        int n=0;
        int[][] neighbor = new int[6][2];
        int par = cW % 2;
        int[][] visited = new int[H][W];
        visited[cH][cW] = 1;
        for(int p=0;p<6;p++){
            int nH = cH + path[par][p][1];
            int nW = cW + path[par][p][0];
            if( (0<=nH && nH<H) && (0<=nW && nW<W) ){
                visited[nH][nW] = 1;
                neighbor[n][0] = nH;
                neighbor[n][1] = nW; 
                n++;
            }
        }

        //System.out.print(Quad[cH][cW]+" ");
        //for(int i=0;i<n;i++){
        //    System.out.print(Quad[neighbor[i][0]][neighbor[i][1]]+" ");
        //}System.out.println();    

        int[] userNum = new int[4];
        userNum[0] = Quad[cH][cW];
        NeighFind(H,W,Quad,cH,cW,neighbor,n,0,visited,userNum,1);
    }

    public static void main(String[] args) {
        /* Enter your code here. Print output to STDOUT. Your class should be named Solution. */

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int t=0;t<T;t++){
            int W = sc.nextInt();
            int H = sc.nextInt();
            int[][] Quad = new int[H][W];

            for(int i=0;i<H;i++){
                for(int j=0;j<W;j++){
                    Quad[i][j] = sc.nextInt();
                }
            }

            Total = 0;
            for(int i=0;i<H;i++){
                for(int j=0;j<W;j++){
                    NeighGroup(H,W,Quad,i,j);
                }
            }

            System.out.println(Total);
        }

    }
}
