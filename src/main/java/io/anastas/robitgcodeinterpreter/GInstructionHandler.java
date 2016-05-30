package io.anastas.robitgcodeinterpreter;

import java.util.ArrayList;

public class GInstructionHandler {
	public static void executeGInstructions(ArrayList<GInstruction> instructionList) {
		// Loop through each element of the list and execute
		for (int i=0; i<instructionList.size(); i++) {
			GInstruction curInstruction = instructionList.get(i);
			System.out.println("Executing Instruction type: " + curInstruction.type);
			System.out.println("Previous type was: " + instructionList.get(i-1).type);
		}
	}
}
