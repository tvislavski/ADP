package news;

import java.util.Date;

public class UrgentNews extends News {

	public UrgentNews(String subject, Date date, String content) {
		super(subject, date, content);
	}

	@Override
	protected String subjectForPrinting() {
		return getSubject().toUpperCase();
	}

	@Override
	protected Date dateForPrinting() {
		return getDate();
	}

	@Override
	protected String contentForPrinting() {
		return getContent();
	}

}
