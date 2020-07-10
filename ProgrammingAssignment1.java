import java.io.*;
import java.lang.*;
import java.util.*;

/**
 * CPSC-3283-AO1 -  Module 2- Programming Assignment
 *
 * @author     Genji Nakano (GZN0006@tigermail.auburn.edu)
 * @author     Wilbur Rotoni (wzr0018@auburn.edu)
 
 * @version    21 March 2020
 *
 * Creates a randomly built binary search tree using the 
 * Tree-Insert(T, z) algorithm discussed in Module 2 lecture.
 */

public class ProgrammingAssignment1 {

   public static void main(String args[]) throws IOException {
   
   /** 
    * Create file F    
    * The user should change this file directory (and file name) of his choosing. It is strongly recommended
    * that the user selects the same path/folder where this source code (i.e. M1Project.java) is saved.
    */   
      File file = new File("C:\\Users\\wrotoni\\Documents\\customertestdata\\test\\Algo\\Algo2\\Programs\\M2\\M2Project-File F.txt");
      //Instantiating the PrintStream class
      PrintStream stream = new PrintStream(file);
      
      System.setOut(stream);
      //Printing headers to file F
      System.out.println("n,Height(n)");
      
      // Call collectData()
      collectData();
      
   }
   
   
   public static void collectData() throws IOException {
   
      // creates random numbers
      Random rand = new Random();
   
      for (int n = 250; n <= 10000; n = n + 250) {   // 250, 500, 750, …. 10,000
         
         int sum_height_n = 0;
         
         for (int j = 1; j <= 10; j++) {   // Take 10 measurements mj for j=1 to 10
         
            BinarySearchTree T = new BinarySearchTree();
         
            for (int i = 1; i <= n; i++) {   // start building binary search tree with n nodes
            
            // pick randomly a number p in the range [0,n]
               int randomNumberLow = 0;
               int randomNumberHigh = n;
               int randomNumber = rand.nextInt(randomNumberHigh - randomNumberLow) + randomNumberLow;
            
            // create a node z and set z.key = p
               Node z = new Node(randomNumber);
               
            // call Tree-Insert(T, z)
               Tree_Insert(T, z);
            }
            
            // Measure the height hj of the tree
            int hj = getHeight(T.root);
            
            // Discard Tree
            T.root = null;
            
            // sum_height += hj
            sum_height_n += hj;
            
         }
            // Height(n)= sum_heightn/10 // Average height for n
         int Height_n = sum_height_n / 10;
            
            // Write in a file F the value n and Height(n)
         printToFileF(n,Height_n);
      }
   }
   
   // Tree-Insert Implementation
   public static void Tree_Insert(BinarySearchTree T, Node z) {
   
      Node y = null;
      Node x = T.root;
      
      while (x != null) {
      
         y = x;
         if (z.key < x.key) {
            x = x.left;
         }
         else {
            x = x.right;
         }
      }
      z.parent = y;
      
      if (y == null) {
         T.root = z;    // tree T was empty
      }
      else if (z.key < y. key) {
         y.left = z;
      }
      else {
         y.right = z;
      }
   }  
   
   // calculate height of randomly built binary tree
   public static int getHeight(Node root) {
   
      if (root == null || (root.right == null && root.left == null))  {
      
         return 0;
      }
      else {
         
         int leftHeight = getHeight(root.left);
         int rightHeight = getHeight(root.right);
         
         return 1 + ((leftHeight >= rightHeight) ? leftHeight : rightHeight);
      }
   }    
   
   public static void printToFileF(int n, int Height_n) throws IOException {
   
      try{
         FileWriter fstream = new FileWriter("M2Project-File F.txt",true);
         BufferedWriter out = new BufferedWriter(fstream);
         
         out.newLine();
      
         out.write(n + "," + Height_n);
      
         out.close();
         
      }catch (Exception e){
         System.err.println("Error while writing to file: " +
            e.getMessage());
      }
   }

}

// initial binary search tree
class BinarySearchTree {

   public static Node root;
  
   public BinarySearchTree() {
   
      this.root = null;
   }  
   
}

// Node class   
class Node {

   int key;
   Node left;
   Node right;	
   Node parent;
   
   public Node(int key) {
      
      this.key = key;
      left = null;
      right = null;
      parent = null;
   }
}


