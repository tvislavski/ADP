package news;

import java.util.Calendar;
import java.util.Date;

public class RegularNews extends News {

	public RegularNews(String subject, Date date, String content) {
		super(subject, date, content);
	}

	@Override
	protected String subjectForPrinting() {
		return getSubject();
	}

	@Override
	protected Date dateForPrinting() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, 24);
		return cal.getTime();
	}

	@Override
	protected String contentForPrinting() {
		return getContent();
	}

}
