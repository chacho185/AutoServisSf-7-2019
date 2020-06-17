package servis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import enumeracija.Status;
import osoba.Serviser;

public class Servis {

	private Automobili automobil;
	private Serviser serviser;
	private Date termin;
	private String opis;
	private List<ServisniDio> lista_dijelova;
	private Status status;
	
	public Servis() {
		super();
		this.automobil=new Automobili();
		this.serviser=new Serviser();
		this.termin=new Date();
		this.opis="";
		this.lista_dijelova=new ArrayList<ServisniDio>();
		this.status=Status.ZAKAZAN;
		
	}

	public Servis(Automobili automobil, Serviser serviser, Date termin, String opis, List<ServisniDio> lista_dijelova,
			Status status) {
		super();
		this.automobil = automobil;
		this.serviser = serviser;
		this.termin = termin;
		this.opis = opis;
		this.lista_dijelova = lista_dijelova;
		this.status = status;
	}
	public Servis(Automobili automobil, Serviser serviser, Date termin, String opis,Status status)
			 {
		super();
		this.automobil = automobil;
		this.serviser = serviser;
		this.termin = termin;
		this.opis = opis;
		this.status = status;
	}
	public Automobili getAutomobil() {
		return automobil;
	}

	public void setAutomobil(Automobili automobil) {
		this.automobil = automobil;
	}

	public Serviser getServiser() {
		return serviser;
	}

	public void setServiser(Serviser serviser) {
		this.serviser = serviser;
	}

	public Date getTermin() {
		return termin;
	}

	public void setTermin(Date termin) {
		this.termin = termin;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public List<ServisniDio> getLista_dijelova() {
		return lista_dijelova;
	}

	public void setLista_dijelova(List<ServisniDio> lista_dijelova) {
		this.lista_dijelova = lista_dijelova;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
}
