// Henry Ball
// hfball
// 12B
// 05-26-16
// Dictionary.c
// This program implements the dictionary ADT as a hash table.

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

const int tableSize=101;

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift){
   int sizeInBits = 8*sizeof(unsigned int);
   shift = shift & (sizeInBits - 1);
   if (shift == 0){
      return value;
   }
   return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input){
   unsigned int result = 0xBAE86554;
   while (*input){
      result ^= *input++;
      result = rotate_left(result, 5);
   }
   return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
   return pre_hash(key)%tableSize;
}

// NodeObj
typedef struct NodeObj{
   char* key;
   char* value;
   struct NodeObj* next;
} NodeObj;

typedef NodeObj* Node;

// Constructor for Node
Node newNode(char* k, char* v){
   Node N = malloc(sizeof(NodeObj));
   assert(N != NULL);
   N->key = k;
   N->value = v;
   N->next = NULL;
   return(N);
}

// Destructor for Node
void freeNode(Node* pN){
   if(pN != NULL && *pN != NULL){
      free(*pN);
      *pN = NULL;
   }
}

// DictionaryObj
typedef struct DictionaryObj{
   int numItems;
   Node *table;
} DictionaryObj;

typedef DictionaryObj* Dictionary;

// Constructor for Dictionary
Dictionary newDictionary(void){
   Dictionary D = malloc(sizeof(DictionaryObj));
   D->table = calloc(tableSize, sizeof(Node));
   assert(D != NULL);
   D->numItems = 0;
   return D;
}

void freeDictionary(Dictionary* pD){
   makeEmpty(*pD);
   free(*pD);
}

int isEmpty(Dictionary D){
   int x = 0;
   if (D->numItems == 0){
      x = 1;
   }
   return x;
}

int size(Dictionary D){
   return(D->numItems);
}

char* lookup(Dictionary D, char* k){
   char* v;
   int i = hash(k);
   while (D->table[i] != NULL){
      if (D->table[i]->key != k){
         D->table[i] = D->table[i]->next;
      }else if (D->table[i]->key == k){
         v = D->table[i]->value;
         return v;
      }
   }
   return NULL;
}

void insert(Dictionary D, char* k, char* v){
   int i;
   if (lookup(D, k) != NULL){
      fprintf(stderr,
            "DuplicateKeyException: can't insert duplicate keys\n");
      exit(EXIT_FAILURE);
   }
   i = hash(k);
   Node N = newNode(k, v);

   if (D->table[i] == NULL){
      D->table[i] = N;
   }else{
      Node temp = D->table[i];
      D->table[i] = N;
      D->table[i]->next = temp;
   }
   D->numItems++;
}

void delete(Dictionary D, char* k){
   int i;
   if (lookup(D, k) == NULL){
      fprintf(stderr,
            "KeyNotFoundException: can't delete a non-existent key");
      exit(EXIT_FAILURE);
   }

   i = hash(k);
   Node P = D->table[i];
   if (strcmp(D->table[i]->key, k) == 0){
      D->table[i] = D->table[i]->next;
      freeNode(&P);
   }else{
      while (strcmp(P->next->key, k) != 0){
         P = P->next;
      }
      Node N = P->next;
      P->next = N->next;
      freeNode(&N);
   }
   D->numItems--;
}

void makeEmpty(Dictionary D){
   int i;
   for (i=0; i<tableSize; i++){
      if (D->table[i] != NULL){
         while (D->table[i]->next != NULL){
            delete(D, D->table[i]->next->key);
         }
         delete(D, D->table[i]->key);
      }
   }
   D->numItems = 0;
   free(D->table);
   D->table = NULL;
}

void printDictionary(FILE* out, Dictionary D){
   int i;
   Node N;
   for (i=0; i<tableSize; i++){
      if (D->table[i] != NULL){
         for (N = D->table[i]; N != NULL; N = N->next) fprintf(out, "%s %s\n", N->key, N->value);
      }
   }
}
