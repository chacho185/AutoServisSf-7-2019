package guiZaIzmjenuIDodavanje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import main.PoslovnaLogika;
import net.miginfocom.swing.MigLayout;
import servis.Automobili;
import servis.Servis;
import servis.ServisnaKnjizica;

public class ServisnaKnjizicaDodavanje extends JFrame {
	
	private int brojac = 0;
	private JLabel lblAutomobil = new JLabel("Automobil");
	private JComboBox<String> cbAutomobil = new JComboBox<String>();
	
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private PoslovnaLogika poslovnaLogika;
	private ServisnaKnjizica servisnaKnjizica;
	
	public ServisnaKnjizicaDodavanje(PoslovnaLogika poslovnaLogika , ServisnaKnjizica servisnaKnjizica) {
		this.poslovnaLogika = poslovnaLogika;
		this.servisnaKnjizica = servisnaKnjizica;
		if(this.cbAutomobil == null) {
			setTitle("Dodavanje nove ServKnjizice");
		}else {
			setTitle("Izmena servisa:" + this.servisnaKnjizica.getAutomobil());
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
		
		if(this.servisnaKnjizica != null) {
			popuniPolja();
		}
		for(Automobili automobil:this.poslovnaLogika.getListaAutomobila()) {
			cbAutomobil.addItem(String.valueOf(automobil.getMarka()));
		}
		add(lblAutomobil);
		add(cbAutomobil);
		add(new JLabel());
		add(btnOK , "split 2");
		add(btnCancel);
	}

	private void popuniPolja() {
		// TODO Auto-generated method stub
		cbAutomobil.setSelectedItem(this.servisnaKnjizica.getAutomobil());
		
	}

	
	private void initListeners() {
		// TODO Auto-generated method stub
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					String m = cbAutomobil.getSelectedItem().toString();
					Automobili automobil = poslovnaLogika.nadjiAutomobil(m);
					if(servisnaKnjizica == null) {
						servisnaKnjizica = new ServisnaKnjizica(automobil);
					poslovnaLogika.getListaServisnahKnj().add(servisnaKnjizica);
		
					}else {
						servisnaKnjizica.setAutomobil(automobil);
						
						
					}
					
					poslovnaLogika.upisiUFajlServisnuKnj();
					ServisnaKnjizicaDodavanje.this.dispose();
					ServisnaKnjizicaDodavanje.this.setVisible(false);
					
					
			}
			
		});
	}

}		
		
	

		
			
			
		
			
		
					
					
					
					
					
	

