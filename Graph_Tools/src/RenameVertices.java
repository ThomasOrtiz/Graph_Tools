import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Given a files like below, this program will rename vertices id 
 * from letters to indices or vice versa.
 * 
 * It does so by using intToLetters() or lettersToInts().
 * 
 * Replace these method calls in renameVertices based on how you want your graph renamed.
 * 
 * Graph_File
 * -------------------------
 * <vertex id> <xpos> <ypox>
 * ...
 * $
 * <v1> <v2> <weight>
 * ...
 * -------------------------
 * 
 * @author Thomas Ortiz (tdortiz)
 *
 */
public class RenameVertices {

	public static void main(String[] args) throws IOException {
		// Get user input
		/// TODO : Change this to your graph source
		String inputName  = "graphs/graph_5.txt";
		String outputName = "graphs/graph_5_renamed.txt";
		boolean useAlphaToNum = true;
		
        Scanner in = new Scanner(System.in);
        try { 
            String inputStr = "";
            String[] items = { "True", "False", "T", "F" };
            while( !Arrays.asList(items).contains(inputStr) ){
            	System.out.print("Converting Vertices From Alpha to Numeric? (T)rue or (F)alse : ");
            	inputStr = in.nextLine().toUpperCase();
            }

            if( inputStr.equals("True") || inputStr.equals("T") ){
            	useAlphaToNum = true;
            } else {
            	useAlphaToNum  = false;
            }
            
        } catch( InputMismatchException e){
            System.out.println("Invald input - insert a number");
            System.exit(1);
        }
        in.close();
		
		renameVertices(inputName, outputName, useAlphaToNum);
	}
	
	/**
     * Duplicates edges in a file:
     * 
     * If a--b exists then it creates b--a
     * 
     * @param fileName of file with edges
     * @throws IOException
     */
    public static void renameVertices(String inputFileName, String outputFileName, boolean useAlphaToNum){
    	Scanner in = null;
		BufferedWriter bw = null;
		FileWriter fw = null;
		File inputFile = new File(inputFileName);
		File outputFile = new File(outputFileName);
		
		try {
			// if input-file doesnt exists, then create it
			if (!inputFile.exists()) {
				throw new FileNotFoundException("ERROR: INPUT FILE DOESN'T EXIST");
			}
			
			outputFile.createNewFile();

			// true = append file
			fw = new FileWriter(outputFile.getAbsoluteFile(), false);
			bw = new BufferedWriter(fw);
			
			try {
				in = new Scanner(inputFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			// Scan for vertices
			Scanner lineScan = null;
			String line = "";
			while( in.hasNextLine() ){
				line = in.nextLine();
				if(line.equals("$")) break;
				
				lineScan = new Scanner(line);
				String id1 = "";
				if( useAlphaToNum ){
					id1 = "" + lettersToInts( lineScan.next() ); // (if going from a --> 0)
				} else {
					id1 = "" + intsToLetters( Integer.parseInt(lineScan.next()) ); // (if going from 0 --> a)
				}
				int x = lineScan.nextInt();
				int y = lineScan.nextInt();
				
				bw.write(id1 + " " + x + " " + y + "\n");
			}
			
			bw.write("$\n");
			
			// Scan for edges and copy them to the new file
	        Scanner edgeScan = null;
	        while( in.hasNextLine() ){
	            line = in.nextLine();
	            edgeScan = new Scanner(line);
	            
	            String id1 = "";
	            String id2 = "";
				if( useAlphaToNum ){
					id1 = "" + lettersToInts( edgeScan.next() ); // (if going from a --> 0)
		            id2 = "" + lettersToInts( edgeScan.next() ); // (if going from a --> 0)
				} else {
					id1 = "" + intsToLetters( Integer.parseInt(edgeScan.next()) ); // (if going from 0 --> a)
		            id2 = "" + intsToLetters( Integer.parseInt(edgeScan.next()) ); // (if going from 0 --> a)
				}
	            double weight = edgeScan.nextDouble();
	            
	            bw.write( id1 + " " + id2 + " " + weight + "\n" );
	        }
			
			// Close scanners
			in.close();
			edgeScan.close();
			lineScan.close();
			bw.close();
		} catch (IOException e){
			System.out.println(e.getMessage());
		}
		
    }
    
    /**
     * Changes index from ints to alphas (like excel)
     * @param value
     * @return
     */
    public static String intsToLetters(int value) {
    	String result = "";
        while (value >= 0) {
        	result = (char)('a' + value % 26 ) + result;
            value /= 26;
            value--;
        }
        return result;
    }
    
    /**
     * Changes index from ints to alphas (like excel)
     * @param value
     * @return
     */
    public static int lettersToInts(String value) {
    	value = value.toUpperCase();
    	int sum = 0;
    	
    	for(char c : value.toCharArray()){
    		sum = sum * 26 + c - 'A' + 1;
    	}
    	
    	sum--; // take 1 off since we added 1 earlier for the calculation
    	
        return sum;
    }
	
}
