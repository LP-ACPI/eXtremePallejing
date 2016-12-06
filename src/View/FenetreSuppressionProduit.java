package View;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Controller.XPControlProduits;

public class FenetreSuppressionProduit extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btSupprimer;
	private JComboBox<String> combo;
	
	XPControlProduits xpcp;
	
	public FenetreSuppressionProduit(XPControlProduits xpIn) {
		
		setTitle("Suppression produit");
		setBounds(500, 500, 200, 105);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btSupprimer = new JButton("Supprimer");

		combo = new JComboBox<String>(xpIn.listeNomsProduits());
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(btSupprimer);

		btSupprimer.addActionListener(this);
		
		xpcp = xpIn;

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btSupprimer){
			xpcp.XPEnleverProduit(combo.getSelectedItem().toString());
		}
		this.dispose();
	}

}
