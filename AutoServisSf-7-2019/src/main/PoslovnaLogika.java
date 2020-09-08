package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.print.attribute.standard.Severity;

import enumeracija.Marka_automobila;
import enumeracija.Pol;
import enumeracija.Specijalizacija;
import enumeracija.Status;
import enumeracija.Uloga;
import enumeracija.model_automobila;
import osoba.Administrator;
import osoba.Korisnik;
import osoba.Musterija;
import osoba.Serviser;
import servis.Automobili;
import servis.Servis;
import servis.ServisnaKnjizica;
import servis.ServisniDio;

public class PoslovnaLogika {

	private List<Administrator> listaAdministratora;
	private List<Musterija> listaMusterija;
	private List<Automobili> listaAutomobila;
	private List<Serviser> listaServisera;
	private List<ServisniDio> listaServisniDio;
	private List<Servis> listaServisa;
	private List<ServisnaKnjizica> listaKnjizica;
	
	public PoslovnaLogika() {
		this.listaAdministratora=new ArrayList<Administrator>();
		this.listaMusterija=new ArrayList<Musterija>();
		this.listaAutomobila=new ArrayList<Automobili>();
		this.listaServisera=new ArrayList<Serviser>();
		this.listaServisniDio = new ArrayList<ServisniDio>();
		this.listaServisa = new ArrayList<Servis>();
		this.listaKnjizica = new ArrayList<ServisnaKnjizica>();
	}
	public void dodajUListuAdministratora(Administrator admin) {
		this.listaAdministratora.add(admin);
		
	}
	public void dodajUListuMusterija(Musterija musterija) {
		this.listaMusterija.add(musterija);
	}

	public void dodajUListuAutomobila(Automobili automobili) {
		this.listaAutomobila.add(automobili);
	}
	
	public void dodajUListuServisera(Serviser serviser) {
		this.listaServisera.add(serviser);
		
	}
	
	public void dodajUListuServisniDio(ServisniDio sd) {
		this.listaServisniDio.add(sd);
		
	}
	public void dodajUListuServis(Servis servis) {
		this.listaServisa.add(servis);
		
	}
	
	public void dodajUListuKnji(ServisnaKnjizica servisnaKnj) {
		this.listaKnjizica.add(servisnaKnj);
		
	}
	
	public void upisiUFajlServisnuKnj(){
		try {
			FileWriter fw=new FileWriter("src/fajlovi/servisnaKnjizica.txt");
			
			BufferedWriter bw= new BufferedWriter(fw);
			
			Iterator<ServisnaKnjizica> iter=this.listaKnjizica.iterator();
			while(iter.hasNext()) {
				ServisnaKnjizica s=iter.next();
//				System.out.println(s.getAutomobil().getGodina_proizvodnje());
				bw.write(String.valueOf(s.getAutomobil().getId()));
				bw.newLine();
				
			}
			bw.close();
		}catch(IOException e) {
			System.out.println("Greska prilikom ucitavanja fajla");
			
		}
	}
	
	
	public void procitajIzFajlaServisnuKnj(){
		try {
			File file=new File("src/fajlovi/servisnaKnjizica.txt");
			BufferedReader reader=new BufferedReader(new FileReader(file)); 
			
			 
			String line;
			while((line=reader.readLine())!=null) {
				String[] dijelovi=line.split("\\|");
				Automobili automobil= getAutomobilById(Integer.parseInt(dijelovi[0]));
				
				ServisnaKnjizica sk = new ServisnaKnjizica(automobil);
				listaKnjizica.add(sk);
				
			}
			reader.close();
		}catch(IOException e) {
			System.out.println("Greska prilikom iscitavanja fajla"); 
		
		}
	}
	
