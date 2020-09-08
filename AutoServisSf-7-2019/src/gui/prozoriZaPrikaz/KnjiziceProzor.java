package gui.prozoriZaPrikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import guiZaIzmjenuIDodavanje.AutomobilDodavanje;
import guiZaIzmjenuIDodavanje.ServisnaKnjizicaDodavanje;
import main.PoslovnaLogika;
import servis.Automobili;
import servis.ServisnaKnjizica;

public class KnjiziceProzor extends JFrame{

	private JToolBar toolbar = new JToolBar();
	private ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
	private JButton btnAdd = new JButton(addIcon);
	private ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
	private JButton btnEdit = new JButton(editIcon);
	private ImageIcon removeIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
	private JButton btnRemove = new JButton(removeIcon);
	private ImageIcon refreshIcon = new ImageIcon("Osvezi");
	private JButton btnRefresh = new JButton(refreshIcon);
	
	private JTable tabela;
	private PoslovnaLogika poslovnalogika;
			
	public KnjiziceProzor(PoslovnaLogika poslovnaLogika) {
		this.poslovnalogika = poslovnaLogika;
		setTitle("Automobili");
		setSize(700, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initListeners();
	}
	
	private void initGUI() {
		// TODO Auto-generated method stub
		toolbar.add(btnAdd);
		toolbar.add(btnEdit);
		toolbar.add(btnRemove);
		toolbar.add(btnRefresh);
		add(toolbar,BorderLayout.NORTH);
		
		String[] zaglavlje = new String[] {
				"Id","Automobil"
		
		};
		Object[][] sadrzaj = new Object[poslovnalogika.getListaAutomobila().size()][zaglavlje.length];
		
		
		for(int i=0; i<poslovnalogika.getListaAutomobila().size(); i++) {
			ServisnaKnjizica knj = poslovnalogika.getListaServisnahKnj().get(i);
			
			//sadrzaj[i][0] = automobil.getId();
			sadrzaj[i][1] = knj.getAutomobil().getMarka();
			
		
			
		}
		DefaultTableModel model = new DefaultTableModel(sadrzaj, zaglavlje);
		tabela = new JTable(model);
		tabela = new JTable(model);
		tabela.setRowSelectionAllowed(true);
		tabela.setColumnSelectionAllowed(false);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabela.setDefaultEditor(Object.class, null);
	//	tabela.getTableHeader().setReorderingAllowed(false);
		
		
		JScrollPane scroll = new JScrollPane(tabela);
		add(scroll, BorderLayout.CENTER);
	}
	private void initListeners() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			ServisnaKnjizicaDodavanje ad = new ServisnaKnjizicaDodavanje(poslovnalogika, null);
			ad.setVisible(true);
				
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = tabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)tabela.getModel();
					String idstr = model.getValueAt(red, 0).toString();
					int id = Integer.parseInt(idstr);
					ServisnaKnjizica s = new ServisnaKnjizica();
					if(s != null) {
						ServisnaKnjizicaDodavanje sd = new ServisnaKnjizicaDodavanje(poslovnalogika, s);
						sd.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani automobil!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = tabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);	
				}else {
					DefaultTableModel model = (DefaultTableModel)tabela.getModel();
					int id = Integer.parseInt(model.getValueAt(red, 0).toString());
					ServisnaKnjizica sk = new ServisnaKnjizica();
					if(sk != null) {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete knnjizicu" , sk.getId() + " - Potvrda brisanja" , JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							poslovnalogika.getListaServisnahKnj().remove(sk);
							model.removeRow(red);
							poslovnalogika.upisiUFajlServisnuKnj();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani automobil","Greska!", JOptionPane.ERROR_MESSAGE);
						
					}
				}
				
			}
		});

		btnRefresh.addActionListener(new ActionListener() {
	
		@Override
		public void actionPerformed(ActionEvent e) {
		KnjiziceProzor.this.dispose();
		KnjiziceProzor ap = new KnjiziceProzor(poslovnalogika);
		ap.setVisible(true);
	
	}
});

}
}
