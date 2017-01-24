package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import application.ControleurAccueil;
import application.observateurs.CatalogueInfosObservables;
import application.observateurs.ObserverInfosCatalogues;
import dao.DAOException;

@SuppressWarnings("serial")
public class FenetreAccueil extends JFrame 
		implements ActionListener, ObserverInfosCatalogues, WindowListener {

	private JButton btAjouter, btSupprimer, btSelectionner;
	private JTextField txtAjouter;
	private JLabel lbNbCatalogues;
	private JComboBox<String> cmbSupprimer, cmbSelectionner;
	private TextArea taDetailCatalogues;

	public FenetreAccueil() {
		setTitle("Catalogues");
		setBounds(500, 250, 200, 125);
		Container contentPane = getContentPane();
		JPanel panInfosCatalogues = new JPanel();
		JPanel panNbCatalogues = new JPanel();
		JPanel panDetailCatalogues = new JPanel();
		JPanel panGestionCatalogues = new JPanel();
		JPanel panAjouter = new JPanel();
		JPanel panSupprimer = new JPanel();
		JPanel panSelectionner = new JPanel();
		panNbCatalogues.setBackground(Color.white);
		panDetailCatalogues.setBackground(Color.white);
		panAjouter.setBackground(Color.gray);
		panSupprimer.setBackground(Color.lightGray);
		panSelectionner.setBackground(Color.gray);

		panNbCatalogues.add(new JLabel("Nous avons actuellement : "));
		lbNbCatalogues = new JLabel();
		panNbCatalogues.add(lbNbCatalogues);

		taDetailCatalogues = new TextArea();
		taDetailCatalogues.setEditable(false);
		new JScrollPane(taDetailCatalogues);
		taDetailCatalogues.setPreferredSize(new Dimension(300, 100));
		panDetailCatalogues.add(taDetailCatalogues);

		panAjouter.add(new JLabel("Ajouter un catalogue : "));
		txtAjouter = new JTextField(10);
		panAjouter.add(txtAjouter);
		btAjouter = new JButton("Ajouter");
		panAjouter.add(btAjouter);

		panSupprimer.add(new JLabel("Supprimer un catalogue : "));
		cmbSupprimer = new JComboBox<String>();
		cmbSupprimer.setPreferredSize(new Dimension(100, 20));
		panSupprimer.add(cmbSupprimer);
		btSupprimer = new JButton("Supprimer");
		panSupprimer.add(btSupprimer);

		panSelectionner.add(new JLabel("Selectionner un catalogue : "));
		cmbSelectionner = new JComboBox<String>();
		cmbSelectionner.setPreferredSize(new Dimension(100, 20));
		panSelectionner.add(cmbSelectionner);
		btSelectionner = new JButton("Selectionner");
		panSelectionner.add(btSelectionner);

		panGestionCatalogues.setLayout(new BorderLayout());
		panGestionCatalogues.add(panAjouter, "North");
		panGestionCatalogues.add(panSupprimer);
		panGestionCatalogues.add(panSelectionner, "South");

		panInfosCatalogues.setLayout(new BorderLayout());
		panInfosCatalogues.add(panNbCatalogues, "North");
		panInfosCatalogues.add(panDetailCatalogues, "South");

		contentPane.add(panInfosCatalogues, "North");
		contentPane.add(panGestionCatalogues, "South");
		pack();

		btAjouter.addActionListener(this);
		btSupprimer.addActionListener(this);
		btSelectionner.addActionListener(this);

		modifierListesCatalogues(ControleurAccueil.listerCatalogues());
		modifierDetailCatalogues(ControleurAccueil.listerDetailsCatalogues());
		modifierNbCatalogues(ControleurAccueil.enumererCatalogues());

		addWindowListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btAjouter) {
			String texteAjout = txtAjouter.getText();
			if (!texteAjout.equals("")) {					
				try {
					if(ControleurAccueil.ajouterCatalogue(texteAjout)) {
						System.out.println("ajouter le catalogue " + texteAjout);
					} else {
						System.out.println("ajout impossible");
						JOptionPane.showMessageDialog(this,
							    "Ce catalogue existe déjà !",
							    "ajout Catalogue impossible !",
							    JOptionPane.WARNING_MESSAGE);
					}
				} catch (HeadlessException | DAOException exception) {
					JOptionPane.showMessageDialog(this,
							exception.getMessage(),
						    "Erreur !",
						    JOptionPane.WARNING_MESSAGE);
					exception.printStackTrace();
				}
				txtAjouter.setText(null);
			}
		}
		if (e.getSource() == btSupprimer) {
			String texteSupprime = (String) cmbSupprimer.getSelectedItem();
			if (texteSupprime != null) {
				System.out.println("supprime catalogue " + texteSupprime);
				try {
					ControleurAccueil.supprimerCatalogue(texteSupprime);
				} catch (DAOException exception) {
					JOptionPane.showMessageDialog(this,
							exception.getMessage(),
						    "Erreur !",
						    JOptionPane.WARNING_MESSAGE);
					exception.printStackTrace();
				}
			}
		}
		if (e.getSource() == btSelectionner) {
			String texteSelection = (String) cmbSelectionner.getSelectedItem();
			if (texteSelection != null) {
				System.out.println("selection du catalogue " + texteSelection);
				try {
					ControleurAccueil.selectionnerCatalogue(texteSelection);
				} catch (DAOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				new FenetrePrincipale();
				this.dispose();
			}
		}
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		System.out.println("Au revoir");
		ControleurAccueil.quit();
		System.exit(0);
	}

	public void windowActivated(WindowEvent arg0) {
	}

	public void windowClosed(WindowEvent arg0) {
	}

	public void windowDeactivated(WindowEvent arg0) {
	}

	public void windowDeiconified(WindowEvent arg0) {
	}

	public void windowIconified(WindowEvent arg0) {
	}

	public void windowOpened(WindowEvent arg0) {
	}

	private void modifierListesCatalogues(String[] nomsCatalogues) {
		cmbSupprimer.removeAllItems();
		cmbSelectionner.removeAllItems();
		if (nomsCatalogues != null)
			for (int i = 0; i < nomsCatalogues.length; i++) {
				cmbSupprimer.addItem(nomsCatalogues[i]);
				cmbSelectionner.addItem(nomsCatalogues[i]);
			}
	}

	private void modifierNbCatalogues(int nb) {
		lbNbCatalogues.setText(nb + " catalogues");
	}

	private void modifierDetailCatalogues(String[] detailCatalogues) {
		String detailsCataloguesOutput = ""; // règlement de quelques soucis d'affichage
		if (detailCatalogues.length != 0) {
			for (int i = 0; i < detailCatalogues.length; i++)
				detailsCataloguesOutput += detailCatalogues[i] + "\n";
		} else
			detailsCataloguesOutput = " ";
		taDetailCatalogues.setText(detailsCataloguesOutput);
	}

	@Override
	public void mettreAJour(CatalogueInfosObservables objetObserve) {	
		modifierNbCatalogues(objetObserve.getNombreCatalogues());
		modifierDetailCatalogues(objetObserve.getDetailsCatalogues());
		modifierListesCatalogues(objetObserve.getNomsCatalogues());
		
	}

}
