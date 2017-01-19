package presentation;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import application.ControleurStock;

public class FenetreAchat extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btAchat;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
	

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
		
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btAchat){
			if(ControleurStock.XPApprovisionnerStock(
					combo.getSelectedItem().toString(),
					Integer.parseInt(txtQuantite.getText())
				)) {
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this,
					    "Merci d'entrer une valeur valide :\n"
					    + " un entier naturel",
					    "Valeur non valide !",
					    JOptionPane.WARNING_MESSAGE);
				txtQuantite.setText("0");
			}
			  
		}
	}

}