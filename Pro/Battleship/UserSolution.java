class UserSolution
{
	// Main API :
	//   Solution.fire(int r, int c)

	private final static int MISS 	= 6;
	private final static int IDCARRIER 	= 1;
	private final static int IDBATTLESHIP = 2;
	private final static int IDCRUISER 	= 3;
	private final static int IDSUBMARINE 	= 4;
	private final static int IDDESTROYER 	= 5;
	
	private final static int[] MAXLIFE = {0,5,4,3,3,2};
	
	private int[][] BattleArea ;
	private int maxLimit;
	
	public void init(int limit)
	{
		maxLimit = limit;
	}
	
	
	
	public void play()
	{
		BattleArea = new int[10][10];
		int shootFired = 0;
		int totalShipHit = 0;
		//int[] Numb = {5,4,1,8,2,7,3,6,0,9};
		int[] Numb = {5,2,7,1,8,3,6,0,9,4};
		//RANDOM SCAN
		for(int cy = 0;cy<10;cy++) {
			for(int rx = 0;rx<10;rx++) {
				int c = Numb[cy];
				int r = Numb[rx];
				
				if(totalShipHit==5) {
					break;
				}
				if( (c%2==0 && r%2==0) || (c%2!=0 && r%2!=0) ) {
					if(BattleArea[c][r] == 0 ) {
						int shoot = Solution.fire(r, c);
						shootFired++;
						if(shoot == 0) {
							BattleArea[c][r] = 6; 
						}else {
							BattleArea[c][r] = shoot;
							shootFired += destroyShip(BattleArea,c,r,shoot);
							//System.out.println("found "+shoot+" "+shootFired);
							totalShipHit++;
						}
					}	
				}
				
			}
		}
		/*
		int totalSucceed = 0;
		for(int i = 0;i<10;i++) {
			for(int j = 0;j<10;j++) {
				int res = BattleArea[i][j];
				System.out.print(res+" ");
				if(BattleArea[i][j] != 0 && BattleArea[i][j]!=6) {
					totalSucceed++;
				}
				
			}
			System.out.println("");
		}
		
		System.out.println("------------------|"+shootFired+"|"+totalSucceed);
		*/
		
		System.out.println(shootFired);
	}
	
	public int destroyShip(int[][] BattleArea, int c,int r,int type){
		int TotalLife = MAXLIFE[type]-1;
		int shootFired = 0;
		
		if(c > 10-c) {
			//up
			for(int cc=c-1;cc>=0;cc--) {
				if( TotalLife == 0 || BattleArea[cc][r] == 6 ) { //|| (BattleArea[cc][r] != 0 && BattleArea[cc][r] != type)
					break;
				}
				if(BattleArea[cc][r] == 0 ) {
					int shoot = Solution.fire(r, cc);
					shootFired++;
					if(shoot == 0) {
						BattleArea[cc][r] = 6; 
						break;
					}else {
						BattleArea[cc][r] = shoot;
						if(shoot == type) {
							TotalLife--;
						}
					}
				}	
			}
			//down
			for(int cc=c+1;cc<10;cc++) {
				if(TotalLife == 0 || BattleArea[cc][r] == 6 ) { //|| (BattleArea[cc][r] != 0 && BattleArea[cc][r] != type )
					break;
				}
				if(BattleArea[cc][r] == 0 ) {
					int shoot = Solution.fire(r, cc);
					shootFired++;
					if(shoot == 0) {
						BattleArea[cc][r] = 6; 
						break;
					}else {
						BattleArea[cc][r] = shoot;
						if(shoot == type) {
							TotalLife--;
						}
					}
				}	
			}
		}else {
			//down
			for(int cc=c+1;cc<10;cc++) {
				if(TotalLife == 0 || BattleArea[cc][r] == 6 ) { //|| (BattleArea[cc][r] != 0 && BattleArea[cc][r] != type )
					break;
				}
				if(BattleArea[cc][r] == 0 ) {
					int shoot = Solution.fire(r, cc);
					shootFired++;
					if(shoot == 0) {
						BattleArea[cc][r] = 6; 
						break;
					}else {
						BattleArea[cc][r] = shoot;
						if(shoot == type) {
							TotalLife--;
						}
					}
				}	
			}
			//up
			for(int cc=c-1;cc>=0;cc--) {
				if( TotalLife == 0 || BattleArea[cc][r] == 6 ) { //|| (BattleArea[cc][r] != 0 && BattleArea[cc][r] != type)
					break;
				}
				if(BattleArea[cc][r] == 0 ) {
					int shoot = Solution.fire(r, cc);
					shootFired++;
					if(shoot == 0) {
						BattleArea[cc][r] = 6; 
						break;
					}else {
						BattleArea[cc][r] = shoot;
						if(shoot == type) {
							TotalLife--;
						}
					}
				}	
			}
			
		}
		
		if(r > 10-r) {
			//left
			for(int rr=r-1;rr>=0;rr--) {
				if(TotalLife == 0 || BattleArea[c][rr] == 6 ) {//|| (BattleArea[c][rr] != 0 && BattleArea[c][rr] != type)
					break;
				}
				if(BattleArea[c][rr] == 0 ) {
					int shoot = Solution.fire(rr, c);
					shootFired++;
					if(shoot == 0) {
						BattleArea[c][rr] = 6; 
						break;
					}else {
						BattleArea[c][rr] = shoot;
						if(shoot == type) {
							TotalLife--;
						}
					}
				}	
			}
			//right
			for(int rr=r+1;rr<10;rr++) {
				if(TotalLife == 0 || BattleArea[c][rr] == 6 ) {//|| (BattleArea[c][rr] != 0 && BattleArea[c][rr] != type)
					break;
				}
				if(BattleArea[c][rr] == 0 ) {
					int shoot = Solution.fire(rr, c);
					shootFired++;
					if(shoot == 0) {
						BattleArea[c][rr] = 6; 
						break;
					}else {
						BattleArea[c][rr] = shoot;
						if(shoot == type) {
							TotalLife--;
						}
					}
				}	
			}
		
		}else {
			//right
			for(int rr=r+1;rr<10;rr++) {
				if(TotalLife == 0 || BattleArea[c][rr] == 6 ) {//|| (BattleArea[c][rr] != 0 && BattleArea[c][rr] != type)
					break;
				}
				if(BattleArea[c][rr] == 0 ) {
					int shoot = Solution.fire(rr, c);
					shootFired++;
					if(shoot == 0) {
						BattleArea[c][rr] = 6; 
						break;
					}else {
						BattleArea[c][rr] = shoot;
						if(shoot == type) {
							TotalLife--;
						}
					}
				}	
			}
			//left
			for(int rr=r-1;rr>=0;rr--) {
				if(TotalLife == 0 || BattleArea[c][rr] == 6 ) {//|| (BattleArea[c][rr] != 0 && BattleArea[c][rr] != type)
					break;
				}
				if(BattleArea[c][rr] == 0 ) {
					int shoot = Solution.fire(rr, c);
					shootFired++;
					if(shoot == 0) {
						BattleArea[c][rr] = 6; 
						break;
					}else {
						BattleArea[c][rr] = shoot;
						if(shoot == type) {
							TotalLife--;
						}
					}
				}	
			}
		}
		
		
		
		return shootFired;
	}
	
	public int RandomGenerator(int min, int max) {
		double rand = Math.random();
		return (int)(rand * ( (max-min) + 1 ) ) + min;
	}
	
	// play test
	public void playf()
	{
		for(int c = 0;c<10;c++) {
			for(int r = 0;r<10;r++) {
				int res = Solution.fire(r, c);
				System.out.print(res+" ");
			}
			System.out.println("");
		}
		
		System.out.println("------------------");
	}
	
}
