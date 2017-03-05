// Henry Ball
// hfball
// 12B
// 04-17-16
// Search.java
// This program takes in an input file and a target word or words and determines
// whether of not the targets are within the input file.

import java.io.*;
import java.util.Scanner;

class Search{
   // scans in a file of text and searches for a specified target
   public static void main (String[] args) throws IOException{
      if (args.length < 2){
         System.out.println("Usage: Search file target1 [target2..]");
      }
      int i;
      Scanner in = new Scanner(new File(args[0]));
      in.useDelimiter("\\Z");
      String s = in.next();
      in.close();
      String[] word = s.split("\n");
      int[] lineNumber = new int[word.length];
      for (i=0; i<lineNumber.length; i++){
         lineNumber[i] = i+1;
      }
      int lineCount = word.length;
      mergeSort(word, lineNumber, 0, word.length-1);
      for (i = 1; i<args.length; i++){ // iterate through the targets and search for each within the input file
         if (binarySearch(word, 0, word.length-1, args[i]) >= 0){
            for (int j=0; j<word.length; j++){ // iterate through the word array to find the index of the target
               if (args[i].equals( word[j] )){
                  int p = j; // store the index of the target
                  System.out.println(args[i] + " found on line " + lineNumber[p]);
               }
            }
         }else{
            System.out.println(args[i] + " not found");
         }
      }   
   }
   // sorts a string array 
   public static void mergeSort(String[] A, int[]B, int p, int r){
      int q;
      if (p < r){
         q = (p+r)/2;
         mergeSort(A, B, p, q);
         mergeSort(A, B, q+1, r);
         merge(A, B, p, q, r);
      }
   }
   // splits a string array in half and compares the indicies of the two halves
   public static void merge(String[] A, int[]B, int p, int q, int r){
      int n1 = q-p+1;
      int n2 = r-q;
      String[] L = new String[n1]; // string arrays to be sorted
      String[] R = new String[n2];
      int[] L2 = new int[n1]; // int arrays to be sorted in order to keep track of the original indicies
      int[] R2 = new int[n2];
      int i, j, k;

      for(i=0; i<n1; i++){
         L[i] = A[p+i];
         L2[i] = B[p+i];
      }
      for(j=0; j<n2; j++){
         R[j] = A[q+j+1];
         R2[j] = B[q+j+1];
      }
      i = 0; j = 0;
      for(k=p; k<=r; k++){
         if(i<n1 && j<n2){
            if((L[i].compareTo(R[j])<0)){
               A[k] = L[i];
               B[k] = L2[i];
               i++;
            }else{
               A[k] = R[j];
               B[k] = R2[j];
               j++;
            }  
         }else if(i<n1){
            A[k] = L[i];
            B[k] = L2[i];
            i++;
         }else{
            A[k] = R[j];
            B[k] = R2[j];
            j++;   
         }
      } 
   }
   // searches a sorted array for a specified target
   public static int binarySearch(String[] A, int p, int r, String target){
      int q;
      if(p>r){
         return -1;
      }else{
         q = (p+r)/2;
         if(target.equals(A[q])){
            return q;
         }else if(target.compareTo(A[q])<0){
            return binarySearch(A, p, q-1, target);
         }else{
            return binarySearch(A, q+1, r, target);
         }
      }
   }
}
