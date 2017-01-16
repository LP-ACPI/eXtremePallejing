package Fabrique;

import DAO.I_CatalogueDAO;
import DAO.I_ProduitDAO;

public abstract class FabriqueAbstraiteDAO {
	
	private static FabriqueAbstraiteDAO instance;
	
	protected FabriqueAbstraiteDAO(){}
	
	public synchronized static FabriqueAbstraiteDAO getInstance(){
		if(instance == null){
			instance = new FabriqueDAORelationnel();
		}
		return instance;
	}

	public abstract I_ProduitDAO createProduitDAO();

	public abstract I_CatalogueDAO createCatalogueDAO();


}
