import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Given a file (like described below) duplicate the edges in 
 * reverse order and appends the new edges onto the given file
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
public class DupEdges {

    public static void main(String[] args) throws IOException {
        /// TODO : Change this to your graph source
    	dupEdges("graphs/Graph_6V_7E.txt", "graphs/Graph_6V_7E_DupedEdges.txt");
    }
    
    /**
     * Duplicates edges in a file:
     * 
     * If a--b exists then it creates b--a
     * 
     * @param fileName of file with edges
     * @throws IOException
     */
    public static void dupEdges(String inputFileName, String outputFileName) throws IOException{
    	Scanner in = null;
		BufferedWriter bw = null;
		FileWriter fw = null;
		File inputFile = new File(inputFileName);
		File outpuFile = new File(outputFileName);
		
		// if input-file doesnt exists, error out
        if (!inputFile.exists()) {
            throw new FileNotFoundException("ERROR: INPUT FILE DOESN'T EXIST");
        }
		
		// if output-File doesnt exists, then create it
		if (!outpuFile.exists()) {
		    outpuFile.createNewFile();
		}
		
		try {
            in = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

		// true = append file
		fw = new FileWriter(outpuFile.getAbsoluteFile(), false);
		bw = new BufferedWriter(fw);
		
		// Scan for vertices
        Scanner lineScan = null;
        while( in.hasNextLine() ){
            String line = in.nextLine();
            if(line.equals("$")) break;
            
            lineScan = new Scanner(line);
            String id1 = lineScan.next();
            int x = lineScan.nextInt();
            int y = lineScan.nextInt();
            
            bw.write(id1 + " " + x + " " + y + "\n");
        }
        
        bw.write("$\n");
		
		// Scan for edges and copy them to the new file
		Scanner edgeScan = null;
		while( in.hasNextLine() ){
			String line = in.nextLine();
			edgeScan = new Scanner(line);
			String id1 = edgeScan.next();
			String id2 = edgeScan.next();
			double weight = edgeScan.nextDouble();
			
			bw.write(id1 + " " + id2 + " " + weight + "\n");
			bw.write(id2 + " " + id1 + " " + weight + "\n");
		}
		
		// Close scanners
		in.close();
		lineScan.close();
		edgeScan.close();
		bw.close();
    }
    
}