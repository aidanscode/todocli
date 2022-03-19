package com.aidanmurphey.todocli.utility;

public class Parsing {

	public static int stringToInt(String str) {
		int val = 0;
		try {
			val = Integer.parseInt(str);
		} catch(NumberFormatException e) {
			System.err.println("Failed to parse string \"" + str + "\" as an int.");
			System.exit(1);
		}

		return val;
	}

}
