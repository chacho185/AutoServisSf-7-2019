package main;

import enumeracija.Marka_automobila;
import enumeracija.Pol;
import enumeracija.Specijalizacija;
import enumeracija.Uloga;
import enumeracija.Vrsta_goriva;
import enumeracija.model_automobila;
import osoba.Administrator;
import osoba.Musterija;
import osoba.Serviser;
import servis.Automobili;

public class AutoServisMain {

	public static void main(String[] args) {
		
		PoslovnaLogika pl=new PoslovnaLogika();
		pl.procitajIzFajlaAdministratori();
		pl.procitajIzFajlaMusterije();
		//pl.procitajIzFajlaServiser();
		
		
		Administrator admin1=new Administrator(1, "Milos", "Sikimic", "1910999153992", Pol.MUSKI, "ljubinje123", "5555" ,"chacho185", "12345", Uloga.ADMINISTRATOR ,150000.00);
		Administrator admin2=new Administrator(2,"Marko","Markovic","152442151221",Pol.MUSKI,"trebinje123", "4444","marko123","34321",Uloga.ADMINISTRATOR,130000.00);
		Musterija musterija1=new Musterija(1,"Ranko","Bokic","1898412974",Pol.MUSKI,"ljubinje53","8888","ranko68","53212",Uloga.MUSTERIJA,55);
		Automobili automobili1=new Automobili(1,musterija1, Marka_automobila.AUDI, model_automobila.A6, 2005,3.0,140, Vrsta_goriva.DIZEL);
		Serviser serviser=new Serviser(1, "Mitar", "Novokmet", "821731731", Pol.MUSKI, "ljubinje999", "0651111", "mmitar", "12345", Uloga.SERVISER , 140000.00, Specijalizacija.VULKANIZER);
		pl.dodajUListuAutomobila(automobili1);
		pl.dodajUListuMusterija(musterija1);
		pl.dodajUListuAdministratora(admin1);
		pl.dodajUListuAdministratora(admin2);
		pl.dodajUListuServisera(serviser);
		pl.upisiUFajlAutomobili();
		pl.upisiUFajlMusterija();
		pl.upisiUFajlAdministratora();
		pl.upisiUFajlServisera();
		pl.procitajIzFajlaAutomobili();
		pl.procitajIzFajlaServiser();
	}		

}
