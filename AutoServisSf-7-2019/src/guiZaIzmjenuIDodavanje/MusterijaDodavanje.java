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
import enumeracija.Uloga;
import main.PoslovnaLogika;
import net.miginfocom.swing.MigLayout;
import osoba.Administrator;
import osoba.Musterija;

public class MusterijaDodavanje extends JFrame {

	
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
	private JLabel lblBrojSkupljenihBodova = new JLabel("BrojSkupljenihBodova");
	private JTextField txtBrojSkupljenihBodova = new JTextField(20);
	
	private JButton btnOK = new  JButton ("OK");
	private JButton btnCancel = new JButton ("Cancel");
	
	private PoslovnaLogika poslovnaLogika;
	private Musterija musterija;
	
	public MusterijaDodavanje(Musterija musterija) {
		this.musterija = musterija;
		
		if(this.musterija == null) {
			setTitle("Dodavanje novog musterije");
		}else {
			setTitle("Izmjena musterije: " + this.musterija.getKorIme());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initListener();
		setResizable(false);
		pack();
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
					int BrojSkupljenihBodova =  Integer.parseInt(txtBrojSkupljenihBodova.getText().trim());
					
					if(musterija == null) {
						musterija = new Musterija(id, ime, prezime, jmbg, pol, adresa, brojTelefona, korIme, lozinka, uloga, BrojSkupljenihBodova);
						poslovnaLogika.dodajUListuMusterija(musterija);
					}else {
						musterija.setId(id);
						musterija.setPrezime(prezime);
						musterija.setPol(pol);
						musterija.setAdresa(adresa);
						musterija.setBrTel(brojTelefona);
						musterija.setKorIme(korIme);
						musterija.setLozinka(lozinka);
						musterija.setUloga(uloga);
						musterija.setJMBG(jmbg);
						musterija.setBrojSkupljenihBodova(BrojSkupljenihBodova);
					}
					poslovnaLogika.upisiUFajlAdministratora();
					MusterijaDodavanje.this.dispose();
					MusterijaDodavanje.this.setVisible(false);
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
				Integer.parseInt(txtBrojSkupljenihBodova.getText().trim());
			}catch(Exception e) {
				poruka += "- plata mora biti broj";
				ok = false;
			}
			if(ok == false) {
				JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
			}
			return ok;
		}
		
		
		private void initGUI() {
			MigLayout mig = new MigLayout("wrap 2");
			setLayout(mig);
			
			if(this.musterija != null) {
				popuniPolja();
				
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
				add(lblBrojSkupljenihBodova);
				add(txtBrojSkupljenihBodova);
				add(new JLabel());
				add(btnOK, "split 2");
				add(btnCancel);
			}
		}

		private void popuniPolja() {
			txtIme.setText(this.musterija.getIme());
			txtPrezime.setText(this.musterija.getPrezime());
			cbPol.setSelectedItem(this.musterija.getPol());
			txtAdresa.setText(this.musterija.getAdresa());
			txtBrojTelefona.setText(this.musterija.getBrTel());
			txtKorisinickoIme.setText(this.musterija.getKorIme());
			txtLozinka.setText(this.musterija.getLozinka());
			cbUloga.setSelectedItem(this.musterija.getUloga());
			txtJMBG.setText(this.musterija.getJMBG());
			txtBrojSkupljenihBodova.setText(String.valueOf(this.musterija.getBrojSkupljenihBodova()));
		}
	}


