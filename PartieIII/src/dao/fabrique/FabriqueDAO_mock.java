package dao.fabrique;

import dao.catalogue.CatalogueDAO_mock;
import dao.catalogue.I_CatalogueDAO;
import dao.produit.I_ProduitDAO;
import dao.produit.ProduitDAO_mock;

public class FabriqueDAO_mock extends FabriqueAbstraiteDAO{

	@Override
	public I_ProduitDAO createProduitDAO() {
		return new ProduitDAO_mock();
	}

	@Override
	public I_CatalogueDAO createCatalogueDAO() {
		return new CatalogueDAO_mock();
	}

}
