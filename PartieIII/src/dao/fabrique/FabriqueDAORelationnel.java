package dao.fabrique;

import dao.ConnexionDAORelationnel;
import dao.DAOException;
import dao.catalogue.CatalogueDAORelationnel;
import dao.catalogue.I_CatalogueDAO;
import dao.produit.I_ProduitDAO;
import dao.produit.ProduitDAORelationnel;

public class FabriqueDAORelationnel extends FabriqueAbstraiteDAO{

	@Override
	public I_ProduitDAO createProduitDAO() throws DAOException  {
			return new ProduitDAORelationnel(ConnexionDAORelationnel.getInstance().getConnexion());

	}

	@Override
	public I_CatalogueDAO createCatalogueDAO() throws DAOException{
			return new CatalogueDAORelationnel(ConnexionDAORelationnel.getInstance().getConnexion());

	}
}
