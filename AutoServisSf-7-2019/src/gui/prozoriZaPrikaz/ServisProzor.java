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


import guiZaIzmjenuIDodavanje.ServisDodavanje;
import main.PoslovnaLogika;
import osoba.Administrator;

import servis.Servis;

public class ServisProzor extends JFrame {
	
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
	
	public ServisProzor(PoslovnaLogika poslovnaLogika) {
		this.poslovnalogika = poslovnaLogika;
		setTitle("Servisi");
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
				"Automobil","Serviser", "Termin", "Opis", "Lista_dijelova", "Status"
		
		};
		Object[][] sadrzaj = new Object[poslovnalogika.getListaServisa().size()][zaglavlje.length];
		DateFormat konverter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		
		for(int i=0; i<poslovnalogika.getListaServisa().size(); i++) {
			Servis servis = poslovnalogika.getListaServisa().get(i);
			String termin = konverter.format(servis.getTermin());
			sadrzaj[i][0] = servis.getAutomobil();
			sadrzaj[i][1] = servis.getServiser();
			sadrzaj[i][2] = servis.getTermin();
			sadrzaj[i][3] = servis.getOpis();
			sadrzaj[i][4] = servis.getLista_dijelova();
			sadrzaj[i][5] = servis.getStatus();
		
			
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
				ServisDodavanje sd = new ServisDodavanje(poslovnalogika, null);
				sd.setVisible(true);
				
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
					Servis servis = poslovnalogika.nadjiServisPoId(id);
					if(servis != null) {
						ServisDodavanje sd = new ServisDodavanje(poslovnalogika, servis);
						sd.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani servis!", "Greska!", JOptionPane.ERROR_MESSAGE);
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
					Servis servis = poslovnalogika.nadjiServisPoId(id);
					if(servis != null) {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete servis" , servis.getId() + " - Potvrda brisanja" , JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							poslovnalogika.getListaServisa().remove(servis);
							model.removeRow(red);
							poslovnalogika.upisiUFajlServis();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani servis","Greska!", JOptionPane.ERROR_MESSAGE);
						
					}
				}
				
			}
		});

btnRefresh.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
	ServisProzor.this.dispose();
	ServisProzor sp = new ServisProzor(poslovnalogika);
	sp.setVisible(true);
	


	}
});

}


		
		
	
	

	}

