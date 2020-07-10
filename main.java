import java.text.ParseException;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Creates a an array G with with length L of random numbers.
 * Creates an array A starting with length 5,000
 * sorts A in descending order and increses A by 1,000
 * times algorithm to naive sort the A algorithm on each iteration
 *
 * @author     Genji Nakano (GZN0006@tigermail.auburn.edu)
 * @author     Wilbur Rotoni (dh@auburn.edu)
 * @version    01-19-2020
 *
 */



public class Main
{
   public static void main(String[] args) throws ParseException {


   // variables
      // int n = 50;
      int step = 1000;
      int L = 500000;
      int[] G = new int[L];
      Random rand = new Random();
      int[] A;
      float y; // for T(n) /n
      float x; // for T(n) /n^2 or (n*n)
      float z; // for T(n) / n ^3 or (n*n*n)
      String fileName = "F.txt";





      try {
         FileWriter writer = new FileWriter(fileName);

         writer.write("n,T(n)/n,T(n)/n^2,T(n)/n^3");
         writer.write("\r\n");




      // fill up array with random numbers
         for (int k = 0; k < G.length; k++) {
            int rand_int1 = rand.nextInt(L + 1);
            G[k] = rand_int1;
         }


      // create an array wiht length n = 5000 with an increase in step by 1000
         for (int n = 5000; n <= G.length; n+=step) {

            A = new int[n];
            for (int m = 0; m < A.length; m++){
               A[m] = G[m];
            //System.out.println("The elements of A is: " + A[m]);

            }

         // timing function
            //System.out.println("The size of the Array timed is: " + A.length);
            float startTime = System.nanoTime();
          //  System.out.println("The startTime is: " + startTime);
            naiveSorting(A);
            float endTime = System.nanoTime();
          //  System.out.println("The endTime is " + endTime);
            float durationInNano = (endTime - startTime);  //Total execution time in nano seconds

            //System.out.println("The time of this function in nano is: " + durationInNano);
            float durationInMillis = durationInNano / 1000000;
            //System.out.println("The T(n) in milliseconds : " +
          //     		durationInMillis);
         //for (int u = 0; u < A.length; u++) {
            //System.out.println("The sorted array elements are: " + A[u]);

         //}

         // writing function to F file
            writer.write(n + ",");
            writer.write(durationInMillis + ",");
            y = durationInMillis / n;
            //System.out.println("The T(n) / n is: " + y);
            writer.write(y + ",");
            x = (durationInMillis / (n*n));
            //System.out.println("The T(n) / n^2 is: " + x);
            writer.write(x + ",");
            z = (durationInMillis / (n*n*n));
            //System.out.println("The T(n) / n^3 is: " + z);
            writer.write(z + ",");
            writer.write("\r\n");



         }
         writer.close();


      }
      catch(IOException e) {
         e.printStackTrace();
      }





   }


         /**
    * Naive Sorting - sorts an array in descending order to be timed.
    * param int array A to be sorted
    */
   public static void naiveSorting(int[]A) {
      for (int j = 0; j < A.length - 1; j++) {
         for (int i = (j+1);i < A.length; i++) {
            if (A[i] < A[j]) {
               int buffer = A[j];
               A[j] = A[i];
               A[i] = buffer;
            }
         }

      }

   }


}
