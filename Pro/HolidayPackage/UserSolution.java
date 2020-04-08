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
					break;
				}
				
				selectedAddr = selectedAddr.next;
			}
			
			selectedAddr.next = new pack(pid,price,area);
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
		/*
		if(area == 8) {
			System.out.println("add");
			pack tempo = PackageList[area];
			
			while(tempo != null) {
				
				System.out.println("id:"+tempo.id+" price:"+tempo.price+" "+area);
				
				tempo = tempo.next;
			}
			System.out.println("add--");
		}*/
		
		
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
		/*
		if(uid==723 && pid==604351784) {
			
			System.out.println("first"+PackageList[selectedArea].id+" "+selectedArea);
			pack tempo = PackageList[selectedArea];
					
			while(tempo != null) {
				
				System.out.println("id:"+tempo.id+" price:"+tempo.price+" "+selectedArea);
				
				tempo = tempo.next;
			}
		}*/
		
		pack selectedPack = PackageList[selectedArea];
		pack prev = null;
		while(selectedPack != null) {
			
			if(selectedPack.id == pid) {
				/*
				if(uid==723 && pid==604351784) {
					System.out.println("other"+selectedPack.id +" "+selectedArea);
				}*/
				break;
			}
			
			prev = selectedPack;
			selectedPack = selectedPack.next;
		}
		
		if(prev == null) {
			/*
			if(uid==723 && pid==604351784) {
				System.out.println("prev");
			}*/
			PackageList[selectedArea] = selectedPack.next;
		}else {
			/*
			if(uid==723 && pid==604351784) {
				System.out.println("second prev ");
				if(selectedPack.next==null) {
					System.out.println("second prev is null"+prev.id);
				}
			}*/
			prev.next = selectedPack.next;
		}
		
		/*
		if(uid==723 && pid==604351784) {
			System.out.println("second "+PackageList[selectedArea].id+" "+selectedArea);
			pack tempo = PackageList[selectedArea];
			
			while(tempo != null) {
				
				System.out.println("id:"+tempo.id+" price:"+tempo.price+" "+selectedArea);
				
				tempo = tempo.next;
			}
		}*/
		
		if(UserList[uid] == null) {
			//System.out.println("reserve uid "+uid);
			UserList[uid] = new user(userLen,areaLen);
		}
		
		boolean check = false;
		if(uid == 535) {
			check = true;
		}
		UserList[uid].area[selectedArea]++;
		int ft = UserList[uid].friends.length;
		for(int i=1;i<ft;i++) {
			if(UserList[uid].friends[i] == 1) {
				int fuid = i;
				if(UserList[fuid] == null) {
					UserList[fuid] = new user(userLen,areaLen);
				}
				UserList[fuid].area[selectedArea]++;
				if(fuid == 535) {
					check = true;
				}
				//System.out.println("result : "+fuid+" "+ UserList[fuid].area[selectedArea] );
			}
			 
		}
		
		
		
		if(selectedArea == 4 && check ) {
			System.out.println("id "+uid);
			//for(int i=1;i<areaLen;i++) {
			System.out.println("area: "+4+" total:"+UserList[535].area[4]);
				 
			//}
			System.out.println("id--");
		}
		
		//System.out.println("result : "+uid+" "+ UserList[uid].area[selectedArea] );
		
	}
	
	static boolean first = true;
	public int recommend(int uid)
	{	
		if(UserList[uid] == null) {
			//System.out.println("recommend uid "+uid);
			UserList[uid] = new user(userLen,areaLen);
		}
		
		int[] Visited = new int[areaLen];
		int[] AreaTotal = UserList[uid].area;
		
		int selectedArea = 0;
		int checked = 0;
		
		//if(first) {
		/*for(int i=1;i<areaLen;i++) {
			if(PackageList[i] != null) {
				System.out.print("id:"+PackageList[i].id+" price:"+PackageList[i].price);
			}else {
				System.out.print("id: - ");
			}
			System.out.println(" check total_visit:"+AreaTotal[i]+" area:"+i);
		}*/
		//}
		
		//System.out.print("user:"+ uid+" |");
		while(checked<areaLen) {
			int max = -1;
			int minPrice = Integer.MAX_VALUE;
			for(int i=1;i<areaLen;i++) {
				//System.out.println("check "+AreaTotal[i]+" "+i);
				if( max<AreaTotal[i] && Visited[i] == 0 && PackageList[i] != null) {
					//System.out.println("max "+AreaTotal[i]+" "+i);
					max = AreaTotal[i];
					selectedArea = i;
					minPrice =  PackageList[selectedArea].price;
				}else if(max == AreaTotal[i] && Visited[i] == 0 && PackageList[i] != null) {
					//System.out.println("other max "+AreaTotal[i]+" "+i+"p "+minPrice+" np"+PackageList[i].price);
					if(minPrice > PackageList[i].price ) {
						max = AreaTotal[i];
						selectedArea = i;
						minPrice =  PackageList[selectedArea].price;
					}
				}
				
			}
			
			//System.out.println("user:"+uid+" selected area:"+selectedArea +" ");
			Visited[selectedArea] = 1;
			if(PackageList[selectedArea] != null) {
				//System.out.print("id:"+PackageList[selectedArea].id  +" ");
				//if(first) {
				//	System.out.println("user:"+uid+" area: "+selectedArea+" recommend:"+PackageList[selectedArea].id);
					//System.out.println("ll");
				//	first = false;
				//}
				
				
				return PackageList[selectedArea].id ;
			}
			
			//System.out.println();
			checked++;
			
		}
		//System.out.println("recommend fail");
		return 0;
		
	}
	
	public int recommend_debug(int uid)
	{	
		if(UserList[uid] == null) {
			//System.out.println("recommend uid "+uid);
			UserList[uid] = new user(userLen,areaLen);
		}
		
		int[] Visited = new int[areaLen];
		int[] AreaTotal = UserList[uid].area;
		
		int selectedArea = 0;
		int checked = 0;
		
	//	if(first) {
		System.out.println("compare");
			for(int i=1;i<areaLen;i++) {
				if(PackageList[i] != null) {
					System.out.print("id:"+PackageList[i].id+" price:"+PackageList[i].price);
				}else {
					System.out.print("id: - ");
				}
				System.out.println(" check total_visit:"+AreaTotal[i]+" area:"+i);
			}
		//}
		
		//System.out.print("user:"+ uid+" |");
		while(checked<areaLen) {
			int max = -1;
			int minPrice = Integer.MAX_VALUE;
			for(int i=1;i<areaLen;i++) {
				//System.out.println("check "+AreaTotal[i]+" "+i);
				if( max<AreaTotal[i] && Visited[i] == 0 && PackageList[i] != null) {
					//System.out.println("max "+AreaTotal[i]+" "+i);
					max = AreaTotal[i];
					selectedArea = i;
					minPrice =  PackageList[selectedArea].price;
				}else if(max == AreaTotal[i] && Visited[i] == 0 && PackageList[i] != null) {
					//System.out.println("other max "+AreaTotal[i]+" "+i+"p "+minPrice+" np"+PackageList[i].price);
					if(minPrice > PackageList[i].price ) {
						max = AreaTotal[i];
						selectedArea = i;
						minPrice =  PackageList[selectedArea].price;
					}
				}
				
			}
			
			//System.out.println("user:"+uid+" selected area:"+selectedArea +" ");
			Visited[selectedArea] = 1;
			if(PackageList[selectedArea] != null) {
				//System.out.print("id:"+PackageList[selectedArea].id  +" ");
				//if(first) {
				System.out.println("---");
					System.out.println("user:"+uid+" area: "+selectedArea+" recommend:"+PackageList[selectedArea].id);
					//System.out.println("ll");
				//	first = false;
				//}
				
				
				return PackageList[selectedArea].id ;
			}
			
			//System.out.println();
			checked++;
			
		}
		//System.out.println("recommend fail");
		return 0;
		
	}
}





