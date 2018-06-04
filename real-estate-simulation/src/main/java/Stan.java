import java.util.Optional;

public class Stan extends StambeniObjekat {

	public static Optional<Stan> kreiraj(String... tokeni) {
		try {
			return Optional.of(new Stan(Integer.parseInt(tokeni[0].trim()), Double.parseDouble(tokeni[1].trim()),
					Integer.parseInt(tokeni[2].trim())));
		} catch (NumberFormatException e) {
			return Optional.empty();
		}

	}

	private int sprat;

	public int getSprat() {
		return sprat;
	}

	public Stan(int brojSoba, double cenakvadrata, int sprat) {
		super(brojSoba, cenakvadrata);
		this.sprat = sprat;
	}

	@Override
	public double vrednost() {
		return super.vrednost() * koeficijentZaCenu();
	}

	private double koeficijentZaCenu() {
		if (prizemlje())
			return 0.1;
		if (izmedju5i10sprata())
			return 0.05;
		if (penthouse())
			return 1.5;
		return 1;
	}

	private boolean prizemlje() {
		return sprat == 0;
	}

	private boolean izmedju5i10sprata() {
		return sprat > 5 && sprat < 10;
	}

	private boolean penthouse() {
		return sprat >= 10;
	}

}
