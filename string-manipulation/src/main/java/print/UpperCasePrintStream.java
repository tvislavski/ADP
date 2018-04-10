package print;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class UpperCasePrintStream implements print.PrintStream {

	private File log;

	public UpperCasePrintStream() {
		log = new File(getClass().getClassLoader().getResource("upper-case.log").getFile());
	}

	@Override
	public void println(String s) {
		try (PrintStream printStream = new PrintStream(new FileOutputStream(log, true), true)) {
			printStream.println(s.toUpperCase());
			printStream.flush();
		} catch (FileNotFoundException e) {
			System.err.println(s.toUpperCase());
		}
	}
}
