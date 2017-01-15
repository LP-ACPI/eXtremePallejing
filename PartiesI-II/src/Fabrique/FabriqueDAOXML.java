package Fabrique;

import DAO.I_ProduitDAO;
import DAO.ProduitDAOXML_Adapter;

public class FabriqueDAOXML{

	public static I_ProduitDAO createProduitDAO() {
		return new ProduitDAOXML_Adapter();
	}
}
