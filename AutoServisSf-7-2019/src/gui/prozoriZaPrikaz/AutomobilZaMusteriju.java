package gui.prozoriZaPrikaz;

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

import guiZaIzmjenuIDodavanje.AutomobilDodavanjeMusterija;
import main.PoslovnaLogika;
import osoba.Korisnik;
import servis.Automobili;
import servis.Servis;

public class AutomobilZaMusteriju extends JFrame{

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
	public Automobili automobil;
	ArrayList<Automobili> automobilis = new ArrayList<Automobili>();
	
	public AutomobilZaMusteriju(PoslovnaLogika poslovnaLogika, Korisnik korisnik) {
		this.poslovnaLogika = poslovnaLogika;
		this.korisnik = korisnik;
		setTitle("Automobili");
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
		
		for(Automobili automobil1:poslovnaLogika.getListaAutomobila()) {
			if(automobil1.getVlasnik().getKorIme().equalsIgnoreCase(korisnik.getKorIme())) {
				automobilis.add(automobil1);
			}
		}
		
		String[] zaglavlje = new String[] {
				"Id", "Vlasnik", "Marka", "Model", "Godina proizvodnje", "Zapremina", "Snaga motora", "Vrsta goriva"
		};
		
		Object[][] sadrzaj = new Object[this.automobilis.size()][zaglavlje.length];
		for(int i=0; i<this.automobilis.size(); i++) {
			
			Automobili a = this.automobilis.get(i);
			
			sadrzaj[i][0] = a.getId();
			sadrzaj[i][1] = a.getVlasnik().getKorIme();
			sadrzaj[i][2] = a.getMarka();
			sadrzaj[i][3] = a.getModel();
			sadrzaj[i][4] = a.getGodina_proizvodnje();
			sadrzaj[i][5] = a.getZapremina_motora();
			sadrzaj[i][6] = a.getSnaga_motora();
			sadrzaj[i][7] = a.getVrsta_goriva();
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
				AutomobilDodavanjeMusterija pd = new AutomobilDodavanjeMusterija(poslovnaLogika, automobil, korisnik);
				pd.setVisible(true);
			}
		});
	}
}
