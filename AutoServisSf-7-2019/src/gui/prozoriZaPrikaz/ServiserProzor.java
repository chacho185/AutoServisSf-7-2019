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
import guiZaIzmjenuIDodavanje.ServisDodavanje;
import guiZaIzmjenuIDodavanje.ServiserDodavanje;
import main.PoslovnaLogika;
import osoba.Administrator;
import osoba.Serviser;

public class ServiserProzor extends JFrame {
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
	
	public ServiserProzor(PoslovnaLogika poslovnaLogika) {
		this.poslovnalogika = poslovnaLogika;
		setTitle("Serviseri");
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
				"Id","Ime", "Prezime", "Jmbg", "Pol", "Adresa", "Broj telefona", "Korisnicko ime", "Lozinka","Uloga","Plata","Specijalizacija"
		
		};
		Object[][] sadrzaj = new Object[poslovnalogika.getListaServisera().size()][zaglavlje.length];
		
		for(int i=0; i<poslovnalogika.getListaServisera().size(); i++) {
			Serviser serviser = poslovnalogika.getListaServisera().get(i);
			sadrzaj[i][0] = serviser.getId();
			sadrzaj[i][1] = serviser.getIme();
			sadrzaj[i][2] = serviser.getPrezime();
			sadrzaj[i][3] = serviser.getJMBG();
			sadrzaj[i][4] = serviser.getPol();
			sadrzaj[i][5] = serviser.getAdresa();
			sadrzaj[i][6] = serviser.getBrTel();
			sadrzaj[i][7] = serviser.getKorIme();
			sadrzaj[i][8] = serviser.getLozinka();
			sadrzaj[i][9] = serviser.getUloga();
			sadrzaj[i][10] = serviser.getPlata();
			sadrzaj[i][11] = serviser.getSpecijalizacija();
			
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
				ServiserDodavanje s = new ServiserDodavanje(poslovnalogika, null);
				s.setVisible(true);
				
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
					Serviser serviser = poslovnalogika.nadjiServiseraPoKorIme(korIme);
					if(serviser !=null) {
						ServiserDodavanje sd = new ServiserDodavanje(poslovnalogika,null);
						sd.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci Servisera" ,"Greska", JOptionPane.ERROR_MESSAGE);
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
					Serviser serviser = poslovnalogika.nadjiServiseraPoKorIme(korIme);
					if(serviser != null) {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete Servisera?" , serviser.getKorIme() + " - Potvrda brisanja" , JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							poslovnalogika.getListaServisera().remove(serviser);
							model.removeRow(red);
							poslovnalogika.upisiUFajlServisera();
						}
						else {
							JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog Servisera","Greska!", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				
			}
		
		});
			
		btnRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			ServiserProzor.this.dispose();
			ServiserProzor sp = new ServiserProzor(poslovnalogika);
			sp.setVisible(true);
			


			}
		});
	
	}
}



