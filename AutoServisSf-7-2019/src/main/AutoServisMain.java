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
import gui.glavni.GlavniProzorZaAdministratora;
import gui.glavni.LoginProzor;
import gui.prozoriZaPrikaz.AdministratorProzor;
import gui.prozoriZaPrikaz.AutomobilProzor;
import guiZaIzmjenuIDodavanje.AdministratorDodavanje;
import guiZaIzmjenuIDodavanje.AutomobilDodavanje;
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
		
		pl.procitajIzFajlaAdministratori();
		pl.procitajIzFajlaMusterije();
		pl.procitajIzFajlaServiser();
		pl.procitajIzFajlaServis();
		pl.procitajIzFajlaServisniDio();
		pl.procitajIzFajlaServisnuKnj();
		
		LoginProzor lp = new LoginProzor(pl);
		lp.setVisible(true);
	}
	
}
