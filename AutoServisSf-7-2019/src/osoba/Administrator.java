package osoba;

import enumeracija.Pol;
import enumeracija.Uloga;

public class Administrator extends Korisnik {


	private double plata;
	
	public Administrator() {
		super();
		this.plata=0;
	}
	public Administrator(int id,String ime, String prezime,String JMBG, Pol pol, String adresa, String brTel, String korIme, String lozinka,Uloga uloga,double plata) {
		super(id,ime,prezime,JMBG,pol,adresa,brTel,korIme,lozinka,uloga);
		this.plata = plata;
	}
	public double getPlata() {
		return plata;
	}
	public void setPlata(double plata) {
		this.plata = plata;
	}
	
	
}
