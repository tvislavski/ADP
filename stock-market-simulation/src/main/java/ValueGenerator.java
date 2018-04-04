

public enum ValueGenerator {

	INSTANCE;

	public static final int SLEEP_SECONDS = 5;

	private double currentValue;

	public double getCurrentValue() {
		return currentValue;
	}

	public Thread generate(double min, double max) {
		System.out.println("Starting value generation for min = " + min + ", max = " + max);
		Thread thread = new Thread(() -> {
			while (true) {
				generateValue(min, max);
				try {
					Thread.sleep(SLEEP_SECONDS * 1000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					break;
				}
			}
		});
		thread.start();
		return thread;
	}

	private void generateValue(double min, double max) {
		currentValue = Math.random() * (max - min) + min;
		System.out.println(currentValue);
	}
}