	public void upisiUFajlServis() {
		try {
			FileWriter fw=new FileWriter("src/fajlovi/servis.txt");
			DateFormat konverter = new SimpleDateFormat("dd.MM.yyyy HH.mm");
			BufferedWriter bw= new BufferedWriter(fw);
			
			Iterator<Servis> iter=this.listaServisa.iterator();
			while(iter.hasNext()) {
				Servis s=iter.next();
				String termin = konverter.format(s.getTermin());
				bw.write(s.getId() + "|" + s.getAutomobil().getId() + "|" + s.getServiser().getKorIme()+ "|" + termin + "|" + s.getOpis() + "|" + s.getStatus());
				bw.newLine();
				
			}
			bw.close();
		}catch(IOException e) {
			System.out.println("Greska prilikom ucitavanja fajla");
			
		}
	}

	public void procitajIzFajlaServis() throws ParseException {
		try {
			File file=new File("src/fajlovi/servis.txt");
			BufferedReader reader=new BufferedReader(new FileReader(file)); 
			
			 
			String line;
			DateFormat konverter = new SimpleDateFormat("dd.MM.yyyy HH.mm");
			while((line=reader.readLine())!=null) {
				String[] dijelovi=line.split("\\|");
				int id = Integer.parseInt(dijelovi[0]);
				Automobili automobil= getAutomobilById(Integer.parseInt(dijelovi[1]));
				
				Serviser serviser= getServiserByKorIme(dijelovi[2]);

				String terminn = dijelovi[3];
				Date termin = konverter.parse(terminn);
				String opis=dijelovi[4];
				String statusStr=dijelovi[5];
				Status status = Status.valueOf(statusStr);
				Servis s = new Servis(id, automobil, serviser, termin, opis, status);
				listaServisa.add(s);
				
			}
			reader.close();
		}catch(IOException e) {
			System.out.println("Greska prilikom iscitavanja fajla"); 
		
		}
	}
	
	
	
	
	public void upisiUFajlServisniDio() {
		try {
			FileWriter fw=new FileWriter("src/fajlovi/servisniDio.txt");
			BufferedWriter bw= new BufferedWriter(fw);
			
			Iterator<ServisniDio> iter=this.listaServisniDio.iterator();
			while(iter.hasNext()) {
				ServisniDio s=iter.next();
				
				bw.write(s.getNaziv_dijela() + "|" + s.getCijena()+ "|" + s.getMarka() + "|" + s.getModel());
				bw.newLine();
				
			}
			bw.close();
		}catch(IOException e) {
			System.out.println("Greska prilikom ucitavanja fajla");
			
		}
	}
	
	public void procitajIzFajlaServisniDio() {
		try {
			File file=new File("src/fajlovi/servisniDio.txt");
			BufferedReader reader=new BufferedReader(new FileReader(file)); 
			
			 
			String line;
			while((line=reader.readLine())!=null) {
				String[] dijelovi=line.split("\\|");
				String naziv_djela=dijelovi[0];
				double cijena=Double.parseDouble(dijelovi[1]);
				String markaStr=dijelovi[2];
				Marka_automobila marka = Marka_automobila.valueOf(markaStr);
				String modelStr=dijelovi[3];
				model_automobila model = model_automobila.valueOf(modelStr);
				
				ServisniDio sd = new ServisniDio(naziv_djela, cijena, marka, model);
				listaServisniDio.add(sd);
			}
			reader.close();
		}catch(IOException e) {
			System.out.println("Greska prilikom iscitavanja fajla"); }	
	}
	
	public void upisiUFajlAdministratora() {
		try {
			FileWriter fw=new FileWriter("src/fajlovi/administratori.txt");
			BufferedWriter bw= new BufferedWriter(fw);
			
			Iterator<Administrator> iter=this.listaAdministratora.iterator();
			while(iter.hasNext()) {
				Administrator a=iter.next();
				
				bw.write(a.getId() + "|" + a.getIme() + "|" + a.getPrezime()+ "|" + a.getJMBG() + "|" + a.getPol() + "|" + a.getAdresa() + "|" + a.getBrTel() + "|" + a.getKorIme() + "|" + a.getLozinka() + "|" + a.getUloga() + "|" + a.getPlata());
				bw.newLine();
				
			}
			bw.close();
		}catch(IOException e) {
			System.out.println("Greska prilikom ucitavanja fajla");
			
		}
	}
	
