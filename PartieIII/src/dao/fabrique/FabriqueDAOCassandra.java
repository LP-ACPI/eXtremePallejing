package dao.fabrique;

import dao.ConnexionDAOCassandra;
import dao.DAOException;
import dao.catalogue.CatalogueDAOCassandra;
import dao.catalogue.I_CatalogueDAO;
import dao.produit.I_ProduitDAO;
import dao.produit.ProduitDAOCassandra;

public class FabriqueDAOCassandra extends FabriqueAbstraiteDAO {

	@Override
	public I_ProduitDAO createProduitDAO() {
		try {
			return new ProduitDAOCassandra(ConnexionDAOCassandra.getInstance().getConnexion());
		} catch (DAOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public I_CatalogueDAO createCatalogueDAO() {
		try {
			return new CatalogueDAOCassandra(ConnexionDAOCassandra.getInstance().getConnexion());
		} catch (DAOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
