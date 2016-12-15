package Presentation;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Application.XPControlStock;

public class FenetreAchat extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btAchat;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
	
	XPControlStock xpCS;

	public FenetreAchat(XPControlStock xpIN) {
		setTitle("Achat");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btAchat = new JButton("Achat");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(xpIN.listeNomsProduits());
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantité achetée"));
		contentPane.add(txtQuantite);
		contentPane.add(btAchat);

		btAchat.addActionListener(this);

		this.setVisible(true);
		
		xpCS = xpIN;
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btAchat){
			if(!xpCS.XPApprovisionnerStock(
					combo.getSelectedItem().toString(),
					Integer.parseInt(txtQuantite.getText())
				)){
				JOptionPane.showMessageDialog(this,
					    "Si vous avez ouvert cette fenêtre, c'est pour "
					    + "acheter des produits",
					    "Valeur non valide",
					    JOptionPane.WARNING_MESSAGE);
			}
			  
		}
		this.dispose();
	}

}