	public void procitajIzFajlaAdministratori() {
		try {
			File file=new File("src/fajlovi/administratori.txt");
			BufferedReader reader=new BufferedReader(new FileReader(file)); 
			
			 
			String line;
			while((line=reader.readLine())!=null) {
				String[] dijelovi=line.split("\\|");
				int id=Integer.parseInt(dijelovi[0]);
				String ime=dijelovi[1];
				String prezime=dijelovi[2];
				String JMBG=dijelovi[3];
				String polStr=dijelovi[4];
				Pol pol = Pol.valueOf(polStr);
				String adresa=dijelovi[5];
				String brTel=dijelovi[6];
				String korIme=dijelovi[7];
				String lozinka=dijelovi[8];
				String ulogaStr=dijelovi[9];
				Uloga uloga = Uloga.valueOf(ulogaStr);
				double plata=Double.parseDouble(dijelovi[10]);
				Administrator a = new Administrator(id, ime, prezime, JMBG, pol, adresa, brTel, korIme, lozinka, uloga, plata);
				listaAdministratora.add(a);
			}
			reader.close();
		}catch(IOException e) {
			System.out.println("Greska prilikom iscitavanja fajla"); }	
	}
   


		
	
	public void upisiUFajlMusterija() {
		try {
			FileWriter fw=new FileWriter("src/fajlovi/musterije.txt");
			BufferedWriter bw= new BufferedWriter(fw);
			
			Iterator<Musterija> iter=this.listaMusterija.iterator();
			while(iter.hasNext()) {
				Musterija m=iter.next();
				
				bw.write(m.getId() + "|" + m.getIme() + "|" + m.getPrezime()+ "|" + m.getJMBG() + "|" + m.getPol() + "|" + m.getAdresa() + "|" + m.getBrTel() + "|" + m.getKorIme() + "|" + m.getLozinka() + "|" + m.getUloga() + "|" + m.getBrojSkupljenihBodova());
				bw.newLine();
				
			}
			bw.close();
		}catch(IOException e) {
			System.out.println("Greska prilikom ucitavanja fajla");
   }
	}

	public void procitajIzFajlaMusterije() {
		try {
			File file=new File("src/fajlovi/musterije.txt");
			BufferedReader reader=new BufferedReader(new FileReader(file));
			String line;
			while((line=reader.readLine())!=null) {
				String[] dijelovi=line.split("\\|");
				int id=Integer.parseInt(dijelovi[0]);
				String ime=dijelovi[1];
				String prezime=dijelovi[2];
				String JMBG=dijelovi[3];
				String polStr=dijelovi[4];
				Pol pol = Pol.valueOf(polStr);
				String adresa=dijelovi[5];
				String brTel=dijelovi[6];
				String korIme=dijelovi[7];
				String lozinka=dijelovi[8];
				String ulogaStr=dijelovi[9];
				Uloga uloga = Uloga.valueOf(ulogaStr);
				int brojSkupljenihBodova=Integer.parseInt(dijelovi[10]);
				Musterija m = new Musterija(id, ime, prezime, JMBG, pol, adresa, brTel, korIme, lozinka, uloga, brojSkupljenihBodova);
				listaMusterija.add(m);
			}
			reader.close();
		}catch(IOException e) {
			System.out.println("Greska prilikom iscitavanja fajla");
		}
		}
		
	

	public void upisiUFajlAutomobili() {
		try {
			FileWriter fw =new FileWriter("src/fajlovi/automobili.txt");
			BufferedWriter bw =new BufferedWriter(fw);
			
			Iterator<Automobili> iter=this.listaAutomobila.iterator();
			while(iter.hasNext()) {
				Automobili a=iter.next();
				
				bw.write(a.getId() + "|" + a.getVlasnik().getId() + "|" + a.getMarka() + "|" + a.getModel()+ "|" + a.getGodina_proizvodnje() + "|" + a.getZapremina_motora() + "|" + a.getSnaga_motora() + "|" + a.getVrsta_goriva());
				bw.newLine();
				
				
		}
			bw.close();
			
		}catch(IOException e) {
			System.out.println("Greska prilikom ucitavanja fajla");
		}
			
			
		}
	



