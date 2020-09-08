package guiZaIzmjenuIDodavanje;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import main.PoslovnaLogika;
import osoba.Korisnik;
import servis.Automobili;
import servis.Servis;

public class ServisZaServisera extends JFrame {

	private JToolBar toolbar = new JToolBar();
	private ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
	
	private JButton btnAdd = new JButton(addIcon);
	private ImageIcon refreshIcon = new ImageIcon("Osvezi");
	private JButton btnRefresh = new JButton(refreshIcon);
	private ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
	private JButton btnEdit = new JButton(editIcon);
	
	private JTable tabela;
	private PoslovnaLogika poslovnaLogika;
	public Korisnik korisnik;
	public Servis servis;
	ArrayList<Servis> servisi = new ArrayList<Servis>();
	
	public ServisZaServisera(PoslovnaLogika poslovnaLogika, Korisnik korisnik) {
		this.poslovnaLogika = poslovnaLogika;
		this.korisnik = korisnik;
		setTitle("Servisi");
		setSize(700, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initListeners();
	}
	
	private void initGUI() {
		toolbar.add(btnAdd);
		toolbar.add(btnEdit);
		toolbar.add(btnRefresh);
		add(toolbar, BorderLayout.NORTH);
		
		for(Servis s:poslovnaLogika.getListaServisa()) {
			if(s.getServiser().getKorIme().equalsIgnoreCase(korisnik.getKorIme())) {
				servisi.add(s);
			}
		}
		
		String[] zaglavlje = new String[] {
				"Id", "Automobil", "Serviser", "Termin", "Opis", "Status"
		};
		
		Object[][] sadrzaj = new Object[this.servisi.size()][zaglavlje.length];
		for(int i=0; i<this.servisi.size(); i++) {
			
			Servis s = this.servisi.get(i);
			
			sadrzaj[i][0] = s.getId();
			sadrzaj[i][1] = s.getAutomobil().getId();
			sadrzaj[i][2] = s.getServiser().getKorIme();
			sadrzaj[i][3] = s.getTermin();
			sadrzaj[i][4] = s.getOpis();
			sadrzaj[i][5] = s.getStatus();
		}
		DefaultTableModel model = new DefaultTableModel(sadrzaj, zaglavlje);
		tabela = new JTable(model);
		tabela = new JTable(model);
		tabela.setRowSelectionAllowed(true);
		tabela.setColumnSelectionAllowed(false);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabela.setDefaultEditor(Object.class, null);
		
		JScrollPane scroll = new JScrollPane(tabela);
		add(scroll, BorderLayout.CENTER);
	}

	private void initListeners() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ServisDodavanjeServiser sds= new ServisDodavanjeServiser(poslovnaLogika, servis, korisnik);
				sds.setVisible(true);
			}
		});
	}
}
