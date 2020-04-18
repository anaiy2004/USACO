import java.io.*;
import java.util.*;
public class Meetings
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("meetings.in"));
		StringTokenizer st = new StringTokenizer(f.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("meetings.out")));
		int c = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		int[][] barn = new int[c][3];
		int total = 0;
		int start = 0;
		int end = c - 1;
		int lefts = 0;
		int rights = 0;
		for(int i = 0; i < c; i ++)
		{
			st = new StringTokenizer(f.readLine());
			int weight = Integer.parseInt(st.nextToken());
			total += weight;
			barn[i][0] = weight;
			barn[i][1] = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());
			barn[i][2] = direction;
			if(direction == -1) lefts ++;
			else rights ++;
		}
		int acc = 0;
		Arrays.sort(barn, Comparator.comparingInt(o -> o[1]));
		int[] left = new int[lefts];
		int[] right = new int[rights];
		int j = 0;
		int k = 0;
		//System.out.println(lefts);
		//System.out.println(rights);
		for(int i = 0; i < barn.length; i ++)
		{
			if(barn[i][2] == -1)
			{
				left[j] = barn[i][1];
				j ++;
			}
			if(barn[i][2] == 1)
			{
				right[k] = barn[i][1];
				k ++;
			}
		}
		int T = 0;
		boolean doneLeft = false;
		boolean doneRight = false;
		int	tLeft = 0;
		int tRight = rights - 1;
		int wLeft = 0;
		int wRight = c - 1;
		int totalMeetings = 0;
		int meetingsRight = 0;
		int meetingsLeft = 0;
		while(total  > acc * 2)
		{
			int timeLeft = left[tLeft];
			int timeRight = l - right[tRight];			
			if(timeLeft < timeRight)
			{
				acc += barn[wLeft][0];
				tLeft ++;
				wLeft ++;
				T = timeLeft;
			}
			else if(timeRight < timeLeft)
			{
				acc += barn[wRight][0];
				tRight --;
				wRight --;
				T = timeRight;
			}
			else
			{
				acc += barn[wLeft][0];
				acc +=  barn[wRight][0];
				tLeft ++;
				wLeft ++;
				tRight --;
				wRight --;
				T = timeLeft;
			}
		}
		//System.out.println(T + " " + acc);
		int count = 0;
		int minIndex = 0;
		for(int i = 0; i < right.length; i ++ )
		{
			int min = right[i];
			int max = min + T*2;
			while(minIndex < lefts && left[minIndex] < min)
			{
				minIndex ++;
			}
			for(k = minIndex; k < lefts; k ++)
			{
				int m = left[k];
				if(m <= max && m >= min)
					count ++;
			}
		}
		out.print(count);
		out.close();
	}
}
