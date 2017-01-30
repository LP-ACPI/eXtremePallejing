package dao.produit;

import java.util.List;

import metier.I_Catalogue;
import metier.I_Produit;

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
	public I_Produit read(String nomProduit) {
		return null;
	}

	@Override
	public List<I_Produit> readAll() {
		return null;
	}

	@Override
	public void setCatalogue(I_Catalogue catalog) {	}

	@Override
	public int catalogsProductCount() {	return 0;
	}

}
