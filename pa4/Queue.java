// Henry Ball
// hfball
// 12B
// 05-02-16
// Queue.java

public class Queue implements QueueInterface{

   // Node class
   private class Node{
	  Object item;
	  Node next;
      
	  Node(Object x){
        item = x;
		  next = null;
	  }
   }
   
   private int numItems;
   private Node head;
   private Node tail;

   // constructor for Queue class
   public Queue(){
      head = null;
	   tail = null;
	   numItems = 0;
   }

   // returns the objects of every Node after the given Node
   private String myString(Node N){
	  if (N == null){
        return "";
	  }else{
		  return (N.item + " " + myString(N.next));
	  }
   }
   
   // returns true if the queue is empty
   public boolean isEmpty(){
      return (numItems == 0);
   }
 
   // returns the length of the queue
   public int length(){
      return numItems;
   }

   // adds an item to the back of the queue
   public void enqueue(Object newItem){

      Node N = new Node(newItem);

	   if (head == null){
	      head = N;
		   tail = N;
	   }else{
         tail.next = N;
		   tail = tail.next;
	   }
	   numItems++;
   }

   // returns and removes the item from the front of the queue
   public Object dequeue()
	   throws QueueEmptyException{
	
	   if (head == null){
	      throw new QueueEmptyException ("Can't dequeue an empty queue");
	   }

      Node temp = head;
      head = head.next;
      numItems--;
      return temp.item;
   }

   // returns the item at the front of the queue
   public Object peek()
	   throws QueueEmptyException{
	   
	   if (head == null){
		   throw new QueueEmptyException ("Can't peek an empty queue");
	   }

	   return head.item;
   }

   // resets the list
   public void dequeueAll()
	   throws QueueEmptyException{
	   
	   if (head == null){
	      throw new QueueEmptyException ("Can't dequeue an empty queue");
	   }

	   while (head.next != null){
	      head = head.next;
	   }

	   head = null;
	   numItems = 0;
   }

   // prints the queue
   public String toString(){
      return myString(head);
   }
}
