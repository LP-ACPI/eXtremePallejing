package dao.fabrique;

import dao.DAOException;
import dao.catalogue.I_CatalogueDAO;
import dao.produit.I_ProduitDAO;

public abstract class FabriqueAbstraiteDAO {
	
	private static FabriqueAbstraiteDAO instance;

	protected FabriqueAbstraiteDAO(){}
	
	public synchronized static FabriqueAbstraiteDAO getInstance(){
		if(instance == null)
			instance = new FabriqueDAOMongoDB();
		return instance;
	}
	
	public static void setInstance(FabriqueAbstraiteDAO instance) {
		FabriqueAbstraiteDAO.instance = instance;
	}

	public abstract I_ProduitDAO createProduitDAO() throws DAOException;

	public abstract I_CatalogueDAO createCatalogueDAO() throws DAOException;


}
