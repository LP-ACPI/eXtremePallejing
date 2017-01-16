package Fabrique;

import DAO.CatalogueDAOXML;
import DAO.I_CatalogueDAO;
import DAO.I_ProduitDAO;
import DAO.ProduitDAOXML_Adapter;

public class FabriqueDAOXML extends FabriqueAbstraiteDAO {

	@Override
	public I_ProduitDAO createProduitDAO() {
		return new ProduitDAOXML_Adapter();
	}

	@Override
	public I_CatalogueDAO createCatalogueDAO() {
		return new CatalogueDAOXML();
	}
}
