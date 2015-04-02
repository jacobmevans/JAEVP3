import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;



public class JAEVP3 {
		
	public static class unionFind{
		
		int mainUnion[] = new int[0];
		int pathLength[] = new int[0];
		int totalPathLength = 0;
		int numCalls = 0;
		
		// creates a union find object for integer elements 0 ... n-1.
		public unionFind(int n){
			
			mainUnion = new int[n];	//Create a new array of unionNodes.
			pathLength = new int[n];
		
			for(int i = 0; i < n; i++){
				
				mainUnion[i] = -1;	
				pathLength[i] = 1;
				
			}
			
		}//end of constructor.
		
		
	    // forms the union of elements x and y using the union by size strategy.
		// If the sizes of the trees containing x and y are the same make
		// the tree containing y a subtree of the root of the tree containing x.
		public void union(int x, int y){
			
			int firstRoot = find(x);
			int secondRoot = find(y);
		
			if(mainUnion[secondRoot] < mainUnion[firstRoot]){	//If second tree is larger than first, let second be the new root.
					
					mainUnion[secondRoot] += mainUnion[firstRoot];
					mainUnion[firstRoot] = secondRoot;
					System.out.println(secondRoot + " " + (-mainUnion[secondRoot]));
					
			}else{	//Else if first tree is larger than second, let first be the new root. (Default case if trees are equal.)
					
				if(secondRoot != firstRoot){	//Check if duplicate attempt at a union, do not union again if so.
					
					mainUnion[firstRoot] += mainUnion[secondRoot];
					mainUnion[secondRoot] = firstRoot;
				
				}
				System.out.println(firstRoot + " " + (-mainUnion[firstRoot]));	
				
			}			
	    }//End of union();
		
		public boolean alreadyConnected(int x, int y){
			
			
			
			
			return false;
			
		}//End of alreadyConnected();
		
		 //Searches for element y and returns the key in the root of the tree containing y. Implements path compression on each find.
		public int find(int y){
			
			totalPathLength++;
			numCalls++;
			int k = y;
			
			while(mainUnion[k] >= 0){	//While next node is not a root node.
				
				k = mainUnion[k];		//Increment to the next node.
				totalPathLength++;
			}	
			
			int root = k;	
			k = y;	//Reset variable to perform path compression.
			
			while(mainUnion[k] >= 0){	//While next node is not a root node.
				
				mainUnion[k] = root;	//Make current node's parent the root node of the tree.
				k = mainUnion[k];		//Increment to the next node.
				
			}
				return root;			//Return the value of the root.
		}//End of find();
			
		
		//Returns the number of disjoint sets remaining
		public int numberOfSets(){
			
			int numOfSets = 0;
			
			for(int i = 0; i < mainUnion.length; i++){
				
				if(mainUnion[i] <= -1){
					numOfSets++;
				}
			}
			return numOfSets;
			
		}//End of numberOfSets();
		
		
		public double calcAverage(){
			
			double avg = 0;
			
			avg = (double)totalPathLength/(double)numCalls;
			return avg;
			
		}//End of calcAverage();
		
		//See description below
		public void printStats(){
			
			System.out.println();
			System.out.print("Number of sets remaining = ");
			System.out.printf("%4d",numberOfSets());
			System.out.println();
			System.out.print("Mean path length in find  = ");
			System.out.printf("%6.2f",calcAverage());
			System.out.println();
			
			
		}//End of printStats();
		
		
		// prints the array contents in the UnionFind data structure as one line. See the description below.
		public void printSets(){
			
			for(int i = 0; i < mainUnion.length; i++){
				System.out.print(mainUnion[i] + " ");
			}
			
		}//End of printSets();
		
		
		
		
	}//End of Union	
	
	 public static void main(String[] args) throws FileNotFoundException {
		 
		 unionFind uf = new unionFind(0);
	   
		 
	        Scanner sc = new Scanner(System.in); 
	        //Scanner sc = new Scanner(new File("E:/JakeEclipse/JAEVP3/src/test.txt"));
	        String line = "";
	        boolean done = false;
	        
	        while(!done) {
		        line = sc.nextLine();
		        String [] tokens = line.split(" ");
				switch(tokens[0]) {
		        
				    //Case N, returns name.
		            case "n": {
		            
		                System.out.println("JAEVP3.java");
		                break;
		                
		            }
		            
		            //Case D, Create a UnionFind data structure with elements as the input.
		            case "d": {
		            	
		            	int x = Integer.parseInt(tokens[1]);
		            	uf = new unionFind(x);
		            	break;
		            	
		            }
		            
		            //Case U, Call union(input,input), outputs the root value and the size of the resulting tree. 
		            case "u": {
		        
		            	int x = Integer.parseInt(tokens[1]);
		            	int y = Integer.parseInt(tokens[2]);
		            	
		               uf.union(x, y);
		    
		               break;
		            	
		            }
		            
		            //Case F, Call find(input), outputs the root index. Keep track of the total path length required in all find operations.
		            case "f":{
		            	
		            	int x = Integer.parseInt(tokens[1]);
		            	int y = 0;
		            	
		            	y = uf.find(x);
		       
		            	System.out.println(y);
		            	break;
		            	
		            	
		            }
		            
		            //Case P, outputs the array elements in your UnionFind data structure, space separated on one line.
		            case "p":{
		            	
		            	uf.printSets();
		            	
		            	break;
		            	
		            }
		            
		            //Case S, outputs statistics as described below.
		            case"s":{
		            	
		            	uf.printStats();
		            	
		            	break;
		            	
		            	
		            }
		            
		            //Case M, create a new UnionFind class with elements 0,1,..,(2^3)*(2^3)-1, 
		            //generate a Torus Maze as described below and print it.
		            case"m":{
		            	
		            	
		            	
		            }
		            
		            //Case E, sets done to true and exits the program.
		            case "e": {
		            	
		            	done = true;
		                break;		            	
		                
		            }
		            
		        } //End of switch

	        }//End of while
	  }//End of  main
}//End of file

