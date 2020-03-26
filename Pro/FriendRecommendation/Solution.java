import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	private final static int MAXL		= 5;
	private final static int MAXF		= 10;
	
	private final static int INIT		= 1;
	private final static int ADD		= 2;
	private final static int DEL		= 3;
	private final static int RECOMMEND	= 4;
	
	private static int N, M;
	
	private final static UserSolution usersolution = new UserSolution();

	private static boolean run(BufferedReader br) throws Exception
	{
		int cmd;
		int id, F;
		int ids[] = new int[MAXF];
		int id1, id2;
		int len, len_a;
		int list[] = new int[MAXL];
		int list_a[] = new int[MAXL];
		
		boolean okay;
		
		okay = false;
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		
		for (int k = 0; k < M; ++k)
		{
			st = new StringTokenizer(br.readLine(), " ");
			
			cmd = Integer.parseInt(st.nextToken());
			switch(cmd)
			{
			case INIT:
				N = Integer.parseInt(st.nextToken());
				usersolution.init(N);
				okay = true;
				break;
			case ADD:
				id = Integer.parseInt(st.nextToken());
				F = Integer.parseInt(st.nextToken());
				for (int i = 0; i < F; ++i){
					ids[i] = Integer.parseInt(st.nextToken());
				}
				if (okay)
					usersolution.add(id, F, ids);
				break;
			case DEL:
				id1 = Integer.parseInt(st.nextToken());
				id2 = Integer.parseInt(st.nextToken());
				if (okay)
					usersolution.del(id1, id2);
				break;
				
			case RECOMMEND:
				id = Integer.parseInt(st.nextToken());
				len_a = Integer.parseInt(st.nextToken());
				for (int i = 0; i < len_a; ++i)
					list_a[i] = Integer.parseInt(st.nextToken());
				if (okay)
				{
					len = usersolution.recommend(id, list);
					if (len != len_a)
						okay = false;
					for (int i = 0; okay && i < len_a; ++i)
						if (list[i] != list_a[i])
							okay = false;
				}
				break;
			}
		}

		return okay;
	}
	
	public static void main(String[] args) throws Exception
	{
		int TC, MARK;
	
		System.setIn(new java.io.FileInputStream("sample_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		long startTime = System.currentTimeMillis();
		
		TC = Integer.parseInt(st.nextToken());
		MARK = Integer.parseInt(st.nextToken());

		for (int testcase = 1; testcase <= TC; ++testcase) {
			int score = run(br) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
		}
		
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("time "+elapsedTime);

		br.close();
	}
}
