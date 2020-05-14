package main;

import enumeracija.Pol;
import enumeracija.Uloga;
import osoba.Administrator;
import osoba.Musterija;

public class AutoServisMain {

	public static void main(String[] args) {
		
		PoslovnaLogika pl=new PoslovnaLogika();
		pl.procitajIzFajlaAdministratori();
		pl.procitajIzFajlaMusterije();
		
		Administrator admin1=new Administrator(1, "Milos", "Sikimic", "1910999153992", Pol.MUSKI, "ljubinje123", "5555" ,"chacho185", "12345", Uloga.ADMINISTRATOR ,150000.00);
		Administrator admin2=new Administrator(2,"Marko","Markovic","152442151221",Pol.MUSKI,"trebinje123", "4444","marko123","34321",Uloga.ADMINISTRATOR,130000.00);
		Musterija musterija1=new Musterija(1,"Ranko","Bokic","1898412974",Pol.MUSKI,"ljubinje53","8888","ranko68","53212",Uloga.MUSTERIJA,55);
		pl.dodajUListuMusterija(musterija1);
		pl.dodajUListuAdministratora(admin1);
		pl.dodajUListuAdministratora(admin2);
		pl.upisiUFajlMusterija();
		pl.upisiUFajlAdministratora();
	}		

}
