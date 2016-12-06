package View;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Controller.XPControlProduits;
import Entities.Produit;

public class FenetreNouveauProduit extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8141806231992288500L;
	private JTextField txtPrixHT;
	private JTextField txtNom;
	private JTextField txtQte;
//	private JComboBox<String> combo;
	private JButton btValider;
	
	XPControlProduits xpcp;

//	public FenetreNouveauProduit(String[] lesCategories) {
	public FenetreNouveauProduit(XPControlProduits xpIn) {	

		setTitle("Creation Produit");
		setBounds(500, 500, 200, 250);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());

		JLabel labNom = new JLabel("Nom produit");
		JLabel labPrixHT = new JLabel("Prix Hors Taxe");
		JLabel labQte = new JLabel("Quantité en stock");
//		JLabel labCategorie = new JLabel("Categorie");
		contentPane.add(labNom);
		txtNom = new JTextField(15);
		contentPane.add(txtNom);
		contentPane.add(labPrixHT);
		txtPrixHT = new JTextField(15);
		contentPane.add(txtPrixHT);
		contentPane.add(labQte);
		txtQte = new JTextField(15);
		contentPane.add(txtQte);

//		combo = new JComboBox<String>(lesCategories);
//		combo.setPreferredSize(new Dimension(100, 20));
//		contentPane.add(labCategorie);
//		contentPane.add(combo);

		
		btValider = new JButton("Valider");
		contentPane.add(btValider);

		btValider.addActionListener(this);
		setVisible(true);
		
		xpcp = xpIn;
	}

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btValider){
//			if(txtNom.isEmpty() ||)
			String nom = txtNom.getText();
			int pxHT = Integer.parseInt(txtPrixHT.getText());
			int qte = Integer.parseInt(txtQte.getText());
			Produit p = new Produit(nom,pxHT,qte);
			xpcp.XPAjouterProduit(p);
		}
		
		this.dispose();
	}

}