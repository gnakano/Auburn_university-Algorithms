import java.io.*;
import java.lang.*;
import java.util.*;

   
/**
 * CPSC-3283-AO1 -  Module 4- Programming Assignment
 *
 * @author     Wilbur Rotoni (wzr0018@auburn.edu)
 * @author     Genji Nakano (GZN0006@tigermail.auburn.edu)
 * 
 
 * @version    02 April 2020
 *
 * Implements a naïve recursive (top-down), Recursive with Memoization, and
 * Iterative (bottom-up) algorithms to compute the Fibonacci numbers.
 */
 

public class M4ProgrammingAssignment {

   private static long[] Fib;
   
   public static void main(String[] args) throws IOException {
   
   
   /** 
    * Create file F    
    * The user should change this file directory (and file name) of his choosing. It is strongly recommended
    * that the user selects the same path/folder where this source code (i.e. M4ProgrammingAssignment.java) is saved.
    */   
      File file = new File("C:\\Users\\wrotoni\\Documents\\customertestdata\\test\\Algo\\Algo2\\Programs\\M4\\M4Project-File F.txt");
      
      //Instantiating the PrintStream class
      PrintStream stream = new PrintStream(file);
      
      System.setOut(stream);
      //Printing headers to file F
      System.out.println("i,Naive Recursive Top-Down,Recursive with Memoization,Iterative Bottom-Up");
      
      // compute Fibonacci(i) from i = 0 to 55, using the 3 Algorithms
      for (int i = 0; i <= 55; i++) {
      
         // declare Fib table (array)
         Fib = new long[i + 1];
         
         // intialize table to 4, per assignment instructions
         for (int j = 0; j < i + 1; j++) {
            Fib[j] = 4;
         }
      
        // Measure execution time of Algorithm A1
         double startTime1 = System.nanoTime();   
         Fibonacci(i);
         double endTime1 = System.nanoTime(); 
         double Tn1 = (endTime1 - startTime1) / 1000000;    // time in milliseconds
         
         // Measure execution time of Algorithm A2
         double startTime2 = System.nanoTime();  
         Fibonacci_Memoization(i);
         double endTime2 = System.nanoTime();   
         double Tn2 = (endTime2 - startTime2);    // time in nanoseconds
      
         // Measure execution time of Algorithm A3
         double startTime3 = System.nanoTime();  
         Fibonacci_BottomUp(i);
         double endTime3 = System.nanoTime();   
         double Tn3 = (endTime3 - startTime3);   // time in nanoseconds
      
           // print Tn1, Tn2, Tn3 to file F
         printToFileF(i, Tn1, Tn2, Tn3);
         
      }
   }
   
   // Algorithm 1: Naive recursive top-down algorithm
   private static int Fibonacci(int n) {
   
      if (n == 0) {
         return 0;            
      }
      
      if (n == 1) {
         return 1;
      }
      
      return ((Fibonacci(n - 1) + Fibonacci(n - 2)));         
   }
    
   // Algorithm 2: Using Memoization to compute Fibonacci number
   private static long Fibonacci_Memoization(int n) {
   
      if (n <= 1) {
         return n;
      }
      
      if (Fib[n] != 4) {
         return Fib[n];
      } 
            
      Fib[n] = Fibonacci_Memoization(n-1) + Fibonacci_Memoization(n-2); 
            
      return Fib[n];          
   }

   // Algorithm 3: Using Iterative Bottom-up method
   private static int Fibonacci_BottomUp(int n) {
   
      int[] F = new int[n + 2];
      F[0] = 0;
      F[1] = 1;
      
      for (int i = 2; i <= n; i++) {
      
         F[i] = F[i - 1] + F[i - 2];
      }   
      
      return F[n];      
   }
   
   // prints i, Tn1, Tn2, and Tn3 to File F
   private static void printToFileF(int i, double Tn1, double Tn2,
                                     double Tn3) throws IOException {
   
      try{
         FileWriter fstream = new FileWriter("M4Project-File F.txt",true);
         BufferedWriter out = new BufferedWriter(fstream);
         
         out.newLine();
      
         out.write(i + "," + Tn1 + "," + Tn2 + "," + Tn3);
      
         out.close();
      }catch (Exception e){
         System.err.println("Error while writing to file: " +
            e.getMessage());
      }
   }
}