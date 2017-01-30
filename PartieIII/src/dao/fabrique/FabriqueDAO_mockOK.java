package dao.fabrique;

import dao.catalogue.CatalogueDAO_mockOK;
import dao.catalogue.I_CatalogueDAO;
import dao.produit.I_ProduitDAO;
import dao.produit.ProduitDAO_mockOK;

public class FabriqueDAO_mockOK extends FabriqueAbstraiteDAO{

	@Override
	public I_ProduitDAO createProduitDAO() {
		return new ProduitDAO_mockOK();
	}

	@Override
	public I_CatalogueDAO createCatalogueDAO() {
		return new CatalogueDAO_mockOK();
	}

}
