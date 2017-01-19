package dao.fabrique;

import dao.catalogue.CatalogueDAOXML;
import dao.catalogue.I_CatalogueDAO;
import dao.produit.I_ProduitDAO;
import dao.produit.ProduitDAOXML_Adapter;

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
