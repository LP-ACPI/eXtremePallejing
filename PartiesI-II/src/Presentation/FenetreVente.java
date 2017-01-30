package Presentation;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Application.ControleurFrontal;
import Application.ControleurStock;

@SuppressWarnings("serial")
public class FenetreVente extends JFrame implements ActionListener {

	private JButton btVente;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
	private ControleurFrontal controlFrontal;
	
	public FenetreVente() {
		setTitle("Vente");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btVente = new JButton("Vente");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(ControleurStock.listeNomsProduits());
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantité vendue"));
		contentPane.add(txtQuantite);
		contentPane.add(btVente);

		btVente.addActionListener(this);
		this.setVisible(true);
		controlFrontal = ControleurFrontal.getInstance();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btVente){
			try {
				String nomProduit = combo.getSelectedItem().toString();
				int quantite = Integer.parseInt(txtQuantite.getText());
				
				if(controlFrontal.liquiderStock(nomProduit,quantite)){
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(this,
							"Merci d'entrer une valeur positive\n"
						    + "et de vérifier que vous avez assez de ce produit en stock",
						    "Valeur non valide",
						    JOptionPane.WARNING_MESSAGE);
					txtQuantite.setText("0");
				}
			 } catch (NumberFormatException i) {
					JOptionPane.showMessageDialog(this,
						    "Merci d'entrer des valeurs numériques",
						    "Valeur non valide",
						    JOptionPane.WARNING_MESSAGE);
		    }
		}
	}

}
