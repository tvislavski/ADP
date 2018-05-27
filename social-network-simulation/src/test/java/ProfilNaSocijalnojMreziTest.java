import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProfilNaSocijalnojMreziTest {

	/*
	 * Testiramo public metode ove klase. Svaki metod bismo trebali testirati u svim
	 * mogucim scenarijima, ne samo "happy" scenario
	 */
	private ProfilNaSocijalnojMrezi profil;

	/*
	 * @Before anotacija kaze JUnit framework-u da pozove ovaj metod pre poziva
	 * svakog metoda oznacenog sa @Test anotacijom. Pre svakog test metoda
	 * resetujemo objekat koji testiramo
	 */
	@Before
	public void setUp() {
		profil = new ProfilNaSocijalnojMrezi(1) {
		};
	}

	/*
	 * @Test anotacija govori JUnit framework-u da se u ovom metodu testira neki
	 * objekat. Prilikom build procesa, svaki metod oznacen ovom anotacijom ce biti
	 * pokrenut i, ukoliko rezultat testa ne bude onaj koji navedemo kao ocekivani,
	 * build projekta nece proci.
	 */
	@Test
	public void testDodajPrijatelja() throws Exception {
		/*
		 * Posto je dodajPrijatelja void metod, nemamo ocekivanih rezultata za njegovo
		 * izvrsavanje. Ukoliko dodje do izuzetka u toku izvrsavanja metoda, test nece
		 * proci
		 */
		profil.dodajPrijatelja(new ProfilNaSocijalnojMrezi(1) {
		});
	}

	/*
	 * Ovaj test kaze da ocekuje da se generise NekonzistentniPodaci izuzetak.
	 * Ukoliko do generisanja izuzetka ne dodje, test nece proci.
	 */
	@Test(expected = NekonzistentniPodaci.class)
	public void testDodajPrijateljaSaNekonzistentnimPodacima() throws Exception {
		profil.dodajPrijatelja(new ProfilNaSocijalnojMrezi(1) {
		});
		/*
		 * Objekat koji testiramo je kreiran u setUp() metodi sa jednim projateljem.
		 * Kada pokusamo da dodamo jos jednog, ocekujemo NekonzistentniPodaci izuzetak
		 */
		profil.dodajPrijatelja(new ProfilNaSocijalnojMrezi(1) {
		});
	}

	@Test
	public void testSusedKadaJesuSusedi() throws Exception {
		ProfilNaSocijalnojMrezi prijatelj = new ProfilNaSocijalnojMrezi(1) {
		};
		profil.dodajPrijatelja(prijatelj);
		Assert.assertTrue(profil.sused(prijatelj));
	}

	@Test
	public void testSusedKadaNisuSusedi() throws Exception {
		/*
		 * Klasa Assert sadrzi mnoge staticke metode koji nam omogucuju da testiramo
		 * ocekivano ponasanje metoda. Konkretno, assertFalse ocekuje da prosedjedni
		 * izraz bude netacan i tada test prolazi. Ukoliko izraz nije netacan, test nece
		 * proci
		 */
		Assert.assertFalse(profil.sused(new ProfilNaSocijalnojMrezi(1) {
		}));
	}
}
