public class Solve{
	
	private static Cube cube1 = new Cube(new Color[]{Color.BLUE,Color.GREEN,Color.WHITE,Color.GREEN,Color.BLUE,Color.RED});
	private static Cube cube2 = new Cube(new Color[]{Color.WHITE,Color.GREEN,Color.BLUE,Color.WHITE,Color.RED,Color.RED});
	private static Cube cube3 = new Cube(new Color[]{Color.GREEN,Color.WHITE,Color.RED,Color.BLUE,Color.RED,Color.RED});
	private static Cube cube4 = new Cube(new Color[]{Color.BLUE,Color.RED,Color.GREEN,Color.GREEN,Color.WHITE,Color.WHITE});

	

	public static Queue<Solution> generateAndTest(){

	    Queue<Solution> queue = new LinkedQueue<Solution>();
	    Solution.resetNumberOfCalls();
		while(cube1.hasNext()){
			cube1.next();
			while(cube2.hasNext()){
				cube2.next();
				while(cube3.hasNext()){
					cube3.next();
					while(cube4.hasNext()){
						cube4.next();
						Solution sol = new Solution(new Cube[]{cube1,cube2,cube3,cube4});
						if(sol.isValid()){
							queue.enqueue(sol);
						}

					}
					cube4.reset();
				}
				cube3.reset();
			}
			cube2.reset();
		}
		System.out.println(Solution.getNumberOfCalls());
		return queue;
	}

	public static Queue<Solution> breadthFirstSearch(){


		Queue<Solution> queue = new LinkedQueue<Solution>();
		Queue<Solution> possible = new LinkedQueue<Solution>();

		Solution.resetNumberOfCalls();
		cube1.reset();
		cube2.reset();
		cube3.reset();
		cube4.reset();

		while(cube1.hasNext()){
			cube1.next();
			Solution sol = new Solution(new Cube[]{cube1});
			possible.enqueue(sol);
		}

		cube1.reset();

		while(!possible.isEmpty()){

			Solution currentSol = possible.dequeue();
			if (currentSol.size()==1){
				while(cube2.hasNext()){
					cube2.next();
					if(currentSol.isValid(cube2)){
						possible.enqueue(new Solution(currentSol, cube2));
					}
				}
				cube2.reset();
			}

			if(currentSol.size()==2){
				while(cube3.hasNext()){
					cube3.next();
					if (currentSol.isValid(cube3)){
						possible.enqueue(new Solution(currentSol, cube3));
					}
				}
				cube3.reset();
			}

			if(currentSol.size()==3){
				while(cube4.hasNext()){
					cube4.next();
					if (currentSol.isValid(cube4)){
						queue.enqueue(new Solution(currentSol, cube4));
					}
				}
				cube4.reset();
			}

			}
			System.out.println(Solution.getNumberOfCalls());
			return queue;
		}


	public static void main(String[] args){
		
		StudentInfo.display();
		System.out.println();
		System.out.println();

		long start, stop;
		System.out.println();

		System.out.println("generateAndTest:");
		start = System.currentTimeMillis();

		generateAndTest();

		stop=System.currentTimeMillis();
		System.out.println("Elapsed time: " + (stop-start) + " milliseconds");

		System.out.println();
		System.out.println();
		System.out.println("breadthFirstSearch:");
		start=System.currentTimeMillis();

		breadthFirstSearch();

		stop=System.currentTimeMillis();
		System.out.println("Elapsed time: " + (stop-start) + " milliseconds");
	}
}