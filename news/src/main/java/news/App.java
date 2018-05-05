package news;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class App {

	private static List<User> users = Arrays.asList(new User("john.doe@example.com"), new User("jane.doe@example.com"),
			new User("asd.asd@example.com"));

	public static void main(String[] args) {
		registerAndPublish(new UrgentNews("Breaking news", Calendar.getInstance().getTime(), "Content content content"));
		registerAndPublish(new RegularNews("Boring news", Calendar.getInstance().getTime(), "Content content content"));
	}

	private static void registerAndPublish(News news) {
		randomlyRegisterObservers(news);
		news.publish();
	}

	private static void randomlyRegisterObservers(News news) {
		users.forEach(u -> {
			if (new Random().nextBoolean()) {
				news.registerObserver(u);
			}
		});
	}

}
