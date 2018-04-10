package command;

import print.LowerCasePrintStream;

public class AllLowerCase extends Command {

	public AllLowerCase() {
		super(new LowerCasePrintStream());
	}
}
