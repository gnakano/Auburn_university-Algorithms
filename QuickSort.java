
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Math;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random; 
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


   

 
public class QuickSort {

      /**
      * Main driver to run Merge-sort algorithm.
      */
   public static void main(String[] args) throws ParseException {
      int L = 40000;   // size limit of Array
      int step = 1000;
      int minimumArraySize = 5000;
      Random rand = new Random();
   
   
      //long[] sample = {5, 34, 4, 23, 53, 16, 10, 20, 9, 18, 75, 30, 25, 45};
      //int p = 0;
    
      // long[] G = new long[L];
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
         printArray(A);
         QuickSort(A, p, r);
         printArray(A);
      
      }
   
   
   
   }
            
   

   
   // p is the index of the first element
   // r is the index of the last element
   public static int Partition(long[] A, int p, int r) {
   
      long x = A[r]; // pivot;
      int i = p - 1;  // p is index of first element
   
      for (int j = p; j < r; j++) {
         if (A[j] <= x) {
            i = i + 1;
            long temp = A[i];
            A[i] = A[j];
            A[j] = temp;
         }
      }  
         // place the pivot right at the frontier
      long temp = A[i + 1];
      A[i + 1] = A[r];
      A[r] = temp;
   
      return (i + 1); // return index of frontier 
   
   }
   
   
   // Implement QuickSort Algorithm
   
   public static void QuickSort(long[] A, int p, int r) {
   
      if (p < r) {
      
         int q = Partition(A, p, r);
         QuickSort(A, p, q - 1);
         QuickSort(A, q + 1, r);
      
      }  
   }
   
   
   
   
   
   static void printArray(long A[]) 
   { 
      int n = A.length; 
      for (int i=0; i<n; ++i) 
         System.out.print(A[i] + " "); 
      System.out.println(); 
   } 

   
   
   
        
   
   
   
   
         
   
}