package Fabrique;

import DAO.ConnexionDAORelationnel;
import DAO.I_ProduitDAO;
import DAO.ProduitDAOrelationnel;

public class FabriqueDAORelationnel{

	public static I_ProduitDAO createProduitDAO() {
		return new ProduitDAOrelationnel(ConnexionDAORelationnel.getInstance().getConnexion());
	}

}
