// Henry Ball
// hfball
// 04-25-16
// Dictionary.java
// This program creates the Dictionary ADT. It has several functions which
// create, delete and manipulate elements of the linked list.

public class Dictionary implements DictionaryInterface{

   // Node class
   private class Node{
      String key;
	  String value;
	  Node next;

	  Node(String x, String y){
	     key = x;
		 value = y;
		 next = null;
	  }
   }

   // initialize head, tail and numItems
   private Node head;
   private Node tail;
   private int numItems;

   public Dictionary(){
      head = null;
      tail = null;
	  numItems = 0;
   }
    
   // private helper functions
   private Node findKey(String key){
      Node N = head;
	  while(N != null){
	     if(N.key.equals(key)){
	        return N;
	     }else{
	 	    N = N.next;
         }   
	  }
	  return null;
   }

   private String myString(Node N){
      if(N == null){
	     return "";
	  }else{
		 return (N.key + " " + N.value + "\n" +  myString(N.next));
	  }
   }
    
   // public methods
   public int size(){
      return numItems;
   }

   public boolean isEmpty(){
      return(numItems == 0);
   }


   public String lookup(String key){
      if (findKey(key) == null){ // if no node exists with the given key return null
	     return null;
	  }else{ // return the value of the given key
	     return findKey(key).value;
	  }   
   }
        
   public void insert(String key, String value)
      throws DuplicateKeyException{
   
      if(findKey(key) != null){ // throw exception if a node with the given key already exists
	     throw new DuplicateKeyException ("can not insert duplicate keys");
	  }
        
	  Node N = new Node(key, value);
	  Node T = tail;

	  if (head == null){ // if the list is empty
	     head = N;
	     tail = N;
	  }else{ // if the list already has at least one item insert the new node after the tail
         tail.next = N;
	     tail = tail.next;
	  }
	  numItems++;
   }

   public void delete(String key)
      throws KeyNotFoundException{

      Node N = findKey(key);
	  Node P = head;

	  if (N == null){ // throw exception if a node with the given key does not exist
	     throw new KeyNotFoundException ("cannot delete non-existent key");
	  }
             
	  if (head == N){ // if the head is N
	     head = head.next;
	  }else{ // if N is anywhere else in the list
         while (P.next != N){
		    P = P.next;
		 }
	     P.next = N.next; 
	  }
      numItems--;
   }

   public void makeEmpty(){
      while (head.next != null){
	     delete((head.next).key);
	  }
	  delete(head.key);
	  numItems = 0;
   }

   public String toString(){
      return myString(head);
   }
}
