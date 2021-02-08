public class Solution{
	
	private Cube[] currentPermutation;

	private static int numberOfCalls=0;

	public Solution(Cube[] cubes){
		if (cubes.length==0 ||cubes.length>4){
			throw new IllegalStateException("Cannot have a pile of 0 or more than 4 cubes!");
		}

		currentPermutation = new Cube[cubes.length];
		for (int i=0; i<cubes.length; i++){
			if(cubes[i]==null){
				throw new NullPointerException("No null cube!");
			}
			currentPermutation[i]=cubes[i].copy();
		}
	}

	public Solution(Solution other, Cube c){
		if(c==null){
			throw new NullPointerException("No null cube!");
		}
		if ( other==null){
			currentPermutation = new Cube[1];
			currentPermutation[0]=c.copy();
		}
		if(other.size()==4){
			throw new IllegalStateException("Cannot create a pile of 5 cubes!");
		}
		else{
			currentPermutation= new Cube[other.size()+1];
			for (int i=0; i<other.size(); i++){
				currentPermutation[i]=other.getCube(i).copy();
			}
			currentPermutation[other.size()]=c.copy();
		}

	}

	public int size(){
		return currentPermutation.length;
	}

	public Cube getCube(int pos){

		if(pos<0 || pos>=currentPermutation.length){
			throw new IndexOutOfBoundsException("Given position is not in the pile");
		}

		return currentPermutation[pos];
	}

	public boolean isValid(){
		numberOfCalls++;
		Color[] front = new Color[currentPermutation.length];
		Color[] right = new Color[currentPermutation.length];
		Color[] back = new Color[currentPermutation.length];
		Color[] left = new Color[currentPermutation.length];

		for (int i=0; i<currentPermutation.length; i++){
			front[i]=currentPermutation[i].getFront();
			right[i]=currentPermutation[i].getRight();
			back[i]=currentPermutation[i].getBack();
			left[i]=currentPermutation[i].getLeft();
		}

		for (int i=0; i<front.length; i++){
			for(int j=i+1; j<front.length; j++){
				if((front[i]==front[j]) || (right[i]==right[j]) || (back[i]==back[j]) || (left[i]==left[j])){
					return false;
				}
			}
		}
		return true;
	}

	public boolean isValid(Cube next){

		Solution attemptedNewSolution = new Solution(this, next);
		return attemptedNewSolution.isValid();
	}

	public String toString(){
		String rep="";

		for ( int i=0; i<currentPermutation.length; i++){
			rep+= currentPermutation[i] + "\n";
		}

		return rep;
	}	

	public static int getNumberOfCalls(){
		return numberOfCalls;
	}

	public static void resetNumberOfCalls(){

		numberOfCalls=0;
	}

}