package io.anastas.robitgcodeinterpreter;

public class GInstruction {
	public String type;
	public double xcoord, ycoord, zcoord, extrude, temperature;
	
	public GInstruction(String type, double xcoord, double ycoord, double zcoord, double extrude, double temperature) {
		this.type = type;
		this.xcoord = xcoord;
		this.ycoord = ycoord;
		this.zcoord = zcoord;
		this.extrude = extrude;
		this.temperature = temperature; 
	}
	
	public String toString() {
		return "Type: " + type + ", X: " + xcoord + ", Y: " + ycoord + ", Z: " + zcoord + ", E: " + extrude + ", S: " + temperature;
	}
	
	/**
	 * Parses a line containing a GInstruction and returns a GInstruction object
	 * @param line line of GInstruction
	 * @return GInstruction object
	 */
	public static GInstruction fromLine(String line) {
		if (line.length() == 0) return null; // If line is empty, its useless

		if (line.charAt(0) == 'G') { // Handle G code
			String type, temp;
			double xcoord = 0, ycoord = 0, zcoord = 0, extrude = 0;

			// Extract type
			type = line.substring(0, getDelimiter(line));

			// Extract X coord
			if (line.contains("X")) {
				temp = line.substring(line.indexOf("X"));
				temp = temp.substring(1, getDelimiter(temp));
				xcoord = Double.parseDouble(temp);
			}

			// Extract Y coord
			if (line.contains("Y")) {
				temp = line.substring(line.indexOf("Y"));
				temp = temp.substring(1, getDelimiter(temp));
				ycoord = Double.parseDouble(temp);
			}

			// Extract Z coord
			if (line.contains("Z")) {
				temp = line.substring(line.indexOf("Z"));
				temp = temp.substring(1, getDelimiter(temp));
				zcoord = Double.parseDouble(temp);
			}

			// Extract extrude
			if (line.contains("E")) {
				temp = line.substring(line.indexOf("E"));
				temp = temp.substring(1, getDelimiter(temp));
				extrude = Double.parseDouble(temp);
			}

			return new GInstruction(type, xcoord, ycoord, zcoord, extrude, 0);
		} else if (line.charAt(0) == 'M') { // Handle M code
			String type, temp;
			double temperature = 0;
			
			type = line.substring(0, getDelimiter(line));
			if (line.contains("S")) {
				temp = line.substring(line.indexOf("S"));
				temp = temp.substring(1, getDelimiter(temp));
				temperature = Double.parseDouble(temp);
			}
			
			return new GInstruction(type, 0, 0, 0, 0, temperature);
		} else { // Invalid code
			return null;
		}
	}

	/**
	 * Get the index of the string delimiter (either a space or end of line)
	 * @param line
	 */
	private static int getDelimiter(String line) {
		int index = line.indexOf(" ");
		if (index <= 0) {
			index = line.length();
		}
		return index;
	}
}
