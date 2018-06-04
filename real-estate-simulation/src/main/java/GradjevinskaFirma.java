import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class GradjevinskaFirma {
	StambeniObjekat[] objekti;

	public GradjevinskaFirma(String imef) {
		Par<List<String>, List<String>> grupisaneLinije = ucitajIzFajla(imef);
		objekti = new StambeniObjekat[grupisaneLinije.prvi.size()];
		ucitajObjekte(grupisaneLinije.prvi);
		dodajSobeUObjekte(grupisaneLinije.drugi);
	}

	private Par<List<String>, List<String>> ucitajIzFajla(String imef) {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(imef)))) {
			return podeli(reader.lines().collect(Collectors.toList()));
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (NullPointerException e) {
			System.err.println("Ne postoji fajl " + imef);
		}
		return new Par<List<String>, List<String>>(new LinkedList<>(), new LinkedList<>());
	}

	private Par<List<String>, List<String>> podeli(List<String> linije) {
		int broj = linije.stream().limit(1).mapToInt(prvaLinija -> Integer.parseInt(prvaLinija.trim())).sum();
		return new Par<List<String>, List<String>>(linije.stream().skip(1).limit(broj).collect(Collectors.toList()),
				linije.stream().skip(1 + broj).collect(Collectors.toList()));
	}

	private void ucitajObjekte(List<String> linije) {
		for (int i = 0, k = 0; i < linije.size(); i++) {
			Optional<? extends StambeniObjekat> o = StambeniObjekat.kreiraj(linije.get(i).split(";"));
			if (o.isPresent()) {
				objekti[k++] = o.get();
			}
		}
	}

	private void dodajSobeUObjekte(List<String> linije) {
		linije.forEach(linija -> {
			String[] tokeni = linija.split(":");
			Optional<StambeniObjekat> stambeniObjekat = nadjiObjekatSaIndeksom(Integer.parseInt(tokeni[0].trim()));
			if (stambeniObjekat.isPresent()) {
				dodajSobeUObjekat(stambeniObjekat.get(), Arrays.asList(tokeni[1].trim().split(";")).stream()
						.mapToDouble(token -> Double.parseDouble(token.trim())));
			}
		});
	}

	private void dodajSobeUObjekat(StambeniObjekat stambeniObjekat, DoubleStream kvadrature) {
		kvadrature.forEach(kvadratura -> {
			try {
				stambeniObjekat.dodajSobu(kvadratura);
			} catch (NemaMesta e) {
				System.err.println(e.getMessage());
			}
		});
	}

	private Optional<StambeniObjekat> nadjiObjekatSaIndeksom(int indeks) {
		if (indeks < 0 || indeks >= objekti.length) {
			System.err.println("Greska! Nekretnina sa indeksom " + indeks + " ne postoji!");
			return Optional.empty();
		}
		return Optional.of(objekti[indeks]);
	}

	@Override
	public String toString() {
		return Arrays.asList(objekti).stream().map(o -> o.toString() + "\n").reduce("", (a, b) -> a + b);
	}

	private static class Par<T1, T2> {

		Par(T1 prvi) {
			this.prvi = prvi;
		}

		Par(T1 prvi, T2 drugi) {
			this(prvi);
			this.drugi = drugi;
		}

		T1 prvi;
		T2 drugi;
	}

}
