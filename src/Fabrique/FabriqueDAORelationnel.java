package Fabrique;

import DAO.I_ProduitDAO;
import DAO.ProduitDAOrelationnel;

public class FabriqueDAORelationnel extends FabriqueAbstraiteDAO{

	@Override
	public I_ProduitDAO createProduitDAO() {
		return new ProduitDAOrelationnel();
	}

//	@Override
//	public I_Catalogue createCatalogueDAO() {
//		// TODO Auto-generated method stub
//		
//	}

}
