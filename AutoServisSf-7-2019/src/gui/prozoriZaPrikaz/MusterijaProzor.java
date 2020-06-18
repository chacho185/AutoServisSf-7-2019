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

import guiZaIzmjenuIDodavanje.AdministratorDodavanje;
import guiZaIzmjenuIDodavanje.MusterijaDodavanje;
import main.PoslovnaLogika;
import osoba.Administrator;
import osoba.Musterija;

public class MusterijaProzor extends JFrame {
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
	
	public MusterijaProzor(PoslovnaLogika poslovnaLogika) {
		this.poslovnalogika = poslovnaLogika;
		setTitle("Musterija");
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
				"Id","Ime", "Prezime", "Jmbg", "Pol", "Adresa", "Broj telefona", "Korisnicko ime", "Lozinka","Uloga","BrojSkupljenihBodova"
		
		};
		Object[][] sadrzaj = new Object[poslovnalogika.getListaMusterija().size()][zaglavlje.length];
		
		for(int i=0; i<poslovnalogika.getListaMusterija().size(); i++) {
			Musterija musterija = poslovnalogika.getListaMusterija().get(i);
			sadrzaj[i][0] = musterija.getId();
			sadrzaj[i][1] = musterija.getIme();
			sadrzaj[i][2] = musterija.getPrezime();
			sadrzaj[i][3] = musterija.getJMBG();
			sadrzaj[i][4] = musterija.getPol();
			sadrzaj[i][5] = musterija.getAdresa();
			sadrzaj[i][6] = musterija.getBrTel();
			sadrzaj[i][7] = musterija.getKorIme();
			sadrzaj[i][8] = musterija.getLozinka();
			sadrzaj[i][9] = musterija.getUloga();
			sadrzaj[i][10] = musterija.getBrojSkupljenihBodova();
			
			
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
				MusterijaDodavanje m = new MusterijaDodavanje(poslovnalogika,null);
				m.setVisible(true);
				
			}
		
			
				
		});
btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int red = tabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli", "Greska", JOptionPane.WARNING_MESSAGE);
					
				}else {
					DefaultTableModel model = (DefaultTableModel)tabela.getModel();
					String korIme = model.getValueAt(red, 7).toString();
					Musterija musterija = poslovnalogika.nadjiMusterijuPoKorIme(korIme);
					if(musterija !=null) {
						MusterijaDodavanje md = new MusterijaDodavanje(poslovnalogika , musterija);
						md.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog Musteriju" ,"Greska", JOptionPane.ERROR_MESSAGE);
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
			String korIme = model.getValueAt(red, 7).toString();
			Musterija musterija = poslovnalogika.nadjiMusterijuPoKorIme(korIme);
			if(musterija != null) {
				int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete Musteriju?" , musterija.getKorIme() + " - Potvrda brisanja" , JOptionPane.YES_NO_OPTION);
				if(izbor == JOptionPane.YES_OPTION) {
					poslovnalogika.getListaMusterija().remove(musterija);
					model.removeRow(red);
					poslovnalogika.upisiUFajlMusterija();;
				}
				else {
					JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog Musteriju!","Greska!", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
	}

});
	
btnRefresh.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
	MusterijaProzor.this.dispose();
	MusterijaProzor mp = new MusterijaProzor(poslovnalogika);
	mp.setVisible(true);
	


	}
});

}
	

}
