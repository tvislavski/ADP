package news;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public abstract class News implements Subject {

	private List<Observer> observers = new LinkedList<>();
	private String subject;
	private Date date;
	private String content;

	public News(String subject, Date date, String content) {
		this.subject = subject;
		this.date = date;
		this.content = content;
	}

	protected String getSubject() {
		return subject;
	}

	protected Date getDate() {
		return date;
	}

	protected String getContent() {
		return content;
	}

	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	public void unregisterObserver(Observer observer) {
		observers.remove(observer);
	}

	public void notifyObservers() {
		observers.forEach(o -> o.update(this));
	}

	@Override
	public final String toString() {
		return String.format("%s %tF\n%s\n", subjectForPrinting(), dateForPrinting(), contentForPrinting());
	}
	
	public void publish() {
		System.out.println(String.format("Publishing news with subject: %s\n",subject));
		notifyObservers();
	}

	protected abstract String subjectForPrinting();

	protected abstract Date dateForPrinting();

	protected abstract String contentForPrinting();

}
