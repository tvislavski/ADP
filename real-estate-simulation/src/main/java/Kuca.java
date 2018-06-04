import java.util.Optional;

public class Kuca extends StambeniObjekat {

	public static Optional<Kuca> kreiraj(String... tokeni) {
		try {
			return Optional.of(new Kuca(Integer.parseInt(tokeni[0].trim()), Double.parseDouble(tokeni[1].trim())));
		} catch(NumberFormatException e) {
			return Optional.empty();
		}
		
	}

	public Kuca(int brojSoba, double cenakvadrata) {
		super(brojSoba, cenakvadrata);
	}

}
