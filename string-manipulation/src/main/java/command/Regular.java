package command;

import java.io.FileNotFoundException;

import print.RegularPrintStream;

public class Regular extends Command {

	public Regular() {
		try {
			printStream = new RegularPrintStream();
		} catch (FileNotFoundException e) {
			printStream = System.err::println;
		}
	}
}
