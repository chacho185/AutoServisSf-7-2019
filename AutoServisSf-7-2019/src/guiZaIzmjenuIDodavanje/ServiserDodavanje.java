package guiZaIzmjenuIDodavanje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import enumeracija.Pol;
import enumeracija.Specijalizacija;
import enumeracija.Uloga;
import main.PoslovnaLogika;
import net.miginfocom.swing.MigLayout;
import osoba.Administrator;
import osoba.Serviser;

public class ServiserDodavanje extends JFrame {

	
	private int brojac = 0;
	private JLabel lblIme = new JLabel("Ime");
	private JTextField txtIme = new JTextField(20);
	private JLabel lblPrezime = new JLabel("Prezime");
	private JTextField txtPrezime = new JTextField(20);
	private JLabel lblPol = new JLabel("Pol");
	private JComboBox<Pol> cbPol = new JComboBox<Pol>(Pol.values());
	private JLabel lblAdresa = new JLabel("Adresa");
	private JTextField txtAdresa = new JTextField(20);
	private JLabel lblBrojTelefona = new JLabel("Broj telefona");
	private JTextField txtBrojTelefona = new JTextField(20);
	private JLabel lblKorisinickoIme = new JLabel("Korisnicko ime");
	private JTextField txtKorisinickoIme = new JTextField(20);
	private JLabel lblLozinka = new JLabel("Lozinka");
	private JPasswordField txtLozinka = new JPasswordField(20);
	private JLabel lblUloga = new JLabel("Uloga");
	private JComboBox<Uloga> cbUloga = new JComboBox<Uloga>(Uloga.values());
	private JLabel lblJMBG = new JLabel("JMBG");
	private JTextField txtJMBG = new JTextField(20);
	private JLabel lblPlate = new JLabel("Plata");
	private JTextField txtPlata = new JTextField(20);
	private JLabel lblSpecijalizacija = new JLabel("Specijalizacija");
	private JComboBox<Specijalizacija> cbSpecijalizacija = new JComboBox<Specijalizacija>(Specijalizacija.values());
	
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private PoslovnaLogika poslovnaLogika;
	private Serviser serviser;
	
	public ServiserDodavanje(PoslovnaLogika poslovnaLogika,Serviser serviser) {
		this.serviser = serviser;
		this.poslovnaLogika = poslovnaLogika;
		
		if(this.serviser == null) {
			setTitle("Dodavanje novog servisera");
		}else {
			setTitle("Izmjena servisera: " + this.serviser.getKorIme());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initListener();
		setResizable(false);
		pack();
	}

	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
			
		if(this.serviser != null) {
			popuniPolja();
		}
		add(lblIme);
		add(txtIme);
		add(lblPrezime);
		add(txtPrezime);
		add(lblPol);
		add(cbPol);
		add(lblAdresa);
		add(txtAdresa);
		add(lblBrojTelefona);
		add(txtBrojTelefona);
		add(lblKorisinickoIme);
		add(txtKorisinickoIme);
		add(lblLozinka);
		add(txtLozinka);
		add(lblUloga);
		add(cbUloga);
		add(lblJMBG);
		add(txtJMBG);
		add(lblPlate);
		add(txtPlata);
		add(lblSpecijalizacija);
		add(cbSpecijalizacija);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
	}
	
	private void popuniPolja() {
		txtIme.setText(this.serviser.getIme());
		txtPrezime.setText(this.serviser.getPrezime());
		cbPol.setSelectedItem(this.serviser.getPol());
		txtAdresa.setText(this.serviser.getAdresa());
		txtBrojTelefona.setText(this.serviser.getBrTel());
		txtKorisinickoIme.setText(this.serviser.getKorIme());
		txtLozinka.setText(this.serviser.getLozinka());
		cbUloga.setSelectedItem(this.serviser.getUloga());
		txtJMBG.setText(this.serviser.getJMBG());
		txtPlata.setText(String.valueOf(this.serviser.getPlata()));
	}
	
	private void initListener() {
		btnOK.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija() == true) {
					int id = ++brojac;
					String ime = txtIme.getText().trim();
					String prezime = txtPrezime.getText().trim();
					Pol pol = (Pol) cbPol.getSelectedItem();
					String adresa = txtAdresa.getText().trim();
					String brojTelefona = txtBrojTelefona.getText().trim();
					String korIme = txtKorisinickoIme.getText().trim();
					String lozinka = new String(txtLozinka.getPassword()).trim();
					Uloga uloga = (Uloga) cbUloga.getSelectedItem();
					String jmbg = txtJMBG.getText().trim();
					double plata = Double.parseDouble(txtPlata.getText().trim());
					Specijalizacija specijalizacija = (Specijalizacija) cbSpecijalizacija.getSelectedItem();
								
					
					if(serviser == null) {
						serviser = new Serviser(id, ime, prezime, jmbg, pol, adresa, brojTelefona, korIme, lozinka, uloga, plata,specijalizacija);
						poslovnaLogika.dodajUListuServisera(serviser);
					}else {
						serviser.setId(id);
						serviser.setPrezime(prezime);
						serviser.setPol(pol);
						serviser.setAdresa(adresa);
						serviser.setBrTel(brojTelefona);
						serviser.setKorIme(korIme);
						serviser.setLozinka(lozinka);
						serviser.setUloga(uloga);
						serviser.setJMBG(jmbg);
						serviser.setPlata(plata);
					}
					poslovnaLogika.upisiUFajlAdministratora();
					ServiserDodavanje.this.dispose();
					ServiserDodavanje.this.setVisible(false);
				}	
			}
		});
	}
		
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo vas da popravite sledece greske u unosu:\n";
		if(txtIme.getText().trim().equals("")) {
			poruka += "- Uneste ime\n";
			ok = false;
		}
		if(txtPrezime.getText().trim().equals("")) {
			poruka += "- Uneste prezime\n";
			ok = false;
		}
		if(txtAdresa.getText().trim().equals("")) {
			poruka += "- Uneste adresu\n";
			ok = false;
		}
		if(txtBrojTelefona.getText().trim().equals("")) {
			poruka += "- Uneste broj telefona\n";
			ok = false;
		}
		if(txtKorisinickoIme.getText().trim().equals("")) {
			poruka += "- Uneste korisnicko ime\n";
			ok = false;
		}
		String lozinka = new String(txtLozinka.getPassword()).trim();
		if(lozinka.trim().equals("")) {
			poruka += "- Uneste lozinka\n";
			ok = false;
		}
		if(txtJMBG.getText().trim().equals("")) {
			poruka += "- Uneste jmbg\n";
			ok = false;
		}
		try {
			Double.parseDouble(txtPlata.getText().trim());
		}catch(Exception e) {
			poruka += "- plata mora biti broj";
			ok = false;
				
		}
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}
		
	
		
	
}

