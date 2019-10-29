/*
ID: anaiyso1
LANG: JAVA
PROG: gift1
 */
import java.io.*;
import java.util.*;
public class gift1 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("gift1.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int n = Integer.parseInt(st.nextToken());
		String[] names = new String[n];
		int[] bank = new int[n];
		boolean check = false;
		int money = 0;
		int moneysplit = 0;
		ArrayList<String> namessharing 	= new ArrayList<String>();
		int peoplesharing = 0;
		for(int i = 0; i < n; i ++)
		{
			names[i] = f.readLine();
		}
		for(int i = 0; i < n; i ++)	
		{
			String temp = f.readLine();
			st = new StringTokenizer(f.readLine());
			money = Integer.parseInt(st.nextToken());
			peoplesharing = Integer.parseInt(st.nextToken());
			for(int j = 0; j < peoplesharing; j ++)
			{
				namessharing.add(f.readLine());
			}
			if(peoplesharing != 0)
			{
				moneysplit = (int) money / (int) peoplesharing;
				check = true;
			}
			for(int j = 0; j < n; j ++)
			{
				if(temp.equals(names[j]))
				{
					if(check)
					{
						bank[j] += ((money % peoplesharing) - money);
					}
					break;
				}
			}
			
			for(int j = 0; j < peoplesharing; j ++)
			{
				for(int k = 0; k < n; k ++)
				{
					if(namessharing.get(j).equals(names[k]))
					{
						bank[k] += moneysplit;
					}
				}
			}	
			namessharing.clear();
			moneysplit = 0;
			money = 0;
			check = false;
		}
		for(int i = 0; i < bank.length; i ++)
		{
			out.println(names[i] + " "+ bank[i]);
		}
		out.close();
	}
}
