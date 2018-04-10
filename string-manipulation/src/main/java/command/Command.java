package command;

import print.PrintStream;

public abstract class Command {

	public static Command get(String s) {
		char c = s.charAt(0);
		if (c >= 'A' && c <= 'Z') {
			return new AllUpperCase();
		} else if (c >= 'a' && c <= 'z') {
			return new AllLowerCase();
		}
		return new Regular();
	}

	protected PrintStream printStream;

	protected Command() {

	}

	public Command(PrintStream printStream) {
		this.printStream = printStream;
	}

	public void execute(String s) {
		printStream.println(s);
	}
}
