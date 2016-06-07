package io.anastas.robitgcodeinterpreter;

import java.io.IOException;
import java.util.ArrayList;

public class GInstructionHandler {
	
	private static final NXTRegulatedMotor mA = new NXTRegulatedMotor(MotorPort.A);
	private static final NXTRegulatedMotor mB = new NXTRegulatedMotor(MotorPort.B);
	
	public static void executeGInstructions(ArrayList<GInstruction> instructionList) throws InterruptedException {		
			System.out.println("setting up");
			/**while (!xTouch.isSet()) {
				xAxis.rotate(-0.01, 5);
			}
			while (!yTouch.isSet()) {
				yAxis.rotate(-0.01, 5);
			}**/
			double xyRotationToMM = 1/127.2;
			// Loop through each element of the list and execute
			double curX = 0, curY = 0, curZ = 0, curE = 0;
			double prevX = 0, prevY = 0, prevZ = 0, prevE = 0;
			for (int i=0; i<instructionList.size(); i++) {
				GInstruction curInstruction = instructionList.get(i);
				
				// Update position variables
				if (curInstruction.xcoord != 0) {
					if (curX != 0) prevX = curX;
					curX = curInstruction.xcoord;
				}
				if (curInstruction.ycoord != 0) {
					if (curY != 0) prevY = curY;
					curY = curInstruction.ycoord;
				}
				if (curInstruction.zcoord != 0) {
					if (curZ != 0) prevZ = curZ;
					curZ = curInstruction.zcoord;
				}
				if (curInstruction.extrude != 0) {
					if (curE != 0) prevE = curE;
					curE = curInstruction.extrude;
				}
				
				

				
				System.out.println("Executing Instruction type: " + curInstruction.type);
				//System.out.println("Previous type was: " + instructionList.get(i-1).type);
				if (curInstruction.type.equals("G1")) {
					
					System.out.println(curInstruction.xcoord-prevX);
					double timeToMM = 60000/127.2;
					mA.setSpeed(360);
					mB.setSpeed(360);
					mA.rotate(((curInstruction.xcoord-prevX)/127.2));
					Thread.sleep(1000);
				}
			}
		} 
		
	}

