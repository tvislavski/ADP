import java.util.LinkedList;
import java.util.List;

public abstract class ProfilNaSocijalnojMrezi implements CvorUMrezi {

	private List<ProfilNaSocijalnojMrezi> prijatelji;
	private int brojPrijatelja;

	ProfilNaSocijalnojMrezi(int brojPrijatelja) {
		this.brojPrijatelja = brojPrijatelja;
		this.prijatelji = new LinkedList<>();
	}
	
	protected List<ProfilNaSocijalnojMrezi> getPrijatelji() {
		return prijatelji;
	}

	@Override
	public boolean sused(CvorUMrezi c) {
		return prijatelji.contains(c);
	}

	public void dodajPrijatelja(ProfilNaSocijalnojMrezi pr) throws NekonzistentniPodaci {
		if (prijatelji.size() < brojPrijatelja) {
			prijatelji.add(pr);
		} else {
			throw new NekonzistentniPodaci("Previse ih je u " + this.toString());
		}
	}
}
