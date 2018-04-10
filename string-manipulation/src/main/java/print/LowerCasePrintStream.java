package print;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class LowerCasePrintStream implements print.PrintStream {

	private File log;

	public LowerCasePrintStream() {
		log = new File(getClass().getClassLoader().getResource("lower-case.log").getFile());
	}
	
	@Override
	public void println(String s) {
		try (PrintStream printStream = new PrintStream(new FileOutputStream(log, true), true)) {
			printStream.println(s.toLowerCase());
		} catch (FileNotFoundException e) {
			System.err.println(s.toLowerCase());
		}
	}

}
