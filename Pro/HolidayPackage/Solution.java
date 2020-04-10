import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

interface SRINsolution		// SRIN: to simplify switching solutions (for benchmarking purpose)
{	
	void init(int N, int M);
	void befriend(int uid1, int uid2);
	void add(int pid, int area, int price);
	void reserve(int uid, int pid);
	int recommend(int uid);
}

class Solution
{
	private final static int INIT				= 100;
	private final static int BEFRIEND			= 200;
	private final static int ADD				= 300;
	private final static int RESERVE			= 400;
	private final static int RECOMMEND			= 500;
		
	// private final 
	static UserSolution usersolution = new UserSolution();
	// static SRINsolution usersolution;
		
	private static boolean run(BufferedReader br) throws Exception
	{
		StringTokenizer st;
		
		int L, N, M;
		boolean okay = false;
		
		int pid, uid;
		int uid1, uid2;
		int area, price;
		
		int ans;
		int cmd, ret;
		
		L = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < L; ++i)
		{
			st = new StringTokenizer(br.readLine(), " ");
			cmd = Integer.parseInt(st.nextToken());
			switch(cmd)
			{
			case INIT:
				N = Integer.parseInt(st.nextToken());
				M = Integer.parseInt(st.nextToken());

				//if (!SRINtimer.isCompare) SRINtimer.timer.timerStart(SRINtimer.timerSlot = 1);
				usersolution.init(N, M);
				okay = true;
				break;
			case BEFRIEND:
				uid1 = Integer.parseInt(st.nextToken());
				uid2 = Integer.parseInt(st.nextToken());

				//if (!SRINtimer.isCompare) SRINtimer.timer.timerStart(SRINtimer.timerSlot = 2);
				usersolution.befriend(uid1, uid2);
				break;
			case ADD:
				pid = Integer.parseInt(st.nextToken());
				area = Integer.parseInt(st.nextToken());
				price = Integer.parseInt(st.nextToken());

				//if (!SRINtimer.isCompare) SRINtimer.timer.timerStart(SRINtimer.timerSlot = 3);
				usersolution.add(pid, area, price);
				break;
			case RESERVE:
				uid = Integer.parseInt(st.nextToken());
				pid = Integer.parseInt(st.nextToken());
				
				//if (!SRINtimer.isCompare) SRINtimer.timer.timerStart(SRINtimer.timerSlot = 4);
				usersolution.reserve(uid, pid);
				break;
			case RECOMMEND:
				uid = Integer.parseInt(st.nextToken());
				ans = Integer.parseInt(st.nextToken());

				//if (!SRINtimer.isCompare) SRINtimer.timer.timerStart(SRINtimer.timerSlot = 5);
				
				ret = usersolution.recommend(uid);
				/*
				if(ans==396308286) {
					System.out.println("ans : " + ans + ", ret : " + ret);
				
					int rr = usersolution.recommend_debug(uid);
					first = false;
				}*/
				
				if (ret != ans) {
					//if(first) {
					//	System.out.println("ans : " + ans + ", ret : " + ret);
						
					//	int rr = usersolution.recommend_debug(uid);
					//	first = false;
					//}
					
					okay = false;
				}
				break;
			}
			//if (!SRINtimer.isCompare) SRINtimer.timer.timerStop(SRINtimer.timerSlot);

		}
		
		return okay;
	}
	
	public static void main(String[] args) throws Exception {
		int TC, MARK;
		// long stt = System.currentTimeMillis();
	
		System.setIn(new java.io.FileInputStream("src/sample_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		TC = Integer.parseInt(st.nextToken());
		MARK = Integer.parseInt(st.nextToken());

		for (int testcase = 1; testcase <= TC; ++testcase) {
			int score = run(br) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
		}

		br.close();
		// System.out.println(System.currentTimeMillis()-stt);
	}
}
