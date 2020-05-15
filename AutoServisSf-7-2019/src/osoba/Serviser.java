package osoba;

import enumeracija.Pol;
import enumeracija.Specijalizacija;
import enumeracija.Uloga;

public class Serviser extends Korisnik {

	private double plata;
	private Specijalizacija specijalizacija;
	
	public Serviser() {
		super();
		this.plata=0;
		this.specijalizacija=specijalizacija.AUTOMEHANICAR;
		
		
	}

	public Serviser(int id,String ime, String prezime,String JMBG, Pol pol, String adresa, String brTel, String korIme, String lozinka,Uloga uloga,double plata, Specijalizacija specijalizacija) {
		super(id,ime,prezime,JMBG,pol,adresa,brTel,korIme,lozinka,uloga);
		this.plata = plata;
		this.specijalizacija = specijalizacija;
	}

	public double getPlata() {
		return plata;
	}

	public void setPlata(double plata) {
		this.plata = plata;
	}

	public Specijalizacija getSpecijalizacija() {
		return specijalizacija;
	}

	public void setSpecijalizacija(Specijalizacija specijalizacija) {
		this.specijalizacija = specijalizacija;
	}
	
}
