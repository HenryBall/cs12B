// Henry Ball
// hfball
// 12M
// 05-28-16
// Dictionary.java

import java.lang.String;

@SuppressWarnings("serial")
public class Dictionary implements DictionaryInterface{

   // inner Node class
   private class Node{
      String key;
      String value;
      Node left;
      Node right;

      Node(String x, String y){
         key = x;
         value = y;
         left = null;
         right = null;
      }
   }

   // private global fields
   private Node root;
   private int numItems;

   public Dictionary(){
      root = null;
      numItems = 0;
   }

   // returns the node with the given key rooted at R
   private Node findKey(Node R, String key){
      if (R==null || key.compareTo(R.key)==0)
         return R;
      if (key.compareTo(R.key)<0)
         return findKey(R.left, key);
      else //(key.compareTo(N.key)>0)
         return findKey(R.right, key);
   }

   // returns the parent of the given node rooted at R
   private Node findParent(Node N, Node R){
      Node P = null;
      if (N != R){ // if there is more than one node
         P = R;
         while (P.left != N && P.right != N){
            if ((N.key).compareTo(P.key)<0)
               P = P.left;
            else
               P = P.right;
         }
      }
      return P;
   }

   // returns the leftmost node rooted at R
   private Node findLeftmost(Node R){
      Node L = R;
      if (L != null){ // if the root is not null
         while (L.left != null){
            L = L.left;
         }
      }
      return L;
   }

   // prints the tree in order
   private String printInOrder(Node R){
      StringBuffer sb = new StringBuffer();
      if (R != null){
         printInOrder(R.left);
         System.out.println(R.key + " " + R.value);
         printInOrder(R.right);
      }
      return "";
   }

   // deletes all nodes in the tree rooted at N
   private void deleteAll(Node N){
      if (N != null){
         deleteAll(N.left);
         deleteAll(N.right);
      }
   }

// public functions---------------------------------------
   
   // returns true if the tree is empty and false otherwise
   public boolean isEmpty(){
      boolean x = false;
      if (numItems == 0) x = true;
      return(x);
   }

   // returns the number of items in the tree
   public int size(){
      return numItems;
   }

   // returns the value of the node with the given key
   public String lookup(String key){
      Node N = findKey(root, key);
      return (N==null) ? null : N.value;
   }

   // inserts a new node into the tree
   public void insert(String key, String value)
      throws DuplicateKeyException{
      Node A, B, N;
      if (findKey(root, key) != null){
         throw new DuplicateKeyException ("Can't insert duplicate keys");
      }

      A = root;
      B = null;
      N = new Node(key, value);

      while (A != null){
         B = A;
         if (key.compareTo(A.key)<0) A = A.left;
         else A = A.right;
      }

      if (B == null) root = N;
      else if (key.compareTo(B.key)<0) B.left = N;
      else B.right = N;
      numItems++;
   }

   // deletes the node with the given key from the tree
   public void delete(String key)
      throws KeyNotFoundException{
      Node N, P, S;
      N = findKey(root, key);
      if (lookup(key) == null){
         throw new KeyNotFoundException("can't delete a non-existent key");
      }

      if (N.left==null && N.right==null){
         if (N == root){
            root = null;
         }else{
            P = findParent(N, root);
            if (P.right == N) P.right = null;
            else P.left = null;
         } 
      }else if (N.right==null){
         if (N == root){
            root = N.left;
         }else{
            P = findParent(N, root);
            if (P.right == N) P.right = N.left;
            else P.left = N.left;
         }
      }else if (N.left==null){
         if (N==root){
            root = N.right;
         }else{
            P = findParent(N, root);
            if (P.right == N) P.right = N.right;
            else P.left = N.right;
         }
      }else{
         S = findLeftmost(N.right);
         N.key = S.key;
         N.value = S.value;
         P = findParent(S, N);
         if (P.right == S) P.right = S.right;
         else P.left = S.right;
      }
      numItems--;
   }

   // returns the dictionary to the empty state
   public void makeEmpty(){
      deleteAll(root);
      root = null;
      numItems = 0;
   }

   // overrides objects toString() function
   public String toString(){
      return (printInOrder(root));
   }
}
