public class LinkedQueue<T> implements Queue<T>{
	
	private static class Node<V>{

		private V value;
		private Node<V> next;

		public Node(V value, Node<V> next){

			this.value=value;
			this.next=next;
		}
	}

	private Node<T> head, tail;

	public LinkedQueue(){
		head=tail=null;
	}

	public boolean isEmpty(){

		return head==null;
	}

	public void enqueue(T elem){

		if (elem == null){
			throw new NullPointerException("Cannot add null to queue");
		}

		Node<T> newNode = new Node<T>(elem, null);
		if(isEmpty()){
			head=tail=newNode;
		}
		else{
			tail.next = newNode;
			tail=tail.next;
		}
		

	}
	public T dequeue(){
		
		if (isEmpty()){

			throw new IllegalStateException("Cannot remove from empty queue");
		}

		T value = head.value;

		if (head.next==null){

			head=tail=null;
		}
		else{

			head=head.next;
		}

		return value;
		
	}

	public String toString(){

		StringBuffer rep= new StringBuffer("[");
		Node<T> cursor = head;
		while(cursor!=null){
			rep.append(cursor.value);
			if(cursor.next!=null){
				rep.append(", ");
			}
			cursor=cursor.next;
		}
		rep.append("]");

		return rep.toString();
	}

}