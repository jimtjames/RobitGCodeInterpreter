package io.anastas.robitgcodeinterpreter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.ergotech.brickpi.BrickPi;
import com.ergotech.brickpi.motion.Motor;
import com.ergotech.brickpi.motion.MotorPort;
import com.ergotech.brickpi.sensors.SensorPort;
import com.ergotech.brickpi.sensors.TouchSensor;

public class GInstructionReader {
	public static void main(String[] args) {
		BrickPi brickPi = null;
		try {
			brickPi = new BrickPi();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Motor xAxis = new Motor();
		Motor yAxis = new Motor();
		brickPi.setMotor(xAxis, MotorPort.MA);
		brickPi.setMotor(yAxis, MotorPort.MB);
		TouchSensor xTouch = new TouchSensor();
		TouchSensor yTouch = new TouchSensor();
		brickPi.setSensor(xTouch, SensorPort.S1);
		brickPi.setSensor(yTouch, SensorPort.S2);
		boolean calibratedX = false;
		boolean calibratedY = false;
		while (!xTouch.isSet() && calibratedX == false){
			if (xTouch.isSet()){
				calibratedX = true;
			}
			xAxis.rotate(-0.01, 5);
		}
		while (!yTouch.isSet() && calibratedY == false){
			if (yTouch.isSet()){
				calibratedY = true;
			}
			yAxis.rotate(-0.01, 5);
		}
		
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
