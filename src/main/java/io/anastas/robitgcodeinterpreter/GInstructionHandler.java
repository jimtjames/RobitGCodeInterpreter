package io.anastas.robitgcodeinterpreter;

import java.io.IOException;
import java.util.ArrayList;

import com.ergotech.brickpi.BrickPi;
import com.ergotech.brickpi.motion.Motor;
import com.ergotech.brickpi.motion.MotorPort;

public class GInstructionHandler {
	
	
	
	public static void executeGInstructions(ArrayList<GInstruction> instructionList) {
		// Loop through each element of the list and execute
		for (int i=0; i<instructionList.size(); i++) {
			BrickPi brickPi = null;

			try {
				brickPi = new BrickPi();
			} catch (IOException e) {
				e.printStackTrace();
			}
			double rotationToMM = 1/127.2;
			GInstruction curInstruction = instructionList.get(i);
			System.out.println("Executing Instruction type: " + curInstruction.type);
			System.out.println("Previous type was: " + instructionList.get(i-1).type);

			Motor xAxis = new Motor();
			Motor yAxis = new Motor();
			Motor zAxis = new Motor();
			brickPi.setMotor(xAxis, MotorPort.MA);
			brickPi.setMotor(yAxis, MotorPort.MB);
			brickPi.setMotor(zAxis, MotorPort.MC);
		}
	}
}
