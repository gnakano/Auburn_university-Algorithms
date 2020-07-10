import java.io.*;
import java.lang.*;
import java.util.*;

/**
 * CPSC-3273-AO1 -  Module 5- Programming Assignment
 *
 * @author     Wilbur Rotoni (wzr0018@auburn.edu) and Genji Nakano (GZN0006@tigermail.auburn.edu)
 
 * @version    15 February 2020
 *
 * Creates an array G with with length L of random numbers.
 * Creates an array A starting with length 5,000
 * sorts A using Insertion Sort, Quick Sort, and Merge Sort 
 * and increses A by 1,000 times for each iteration.
 */
    
public class M5Project1 {

   public static int L = 40000; // size limit of Array- Warnig: Setting this to a higher value
                                 // will obviously increase the running time of the program 
                                 
   public static int step = 1000;
   public static int minimumArraySize = 4000;
   public static int numRows = ((L - minimumArraySize) + step)/step;
   public static double[][] Tn_Storage = new double[numRows][4];   // This is strictly for storing and printing the 
                                                                   // n and Tn values in different columns of the *.txt file.
                                                                   // This does NOT affect AT ALL the Tn's that we're trying to
                                                                   // calculate for the InsertSort, QuickSort, and MergeSort.

   public static void main(String[] args) throws IOException {
        
   /** 
    * Create file F    
    * The user should change this file directory (and file name) of his choosing. It is strongly recommended
    * that the user selects the same path/folder where this source code (i.e. M5Project.java) is saved.
    */   
      File file = new File("F-File.txt");
      FileWriter fileWriter = new FileWriter(file, true);
      BufferedWriter buffer = new BufferedWriter(fileWriter);
      PrintWriter printWriter = new PrintWriter(buffer);
      
      //Printing headers to file F
      printWriter.println("n,InsertSort(Tn),QuickSort(Tn),MergeSort(Tn)");
   
      printWriter.close();
      
      long[] G = new long[L];
         
         // creating array G
      for(int i = 0; i < L; i++) {
         long randomNumberLow = (long) minimumArraySize;
         long randomNumberHigh = 4294967294L;  // 0xfffffffe;
         
         long randomNumber = randomNumberLow + (long) (Math.random() * (randomNumberHigh - randomNumberLow));
      
         G[i] = randomNumber;
      }
      
      
      // Fetch n values and store in Tn_Storage first column
      Print_n_in_fileF();
      
      // Fetch Tn's of InsertionSort, QuickSort, and MergeSort, and store in
      // Tn_Storage second, third, and fourth columns, respectively.
      
      ImplementInsertSort(G);
      
      ImplementQuickSort(G);
      
      ImplementMergeSort(G);
      
      // Now print the contents of Tn_Storage to the opened *.txt file.
      printToFileF(Tn_Storage);
         
   } 
   
   // Generating n values and storing in first column of Tn_Storage
   public static void Print_n_in_fileF() throws IOException {
   
      int row = 0;
      int column = 0;
      int step = 1000;
      int val = 4000;
         
      for (int n = minimumArraySize; n < L + 1; n = n + step) {
      
         Tn_Storage[row][column] = val;
         val += step;
      
         row++;
      
      }
   
   }
   
   // Create Array A and call InsertSort method, and
   // determine and return Tn.
   
   public static void ImplementInsertSort(long[] G) throws IOException {
   
      double Tn = 0;
      int row = 0;
      int column = 1;
   
      for (int n = minimumArraySize; n < L + 1; n = n + step) {
         
         // create array A
         long[] A = new long[n];
         
         // copying in array A the first n values from array G
         for(int i = 0; i < n; i++) {
            A[i] = G[i];
         }
         
         
         // start sorting Array A and also start timing
         double startTime = System.nanoTime()/1000000;   // time in milliseconds
         InsertSort(A);
         double endTime = System.nanoTime()/1000000;   // time in milliseconds
         Tn = (endTime - startTime);           
         
         // Store Tn of InsertSort to second column of Tn_Storage
         Tn_Storage[row][column] = Tn;
         
         row++;
        
      } 
        
   }
   
   // Implement InsertionSort Algorithm
   public static void InsertSort(long[] A) {
   
      for (int j = 1; j < A.length; j++) {
      
         long key = A[j];
      
         int i = j - 1;
      
         while ((i > -1) && (A[i] > key)) {
            A[i + 1] = A[i];
            i = i - 1;
         
         }
         
         A[i + 1] = key;
      
      }
   }
       
   // Create Array A and call QuickSort method, and
   // determine and return Tn.
   public static void ImplementQuickSort(long[] G) throws IOException {
   
      double Tn = 0;
      int row = 0;
      int column = 2;
   
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
         QuickSort(A, p, r);
         double endTime = System.nanoTime()/1000000;   // time in milliseconds
         Tn = (endTime - startTime);
         
         // Store Tn of QuickSort to third column of Tn_Storage
         Tn_Storage[row][column] = Tn;
         
         row++;
                      
      }        
   }
   
         
   // Implement Partition in QuickSort
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
      long temp2 = A[i + 1];
      A[i + 1] = A[r];
      A[r] = temp2;
   
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
   
      
   // Create Array A and call MergeSort method, and 
   // determine and return Tn.
   public static void ImplementMergeSort(long[] G) throws IOException  {
      
      double Tn = 0;
      int row = 0;
      int column = 3;
      
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
         Tn = (endTime - startTime);        
         
         // Store Tn of MergeSort to fourth column of Tn_Storage
         Tn_Storage[row][column] = Tn;
         
         row++;
                       
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
   
   
   // Print n and Tn values to M5 F-File.txt     
   public static void printToFileF(double[][] Tn_Storage) throws IOException {
   
      try{
         FileWriter fileWriter= new FileWriter("F-File.txt",true);
         BufferedWriter buffer = new BufferedWriter(fileWriter);
         PrintWriter printWriter = new PrintWriter(buffer);
         
      // Now printing Tn_Storage[row][column] elements to M5 F-File.txt.
         for (int row = 0; row < numRows; row++) {
         
            for (int col = 0; col < 4; col++) {
            
               printWriter.print(Tn_Storage[row][col] + ",");
            }
            
            printWriter.println();
         }
      
         printWriter.close();
         
      }catch (Exception e){
         System.err.println("Error while writing to file: " +
            e.getMessage());
      }
   }     
}