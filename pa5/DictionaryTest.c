// Henry Ball
// hfball
// 12B
// 05-26-16
// DictionaryTest.c
// This is the test file for Dictionary.c

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
   Dictionary A = newDictionary();
   printf("%d\n", isEmpty(A));
   insert(A, "1", "A");
   insert(A, "2", "B");
   insert(A, "3", "C");
   insert(A, "4", "D");
   insert(A, "5", "E");
   
   printf("%s\n", lookup(A, "1"));
   
   delete(A, "1");
   delete(A, "5");
   printDictionary(stdout, A);

   printf("%d\n", size(A));

   makeEmpty(A);

   printf("%d\n", size(A));
   freeDictionary(&A);
   printf("%d\n", size(A));
}
