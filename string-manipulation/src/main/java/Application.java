import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import command.Command;

public class Application {

	public static void main(String[] args) throws IOException {
		String s;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while ((s = in.readLine()) != null && !s.equals("STOP")) {
			if (s.length() == 0) continue;
			Command.get(s).execute(s);
		}
	}
}
