package gui.glavni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import gui.prozoriZaPrikaz.AdministratorProzor;
import gui.prozoriZaPrikaz.AutomobilProzor;
import gui.prozoriZaPrikaz.MusterijaProzor;
import gui.prozoriZaPrikaz.ServiserProzor;
import gui.prozoriZaPrikaz.ServisniDioProzor;
import main.PoslovnaLogika;
import osoba.Korisnik;

public class GlavniProzorZaAdministratora extends JFrame{

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
	
	public GlavniProzorZaAdministratora(PoslovnaLogika poslovnaLogika, Korisnik prijavljeniKorisnik) {
		this.poslovnaLogika = poslovnaLogika;
		this.prijavljeniKorisnik = prijavljeniKorisnik;
		setTitle("Auto servis - " + prijavljeniKorisnik.getKorIme());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(500, 500);
		setResizable(false);
		initMenu();
		initListeners();
	}
	
	private void initMenu() {
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
		
		this.knjiziceMenu.add(knjiziceItem);
		
		this.glavniMeni.add(korisniciMenu);
		this.glavniMeni.add(servisMenu);
		this.glavniMeni.add(knjiziceMenu);
		setJMenuBar(this.glavniMeni);
	}
	
	private void initListeners() {
		serviseriItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ServiserProzor sp = new ServiserProzor(poslovnaLogika);
				sp.setVisible(true);
			}
		});
		administratoriItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdministratorProzor ap = new AdministratorProzor(poslovnaLogika);
				ap.setVisible(true);
			}
		});
		musterijeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MusterijaProzor mp = new MusterijaProzor(poslovnaLogika);
				mp.setVisible(true);
			}
		});
		servisniDioItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ServisniDioProzor sdp = new ServisniDioProzor(poslovnaLogika);
				sdp.setVisible(true);
			}
		});
		automobilItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AutomobilProzor ap = new AutomobilProzor(poslovnaLogika);
				ap.setVisible(true);
			}
		});
		serviseriItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ServiserProzor sp = new ServiserProzor(poslovnaLogika);
				sp.setVisible(true);
			}
		});
//		knjiziceItem.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		});
	}
}
