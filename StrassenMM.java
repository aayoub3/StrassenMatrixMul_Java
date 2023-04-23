// COPYRIGHTS.© ABANOB AYOUB 
// Strassen's MM
//This code takes the matrix size 'r' from the user for example r=4,
//and generates two random matrices of size r x r , and calculate the 
//result of multiplying both time each other. Also it claculates algorithm execution time.

package com.mycompany.strassenmm;
import java.util.Scanner;
import java.util.Random;


public class StrassenMM {


	public int[][] multiply(int[][] A, int[][] B)
	{
		// Order of matrix
		int n = A.length;

		// Creating a 2D square matrix with size n
		// n is input from the user
		int[][] R = new int[n][n];

		// Base case
		// If there is only single element
		if (n == 1)

			// Returning the simple multiplication of
			// two elements in matrices
			R[0][0] = A[0][0] * B[0][0];

		// Matrix
		else {
			// Step 1: Dividing Matrix into parts
			// by storing sub-parts to variables
			int[][] A11 = new int[n / 2][n / 2];
			int[][] A12 = new int[n / 2][n / 2];
			int[][] A21 = new int[n / 2][n / 2];
			int[][] A22 = new int[n / 2][n / 2];
			int[][] B11 = new int[n / 2][n / 2];
			int[][] B12 = new int[n / 2][n / 2];
			int[][] B21 = new int[n / 2][n / 2];
			int[][] B22 = new int[n / 2][n / 2];

			// Step 2: Dividing matrix A into 4 halves
			split(A, A11, 0, 0);
			split(A, A12, 0, n / 2);
			split(A, A21, n / 2, 0);
			split(A, A22, n / 2, n / 2);

			// Step 2: Dividing matrix B into 4 halves
			split(B, B11, 0, 0);
			split(B, B12, 0, n / 2);
			split(B, B21, n / 2, 0);
			split(B, B22, n / 2, n / 2);

			// Using Formulas as described in algorithm

			// M1:=(A1+A3)×(B1+B2)
			int[][] M1
				= multiply(add(A11, A22), add(B11, B22));
		
			// M2:=(A2+A4)×(B3+B4)
			int[][] M2 = multiply(add(A21, A22), B11);
		
			// M3:=(A1−A4)×(B1+A4)
			int[][] M3 = multiply(A11, sub(B12, B22));
		
			// M4:=A1×(B2−B4)
			int[][] M4 = multiply(A22, sub(B21, B11));
		
			// M5:=(A3+A4)×(B1)
			int[][] M5 = multiply(add(A11, A12), B22);
		
			// M6:=(A1+A2)×(B4)
			int[][] M6
				= multiply(sub(A21, A11), add(B11, B12));
		
			// M7:=A4×(B3−B1)
			int[][] M7
				= multiply(sub(A12, A22), add(B21, B22));

			// P:=M2+M3−M6−M7
			int[][] C11 = add(sub(add(M1, M4), M5), M7);
		
			// Q:=M4+M6
			int[][] C12 = add(M3, M5);
		
			// R:=M5+M7
			int[][] C21 = add(M2, M4);
		
			// S:=M1−M3−M4−M5
			int[][] C22 = add(sub(add(M1, M3), M2), M6);

			// Step 3: Join 4 halves into one result matrix
			join(C11, R, 0, 0);
			join(C12, R, 0, n / 2);
			join(C21, R, n / 2, 0);
			join(C22, R, n / 2, n / 2);
		}

		// Step 4: Return result
		return R;
	}


	public int[][] sub(int[][] A, int[][] B)
	{
		//
		int n = A.length;

		//
		int[][] C = new int[n][n];

		// Iterating over elements of 2D matrix
		// using nested for loops

		// Outer loop for rows
		for (int i = 0; i < n; i++)

			// Inner loop for columns
			for (int j = 0; j < n; j++)

				// Subtracting corresponding elements
				// from matrices
				C[i][j] = A[i][j] - B[i][j];

		// Returning the resultant matrix
		return C;
	}


	public int[][] add(int[][] A, int[][] B)
	{

		//
		int n = A.length;

		// Creating a 2D square matrix
		int[][] C = new int[n][n];

		// Iterating over elements of 2D matrix
		// using nested for loops

		// Outer loop for rows
		for (int i = 0; i < n; i++)

			// Inner loop for columns
			for (int j = 0; j < n; j++)

				// Adding corresponding elements
				// of matrices
				C[i][j] = A[i][j] + B[i][j];

		// Returning the resultant matrix
		return C;
	}


	public void split(int[][] P, int[][] C, int iB, int jB)
	{
		// Iterating over elements of 2D matrix
		// using nested for loops

		// Outer loop for rows
		for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)

			// Inner loop for columns
			for (int j1 = 0, j2 = jB; j1 < C.length;
				j1++, j2++)

				C[i1][j1] = P[i2][j2];
	}


	public void join(int[][] C, int[][] P, int iB, int jB)

	{
		// Iterating over elements of 2D matrix
		// using nested for loops

		// Outer loop for rows
		for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)

			// Inner loop for columns
			for (int j1 = 0, j2 = jB; j1 < C.length;
				j1++, j2++)

				P[i2][j2] = C[i1][j1];
	}

        
            public static int[][] generateRandomMatrix(int size) { //Creates Random n x n Matrix
        int[][] matrix = new int[size][size]; 
        Random random = new Random();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = random.nextInt(10); // Generate a random integer between 0 and 9
            }
        }
        return matrix;
    }
        
         public static void printMat(int[][] a, int r, int c){
    for(int i=0;i<r;i++){
      for(int j=0;j<c;j++){
        System.out.print(a[i][j]+" ");
      }
      System.out.println("");
    }
    System.out.println("");
  }
         

	public static void main(String[] args)
	{
           Scanner input = new Scanner(System.in);

           System.out.print("Enter an int matrix size ex) 2, 4, 8, etc... : ");
            int r = input.nextInt();

            
             //Generate and print first matrix input
        int[][] A = generateRandomMatrix(r);
        System.out.println("Array A =>");
        printMat(A,r,r);
    
    //Generate and print second matrix input
        int[][] B = generateRandomMatrix(r);
        System.out.println("Array B =>");
        printMat(B,r,r);

		// Create an object of Strassen class
		// in the main function
		StrassenMM s = new StrassenMM();

                
                
               //Start Timer 
               long startTime = System.nanoTime();
      
		// Matrix C computations
		// Matrix C calling method to get Result
		int[][] C = s.multiply(A, B);

                 //Stop Timer
                  long endTime = System.nanoTime();            
                
                //Calculate excution time
                 double executionTimeInSeconds = (endTime - startTime) / 1000000000.0;
                
		// Display message
		System.out.println(
			"\nProduct of matrices A and B : ");

		// Iterating over elements of 2D matrix
		// using nested for loops

		// Outer loop for rows
		for (int i = 0; i < r; i++) {
			// Inner loop for columns
			for (int j = 0; j < r; j++)

				// Printing elements of resultant matrix
				// with whitespaces in between
				System.out.print(C[i][j] + " ");

			// New line once the all elements
			// are printed for specific row
			System.out.println();
		}
                
                //Print excution time    
                System.out.println("\nAlgorithm Execution time: " + executionTimeInSeconds + " seconds");
	}
}

// COPYRIGHTS.© ABANOB AYOUB 
// COPYRIGHTS.© ABANOB AYOUB 

