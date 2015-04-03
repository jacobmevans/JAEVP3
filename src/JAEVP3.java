import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;



public class JAEVP3 {
		
	public static class unionFind{
		
		int mainUnion[] = new int[0];
		int totalPathLength = 0;
		int numCalls = 0;
		
		// creates a union find object for integer elements 0 ... n-1.
		public unionFind(int n){
			
			mainUnion = new int[n];	//Create a new array of unionNodes.
				
			for(int i = 0; i < n; i++){
				
				mainUnion[i] = -1;
				
			}
		}//end of constructor.
		
		public void reset(){
			
			totalPathLength = 0;
			numCalls = 0;
			
		}
		
		
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
			//FIX THIS SHIT
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
		
		
		public int calcDim(){ 
			
			int dim = (int)Math.sqrt(mainUnion.length);
			System.out.println(dim);
			return dim;
			
		}
		
		
		public int genRand(){
			
			Random ran = new Random();		
			int rand = ran.nextInt(4);
			return rand;
			
		}
		
		
		public int genRand(int dim){

			Random ran = new Random();	
			int rand = ran.nextInt(dim * dim);
			return rand;
			
		}
		
		
		public int determineOutEdge(int e, int dim){
			
			int lowerBound = dim * (dim - 1);
			int upperBound = (dim * (dim - 1)) + (dim - 1);
			
			if(e >= 0 && e <= (dim-1)){	//If top outside edge.
				return 1;
			}else if(e >= lowerBound && e <= upperBound){	//If bottom outside edge.
				return 2;
			}else if((e % dim) == 0){	//If left outside edge.
				return 3;
			}else if(((e + 1) % dim) == 0){	//If right outside edge.
				return 4;
			}
			
			return 0;
		}
		
		
		public void generateMaze(){
			
			int dim = calcDim();	//Variable to be used in calculations.
			int temp = 0;
			while(numberOfSets() != 1){
				
				int rand = genRand();	//Random number for edge.
				int ran = genRand(dim);	//Random number for vertex.
				int isOutEdge = determineOutEdge(ran, dim);	//Variable to check if edge is outer. 
				System.out.println("Random is: " + ran);
				System.out.println("Random direction is: " + rand);
				System.out.println("Is out edge is: " + isOutEdge);
				if(rand == 0){	//Generate a left edge.
					if(isOutEdge == 3 || ran == 0 || ran == dim*(dim - 1)){
						
						temp = ran + (dim - 1);
						union(ran,temp);
						
					}else{
						temp = ran - 1;
						System.out.println(temp);
						union(ran,temp);
					}
				}else if(rand == 1){	//Generate a right edge.
					if(isOutEdge == 4 || ran == (dim - 1) || ran == (dim * dim) - 1){
						
						temp = ran - (dim - 1);
						union(ran,temp);
						
					}else{
						temp = ran + 1;
						union(ran,temp);
					}
				}else if(rand == 2){	//Generate an up edge.
					if(isOutEdge == 1){
						
						temp = ran + (dim * (dim - 1));
						System.out.println(temp);
						union(ran,temp);
						
					}else{
						temp = ran - dim;
						union(ran,temp);
					}
				}else if(rand == 3){	//Generate a down edge.
					if(isOutEdge == 2){
						
						temp = ran - (dim * (dim - 1));
						union(ran,temp);
						
					}else{
						temp = ran + dim;
						union(ran,temp);
					}
				}
			}
		}
		
		
		public void printMaze(){
			
			for(int i = 0; i < mainUnion.length; i ++){
				
			System.out.print((-mainUnion[i]));	
			
			}
		}
		
		
	}//End of Union	
	
	 public static void main(String[] args) throws FileNotFoundException {
		 
		 unionFind uf = new unionFind(0);
	   
		 
	        //Scanner sc = new Scanner(System.in); 
	        Scanner sc = new Scanner(new File("C:/users/jake/workspace/JAEVP3/src/test.txt"));
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
		            	
		            	double x = Integer.parseInt(tokens[1]);
		            	int y = Integer.parseInt(tokens[2]);
		            	
		            	x = Math.pow(2, x) * Math.pow(2, x);
		            	System.out.println(x);
		            	uf.reset();
		            	uf = new unionFind((int)x);
		            	uf.generateMaze();
		            	uf.printMaze();
		            	
		            	
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