	public void procitajIzFajlaAutomobili() {
		try {
			File file=new File("src/fajlovi/automobili.txt");
			BufferedReader reader=new BufferedReader(new FileReader(file));
			String line;
			while((line=reader.readLine()) !=null) {
			String[] dijelovi=line.split("\\|");
			int id=Integer.parseInt(dijelovi[0]);
			Musterija vlasnik=getMusterijaById(Integer.parseInt(dijelovi[1]));
			String marka=dijelovi[2];
			String model=dijelovi[3];
			int godina_proizvodnje=Integer.parseInt(dijelovi[4]); 
			double zapremina_motora=Double.parseDouble(dijelovi[5]);
			int snaga_motora=Integer.parseInt(dijelovi[6]);
			String vrsta_goriva=dijelovi[7];
			System.out.println("Vlasnik auta je:" + vlasnik.getIme() + "Marka automobila je:" + marka);
			
			}
			
			reader.close();
			
		} catch (Exception e) {
			System.out.println("Greska prilikom iscitavanja fajla");
			
		}
	}

	public Musterija getMusterijaById(int id)  {
		for(Musterija m:listaMusterija) {
			if(id==m.getId()) {
				return m;
			
			}
	
		}
		return null;
	}
	public Automobili nadjiAutomobil(String m) {
		for(Automobili a:listaAutomobila) {
			if(m.equals(a.getMarka())) {
				return a;
				
			}
		}
		return null;
	}
	
	public Automobili getAutomobilById(int id)  {
		for(Automobili a:listaAutomobila) {
			if(id == a.getId()) {
				return a;
			
			}
	
		}
		return null;
	}
	
	public Musterija getMusterijaByKorIme(String korIme)  {
		for(Musterija m:listaMusterija) {
			if(korIme==m.getKorIme()) {
				return m;
			
			}
	
		}
		return null;
	}

	public Serviser getServiserByKorIme(String korIme)  {
		for(Serviser s:listaServisera) {
			if(korIme==s.getKorIme()) {
				return s;
			
			}
	
		}
		return null;
	}
	
	public Administrator nadjiAdministratoraPoKorIme(String korIme) {
		for(Administrator a: listaAdministratora) {
			if(a.getKorIme().equals(korIme)) {
				return a;
			}
			
		}
		return null;
	}
	
	public Musterija nadjiMusterijuPoKorIme(String korIme) {
		for(Musterija m: listaMusterija) {
			if(m.getKorIme().equals(korIme)) {
				return m;
			}
		}
		return null;
	}
	
	public Serviser nadjiServiseraPoKorIme(String korime) {
		for(Serviser s: listaServisera) {
			if(s.getKorIme().equals(korime)) {
				return s;
			}
		}
		return null;
	}
	public Servis nadjiServisPoId(int id) {
		for(Servis s: listaServisa) {
			if(s.getId()==id) {
				return s;
				
			}
		}
		return null;
	}
	
	public ServisniDio nadjiServisniDioPoId(int id) {
		for(ServisniDio s: listaServisniDio) {
			if(s.getId()==id) {
				return s;
				
			}
		}
		return null;
	}
	
	public Automobili nadjiAutomobilPoId(int id) {
		for(Automobili a: listaAutomobila) {
			if(id == a.getId()) {
				return a;
			}
		}
		return null;
	}
	
	

