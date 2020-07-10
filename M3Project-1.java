import java.io.*;
import java.lang.*;
import java.util.*;

/**
 * CPSC-3273-AO1 -  Module 3- Programming Assignment
 *
 * @author     Wilbur Rotoni (wzr0018@auburn.edu) and Genji Nakano (GZN0006@tigermail.auburn.edu)
 
 * @version    02 February 2020
 *
 * Creates an array G with with length L of random numbers.
 * Creates an array A starting with length 5,000
 * sorts A in descending order and increses A by 1,000
 * times algorithm to naive sort the A algorithm on each iteration
 */

public class M3Project {

   public static void main(String[] args) throws IOException {
   
      int L = 400000000;   // size limit of Array
      int step = 1000;
      int minimumArraySize = 5000;
      Random rand = new Random();
      
   /** 
    * Create file F    
    * The user should change this file directory (and file name) of his choosing. It is strongly recommended
    * that the user selects the same path/folder where this source code (i.e. M3Project.java) is saved.
    */   
      File file = new File("C:\\Users\\wrotoni\\Documents\\customer test data\\test\\Algo\\proj\\M3\\M3 F-File.txt");
      //Instantiating the PrintStream class
      PrintStream stream = new PrintStream(file);
      
      System.setOut(stream);
      //Printing headers to file F
      System.out.println("n,T(n),T(n)/log2(n),T(n)/nlog2(n),T(n)/n^2log2(n)");
      
      long[] G = new long[L];
         
         // creating array G
      for(int i = 0; i < L; i++) {
         long randomNumberLow = (long) minimumArraySize;
         long randomNumberHigh = 4294967294L;  // 0xfffffffe;
         
         long randomNumber = randomNumberLow + (long) (Math.random() * (randomNumberHigh - randomNumberLow));
      
         G[i] = randomNumber;
      }
         
      for (int n = minimumArraySize; n < L + 1; n = n + step) {
         
         // create array A
         long[] A = new long[n];
         
         // copying in array A the first n values from array G
         for(int i = 0; i < n; i++) {
            A[i] = G[i];
         }
         
         int p = 0;
         int r = A.length - 1;
         
         // start sorting Array A and also start timing
         double startTime = System.nanoTime()/1000000;   // time in milliseconds
         MergeSort(A, p, r);
         double endTime = System.nanoTime()/1000000;   // time in milliseconds
         double Tn = (endTime - startTime);
      
      // create new variables and compute  T(n)/log2(n), T(n)/nlog2(n) and T(n)/n^2log2(n)
      
         double Tn_log2n = Tn / (Math.log(n) / Math.log(2));       // T(n)/log2(n)
         double Tn_nlog2n = Tn / (n * (Math.log(n) / Math.log(2)));     // T(n)/nlog2(n)
         double Tn_nSquaredlog2n = Tn / (Math.pow(n, 2) * (Math.log(n) / Math.log(2)));    // T(n)/n^2log2(n)
      
      // print n, T(n)/log2(n), T(n)/nlog2(n) and T(n)/n^2log2(n)  to file F
         printToFileF(n, Tn, Tn_log2n, Tn_nlog2n, Tn_nSquaredlog2n);
      }
   
   }
   
   // Implement Merge-Sort Pseudocode
   public static void MergeSort(long A[], int p, int r) {
   
      if (p < r) {
      
         int q = (int) Math.floor((p + r) / 2);
         MergeSort(A, p, q);
         MergeSort(A, q + 1, r);
         Merge(A, p, q, r);
      }
   
   }
   
   // Implement Merge Pseudocode
   public static void Merge(long A[], int p, int q, int r) {
   
      int n1 = q - p + 1; 
      int n2 = r - q;
      
      long[] L = new long[n1 + 1];
      long[] R = new long[n2 + 1];    
      
      for (int i = 0; i < n1; i++) {
         L[i] = A[p + i];
      }
      
      for (int j = 0; j < n2; j++) {
         R[j] = A[q + j];
      }
      
      L[n1] = 4294967295L; //0xffffffff;
      R[n2] = 4294967295L; //0xffffffff;
      int i = 0;
      int j = 0;
      
      for (int k = p; k < r; k++) { 
         if (L[i] <= R[j]) {
            A[k] = L[i];
            i = i + 1;
         }
         else {
            A[k] = R[j];
            j = j + 1;
         }
      }
   }
   
   // print n, T(n)/log2(n), T(n)/nlog2(n) and T(n)/n^2log2(n)  to file F
      
   public static void printToFileF(int n, double Tn, double Tn_log2n, double Tn_nlog2n,
                                   double Tn_nSquaredlog2n) throws IOException {
   
      try{
         FileWriter fstream = new FileWriter("M3 F-File.txt",true);
         BufferedWriter out = new BufferedWriter(fstream);
         
         out.newLine();
      
         out.write(n + "," + Tn + "," + Tn_log2n + "," + Tn_nlog2n + "," + Tn_nSquaredlog2n);
      
         out.close();
         
      }catch (Exception e){
         System.err.println("Error while writing to file: " +
            e.getMessage());
      }
   }
   
}