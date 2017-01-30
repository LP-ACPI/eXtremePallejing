package Presentation;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Application.ControleurFrontal;
import Application.ControleurStock;

@SuppressWarnings("serial")
public class FenetreAchat extends JFrame implements ActionListener {

	private JButton btAchat;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
	private ControleurFrontal controlFrontal;
	

	public FenetreAchat() {
		setTitle("Achat");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btAchat = new JButton("Achat");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(ControleurStock.listeNomsProduits());
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantité achetée"));
		contentPane.add(txtQuantite);
		contentPane.add(btAchat);

		btAchat.addActionListener(this);

		this.setVisible(true);
		controlFrontal = ControleurFrontal.getInstance();
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btAchat){
			try {
				String nomProduit = combo.getSelectedItem().toString();
				int quantite = Integer.parseInt(txtQuantite.getText());
				
				if(controlFrontal.approvisionnerStock(nomProduit,quantite)){
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(this,
						    "Merci d'entrer une valeur entière positive.",
						    "Valeur non valide !",
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
