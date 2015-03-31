import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class JAEVP3 {
		
	public static class unionFind{
		
		unionFind mainUnion[];
		int pathLength[];
		int parent = 0;
		int key = 0;
		
		
		
		
		// creates a union find object for integer elements 0 ... n-1.
		public unionFind(int n){
			
			mainUnion = new unionFind[n];	//Create a new array of unionNodes.
			pathLength = new int[n];
			
			for(int i = 0; i < n; i++){
				
				mainUnion[i].key = i;	
				mainUnion[i].parent = i;
				
			}
			
		}
		
		
		
	    // forms the union of elements x and y using the union by size strategy.
		// If the sizes of the trees containing x and y are the same make
		// the tree containing y a subtree of the root of the tree containing x.
		public void union(int x, int y){
			
			int firstRoot = this.find(x);
			int secondRoot = this.find(y);
			
			if(pathLength[firstRoot] > pathLength[secondRoot]){
				
				mainUnion[secondRoot].parent = firstRoot;
				pathLength[firstRoot] = (pathLength[secondRoot] + 1);
				
			}else if(pathLength[secondRoot] > pathLength[firstRoot]){
				
				mainUnion[firstRoot].parent = secondRoot;
				pathLength[secondRoot] = (pathLength[firstRoot] + 1);
				
			}else{
				
				mainUnion[firstRoot].parent = secondRoot;
				
				if(pathLength[secondRoot] == 0){
				
					pathLength[secondRoot]++;
				
				}else{
					
					pathLength[secondRoot] = (pathLength[firstRoot] + 1);
					
				}
				
			}
			
			
			
		}
		
		 //Searches for element y and returns the key in the root of the tree containing y. Implements path compression on each find.
		public int find(int y){
				
			int i = mainUnion[y].key;
			
			if(i == y){
				return i;
			}
			
			return mainUnion[y].key = find(i);
			
		}
			
		
		//Returns the number of disjoint sets remaining
		public int numberOfSets(){
			
			return 0;
			
		}
		
		//See description below
		public void printStats(){
			
			
			
		}
		
		// prints the array contents in the UnionFind data structure as one line. See the description below.
		public void printSets(){
			
			
			
		}
		
		
		
		
	}//End of Union	
	
	 public static void main(String[] args) throws FileNotFoundException {
		 
		 unionFind newArray = new unionFind(0);
	   
		 
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
		            	newArray = new unionFind(x);
		            	
		            }
		            
		            //Case U, Call union(input,input), outputs the root value and the size of the resulting tree. 
		            case "U": {
		        
		            	int x = Integer.parseInt(tokens[1]);
		            	int y = Integer.parseInt(tokens[2]);
		            	
		               newArray.union(x, y);
		            	
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

