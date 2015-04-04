import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;



public class JAEVP3 {
		
	public static class unionFind{
		
		int mainUnion[] = new int[0];		//Variable array to store the array of unions.
		int mazeUnion[][] = new int[0][0];	//Variable array used to keep track of edges in the maze.
		int totalPathLength = 0;			//Variable used in path length calculations.
		int numCalls = 0;					//Variable used in path length calculations.
		
		
		// creates a union find object for integer elements 0 ... n-1.
		public unionFind(int n){
			
			mainUnion = new int[n];	//Create a new array of unionNodes.
				
			for(int i = 0; i < n; i++){
				
				mainUnion[i] = -1;
				
			}
		}//end of constructor.
		
		
		//Method used to initialize the maze array used to store edges.
		public void createMazeArray(){
			
			int length = mainUnion.length;
			mazeUnion = new int[length][length];
			
			for(int i = 0; i < length; i++){
				for(int j = 0; j < length; j++){
					mazeUnion[i][j] = 0;
				}
			}
		}//End of createMazeArray();
		
		
		//Method used to reset variables tracking path length.
		public void reset(){
			
			totalPathLength = 0;
			numCalls = 0;
			
		}//End of reset();
		
		
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
		
		
		// forms the union of elements x and y using the union by size strategy for creation of the maze.
		// If the sizes of the trees containing x and y are the same make
		// the tree containing y a subtree of the root of the tree containing x.
		public void unionMaze(int x, int y){
					
			int firstRoot = find(x);
			int secondRoot = find(y);
				
			if(secondRoot != firstRoot){	//Check if duplicate attempt at a union, do not union again if so.
				if(mainUnion[secondRoot] < mainUnion[firstRoot]){	//If second tree is larger than first, let second be the new root.
						
						mainUnion[secondRoot] += mainUnion[firstRoot];
						mainUnion[firstRoot] = secondRoot;
						
						if(x < y){
							mazeUnion[x][x]++;
							mazeUnion[x][y] = genRand();
						}
						if(y < x){
							mazeUnion[y][y]++;
							mazeUnion[y][x] = genRand();
						}
		
				}else{	//Else if first tree is larger than second, let first be the new root. (Default case if trees are equal.)
							
						mainUnion[firstRoot] += mainUnion[secondRoot];
						mainUnion[secondRoot] = firstRoot;
						
						if(x < y){
							mazeUnion[x][x]++;
							mazeUnion[x][y] = genRand();
						}
						if(y < x){
							mazeUnion[y][y]++;
							mazeUnion[y][x] = genRand();
						}	
					}
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
				
				if(mainUnion[i] <= -1){	//If mainUnion[i] is a root increases numOfSets++.
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
			//Can this be made neater?
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
			System.out.println();
			
		}//End of printSets();
		
		
		public int calcDim(){ 
			
			int dim = (int)Math.sqrt(mainUnion.length);
			return dim;
			
		}//End of calcDim();
		
		
		//Method used to generate a random number used to choose an edge to create.
		public int genRand(){
			
			Random ran = new Random();		
			int rand = ran.nextInt(4) + 1;
			return rand;
			
		}//End of genRand();
		
		
		//Method used to generate a random number to act as a base for the maze union.
		public int genRand(int dim){

			Random ran = new Random();	
			int rand = ran.nextInt(dim * dim);
			return rand;
			
		}//End of genRand(int dim);
		
		
		//Method used to determine if the chosen vertex is located on the outside of the maze.
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
		}//End of determineOutEdge();
		
		
		//Method used to generate a maze by choosing random vertices and a random direction and attempting to union them.
		public void generateMaze(){
			
			int dim = calcDim();	//Variable to be used in calculations.
			createMazeArray();
			int temp = 0;
	
			
			while(numberOfSets() != 1){
				
				int rand = genRand();	//Random number for edge.
				int ran = genRand(dim);	//Random number for vertex.
				int isOutEdge = determineOutEdge(ran, dim);	//Variable to check if edge is outer. 
				
				if(rand == 1){	//Generate a left edge.
					if(isOutEdge == 3 || ran == 0 || ran == dim*(dim - 1)){	//Check if vertex is on outside left.
						temp = ran + (dim - 1);
					}else{
						temp = ran - 1;
					}
					unionMaze(ran,temp);
				}else if(rand == 2){	//Generate a right edge.
					if(isOutEdge == 4 || ran == (dim - 1) || ran == (dim * dim) - 1){	//Check if vertex is on outside right.
						temp = ran - (dim - 1);
					}else{
						temp = ran + 1;
					}
					unionMaze(ran,temp);
				}else if(rand == 3){	//Generate an up edge.
					if(isOutEdge == 1){	//Check if vertex is on outside top.
						temp = ran + (dim * (dim - 1));
					}else{
						temp = ran - dim;
					}
					unionMaze(ran,temp);
				}else if(rand == 4){	//Generate a down edge.
					if(isOutEdge == 2){	//Check if vertex is on outside bottom.
						temp = ran - (dim * (dim - 1));					
					}else{
						temp = ran + dim;
					}
					unionMaze(ran,temp);
				}
			}
		}//End of generateMaze();
			
		
		public int getWeight(int eWeight){
			
			Random ran = new Random();	
			int weight = ran.nextInt(eWeight) + 1;
			
			return weight;
		}//End of getWeight();
		
			
		//Prints out the generated maze with input variable used to calculate the weights.
		public void printMaze(int eWeight){
			
		
			for(int i = 0; i < mainUnion.length; i ++){
				
				
				System.out.print(mazeUnion[i][i] + " ");	//Print out number of edges with keys greater than current (i).
				int temp = mazeUnion[i][i];	//Temporary variable used to store number of edges to use for weight calculation.
				
				for (int j = 0; j < mainUnion.length; j++) {
					
					if(mazeUnion[i][j] > 0){	//If integer is greater than 0 (is a connected edge to i).
						if(j > i){				//If edge has higher ID number than i.
							System.out.print(j + " ");					
						}
					}	
				}
				for (int k = 0; k < temp; k++) {	//Loop through temp times to print weights. *Make cleaner (figure out how to print values from array.)*
					System.out.print(getWeight(eWeight)+ " ");
				}
				System.out.println();
			}
		}//End of printMaze();
		
		
	}//End of Union	
	
	
	 public static void main(String[] args) throws FileNotFoundException {
		 
		 unionFind uf = new unionFind(0);
	   
		 
	        Scanner sc = new Scanner(System.in); 
	        //Scanner sc = new Scanner(new File("src/test.txt"));
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
		            	
		            	uf.reset();
		            	uf = new unionFind((int)x);
		            	uf.generateMaze();
		            	uf.printMaze(y);
		            	
		            	break;
		            	
		            	
		            }
		            
		            //Case E, sets done to true and exits the program.
		            case "e": {
		            	sc.close();
		            	done = true;
		                break;		            	
		                
		            }
		            
		        } //End of switch

	        }//End of while
	  }//End of  main
}//End of file

