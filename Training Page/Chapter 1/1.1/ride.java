/*
ID: anaiyso1
LANG: JAVA
PROG: ride
 */
import java.io.*;
import java.util.*;
public class ride 
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("ride.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    double numc = 1;
	    double numg = 1;
	    String comet = st.nextToken();
	    String group = f.readLine();
	    for(int i = 0; i < comet.length(); i ++)
	    {
	    	numc *= (alphabet.indexOf(comet.substring(i, i + 1)) + 1);
	    }
	    for(int i = 0; i < group.length(); i ++)
	    {
	    	numg *= (alphabet.indexOf(comet.substring(i, i + 1)) + 1);
	    }
	    if(numc % 47 == numg % 47)
	    {
	    	out.println("GO");
	    }
	    else
	    {
	    	out.println("STAY");
	    }
	    out.close();
	}
}
