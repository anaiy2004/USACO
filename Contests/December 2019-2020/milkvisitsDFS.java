import java.io.*;
import java.util.*;
class Node
{
	int componentID;
	Node parent;
	int level;
	char type;
	int data;
	boolean visited;
	List<Node> neighbors;
	public Node(int data , char type)
	{
		this.data = data;
		visited = false;
		neighbors = new ArrayList<Node>();
		this.type = type;
	}
	public void addNeighbor(Node x)
	{	
		neighbors.add(x);
	}
	public List	getNeighbours()
	{
		return neighbors;
	}
	public void setNeighbors(List neighbors)
	{
		this.neighbors = neighbors;
	}
}
public class DFSArray
{
	public static void main(String[] args) throws IOException
	{
		long start = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("milkvisits.in"));
		StringTokenizer st = new StringTokenizer(f.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));
		int farms = Integer.parseInt(st.nextToken());
		int querries = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		String milktypes = st.nextToken();
		Node[] x = new Node[farms];
		for(int i = 0; i < farms; i ++)
		{
			Node m = new Node(i + 1, milktypes.charAt(i));
			x[i] = m;
		}
		for(int i = 0; i < farms - 1; i ++)
		{
			st = new StringTokenizer(f.readLine());
			int connect1 = Integer.parseInt(st.nextToken());
			int connect2 = Integer.parseInt(st.nextToken());
			Node c1 = x[connect1-1];
			Node c2 = x[connect2-1];
			c1.addNeighbor(c2);
			c2.addNeighbor(c1);
		}
		long check = System.currentTimeMillis();
		start = check;
		for(int i = 0; i < x.length; i ++) 
		{
			x[i].visited = false;
		}
		int componentID = 0;
		for(int i = 0; i < x.length; i ++)
		{
			if(!x[i].visited)
			{
				x[i].visited = true;
				SetComponentID(x[i], componentID);
				componentID ++;
			}
		}
		String ans = "";
		check = System.currentTimeMillis();
		StringBuilder build = new StringBuilder("");
		start = check;
		for(int i = 0; i < querries; i ++)
		{
			st = new StringTokenizer(f.readLine());
			int id1 = Integer.parseInt(st.nextToken());
			int id2 = Integer.parseInt(st.nextToken());
			char question = st.nextToken().charAt(0);
			Node node1 = x[id1-1];
			Node node2 = x[id2-1];
			long t1 = System.currentTimeMillis();
			boolean eachans = check2(node1, node2, question);
			long t2 = System.currentTimeMillis();
			if(eachans)
			{
				build.append('1');
			}
			else
			{
				build.append('0');
			}
		}
		out.println(build.toString());
		out.close();
	}
	public static void DFS(Node x, int level, Node parent)
	{
		x.parent = parent;
		x.level = level;
		x.visited = true;
		for(int i = 0; i < x.neighbors.size(); i ++)
		{
			if(!x.neighbors.get(i).visited)
				DFS(x.neighbors.get(i), level+1, x);
		}
	}
	public static void SetComponentID(Node x, int componentID)
	{
		x.componentID = componentID;
		for(int i = 0; i < x.neighbors.size(); i ++)
		{
			if((!x.neighbors.get(i).visited) && x.neighbors.get(i).type == x.type)
			{
				x.neighbors.get(i).visited = true;
				SetComponentID(x.neighbors.get(i) , componentID);
			}
		}
	}
	public static boolean check2(Node node1, Node node2, char question)
	{
		if(node1.type == question || node2.type == question)
			return true;
		if(node1.componentID == node2.componentID)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
