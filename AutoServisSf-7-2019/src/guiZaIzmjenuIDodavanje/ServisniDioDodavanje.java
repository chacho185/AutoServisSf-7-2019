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
import enumeracija.model_automobila;
import main.PoslovnaLogika;
import net.miginfocom.swing.MigLayout;
import servis.Automobili;
import servis.ServisniDio;

public class ServisniDioDodavanje extends JFrame {
	
	private int brojac = 0;
	private JLabel lblNaziv_dijela = new JLabel("Naziv_dijela");
	private JTextField txtNaziv_dijela = new JTextField(20);
	private JLabel lblCijena = new JLabel("Cijena");
	private JTextField txtCijena = new JTextField(20);
	private JLabel lblMarkaAutomobila = new JLabel("MarkaAutomobila");
	private JComboBox<Marka_automobila> cbMarkaAutomobila = new JComboBox<Marka_automobila>(Marka_automobila.values());
	private JLabel lblModelAutomobila = new JLabel("ModelAutomobila");
	private JComboBox<model_automobila> cbModelAutomobila = new JComboBox<model_automobila>(model_automobila.values());
	
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private PoslovnaLogika poslovnaLogika;
	private ServisniDio servisniDio;
	
	public ServisniDioDodavanje(PoslovnaLogika poslovnaLogika, ServisniDio servisniDio) {
		this.poslovnaLogika = poslovnaLogika;
		this.servisniDio = servisniDio;
		if(this.servisniDio == null) {
			setTitle("Dodavanje novog servisnog dijela");
		}else {
			setTitle("Izmena servisnog dijela: " + this.servisniDio.getNaziv_dijela()+ this.servisniDio.getModel());
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
				// TODO Auto-generated method stub
				if(validacija() == true) {
					int id = brojac++;
					String naziv_dijela = txtNaziv_dijela.getText().trim();
					double cijena = Double.parseDouble(txtCijena.getText().trim());
					Marka_automobila marka_automobila = (Marka_automobila) cbMarkaAutomobila.getSelectedItem();
					model_automobila model_automobila = (model_automobila) cbModelAutomobila.getSelectedItem();
					if(servisniDio == null) {
						servisniDio = new ServisniDio(id, naziv_dijela, cijena, marka_automobila, model_automobila);
						poslovnaLogika.getListaServisniDio().add(servisniDio);
					}
					else {
						servisniDio.setNaziv_dijela(naziv_dijela);
						servisniDio.setCijena(cijena);
						servisniDio.setMarka(marka_automobila);
						servisniDio.setModel(model_automobila);
						
						
					}
					poslovnaLogika.getListaServisniDio().add(servisniDio);
					poslovnaLogika.upisiUFajlServisniDio();
					ServisniDioDodavanje.this.dispose();
					ServisniDioDodavanje.this.setVisible(false);
					
				}
			}
			
			
			private boolean validacija() {
				boolean ok = true;
				String poruka = "Molimo popravite sledece greske u iznosu:\n";
				if(txtNaziv_dijela.getText().trim().equals("")) {
					poruka += "- Unesite naziv dijela";
					ok = false;
				}
				if(txtCijena.getText().trim().equals("")) {
					poruka += "- Unesite cijenu";
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
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		
		if(this.servisniDio != null) {
			popuniPolja();
		}
		
		add(lblNaziv_dijela);
		add(txtNaziv_dijela);
		add(lblCijena);
		add(txtCijena);
		add(lblMarkaAutomobila);
		add(cbMarkaAutomobila);
		add(lblModelAutomobila);
		add(cbModelAutomobila);
		add(new JLabel());
		add(btnOK , "split 2");
		add(btnCancel);
			
		
	}
	
	private void popuniPolja() {
		txtNaziv_dijela.setText(String.valueOf(this.servisniDio.getNaziv_dijela()));
		txtCijena.setText(String.valueOf(this.servisniDio.getCijena()));
		cbMarkaAutomobila.setSelectedItem(this.servisniDio.getMarka());
		cbModelAutomobila.setSelectedItem(this.servisniDio.getModel());
	}
}
