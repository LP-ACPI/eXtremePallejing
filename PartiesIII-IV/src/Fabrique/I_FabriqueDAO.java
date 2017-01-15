package Fabrique;

import DAO.I_ProduitDAO;

public interface I_FabriqueDAO {
	
	public abstract I_ProduitDAO createProduitDAO();
	
//	public abstract I_CatalogueDAO createCatalogueDAO();
}
