package osoba;

import enumeracija.Pol;
import enumeracija.Uloga;

public abstract class Korisnik {

	
	//me, prezime, JMBG, pol, adresa, broj telefona, korisničko ime i lozinka.
	
	private int id;
	private String ime;
	private String prezime;
	private Pol pol;
	private String adresa;
	private String brTel;
	private String korIme;
	private String lozinka;
	private Uloga uloga;
	private String JMBG;
	
	public Korisnik() {
		super();
		this.id =0;
		this.ime = "";
		this.prezime = "";
		this.pol = pol.MUSKI;
		this.brTel = "";
		this.korIme = "";
		this.lozinka = "";
		this.uloga = uloga.MUSTERIJA;
		this.JMBG = "";
		
	}
	
	public Korisnik(int id,String ime, String prezime,String JMBG, Pol pol, String adresa, String brTel, String korIme, String lozinka,Uloga uloga) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.JMBG = JMBG;
		this.pol = pol;
		this.adresa = adresa;
		this.brTel = brTel;
		this.korIme = korIme;
		this.lozinka = lozinka;
		this.uloga = uloga;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Pol getPol() {
		return pol;
	}

	public void setPol(Pol pol) {
		this.pol = pol;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getBrTel() {
		return brTel;
	}

	public void setBrTel(String brTel) {
		this.brTel = brTel;
	}

	public String getKorIme() {
		return korIme;
	}

	public void setKorIme(String korIme) {
		this.korIme = korIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public Uloga getUloga() {
		return uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}

	public String getJMBG() {
		return JMBG;
	}

	public void setJMBG(String jMBG) {
		JMBG = jMBG;
	}
	
	
}
