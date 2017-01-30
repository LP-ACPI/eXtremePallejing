package dao.catalogue;

import java.util.ArrayList;
import java.util.List;

import dao.DAOException;
import metier.Catalogue;
import metier.I_Catalogue;

public class CatalogueDAO_mockOK implements I_CatalogueDAO{

	@Override
	public boolean create(I_Catalogue catalogue) {
		return true;
	}

	@Override
	public boolean update(I_Catalogue catalogue) {
		return true;
	}

	@Override
	public boolean delete(I_Catalogue catalogue) {
		return true;
	}

	@Override
	public I_Catalogue read(String nomCatalogue) throws DAOException {
		return new Catalogue("bidon");
	}

	@Override
	public List<I_Catalogue> readAll() {
		return new ArrayList<I_Catalogue>();
	}

	@Override
	public int getProductCount(I_Catalogue catalogue) throws DAOException {
		return 0;
	}

}
