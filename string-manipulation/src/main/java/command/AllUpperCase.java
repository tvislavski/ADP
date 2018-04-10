package command;

import print.UpperCasePrintStream;

public class AllUpperCase extends Command {

	public AllUpperCase() {
		super(new UpperCasePrintStream());
	}
}
