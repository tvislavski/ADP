
public class NemaMesta extends Exception {

	public NemaMesta(StambeniObjekat stambeniObjekat, double kvadratura) {
		super(String.format("Nema mesta za sobu od %.1f kvadrata u %s", kvadratura, stambeniObjekat));
	}

}
