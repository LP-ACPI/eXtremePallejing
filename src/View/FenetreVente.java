package View;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Controller.XPControlStock;

public class FenetreVente extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btVente;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
	
	XPControlStock xpCS;

	public FenetreVente(XPControlStock xpIN) {
		setTitle("Vente");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btVente = new JButton("Vente");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(xpIN.listeNomsProduits());
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantité vendue"));
		contentPane.add(txtQuantite);
		contentPane.add(btVente);

		btVente.addActionListener(this);
		this.setVisible(true);
		
		xpCS = xpIN;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btVente){
			if(!xpCS.XPLiquiderStock(
					combo.getSelectedItem().toString(),
					Integer.parseInt(txtQuantite.getText())
				)){
				JOptionPane.showMessageDialog(this,
						"Merci d'entrer une valeur positive"
					    + "\net de vérifier que vous avez assez de ce produit en stock",
					    "Valeur non valide",
					    JOptionPane.WARNING_MESSAGE);
			}
		}
		this.dispose();
	}

}
