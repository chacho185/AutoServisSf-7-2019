package gui.glavni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import gui.prozoriZaPrikaz.AutomobilZaMusteriju;
import guiZaIzmjenuIDodavanje.ServisZaServisera;
import main.PoslovnaLogika;
import osoba.Korisnik;

public class GlavniProzorZaServisera extends JFrame{

	private JMenuBar glavniMeni;
	private JMenu korisniciMenu;
	private JMenuItem serviseriItem;
	private JMenuItem administratoriItem;
	private JMenuItem musterijeItem;
	private JMenu servisMenu;
	private JMenuItem servisniDioItem;
	private JMenuItem automobilItem;
	private JMenuItem servisItem;
	private JMenu knjiziceMenu;
	private JMenuItem knjiziceItem;

	private PoslovnaLogika poslovnaLogika;
	private Korisnik prijavljeniKorisnik;
	
	public GlavniProzorZaServisera(PoslovnaLogika pl, Korisnik prijavljeniKorisnik) {
		this.poslovnaLogika = pl;
		this.prijavljeniKorisnik = prijavljeniKorisnik;
		setTitle("Auto servis - " + prijavljeniKorisnik.getKorIme());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(500, 500);
		setResizable(false);
		initMenu();
		initListeners();
	}
	
	public void initMenu() {
		this.glavniMeni = new JMenuBar();
		this.korisniciMenu = new JMenu("Korisnici");
		this.serviseriItem = new JMenuItem("Serviseri");
		this.administratoriItem = new JMenuItem("Administratori");
		this.musterijeItem = new JMenuItem("Musterije");
		this.servisMenu = new JMenu("Servis");
		this.servisniDioItem = new JMenuItem("Servisni dio");
		this.automobilItem = new JMenuItem("Automobili");
		this.servisItem = new JMenuItem("Servisi");
		this.knjiziceMenu = new JMenu("Knjizice");
		this.knjiziceItem = new JMenuItem("Knjizice");
		
		this.korisniciMenu.add(serviseriItem);
		this.korisniciMenu.add(serviseriItem);
		this.korisniciMenu.add(administratoriItem);
		this.korisniciMenu.add(musterijeItem);
		
		this.servisMenu.add(servisniDioItem);
		this.servisMenu.add(automobilItem);
		this.servisMenu.add(servisItem);
		
		this.knjiziceMenu.add(knjiziceItem);
		
		this.glavniMeni.add(korisniciMenu);
		this.glavniMeni.add(servisMenu);
		this.glavniMeni.add(knjiziceMenu);
		setJMenuBar(this.glavniMeni);
	}

	public void initListeners() {
		servisItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ServisZaServisera szs = new ServisZaServisera(poslovnaLogika, prijavljeniKorisnik);
				szs.setVisible(true);
			}
		});
		automobilItem.setEnabled(false);
		korisniciMenu.setEnabled(false);
		knjiziceMenu.setEnabled(false);
	}
}
