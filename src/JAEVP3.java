import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;



public class JAEVP3 {
		
	public static class unionFind{
		
		unionFind mainUnion[] = new unionFind[0];
		int pathLength[] = new int[0];
		Integer parent = 1;
		int key = 1;
		boolean isRoot = false;
		
		
		public unionFind(int x, int y){
			
			this.key = x;
			this.parent = x;
			this.isRoot = true;
			
		}
		
		// creates a union find object for integer elements 0 ... n-1.
		public unionFind(int n){
			
			mainUnion = new unionFind[n];	//Create a new array of unionNodes.
			pathLength = new int[n];
			System.out.println(n);
			for(int i = 0; i < n; i++){
				
				mainUnion[i] = new unionFind(i,n);	
				System.out.println(mainUnion[i].key);
				
			}
			
		}
		
		
		
	    // forms the union of elements x and y using the union by size strategy.
		// If the sizes of the trees containing x and y are the same make
		// the tree containing y a subtree of the root of the tree containing x.
		public void union(int x, int y){
			
			int firstRoot = find(x);
			int secondRoot = find(y);
			
			System.out.println("Value of firstRoot is: " + firstRoot);
			System.out.println("Value of secondRoot is: " + secondRoot);
			
			if(pathLength[firstRoot] > pathLength[secondRoot]){
				
				mainUnion[secondRoot].parent = firstRoot;
				pathLength[firstRoot] += pathLength[secondRoot] + 1;
				mainUnion[secondRoot].isRoot = false;
				
			}else if(pathLength[secondRoot] > pathLength[firstRoot]){
				
				mainUnion[firstRoot].parent = secondRoot;
				pathLength[secondRoot] += pathLength[firstRoot] + 1;
				mainUnion[firstRoot].isRoot = false;
				
			}else{
				
				mainUnion[secondRoot].parent = firstRoot;
				mainUnion[secondRoot].isRoot = false;
				
				if(pathLength[firstRoot] == 0){
					
					pathLength[firstRoot]++;
				
				}else{
					
					pathLength[firstRoot] += pathLength[secondRoot] + 1;
					
				}
				
			}
			System.out.println("Parent of firstRoot is: " + mainUnion[firstRoot].parent);
			System.out.println("Parent of secondRoot is: " + mainUnion[secondRoot].parent);
			System.out.println("pathLength of firstRoot is: " + pathLength[firstRoot]);
			System.out.println("pathLength of secondRoot is: " + pathLength[secondRoot]);
			
			
			
		}
		
		 //Searches for element y and returns the key in the root of the tree containing y. Implements path compression on each find.
		public int find(int y){
			
			int x = mainUnion.length;
			int[] temp = new int[x];
			int i = 0;
			
			while(mainUnion[y].parent != null){
				if(mainUnion[y].isRoot == true){
					
					temp[i] = y;
					
					for(int k = 0; k < x; k++){
						
						if(mainUnion[temp[k]].isRoot == true){
							break;
						}else{
							mainUnion[temp[k]].parent = mainUnion[y].key;
							System.out.println("Parent of temp is: " + mainUnion[temp[k]].parent);
						}
						
					}
					
					return y;
					
				}else{
					temp[i] = y;
					i++;
					y = mainUnion[y].parent;
					
				}
			}
				
				return 0;
			
		}
			
		
		//Returns the number of disjoint sets remaining
		public int numberOfSets(){
			
			int numOfSets = 0;
			
			for(int i = 0; i < pathLength.length; i++){
				
				if(mainUnion[i].isRoot == true){
					numOfSets++;
				}
			}
			return numOfSets;
			
		}
		
		//See description below
		public void printStats(){
			
			
			
		}
		
		// prints the array contents in the UnionFind data structure as one line. See the description below.
		public void printSets(){
			
			
			
		}
		
		
		
		
	}//End of Union	
	
	 public static void main(String[] args) throws FileNotFoundException {
		 
		 unionFind uf = new unionFind(0);
	   
		 
	        Scanner sc = new Scanner(System.in); 
	        //Scanner sc = new Scanner(new File("C:/Users/Jake/workspace/JAEVP2/p2in4.txt"));
	        String line = "";
	        boolean done = false;
	        
	        while(!done) {
		        line = sc.nextLine();
		        String [] tokens = line.split(" ");
				switch(tokens[0]) {
		        
				    //Case N, returns name.
		            case "N": {
		            
		                System.out.println("Jacob Evans");
		                break;
		                
		            }
		            
		            //Case D, Create a UnionFind data structure with elements as the input.
		            case "D": {
		            	
		            	int x = Integer.parseInt(tokens[1]);
		            	uf = new unionFind(x);
		            	break;
		            	
		            }
		            
		            //Case U, Call union(input,input), outputs the root value and the size of the resulting tree. 
		            case "U": {
		        
		            	int x = Integer.parseInt(tokens[1]);
		            	int y = Integer.parseInt(tokens[2]);
		            	
		               uf.union(x, y);
		          
		               break;
		            	
		            }
		            
		            //Case F, Call find(input), outputs the root index. Keep track of the total path length required in all find operations.
		            case "F":{
		            	
		            	
		            	
		            }
		            
		            //Case P, outputs the array elements in your UnionFind data structure, space separated on one line.
		            case "P":{
		            	
		            	
		            	
		            }
		            
		            //Case S, outputs statistics as described below.
		            case"S":{
		            	
		            	
		            	
		            }
		            
		            //Case M, create a new UnionFind class with elements 0,1,..,(2^3)*(2^3)-1, 
		            //generate a Torus Maze as described below and print it.
		            case"M":{
		            	
		            	
		            	
		            }
		            
		            //Case E, sets done to true and exits the program.
		            case "E": {
		            	
		            	done = true;
		                break;		            	
		                
		            }
		            
		        } // end of switch

	        }//end of while
	  }//end of  main
}//End of file

