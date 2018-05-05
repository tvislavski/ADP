package news;

public class User implements Observer{
	
	private String email;
	
	public User(String email) {
		this.email = email;
	}

	public void sendMail(String content) {
		System.out.println(String.format("Sending email to %s:\n%s", email, content));		
	}

	public void update(Subject subject) {
		if (subject instanceof News)
			sendMail(subject.toString());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof User))
			return false;
		if (obj == this)
			return true;
		return email.equals(((User)obj).email);
	}
	
	@Override
	public int hashCode() {
		return email.hashCode();
	}

}