	public void upisiUFajlServisera() {
		try {
			FileWriter fw =new FileWriter("src/fajlovi/serviser.txt");
			BufferedWriter bw =new BufferedWriter(fw);
			
			Iterator<Serviser> iter=this.listaServisera.iterator();
			while(iter.hasNext()) {
				Serviser s=iter.next();
				
				bw.write(s.getId() + "|" + s.getIme() + "|" + s.getPrezime() + "|" + s.getJMBG() + "|" + s.getPol() + "|" + s.getAdresa() + "|" + s.getBrTel() + "|" + s.getKorIme() + "|" + s.getLozinka() + "|" + s.getUloga() + "|" + s.getPlata() +"|" + s.getSpecijalizacija());
				bw.newLine();
				
				
			}
			bw.close();
			} catch (Exception e) {
				System.out.println("Greska prilikom ucitavanja fajla");
			}
		
		
		}
		
	
	
	
	
	public void procitajIzFajlaServiser() {
		try {
			File file=new File("src/fajlovi/serviser.txt");
			BufferedReader reader=new BufferedReader(new FileReader(file));
			String line;
			while((line=reader.readLine()) !=null) {
				String[] dijelovi=line.split("\\|");
				int id=Integer.parseInt(dijelovi[0]);
				String ime=dijelovi[1];
				String prezime=dijelovi[2];
				String JMBG=dijelovi[3];
				String polStr=dijelovi[4];
				Pol pol = Pol.valueOf(polStr);
				String adresa=dijelovi[5];
				String brTel=dijelovi[6];
				String korIme=dijelovi[7];
				String lozinka=dijelovi[8];
				String ulogaStr=dijelovi[9];
				Uloga uloga = Uloga.valueOf(ulogaStr);
				double plata=Double.parseDouble(dijelovi[10]);
				String specijalizacijaStr=dijelovi[11];
				Specijalizacija specijalizacija = Specijalizacija.valueOf(specijalizacijaStr);
				Serviser s = new Serviser(id, ime, prezime, JMBG, pol, adresa, brTel, korIme, lozinka, uloga, plata, specijalizacija);
				listaServisera.add(s);
			}
			reader.close();
			
			
		} catch (Exception e) {
			System.out.println("Greska prilikom iscitavanja fajla");
			
	
		}
	}
	
	public Administrator izmjeniAdministratora(Administrator a) {
		for(Administrator ad:listaAdministratora) {
			if(a.getId()==ad.getId()) {
				ad.setIme(a.getIme());
				ad.setPrezime(a.getPrezime());
			}
			else {
				System.out.println("Ne postoji taj Administrator");
			}
		}
		return a;
	}
	
	public void obrisiAdministratora(Administrator a) {
		for(Administrator ad:listaAdministratora) {
			if(ad.getId()==a.getId()) {
				this.listaAdministratora.remove(ad);
				System.out.println("obrisan");
			}
		}
	}
	public List<Administrator> getListaAdministratora() {
		return listaAdministratora;
	}
	public void setListaAdministratora(List<Administrator> listaAdministratora) {
		this.listaAdministratora = listaAdministratora;
	}
	public List<Automobili> getListaAutomobila() {
		return listaAutomobila;
	}
	public List<Musterija> getListaMusterija() {
		return listaMusterija;
	}
	public List<ServisniDio> getListaServisniDio() {
		return listaServisniDio;
	}

	public List<Servis> getListaServisa() {
		return listaServisa;
	}
	
	public List<ServisnaKnjizica> getListaServisnahKnj() {
		return listaKnjizica;
	}
	public List<Serviser> getListaServisera() {
		return listaServisera;
	}

	public Korisnik login(String korisnickoIme, String sifra) {
		for(Korisnik a : listaAdministratora) {
			if(a.getKorIme().equalsIgnoreCase(korisnickoIme) && 
				a.getLozinka().equals(sifra)){
				return a;
			}
		}
		for(Korisnik s: listaServisera) {
			if(s.getKorIme().equalsIgnoreCase(korisnickoIme) &&
				s.getLozinka().equals(sifra)){
					return s;
				}
		}
		for(Korisnik m : listaMusterija) {
			if(m.getKorIme().equalsIgnoreCase(korisnickoIme) &&
					m.getLozinka().equals(sifra)) {
				return m;
			}
		}
		return null;
	}
}


	

	
	
	
		
		

	