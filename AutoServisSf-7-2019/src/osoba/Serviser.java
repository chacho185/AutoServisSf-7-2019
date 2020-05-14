package osoba;

import enumeracija.Specijalizacija;

public class Serviser extends Korisnik {

	private double plata;
	private Specijalizacija specijalizacija;
	
	public Serviser() {
		super();
		this.plata=0;
		this.specijalizacija=specijalizacija.AUTOMEHANICAR;
		
		
	}

	public Serviser(double plata, Specijalizacija specijalizacija) {
		super();
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
