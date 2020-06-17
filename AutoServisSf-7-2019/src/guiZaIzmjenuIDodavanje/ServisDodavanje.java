package guiZaIzmjenuIDodavanje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import enumeracija.Status;
import main.PoslovnaLogika;
import net.miginfocom.swing.MigLayout;
import osoba.Serviser;

import servis.Automobili;
import servis.Servis;

public class ServisDodavanje extends JFrame {
	
	private int brojac = 0;
	private JLabel lblAutomobil = new JLabel("Automobil");
	private JComboBox<String> cbAutomobil = new JComboBox<String>();
	private JLabel lblServiser = new JLabel("Serviser");
	private JComboBox<String> cbServiser = new JComboBox<String>();
	private JLabel lblTermin = new JLabel("Termin");
	private JCalendar cbTermin = new JCalendar();
	private JLabel lblOpis = new JLabel("Opis");
	private JTextField txtOpis = new JTextField(20);
	private JLabel lblStatus = new JLabel("Status");
	private JComboBox<Status> cbStatus = new JComboBox<Status>(Status.values());
	
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private PoslovnaLogika poslovnaLogika;
	private Servis servis;
	
	public ServisDodavanje(PoslovnaLogika poslovnaLogika , Servis servis) {
		this.poslovnaLogika = poslovnaLogika;
		this.servis = servis;
		if(this.cbServiser == null) {
			setTitle("Dodavanje novog servisa");
		}else {
			setTitle("Izmena servisa:" + this.servis.getAutomobil());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initListeners();
		setResizable(false);
		pack();
		}
	
	
	


	private void initGUI() {
		// TODO Auto-generated method stub
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		
		if(this.servis != null) {
			popuniPolja();
		}
		for(Automobili automobil:this.poslovnaLogika.getListaAutomobila()) {
			cbAutomobil.addItem(String.valueOf(automobil.getMarka()));
		}
		for(Serviser serviser:this.poslovnaLogika.getListaServisera()) {
			cbServiser.addItem(String.valueOf(serviser.getKorIme()));
		}

		add(lblAutomobil);
		add(cbAutomobil);
		add(lblServiser);
		add(cbServiser);
		add(lblTermin);
		add(cbTermin);
		add(lblOpis);
		add(txtOpis);
		add(lblStatus);
		add(cbStatus);
		add(new JLabel());
		add(btnOK , "split 2");
		add(btnCancel);
		
	
	}
	private void popuniPolja() {
		cbAutomobil.setSelectedItem(this.servis.getAutomobil());
		cbServiser.setSelectedItem(this.servis.getServiser());
		cbTermin.setDate(this.servis.getTermin());
		txtOpis.setText(this.servis.getOpis());
		cbStatus.setSelectedItem(this.servis.getStatus());
		
	}	

	private void initListeners() {
		// TODO Auto-generated method stub
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(validacija() == true) {
					String markaAutomobila = cbAutomobil.getSelectedItem().toString();
					Automobili automobil = poslovnaLogika.nadjiAutomobil(markaAutomobila);
					String KorIme = cbServiser.getSelectedItem().toString();
					Serviser serviser = poslovnaLogika.getServiserByKorIme(KorIme);
					Date termin = cbTermin.getDate();
					String opis = txtOpis.getText().trim();
					Status status = (Status) cbStatus.getSelectedItem();
					if(servis == null) {
						servis = new Servis(automobil, serviser, termin, opis, status);
					poslovnaLogika.getListaServisa().add(servis);
		
					}else {
						servis.setAutomobil(automobil);
						servis.setServiser(serviser);
						servis.setTermin(termin);
						servis.setOpis(opis);
						servis.setStatus(status);
						
					}
					poslovnaLogika.upisiUFajlServis();
					ServisDodavanje.this.dispose();
					ServisDodavanje.this.setVisible(false);
					
						
					
			}
		}
		});
	}
	public boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popunite sledece greske u unosu:\n";
		if(txtOpis.getText().trim().equals("")) {
			poruka += "- Unesite opis";
			ok = false;
		}
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
		}
			
				
			
		
		
		

	
}
	