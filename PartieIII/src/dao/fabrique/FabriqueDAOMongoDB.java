package dao.fabrique;

import dao.ConnexionDAOMongoDB;
import dao.catalogue.CatalogueDAOMongoDB;
import dao.catalogue.I_CatalogueDAO;
import dao.produit.I_ProduitDAO;
import dao.produit.ProduitDAOMongoDB;

public class FabriqueDAOMongoDB extends FabriqueAbstraiteDAO {

	@Override
	public I_ProduitDAO createProduitDAO() {
		return new ProduitDAOMongoDB(ConnexionDAOMongoDB.getInstance());
	}

	@Override
	public I_CatalogueDAO createCatalogueDAO() {
		return new CatalogueDAOMongoDB(ConnexionDAOMongoDB.getInstance());
	}

}
