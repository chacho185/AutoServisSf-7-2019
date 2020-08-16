package servis;

import enumeracija.Marka_automobila;
import enumeracija.model_automobila;

public class ServisniDio {

	private int id;
	private String naziv_dijela;
	private double cijena;
	private Marka_automobila marka;
	private model_automobila model;
	
	public ServisniDio() {
		super();
		this.id = 0;
		this.naziv_dijela= "";
		this.cijena= 0;
		this.marka=Marka_automobila.AUDI;
		this.model=model_automobila.A5;
		
	}

	public ServisniDio(int id,String naziv_dijela, double cijena, Marka_automobila marka, model_automobila model) {
		super();
		this.id = id;
		this.naziv_dijela = naziv_dijela;
		this.cijena = cijena;
		this.marka = marka;
		this.model = model;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv_dijela() {
		return naziv_dijela;
	}

	public void setNaziv_dijela(String naziv_dijela) {
		this.naziv_dijela = naziv_dijela;
	}

	public double getCijena() {
		return cijena;
	}

	public void setCijena(double cijena) {
		this.cijena = cijena;
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
	
}
