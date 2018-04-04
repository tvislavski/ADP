

public class ValueGeneratorAdapter {

	private ValueGenerator valueGenerator = ValueGenerator.INSTANCE;
	
	public Stock getValue() {
		return new Stock(valueGenerator.getCurrentValue());
	}
}
