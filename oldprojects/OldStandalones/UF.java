/*
this is an implementation of quick-union algorithm.
somjit nag , 29/7/2013.
source : Algorithms 4th ed. robert sedgewick , kevin wayne.
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
class UF{
	BufferedReader helper;
	FileReader fr;
	String line;
	int[] arr;
	
	private void print(String msg){
		System.out.println(msg);
	}
	private int root(int i){
		while(arr[i]!=i){ 
			i = arr[i];
		}
		return i;
	}		
	private boolean connected(int i , int j){
		return root(i) == root(j);
	}
	private void check(int i , int j){
		if(!connected( i , j )){
			print(String.format("connecting %d and %d .",i,j));
			union(i,j);
		}else{
			print(String.format(" %d , %d are conneceted.",i,j));
		}
	}
	private void union(int f , int s){
		// first point gets connected to second point
		int changeRoot = root(f);
		int destRoot = root(s);
		arr[changeRoot] = destRoot;
	}
	private void display(){
		for(int i : arr){
			System.out.println(arr[i]);
		}
	}
	
	public UF(){
		arr = new int[15];
		for(int i = 0; i < 15 ; i++){
			arr[i] = i;
		}
		try{
			helper = new BufferedReader(new FileReader("points.txt"));
			String[] parsed;
			while((line = helper.readLine())!=null){
				parsed = line.split(" ");
				check(Integer.parseInt(parsed[0]),Integer.parseInt(parsed[1]));
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			print("all done!");
		}
	}
	
	
	public static void main(String[] args){
		new UF();
	}
}