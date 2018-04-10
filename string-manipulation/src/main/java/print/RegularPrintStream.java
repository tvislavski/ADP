package print;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class RegularPrintStream extends PrintStream implements print.PrintStream {

	public RegularPrintStream() throws FileNotFoundException {
		super(new FileOutputStream(
				new File(RegularPrintStream.class.getClassLoader().getResource("regular.log").getFile()), true), true);
	}
}
