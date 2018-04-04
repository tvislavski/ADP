
import org.junit.Assert;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class ValueGeneratorAdapterTest {

	@DataPoints
	public static double[][] ranges = { { 0, 1 }, {100, 999}, {2.5, 17.9} };

	@Theory
	public void getValue(double[] ranges) throws Exception {
		Thread t = ValueGenerator.INSTANCE.generate(ranges[0], ranges[1]);
		Thread.sleep(2000);
		Assert.assertNotNull(new ValueGeneratorAdapter().getValue());
		Stock stock = new ValueGeneratorAdapter().getValue();
		Assert.assertTrue(stock.getStockMarketValue() >= ranges[0]);
		Assert.assertTrue(stock.getStockMarketValue() <= ranges[1]);
		t.interrupt();
		t.join();
	}
}
