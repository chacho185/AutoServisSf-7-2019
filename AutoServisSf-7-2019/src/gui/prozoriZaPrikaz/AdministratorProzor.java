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
import main.PoslovnaLogika;
import osoba.Administrator;


public class AdministratorProzor extends JFrame {
	
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
	
	public AdministratorProzor(PoslovnaLogika poslovnaLogika) {
		this.poslovnalogika = poslovnaLogika;
		setTitle("Administratori");
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
				"Id","Ime", "Prezime", "Jmbg", "Pol", "Adresa", "Broj telefona", "Korisnicko ime", "Lozinka","Uloga"
		
		};
		Object[][] sadrzaj = new Object[poslovnalogika.getListaAdministratora().size()][zaglavlje.length];
		
		for(int i=0; i<poslovnalogika.getListaAdministratora().size(); i++) {
			Administrator administrator = poslovnalogika.getListaAdministratora().get(i);
			sadrzaj[i][0] = administrator.getId();
			sadrzaj[i][1] = administrator.getIme();
			sadrzaj[i][2] = administrator.getPrezime();
			sadrzaj[i][3] = administrator.getJMBG();
			sadrzaj[i][4] = administrator.getPol();
			sadrzaj[i][5] = administrator.getAdresa();
			sadrzaj[i][6] = administrator.getBrTel();
			sadrzaj[i][7] = administrator.getKorIme();
			sadrzaj[i][8] = administrator.getLozinka();
			sadrzaj[i][9] = administrator.getUloga();
			
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
				AdministratorDodavanje a = new AdministratorDodavanje(poslovnalogika, null);
				a.setVisible(true);
				
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
					String korIme = model.getValueAt(red, 6).toString();
					Administrator administrator = poslovnalogika.nadjiAdministratoraPoKorIme(korIme);
					if(administrator !=null) {
						AdministratorDodavanje ad = new AdministratorDodavanje(poslovnalogika, administrator);
						ad.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci Administratora" ,"Greska", JOptionPane.ERROR_MESSAGE);
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
					Administrator administrator = poslovnalogika.nadjiAdministratoraPoKorIme(korIme);
					if(administrator != null) {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete Administratora?" , administrator.getKorIme() + " - Potvrda brisanja" , JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							poslovnalogika.getListaAdministratora().remove(administrator);
							model.removeRow(red);
							poslovnalogika.upisiUFajlAdministratora();
						}
						else {
							JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog Administratora!","Greska!", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				
			}
		
		});
			
		btnRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			AdministratorProzor.this.dispose();
			AdministratorProzor ap = new AdministratorProzor(poslovnalogika);
			ap.setVisible(true);
			


			}
		});
	
	}
}
