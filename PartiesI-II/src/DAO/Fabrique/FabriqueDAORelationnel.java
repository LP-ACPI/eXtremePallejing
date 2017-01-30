package DAO.Fabrique;

import DAO.ConnexionDAORelationnel;
import DAO.I_ProduitDAO;
import DAO.ProduitDAORelationnel;

public class FabriqueDAORelationnel{

	public static I_ProduitDAO createProduitDAO() {
		return new ProduitDAORelationnel(ConnexionDAORelationnel.getInstance().getConnexion());
	}

}
