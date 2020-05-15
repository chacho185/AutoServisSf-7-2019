package servis;

import enumeracija.Marka_automobila;
import enumeracija.Vrsta_goriva;
import enumeracija.model_automobila;
import osoba.Musterija;

public class Automobili {
	private int id;
	private Musterija vlasnik;
	private Marka_automobila marka;
	private model_automobila model;
	private int godina_proizvodnje;
	private double zapremina_motora;
	private int snaga_motora;
	private Vrsta_goriva vrsta_goriva;
	
	public Automobili() {
		super();
		this.id=0;
		this.vlasnik=new Musterija();
		this.marka=Marka_automobila.AUDI;
		this.model=model_automobila.A5;
		this.godina_proizvodnje=0;
		this.zapremina_motora=0;
		this.snaga_motora=0;
		this.vrsta_goriva=Vrsta_goriva.DIZEL;
	}

	public Automobili(int id,Musterija vlasnik, Marka_automobila marka, model_automobila model, int godina_proizvodnje,
			double zapremina_motora, int snaga_motora, Vrsta_goriva vrsta_goriva) {
		super();
		this.id=id;
		this.vlasnik = vlasnik;
		this.marka = marka;
		this.model = model;
		this.godina_proizvodnje = godina_proizvodnje;
		this.zapremina_motora = zapremina_motora;
		this.snaga_motora = snaga_motora;
		this.vrsta_goriva = vrsta_goriva;
	}

	
	public Musterija getVlasnik() {
		return vlasnik;
	}

	public void setVlasnik(Musterija vlasnik) {
		this.vlasnik = vlasnik;
	}

	public Marka_automobila getMarka() {
		return marka;
	}

	public void setMarka(Marka_automobila marka) {
		this.marka = marka;
	}

	public model_automobila getModel() {
		return model;
	}

	public void setModel(model_automobila model) {
		this.model = model;
	}

	public int getGodina_proizvodnje() {
		return godina_proizvodnje;
	}

	public void setGodina_proizvodnje(int godina_proizvodnje) {
		this.godina_proizvodnje = godina_proizvodnje;
	}

	public double getZapremina_motora() {
		return zapremina_motora;
	}

	public void setZapremina_motora(double zapremina_motora) {
		this.zapremina_motora = zapremina_motora;
	}

	public int getSnaga_motora() {
		return snaga_motora;
	}

	public void setSnaga_motora(int snaga_motora) {
		this.snaga_motora = snaga_motora;
	}

	public Vrsta_goriva getVrsta_goriva() {
		return vrsta_goriva;
	}

	public void setVrsta_goriva(Vrsta_goriva vrsta_goriva) {
		this.vrsta_goriva = vrsta_goriva;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	 
}
