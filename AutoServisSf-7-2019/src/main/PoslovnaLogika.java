package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import osoba.Administrator;
import osoba.Musterija;

public class PoslovnaLogika {

	private List<Administrator> listaAdministratora;
	private List<Musterija> listaMusterija;
	
	public PoslovnaLogika() {
		this.listaAdministratora=new ArrayList<Administrator>();
		this.listaMusterija=new ArrayList<Musterija>();
	}
	public void dodajUListuAdministratora(Administrator admin) {
		this.listaAdministratora.add(admin);
		
	}
	public void dodajUListuMusterija(Musterija musterija) {
		this.listaMusterija.add(musterija);
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
				String pol=dijelovi[4];
				String adresa=dijelovi[5];
				String brTel=dijelovi[6];
				String korIme=dijelovi[7];
				String lozinka=dijelovi[8];
				String uloga=dijelovi[9];
				double plata=Double.parseDouble(dijelovi[10]);
				System.out.println("ime Automehanicara: " + ime + "prezime: " + prezime); 
				
				
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
				String pol=dijelovi[4];
				String adresa=dijelovi[5];
				String brTel=dijelovi[6];
				String korIme=dijelovi[7];
				String lozinka=dijelovi[8];
				String uloga=dijelovi[9];
				int brojSkupljenihBodova=Integer.parseInt(dijelovi[10]);
				System.out.println("Ime musterije:" + ime + "Prezime:" + prezime);
				
				
				
			}
			reader.close();
		}catch(IOException e) {
			System.out.println("Greska prilikom iscitavanja fajla");
		}
		}
		
	}


	
	
	
		
		
