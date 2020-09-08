package guiZaIzmjenuIDodavanje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import enumeracija.Marka_automobila;
import enumeracija.Vrsta_goriva;
import enumeracija.model_automobila;
import main.PoslovnaLogika;
import net.miginfocom.swing.MigLayout;
import osoba.Korisnik;
import osoba.Musterija;
import osoba.Serviser;
import servis.Automobili;

public class AutomobilDodavanjeMusterija extends JFrame{

	private int brojac= 0;
	private JLabel lblVlasnik = new JLabel("Vlasnik");
	private JComboBox<String> cbVlasnik = new JComboBox<String>();
	private JLabel lblMarkaAutomobila = new JLabel("MarkaAutomobila");
	private JComboBox<Marka_automobila> cbMarkaAutomobila = new JComboBox<Marka_automobila>(Marka_automobila.values());
	private JLabel lblModelAutomobila = new JLabel("ModelAutomobila");
	private JComboBox<model_automobila> cbModelAutomobila = new JComboBox<model_automobila>(model_automobila.values());
	private JLabel lblGodinaProizvodnje = new JLabel("GodinaProizvodnje");
	private JTextField txtGodinaProizvodnje = new JTextField(4);
	private JLabel lblZapreminaMotora = new JLabel("ZapreminaMotora");
	private JTextField txtZapreminaMotora = new JTextField(4);
	private JLabel lblSnagaMotora = new JLabel("SnagaMotora");
	private JTextField txtSnagaMotora = new JTextField(4);
	private JLabel lblVrstaGoriva = new JLabel("VrstaGoriva");
	private JComboBox<Vrsta_goriva> cbVrstaGoriva = new JComboBox<Vrsta_goriva>(Vrsta_goriva.values());
	
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private PoslovnaLogika pl;
	private Automobili a;
	private Korisnik k;
	
	public AutomobilDodavanjeMusterija(PoslovnaLogika pl, Automobili a, Korisnik k) {
		this.pl = pl;
		this.a = a;
		this.k = k;
		if(this.a == null) {
			setTitle("Dodavanje novog automobila");
		}else {
			setTitle("Izmjena automobila: " + this.a.getId());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initListeners();
		setResizable(false);
		pack();
	}

	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		
		if(this.a != null) {
			popuniPolja();
		}
		cbVlasnik.addItem(k.getKorIme());
		for(Serviser s:this.pl.getListaServisera()) {
			cbVlasnik.addItem(s.getKorIme());
		}
		
		add(lblVlasnik);
		add(cbVlasnik);
		add(lblMarkaAutomobila);
		add(cbMarkaAutomobila);
		add(lblModelAutomobila);
		add(cbModelAutomobila);
		add(lblGodinaProizvodnje);
		add(txtGodinaProizvodnje);
		add(lblZapreminaMotora);
		add(txtZapreminaMotora);
		add(lblSnagaMotora);
		add(txtSnagaMotora);
		add(lblVrstaGoriva);
		add(cbVrstaGoriva);
		add(new JLabel());
		add(btnOK , "split 2");
		add(btnCancel);
	}
	
	private void popuniPolja() {
		// TODO Auto-generated method stub
		cbVlasnik.setSelectedItem(this.a.getVlasnik().getKorIme());
		cbMarkaAutomobila.setSelectedItem(this.a.getMarka());
		cbModelAutomobila.setSelectedItem(this.a.getModel());
		txtGodinaProizvodnje.setText(String.valueOf(this.a.getGodina_proizvodnje()));
		txtZapreminaMotora.setText(String.valueOf(this.a.getZapremina_motora()));
		txtSnagaMotora.setText(String.valueOf(this.a.getSnaga_motora()));
		cbVrstaGoriva.setSelectedItem(this.a.getVrsta_goriva());
		
	}
	
	private void initListeners() {
btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija() == true) {
					String vlasnikKorIme = cbVlasnik.getSelectedItem().toString();
					Musterija musterija = pl.getMusterijaByKorIme(vlasnikKorIme);
					Marka_automobila marka_automobila = (Marka_automobila) cbMarkaAutomobila.getSelectedItem();
					model_automobila model_automobila = (model_automobila) cbModelAutomobila.getSelectedItem();
					int godinaProizvodnje = Integer.parseInt(txtGodinaProizvodnje.getText().trim());
					double zapreminaMotora = Double.parseDouble(txtZapreminaMotora.getText().trim());
					int snagaMotora = Integer.parseInt(txtSnagaMotora.getText().trim());
					Vrsta_goriva vrsta_goriva = (Vrsta_goriva) cbVrstaGoriva.getSelectedItem();
					if(a == null) {
						a = new Automobili(++brojac, musterija, marka_automobila, model_automobila, godinaProizvodnje, zapreminaMotora, snagaMotora, vrsta_goriva);
						pl.getListaAutomobila().add(a);
					}
					else {
						a.setId(++brojac);
						a.setVlasnik(musterija);
						a.setMarka(marka_automobila);
						a.setModel(model_automobila);
						a.setGodina_proizvodnje(godinaProizvodnje);
						a.setZapremina_motora(zapreminaMotora);
						a.setSnaga_motora(snagaMotora);
						a.setVrsta_goriva(vrsta_goriva);
					}
					pl.getListaAutomobila().add(a);
					pl.upisiUFajlAutomobili();
					AutomobilDodavanjeMusterija.this.dispose();
					AutomobilDodavanjeMusterija.this.setVisible(false);
				}
				
			}

			private boolean validacija() {
				boolean ok = true;
				String poruka = "Molimo popravite sledece greske u iznosu:\n";
				if(txtGodinaProizvodnje.getText().trim().equals("")) {
					poruka += "- Unesite godinu proizvodnje";
					ok = false;
				}
				if(txtZapreminaMotora.getText().trim().equals("")) {
					poruka += "- Unesite zapreminu motora";
					ok = false;
				}
				if(txtSnagaMotora.getText().trim().equals("")) {
					poruka += "- Unesite snagu motora";
					ok = false;
				}
				if(ok == false) {
					JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
				}
				return ok;
			}
		});
	}
}
