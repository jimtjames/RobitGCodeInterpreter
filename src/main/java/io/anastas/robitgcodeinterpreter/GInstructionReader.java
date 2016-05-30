package io.anastas.robitgcodeinterpreter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class GInstructionReader {
	public static void main(String[] args) {
		try {
			// Open file C:\gcode.txt
			BufferedReader fileReader = new BufferedReader(new FileReader("C:\\gcode.txt"));
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
			fileReader.close();
		} catch (Exception e) {e.printStackTrace();}
	}
}
