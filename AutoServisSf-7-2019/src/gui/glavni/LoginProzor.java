package gui.glavni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import enumeracija.Uloga;
import main.PoslovnaLogika;
import net.miginfocom.swing.MigLayout;
import osoba.Korisnik;

public class LoginProzor extends JFrame{

	private JLabel lblPozdrav;
	private JLabel lblKorisnickoIme;
	private JTextField txtKorisnickoIme;
	private JLabel lblSifra;
	private JPasswordField txtSifra;
	private JButton btnPrijava;
	private JButton btnOtkazi;
	private PoslovnaLogika poslovnaLogika;
	
	public LoginProzor(PoslovnaLogika poslovnaLogika) {
		this.poslovnaLogika = poslovnaLogika;
		setTitle("Prijava");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initListeners();
		pack();
	}
	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		
		this.lblPozdrav = new JLabel("Dobrodosli. Molimo da se prijavite.");
		this.lblKorisnickoIme = new JLabel("Korisnicko ime");
		this.txtKorisnickoIme = new JTextField(20);
		this.lblSifra = new JLabel("Sifra");
		this.txtSifra = new JPasswordField(20);
		this.btnPrijava = new JButton("Prijava");
		this.btnOtkazi = new JButton("Otkazi");
		
		this.getRootPane().setDefaultButton(btnPrijava);
		
		add(lblPozdrav , "span 2");
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblSifra);
		add(txtSifra);
		add(new JLabel());
		add(btnPrijava, "split 2");
		add(btnOtkazi);
	}
	
	private void initListeners() {
		btnPrijava.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String korisnickoIme = txtKorisnickoIme.getText().trim();
				String sifra = new String(txtSifra.getPassword()).trim();
				
				if(korisnickoIme.equals("") || txtSifra.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke");
				}else {
				
					Korisnik prijavljen = poslovnaLogika.login(korisnickoIme, sifra);
					if(prijavljen == null) {
						JOptionPane.showMessageDialog(null, "Neispravni login podaci", "Greska", JOptionPane.WARNING_MESSAGE);
					}else if(prijavljen.getUloga() == Uloga.ADMINISTRATOR){
						LoginProzor.this.dispose();
						GlavniProzorZaAdministratora mp = new GlavniProzorZaAdministratora(poslovnaLogika, prijavljen);
						mp.setVisible(true);
					}else if(prijavljen.getUloga() == Uloga.MUSTERIJA){
						LoginProzor.this.dispose();
//						GlavniProzorZaMusteriju lp = new GlavniProzorZaMusteriju(poslovnaLogika, prijavljen);
						GlavniProzorZaMusteriju lp = new GlavniProzorZaMusteriju(poslovnaLogika, prijavljen);
						lp.setVisible(true);
					}else if(prijavljen.getUloga() == Uloga.SERVISER){
						LoginProzor.this.dispose();
//						GlavniProzorZaServisera pp = new GlavniProzorZaPacijenta(domZdravlja, prijavljen);
						GlavniProzorZaServisera gzs = new GlavniProzorZaServisera(poslovnaLogika, prijavljen);
						gzs.setVisible(true);
					}
				}
			}
		});
		btnOtkazi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginProzor.this.setVisible(false);
				LoginProzor.this.dispose();
			}
		});
	}
}
