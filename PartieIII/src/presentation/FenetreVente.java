package presentation;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import application.ControleurStock;
import dao.DAOException;

@SuppressWarnings("serial")
public class FenetreVente extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private JButton btVente;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
	
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
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btVente){
			try {
				String selectionProduit = combo.getSelectedItem().toString();
				int qteAchat = Integer.parseInt(txtQuantite.getText());
				
				if(ControleurStock.XPLiquiderStock(selectionProduit,qteAchat)){
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(this,
							"Merci d'entrer une valeur positive"
						    + "\net de vérifier que vous avez assez de ce produit en stock",
						    "Valeur non valide",
						    JOptionPane.WARNING_MESSAGE);
					txtQuantite.setText("0");
				}
			} catch (NumberFormatException exceptioni) {
				JOptionPane.showMessageDialog(this,
					    "Merci de remplir avec des valeurs valides",
					    "Valeurs non valides",
					    JOptionPane.WARNING_MESSAGE);			
		    } catch(DAOException | HeadlessException exception){
		    	JOptionPane.showMessageDialog(this,
						exception.getMessage(),
					    "Erreur !",
					    JOptionPane.WARNING_MESSAGE);
				exception.printStackTrace();
		    }
		}
	}

}
