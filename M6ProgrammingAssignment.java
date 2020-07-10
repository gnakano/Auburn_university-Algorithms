import java.io.*;
import java.lang.*;
import java.util.*;  
   
/**
 * CPSC-3283-AO1 -  Module 6- Programming Assignment
 *
 * @author     Wilbur Rotoni (wzr0018@auburn.edu)
 * @author     Genji Nakano (GZN0006@tigermail.auburn.edu)
 * 
 
 * @version    16 April 2020
 *
 * The objective is to study the overhead of the function calls.
 */
 
class M6ProgrammingAssignment {
 
   static int[] s; // table of start times
   static int[] f; // table of finish times   
   static ArrayList<Integer> A_ofRecursive;
   static ArrayList<Integer> A_ofIterative;
   
   static int n = 20001;
   static int NumberPoints = 20001;
 
   public static void main(String[] args) throws IOException {
    
    /** 
    * Create file F    
    * The user should change this file directory (and file name) of his choosing. It is strongly recommended
    * that the user selects the same path/folder where this source code (i.e. M4ProgrammingAssignment.java) is saved.
    */   
      File file = new File("C:\\Users\\wrotoni\\Documents\\customertestdata\\test\\Algo\\Algo2\\Programs\\M6\\M6Project-File F.txt");
      
      //Instantiating the PrintStream class
      PrintStream stream = new PrintStream(file);
      
      System.setOut(stream);
      //Printing headers to file F
      System.out.println("i,M[i]");
      
      StudyOverhead(NumberPoints);
      
   }
   
   static void StudyOverhead(int NumberPoints) throws IOException {
      
      double[] M = new double[n];
      
      // initialize table of start and finish times
      InitializeArrays(n);
      
      for (int i = 1; i < NumberPoints; i++) {
      
         double TimeRecursive = 0;
         double TimeIterative = 0;
         
         for (int j = 1; j <= NumberPoints; j++) {
           
            // Initialize set A
            // Use an array to represent a set A[i] = 0 if ai not element of A
            A_ofRecursive = new ArrayList<Integer>(0);
            A_ofIterative = new ArrayList<Integer>(0);
                           
            // Measure execution time of RecursiveActivitySelector
            double startTime1 = System.nanoTime();  
            RecursiveActivitySelector(0, i - 1);
            double endTime1 = System.nanoTime(); 
            double Tn1 = (endTime1 - startTime1); // time in nanoseconds
            TimeRecursive += Tn1;
               
             // Measure execution time of GreedyActivitySelector
            double startTime2 = System.nanoTime();  
            GreedyActivitySelector();
            double endTime2 = System.nanoTime(); 
            double Tn2 = (endTime2 - startTime2); // time in nanoseconds
            TimeIterative += Tn2; 
         }
         
         M[i] = TimeRecursive / TimeIterative;
         
          // print i and M_i to file F
         printToFileF(i, M[i]);
         
      }
   }
   
   // Create about n/2 mutually compatible activities
   static void InitializeArrays(int n) {
   
      s = new int[n];
      f = new int[n];
   
      s[0] = 0;
      f[0] = 0;
   
      for (int i = 1; i < n; i++) {
      
         if (i % 2 == 0) {
            s[i] = f[i - 2];
            f[i] = s[i] + 2;
         }
         else {
            s[i] = f[i - 1] - 1; // s[1] will be negative, but that is fine. 
            f[i] = f[i - 1] + 1;
         }
      }
   }
   
   // Recursive approach
   static ArrayList<Integer> RecursiveActivitySelector(int k, int n) {
   
      int m = k + 1;
      
      while (m <= n && s[m] <= f[k]) {
         m = m + 1;
      }
      
      if (m <= n) {
      
         A_ofRecursive.add(m);
         RecursiveActivitySelector(m , n);
         return A_ofRecursive;
      }
      
      else {
         return null;
      }
   }
   
   // Greedy approach
   static ArrayList<Integer> GreedyActivitySelector() {
   
      A_ofIterative.add(1);
      int k = 1;
      int n = s.length;
      
      for (int m = 2; m < n; m++) {
      
         if (s[m] >= f[k]) {
            A_ofIterative.add(m);
            k = m;
         }
      }
      return A_ofIterative;
   }
   
// prints i and M[i] to File F
   private static void printToFileF(int i, double M) throws IOException {
   
      try{
         FileWriter fstream = new FileWriter("M6Project-File F.txt", true);
         BufferedWriter out = new BufferedWriter(fstream);
         
         out.newLine();
      
         out.write(i + "," + M);
      
         out.close();
         
      } catch (Exception e){
         System.err.println("Error while writing to file: " +
            e.getMessage());
      }
   }
   
}
