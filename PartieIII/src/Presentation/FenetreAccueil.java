package Presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Application.CataloguesObservables;
import Application.FrontController;
import Application.ObserverCatalogues;

@SuppressWarnings("serial")
public class FenetreAccueil extends JFrame implements ActionListener, ObserverCatalogues, WindowListener {

	private JButton btAjouter, btSupprimer, btSelectionner;
	private JTextField txtAjouter;
	private JLabel lbNbCatalogues;
	private JComboBox<String> cmbSupprimer, cmbSelectionner;
	private TextArea taDetailCatalogues;

	public FenetreAccueil() {
		setTitle("Catalogues");
		setBounds(500, 500, 200, 125);
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

		modifierListesCatalogues(FrontController.listerCatalogues());
		modifierDetailCatalogues(FrontController.listerDetailsCatalogues());
		modifierNbCatalogues(FrontController.nombreDeCatalogues());

		addWindowListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btAjouter) {
			String texteAjout = txtAjouter.getText();
			if (!texteAjout.equals("")) {
				System.out.println("ajouter le catalogue " + texteAjout);
				FrontController.ajouterCatalogue(texteAjout);
				txtAjouter.setText(null);
			}
		}
		if (e.getSource() == btSupprimer) {
			String texteSupprime = (String) cmbSupprimer.getSelectedItem();
			if (texteSupprime != null) {
				System.out.println("supprime catalogue " + texteSupprime);
				FrontController.supprimerCatalogue(texteSupprime);
			}
		}
		if (e.getSource() == btSelectionner) {
			String texteSelection = (String) cmbSelectionner.getSelectedItem();
			if (texteSelection != null) {
				System.out.println("selection du catalogue " + texteSelection);
				FrontController.selectionnerCatalogue(texteSelection);
				this.dispose();
				new FenetrePrincipale();
			}
		}
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		System.out.println("Au revoir");
		FrontController.quit();
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
	public void mettreAJour(CataloguesObservables catalogues) {
		int nombreDeCatalogues = catalogues.getNombreCatalogues();
		String[] detailsCatalogues = new String[nombreDeCatalogues];
		String[] nomsCatalogues = catalogues.getNomsCatalogues();
		String[] nombresDeProduitParCatalogue = catalogues.getNombresProduitsCatalogues();
		for (int i = 0; i < nombreDeCatalogues; i++)
			detailsCatalogues[i] = nomsCatalogues[i] + " : " + nombresDeProduitParCatalogue[i] + " produits";

		modifierNbCatalogues(nombreDeCatalogues);
		modifierDetailCatalogues(detailsCatalogues);
		modifierListesCatalogues(nomsCatalogues);
	}
}
