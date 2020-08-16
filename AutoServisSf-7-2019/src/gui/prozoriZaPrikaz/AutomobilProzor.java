package gui.prozoriZaPrikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
import guiZaIzmjenuIDodavanje.ServisDodavanje;
import main.PoslovnaLogika;
import servis.Automobili;


public class AutomobilProzor extends JFrame {
	
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
	
	public AutomobilProzor(PoslovnaLogika poslovnaLogika) {
		this.poslovnalogika = poslovnaLogika;
		setTitle("Automobili");
		setSize(700, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
//		initGUI();
	//	initListeners();
	}
	
	private void initGUI() {
		// TODO Auto-generated method stub
		toolbar.add(btnAdd);
		toolbar.add(btnEdit);
		toolbar.add(btnRemove);
		toolbar.add(btnRefresh);
		add(toolbar,BorderLayout.NORTH);
		
		String[] zaglavlje = new String[] {
				"Id","Vlasnik", "Marka", "Model", "Godina_proizvodnje", "Zapremina_motora", "Snaga_motora", "Vrsta_goriva"
		
		};
		Object[][] sadrzaj = new Object[poslovnalogika.getListaAutomobila().size()][zaglavlje.length];
		
		
		for(int i=0; i<poslovnalogika.getListaAutomobila().size(); i++) {
			Automobili automobil = poslovnalogika.getListaAutomobila().get(i);
			
			sadrzaj[i][0] = automobil.getId();
			sadrzaj[i][1] = automobil.getVlasnik();
			sadrzaj[i][2] = automobil.getMarka();
			sadrzaj[i][3] = automobil.getModel();
			sadrzaj[i][4] = automobil.getGodina_proizvodnje();
			sadrzaj[i][5] = automobil.getZapremina_motora();
			sadrzaj[i][6] = automobil.getSnaga_motora();
			sadrzaj[i][7] = automobil.getVrsta_goriva();
		
			
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
				AutomobilDodavanje ad = new AutomobilDodavanje(poslovnalogika, null);
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
					Automobili automobil = poslovnalogika.nadjiAutomobilPoId(id);
					if(automobil != null) {
						AutomobilDodavanje sd = new AutomobilDodavanje(poslovnalogika, automobil);
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
					Automobili automobil = poslovnalogika.nadjiAutomobilPoId(id);
					if(automobil != null) {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete servis" , automobil.getId() + " - Potvrda brisanja" , JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							poslovnalogika.getListaAutomobila().remove(automobil);
							model.removeRow(red);
							poslovnalogika.upisiUFajlAutomobili();
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
	AutomobilProzor.this.dispose();
	AutomobilProzor ap = new AutomobilProzor(poslovnalogika);
	ap.setVisible(true);
	


	}
});

}


}
