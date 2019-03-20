import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;
/**
 * Given an input file with many nodes and many edges
 * and with the line-structure:
 * 
 * node-name x-coord y-coord
 * $
 * node1 node2
 * 
 * Outputs a file with the same nodes and added edge weights
 * @author grysvn
 *
 */
public class AddEdgeWeights {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int exitCode = 0;
		try {
			// Get a valid input and output file
			String inFileName;
			File inFile;
			String outFileName;
			File outFile;
			// We'll loop until we have a valid input file name that we can read from
			do {
				System.out.print("Please type a valid file name to add edge weights to: ");
				inFileName = in.nextLine();
				inFile = new File(inFileName);
			}
			while (!(inFile.exists() && inFile.canRead()));
			// We'll loop until we can write to the file or it can be created
			do {
				System.out.print("Please enter a different output file name (that is writeable or can be created): ");
				outFileName = in.nextLine();
				outFile = new File(outFileName);
			}
			while (!(outFile.canWrite() || outFile.createNewFile()));
			BufferedReader reader = new BufferedReader(new FileReader(inFileName));
			BufferedWriter writer = new BufferedWriter(new FileWriter(outFile, false));
			
			// Lets parse the file's nodes into dict with locations
			HashMap<String, Point> nodes = new HashMap<String, Point>();
			String line;
			while ( !(line = reader.readLine()).equals("$") && line != null ) {
				// Should have 3 tokens per line, first string, second two ints
				String[] tokens = line.split(" ");
				String nodeName = tokens[0];
				int x = Integer.parseInt(tokens[1]);
				int y = Integer.parseInt(tokens[2]);
				nodes.put(nodeName, new Point(x, y));
				writer.write(line);
				writer.newLine();
			}
			if (!line.equals("$")) {
				System.out.println("No valid separator between nodes and edges.");
				System.exit(1);
			}
			writer.write("$");
			writer.newLine();
			
			DecimalFormat df = new DecimalFormat("#.##");
			// Now let's calculate edges
			while ( (line = reader.readLine()) != null ) {
				String[] tokens = line.split(" ");
				Point p1 = nodes.get(tokens[0]);
				Point p2 = nodes.get(tokens[1]);
				writer.write(line + " " + df.format(p1.distance(p2)));
				writer.newLine();
			}
			
			reader.close();
			writer.close();
			
			System.out.println("New graph file written to " + outFileName);	
		} catch (Exception ex) {
			System.out.println(ex.getLocalizedMessage());
			exitCode = 1;
		} finally {
			in.close();
			System.exit(exitCode);
		}
	}
}
