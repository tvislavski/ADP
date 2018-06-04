import java.util.*;

public abstract class StambeniObjekat implements Nekretnina {
	
	public static Optional<? extends StambeniObjekat> kreiraj(String... tokeni) {
		switch(tokeni.length) {
		case 2: return Kuca.kreiraj(tokeni);
		case 3: return Stan.kreiraj(tokeni);
		default: return Optional.empty();
		}
	}

	private ArrayList<Soba> sobe;
	private int brojSoba;
	private double cenaPoKvadratu;

	public StambeniObjekat(int brojSoba, double cenaKvadrata) {
		sobe = new ArrayList<Soba>();
		this.brojSoba = brojSoba;
		this.cenaPoKvadratu = cenaKvadrata;
	}

	@Override
	public String toString() {
		return String.format("%s (%d, %.1f): %s", getClass().getSimpleName(), brojSoba, cenaPoKvadratu,
				sobe.stream().map(soba -> soba.toString()).reduce("", (a, b) -> a + b));
	}

	public double kvadratura() {
		return sobe.stream().mapToDouble(soba -> soba.kvadratura).sum();
	}

	public double vrednost() {
		return kvadratura() * cenaPoKvadratu;
	}

	public void dodajSobu(double kvadratura) throws NemaMesta {
		if (sobe.size() < brojSoba) {
			sobe.add(new Soba(kvadratura));
		} else {
			pregradiNekuSobu(kvadratura);
		}

	}

	private void pregradiNekuSobu(double kvadratura) throws NemaMesta {
		Soba sobaZaPregradjivanje = nadjiSobuZaPregradjivanje(kvadratura)
				.orElseThrow(() -> new NemaMesta(this, kvadratura));
		sobe.add(sobaZaPregradjivanje.pregradiSobu(kvadratura));
	}

	private Optional<Soba> nadjiSobuZaPregradjivanje(double kvadratura) {
		return sobe.stream().filter(soba -> soba.getKvadratura() - kvadratura >= 2)
				.min((s1, s2) -> Double.compare(s1.getKvadratura(), s2.getKvadratura()));
	}

	private class Soba {
		private double kvadratura;

		public Soba(double kvadratura) {
			this.kvadratura = kvadratura;
		}

		public Soba pregradiSobu(double kvadratura) {
			this.kvadratura = this.kvadratura - kvadratura;
			return new Soba(kvadratura);
		}

		public String toString() {
			return kvadratura + "m^2" + "|";
		}

		public double getKvadratura() {
			return kvadratura;
		}
	}

}
