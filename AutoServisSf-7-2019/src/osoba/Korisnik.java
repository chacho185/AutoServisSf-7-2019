package osoba;

import enumeracija.Pol;

public abstract class Korisnik {

	
	//me, prezime, JMBG, pol, adresa, broj telefona, korisničko ime i lozinka.
	private String ime;
	private String prezime;
	private Pol pol;
	private String adresa;
	private String brTel;
	private String korIme;
	private String lozinka;
	
	public Korisnik() {
		super();
		this.ime = "";
		this.prezime = "";
		this.pol = pol.MUSKI;
		this.brTel = "";
		this.korIme = "";
		this.lozinka = "";
		
	}
	
	public Korisnik(String ime, String prezime, Pol pol, String adresa, String brTel, String korIme, String lozinka) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		this.adresa = adresa;
		this.brTel = brTel;
		this.korIme = korIme;
		this.lozinka = lozinka;
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
	
	
}
