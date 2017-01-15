package Presentation;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Application.XPControlProduits;
import Metier.I_Produit;
import Metier.Produit;

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
	

	public FenetreNouveauProduit() {	

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
		
	}

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btValider){
			String nom = txtNom.getText();
			try {

				double pxHT = Double.parseDouble(txtPrixHT.getText());
				int qte = Integer.parseInt(txtQte.getText());

				I_Produit p = new Produit(nom,pxHT,qte);
				if(XPControlProduits.XPAjouterProduit(p)){
					JOptionPane.showMessageDialog(this,
						    "Produit " + nom
						    + " créé!",
						    "Suppression",
						    JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(this,
						    "Apparemment ce produit existe déjà",
						    "Produit non valide",
						    JOptionPane.WARNING_MESSAGE);
				}
		    } catch (NumberFormatException i) {
					JOptionPane.showMessageDialog(this,
						    "Merci de remplir avec des valeurs valides",
						    "Valeurs non valides",
						    JOptionPane.WARNING_MESSAGE);
				
		    }
			
		}
		
	}

}