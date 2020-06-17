package servis;

import java.util.ArrayList;
import java.util.List;

public class ServisnaKnjizica {

	private Automobili automobil;
	private List<Servis> lista_servisa;
	
	public ServisnaKnjizica() {
		super();
		this.automobil=new Automobili();
		this.lista_servisa= new ArrayList<Servis>();
		
		
	}

	public ServisnaKnjizica(Automobili a) {
		super();
		this.automobil= a;
	}
	
	public ServisnaKnjizica(Automobili automobil, List<Servis> lista_servisa) {
		super();
		this.automobil = automobil;
		this.lista_servisa = lista_servisa;
	}

	public Automobili getAutomobil() {
		return automobil;
	}

	public void setAutomobil(Automobili automobil) {
		this.automobil = automobil;
	}

	public List<Servis> getLista_servisa() {
		return lista_servisa;
	}

	public void setLista_servisa(List<Servis> lista_servisa) {
		this.lista_servisa = lista_servisa;
	}
	
	
}
