// Henry Ball
// hfball
// 12B
// 04-26-16
// DictionaryTest.java
// A test file for Dictionary.java

public class DictionaryTest{
   public static void main(String[] args){
      Dictionary A = new Dictionary();
      A.insert("1", "a");
	  A.insert("2", "b");
	  A.insert("3", "c");
	  A.insert("4", "d");
	  A.insert("5", "e");
      A.insert("6", "f");
 	  A.insert("7", "g");
      A.insert("8", "h");
      A.insert("9", "i");
      A.insert("10", "j");
      A.insert("11", "k");
      A.insert("12", "l");
      A.insert("13", "m");
      A.insert("14", "n");
      A.insert("15", "o");
      A.insert("16", "p");
      A.insert("17", "q");
      A.insert("18", "r");
      A.insert("19", "s");
      A.insert("20", "t");
      A.insert("21", "u");
      A.insert("22", "v");
      A.insert("23", "w");
      A.insert("24", "x");
      A.insert("25", "y");
      A.insert("26", "z");
	  System.out.println(A.lookup("15"));
	  System.out.println(A.toString());
	  A.delete("1");
      A.delete("7");
	  A.delete("5");
	  A.delete("20");
	  A.delete("26");
	  System.out.println(A.size());
	  System.out.println();
	  System.out.println(A.toString());
	  A.makeEmpty();
	  System.out.println(A.size());
	  System.out.println(A.toString());
	  System.out.println(A.isEmpty());
	  //A.delete("1");
	  //A.insert("2", "b");
	  //A.insert("2", "c");
   }
}
