package main;

import java.text.ParseException;
import java.util.Date;

import enumeracija.Marka_automobila;
import enumeracija.Pol;
import enumeracija.Specijalizacija;
import enumeracija.Status;
import enumeracija.Uloga;
import enumeracija.Vrsta_goriva;
import enumeracija.model_automobila;
import guiZaIzmjenuIDodavanje.AdministratorDodavanje;
import guiZaIzmjenuIDodavanje.AutomobilDodavanje;
import guiZaIzmjenuIDodavanje.MusterijaDodavanje;
import guiZaIzmjenuIDodavanje.ServisDodavanje;
import guiZaIzmjenuIDodavanje.ServiserDodavanje;
import guiZaIzmjenuIDodavanje.ServisnaKnjizicaDodavanje;
import guiZaIzmjenuIDodavanje.ServisniDioDodavanje;
import osoba.Administrator;
import osoba.Musterija;
import osoba.Serviser;
import servis.Automobili;
import servis.Servis;
import servis.ServisnaKnjizica;
import servis.ServisniDio;

public class AutoServisMain {

	public static void main(String[] args) throws ParseException {
		
		PoslovnaLogika pl=new PoslovnaLogika();
		
//		pl.procitajIzFajlaAdministratori();
	//	pl.procitajIzFajlaMusterije();
//		pl.procitajIzFajlaServiser();
//		
//		
		
//		Administrator admin1=new Administrator(1, "Milos", "Sikimic", "1910999153992", Pol.MUSKI, "ljubinje123", "5555" ,"chacho185", "12345", Uloga.ADMINISTRATOR ,150000.00);
//		Administrator admin2=new Administrator(2,"Marko","Markovic","152442151221",Pol.MUSKI,"trebinje123", "4444","marko123","34321",Uloga.ADMINISTRATOR,130000.00);
		Musterija musterija1=new Musterija(1,"Ranko","Bokic","1898412974",Pol.MUSKI,"ljubinje53","8888","ranko68","53212",Uloga.MUSTERIJA,55);
//		Musterija musterija2=new Musterija(2,"m","m","1898412974",Pol.MUSKI,"ljubinje52","88878","aaa44","53212",Uloga.MUSTERIJA,55);

		pl.dodajUListuMusterija(musterija1);
	//	Automobili automobili1=new Automobili(1,musterija1, Marka_automobila.AUDI, model_automobila.A6, 2005,3.0,140, Vrsta_goriva.DIZEL);
		Automobili a= new Automobili(2, musterija1, Marka_automobila.AUDI, model_automobila.A5, 2010, 2.5, 160, Vrsta_goriva.BENZIN);

		pl.dodajUListuAutomobila(a);
//		pl.dodajUListuAutomobila(automobili1);
		ServisnaKnjizica sk = new ServisnaKnjizica(a);
//		ServisnaKnjizica s = new ServisnaKnjizica(automobili1);
		pl.dodajUListuKnji(sk);
//		pl.dodajUListuKnji(s);
		pl.upisiUFajlServisnuKnj();
		pl.procitajIzFajlaServisnuKnj();
//		Serviser serviser=new Serviser(1, "Mitar", "Novokmet", "821731731", Pol.MUSKI, "ljubinje999", "0651111", "mmitar", "12345", Uloga.SERVISER , 140000.00, Specijalizacija.VULKANIZER);
//
//		pl.dodajUListuServisera(serviser);
//		ServisniDio sd = new ServisniDio("nek dio", 55, Marka_automobila.AUDI, model_automobila.A6);
//		ServisniDio sd2 = new ServisniDio("MJENJAC", 500, Marka_automobila.PEUGEOT, model_automobila.SW307);
		//pl.dodajUListuServisniDio(sd);
//		pl.dodajUListuServisniDio(sd2);
	//	pl.upisiUFajlServisniDio();
//		pl.procitajIzFajlaServisniDio();
//		Servis s = new Servis(a, serviser, new Date(), "oo", pl.getListaServisniDio(), Status.SLOBODAN);
//		
//		pl.dodajUListuServis(s);
//		pl.upisiUFajlServis();
//		pl.procitajIzFajlaServis();
//		pl.dodajUListuMusterija(musterija1);
//		pl.dodajUListuAdministratora(admin1);
//		pl.dodajUListuAdministratora(admin2);
//		pl.dodajUListuServisera(serviser);
//
//		pl.upisiUFajlAutomobili();
//		pl.upisiUFajlMusterija();
//		pl.upisiUFajlAdministratora();
//		pl.upisiUFajlServisera();
//		pl.procitajIzFajlaAutomobili();
//		pl.procitajIzFajlaServiser();
//		
//		AdministratorDodavanje ad = new AdministratorDodavanje(admin1);
//		ad.setVisible(true);
		
//		MusterijaDodavanje m = new MusterijaDodavanje(musterija1);
//		m.setVisible(true);
		
//		ServiserDodavanje s = new ServiserDodavanje(serviser);
//		AutomobilDodavanje s = new AutomobilDodavanje(pl, a);
//		s.setVisible(true);
		
//		ServisDodavanje sd = new ServisDodavanje(pl, s);
	//	sd.setVisible(true);
		
//		ServisniDioDodavanje sds = new ServisniDioDodavanje(pl, sd);
	//	sds.setVisible(true);
		
		ServisnaKnjizicaDodavanje skd = new ServisnaKnjizicaDodavanje(pl, sk);
		skd.setVisible(true);
	}
	

}
