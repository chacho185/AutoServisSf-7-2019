package guiZaIzmjenuIDodavanje;

import java.awt.TextField;
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
import osoba.Musterija;
import servis.Automobili;

public class AutomobilDodavanje extends JFrame {

	private int brojac = 0;
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
	
	private PoslovnaLogika poslovnaLogika;
	private Automobili automobil;
	
	public AutomobilDodavanje(PoslovnaLogika poslovnaLogika, Automobili automobil) {
		this.poslovnaLogika = poslovnaLogika;
		this.automobil = automobil;
		if(this.automobil == null) {
			setTitle("Dodavanje novog automobila");
		}else {
			setTitle("Izmena automobila: " + this.automobil.getMarka()+ this.automobil.getModel());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initListeners();
		setResizable(false);
		pack();
	}

	private void initListeners() {
		// TODO Auto-generated method stub
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija() == true) {
					String vlasnikKorIme = cbVlasnik.getSelectedItem().toString();
					Musterija musterija = poslovnaLogika.getMusterijaByKorIme(vlasnikKorIme);
					Marka_automobila marka_automobila = (Marka_automobila) cbMarkaAutomobila.getSelectedItem();
					model_automobila model_automobila = (model_automobila) cbModelAutomobila.getSelectedItem();
					int godinaProizvodnje = Integer.parseInt(txtGodinaProizvodnje.getText().trim());
					double zapreminaMotora = Double.parseDouble(txtZapreminaMotora.getText().trim());
					int snagaMotora = Integer.parseInt(txtSnagaMotora.getText().trim());
					Vrsta_goriva vrsta_goriva = (Vrsta_goriva) cbVrstaGoriva.getSelectedItem();
					if(automobil == null) {
						automobil = new Automobili(++brojac, musterija, marka_automobila, model_automobila, godinaProizvodnje, zapreminaMotora, snagaMotora, vrsta_goriva);
						poslovnaLogika.getListaAutomobila().add(automobil);
					}
					else {
						automobil.setId(++brojac);
						automobil.setVlasnik(musterija);
						automobil.setMarka(marka_automobila);
						automobil.setModel(model_automobila);
						automobil.setGodina_proizvodnje(godinaProizvodnje);
						automobil.setZapremina_motora(zapreminaMotora);
						automobil.setSnaga_motora(snagaMotora);
						automobil.setVrsta_goriva(vrsta_goriva);
					}
					poslovnaLogika.getListaAutomobila().add(automobil);
					poslovnaLogika.upisiUFajlAutomobili();
					AutomobilDodavanje.this.dispose();
					AutomobilDodavanje.this.setVisible(false);
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

	private void initGUI() {
		// TODO Auto-generated method stub
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		
		if(this.automobil != null) {
			popuniPolja();
		}
		for(Musterija m:this.poslovnaLogika.getListaMusterija()) {
			cbVlasnik.addItem(m.getKorIme());
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
		cbVlasnik.setSelectedItem(this.automobil.getVlasnik().getKorIme());
		cbMarkaAutomobila.setSelectedItem(this.automobil.getMarka());
		cbModelAutomobila.setSelectedItem(this.automobil.getModel());
		txtGodinaProizvodnje.setText(String.valueOf(this.automobil.getGodina_proizvodnje()));
		txtZapreminaMotora.setText(String.valueOf(this.automobil.getZapremina_motora()));
		txtSnagaMotora.setText(String.valueOf(this.automobil.getSnaga_motora()));
		cbVrstaGoriva.setSelectedItem(this.automobil.getVrsta_goriva());
		
	}
}
