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

import enumeracija.Marka_automobila;
import enumeracija.model_automobila;
import guiZaIzmjenuIDodavanje.ServisDodavanje;
import guiZaIzmjenuIDodavanje.ServisniDioDodavanje;
import main.PoslovnaLogika;
import servis.Servis;
import servis.ServisniDio;

public class ServisniDioProzor extends JFrame {
	
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
	
	public ServisniDioProzor(PoslovnaLogika poslovnaLogika) {
		this.poslovnalogika = poslovnaLogika;
		setTitle("ServisniDio");
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
				"Naziv_dijela","Cijena", "Marka","Model"
		
		};
		Object[][] sadrzaj = new Object[poslovnalogika.getListaServisa().size()][zaglavlje.length];
		
		
		for(int i=0; i<poslovnalogika.getListaServisniDio().size(); i++) {
			ServisniDio servisniDio = poslovnalogika.getListaServisniDio().get(i);
			
			sadrzaj[i][0] = servisniDio.getNaziv_dijela();
			sadrzaj[i][1] = servisniDio.getCijena();
			sadrzaj[i][2] = servisniDio.getMarka();
			sadrzaj[i][3] = servisniDio.getModel();
		
			
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
				ServisniDioDodavanje sdd = new ServisniDioDodavanje(poslovnalogika, null);
				sdd.setVisible(true);
				
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
					ServisniDio servisniDio = poslovnalogika.nadjiServisniDioPoId(id);
					if(servisniDio != null) {
						ServisniDioDodavanje sdd = new ServisniDioDodavanje(poslovnalogika, servisniDio);
						sdd.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani servisni dio!", "Greska!", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
	}
}
