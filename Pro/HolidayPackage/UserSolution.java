class UserSolution
{
	class user{
		int[] friends;
		int[] area;
		
		user(int f,int a){
			friends = new int[f];
			area = new int[a];
		}
	}
	
	class pack{
		int id;
		int price;
		int area;
		
		pack next;
		
		pack(int i,int p, int a) {
			id = i;
			price = p;
			area = a;
		}
	}
	
	static user[] UserList ;
	static pack[] PackageList;
	
	static int userLen;
	static int areaLen;
	
	static int CAP = 100;
	static pack[] PackageAddress;
	
	public void init(int N, int M)
	{
		UserList = new user[N+1];
		PackageList = new pack[M+1]; 
		userLen = N+1;
		areaLen = M+1;
		
		PackageAddress = new pack[CAP];
		
	}
	
	public void befriend(int uid1, int uid2)
	{
		
		if(UserList[uid1] == null) {
			//System.out.println("befriend uid1 "+uid1);
			UserList[uid1] = new user(userLen,areaLen);
		}
		if(UserList[uid2] == null) {
			//System.out.println("befriend uid2 "+uid2);
			UserList[uid2] = new user(userLen,areaLen);
		}
		
		UserList[uid1].friends[uid2] = 1;
		UserList[uid2].friends[uid1] = 1;
		
		
	}
	
	public void add(int pid, int area, int price)
	{
		int KEY = pid % CAP;
		
		pack selectedAddr = PackageAddress[KEY];
		
		if(selectedAddr == null) {
			PackageAddress[KEY] = new pack(pid,price,area);
		}else {
			while(selectedAddr != null) {
				if(selectedAddr.next == null) {
					selectedAddr.next = new pack(pid,price,area);
					break;
				}
				
				selectedAddr = selectedAddr.next;
			}
			
		}
		
		
		
		if(PackageList[area] == null) {
			PackageList[area] = new pack(pid,price,area);
		}else {
			pack current = PackageList[area];
			pack prev = null;
			
			pack nPack = new pack(pid,price,area);
			while(current != null) {
				
				if(current.price > price || current.price == price && current.id > pid) {
					nPack.next = current;
					
					if(prev == null) {
						PackageList[area] = nPack;
					}else {
						prev.next = nPack;
					}
					
					break;
				}
				
				if(current.next == null) {
					current.next = nPack;
					break;
				}
				prev = current;
				current = current.next;
			}
			
			
			
		}
	
		
		
	}
	
	public void reserve(int uid, int pid)
	{
		int selectedArea = 0;
		
		int KEY = pid % CAP;
		
		pack selectedAddr = PackageAddress[KEY]; 
		pack prevAddr = null;
		while(selectedAddr != null) {
			
			if(selectedAddr.id == pid) {
				selectedArea = selectedAddr.area;
				break;
			}
			
			prevAddr = selectedAddr;
			selectedAddr = selectedAddr.next;
		}
		
		if(prevAddr == null) {
			PackageAddress[KEY] = selectedAddr.next;
		}else {
			prevAddr.next = selectedAddr.next;
		}
		
		
		pack selectedPack = PackageList[selectedArea];
		pack prev = null;
		while(selectedPack != null) {
			
			if(selectedPack.id == pid) {
				
				break;
			}
			
			prev = selectedPack;
			selectedPack = selectedPack.next;
		}
		
		if(prev == null) {
			
			PackageList[selectedArea] = selectedPack.next;
		}else {
			
			prev.next = selectedPack.next;
		}
		
		
		if(UserList[uid] == null) {
			//System.out.println("reserve uid "+uid);
			UserList[uid] = new user(userLen,areaLen);
		}
		
	
		UserList[uid].area[selectedArea]++;
		
		
	}
	
	static boolean first = true;
	public int recommend(int uid)
	{	
		if(UserList[uid] == null) {
			//System.out.println("recommend uid "+uid);
			UserList[uid] = new user(userLen,areaLen);
		}
		
		int[] AreaTotal = new int[areaLen];
				
		for(int j=1;j<areaLen;j++) {
				AreaTotal[j] += UserList[uid].area[j];
		}	
		
		
		int ft = UserList[uid].friends.length;
		for(int i=1;i<ft;i++) {
			if(UserList[uid].friends[i] == 1) {
				int fuid = i;
				
				for(int j=1;j<areaLen;j++) {
					AreaTotal[j] += UserList[fuid].area[j];
				}
			}	 
		}
		
		int selectedArea = 0;
		
		
		int max = -1;
		int minPrice = Integer.MAX_VALUE;
		int curPId = 0;
		for(int i=1;i<areaLen;i++) {
			
			if( max<AreaTotal[i]  && PackageList[i] != null) {//
				
				max = AreaTotal[i];
				selectedArea = i;
				minPrice =  PackageList[selectedArea].price;
				curPId =  PackageList[selectedArea].id;
			}else if(max == AreaTotal[i] && PackageList[i] != null) {//&& Visited[i] == 0 
				
				if(minPrice > PackageList[i].price ) {
					max = AreaTotal[i];
					selectedArea = i;
					minPrice =  PackageList[selectedArea].price;
					curPId =  PackageList[selectedArea].id;
				}else if(minPrice == PackageList[i].price && curPId > PackageList[i].id) {
					max = AreaTotal[i];
					selectedArea = i;
					minPrice =  PackageList[selectedArea].price;
					curPId =  PackageList[selectedArea].id;
				}
				
			}
			
		}
		
		
		
		if(PackageList[selectedArea] != null) {
			
			return PackageList[selectedArea].id ;
		}
			
	
		return 0;
		
	}
	
	
}





