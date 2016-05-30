package io.anastas.robitgcodeinterpreter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class GInstructionReader {
	public static void main(String[] args) {
		try {
			// Read gcode file from parameters
			String fileName = "";
			if (args.length != 1) {
				System.err.println("Usage: robitgcodeinterpreter.jar <filename.gcode>");
				System.exit(1);
			} else {
				fileName = args[0];
			}
			BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
			ArrayList<GInstruction> GInstructionList = new ArrayList<GInstruction>();
			
			// Loop through every line in the file, parse it, and store in GInstruct ArrayList
			String currentLine = fileReader.readLine();
			while(currentLine != null) {
				GInstruction currentInstruction = GInstruction.fromLine(currentLine);
				
				if (currentInstruction != null)
					GInstructionList.add(currentInstruction);
				
				currentLine = fileReader.readLine();
			}
			
			// GInstructionList now contains a GInstruction object for each line of the file
			// We can print out all the instructions to confirm this
			for (GInstruction g : GInstructionList) { // For every GInstruction "g" in GInstructionList...
				System.out.println(g);
			}
			
			// Execute the instructions
			GInstructionHandler.executeGInstructions(GInstructionList);
			
			fileReader.close();
		} catch (Exception e) {e.printStackTrace();}
	}
}
