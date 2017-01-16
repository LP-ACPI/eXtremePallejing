package Fabrique;

import DAO.CatalogueDAORelationnel;
import DAO.ConnexionDAORelationnel;
import DAO.I_CatalogueDAO;
import DAO.I_ProduitDAO;
import DAO.ProduitDAOrelationnel;

public class FabriqueDAORelationnel extends FabriqueAbstraiteDAO{

	@Override
	public I_ProduitDAO createProduitDAO() {
		return new ProduitDAOrelationnel(ConnexionDAORelationnel.getInstance().getConnexion());
	}

	@Override
	public I_CatalogueDAO createCatalogueDAO() {
		return new CatalogueDAORelationnel(ConnexionDAORelationnel.getInstance().getConnexion());

	}
}
