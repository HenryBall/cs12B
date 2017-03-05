// Henry Ball
// hfball
// 12B
// 05-13-16
// QueueTest.java
// Test the Queue ADT

public class QueueTest{
   public static void main (String[] args){
     Queue A = new Queue();
	  A.enqueue("1");
	  A.enqueue("2");
	  A.enqueue("3");
	  //System.out.println(A.toString());
     System.out.println(A.dequeue());
     System.out.println(A.length());
	  A.enqueue("4");
     A.isEmpty();
	  //System.out.println(A.peek());
	  //System.out.println(A.toString());
	  A.dequeueAll();
     A.isEmpty();
	  //System.out.println(A.toString());
     System.out.println(A.length());
	  A.enqueue("1");
	  A.enqueue("2");
	  A.enqueue("3");
	  //System.out.println(A.toString());
   }
}
