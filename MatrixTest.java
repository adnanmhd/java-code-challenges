import java.util.Scanner;

public class MatrixTest {

	public static void main(String[] args) {
		Scanner inputScan = new Scanner(System.in);
	    System.out.print("Masukan dimensi matrix anda: ");

	    int matrixLength = inputScan.nextInt(); 
	    int[][] arrMatrix = new int[matrixLength][matrixLength];
	    
	    System.out.println("Masukan nilai matrix anda");
	    for(int row=0; row<matrixLength; row++){
	    	for(int col=0; col<matrixLength; col++){
	    		System.out.print("baris-"+(row+1)+", kolom-"+(col+1)+" :");
	    		arrMatrix[row][col] = inputScan.nextInt(); 
	    	}
	    }
	    
	    System.out.println();
	    System.out.println("Berikut matrix anda: "); 
	    printMatrix(arrMatrix);
	    System.out.println();
	    System.out.println("Hasil selisih antar dua diagonal: " + getSelisihAntarDiagonal(arrMatrix));
		
	}
	
	public static int getSelisihAntarDiagonal(int[][] arr){
		int result = getDiagonalKiriKanan(arr) - getDiagonalKananKiri(arr);
		return Math.abs(result);
	}
	
	public static int getDiagonalKiriKanan(int[][] arr){
		int result = 0;
		
		for(int i=0; i<arr.length; i++){
			result += arr[i][i];
	    }
		return result;
	}
	
	public static int getDiagonalKananKiri(int[][] arr){
		int result = 0;
		int col=arr.length-1;
		
		for(int row=0; row<arr.length; row++){
			result += arr[row][col];
			col--;
    	}
		return result;
	}
	
	public static void printMatrix(int[][] arr){
		for(int row=0; row<arr.length; row++){
	    	for(int col=0; col<arr.length; col++){
	    		System.out.print(arr[row][col] + " ");
	    	}
	    	System.out.println();
	    }
	}
}
