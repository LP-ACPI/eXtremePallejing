package Fabrique;

import DAO.I_ProduitDAO;
import DAO.ProduitDAOXML_Adapter;

public class FabriqueDAOXML extends FabriqueAbstraiteDAO{

	@Override
	public I_ProduitDAO createProduitDAO() {
		return new ProduitDAOXML_Adapter();
	}
	
//	@Override
//	public I_Catalogue createCatalogueDAO() {
//		// TODO Auto-generated method stub
//		
//	}
}
