public class Cube{

	private Color[] currentPermutation;

	private Color[] originalPermutation;

	private int attemptedNumberOfPermutations;

	private String[] transformations={"Identity", "Rotate", "Rotate", "Rotate", "RightRoll", "Rotate", "Rotate", "Rotate", "RightRoll", "Rotate", 
	"Rotate", "Rotate", "LeftRoll", "Rotate", "Rotate", "Rotate", "LeftRoll", "Rotate", "Rotate", "Rotate", "RightRoll", "Rotate", "Rotate", "Rotate"};

	public Cube (Color[] faces){

		if(faces.length!=6){
			throw new IllegalStateException("Cannot have cube with more or less than 6 faces!");
		}
		
		originalPermutation = new Color[6];

		currentPermutation = new Color[6];

		attemptedNumberOfPermutations=0;
		
		for (int i=0; i<faces.length; i++){
			if(faces[i]==null){
				throw new IllegalStateException("Cannot have face with null color");
			}
			originalPermutation[i]=faces[i];
			
			currentPermutation[i]=faces[i];
		}


	}

	public Cube (Cube other){

		if(other==null){
			throw new NullPointerException("Cannot initialize a cube based on null");
		}

		originalPermutation = new Color[6];

		currentPermutation = new Color[6];

		originalPermutation[0]=currentPermutation[0]=other.getUp();
		originalPermutation[1]=currentPermutation[1]=other.getFront();
		originalPermutation[2]=currentPermutation[2]=other.getRight();
		originalPermutation[3]=currentPermutation[3]=other.getBack();
		originalPermutation[4]=currentPermutation[4]=other.getLeft();
		originalPermutation[5]=currentPermutation[5]=other.getDown();

	}

	public Cube copy(){

		Color[] newCubeColors = new Color[6];
		for ( int i=0; i<6; i++){
			newCubeColors[i]=currentPermutation[i];
		}

		Cube newCube = new Cube(newCubeColors);

		return newCube;
	}

	public boolean hasNext(){

		if(attemptedNumberOfPermutations==24){
			return false;
		}
		return true;
	}
	public void reset(){

		for (int i=0; i<originalPermutation.length; i++){

			currentPermutation[i]=originalPermutation[i];
		}
		attemptedNumberOfPermutations=0;
	}

	public void next(){

		if(attemptedNumberOfPermutations==24){
			throw new IllegalStateException("No more new permutations");
		}
		else{

			String currentTransformation = transformations[attemptedNumberOfPermutations];
			Color[] newPerm= new Color[6];
			switch(currentTransformation){

				case "Identity":
					newPerm[0]= currentPermutation[0];
					newPerm[1]= currentPermutation[1];
					newPerm[2]= currentPermutation[2];
					newPerm[3]= currentPermutation[3];
					newPerm[4]= currentPermutation[4];
					newPerm[5]= currentPermutation[5];
					break;

				case "Rotate":
					newPerm[0]= currentPermutation[0];
					newPerm[1]= currentPermutation[4];
					newPerm[2]= currentPermutation[1];
					newPerm[3]= currentPermutation[2];
					newPerm[4]= currentPermutation[3];
					newPerm[5]= currentPermutation[5];
					break;

				case "RightRoll":
					newPerm[0]= currentPermutation[4];
					newPerm[1]= currentPermutation[1];
					newPerm[2]= currentPermutation[0];
					newPerm[3]= currentPermutation[3];
					newPerm[4]= currentPermutation[5];
					newPerm[5]= currentPermutation[2];
					break;

				case "LeftRoll":
					newPerm[0]= currentPermutation[2];
					newPerm[1]= currentPermutation[1];
					newPerm[2]= currentPermutation[5];
					newPerm[3]= currentPermutation[3];
					newPerm[4]= currentPermutation[0];
					newPerm[5]= currentPermutation[4];
					break;	
			}
			currentPermutation=newPerm;
			attemptedNumberOfPermutations++;
		}

	}


	public Color getUp(){
		return currentPermutation[0];
	}

	public Color getFront(){
		return currentPermutation[1];
	}

	public Color getRight(){
		return currentPermutation[2];
	}

	public Color getBack(){
		return currentPermutation[3];
	}

	public Color getLeft(){
		return currentPermutation[4];
	}

	public Color getDown(){
		return currentPermutation[5];
	}

	public String toString(){

		String rep="[";
		for (int i=0; i<currentPermutation.length; i++){
			rep+=currentPermutation[i];
			if ( i!=(currentPermutation.length-1)){
				rep+=", ";
			}
		}
		rep+="]";
		return rep;
	}
}