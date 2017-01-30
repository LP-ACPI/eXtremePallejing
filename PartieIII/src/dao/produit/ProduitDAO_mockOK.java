package dao.produit;

import java.util.ArrayList;
import java.util.List;

import dao.DAOException;
import metier.I_Catalogue;
import metier.I_Produit;
import metier.Produit;

public class ProduitDAO_mockOK implements I_ProduitDAO {

	@Override
	public boolean create(I_Produit p) {
		return true;
	}

	@Override
	public boolean update(I_Produit p) {
		return true;
	}

	@Override
	public boolean delete(I_Produit p) {
		return true;
	}

	@Override
	public I_Produit read(String nomProduit) throws DAOException {
		return new Produit("bidon",1,1);
	}

	@Override
	public List<I_Produit> readAll() {
		return new ArrayList<I_Produit>();
	}

	@Override
	public void setCatalogue(I_Catalogue catalog) {	}

	@Override
	public int catalogsProductCount() {	return 0;
	}

}
