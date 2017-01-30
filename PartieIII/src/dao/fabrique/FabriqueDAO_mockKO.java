package dao.fabrique;

import dao.catalogue.CatalogueDAO_mockKO;
import dao.catalogue.I_CatalogueDAO;
import dao.produit.I_ProduitDAO;
import dao.produit.ProduitDAO_mockKO;

public class FabriqueDAO_mockKO extends FabriqueAbstraiteDAO{

	@Override
	public I_ProduitDAO createProduitDAO() {
		return new ProduitDAO_mockKO();
	}

	@Override
	public I_CatalogueDAO createCatalogueDAO() {
		return new CatalogueDAO_mockKO();
	}

}