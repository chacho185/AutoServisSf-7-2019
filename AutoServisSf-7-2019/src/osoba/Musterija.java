package osoba;

import enumeracija.Pol;
import enumeracija.Uloga;

public class Musterija extends Korisnik {
	
	private int BrojSkupljenihBodova;
	
	public Musterija() {
		super();
		this.BrojSkupljenihBodova=0;
		
	}

	public Musterija(int id,String ime, String prezime,String JMBG, Pol pol, String adresa, String brTel, String korIme, String lozinka,Uloga uloga,int brojSkupljenihBodova) {
		super(id,ime,prezime,JMBG,pol,adresa,brTel,korIme,lozinka,uloga);
		BrojSkupljenihBodova = brojSkupljenihBodova;
	}

	public int getBrojSkupljenihBodova() {
		return BrojSkupljenihBodova;
	}

	public void setBrojSkupljenihBodova(int brojSkupljenihBodova) {
		BrojSkupljenihBodova = brojSkupljenihBodova;
	}

	
}
