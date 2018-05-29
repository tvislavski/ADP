import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Optional;

public class Facebook {
	
	private static final String PROFILE_INFO_SEPARATOR = "\\|";
	private static final String MATRICA = "matrica.txt";
	private static final String PROFILI = "profili.txt";

	private FacebookProfil niz[];

	Facebook(String fajlSaProfilima, String fajlSaMatricom) {
		if (ucitajProfile(fajlSaProfilima)) {
			Optional<boolean[][]> matrica = ucitajIzFajla(fajlSaMatricom, this::ucitajMatricu);
			if (matrica.isPresent()) {
				poveziPrijatelje(matrica.get());
			}
		}
	}
	
	private boolean ucitajProfile(String fajl) {
		Optional<FacebookProfil[]> profili = ucitajIzFajla(fajl, this::ucitajProfile);
		if (profili.isPresent()) {
			niz = profili.get();
			return true;
		}
		return false;
	}

	private <T> Optional<T> ucitajIzFajla(String fajl,
			FunkcijaKojaCitaBufferedReader<T> funkcijaKojaCitaBufferedReader) {
		Optional<T> elementi = Optional.empty();
		try (BufferedReader bf = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(fajl)))) {
			elementi = Optional.of(funkcijaKojaCitaBufferedReader.citaj(bf));
		} catch (NullPointerException e) {
			System.err.println("Nije pronadjen fajl " + fajl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return elementi;
	}

	private FacebookProfil[] ucitajProfile(BufferedReader fajl) throws IOException {
		int brojProfila = Integer.parseInt(fajl.readLine().trim());
		FacebookProfil[] profili = new FacebookProfil[brojProfila];
		for (int i = 0; i < brojProfila; i++) {
			profili[i] = FacebookProfil.kreirajOdStringa(fajl.readLine(), PROFILE_INFO_SEPARATOR);
		}
		return profili;
	}

	private boolean[][] ucitajMatricu(BufferedReader fajl) throws IOException {
		boolean[][] matrica = new boolean[niz.length][niz.length];
		for (int i = 0; i < matrica.length; i++) {
			String[] tokeni = fajl.readLine().split(PROFILE_INFO_SEPARATOR);
			for (int j = 0; j < matrica.length; j++) {
				matrica[i][j] = tokeni[j].trim().equals("1");
			}
		}
		return matrica;
	}

	private void poveziPrijatelje(boolean[][] matrica) {
		for (int i = 0; i < matrica.length; i++) {
			for (int j = i + 1; j < matrica.length; j++) {
				if (matrica[i][j]) {
					poveziPrijateljeNaMestu(i, j);
					poveziPrijateljeNaMestu(j, i);
				}
			}
		}
	}

	private void poveziPrijateljeNaMestu(int i, int j) {
		try {
			niz[i].dodajPrijatelja(niz[j]);
		} catch (NekonzistentniPodaci e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public String toString() {
		return Arrays.asList(niz).stream().map(p -> p.toString()).reduce("", (a, b) -> a + b);
	}

	@FunctionalInterface
	private static interface FunkcijaKojaCitaBufferedReader<R> {
		R citaj(BufferedReader bf) throws IOException;
	}

	public static void main(String[] sdadsa) {
		Facebook fb = new Facebook(PROFILI, MATRICA);
		System.out.println(fb);
	}
}
