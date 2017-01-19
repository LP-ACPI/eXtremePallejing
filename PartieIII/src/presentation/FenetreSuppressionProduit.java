package presentation;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import application.ControleurProduits;

public class FenetreSuppressionProduit extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btSupprimer;
	private JComboBox<String> combo;
		
	public FenetreSuppressionProduit() {
		
		setTitle("Suppression produit");
		setBounds(500, 500, 200, 105);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btSupprimer = new JButton("Supprimer");

		combo = new JComboBox<String>(ControleurProduits.listeNomsProduits());
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(btSupprimer);

		btSupprimer.addActionListener(this);
		

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btSupprimer){
			if(ControleurProduits.XPEnleverProduit(combo.getSelectedItem().toString())){
				JOptionPane.showMessageDialog(this,
					    "Produit " + combo.getSelectedItem().toString()
					    + " supprimé!",
					    "Suppression",
					    JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this,
					    "Pas de produit à supprimer",
					    "Suppression",
					    JOptionPane.WARNING_MESSAGE);
			}
		}
	}

}
