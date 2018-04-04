

public class Application {

	public static final double MIN = 0;
	public static final double MAX = 5;

	public static void main(String[] args) throws InterruptedException {
		double min = MIN;
		double max = MAX;
		if (args.length == 2) {
			try {
				min = Double.parseDouble(args[0].trim());
				max = Double.parseDouble(args[1].trim());
			} catch (NumberFormatException e) {
				String value = e.getMessage().substring(e.getMessage().indexOf("\"") + 1, e.getMessage().lastIndexOf(
						"\""));
				System.out.println("Error while parsing value " + value);
			}
		}
		Thread thread = ValueGenerator.INSTANCE.generate(min, max);
		Thread.sleep(60000);
		thread.interrupt();
		thread.join();
	}

}
