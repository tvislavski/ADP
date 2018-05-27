public class FacebookProfil extends ProfilNaSocijalnojMrezi {
	
	private static final String NEW_LINE = System.getProperty("line.separator");

	public static FacebookProfil kreirajOdStringa(String string, String separator) {
		String[] tokeni = string.split(separator);
		if (tokeni.length == 4)
			return new FacebookProfil(Integer.parseInt(tokeni[2].trim()), tokeni[0].trim(), tokeni[1].trim(),
					tokeni[3].trim());
		else
			return new FacebookProfil(Integer.parseInt(tokeni[2].trim()), tokeni[0].trim(), tokeni[1].trim());
	}

	private String id, ime, status;

	public FacebookProfil(int brojPrijatelja, String id, String ime, String status) {
		this(brojPrijatelja, id, ime);
		this.status = status;
	}

	public FacebookProfil(int brojPrijatelja, String id, String ime) {
		super(brojPrijatelja);
		this.id = id;
		this.ime = ime;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FacebookProfil other = (FacebookProfil) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	private String info() {
		String status = this.status == null ? "" : ", status=" + this.status;
		return "FacebookProfil [id=" + id + ", ime=" + ime + status + "]";
	}

	@Override
	public String toString() {
		String prijatelji = getPrijatelji().stream().map(p -> ((FacebookProfil)p).info()).reduce("", (a, b) -> a + NEW_LINE + b);
		return info() + NEW_LINE + "Prijatelji:" + prijatelji + NEW_LINE + NEW_LINE;
	}
}
