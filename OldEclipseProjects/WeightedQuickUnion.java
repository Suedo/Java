import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class WeightedQuickUnion{
	private BufferedReader helper;
	private String line;
	private int[] arr,sz;
	
	public int root(int i){
		while(arr[i]!=i){
			//i =  arr[i]; // making each node i now point to its parent
			i =  arr[arr[arr[arr[i]]]]; // making each node i point to its grandparent. this is PATH COMPRESSION and keeps the tree almost flat. practically , this is flat enough for most applications.
		}
		return i;
	}
	public boolean connected(int f, int s){
		return root(f)==root(s);
	}	
	private void check(int f, int s){
		if(!connected(f,s)){
			//System.out.println(String.format("connecting %d , %d .",f,s));
			union(f,s);
		}else{
			//System.out.println(String.format("%d , %d are connected .",f,s));
		}
	}
	public void union(int f, int s){
		int firstRoot = root(f);
		int secondRoot = root(s);
		
		// arr[index] refers to parent of index
		if(sz[f]<sz[s]){
			// amend the smaller tree , in this case , one whose root is f's root, to thebigger tree,pointed by s' root
			arr[firstRoot]=secondRoot;
			sz[secondRoot] += firstRoot;
		}
		else{
			arr[secondRoot] = firstRoot;
			sz[firstRoot]  += sz[secondRoot] ;
		}
	}
	
	public WeightedQuickUnion(int N){
		arr = new int[N];
		sz = new int[N];
		for(int i = 0  ; i < N ; i++){
			arr[i] = i;
			sz[i] = 1;
		}
	}
	
	public WeightedQuickUnion(int N,File myFile){
		arr = new int[N];
		sz = new int[N];
		for(int i = 0  ; i < N ; i++){
			arr[i] = i;
			sz[i] = 1;
		}
		try{
			helper = new BufferedReader( new FileReader(myFile));
			String[] parsed;
			while( ( line = helper.readLine() ) != null ){
				parsed = line.trim().split(" "); //  need to add trim , and error handling here , so that only two numbers are taken inside parsed at each time;
				check(Integer.parseInt(parsed[0]),Integer.parseInt(parsed[1]));
			}
		}catch(NumberFormatException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			//System.out.println("all done! printing final array : \n");
			for(int i = 0 ; i < arr.length ; i++){
				System.out.println(arr[i] + " at : " + i);
			}
			
		}	
	}
	public static void main(String[] args){
		File myFile;
		try{
			myFile = new File("points.txt");
			new WeightedQuickUnion(15,myFile);
		}catch(NullPointerException e){e.printStackTrace();}
		
	}
}