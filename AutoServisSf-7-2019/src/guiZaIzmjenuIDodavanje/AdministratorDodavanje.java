
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
//import net.miginfocom.swing.MigLayout;
import osoba.Administrator;

public class AdministratorDodavanje extends JFrame {
	
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
	
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private PoslovnaLogika poslovnaLogika;
	private Administrator administrator;
	
	public AdministratorDodavanje(PoslovnaLogika poslovnaLogika,Administrator administrator) {
		this.administrator = administrator;
		this.poslovnaLogika = poslovnaLogika;
		
		if(this.administrator == null) {
			setTitle("Dodavanje novog administratora");
		}else {
			setTitle("Izmjena administratora: " + this.administrator.getKorIme());
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
					double plata = Double.parseDouble(txtPlata.getText().trim());
				
					if(administrator == null) {
						administrator = new Administrator(id, ime, prezime, jmbg, pol, adresa, brojTelefona, korIme, lozinka, uloga, plata);
						poslovnaLogika.dodajUListuAdministratora(administrator);
					}else {
						administrator.setId(id);
						administrator.setPrezime(prezime);
						administrator.setPol(pol);
						administrator.setAdresa(adresa);
						administrator.setBrTel(brojTelefona);
						administrator.setKorIme(korIme);
						administrator.setLozinka(lozinka);
						administrator.setUloga(uloga);
						administrator.setJMBG(jmbg);
						administrator.setPlata(plata);
					}
					poslovnaLogika.upisiUFajlAdministratora();
					AdministratorDodavanje.this.dispose();
					AdministratorDodavanje.this.setVisible(false);
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
	
	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		
		if(this.administrator != null) {
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
			add(lblPlate);
			add(txtPlata);
			add(new JLabel());
			add(btnOK, "split 2");
			add(btnCancel);
		}
	}

	private void popuniPolja() {
		txtIme.setText(this.administrator.getIme());
		txtPrezime.setText(this.administrator.getPrezime());
		cbPol.setSelectedItem(this.administrator.getPol());
		txtAdresa.setText(this.administrator.getAdresa());
		txtBrojTelefona.setText(this.administrator.getBrTel());
		txtKorisinickoIme.setText(this.administrator.getKorIme());
		txtLozinka.setText(this.administrator.getLozinka());
		cbUloga.setSelectedItem(this.administrator.getUloga());
		txtJMBG.setText(this.administrator.getJMBG());
		txtPlata.setText(String.valueOf(this.administrator.getPlata()));
	}
}
