class UserSolution
{
	static final int MAXL		= 5;
	static final int MAXF		= 10;
	
	class Friends{
		int id;
		class Blocks{
			int id;
			Blocks prev,next;
			Blocks(int i){
				id = i;
			}
		}
		
		int CAP = 100;//hash table capacity
		Blocks[] FriendList = new Blocks[CAP];
		
		Friends(int i) {
			id = i;
		}
		
		void getFriends() {
			for(int i=0; i<CAP ; i++) {
				Blocks selected = FriendList[i];
				
				while(selected != null) {
					
					System.out.print(selected.id + " ");
					
					selected = selected.next;
				}
			}
			
			System.out.println();
		}
		
		int[] getFriendsArr(){
			int[] f_temp = new int[FriendList.length];
			
			int tf = 0;
			for(int i=0; i<CAP ; i++) {
				Blocks selected = FriendList[i];
				
				while(selected != null) {
					
					//System.out.print(selected.id + " ");
					f_temp[tf] = selected.id;
					tf++;
					
					selected = selected.next;
				}
			}
			
			int[] f = new int[tf];
			for(int i=0;i<tf;i++) {
				f[i] = f_temp[i];
			}
			return f;
			
		}
		
		
		void addFriends(int key) {
			int cell = key % CAP;
			
			if( FriendList[cell] == null ) {
				FriendList[cell]  = new Blocks(id);
			}
			
			Blocks headBlocks = FriendList[cell];
			
			Blocks newBlocks = new Blocks(key);
			if(headBlocks.next == null) {
				headBlocks.next = newBlocks;
				newBlocks.prev = headBlocks;
			}else {
				Blocks partBlocks = headBlocks.next ;
				headBlocks.next = newBlocks;
				partBlocks.prev = newBlocks;
				
				newBlocks.prev = headBlocks;
				newBlocks.next = partBlocks;
			}
			
		
		}
		
		void deleteFriend(int key) {
			int cell = key % CAP;
			
			Blocks selected = FriendList[cell];
			while(selected != null) {
				if(selected.id == key) {
					Blocks back = selected.prev;
					Blocks front = selected.next;
					
					if(back != null) {
						back.next = front;
					}
					
					if(front != null) {
						front.prev = back;
					}
					
					
					
					break;
				}
				
				selected = selected.next;
			}
			
		}
		
		boolean checkFriend(int key) {
			int cell = key % CAP;
			
			Blocks selected = FriendList[cell];
			while(selected != null) {
				if(selected.id == key) {
					return true;
				}
				
				selected = selected.next;
			}
			
			return false;
		}
		
		
	}
	
	class Recommendation{
		int id;
		int coFriends;
		
		Recommendation next;
	}
	
	int getTotalCoFriends(Friends A, Friends B) {
		int[] listA = A.getFriendsArr();
		int[] listB = B.getFriendsArr();
		int coFriends = 0;
		for(int i=0;i<listA.length;i++) {
			for(int j=0;j<listB.length;j++) {
				if(listA[i] == listB[j]) {
					coFriends++;
					break;
				}
			}
		}
		
		return  coFriends;
	}

	Friends[] userList;
	public void init(int N)
	{
		userList = new Friends[N+1];
		/*
		userList[1] = new Friends(1);
		userList[1].addFriends(2);
		userList[1].addFriends(102);
		userList[1].addFriends(202);
		userList[1].addFriends(3);
		
		userList[1].getFriends();
		
		System.out.println(userList[1].checkFriend(102));
		
		userList[1].deleteFriend(102);
		userList[1].getFriends();
		*/
		/*
		int[] g = userList[1].getFriendsArr();
		for(int i=0;i<g.length;i++) {
			System.out.print(g[i]+" ");
		}System.out.println("_");
		*/
		/*
		userList[5] = new Friends(5);
		userList[5].addFriends(202);
		userList[5].addFriends(3);
		userList[5].addFriends(2);
		
		System.out.println(getTotalCoFriends(userList[1], userList[5]));
		System.out.println(userList[1].checkFriend(102));
		*/
	}
	
	
	public void add(int id, int F, int ids[])
	{
		//		System.out.println("add")
		
		//System.out.println("F "+id);
		if(userList[id] == null) {
			userList[id] = new Friends(id);
		}
		
		for(int i=0;i<F;i++) {
			if(userList[ids[i]] == null) {
				userList[ids[i]] = new Friends(ids[i]);
			}
			//System.out.print(ids[i]+" ");
			
			userList[id].addFriends(ids[i]);
			userList[ids[i]].addFriends(id);
		}
		
		//System.out.println();
	}
	
	public void del(int id1, int id2)
	{
		//		System.out.println("del");
		userList[id1].deleteFriend(id2);
		userList[id2].deleteFriend(id1);
	}
	
	
	public int recommend(int id, int list[])
	{
		//			System.out.println("test");
		Recommendation reclist = null;
		
		int minCo = 0;
		for(int i=1;i<userList.length;i++) {
			if(userList[i] == null || i == id || userList[id].checkFriend(i)) {
				continue;
			}
			
			int countCoFriends = getTotalCoFriends(userList[id], userList[i]);
			//System.out.println(id+" "+i+" "+countCoFriends );
			if(countCoFriends >= minCo) {
				//System.out.println("ok");
				minCo = countCoFriends;
				if(reclist == null) {
					//System.out.println("first");
					reclist = new Recommendation();
					reclist.id = i; 
					reclist.coFriends = countCoFriends;
				}else {
					int dive = 0;
					
					Recommendation current = reclist;
					Recommendation prev = null;
					while( dive < 5 ) {
						if(countCoFriends > current.coFriends || (countCoFriends == current.coFriends && i > current.id ) ) {
							
							Recommendation newRec = new Recommendation();
							newRec.id = i;
							newRec.coFriends = countCoFriends;
							
							newRec.next = current;
							
							if(prev == null) {	
								reclist = newRec;
							}else {
								prev.next = newRec;
							}
							
							
							break;
						}
						
						dive++;
						prev = current;
						current = current.next;
					}
					
				}
			}
				
			
			
		}
		
		int totalRec = 0;
		for(int i=0;i<5;i++) {
			
			if(reclist != null) {
				list[i] = reclist.id;
				//System.out.print(reclist.id+" ");
				totalRec++;
				reclist = reclist.next;
			}
			
			
		}//System.out.println();
		
		if(totalRec >= 5) {
			return 5;
		}else {
			return totalRec;
		}
		
	}
}
